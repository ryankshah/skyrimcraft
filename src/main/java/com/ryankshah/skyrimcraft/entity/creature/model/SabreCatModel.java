package com.ryankshah.skyrimcraft.entity.creature.model;

import com.ryankshah.skyrimcraft.Skyrimcraft;
import com.ryankshah.skyrimcraft.entity.creature.SabreCatEntity;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import software.bernie.geckolib.model.GeoModel;

import java.util.Arrays;
import java.util.List;


public class SabreCatModel extends GeoModel<SabreCatEntity>
{
    @Override
    public ResourceLocation getModelResource(SabreCatEntity animatable) {
        return new ResourceLocation(Skyrimcraft.MODID, "geo/sabre_cat.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(SabreCatEntity animatable) {
        final List<ResourceKey<Biome>> SNOWY_BIOMES = Arrays.asList(
                Biomes.SNOWY_BEACH, Biomes.SNOWY_TAIGA,
                Biomes.SNOWY_SLOPES, Biomes.SNOWY_PLAINS,
                Biomes.FROZEN_RIVER, Biomes.FROZEN_PEAKS,
                Biomes.ICE_SPIKES, Biomes.GROVE,
                Biomes.JAGGED_PEAKS
        );
        if(SNOWY_BIOMES.contains(animatable.getBiomeType())) //biome.isPresent() &&
            return new ResourceLocation(Skyrimcraft.MODID, "textures/entity/snowy_sabre_cat.png");
        else
            return new ResourceLocation(Skyrimcraft.MODID, "textures/entity/sabre_cat.png");
    }

    @Override
    public ResourceLocation getAnimationResource(SabreCatEntity animatable) {
        return new ResourceLocation(Skyrimcraft.MODID, "animations/sabre_cat.animation.json");
    }
}