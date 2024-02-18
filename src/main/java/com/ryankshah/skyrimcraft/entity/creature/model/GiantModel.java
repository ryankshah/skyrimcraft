package com.ryankshah.skyrimcraft.entity.creature.model;

import com.ryankshah.skyrimcraft.Skyrimcraft;
import com.ryankshah.skyrimcraft.entity.creature.GiantEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class GiantModel extends GeoModel<GiantEntity>
{
    @Override
    public ResourceLocation getModelResource(GiantEntity object)
    {
        return new ResourceLocation(Skyrimcraft.MODID, "geo/giant.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(GiantEntity object) {
        return new ResourceLocation(Skyrimcraft.MODID, "textures/entity/giant.png");
    }

    @Override
    public ResourceLocation getAnimationResource(GiantEntity object)
    {
        return new ResourceLocation(Skyrimcraft.MODID, "animations/giant.animation.json");
    }
}