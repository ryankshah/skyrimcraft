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

public class SkyrimcraftLootConditionTypes
{
    public static LootItemConditionType MATCH_SKILL;

    public static void register() {
        MATCH_SKILL = add("match_skill", MatchSkillLevel.CODEC);
    }

    public static LootItemConditionType add(String name, Codec<? extends LootItemCondition> lootSerializer) {
        return Registry.register(BuiltInRegistries.LOOT_CONDITION_TYPE, new ResourceLocation(Skyrimcraft.MODID, name), new LootItemConditionType(lootSerializer));
    }
}