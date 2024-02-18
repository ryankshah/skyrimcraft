package com.ryankshah.skyrimcraft.entity.passive.flying.model;

import com.ryankshah.skyrimcraft.Skyrimcraft;
import com.ryankshah.skyrimcraft.entity.passive.flying.LunarMoth;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;


public class LunarMothModel extends GeoModel<LunarMoth>
{
    @Override
    public ResourceLocation getModelResource(LunarMoth object)
    {
        return new ResourceLocation(Skyrimcraft.MODID, "geo/lunarmoth.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(LunarMoth object)
    {
        return new ResourceLocation(Skyrimcraft.MODID, "textures/entity/lunarmoth.png");
    }

    @Override
    public ResourceLocation getAnimationResource(LunarMoth object)
    {
        return new ResourceLocation(Skyrimcraft.MODID, "animations/lunarmoth.animation.json");
    }
}
