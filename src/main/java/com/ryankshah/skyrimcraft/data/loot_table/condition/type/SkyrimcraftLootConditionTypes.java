package com.ryankshah.skyrimcraft.data.loot_table.condition.type;

import com.mojang.serialization.Codec;
import com.ryankshah.skyrimcraft.Skyrimcraft;
import com.ryankshah.skyrimcraft.data.loot_table.condition.MatchSkillLevel;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.SingleItemRecipe;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class SkyrimcraftLootConditionTypes
{
    public static final DeferredRegister<LootItemConditionType> LOOT_CONDITION_TYPE = DeferredRegister.create(BuiltInRegistries.LOOT_CONDITION_TYPE, Skyrimcraft.MODID);

    public static final Supplier<LootItemConditionType> MATCH_SKILL = LOOT_CONDITION_TYPE.register("match_skill", () -> new LootItemConditionType(MatchSkillLevel.CODEC));
}