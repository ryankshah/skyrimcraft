package com.ryankshah.skyrimcraft.character.attachment;

import net.minecraft.nbt.CompoundTag;
import net.neoforged.neoforge.common.util.INBTSerializable;

import java.util.ArrayList;
import java.util.List;

public class PlayerTargetsHandler implements INBTSerializable<CompoundTag>
{
    private List<Integer> targetingEntities;
    private int currentTarget;

    public PlayerTargetsHandler() {
        this.targetingEntities = new ArrayList<>();
        this.currentTarget = -1;
    }

    public PlayerTargetsHandler(List<Integer> targets, int current) {
        this.targetingEntities = targets;
        this.currentTarget = current;
    }

    public List<Integer> getTargets() {
        return targetingEntities;
    }

    public int getCurrentTarget() { return currentTarget; }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();

        tag.putInt("targetingEntitiesSize", targetingEntities.size());
        int counter = 0;
        for(int entityID : targetingEntities)
            tag.putInt("targetingEntity"+counter++, entityID);

        tag.putInt("currentTarget", currentTarget);

        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag tag) {
        int targetingEntitiesSize = tag.getInt("targetingEntitiesSize");
        for(int i = 0; i < targetingEntitiesSize; i++) {
            targetingEntities.add(tag.getInt("targetingEntity"+i));
        }
        tag.getInt("currentTarget");
    }
}
