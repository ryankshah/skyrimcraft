package com.ryankshah.skyrimcraft.entity.boss.dragon.phases;

import com.ryankshah.skyrimcraft.entity.boss.dragon.SkyrimDragon;
import net.minecraft.sounds.SoundEvents;

public class SkyrimDragonSittingAttackingPhase extends AbstractSkyrimDragonPhaseInstance
{
    private static final int ROAR_DURATION = 40;
    private int attackingTicks;

    public SkyrimDragonSittingAttackingPhase(SkyrimDragon pDragon) {
        super(pDragon);
    }

    @Override
    public boolean isSitting() {
        return true;
    }

    /**
     * Generates particle effects appropriate to the phase (or sometimes sounds).
     * Called by dragon's onLivingUpdate. Only used when worldObj.isRemote.
     */
    @Override
    public void doClientTick() {
        this.dragon
                .level()
                .playLocalSound(
                        this.dragon.getX(),
                        this.dragon.getY(),
                        this.dragon.getZ(),
                        SoundEvents.ENDER_DRAGON_GROWL,
                        this.dragon.getSoundSource(),
                        2.5F,
                        0.8F + this.dragon.getRandom().nextFloat() * 0.3F,
                        false
                );
    }

    /**
     * Gives the phase a chance to update its status.
     * Called by dragon's onLivingUpdate. Only used when !worldObj.isRemote.
     */
    @Override
    public void doServerTick() {
        if (this.attackingTicks++ >= 40) {
            this.dragon.getPhaseManager().setPhase(SkyrimDragonPhase.SITTING_FLAMING);
        }
    }

    /**
     * Called when this phase is set to active
     */
    @Override
    public void begin() {
        this.attackingTicks = 0;
    }

    @Override
    public SkyrimDragonPhase<SkyrimDragonSittingAttackingPhase> getPhase() {
        return SkyrimDragonPhase.SITTING_ATTACKING;
    }
}