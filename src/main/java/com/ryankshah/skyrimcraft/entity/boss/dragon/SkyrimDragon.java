package com.ryankshah.skyrimcraft.entity.boss.dragon;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.LookControl;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.Vec3;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

import javax.annotation.Nullable;
import java.util.EnumSet;
import java.util.List;

public class SkyrimDragon extends FlyingMob implements GeoEntity, Enemy
{
    private static final EntityDataAccessor<Integer> PREV_ANIMATION_STATE = SynchedEntityData.defineId(SkyrimDragon.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> ANIMATION_STATE = SynchedEntityData.defineId(SkyrimDragon.class, EntityDataSerializers.INT);

    public static final float FLAP_DEGREES_PER_TICK = 7.448451F;
    public static final int TICKS_PER_FLAP = Mth.ceil(24.166098F);

    private static Vec3 moveTargetPoint = Vec3.ZERO;
    private static BlockPos anchorPoint = BlockPos.ZERO;

    protected static final RawAnimation FLY_IDLE = RawAnimation.begin().thenLoop("animation.dragon.flyidle");
    protected static final RawAnimation GLIDE = RawAnimation.begin().thenLoop("animation.dragon.glide");
    protected static final RawAnimation STAND = RawAnimation.begin().thenLoop("animation.dragon.stand");
    protected static final RawAnimation WALK = RawAnimation.begin().thenLoop("animation.dragon.walk");
    protected static final RawAnimation BITE = RawAnimation.begin().thenPlay("animation.dragon.bite");
    protected static final RawAnimation FLY_SHOUT_BREATHE = RawAnimation.begin().thenPlay("animation.dragon.flyshoutbreathe");
    protected static final RawAnimation TAKE_OFF = RawAnimation.begin().thenPlay("animation.dragon.takeoff");
    protected static final RawAnimation LAND = RawAnimation.begin().thenPlay("animation.dragon.land");
    protected static final RawAnimation DEATH = RawAnimation.begin().thenPlay("animation.dragon.death");
    protected static final RawAnimation DEAD = RawAnimation.begin().thenLoop("animation.dragon.dead");

    private SkyrimDragon.Phase PHASE = Phase.FLY_IDLE;

    private final AnimatableInstanceCache geoCache = GeckoLibUtil.createInstanceCache(this);

    public SkyrimDragon(EntityType<? extends FlyingMob> type, Level worldIn) {
        super(type, worldIn);
        this.noCulling = true;
        this.xpReward = XP_REWARD_BOSS;

        this.moveControl = new SkyrimDragon.DragonMoveControl(this);
        this.lookControl = new SkyrimDragon.DragonLookControl(this);

        this.setPathfindingMalus(BlockPathTypes.WATER, -1.0F);
        this.setPathfindingMalus(BlockPathTypes.DANGER_FIRE, 16.0F);
        this.setPathfindingMalus(BlockPathTypes.DAMAGE_FIRE, -1.0F);
    }

    static enum Phase {
        FLY_IDLE,
        FLY_ATTACK,
        LAND_IDLE,
        LAND_WALK,
        LAND_ATTACK,
        TAKE_OFF,
        DEATH,
        DEAD;
    }

    public int getUniqueFlapTickOffset() {
        return this.getId() * 2;
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(1, new SkyrimDragon.CircleAroundAnchorGoal());
//        this.goalSelector.addGoal(1, new Goal);
//        this.goalSelector.addGoal(1, new FloatGoal(this));
//        //this.goalSelector.addGoal(4, new LeapAtTargetGoal(this, 0.4F));
//        this.goalSelector.addGoal(5, meleeGoal); //new MeleeAttackGoal(this, 1.0D, true));
//        this.goalSelector.addGoal(5, walkingGoal);
//        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 6.0F));
//        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
//        this.targetSelector.addGoal(2, new SprintToNearestAttackableTargetGoal<>(this, Player.class, true));
//        this.targetSelector.addGoal(2, new SprintToNearestAttackableTargetGoal<>(this, Animal.class, true));
    }

    @Override
    public boolean isFlapping() {
        return (this.getUniqueFlapTickOffset() + this.tickCount) % TICKS_PER_FLAP == 0;
    }

    @Override
    public void onFlap() {
        if (this.level().isClientSide && !this.isSilent()) {
            this.level()
                    .playLocalSound(
                            this.getX(),
                            this.getY(),
                            this.getZ(),
                            SoundEvents.ENDER_DRAGON_FLAP,
                            this.getSoundSource(),
                            5.0F,
                            0.8F + this.random.nextFloat() * 0.3F,
                            false
                    );
        }
    }

    @Override
    public void tick() {
        super.tick();
        if (this.level().isClientSide) {
            float f = Mth.cos((float)(this.getUniqueFlapTickOffset() + this.tickCount) * 7.448451F * (float) (Math.PI / 180.0) + (float) Math.PI);
            float f1 = Mth.cos((float)(this.getUniqueFlapTickOffset() + this.tickCount + 1) * 7.448451F * (float) (Math.PI / 180.0) + (float) Math.PI);
            if (f > 0.0F && f1 <= 0.0F) {
                this.level()
                        .playLocalSound(
                                this.getX(),
                                this.getY(),
                                this.getZ(),
                                SoundEvents.PHANTOM_FLAP,
                                this.getSoundSource(),
                                0.95F + this.random.nextFloat() * 0.05F,
                                0.95F + this.random.nextFloat() * 0.05F,
                                false
                        );
            }

            int i = 4; // dragon size
            float f2 = Mth.cos(this.getYRot() * (float) (Math.PI / 180.0)) * (1.3F + 0.21F * (float)i);
            float f3 = Mth.sin(this.getYRot() * (float) (Math.PI / 180.0)) * (1.3F + 0.21F * (float)i);
            float f4 = (0.3F + f * 0.45F) * ((float)i * 0.2F + 1.0F);
            this.level().addParticle(ParticleTypes.MYCELIUM, this.getX() + (double)f2, this.getY() + (double)f4, this.getZ() + (double)f3, 0.0, 0.0, 0.0);
            this.level().addParticle(ParticleTypes.MYCELIUM, this.getX() - (double)f2, this.getY() + (double)f4, this.getZ() - (double)f3, 0.0, 0.0, 0.0);
        }
    }

    @Override
    protected boolean shouldDespawnInPeaceful() {
        return true;
    }

    @Override
    protected void customServerAiStep() {
        super.customServerAiStep();
    }

    @Nullable
    @Override
    public Component getCustomName() {
        return super.getCustomName();
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 200.0f).add(Attributes.JUMP_STRENGTH, 0.5D).add(Attributes.FLYING_SPEED, 1.0D).add(Attributes.MOVEMENT_SPEED, 1.0D).add(Attributes.MAX_HEALTH, 200.0D).add(Attributes.ATTACK_DAMAGE, 9.0D).add(Attributes.FOLLOW_RANGE, 18.0D);
    }

