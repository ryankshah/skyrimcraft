package com.ryankshah.skyrimcraft.entity.boss.dragon.phases;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;

public interface SkyrimDragonPhaseInstance {
    boolean isSitting();

    /**
     * Generates particle effects appropriate to the phase (or sometimes sounds).
     * Called by dragon's onLivingUpdate. Only used when worldObj.isRemote.
     */
    void doClientTick();

    /**
     * Gives the phase a chance to update its status.
     * Called by dragon's onLivingUpdate. Only used when !worldObj.isRemote.
     */
    void doServerTick();

    /**
     * Called when this phase is set to active
     */
    void begin();

    void end();

    /**
     * Returns the maximum amount dragon may rise or fall during this phase
     */
    float getFlySpeed();

    float getTurnSpeed();

    SkyrimDragonPhase getPhase();

    /**
     * Returns the location the dragon is flying toward
     */
    @Nullable
    Vec3 getFlyTargetLocation();

    float onHurt(DamageSource pDamageSource, float pAmount);
}
