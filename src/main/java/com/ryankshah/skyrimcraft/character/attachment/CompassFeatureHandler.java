package com.ryankshah.skyrimcraft.character.attachment;

import com.ryankshah.skyrimcraft.util.CompassFeature;
import net.minecraft.nbt.CompoundTag;
import net.neoforged.neoforge.attachment.IAttachmentHolder;
import net.neoforged.neoforge.common.util.INBTSerializable;

import java.util.ArrayList;
import java.util.List;

public class CompassFeatureHandler implements INBTSerializable<CompoundTag>
{
    private List<CompassFeature> compassFeatures;

    public CompassFeatureHandler() {
        this.compassFeatures = new ArrayList<>();
    }

    public CompassFeatureHandler(List<CompassFeature> features) {
        this.compassFeatures = features;
    }

    public CompassFeatureHandler(IAttachmentHolder iAttachmentHolder) {
        this.compassFeatures = new ArrayList<>();
    }

    public List<CompassFeature> getCompassFeatures() {
        return compassFeatures;
    }

    public void addCompassFeature(CompassFeature feature) {
        this.compassFeatures.add(feature);
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();

        tag.putInt("mapFeaturesSize", compassFeatures.size());
        int counter = 0;
        for(CompassFeature feature : compassFeatures) {
            tag.put("feature"+counter++, feature.serialise());
        }

        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag tag) {
        int mapFeaturesSize = tag.getInt("mapFeaturesSize");
        for(int i = 0; i < mapFeaturesSize; i++) {
            CompoundTag comp = tag.getCompound("feature"+i);
            compassFeatures.add(CompassFeature.deserialise(comp));
        }
    }
}
