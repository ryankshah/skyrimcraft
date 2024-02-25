package com.ryankshah.skyrimcraft.entity.boss.dragon.phases;

import com.ryankshah.skyrimcraft.entity.boss.dragon.SkyrimDragon;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.EndPodiumFeature;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;

public class SkyrimDragonLandingPhase extends AbstractSkyrimDragonPhaseInstance
{
    @Nullable
    private Vec3 targetLocation;

    public SkyrimDragonLandingPhase(SkyrimDragon pDragon) {
        super(pDragon);
    }

    /**
     * Generates particle effects appropriate to the phase (or sometimes sounds).
     * Called by dragon's onLivingUpdate. Only used when worldObj.isRemote.
     */
    @Override
    public void doClientTick() {
        Vec3 vec3 = this.dragon.getHeadLookVector(1.0F).normalize();
        vec3.yRot((float) (-Math.PI / 4));
        Vec3 vec4 = this.dragon.getHeadLookVector(1.0F);
        double d0 = vec4.x;
        double d1 = vec4.y;
        double d2 = vec4.z;

        for(int i = 0; i < 8; ++i) {
            RandomSource randomsource = this.dragon.getRandom();
            double d3 = d0 + randomsource.nextGaussian() / 2.0;
            double d4 = d1 + randomsource.nextGaussian() / 2.0;
            double d5 = d2 + randomsource.nextGaussian() / 2.0;
            Vec3 vec31 = this.dragon.getDeltaMovement();
            this.dragon
                    .level()
                    .addParticle(ParticleTypes.DRAGON_BREATH, d3, d4, d5, -vec3.x * 0.08F + vec31.x, -vec3.y * 0.3F + vec31.y, -vec3.z * 0.08F + vec31.z);
            vec3.yRot((float) (Math.PI / 16));
        }
    }

    /**
     * Gives the phase a chance to update its status.
     * Called by dragon's onLivingUpdate. Only used when !worldObj.isRemote.
     */
    @Override
    public void doServerTick() {
        if (this.targetLocation == null) {
            this.targetLocation = Vec3.atBottomCenterOf(
                    this.dragon.level().getHeightmapPos(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EndPodiumFeature.getLocation(this.dragon.getFightOrigin()))
            );
        }

        if (this.targetLocation.distanceToSqr(this.dragon.getX(), this.dragon.getY(), this.dragon.getZ()) < 1.0) {
            this.dragon.getPhaseManager().getPhase(SkyrimDragonPhase.SITTING_FLAMING).resetFlameCount();
            this.dragon.getPhaseManager().setPhase(SkyrimDragonPhase.SITTING_SCANNING);
        }
    }

    /**
     * Returns the maximum amount dragon may rise or fall during this phase
     */
    @Override
    public float getFlySpeed() {
        return 1.5F;
    }

    @Override
    public float getTurnSpeed() {
        float f = (float)this.dragon.getDeltaMovement().horizontalDistance() + 1.0F;
        float f1 = Math.min(f, 40.0F);
        return f1 / f;
    }

    /**
     * Called when this phase is set to active
     */
    @Override
    public void begin() {
        this.targetLocation = null;
    }

    /**
     * Returns the location the dragon is flying toward
     */
    @Nullable
    @Override
    public Vec3 getFlyTargetLocation() {
        return this.targetLocation;
    }

    @Override
    public SkyrimDragonPhase<SkyrimDragonLandingPhase> getPhase() {
        return SkyrimDragonPhase.LANDING;
    }
}