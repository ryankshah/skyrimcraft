package com.ryankshah.skyrimcraft.item;

import com.ryankshah.skyrimcraft.init.ItemInit;
import com.ryankshah.skyrimcraft.init.TagsInit;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.SimpleTier;

public class ItemTier
{
    public static final Tier ANCIENT_NORD = new SimpleTier(
            TagsInit.BlockTagsInit.NEEDS_ANCIENT_NORD_TOOL,
            250,
            6.0f,
            2.0f,
            14,
            () -> Ingredient.EMPTY
    );

    public static final Tier DRAGONBONE = new SimpleTier(
            TagsInit.BlockTagsInit.NEEDS_DRAGONBONE_TOOL,
            250,
            6.0f,
            2.0f,
            14,
            () -> Ingredient.EMPTY
    );

    public static final Tier STEEL = new SimpleTier(
            TagsInit.BlockTagsInit.NEEDS_STEEL_TOOL,
            275,
            6.5f,
            2.25f,
            20,
            () -> Ingredient.of(ItemInit.STEEL_INGOT)
    );

    public static final Tier FALMER = new SimpleTier(
            TagsInit.BlockTagsInit.NEEDS_FALMER_TOOL,
            250,
            6.0f,
            2.0f,
            14,
            () -> Ingredient.EMPTY
    );

    public static final Tier GLASS = new SimpleTier(
            TagsInit.BlockTagsInit.NEEDS_GLASS_TOOL,
            32,
            12.0f,
            1.0f,
            22,
            () -> Ingredient.of(ItemInit.MALACHITE_INGOT)
    );

    public static final Tier ELVEN = new SimpleTier(
            TagsInit.BlockTagsInit.NEEDS_ELVEN_TOOL,
        32,
        12.0f,
        1.0f,
        22,
        () -> Ingredient.of(ItemInit.MOONSTONE_INGOT)
    );

    public static final Tier ORCISH = new SimpleTier(
            TagsInit.BlockTagsInit.NEEDS_ORCISH_TOOL,
            250,
            6.0f,
            2.0f,
            22,
            () -> Ingredient.of(ItemInit.ORICHALCUM_INGOT)
    );

    public static final Tier DWARVEN = new SimpleTier(
            TagsInit.BlockTagsInit.NEEDS_DWARVEN_TOOL,
            450,
            6.5f,
            2.5f,
            22,
            () -> Ingredient.of(ItemInit.DWARVEN_METAL_INGOT)
    );

    public static final Tier DAEDRIC = new SimpleTier(
            TagsInit.BlockTagsInit.NEEDS_DAEDRIC_TOOL,
            2031,
            9.0f,
            4.0f,
            22,
            () -> Ingredient.of(ItemInit.EBONY_INGOT)
    );

    public static final Tier EBONY = new SimpleTier(
            TagsInit.BlockTagsInit.NEEDS_EBONY_TOOL,
            1743,
            7.0f,
            2.5f,
            18,
            () -> Ingredient.of(ItemInit.EBONY_INGOT)
    );

//    static {
//        TierSortingRegistry.registerTier(
//                COPPER_TIER,
//                //The name to use for internal resolution. May use the Minecraft namespace if appropriate.
//                new ResourceLocation("minecraft", "copper"),
//                //A list of tiers that are considered lower than the type being added. For example, stone is lower than copper.
//                //We don't need to add wood and gold here because those are already lower than stone.
//                List.of(Tiers.STONE),
//                //A list of tiers that are considered higher than the type being added. For example, iron is higher than copper.
//                //We don't need to add diamond and netherite here because those are already higher than iron.
//                List.of(Tiers.IRON)
//        );
//    }

}