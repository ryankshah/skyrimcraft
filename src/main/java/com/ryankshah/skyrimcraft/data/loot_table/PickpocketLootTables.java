package com.ryankshah.skyrimcraft.data.loot_table;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.ryankshah.skyrimcraft.Skyrimcraft;
import com.ryankshah.skyrimcraft.character.skill.Skill;
import com.ryankshah.skyrimcraft.character.skill.SkillRegistry;
import com.ryankshah.skyrimcraft.data.loot_table.condition.MatchSkillLevel;
import com.ryankshah.skyrimcraft.data.loot_table.predicate.SkillPredicate;
import com.ryankshah.skyrimcraft.init.ItemInit;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.functions.LootingEnchantFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

public class PickpocketLootTables implements LootTableSubProvider
{
    private final Map<ResourceLocation, LootTable.Builder> map = Maps.newHashMap();

    protected static LootItemCondition.Builder getSkillLevelCondition(Skill skill, int level) {
        return MatchSkillLevel.skillMatches(SkillPredicate.Builder.skill().of(skill, level, 1F));
    }

    protected static LootItemCondition.Builder getSkillLevelConditionWithChance(Skill skill, int level, float successChance) {
        return MatchSkillLevel.skillMatches(SkillPredicate.Builder.skill().of(skill, level, successChance));
    }

    protected static LootTable.Builder createSingleItemTable(ItemLike itemProvider) {
        return LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1)).add(LootItem.lootTableItem(itemProvider)));
    }

    protected static LootTable.Builder createSingleItemTable(ItemLike itemProvider, LootItemCondition.Builder lootConditionBuilder, LootPoolEntryContainer.Builder<?> lootEntryBuilder) {
        return LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1)).add(LootItem.lootTableItem(itemProvider).when(lootConditionBuilder).otherwise(lootEntryBuilder)));
    }

    protected static LootTable.Builder createSingleItemTableWithRange(ItemLike itemProvider, UniformGenerator rolls) {
        return LootTable.lootTable().withPool(LootPool.lootPool().setRolls(rolls).add(LootItem.lootTableItem(itemProvider)));
    }

    protected static LootTable.Builder createSingleItemTableWithRange(ItemLike itemProvider, UniformGenerator rolls, LootItemCondition.Builder lootConditionBuilder) {
        return LootTable.lootTable().withPool(LootPool.lootPool().setRolls(rolls).add(LootItem.lootTableItem(itemProvider).when(lootConditionBuilder)));//.otherwise(ItemLootEntry.lootTableItem(itemProvider))));
    }

    protected static LootTable.Builder multiplePools(LootItemCondition.Builder lootConditionBuilder, LootPool.Builder... lootPools) {
        LootTable.Builder lootTable = LootTable.lootTable();

        for(LootPool.Builder pool : lootPools) {
            lootTable.withPool(pool.when(lootConditionBuilder));
        }

        return lootTable;
    }

    public static LootTable.Builder noLoot() {
        return LootTable.lootTable();
    }

    protected void add(BiConsumer<ResourceLocation, LootTable.Builder> pOutput, EntityType<?> entityType, LootTable.Builder builder) {
        pOutput.accept(
                new ResourceLocation(entityType.getDefaultLootTable().toString().replace("minecraft", Skyrimcraft.MODID).replace("entities", "pickpocket")),
                builder);
    }
    protected void add(BiConsumer<ResourceLocation, LootTable.Builder> pOutput, EntityType<?> entityType, Function<EntityType<?>, LootTable.Builder> builder) {
        this.add(pOutput, entityType, builder.apply(entityType));
    }

    public static Iterable<EntityType<?>> getPickpocketableEntities() {
        return ImmutableList.of(EntityType.VILLAGER);//, ModEntityType.MERCHANT.get());
    }

    public void addTables(BiConsumer<ResourceLocation, LootTable.Builder> pOutput) {
        LootPool.Builder pickpocketGemPool = LootPool.lootPool()
                .name("pickpocketGemPool")
                .setRolls(UniformGenerator.between(1.0F, 3.0F))
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
                );

        add(pOutput, EntityType.VILLAGER, createSingleItemTableWithRange(Items.EMERALD, UniformGenerator.between(1F, 3F), getSkillLevelConditionWithChance(SkillRegistry.PICKPOCKET.get(), 15, 0.4f)));
    }

    @Override
    public void generate(BiConsumer<ResourceLocation, LootTable.Builder> pOutput) {
        addTables(pOutput);
    }
}