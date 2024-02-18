package com.ryankshah.skyrimcraft.entity.passive.flying.model;

import com.ryankshah.skyrimcraft.Skyrimcraft;
import com.ryankshah.skyrimcraft.entity.passive.flying.BlueDartwing;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;


public class BlueDartwingModel extends GeoModel<BlueDartwing>
{
    @Override
    public ResourceLocation getModelResource(BlueDartwing object)
    {
        return new ResourceLocation(Skyrimcraft.MODID, "geo/bluedartwing.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(BlueDartwing object)
    {
        return new ResourceLocation(Skyrimcraft.MODID, "textures/entity/bluedartwing.png");
    }

    @Override
    public ResourceLocation getAnimationResource(BlueDartwing object)
    {
        return new ResourceLocation(Skyrimcraft.MODID, "animations/bluedartwing.animation.json");
    }
}
