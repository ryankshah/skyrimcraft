package com.ryankshah.skyrimcraft.entity.boss.dragon.phases;

import com.ryankshah.skyrimcraft.entity.boss.dragon.SkyrimDragon;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;

public class SkyrimDragonSittingFlamingPhase extends AbstractSkyrimDragonPhaseInstance
{
    private static final int FLAME_DURATION = 200;
    private static final int SITTING_FLAME_ATTACKS_COUNT = 4;
    private static final int WARMUP_TIME = 10;
    private int flameTicks;
    private int flameCount;
    @Nullable
    private AreaEffectCloud flame;

    public SkyrimDragonSittingFlamingPhase(SkyrimDragon pDragon) {
        super(pDragon);
    }

    /**
     * Generates particle effects appropriate to the phase (or sometimes sounds).
     * Called by dragon's onLivingUpdate. Only used when worldObj.isRemote.
     */
    @Override
    public void doClientTick() {
        ++this.flameTicks;
        if (this.flameTicks % 2 == 0 && this.flameTicks < 10) {
            Vec3 vec3 = this.dragon.getHeadLookVector(1.0F).normalize();
            vec3.yRot((float) (-Math.PI / 4));
            Vec3 vec4 = this.dragon.getHeadLookVector(1.0F);
            double d0 = vec4.x;
            double d1 = vec4.y;
            double d2 = vec4.z;
//            double d0 = this.dragon.head.getX();
//            double d1 = this.dragon.head.getY(0.5);
//            double d2 = this.dragon.head.getZ();

            for(int i = 0; i < 8; ++i) {
                double d3 = d0 + this.dragon.getRandom().nextGaussian() / 2.0;
                double d4 = d1 + this.dragon.getRandom().nextGaussian() / 2.0;
                double d5 = d2 + this.dragon.getRandom().nextGaussian() / 2.0;

                for(int j = 0; j < 6; ++j) {
                    this.dragon
                            .level()
                            .addParticle(ParticleTypes.DRAGON_BREATH, d3, d4, d5, -vec3.x * 0.08F * (double)j, -vec3.y * 0.6F, -vec3.z * 0.08F * (double)j);
                }

                vec3.yRot((float) (Math.PI / 16));
            }
        }
    }

    /**
     * Gives the phase a chance to update its status.
     * Called by dragon's onLivingUpdate. Only used when !worldObj.isRemote.
     */
    @Override
    public void doServerTick() {
        ++this.flameTicks;
        if (this.flameTicks >= 200) {
            if (this.flameCount >= 4) {
                this.dragon.getPhaseManager().setPhase(SkyrimDragonPhase.TAKEOFF);
            } else {
                this.dragon.getPhaseManager().setPhase(SkyrimDragonPhase.SITTING_SCANNING);
            }
        } else if (this.flameTicks == 10) {
            Vec3 vec2 = this.dragon.getHeadLookVector(1.0F);
            Vec3 vec3 = new Vec3(vec2.x - this.dragon.getX(), 0.0, vec2.z - this.dragon.getZ()).normalize();
            float f = 5.0F;
            double d0 = vec3.x + vec3.x * 5.0 / 2.0;
            double d1 = vec3.z + vec3.z * 5.0 / 2.0;
            double d2 = vec3.y; //this.dragon.head.getY(0.5);
            double d3 = d2;
            BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos(d0, d2, d1);

            while(this.dragon.level().isEmptyBlock(blockpos$mutableblockpos)) {
                if (--d3 < 0.0) {
                    d3 = d2;
                    break;
                }

                blockpos$mutableblockpos.set(d0, d3, d1);
            }

            d3 = (double)(Mth.floor(d3) + 1);
            this.flame = new AreaEffectCloud(this.dragon.level(), d0, d3, d1);
            this.flame.setOwner(this.dragon);
            this.flame.setRadius(5.0F);
            this.flame.setDuration(200);
            this.flame.setParticle(ParticleTypes.DRAGON_BREATH);
            this.flame.addEffect(new MobEffectInstance(MobEffects.HARM));
            this.dragon.level().addFreshEntity(this.flame);
        }
    }

    /**
     * Called when this phase is set to active
     */
    @Override
    public void begin() {
        this.flameTicks = 0;
        ++this.flameCount;
    }

    @Override
    public void end() {
        if (this.flame != null) {
            this.flame.discard();
            this.flame = null;
        }
    }

    @Override
    public boolean isSitting() {
        return true;
    }

    @Override
    public SkyrimDragonPhase<SkyrimDragonSittingFlamingPhase> getPhase() {
        return SkyrimDragonPhase.SITTING_FLAMING;
    }

    public void resetFlameCount() {
        this.flameCount = 0;
    }
}