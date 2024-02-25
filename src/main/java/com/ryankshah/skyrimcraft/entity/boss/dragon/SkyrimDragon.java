package com.ryankshah.skyrimcraft.entity.boss.dragon;

import com.google.common.collect.Lists;
import com.ryankshah.skyrimcraft.entity.boss.dragon.phases.SkyrimDragonPhase;
import com.ryankshah.skyrimcraft.entity.boss.dragon.phases.SkyrimDragonPhaseInstance;
import com.ryankshah.skyrimcraft.init.EntityInit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
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
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.boss.enderdragon.phases.EnderDragonPhase;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.EndPodiumFeature;
import net.minecraft.world.level.pathfinder.BinaryHeap;
import net.minecraft.world.level.pathfinder.Node;
import net.minecraft.world.level.pathfinder.Path;
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
    public static final EntityDataAccessor<Integer> DATA_PHASE = SynchedEntityData.defineId(SkyrimDragon.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> PREV_ANIMATION_STATE = SynchedEntityData.defineId(SkyrimDragon.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> ANIMATION_STATE = SynchedEntityData.defineId(SkyrimDragon.class, EntityDataSerializers.INT);

    private static final float SITTING_ALLOWED_DAMAGE_PERCENTAGE = 0.25F;
    private static final int GROWL_INTERVAL_MIN = 200;
    private static final int GROWL_INTERVAL_MAX = 400;
    private static final String DRAGON_PHASE_KEY = "DragonPhase";

    public static final float FLAP_DEGREES_PER_TICK = 7.448451F;
    public static final int TICKS_PER_FLAP = Mth.ceil(24.166098F);

    private static Vec3 moveTargetPoint = Vec3.ZERO;
    private static BlockPos anchorPoint = BlockPos.ZERO;
    private BlockPos fightOrigin = BlockPos.ZERO;
    private final SkyrimDragonPhaseManager phaseManager;
    private float sittingDamageReceived;
    private final Node[] nodes = new Node[24];
    private final int[] nodeAdjacency = new int[24];
    private final BinaryHeap openSet = new BinaryHeap();
    public float yRotA;
    public float oFlapTime;
    public float flapTime;
    private int growlTime = 300;
    public boolean inWall;
    public final double[][] positions = new double[64][3];
    public int posPointer = -1;

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

    private final AnimatableInstanceCache geoCache = GeckoLibUtil.createInstanceCache(this);

    public SkyrimDragon(EntityType<? extends FlyingMob> type, Level worldIn) {
        super(type, worldIn);
        this.setHealth(this.getMaxHealth());
        this.noCulling = true;
        this.xpReward = XP_REWARD_BOSS;

        this.phaseManager = new SkyrimDragonPhaseManager(this);

//        this.moveControl = new SkyrimDragon.DragonMoveControl(this);
//        this.lookControl = new SkyrimDragon.DragonLookControl(this);
//
//        this.setPathfindingMalus(BlockPathTypes.WATER, -1.0F);
//        this.setPathfindingMalus(BlockPathTypes.DANGER_FIRE, 16.0F);
//        this.setPathfindingMalus(BlockPathTypes.DAMAGE_FIRE, -1.0F);
    }

    public int findClosestNode() {
        if (this.nodes[0] == null) {
            for(int i = 0; i < 24; ++i) {
                int j = 5;
                int l;
                int i1;
                if (i < 12) {
                    l = Mth.floor(60.0F * Mth.cos(2.0F * ((float) -Math.PI + (float) (Math.PI / 12) * (float)i)));
                    i1 = Mth.floor(60.0F * Mth.sin(2.0F * ((float) -Math.PI + (float) (Math.PI / 12) * (float)i)));
                } else if (i < 20) {
                    int $$2 = i - 12;
                    l = Mth.floor(40.0F * Mth.cos(2.0F * ((float) -Math.PI + (float) (Math.PI / 8) * (float)$$2)));
                    i1 = Mth.floor(40.0F * Mth.sin(2.0F * ((float) -Math.PI + (float) (Math.PI / 8) * (float)$$2)));
                    j += 10;
                } else {
                    int k1 = i - 20;
                    l = Mth.floor(20.0F * Mth.cos(2.0F * ((float) -Math.PI + (float) (Math.PI / 4) * (float)k1)));
                    i1 = Mth.floor(20.0F * Mth.sin(2.0F * ((float) -Math.PI + (float) (Math.PI / 4) * (float)k1)));
                }

                int j1 = Math.max(
                        this.level().getSeaLevel() + 10, this.level().getHeightmapPos(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, new BlockPos(l, 0, i1)).getY() + j
                );
                this.nodes[i] = new Node(l, j1, i1);
            }

            this.nodeAdjacency[0] = 6146;
            this.nodeAdjacency[1] = 8197;
            this.nodeAdjacency[2] = 8202;
            this.nodeAdjacency[3] = 16404;
            this.nodeAdjacency[4] = 32808;
            this.nodeAdjacency[5] = 32848;
            this.nodeAdjacency[6] = 65696;
            this.nodeAdjacency[7] = 131392;
            this.nodeAdjacency[8] = 131712;
            this.nodeAdjacency[9] = 263424;
            this.nodeAdjacency[10] = 526848;
            this.nodeAdjacency[11] = 525313;
            this.nodeAdjacency[12] = 1581057;
            this.nodeAdjacency[13] = 3166214;
            this.nodeAdjacency[14] = 2138120;
            this.nodeAdjacency[15] = 6373424;
            this.nodeAdjacency[16] = 4358208;
            this.nodeAdjacency[17] = 12910976;
            this.nodeAdjacency[18] = 9044480;
            this.nodeAdjacency[19] = 9706496;
            this.nodeAdjacency[20] = 15216640;
            this.nodeAdjacency[21] = 13688832;
            this.nodeAdjacency[22] = 11763712;
            this.nodeAdjacency[23] = 8257536;
        }

        return this.findClosestNode(this.getX(), this.getY(), this.getZ());
    }

    /**
     * Returns the index into pathPoints of the nearest PathPoint.
     */
    public int findClosestNode(double pX, double pY, double pZ) {
        float f = 10000.0F;
        int i = 0;
        Node node = new Node(Mth.floor(pX), Mth.floor(pY), Mth.floor(pZ));
        int j = 0;

        for(int k = j; k < 24; ++k) {
            if (this.nodes[k] != null) {
                float f1 = this.nodes[k].distanceToSqr(node);
                if (f1 < f) {
                    f = f1;
                    i = k;
                }
            }
        }

        return i;
    }

    public int getUniqueFlapTickOffset() {
        return this.getId() * 2;
    }

    protected void registerGoals() {
//        this.goalSelector.addGoal(1, new SkyrimDragon.CircleAroundAnchorGoal());
    }

    public void setFightOrigin(BlockPos pFightOrigin) {
        this.fightOrigin = pFightOrigin;
    }

    public BlockPos getFightOrigin() {
        return this.fightOrigin;
    }
    @Override
    public boolean isFlapping() {
        float f = Mth.cos(this.flapTime * (float) (Math.PI * 2));
        float f1 = Mth.cos(this.oFlapTime * (float) (Math.PI * 2));
        return f1 <= -0.3F && f >= -0.3F;
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

    @Nullable private Player unlimitedLastHurtByPlayer = null;

    public double[] getLatencyPos(int pBufferIndexOffset, float pPartialTicks) {
        if (this.isDeadOrDying()) {
            pPartialTicks = 0.0F;
        }

        pPartialTicks = 1.0F - pPartialTicks;
        int i = this.posPointer - pBufferIndexOffset & 63;
        int j = this.posPointer - pBufferIndexOffset - 1 & 63;
        double[] adouble = new double[3];
        double d0 = this.positions[i][0];
        double d1 = Mth.wrapDegrees(this.positions[j][0] - d0);
        adouble[0] = d0 + d1 * (double)pPartialTicks;
        d0 = this.positions[i][1];
        d1 = this.positions[j][1] - d0;
        adouble[1] = d0 + d1 * (double)pPartialTicks;
        adouble[2] = Mth.lerp((double)pPartialTicks, this.positions[i][2], this.positions[j][2]);
        return adouble;
    }

    private float getHeadYOffset() {
        if (this.phaseManager.getCurrentPhase().isSitting()) {
            return -1.0F;
        } else {
            double[] adouble = this.getLatencyPos(5, 1.0F);
            double[] adouble1 = this.getLatencyPos(0, 1.0F);
            return (float)(adouble[1] - adouble1[1]);
        }
    }

    @Override
    public void aiStep() {
        // lastHurtByPlayer is cleared after 100 ticks, capture it indefinitely in unlimitedLastHurtByPlayer for LivingExperienceDropEvent
        if (this.lastHurtByPlayer != null) this.unlimitedLastHurtByPlayer = lastHurtByPlayer;
        if (this.unlimitedLastHurtByPlayer != null && this.unlimitedLastHurtByPlayer.isRemoved()) this.unlimitedLastHurtByPlayer = null;
        this.processFlappingMovement();
        if (this.level().isClientSide) {
            this.setHealth(this.getHealth());
            if (!this.isSilent() && !this.phaseManager.getCurrentPhase().isSitting() && --this.growlTime < 0) {
                this.level()
                        .playLocalSound(
                                this.getX(),
                                this.getY(),
                                this.getZ(),
                                SoundEvents.ENDER_DRAGON_GROWL,
                                this.getSoundSource(),
                                2.5F,
                                0.8F + this.random.nextFloat() * 0.3F,
                                false
                        );
                this.growlTime = 200 + this.random.nextInt(200);
            }
        }

        this.oFlapTime = this.flapTime;
        if (this.isDeadOrDying()) {
            float f8 = (this.random.nextFloat() - 0.5F) * 8.0F;
            float f10 = (this.random.nextFloat() - 0.5F) * 4.0F;
            float f11 = (this.random.nextFloat() - 0.5F) * 8.0F;
            this.level()
                    .addParticle(ParticleTypes.EXPLOSION, this.getX() + (double)f8, this.getY() + 2.0 + (double)f10, this.getZ() + (double)f11, 0.0, 0.0, 0.0);
        } else {
            Vec3 vec34 = this.getDeltaMovement();
            float f9 = 0.2F / ((float)vec34.horizontalDistance() * 10.0F + 1.0F);
            f9 *= (float)Math.pow(2.0, vec34.y);
            if (this.phaseManager.getCurrentPhase().isSitting()) {
                this.flapTime += 0.1F;
            } else if (this.inWall) {
                this.flapTime += f9 * 0.5F;
            } else {
                this.flapTime += f9;
            }

            this.setYRot(Mth.wrapDegrees(this.getYRot()));
            if (this.isNoAi()) {
                this.flapTime = 0.5F;
            } else {
                if (this.posPointer < 0) {
                    for(int i = 0; i < this.positions.length; ++i) {
                        this.positions[i][0] = (double)this.getYRot();
                        this.positions[i][1] = this.getY();
                    }
                }

                if (++this.posPointer == this.positions.length) {
                    this.posPointer = 0;
                }

                this.positions[this.posPointer][0] = (double)this.getYRot();
                this.positions[this.posPointer][1] = this.getY();
                if (this.level().isClientSide) {
                    if (this.lerpSteps > 0) {
                        this.lerpPositionAndRotationStep(this.lerpSteps, this.lerpX, this.lerpY, this.lerpZ, this.lerpYRot, this.lerpXRot);
                        --this.lerpSteps;
                    }

                    this.phaseManager.getCurrentPhase().doClientTick();
                } else {
                    SkyrimDragonPhaseInstance dragonphaseinstance = this.phaseManager.getCurrentPhase();
                    dragonphaseinstance.doServerTick();
                    if (this.phaseManager.getCurrentPhase() != dragonphaseinstance) {
                        dragonphaseinstance = this.phaseManager.getCurrentPhase();
                        dragonphaseinstance.doServerTick();
                    }

                    Vec3 vec3 = dragonphaseinstance.getFlyTargetLocation();
                    if (vec3 != null) {
                        double d0 = vec3.x - this.getX();
                        double d1 = vec3.y - this.getY();
                        double d2 = vec3.z - this.getZ();
                        double d3 = d0 * d0 + d1 * d1 + d2 * d2;
                        float f4 = dragonphaseinstance.getFlySpeed();
                        double d4 = Math.sqrt(d0 * d0 + d2 * d2);
                        if (d4 > 0.0) {
                            d1 = Mth.clamp(d1 / d4, (double)(-f4), (double)f4);
                        }

                        this.setDeltaMovement(this.getDeltaMovement().add(0.0, d1 * 0.01, 0.0));
                        this.setYRot(Mth.wrapDegrees(this.getYRot()));
                        Vec3 vec31 = vec3.subtract(this.getX(), this.getY(), this.getZ()).normalize();
                        Vec3 vec32 = new Vec3(
                                (double)Mth.sin(this.getYRot() * (float) (Math.PI / 180.0)),
                                this.getDeltaMovement().y,
                                (double)(-Mth.cos(this.getYRot() * (float) (Math.PI / 180.0)))
                        )
                                .normalize();
                        float f5 = Math.max(((float)vec32.dot(vec31) + 0.5F) / 1.5F, 0.0F);
                        if (Math.abs(d0) > 1.0E-5F || Math.abs(d2) > 1.0E-5F) {
                            float f6 = Mth.clamp(Mth.wrapDegrees(180.0F - (float)Mth.atan2(d0, d2) * (180.0F / (float)Math.PI) - this.getYRot()), -50.0F, 50.0F);
                            this.yRotA *= 0.8F;
                            this.yRotA += f6 * dragonphaseinstance.getTurnSpeed();
                            this.setYRot(this.getYRot() + this.yRotA * 0.1F);
                        }

                        float f19 = (float)(2.0 / (d3 + 1.0));
                        float f7 = 0.06F;
                        this.moveRelative(0.06F * (f5 * f19 + (1.0F - f19)), new Vec3(0.0, 0.0, -1.0));
                        if (this.inWall) {
                            this.move(MoverType.SELF, this.getDeltaMovement().scale(0.8F));
                        } else {
                            this.move(MoverType.SELF, this.getDeltaMovement());
                        }

                        Vec3 vec33 = this.getDeltaMovement().normalize();
                        double d5 = 0.8 + 0.15 * (vec33.dot(vec32) + 1.0) / 2.0;
                        this.setDeltaMovement(this.getDeltaMovement().multiply(d5, 0.91F, d5));
                    }
                }

                this.yBodyRot = this.getYRot();
//                Vec3[] avec3 = new Vec3[this.subEntities.length];
//
//                for(int j = 0; j < this.subEntities.length; ++j) {
//                    avec3[j] = new Vec3(this.subEntities[j].getX(), this.subEntities[j].getY(), this.subEntities[j].getZ());
//                }

                float f12 = (float)(this.getLatencyPos(5, 1.0F)[1] - this.getLatencyPos(10, 1.0F)[1]) * 10.0F * (float) (Math.PI / 180.0);
                float f13 = Mth.cos(f12);
                float f = Mth.sin(f12);
                float f14 = this.getYRot() * (float) (Math.PI / 180.0);
                float f1 = Mth.sin(f14);
                float f15 = Mth.cos(f14);
//                this.tickPart(this.body, (double)(f1 * 0.5F), 0.0, (double)(-f15 * 0.5F));
//                this.tickPart(this.wing1, (double)(f15 * 4.5F), 2.0, (double)(f1 * 4.5F));
//                this.tickPart(this.wing2, (double)(f15 * -4.5F), 2.0, (double)(f1 * -4.5F));
//                if (!this.level().isClientSide && this.hurtTime == 0) {
//                    this.knockBack(
//                            this.level()
//                                    .getEntities(this, this.wing1.getBoundingBox().inflate(4.0, 2.0, 4.0).move(0.0, -2.0, 0.0), EntitySelector.NO_CREATIVE_OR_SPECTATOR)
//                    );
//                    this.knockBack(
//                            this.level()
//                                    .getEntities(this, this.wing2.getBoundingBox().inflate(4.0, 2.0, 4.0).move(0.0, -2.0, 0.0), EntitySelector.NO_CREATIVE_OR_SPECTATOR)
//                    );
//                    this.hurt(this.level().getEntities(this, this.head.getBoundingBox().inflate(1.0), EntitySelector.NO_CREATIVE_OR_SPECTATOR));
//                    this.hurt(this.level().getEntities(this, this.neck.getBoundingBox().inflate(1.0), EntitySelector.NO_CREATIVE_OR_SPECTATOR));
//                }

                float f2 = Mth.sin(this.getYRot() * (float) (Math.PI / 180.0) - this.yRotA * 0.01F);
                float f16 = Mth.cos(this.getYRot() * (float) (Math.PI / 180.0) - this.yRotA * 0.01F);
                float f3 = this.getHeadYOffset();
//                this.tickPart(this.head, (double)(f2 * 6.5F * f13), (double)(f3 + f * 6.5F), (double)(-f16 * 6.5F * f13));
//                this.tickPart(this.neck, (double)(f2 * 5.5F * f13), (double)(f3 + f * 5.5F), (double)(-f16 * 5.5F * f13));
                double[] adouble = this.getLatencyPos(5, 1.0F);

//                for(int k = 0; k < 3; ++k) {
//                    EnderDragonPart enderdragonpart = null;
//                    if (k == 0) {
//                        enderdragonpart = this.tail1;
//                    }
//
//                    if (k == 1) {
//                        enderdragonpart = this.tail2;
//                    }
//
//                    if (k == 2) {
//                        enderdragonpart = this.tail3;
//                    }
//
//                    double[] adouble1 = this.getLatencyPos(12 + k * 2, 1.0F);
//                    float f17 = this.getYRot() * (float) (Math.PI / 180.0) + this.rotWrap(adouble1[0] - adouble[0]) * (float) (Math.PI / 180.0);
//                    float f18 = Mth.sin(f17);
//                    float f20 = Mth.cos(f17);
//                    float f21 = 1.5F;
//                    float f22 = (float)(k + 1) * 2.0F;
//                    this.tickPart(
//                            enderdragonpart,
//                            (double)(-(f1 * 1.5F + f18 * f22) * f13),
//                            adouble1[1] - adouble[1] - (double)((f22 + 1.5F) * f) + 1.5,
//                            (double)((f15 * 1.5F + f20 * f22) * f13)
//                    );
//                }

//                if (!this.level().isClientSide) {
//                    this.inWall = this.checkWalls(this.head.getBoundingBox())
//                            | this.checkWalls(this.neck.getBoundingBox())
//                            | this.checkWalls(this.body.getBoundingBox());
//                    if (this.dragonFight != null) {
//                        this.dragonFight.updateDragon(this);
//                    }
//                }
//
//                for(int l = 0; l < this.subEntities.length; ++l) {
//                    this.subEntities[l].xo = avec3[l].x;
//                    this.subEntities[l].yo = avec3[l].y;
//                    this.subEntities[l].zo = avec3[l].z;
//                    this.subEntities[l].xOld = avec3[l].x;
//                    this.subEntities[l].yOld = avec3[l].y;
//                    this.subEntities[l].zOld = avec3[l].z;
//                }
            }
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

    private float rotWrap(double pAngle) {
        return (float)Mth.wrapDegrees(pAngle);
    }

    @Override
    public void kill() {
        this.remove(Entity.RemovalReason.KILLED);
        this.gameEvent(GameEvent.ENTITY_DIE);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.getEntityData().define(DATA_PHASE, SkyrimDragonPhase.HOVERING.getId());
        this.entityData.define(ANIMATION_STATE, 0);
        this.entityData.define(PREV_ANIMATION_STATE, 0);
    }

    @Override
    public void onSyncedDataUpdated(EntityDataAccessor<?> pKey) {
        if (DATA_PHASE.equals(pKey) && this.level().isClientSide) {
            this.phaseManager.setPhase(SkyrimDragonPhase.getById(this.getEntityData().get(DATA_PHASE)));
        }

        super.onSyncedDataUpdated(pKey);
    }

    public SkyrimDragonPhaseManager getPhaseManager() {
        return this.phaseManager;
    }

    @Nullable
    public Path findPath(int pStartIndex, int pFinishIndex, @Nullable Node pAndThen) {
        for(int i = 0; i < 24; ++i) {
            Node node = this.nodes[i];
            node.closed = false;
            node.f = 0.0F;
            node.g = 0.0F;
            node.h = 0.0F;
            node.cameFrom = null;
            node.heapIdx = -1;
        }

        Node node4 = this.nodes[pStartIndex];
        Node node5 = this.nodes[pFinishIndex];
        node4.g = 0.0F;
        node4.h = node4.distanceTo(node5);
        node4.f = node4.h;
        this.openSet.clear();
        this.openSet.insert(node4);
        Node node1 = node4;
        int j = 0;

        while(!this.openSet.isEmpty()) {
            Node node2 = this.openSet.pop();
            if (node2.equals(node5)) {
                if (pAndThen != null) {
                    pAndThen.cameFrom = node5;
                    node5 = pAndThen;
                }

                return this.reconstructPath(node4, node5);
            }

            if (node2.distanceTo(node5) < node1.distanceTo(node5)) {
                node1 = node2;
            }

            node2.closed = true;
            int k = 0;

            for(int l = 0; l < 24; ++l) {
                if (this.nodes[l] == node2) {
                    k = l;
                    break;
                }
            }

            for(int i1 = j; i1 < 24; ++i1) {
                if ((this.nodeAdjacency[k] & 1 << i1) > 0) {
                    Node node3 = this.nodes[i1];
                    if (!node3.closed) {
                        float f = node2.g + node2.distanceTo(node3);
                        if (!node3.inOpenSet() || f < node3.g) {
                            node3.cameFrom = node2;
                            node3.g = f;
                            node3.h = node3.distanceTo(node5);
                            if (node3.inOpenSet()) {
                                this.openSet.changeCost(node3, node3.g + node3.h);
                            } else {
                                node3.f = node3.g + node3.h;
                                this.openSet.insert(node3);
                            }
                        }
                    }
                }
            }
        }

        if (node1 == node4) {
            return null;
        } else {
            if (pAndThen != null) {
                pAndThen.cameFrom = node1;
                node1 = pAndThen;
            }

            return this.reconstructPath(node4, node1);
        }
    }

    private Path reconstructPath(Node pStart, Node pFinish) {
        List<Node> list = Lists.newArrayList();
        Node node = pFinish;
        list.add(0, pFinish);

        while(node.cameFrom != null) {
            node = node.cameFrom;
            list.add(0, node);
        }

        return new Path(list, new BlockPos(pFinish.x, pFinish.y, pFinish.z), true);
    }


    public void addAdditionalSaveData(CompoundTag p_213281_1_) {
        super.addAdditionalSaveData(p_213281_1_);
        if (p_213281_1_.contains("AX")) {
            this.anchorPoint = new BlockPos(p_213281_1_.getInt("AX"), p_213281_1_.getInt("AY"), p_213281_1_.getInt("AZ"));
        }
        p_213281_1_.putInt("DragonPhase", this.phaseManager.getCurrentPhase().getPhase().getId());
        p_213281_1_.putInt("AnimationState", this.getAnimationState());
        p_213281_1_.putInt("PrevAnimationState", this.getPrevAnimationState());
    }

    public void readAdditionalSaveData(CompoundTag p_70037_1_) {
        super.readAdditionalSaveData(p_70037_1_);
        p_70037_1_.putInt("AX", this.anchorPoint.getX());
        p_70037_1_.putInt("AY", this.anchorPoint.getY());
        p_70037_1_.putInt("AZ", this.anchorPoint.getZ());
        if (p_70037_1_.contains("DragonPhase")) {
            this.phaseManager.setPhase(SkyrimDragonPhase.getById(p_70037_1_.getInt("DragonPhase")));
        }
        this.setAnimationState(p_70037_1_.getInt("AnimationState"));
        this.setPrevAnimationState(p_70037_1_.getInt("PrevAnimationState"));
    }

    @Override
    public boolean isPickable() {
        return false;
    }

    public Vec3 getHeadLookVector(float pPartialTicks) {
        SkyrimDragonPhaseInstance dragonphaseinstance = this.phaseManager.getCurrentPhase();
        SkyrimDragonPhase<? extends SkyrimDragonPhaseInstance> skyrimDragonPhase = dragonphaseinstance.getPhase();
        Vec3 vec3;
        if (skyrimDragonPhase == SkyrimDragonPhase.LANDING || skyrimDragonPhase == SkyrimDragonPhase.TAKEOFF) {
            BlockPos blockpos = this.level().getHeightmapPos(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EndPodiumFeature.getLocation(this.fightOrigin));
            float f5 = Math.max((float)Math.sqrt(blockpos.distToCenterSqr(this.position())) / 4.0F, 1.0F);
            float f2 = 6.0F / f5;
            float f3 = this.getXRot();
            float f4 = 1.5F;
            this.setXRot(-f2 * 1.5F * 5.0F);
            vec3 = this.getViewVector(pPartialTicks);
            this.setXRot(f3);
        } else if (dragonphaseinstance.isSitting()) {
            float f = this.getXRot();
            float f1 = 1.5F;
            this.setXRot(-45.0F);
            vec3 = this.getViewVector(pPartialTicks);
            this.setXRot(f);
        } else {
            vec3 = this.getViewVector(pPartialTicks);
        }

        return vec3;
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
//        this.anchorPoint = this.blockPosition().above(20);
        this.setAnimationState(0);
        this.setPrevAnimationState(0);
        this.setFightOrigin(this.blockPosition().above(20));
        this.getPhaseManager().setPhase(SkyrimDragonPhase.HOLDING_PATTERN);
        BlockPos origin = this.blockPosition().above(20);
        this.moveTo((double)origin.getX(), (double)(128 + origin.getY()), (double)origin.getZ(), p_213386_1_.getLevel().random.nextFloat() * 360.0F, 0.0F);
        return super.finalizeSpawn(p_213386_1_, p_213386_2_, p_213386_3_, p_213386_4_, p_213386_5_);
    }

    @Nullable
    private SkyrimDragon createNewDragon(ServerLevel level, BlockPos origin) {
        level.getChunkAt(new BlockPos(origin.getX(), 128 + origin.getY(), origin.getZ()));
        SkyrimDragon enderdragon = EntityInit.DRAGON.get().create(level);
        if (enderdragon != null) {
            enderdragon.setFightOrigin(origin);
            enderdragon.getPhaseManager().setPhase(SkyrimDragonPhase.HOLDING_PATTERN);
            enderdragon.moveTo(
                    (double)origin.getX(), (double)(128 + origin.getY()), (double)origin.getZ(), level.random.nextFloat() * 360.0F, 0.0F
            );
            level.addFreshEntity(enderdragon);
        }

        return enderdragon;
    }



    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.geoCache;
    }

    private <E extends SkyrimDragon> PlayState dragonController(final software.bernie.geckolib.core.animation.AnimationState<SkyrimDragon> event) {
        AnimationController<SkyrimDragon> controller = event.getController();
        controller.transitionLength(0);

        SkyrimDragonPhaseInstance currentPhase = this.phaseManager.getCurrentPhase();

        if(currentPhase.getPhase().getId() == SkyrimDragonPhase.HOVERING.getId()) {
            return event.setAndContinue(FLY_IDLE);
        } else if(currentPhase.getPhase().getId() == SkyrimDragonPhase.SITTING_ATTACKING.getId()) {
            return event.setAndContinue(BITE);
        } else if(currentPhase.getPhase().getId() == SkyrimDragonPhase.SITTING_SCANNING.getId()) {
            return event.setAndContinue(STAND);
        } else if(currentPhase.getPhase().getId() == SkyrimDragonPhase.STRAFE_PLAYER.getId() || currentPhase.getPhase().getId() == SkyrimDragonPhase.SITTING_FLAMING.getId()) {
            return event.setAndContinue(FLY_SHOUT_BREATHE);
        } else if(currentPhase.getPhase().getId() == SkyrimDragonPhase.LANDING_APPROACH.getId()) {
            return event.setAndContinue(LAND);
        } else if(currentPhase.getPhase().getId() == SkyrimDragonPhase.TAKEOFF.getId()) {
            return event.setAndContinue(TAKE_OFF);
        } else {
            return event.setAndContinue(FLY_IDLE);
        }

//        if(event.isMoving() && isFlapping()) //isFlying
//            return event.setAndContinue(FLY_IDLE);
//        else if(event.isMoving() && !isFlapping() && !onGround())
//            return event.setAndContinue(GLIDE);
//        else if(event.isMoving() && onGround())
//            return event.setAndContinue(WALK);
//        else if(!event.isMoving() && onGround())
//            return event.setAndContinue(STAND);
//        else
//            return event.setAndContinue(FLY_IDLE);
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