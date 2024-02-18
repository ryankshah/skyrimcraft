package com.ryankshah.skyrimcraft.entity.boss.dragon.model;

import com.ryankshah.skyrimcraft.Skyrimcraft;
import com.ryankshah.skyrimcraft.entity.boss.dragon.SkyrimDragonEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class SkyrimDragonModel extends GeoModel<SkyrimDragonEntity>
{
    @Override
    public ResourceLocation getModelResource(SkyrimDragonEntity object)
    {
        return new ResourceLocation(Skyrimcraft.MODID, "geo/dragon.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(SkyrimDragonEntity object) {
        return new ResourceLocation(Skyrimcraft.MODID, "textures/entity/normal_dragon.png");
    }

    @Override
    public ResourceLocation getAnimationResource(SkyrimDragonEntity object)
    {
        return new ResourceLocation(Skyrimcraft.MODID, "animations/dragon.animation.json");
    }
}