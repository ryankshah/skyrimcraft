package com.ryankshah.skyrimcraft.entity.boss.dragon;

import com.mojang.logging.LogUtils;
import com.ryankshah.skyrimcraft.entity.boss.dragon.phases.SkyrimDragonPhase;
import com.ryankshah.skyrimcraft.entity.boss.dragon.phases.SkyrimDragonPhaseInstance;
import org.slf4j.Logger;

import javax.annotation.Nullable;

public class SkyrimDragonPhaseManager {
    private static final Logger LOGGER = LogUtils.getLogger();
    private final SkyrimDragon dragon;
    private final SkyrimDragonPhaseInstance[] phases = new SkyrimDragonPhaseInstance[SkyrimDragonPhase.getCount()];
    @Nullable
    private SkyrimDragonPhaseInstance currentPhase;

    public SkyrimDragonPhaseManager(SkyrimDragon pDragon) {
        this.dragon = pDragon;
        this.setPhase(SkyrimDragonPhase.HOVERING);
    }

    public void setPhase(SkyrimDragonPhase<?> pPhase) {
        if (this.currentPhase == null || pPhase != this.currentPhase.getPhase()) {
            if (this.currentPhase != null) {
                this.currentPhase.end();
            }

            this.currentPhase = this.getPhase(pPhase);
            if (!this.dragon.level().isClientSide) {
                this.dragon.getEntityData().set(SkyrimDragon.DATA_PHASE, pPhase.getId());
            }

            LOGGER.debug("Dragon is now in phase {} on the {}", pPhase, this.dragon.level().isClientSide ? "client" : "server");
            this.currentPhase.begin();
        }
    }

    public SkyrimDragonPhaseInstance getCurrentPhase() {
        return this.currentPhase;
    }

    public <T extends SkyrimDragonPhaseInstance> T getPhase(SkyrimDragonPhase<T> pPhase) {
        int i = pPhase.getId();
        if (this.phases[i] == null) {
            this.phases[i] = pPhase.createInstance(this.dragon);
        }

        return (T)this.phases[i];
    }
}
