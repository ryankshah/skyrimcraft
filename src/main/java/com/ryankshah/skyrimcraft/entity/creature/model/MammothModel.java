package com.ryankshah.skyrimcraft.entity.creature.model;

import com.ryankshah.skyrimcraft.Skyrimcraft;
import com.ryankshah.skyrimcraft.entity.creature.MammothEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class MammothModel extends GeoModel<MammothEntity>
{
    @Override
    public ResourceLocation getModelResource(MammothEntity object)
    {
        return new ResourceLocation(Skyrimcraft.MODID, "geo/mammoth.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(MammothEntity object) {
        return new ResourceLocation(Skyrimcraft.MODID, "textures/entity/mammoth.png");
    }

    @Override
    public ResourceLocation getAnimationResource(MammothEntity object)
    {
        return new ResourceLocation(Skyrimcraft.MODID, "animations/mammoth.animation.json");
    }
}