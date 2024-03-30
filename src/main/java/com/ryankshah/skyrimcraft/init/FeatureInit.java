package com.ryankshah.skyrimcraft.init;

import com.ryankshah.skyrimcraft.Skyrimcraft;
import com.ryankshah.skyrimcraft.world.NbtFeature;
import com.ryankshah.skyrimcraft.world.NbtFeatureConfig;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class FeatureInit
{
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(BuiltInRegistries.FEATURE, Skyrimcraft.MODID);

    public static final Supplier<Feature<NbtFeatureConfig>> NBT_FEATURE = FEATURES.register("nbt_feature", NbtFeature::new);
}