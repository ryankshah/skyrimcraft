package com.ryankshah.skyrimcraft.entity.boss.dragon.phases;

import com.mojang.logging.LogUtils;
import com.ryankshah.skyrimcraft.entity.boss.dragon.SkyrimDragon;
import net.minecraft.world.phys.Vec3;
import org.slf4j.Logger;

import javax.annotation.Nullable;

public class SkyrimDragonChargePlayerPhase extends AbstractSkyrimDragonPhaseInstance
{
    private static final Logger LOGGER = LogUtils.getLogger();
    private static final int CHARGE_RECOVERY_TIME = 10;
    @Nullable
    private Vec3 targetLocation;
    private int timeSinceCharge;

    public SkyrimDragonChargePlayerPhase(SkyrimDragon pDragon) {
        super(pDragon);
    }

    /**
     * Gives the phase a chance to update its status.
     * Called by dragon's onLivingUpdate. Only used when !worldObj.isRemote.
     */
    @Override
    public void doServerTick() {
        if (this.targetLocation == null) {
            LOGGER.warn("Aborting charge player as no target was set.");
            this.dragon.getPhaseManager().setPhase(SkyrimDragonPhase.HOLDING_PATTERN);
        } else if (this.timeSinceCharge > 0 && this.timeSinceCharge++ >= 10) {
            this.dragon.getPhaseManager().setPhase(SkyrimDragonPhase.HOLDING_PATTERN);
        } else {
            double d0 = this.targetLocation.distanceToSqr(this.dragon.getX(), this.dragon.getY(), this.dragon.getZ());
            if (d0 < 100.0 || d0 > 22500.0 || this.dragon.horizontalCollision || this.dragon.verticalCollision) {
                ++this.timeSinceCharge;
            }
        }
    }

    /**
     * Called when this phase is set to active
     */
    @Override
    public void begin() {
        this.targetLocation = null;
        this.timeSinceCharge = 0;
    }

    public void setTarget(Vec3 pTargetLocation) {
        this.targetLocation = pTargetLocation;
    }

    /**
     * Returns the maximum amount dragon may rise or fall during this phase
     */
    @Override
    public float getFlySpeed() {
        return 3.0F;
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
    public SkyrimDragonPhase<SkyrimDragonChargePlayerPhase> getPhase() {
        return SkyrimDragonPhase.CHARGING_PLAYER;
    }
}
