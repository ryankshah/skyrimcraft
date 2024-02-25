package com.ryankshah.skyrimcraft.entity.boss.dragon.phases;

import com.ryankshah.skyrimcraft.entity.boss.dragon.SkyrimDragon;
import net.minecraft.world.entity.boss.enderdragon.phases.EnderDragonPhaseManager;

import java.lang.reflect.Constructor;
import java.util.Arrays;

public class SkyrimDragonPhase<T extends SkyrimDragonPhaseInstance>
{
    private static SkyrimDragonPhase<?>[] phases = new SkyrimDragonPhase[0];
    public static final SkyrimDragonPhase<SkyrimDragonHoldingPatternPhase> HOLDING_PATTERN = create(SkyrimDragonHoldingPatternPhase.class, "HoldingPattern");
    public static final SkyrimDragonPhase<SkyrimDragonStrafePlayerPhase> STRAFE_PLAYER = create(SkyrimDragonStrafePlayerPhase.class, "StrafePlayer");
    public static final SkyrimDragonPhase<SkyrimDragonLandingApproachPhase> LANDING_APPROACH = create(SkyrimDragonLandingApproachPhase.class, "LandingApproach");
    public static final SkyrimDragonPhase<SkyrimDragonLandingPhase> LANDING = create(SkyrimDragonLandingPhase.class, "Landing");
    public static final SkyrimDragonPhase<SkyrimDragonTakeoffPhase> TAKEOFF = create(SkyrimDragonTakeoffPhase.class, "Takeoff");
    public static final SkyrimDragonPhase<SkyrimDragonSittingFlamingPhase> SITTING_FLAMING = create(SkyrimDragonSittingFlamingPhase.class, "SittingFlaming");
    public static final SkyrimDragonPhase<SkyrimDragonSittingScanningPhase> SITTING_SCANNING = create(SkyrimDragonSittingScanningPhase.class, "SittingScanning");
    public static final SkyrimDragonPhase<SkyrimDragonSittingAttackingPhase> SITTING_ATTACKING = create(SkyrimDragonSittingAttackingPhase.class, "SittingAttacking");
    public static final SkyrimDragonPhase<SkyrimDragonChargePlayerPhase> CHARGING_PLAYER = create(SkyrimDragonChargePlayerPhase.class, "ChargingPlayer");
//    public static final SkyrimDragonPhase<DragonDeathPhase> DYING = create(DragonDeathPhase.class, "Dying");
    public static final SkyrimDragonPhase<SkyrimDragonHoverPhase> HOVERING = create(SkyrimDragonHoverPhase.class, "Hover");

    private final Class<? extends SkyrimDragonPhaseInstance> instanceClass;
    private final int id;
    private final String name;

    private SkyrimDragonPhase(int pId, Class<? extends SkyrimDragonPhaseInstance> pInstanceClass, String pName) {
        this.id = pId;
        this.instanceClass = pInstanceClass;
        this.name = pName;
    }

    public SkyrimDragonPhaseInstance createInstance(SkyrimDragon pDragon) {
        try {
            Constructor<? extends SkyrimDragonPhaseInstance> constructor = this.getConstructor();
            return constructor.newInstance(pDragon);
        } catch (Exception exception) {
            throw new Error(exception);
        }
    }

    protected Constructor<? extends SkyrimDragonPhaseInstance> getConstructor() throws NoSuchMethodException {
        return this.instanceClass.getConstructor(SkyrimDragon.class);
    }

    public int getId() {
        return this.id;
    }

    @Override
    public String toString() {
        return this.name + " (#" + this.id + ")";
    }

    public static SkyrimDragonPhase getById(int pId) {
        return pId >= 0 && pId < phases.length ? phases[pId] : HOLDING_PATTERN;
    }

    public static int getCount() {
        return phases.length;
    }

    private static <T extends SkyrimDragonPhaseInstance> SkyrimDragonPhase<T> create(Class<T> pPhase, String pName) {
        SkyrimDragonPhase<T> skyrimDragonPhase = new SkyrimDragonPhase<>(phases.length, pPhase, pName);
        phases = Arrays.copyOf(phases, phases.length + 1);
        phases[skyrimDragonPhase.getId()] = skyrimDragonPhase;
        return skyrimDragonPhase;
    }
}
