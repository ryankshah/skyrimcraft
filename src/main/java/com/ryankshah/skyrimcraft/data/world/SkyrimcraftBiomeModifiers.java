package com.ryankshah.skyrimcraft.data.world;

import com.ryankshah.skyrimcraft.Skyrimcraft;
import com.ryankshah.skyrimcraft.init.EntityInit;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.List;

//TODO: Add mushrooms, canis root, and pearl oysters
public class SkyrimcraftBiomeModifiers
{
    public static final ResourceKey<BiomeModifier> ADD_CORUNDUM_ORE = registerKey("add_corundum_ore");
    public static final ResourceKey<BiomeModifier> ADD_EBONY_ORE = registerKey("add_ebony_ore");
    public static final ResourceKey<BiomeModifier> ADD_MALACHITE_ORE = registerKey("add_malachite_ore");
    public static final ResourceKey<BiomeModifier> ADD_MOONSTONE_ORE = registerKey("add_moonstone_ore");
    public static final ResourceKey<BiomeModifier> ADD_ORICHALCUM_ORE = registerKey("add_orichalcum_ore");
    public static final ResourceKey<BiomeModifier> ADD_QUICKSILVER_ORE = registerKey("add_quicksilver_ore");
    public static final ResourceKey<BiomeModifier> ADD_SILVER_ORE = registerKey("add_silver_ore");

    public static final ResourceKey<BiomeModifier> ADD_MOUNTAIN_FLOWERS = registerKey("add_mountain_flowers");
    public static final ResourceKey<BiomeModifier> ADD_MUSHROOMS = registerKey("add_mushrooms");
    public static final ResourceKey<BiomeModifier> ADD_CREEP_CLUSTER = registerKey("add_creep_cluster");
    public static final ResourceKey<BiomeModifier> ADD_DESERT_PLANTS = registerKey("add_desert_plants");
    public static final ResourceKey<BiomeModifier> ADD_OYSTERS = registerKey("add_oysters");
    public static final ResourceKey<BiomeModifier> ADD_NESTS = registerKey("add_nests");

    public static final ResourceKey<BiomeModifier> ADD_PLAINS_FLYING_MOBS = registerKey("add_plains_flying_mobs");
    public static final ResourceKey<BiomeModifier> ADD_DRIPSTONE_MOBS = registerKey("add_dripstone_mobs");
    public static final ResourceKey<BiomeModifier> ADD_SNOW_MOBS = registerKey("add_snow_mobs");
    public static final ResourceKey<BiomeModifier> ADD_PLAINS_MOBS = registerKey("add_plains_mobs");

