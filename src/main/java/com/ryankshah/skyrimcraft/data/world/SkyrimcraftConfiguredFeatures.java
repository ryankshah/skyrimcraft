package com.ryankshah.skyrimcraft.data.world;

import com.ryankshah.skyrimcraft.Skyrimcraft;
import com.ryankshah.skyrimcraft.init.BlockInit;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.NoiseProvider;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.minecraft.world.level.levelgen.synth.NormalNoise;

import java.util.List;

public class SkyrimcraftConfiguredFeatures
{
    public static final ResourceKey<ConfiguredFeature<?, ?>> CORUNDUM_ORE_KEY = registerKey("corundum_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> EBONY_ORE_KEY = registerKey("ebony_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MALACHITE_ORE_KEY = registerKey("malachite_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MOONSTONE_ORE_KEY = registerKey("moonstone_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORICHALCUM_ORE_KEY = registerKey("orichalcum_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> QUICKSILVER_ORE_KEY = registerKey("quicksilver_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SILVER_ORE_KEY = registerKey("silver_ore");

    public static final ResourceKey<ConfiguredFeature<?, ?>> MOUNTAIN_FLOWER_KEY = registerKey("mountain_flower");

    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {
        RuleTest stoneReplaceable = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplaceables = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
        RuleTest netherrackReplacables = new BlockMatchTest(Blocks.NETHERRACK);
        RuleTest endReplaceables = new BlockMatchTest(Blocks.END_STONE);

        List<OreConfiguration.TargetBlockState> corundumOres = List.of(OreConfiguration.target(stoneReplaceable,
                        BlockInit.CORUNDUM_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepslateReplaceables, BlockInit.DEEPSLATE_CORUNDUM_ORE.get().defaultBlockState()));
        register(context, CORUNDUM_ORE_KEY, Feature.ORE, new OreConfiguration(corundumOres, 9));

        List<OreConfiguration.TargetBlockState> ebonyOres = List.of(OreConfiguration.target(stoneReplaceable,
                        BlockInit.EBONY_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepslateReplaceables, BlockInit.DEEPSLATE_EBONY_ORE.get().defaultBlockState()));
        register(context, EBONY_ORE_KEY, Feature.ORE, new OreConfiguration(ebonyOres, 9));

        List<OreConfiguration.TargetBlockState> malachiteOres = List.of(OreConfiguration.target(stoneReplaceable,
                        BlockInit.MALACHITE_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepslateReplaceables, BlockInit.DEEPSLATE_MALACHITE_ORE.get().defaultBlockState()));
        register(context, MALACHITE_ORE_KEY, Feature.ORE, new OreConfiguration(malachiteOres, 9));

        List<OreConfiguration.TargetBlockState> moonstoneOres = List.of(OreConfiguration.target(stoneReplaceable,
                        BlockInit.MOONSTONE_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepslateReplaceables, BlockInit.DEEPSLATE_MOONSTONE_ORE.get().defaultBlockState()));
        register(context, MOONSTONE_ORE_KEY, Feature.ORE, new OreConfiguration(moonstoneOres, 9));

        List<OreConfiguration.TargetBlockState> orichalcumOres = List.of(OreConfiguration.target(stoneReplaceable,
                        BlockInit.ORICHALCUM_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepslateReplaceables, BlockInit.DEEPSLATE_ORICHALCUM_ORE.get().defaultBlockState()));
        register(context, ORICHALCUM_ORE_KEY, Feature.ORE, new OreConfiguration(orichalcumOres, 9));

        List<OreConfiguration.TargetBlockState> quicksilverOres = List.of(OreConfiguration.target(stoneReplaceable,
                        BlockInit.QUICKSILVER_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepslateReplaceables, BlockInit.DEEPSLATE_QUICKSILVER_ORE.get().defaultBlockState()));
        register(context, QUICKSILVER_ORE_KEY, Feature.ORE, new OreConfiguration(quicksilverOres, 9));

        List<OreConfiguration.TargetBlockState> silverOres = List.of(OreConfiguration.target(stoneReplaceable,
                        BlockInit.SILVER_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepslateReplaceables, BlockInit.DEEPSLATE_SILVER_ORE.get().defaultBlockState()));
        register(context, SILVER_ORE_KEY, Feature.ORE, new OreConfiguration(silverOres, 9));

        register(context, MOUNTAIN_FLOWER_KEY, Feature.FLOWER, new RandomPatchConfiguration(32, 7, 3,
                PlacementUtils.onlyWhenEmpty(
                        Feature.SIMPLE_BLOCK,
                        new SimpleBlockConfiguration(
                                new NoiseProvider(
                                        2345L,
                                        new NormalNoise.NoiseParameters(0, 1.0),
                                        0.020833334F,
                                        List.of(
                                                BlockInit.PURPLE_MOUNTAIN_FLOWER.get().defaultBlockState(),
                                                BlockInit.RED_MOUNTAIN_FLOWER.get().defaultBlockState(),
                                                BlockInit.BLUE_MOUNTAIN_FLOWER.get().defaultBlockState(),
                                                BlockInit.YELLOW_MOUNTAIN_FLOWER.get().defaultBlockState()
                                        )
                                )
                        ))));
    }


    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(Skyrimcraft.MODID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstapContext<ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}