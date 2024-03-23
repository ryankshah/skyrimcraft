package com.ryankshah.skyrimcraft.data.loot_table;

import com.ryankshah.skyrimcraft.Skyrimcraft;
import com.ryankshah.skyrimcraft.init.ItemInit;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.LootingEnchantFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.function.BiConsumer;

public class SkyrimcraftLootTableProvider implements LootTableSubProvider
{
    @Override
    public void generate(BiConsumer<ResourceLocation, LootTable.Builder> pOutput) {
        pOutput.accept(
                new ResourceLocation(Skyrimcraft.MODID, "grasspod"),
                LootTable.lootTable().withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(ItemInit.GRASS_POD.get())
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.875F, 1.0F)))
                                .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(1.0F, 2.0F)))
                        )
                )
        );

        pOutput.accept(
                new ResourceLocation(Skyrimcraft.MODID, "oregemdrops"),
                LootTable.lootTable().withPool(LootPool.lootPool()
                        .name("oresGemPool")
                        .setRolls(UniformGenerator.between(0.125F, 1.0F))
                        .add(LootItem.lootTableItem(ItemInit.FLAWED_RUBY)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.25F, 1.0F)))
                                .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(1.0F, 2.0F)))
                        )
                        .add(LootItem.lootTableItem(ItemInit.FLAWED_EMERALD)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.25F, 1.0F)))
                                .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(1.0F, 2.0F)))
                        )
                        .add(LootItem.lootTableItem(ItemInit.FLAWED_DIAMOND)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.25F, 1.0F)))
                                .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(1.0F, 2.0F)))
                        )
                        .add(LootItem.lootTableItem(ItemInit.FLAWLESS_RUBY)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.25F, 1.0F)))
                                .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(1.0F, 2.0F)))
                        )
                        .add(LootItem.lootTableItem(ItemInit.FLAWED_GARNET)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.25F, 1.0F)))
                                .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(1.0F, 2.0F)))
                        )
                        .add(LootItem.lootTableItem(ItemInit.FLAWLESS_GARNET)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.25F, 1.0F)))
                                .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(1.0F, 2.0F)))
                        )
                        .add(LootItem.lootTableItem(ItemInit.FLAWED_AMETHYST)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.25F, 1.0F)))
                                .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(1.0F, 2.0F)))
                        )
                )
        );

        pOutput.accept(
                new ResourceLocation(Skyrimcraft.MODID, "beehive"),
                LootTable.lootTable().withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(ItemInit.BEEHIVE_HUSK.get())
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.45f, 1.0F)))
                                .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(1.0F, 2.0F)))
                        )
                )
        );

        pOutput.accept(
                new ResourceLocation(Skyrimcraft.MODID, "spider"),
                LootTable.lootTable().withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(ItemInit.SPIDER_EGG.get())
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.45f, 1.0F)))
                                .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.85F, 1.0F)))
                        )
                )
        );

        pOutput.accept(
                new ResourceLocation(Skyrimcraft.MODID, "salmon"),
                LootTable.lootTable().withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(ItemInit.SALMON_ROE.get())
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.45f, 1.0F)))
                                .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.85F, 1.0F)))
                        )
                )
        );

        pOutput.accept(
                new ResourceLocation(Skyrimcraft.MODID, "bee"),
                LootTable.lootTable().withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(ItemInit.BEE.get())
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.875F, 1.0F)))
                                .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(1.0F, 2.0F)))
                        )
                )
        );

        pOutput.accept(
                new ResourceLocation(Skyrimcraft.MODID, "goat"),
                LootTable.lootTable().withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(ItemInit.LEG_OF_GOAT.get())
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.875F, 1.0F)))
                                .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(1.0F, 2.0F)))
                        )
                )
        );

        pOutput.accept(
                new ResourceLocation(Skyrimcraft.MODID, "horse"),
                LootTable.lootTable().withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(ItemInit.HORSE_MEAT.get())
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.875F, 1.0F)))
                                .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(1.0F, 2.0F)))
                        )
                )
        );

        LootTable.Builder chestPool = LootTable.lootTable().withPool(
                LootPool.lootPool()
                        .setRolls(UniformGenerator.between(1.0F, 3.0F))
                        .add(LootItem.lootTableItem(ItemInit.VAMPIRE_DUST)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.5F, 2.0F)))
                        )
                        .add(LootItem.lootTableItem(ItemInit.FIRE_SALTS)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.5F, 1.0F)))
                        )
                        .add(LootItem.lootTableItem(ItemInit.SALT_PILE)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.5F, 3.0F)))
                        )
                        .add(LootItem.lootTableItem(ItemInit.DWARVEN_OIL)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.5F, 2.0F)))
                        )
                        .add(LootItem.lootTableItem(ItemInit.TROLL_FAT)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.5F, 2.0F)))
                        )
                        .add(LootItem.lootTableItem(ItemInit.TAPROOT)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.5F, 2.0F)))
                        )
                        .add(LootItem.lootTableItem(ItemInit.BEAR_CLAWS)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.5F, 2.0F)))
                        )
                        .add(LootItem.lootTableItem(ItemInit.CHARRED_SKEEVER_HIDE)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.5F, 3.0F)))
                        )
                        .add(LootItem.lootTableItem(ItemInit.BRIAR_HEART)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.5F, 2.0F)))
                        )
        );

        pOutput.accept(
                new ResourceLocation(Skyrimcraft.MODID, "chests/simple_dungeon"),
                chestPool
        );
        pOutput.accept(
                new ResourceLocation(Skyrimcraft.MODID, "chests/abandoned_mineshaft"),
                chestPool
        );
        pOutput.accept(
                new ResourceLocation(Skyrimcraft.MODID, "chests/buried_treasure"),
                chestPool
        );
        pOutput.accept(
                new ResourceLocation(Skyrimcraft.MODID, "chests/desert_pyramid"),
                chestPool
        );
        pOutput.accept(
                new ResourceLocation(Skyrimcraft.MODID, "chests/shipwreck_supply"),
                chestPool
        );
        pOutput.accept(
                new ResourceLocation(Skyrimcraft.MODID, "chests/stronghold_corridor"),
                chestPool
        );
        pOutput.accept(
                new ResourceLocation(Skyrimcraft.MODID, "gameplay/piglin_bartering"),
                chestPool
        );
        pOutput.accept(
                new ResourceLocation(Skyrimcraft.MODID, "gameplay/sniffer_digging"),
                LootTable.lootTable().withPool(LootPool.lootPool()
                        .setRolls(UniformGenerator.between(1.0F, 3.0F))
                        .add(LootItem.lootTableItem(ItemInit.TAPROOT)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.5F, 1.0F)))
                        )
                        .add(LootItem.lootTableItem(ItemInit.FIRE_SALTS)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.5F, 1.0F)))
                        )
                        .add(LootItem.lootTableItem(ItemInit.SALT_PILE)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.5F, 1.0F)))
                        )
                        .add(LootItem.lootTableItem(ItemInit.DWARVEN_OIL)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.5F, 1.0F)))
                        )
                )
        );
    }
}
