package com.ryankshah.skyrimcraft.entity.boss.dragon.phases;

import com.ryankshah.skyrimcraft.entity.boss.dragon.SkyrimDragon;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.phys.Vec3;

public class SkyrimDragonSittingScanningPhase extends AbstractSkyrimDragonPhaseInstance
{
    private static final int SITTING_SCANNING_IDLE_TICKS = 100;
    private static final int SITTING_ATTACK_Y_VIEW_RANGE = 10;
    private static final int SITTING_ATTACK_VIEW_RANGE = 20;
    private static final int SITTING_CHARGE_VIEW_RANGE = 150;
    private static final TargetingConditions CHARGE_TARGETING = TargetingConditions.forCombat().range(150.0);
    private final TargetingConditions scanTargeting;
    private int scanningTime;

    public SkyrimDragonSittingScanningPhase(SkyrimDragon pDragon) {
        super(pDragon);
        this.scanTargeting = TargetingConditions.forCombat().range(20.0).selector(p_311680_ -> Math.abs(p_311680_.getY() - pDragon.getY()) <= 10.0);
    }

    /**
     * Gives the phase a chance to update its status.
     * Called by dragon's onLivingUpdate. Only used when !worldObj.isRemote.
     */
    @Override
    public void doServerTick() {
        ++this.scanningTime;
        LivingEntity livingentity = this.dragon
                .level()
                .getNearestPlayer(this.scanTargeting, this.dragon, this.dragon.getX(), this.dragon.getY(), this.dragon.getZ());
        if (livingentity != null) {
            if (this.scanningTime > 25) {
                this.dragon.getPhaseManager().setPhase(SkyrimDragonPhase.SITTING_ATTACKING);
            } else {
                Vec3 vec3 = new Vec3(livingentity.getX() - this.dragon.getX(), 0.0, livingentity.getZ() - this.dragon.getZ()).normalize();
                Vec3 vec31 = new Vec3(
                        (double) Mth.sin(this.dragon.getYRot() * (float) (Math.PI / 180.0)),
                        0.0,
                        (double)(-Mth.cos(this.dragon.getYRot() * (float) (Math.PI / 180.0)))
                )
                        .normalize();
                float f = (float)vec31.dot(vec3);
                float f1 = (float)(Math.acos((double)f) * 180.0F / (float)Math.PI) + 0.5F;
                if (f1 < 0.0F || f1 > 10.0F) {
                    Vec3 vec8 = this.dragon.getHeadLookVector(1.0F);
                    double d0 = livingentity.getX() - vec8.x; //this.dragon.head.getX();
                    double d1 = livingentity.getZ() - vec8.z; //this.dragon.head.getZ();
                    double d2 = Mth.clamp(Mth.wrapDegrees(180.0 - Mth.atan2(d0, d1) * 180.0F / (float)Math.PI - (double)this.dragon.getYRot()), -100.0, 100.0);
                    this.dragon.yRotA *= 0.8F;
                    float f2 = (float)Math.sqrt(d0 * d0 + d1 * d1) + 1.0F;
                    float f3 = f2;
                    if (f2 > 40.0F) {
                        f2 = 40.0F;
                    }

                    this.dragon.yRotA += (float)d2 * (0.7F / f2 / f3);
                    this.dragon.setYRot(this.dragon.getYRot() + this.dragon.yRotA);
                }
            }
        } else if (this.scanningTime >= 100) {
            livingentity = this.dragon.level().getNearestPlayer(CHARGE_TARGETING, this.dragon, this.dragon.getX(), this.dragon.getY(), this.dragon.getZ());
            this.dragon.getPhaseManager().setPhase(SkyrimDragonPhase.TAKEOFF);
            if (livingentity != null) {
                this.dragon.getPhaseManager().setPhase(SkyrimDragonPhase.CHARGING_PLAYER);
                this.dragon
                        .getPhaseManager()
                        .getPhase(SkyrimDragonPhase.CHARGING_PLAYER)
                        .setTarget(new Vec3(livingentity.getX(), livingentity.getY(), livingentity.getZ()));
            }
        }
    }

    /**
     * Called when this phase is set to active
     */
    @Override
    public void begin() {
        this.scanningTime = 0;
    }

    @Override
    public SkyrimDragonPhase<SkyrimDragonSittingScanningPhase> getPhase() {
        return SkyrimDragonPhase.SITTING_SCANNING;
    }
}