    @Override
    protected float getBlockJumpFactor() {
        return super.getBlockJumpFactor();
    }

    private void hurt(List<Entity> pEntities) {
        for(Entity entity : pEntities) {
            if (entity instanceof LivingEntity) {
                entity.hurt(this.damageSources().mobAttack(this), 10.0F); //5 hearts of damage
                this.doEnchantDamageEffects(this, entity);
            }
        }
    }

    @Override
    public boolean addEffect(MobEffectInstance pEffectInstance, @Nullable Entity pEntity) {
        return false;
    }

    @Override
    protected boolean canRide(Entity pEntity) {
        return false;
    }

    /**
     * Returns false if this Entity can't move between dimensions. True if it can.
     */
    @Override
    public boolean canChangeDimensions() {
        return false;
    }

    @Override
    public boolean canAttack(LivingEntity pTarget) {
        return pTarget.canBeSeenAsEnemy();
    }

//    @Override
//    public float getWalkTargetValue(BlockPos pPos, LevelReader pLevel) {
//        return pLevel.getBlockState(pPos.below()).is(Blocks.GRASS_BLOCK) ? 10.0F : pLevel.getBrightness(LightLayer.BLOCK, pPos) - 0.5F;
//    }

    public boolean shouldDropExperience() {
        return true;
    }

    protected boolean shouldDropLoot() {
        return true;
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(ANIMATION_STATE, 0);
        this.entityData.define(PREV_ANIMATION_STATE, 0);

    }

    public void addAdditionalSaveData(CompoundTag p_213281_1_) {
        super.addAdditionalSaveData(p_213281_1_);
        if (p_213281_1_.contains("AX")) {
            this.anchorPoint = new BlockPos(p_213281_1_.getInt("AX"), p_213281_1_.getInt("AY"), p_213281_1_.getInt("AZ"));
        }
        p_213281_1_.putInt("AnimationState", this.getAnimationState());
        p_213281_1_.putInt("PrevAnimationState", this.getPrevAnimationState());
    }

    public void readAdditionalSaveData(CompoundTag p_70037_1_) {
        super.readAdditionalSaveData(p_70037_1_);
        p_70037_1_.putInt("AX", this.anchorPoint.getX());
        p_70037_1_.putInt("AY", this.anchorPoint.getY());
        p_70037_1_.putInt("AZ", this.anchorPoint.getZ());
        this.setAnimationState(p_70037_1_.getInt("AnimationState"));
        this.setPrevAnimationState(p_70037_1_.getInt("PrevAnimationState"));
    }