    public static void bootstrap(BootstapContext<BiomeModifier> context) {
        var placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        var biomes = context.lookup(Registries.BIOME);

        context.register(ADD_CORUNDUM_ORE, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(SkyrimcraftPlacedFeatures.CORUNDUM_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));
        context.register(ADD_EBONY_ORE, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(SkyrimcraftPlacedFeatures.EBONY_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));
        context.register(ADD_MALACHITE_ORE, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(SkyrimcraftPlacedFeatures.MALACHITE_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));
        context.register(ADD_MOONSTONE_ORE, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(SkyrimcraftPlacedFeatures.MOONSTONE_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));
        context.register(ADD_ORICHALCUM_ORE, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(SkyrimcraftPlacedFeatures.ORICHALCUM_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));
        context.register(ADD_QUICKSILVER_ORE, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(SkyrimcraftPlacedFeatures.QUICKSILVER_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));
        context.register(ADD_SILVER_ORE, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(SkyrimcraftPlacedFeatures.SILVER_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));

        context.register(ADD_MOUNTAIN_FLOWERS, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(SkyrimcraftPlacedFeatures.MOUNTAIN_FLOWER_PLACED_KEY)),
                GenerationStep.Decoration.VEGETAL_DECORATION));

        context.register(ADD_MUSHROOMS, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(SkyrimcraftPlacedFeatures.MUSHROOMS_PLACED_KEY)),
                GenerationStep.Decoration.VEGETAL_DECORATION));

        context.register(ADD_DESERT_PLANTS, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(SkyrimcraftPlacedFeatures.DESERT_PLANTS_PLACED_KEY)),
                GenerationStep.Decoration.VEGETAL_DECORATION));

        context.register(ADD_OYSTERS, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_BEACH),
                HolderSet.direct(placedFeatures.getOrThrow(SkyrimcraftPlacedFeatures.OYSTERS_PLACED_KEY)),
                GenerationStep.Decoration.VEGETAL_DECORATION));

        context.register(ADD_NESTS, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_FOREST),
                HolderSet.direct(placedFeatures.getOrThrow(SkyrimcraftPlacedFeatures.BIRDS_NEST_PLACED_KEY)),
                GenerationStep.Decoration.VEGETAL_DECORATION));

        context.register(ADD_CREEP_CLUSTER, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_HILL),
                HolderSet.direct(placedFeatures.getOrThrow(SkyrimcraftPlacedFeatures.CREEP_CLUSTER_PLACED_KEY)),
                GenerationStep.Decoration.VEGETAL_DECORATION));

        context.register(ADD_PLAINS_FLYING_MOBS, new BiomeModifiers.AddSpawnsBiomeModifier(
                HolderSet.direct(
                        biomes.getOrThrow(Biomes.PLAINS), biomes.getOrThrow(Biomes.SUNFLOWER_PLAINS),
                        biomes.getOrThrow(Biomes.MEADOW)
                ),
                List.of(
                        new MobSpawnSettings.SpawnerData(EntityInit.BLUE_BUTTERFLY.get(), 100, 3, 5),
                        new MobSpawnSettings.SpawnerData(EntityInit.MONARCH_BUTTERFLY.get(), 100, 3, 5),
                        new MobSpawnSettings.SpawnerData(EntityInit.BLUE_DARTWING.get(), 100, 3, 5),
                        new MobSpawnSettings.SpawnerData(EntityInit.ORANGE_DARTWING.get(), 100, 3, 5),
                        new MobSpawnSettings.SpawnerData(EntityInit.LUNAR_MOTH.get(), 100, 3, 5)
                )
        ));
        context.register(ADD_DRIPSTONE_MOBS, new BiomeModifiers.AddSpawnsBiomeModifier(
                HolderSet.direct(
                        biomes.getOrThrow(Biomes.DRIPSTONE_CAVES), biomes.getOrThrow(Biomes.LUSH_CAVES)
                ),
                List.of(
                        new MobSpawnSettings.SpawnerData(EntityInit.TORCHBUG.get(), 200, 4, 6)
                )
        ));
        context.register(ADD_SNOW_MOBS, new BiomeModifiers.AddSpawnsBiomeModifier(
                HolderSet.direct(
                        biomes.getOrThrow(Biomes.SNOWY_BEACH), biomes.getOrThrow(Biomes.SNOWY_PLAINS),
                        biomes.getOrThrow(Biomes.SNOWY_SLOPES), biomes.getOrThrow(Biomes.SNOWY_TAIGA)
                ),
                List.of(
                        new MobSpawnSettings.SpawnerData(EntityInit.SABRE_CAT.get(), 1, 1, 2),
                        new MobSpawnSettings.SpawnerData(EntityInit.GIANT.get(), 1, 2, 2),
                        new MobSpawnSettings.SpawnerData(EntityInit.DRAUGR.get(), 2, 2, 2)
                )
        ));
        context.register(ADD_PLAINS_MOBS, new BiomeModifiers.AddSpawnsBiomeModifier(
                HolderSet.direct(
                        biomes.getOrThrow(Biomes.PLAINS), biomes.getOrThrow(Biomes.SAVANNA),
                        biomes.getOrThrow(Biomes.SAVANNA_PLATEAU)
                ),
                List.of(
                        new MobSpawnSettings.SpawnerData(EntityInit.SABRE_CAT.get(), 1, 1, 2),
                        new MobSpawnSettings.SpawnerData(EntityInit.DRAUGR.get(), 3, 2, 2),
                        new MobSpawnSettings.SpawnerData(EntityInit.DWARVEN_SPIDER.get(), 3, 2, 2)
                )
        ));
    }

    private static ResourceKey<BiomeModifier> registerKey(String name) {
        return ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, new ResourceLocation(Skyrimcraft.MODID, name));
    }
}