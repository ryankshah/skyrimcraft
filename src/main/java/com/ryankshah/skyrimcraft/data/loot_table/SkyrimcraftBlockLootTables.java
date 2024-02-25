package com.ryankshah.skyrimcraft.data.loot_table;

import com.ryankshah.skyrimcraft.Skyrimcraft;
import com.ryankshah.skyrimcraft.block.PearlOysterBlock;
import com.ryankshah.skyrimcraft.init.BlockInit;
import com.ryankshah.skyrimcraft.init.ItemInit;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class SkyrimcraftBlockLootTables extends BlockLootSubProvider
{
    protected SkyrimcraftBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {

        // normal ores
//        add(BlockInit.EXAMPLE_ORE.get(), createOreDrop(BlockInit.EXAMPLE_ORE.get(), ItemInit.RAW_EXAMPLE.get()));
//
//        // deepslate ores
//        add(BlockInit.DEEPSLATE_EXAMPLE_ORE.get(), createOreDrop(BlockInit.DEEPSLATE_EXAMPLE_ORE.get(), ItemInit.RAW_EXAMPLE.get()));

        //TODO: Change these when we add ingots etc.!
        dropSelf(BlockInit.CORUNDUM_ORE.get());
        dropSelf(BlockInit.EBONY_ORE.get());
        dropSelf(BlockInit.MALACHITE_ORE.get());
        dropSelf(BlockInit.MOONSTONE_ORE.get());
        dropSelf(BlockInit.ORICHALCUM_ORE.get());
        dropSelf(BlockInit.QUICKSILVER_ORE.get());
        dropSelf(BlockInit.SILVER_ORE.get());
        dropSelf(BlockInit.DEEPSLATE_CORUNDUM_ORE.get());
        dropSelf(BlockInit.DEEPSLATE_EBONY_ORE.get());
        dropSelf(BlockInit.DEEPSLATE_MALACHITE_ORE.get());
        dropSelf(BlockInit.DEEPSLATE_MOONSTONE_ORE.get());
        dropSelf(BlockInit.DEEPSLATE_ORICHALCUM_ORE.get());
        dropSelf(BlockInit.DEEPSLATE_QUICKSILVER_ORE.get());
        dropSelf(BlockInit.DEEPSLATE_SILVER_ORE.get());
        dropSelf(BlockInit.SHOUT_BLOCK.get());
        dropSelf(BlockInit.ALCHEMY_TABLE.get());
        dropSelf(BlockInit.OVEN.get());
        dropSelf(BlockInit.BLACKSMITH_FORGE.get());

        dropSelf(BlockInit.RED_MOUNTAIN_FLOWER.get());
        dropSelf(BlockInit.BLUE_MOUNTAIN_FLOWER.get());
        dropSelf(BlockInit.YELLOW_MOUNTAIN_FLOWER.get());
        dropSelf(BlockInit.PURPLE_MOUNTAIN_FLOWER.get());

        dropSelf(BlockInit.CANIS_ROOT_BLOCK.get());
        dropSelf(BlockInit.BLEEDING_CROWN_BLOCK.get());

        LootItemCondition.Builder lootitemcondition$pearloyster = LootItemBlockStatePropertyCondition.hasBlockStateProperties(BlockInit.PEARL_OYSTER_BLOCK.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(PearlOysterBlock.IS_OPEN, true))
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(PearlOysterBlock.IS_EMPTY, false));
        add(BlockInit.PEARL_OYSTER_BLOCK.get(), createCropDrops(BlockInit.PEARL_OYSTER_BLOCK.get(), ItemInit.PEARL.get(), BlockInit.PEARL_OYSTER.get(), lootitemcondition$pearloyster));

        LootItemCondition.Builder lootitemcondition$builder1 = LootItemBlockStatePropertyCondition.hasBlockStateProperties(BlockInit.TOMATO_CROP.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(CropBlock.AGE, 7));
        add(BlockInit.TOMATO_CROP.get(), createCropDrops(BlockInit.TOMATO_CROP.get(), ItemInit.TOMATO.get(), ItemInit.TOMATO_SEEDS.get(), lootitemcondition$builder1));

        LootItemCondition.Builder ilootcondition$garliccrop = LootItemBlockStatePropertyCondition.hasBlockStateProperties(BlockInit.GARLIC_CROP.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(CropBlock.AGE, 7));
        add(BlockInit.GARLIC_CROP.get(), createCropDrops(BlockInit.GARLIC_CROP.get(), ItemInit.GARLIC.get(), ItemInit.GARLIC.get(), ilootcondition$garliccrop));

    }

    @Override
    public @NotNull Iterable<Block> getKnownBlocks() {
        return BuiltInRegistries.BLOCK.stream()
                .filter(block -> Optional.of(BuiltInRegistries.BLOCK.getKey(block))
                        .filter(key -> key.getNamespace().equals(Skyrimcraft.MODID))
                        .isPresent())
                .collect(Collectors.toSet());
    }
}