    @Override
    public boolean shouldRenderAtSqrDistance(double pDistance) {
        return true;
    }

    @Override
    public SoundSource getSoundSource() {
        return SoundSource.HOSTILE;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENDER_DRAGON_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return SoundEvents.ENDER_DRAGON_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENDER_DRAGON_DEATH;
    }

    @Override
    public MobType getMobType() {
        return MobType.UNDEFINED;
    }

    @Override
    public boolean canAttackType(EntityType<?> pType) {
        return true;
    }

    public void setAnimationState(int animationState) {
        setPrevAnimationState(this.getAnimationState());
        this.entityData.set(PREV_ANIMATION_STATE, this.getAnimationState());
        this.entityData.set(ANIMATION_STATE, animationState);
    }
    public int getAnimationState() {
        return this.entityData.get(ANIMATION_STATE);
    }

    public void setPrevAnimationState(int prevAnimationState) {
        this.entityData.set(PREV_ANIMATION_STATE, prevAnimationState);
    }
    public int getPrevAnimationState() { return this.entityData.get(PREV_ANIMATION_STATE); }

    @Override
    @Nullable
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor p_213386_1_, DifficultyInstance p_213386_2_, MobSpawnType p_213386_3_, @Nullable SpawnGroupData p_213386_4_, @Nullable CompoundTag p_213386_5_) {
        this.anchorPoint = this.blockPosition().above(20);
        this.setAnimationState(0);
        this.setPrevAnimationState(0);
        return super.finalizeSpawn(p_213386_1_, p_213386_2_, p_213386_3_, p_213386_4_, p_213386_5_);
    }



    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.geoCache;
    }

    private <E extends SkyrimDragon> PlayState dragonController(final software.bernie.geckolib.core.animation.AnimationState<SkyrimDragon> event) {
        AnimationController<SkyrimDragon> controller = event.getController();
        controller.transitionLength(0);

        if(event.isMoving() && isFlapping()) //isFlying
            return event.setAndContinue(FLY_IDLE);
        else if(event.isMoving() && !isFlapping() && !onGround())
            return event.setAndContinue(GLIDE);
        else if(event.isMoving() && onGround())
            return event.setAndContinue(WALK);
        else if(!event.isMoving() && onGround())
            return event.setAndContinue(STAND);
        else
            return event.setAndContinue(FLY_IDLE);
    }

    @Override
    public void registerControllers(final AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "dragon_controller", 0, this::dragonController));
    }

    class DragonLookControl extends LookControl {
        public DragonLookControl(Mob pMob) {
            super(pMob);
        }

        /**
         * Updates look
         */
        @Override
        public void tick() {
        }
    }

    class DragonMoveControl extends MoveControl {
        private float speed = 0.1F;

        public DragonMoveControl(Mob pMob) {
            super(pMob);
        }

        @Override
        public void tick() {
            if (SkyrimDragon.this.horizontalCollision) {
                SkyrimDragon.this.setYRot(SkyrimDragon.this.getYRot() + 180.0F);
                this.speed = 0.1F;
            }

            double d0 = SkyrimDragon.this.moveTargetPoint.x - SkyrimDragon.this.getX();
            double d1 = SkyrimDragon.this.moveTargetPoint.y - SkyrimDragon.this.getY();
            double d2 = SkyrimDragon.this.moveTargetPoint.z - SkyrimDragon.this.getZ();
            double d3 = Math.sqrt(d0 * d0 + d2 * d2);
            if (Math.abs(d3) > 1.0E-5F) {
                double d4 = 1.0 - Math.abs(d1 * 0.7F) / d3;
                d0 *= d4;
                d2 *= d4;
                d3 = Math.sqrt(d0 * d0 + d2 * d2);
                double d5 = Math.sqrt(d0 * d0 + d2 * d2 + d1 * d1);
                float f = SkyrimDragon.this.getYRot();
                float f1 = (float)Mth.atan2(d2, d0);
                float f2 = Mth.wrapDegrees(SkyrimDragon.this.getYRot() + 90.0F);
                float f3 = Mth.wrapDegrees(f1 * (180.0F / (float)Math.PI));
                SkyrimDragon.this.setYRot(Mth.approachDegrees(f2, f3, 4.0F) - 90.0F);
                SkyrimDragon.this.yBodyRot = SkyrimDragon.this.getYRot();
                if (Mth.degreesDifferenceAbs(f, SkyrimDragon.this.getYRot()) < 3.0F) {
                    this.speed = Mth.approach(this.speed, 1.8F, 0.005F * (1.8F / this.speed));
                } else {
                    this.speed = Mth.approach(this.speed, 0.2F, 0.025F);
                }

                float f4 = (float)(-(Mth.atan2(-d1, d3) * 180.0F / (float)Math.PI));
                SkyrimDragon.this.setXRot(f4);
                float f5 = SkyrimDragon.this.getYRot() + 90.0F;
                double d6 = (double)(this.speed * Mth.cos(f5 * (float) (Math.PI / 180.0))) * Math.abs(d0 / d5);
                double d7 = (double)(this.speed * Mth.sin(f5 * (float) (Math.PI / 180.0))) * Math.abs(d2 / d5);
                double d8 = (double)(this.speed * Mth.sin(f4 * (float) (Math.PI / 180.0))) * Math.abs(d1 / d5);
                Vec3 vec3 = SkyrimDragon.this.getDeltaMovement();
                SkyrimDragon.this.setDeltaMovement(vec3.add(new Vec3(d6, d8, d7).subtract(vec3).scale(0.2)));
            }
        }
    }

    abstract class DragonMoveTargetGoal extends Goal {
        public DragonMoveTargetGoal() {
            this.setFlags(EnumSet.of(Goal.Flag.MOVE));
        }

        protected boolean touchingTarget() {
            return SkyrimDragon.this.moveTargetPoint.distanceToSqr(SkyrimDragon.this.getX(), SkyrimDragon.this.getY(), SkyrimDragon.this.getZ()) < 4.0;
        }
    }

    class CircleAroundAnchorGoal extends SkyrimDragon.DragonMoveTargetGoal {
        private float angle;
        private float distance;
        private float height;
        private float clockwise;

        /**
         * Returns whether execution should begin. You can also read and cache any state necessary for execution in this method as well.
         */
        @Override
        public boolean canUse() {
            return SkyrimDragon.this.getTarget() == null; //|| SkyrimDragonEntity.this.attackPhase == SkyrimDragonEntity.AttackPhase.CIRCLE;
        }

        /**
         * Execute a one shot task or start executing a continuous task
         */
        @Override
        public void start() {
            this.distance = 5.0F + SkyrimDragon.this.random.nextFloat() * 10.0F;
            this.height = -4.0F + SkyrimDragon.this.random.nextFloat() * 9.0F;
            this.clockwise = SkyrimDragon.this.random.nextBoolean() ? 1.0F : -1.0F;
            this.selectNext();
        }

        /**
         * Keep ticking a continuous task that has already been started
         */
        @Override
        public void tick() {
            if (SkyrimDragon.this.random.nextInt(this.adjustedTickDelay(350)) == 0) {
                this.height = -4.0F + SkyrimDragon.this.random.nextFloat() * 9.0F;
            }

            if (SkyrimDragon.this.random.nextInt(this.adjustedTickDelay(250)) == 0) {
                ++this.distance;
                if (this.distance > 15.0F) {
                    this.distance = 5.0F;
                    this.clockwise = -this.clockwise;
                }
            }

            if (SkyrimDragon.this.random.nextInt(this.adjustedTickDelay(450)) == 0) {
                this.angle = SkyrimDragon.this.random.nextFloat() * 2.0F * (float) Math.PI;
                this.selectNext();
            }

            if (this.touchingTarget()) {
                this.selectNext();
            }

            if (SkyrimDragon.this.moveTargetPoint.y < SkyrimDragon.this.getY() && !SkyrimDragon.this.level().isEmptyBlock(SkyrimDragon.this.blockPosition().below(1))) {
                this.height = Math.max(1.0F, this.height);
                this.selectNext();
            }

            if (SkyrimDragon.this.moveTargetPoint.y > SkyrimDragon.this.getY() && !SkyrimDragon.this.level().isEmptyBlock(SkyrimDragon.this.blockPosition().above(1))) {
                this.height = Math.min(-1.0F, this.height);
                this.selectNext();
            }
        }

        private void selectNext() {
            if (BlockPos.ZERO.equals(SkyrimDragon.this.anchorPoint)) {
                SkyrimDragon.this.anchorPoint = SkyrimDragon.this.blockPosition();
            }

            this.angle += this.clockwise * 15.0F * (float) (Math.PI / 180.0);
            SkyrimDragon.this.moveTargetPoint = Vec3.atLowerCornerOf(SkyrimDragon.this.anchorPoint)
                    .add((double)(this.distance * Mth.cos(this.angle)), (double)(-4.0F + this.height), (double)(this.distance * Mth.sin(this.angle)));
        }
    }
}