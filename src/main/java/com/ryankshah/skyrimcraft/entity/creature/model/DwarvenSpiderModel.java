package com.ryankshah.skyrimcraft.entity.creature.model;

import com.ryankshah.skyrimcraft.Skyrimcraft;
import com.ryankshah.skyrimcraft.entity.creature.DwarvenSpiderEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class DwarvenSpiderModel extends GeoModel<DwarvenSpiderEntity>
{
    @Override
    public ResourceLocation getModelResource(DwarvenSpiderEntity object)
    {
        return new ResourceLocation(Skyrimcraft.MODID, "geo/dwarven_spider.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(DwarvenSpiderEntity object) {
        return new ResourceLocation(Skyrimcraft.MODID, "textures/entity/dwarven_spider.png");
    }

    @Override
    public ResourceLocation getAnimationResource(DwarvenSpiderEntity object)
    {
        return new ResourceLocation(Skyrimcraft.MODID, "animations/dwarven_spider.animation.json");
    }
}