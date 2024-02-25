package com.ryankshah.skyrimcraft.entity.boss.dragon.phases;

import com.ryankshah.skyrimcraft.entity.boss.dragon.SkyrimDragon;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;

public abstract class AbstractSkyrimDragonPhaseInstance implements SkyrimDragonPhaseInstance {
    protected final SkyrimDragon dragon;

    public AbstractSkyrimDragonPhaseInstance(SkyrimDragon pDragon) {
        this.dragon = pDragon;
    }

    @Override
    public boolean isSitting() {
        return false;
    }

    /**
     * Generates particle effects appropriate to the phase (or sometimes sounds).
     * Called by dragon's onLivingUpdate. Only used when worldObj.isRemote.
     */
    @Override
    public void doClientTick() {
    }

    /**
     * Gives the phase a chance to update its status.
     * Called by dragon's onLivingUpdate. Only used when !worldObj.isRemote.
     */
    @Override
    public void doServerTick() {
    }

    /**
     * Called when this phase is set to active
     */
    @Override
    public void begin() {
    }

    @Override
    public void end() {
    }

    /**
     * Returns the maximum amount dragon may rise or fall during this phase
     */
    @Override
    public float getFlySpeed() {
        return 0.6F;
    }

    /**
     * Returns the location the dragon is flying toward
     */
    @Nullable
    @Override
    public Vec3 getFlyTargetLocation() {
        return null;
    }

    @Override
    public float onHurt(DamageSource pDamageSource, float pAmount) {
        return pAmount;
    }

    @Override
    public float getTurnSpeed() {
        float f = (float)this.dragon.getDeltaMovement().horizontalDistance() + 1.0F;
        float f1 = Math.min(f, 40.0F);
        return 0.7F / f1 / f;
    }
}
