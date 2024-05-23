package com.ryankshah.skyrimcraft.dimension;

import com.ryankshah.skyrimcraft.Skyrimcraft;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;

public class DimensionInit
{
    public static final ResourceKey<LevelStem> SOVNGARDE_KEY = ResourceKey.create(Registries.LEVEL_STEM,
            new ResourceLocation(Skyrimcraft.MODID, "sovngarde"));
    public static final ResourceKey<Level> SOVNGARDE_LEVEL_KEY = ResourceKey.create(Registries.DIMENSION,
            new ResourceLocation(Skyrimcraft.MODID, "sovngarde"));
    public static final ResourceKey<DimensionType> SOVNGARDE_DIM_TYPE = ResourceKey.create(Registries.DIMENSION_TYPE,
            new ResourceLocation(Skyrimcraft.MODID, "sovngarde"));
}