package com.ryankshah.skyrimcraft.entity.creature;

import com.ryankshah.skyrimcraft.entity.ai.goal.SpiderSprintToNearestAttackableTargetGoal;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ambient.Bat;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

import javax.annotation.Nullable;

public class DwarvenSpider extends PathfinderMob implements GeoEntity
{
    private static final EntityDataAccessor<Integer> PREV_ANIMATION_STATE = SynchedEntityData.defineId(DwarvenSpider.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> ANIMATION_STATE = SynchedEntityData.defineId(DwarvenSpider.class, EntityDataSerializers.INT);

    private MeleeAttackGoal meleeGoal;
    private WaterAvoidingRandomStrollGoal walkingGoal;
    private NearestAttackableTargetGoal<? extends LivingEntity> sprintToNearestPlayerGoal;
//    private NearestAttackableTargetGoal<? extends LivingEntity> sprintToNearestAnimalGoal;

    protected static final RawAnimation SLEEP = RawAnimation.begin().thenLoop("SLEEP");
    protected static final RawAnimation IDLE_SCAN = RawAnimation.begin().thenLoop("IdleSCAN");
    protected static final RawAnimation BOOTUP = RawAnimation.begin().thenLoop("BOOTUP");
    protected static final RawAnimation IDLE = RawAnimation.begin().thenLoop("Idle");
    protected static final RawAnimation WALKBETTER = RawAnimation.begin().thenLoop("WALKBETTER");
    protected static final RawAnimation ATTACK = RawAnimation.begin().thenLoop("ATTACK");
    protected static final RawAnimation ATTACK2 = RawAnimation.begin().thenLoop("ATTACK2");
    private final AnimatableInstanceCache geoCache = GeckoLibUtil.createInstanceCache(this);

    public DwarvenSpider(EntityType<? extends PathfinderMob> type, Level worldIn) {
        super(type, worldIn);
        this.noCulling = true;
        this.xpReward = 5;
//        this.setMaxUpStep(1.25f); // 1.5 works.. but does 1.25f? if so then this comment may still be here xox

        this.setPathfindingMalus(BlockPathTypes.WATER, -1.0F);
        this.setPathfindingMalus(BlockPathTypes.DANGER_FIRE, 16.0F);
        this.setPathfindingMalus(BlockPathTypes.DAMAGE_FIRE, -1.0F);
    }

    protected void registerGoals() {
        meleeGoal = new MeleeAttackGoal(this, 1.2D, false) {
            @Override
            public void stop() {
                super.stop();
                DwarvenSpider.this.setAnimationState(0);
            }

            @Override
            protected void checkAndPerformAttack(LivingEntity pTarget) {
                if (this.canPerformAttack(pTarget)) {
                    if (getTicksUntilNextAttack() <= 0) {
                        this.resetAttackCooldown();
                        this.mob.swing(InteractionHand.MAIN_HAND);
                        if (DwarvenSpider.this.getAnimationState() != 3 || DwarvenSpider.this.getAnimationState() != 4)
                            DwarvenSpider.this.setAnimationState(random.nextBoolean() ? 3 : 4);
                        this.mob.doHurtTarget(pTarget);
                    }
                }
            }
        };

        walkingGoal = new WaterAvoidingRandomStrollGoal(this, 1.0D) {
            @Override
            public void stop() {
                super.stop();
                DwarvenSpider.this.setAnimationState(DwarvenSpider.this.getPrevAnimationState());
            }

            @Override
            public void tick() {
                super.tick();
                if(DwarvenSpider.this.getAnimationState() != 2)
                    DwarvenSpider.this.setAnimationState(2);
            }
        };

        sprintToNearestPlayerGoal = new NearestAttackableTargetGoal<>(this, Player.class, true) {
            @Override
            public void stop() {
                super.stop();
                DwarvenSpider.this.setAnimationState(DwarvenSpider.this.getPrevAnimationState());
            }

            @Override
            public void tick() {
                super.tick();
                if(DwarvenSpider.this.getAnimationState() != 2)
                    DwarvenSpider.this.setAnimationState(2);
            }
        };

//        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(4, new LeapAtTargetGoal(this, 0.4F));
        this.goalSelector.addGoal(1, walkingGoal);
        this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(2, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(3, meleeGoal); //new MeleeAttackGoal(this, 1.0D, true));
        this.targetSelector.addGoal(2, new SpiderSprintToNearestAttackableTargetGoal<>(this, Player.class, true));
//        this.targetSelector.addGoal(2, new SprintToNearestAttackableTargetGoal<>(this, Animal.class, true));
    }

    @Nullable
    @Override
    public Component getCustomName() {
        return super.getCustomName();
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.JUMP_STRENGTH, 1D)
                .add(Attributes.MOVEMENT_SPEED, 0.15D)
                .add(Attributes.MAX_HEALTH, 20.0D)
                .add(Attributes.ATTACK_DAMAGE, 6.0D)
                .add(Attributes.FOLLOW_RANGE, 18.0D);
    }

    @Override
    protected float getBlockJumpFactor() {
        return super.getBlockJumpFactor();
    }

    public static boolean checkSpawnRules(
            EntityType<DwarvenSpider> pEntity, LevelAccessor pLevel, MobSpawnType pSpawnType, BlockPos pPos, RandomSource pRandom
    ) {
        if (pPos.getY() >= pLevel.getSeaLevel()) {
            return false;
        } else {
            int i = pLevel.getMaxLocalRawBrightness(pPos);

            return i <= 5 && checkMobSpawnRules(pEntity, pLevel, pSpawnType, pPos, pRandom);
        }
    }

    @Override
    public float getWalkTargetValue(BlockPos pPos, LevelReader pLevel) {
        return pLevel.getBlockState(pPos.below()).is(Blocks.GRASS_BLOCK) ? 10.0F : pLevel.getBrightness(LightLayer.BLOCK, pPos) - 0.5F;
    }

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
        p_213281_1_.putInt("AnimationState", this.getAnimationState());
        p_213281_1_.putInt("PrevAnimationState", this.getPrevAnimationState());
    }

    public void readAdditionalSaveData(CompoundTag p_70037_1_) {
        super.readAdditionalSaveData(p_70037_1_);
        this.setAnimationState(p_70037_1_.getInt("AnimationState"));
        this.setPrevAnimationState(p_70037_1_.getInt("PrevAnimationState"));
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
        this.setAnimationState(0);
        this.setPrevAnimationState(0);
        return super.finalizeSpawn(p_213386_1_, p_213386_2_, p_213386_3_, p_213386_4_, p_213386_5_);
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.geoCache;
    }

    private <E extends DwarvenSpider> PlayState spiderController(final software.bernie.geckolib.core.animation.AnimationState<DwarvenSpider> event) {
        AnimationController<DwarvenSpider> controller = event.getController();
        controller.transitionLength(0);

        if(this.getAnimationState() == 0) {
            return event.setAndContinue(IDLE_SCAN);
        } else if (this.getAnimationState() == 2) {
            return event.setAndContinue(WALKBETTER);
        } else if(this.getAnimationState() == 3) {
            return event.setAndContinue(ATTACK);
        } else if(this.getAnimationState() == 4) {
            return event.setAndContinue(ATTACK2);
        } else if(this.getAnimationState() == 5) {
            return event.setAndContinue(BOOTUP);
        } else {
            return event.setAndContinue(IDLE);
        }
    }

    @Override
    public void registerControllers(final AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "dwarven_spider_controller", 0, this::spiderController));
    }
}