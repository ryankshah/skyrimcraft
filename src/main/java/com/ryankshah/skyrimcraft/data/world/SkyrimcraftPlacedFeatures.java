package com.ryankshah.skyrimcraft.data.world;

import com.ryankshah.skyrimcraft.Skyrimcraft;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class SkyrimcraftPlacedFeatures
{
    public static final ResourceKey<PlacedFeature> CORUNDUM_ORE_PLACED_KEY = registerKey("corundum_ore_placed");
    public static final ResourceKey<PlacedFeature> EBONY_ORE_PLACED_KEY = registerKey("ebony_ore_placed");
    public static final ResourceKey<PlacedFeature> MALACHITE_ORE_PLACED_KEY = registerKey("malachite_ore_placed");
    public static final ResourceKey<PlacedFeature> MOONSTONE_ORE_PLACED_KEY = registerKey("moonstone_ore_placed");
    public static final ResourceKey<PlacedFeature> ORICHALCUM_ORE_PLACED_KEY = registerKey("orichalcum_ore_placed");
    public static final ResourceKey<PlacedFeature> QUICKSILVER_ORE_PLACED_KEY = registerKey("quicksilver_ore_placed");
    public static final ResourceKey<PlacedFeature> SILVER_ORE_PLACED_KEY = registerKey("silver_ore_placed");

    public static final ResourceKey<PlacedFeature> MOUNTAIN_FLOWER_PLACED_KEY = registerKey("mountain_flower_placed");

//    public static Holder<PlacedFeature> MOUNTAIN_FLOWER_PLACED;

    public static void bootstrap(BootstapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        register(context, CORUNDUM_ORE_PLACED_KEY, configuredFeatures.getOrThrow(SkyrimcraftConfiguredFeatures.CORUNDUM_ORE_KEY),
                OrePlacement.commonOrePlacement(24,
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(80))));
        register(context, EBONY_ORE_PLACED_KEY, configuredFeatures.getOrThrow(SkyrimcraftConfiguredFeatures.EBONY_ORE_KEY),
                OrePlacement.commonOrePlacement(32,
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(80))));
        register(context, MALACHITE_ORE_PLACED_KEY, configuredFeatures.getOrThrow(SkyrimcraftConfiguredFeatures.MALACHITE_ORE_KEY),
                OrePlacement.commonOrePlacement(28,
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(80))));
        register(context, MOONSTONE_ORE_PLACED_KEY, configuredFeatures.getOrThrow(SkyrimcraftConfiguredFeatures.MOONSTONE_ORE_KEY),
                OrePlacement.commonOrePlacement(32,
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(80))));
        register(context, ORICHALCUM_ORE_PLACED_KEY, configuredFeatures.getOrThrow(SkyrimcraftConfiguredFeatures.ORICHALCUM_ORE_KEY),
                OrePlacement.commonOrePlacement(30,
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(80))));
        register(context, QUICKSILVER_ORE_PLACED_KEY, configuredFeatures.getOrThrow(SkyrimcraftConfiguredFeatures.QUICKSILVER_ORE_KEY),
                OrePlacement.commonOrePlacement(24,
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(80))));
        register(context, SILVER_ORE_PLACED_KEY, configuredFeatures.getOrThrow(SkyrimcraftConfiguredFeatures.SILVER_ORE_KEY),
                OrePlacement.commonOrePlacement(20,
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(80))));

        register(context, MOUNTAIN_FLOWER_PLACED_KEY,
                configuredFeatures.getOrThrow(SkyrimcraftConfiguredFeatures.MOUNTAIN_FLOWER_KEY),
                List.of(
                        RarityFilter.onAverageOnceEvery(26),
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP,
                        BiomeFilter.biome()
                )
        );
    }


    private static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(Skyrimcraft.MODID, name));
    }

    private static void register(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        PlacedFeature feature = new PlacedFeature(configuration, List.copyOf(modifiers));
        context.register(key, feature);
    }
}