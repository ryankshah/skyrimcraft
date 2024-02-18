package com.ryankshah.skyrimcraft.data.recipe;

import com.ryankshah.skyrimcraft.Skyrimcraft;
import com.ryankshah.skyrimcraft.init.BlockInit;
import com.ryankshah.skyrimcraft.init.ItemInit;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class SkyrimcraftRecipeProvider extends RecipeProvider implements IConditionBuilder
{
    private static final List<ItemLike> CORUNDUM_ORE_SMELTABLE = List.of(BlockInit.CORUNDUM_ORE.get(), BlockInit.DEEPSLATE_CORUNDUM_ORE);
    private static final List<ItemLike> EBONY_ORE_SMELTABLE = List.of(BlockInit.EBONY_ORE.get(), BlockInit.DEEPSLATE_EBONY_ORE);
    private static final List<ItemLike> MALACHITE_ORE_SMELTABLE = List.of(BlockInit.MALACHITE_ORE.get(), BlockInit.DEEPSLATE_MALACHITE_ORE);
    private static final List<ItemLike> MOONSTONE_ORE_SMELTABLE = List.of(BlockInit.MOONSTONE_ORE.get(), BlockInit.DEEPSLATE_MOONSTONE_ORE);
    private static final List<ItemLike> ORICHALCUM_ORE_SMELTABLE = List.of(BlockInit.ORICHALCUM_ORE.get(), BlockInit.DEEPSLATE_ORICHALCUM_ORE);
    private static final List<ItemLike> QUICKSILVER_ORE_SMELTABLE = List.of(BlockInit.QUICKSILVER_ORE.get(), BlockInit.DEEPSLATE_QUICKSILVER_ORE);
    private static final List<ItemLike> SILVER_ORE_SMELTABLE = List.of(BlockInit.SILVER_ORE.get(), BlockInit.DEEPSLATE_SILVER_ORE);


    public SkyrimcraftRecipeProvider(PackOutput p_248933_, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(p_248933_, lookupProvider);
    }

    @Override
    protected void buildRecipes(RecipeOutput pWriter) {
        oreSmelting(pWriter, CORUNDUM_ORE_SMELTABLE, RecipeCategory.MISC, ItemInit.CORUNDUM_INGOT.get(), 0.25f, 200, "corundum_ingot");
        oreBlasting(pWriter, CORUNDUM_ORE_SMELTABLE, RecipeCategory.MISC, ItemInit.CORUNDUM_INGOT.get(), 0.25f, 100, "corundum_ingot");

        oreSmelting(pWriter, EBONY_ORE_SMELTABLE, RecipeCategory.MISC, ItemInit.EBONY_INGOT.get(), 0.25f, 200, "ebony_ingot");
        oreBlasting(pWriter, EBONY_ORE_SMELTABLE, RecipeCategory.MISC, ItemInit.EBONY_INGOT.get(), 0.25f, 100, "ebony_ingot");

        oreSmelting(pWriter, MALACHITE_ORE_SMELTABLE, RecipeCategory.MISC, ItemInit.MALACHITE_INGOT.get(), 0.25f, 200, "malachite_ingot");
        oreBlasting(pWriter, MALACHITE_ORE_SMELTABLE, RecipeCategory.MISC, ItemInit.MALACHITE_INGOT.get(), 0.25f, 100, "malachite_ingot");

        oreSmelting(pWriter, MOONSTONE_ORE_SMELTABLE, RecipeCategory.MISC, ItemInit.MOONSTONE_INGOT.get(), 0.25f, 200, "moonstone_ingot");
        oreBlasting(pWriter, MOONSTONE_ORE_SMELTABLE, RecipeCategory.MISC, ItemInit.MOONSTONE_INGOT.get(), 0.25f, 100, "moonstone_ingot");

        oreSmelting(pWriter, ORICHALCUM_ORE_SMELTABLE, RecipeCategory.MISC, ItemInit.ORICHALCUM_INGOT.get(), 0.25f, 200, "orichalcum_ingot");
        oreBlasting(pWriter, ORICHALCUM_ORE_SMELTABLE, RecipeCategory.MISC, ItemInit.ORICHALCUM_INGOT.get(), 0.25f, 100, "orichalcum_ingot");

        oreSmelting(pWriter, QUICKSILVER_ORE_SMELTABLE, RecipeCategory.MISC, ItemInit.QUICKSILVER_INGOT.get(), 0.25f, 200, "quicksilver_ingot");
        oreBlasting(pWriter, QUICKSILVER_ORE_SMELTABLE, RecipeCategory.MISC, ItemInit.QUICKSILVER_INGOT.get(), 0.25f, 100, "quicksilver_ingot");

        oreSmelting(pWriter, SILVER_ORE_SMELTABLE, RecipeCategory.MISC, ItemInit.SILVER_INGOT.get(), 0.25f, 200, "silver_ingot");
        oreBlasting(pWriter, SILVER_ORE_SMELTABLE, RecipeCategory.MISC, ItemInit.SILVER_INGOT.get(), 0.25f, 100, "silver_ingot");

//        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.SAPPHIRE_BLOCK.get())
//                .pattern("SSS")
//                .pattern("SSS")
//                .pattern("SSS")
//                .define('S', ModItems.SAPPHIRE.get())
//                .unlockedBy(getHasName(ModItems.SAPPHIRE.get()), has(ModItems.SAPPHIRE.get()))
//                .save(pWriter);
//
//        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.SAPPHIRE.get(), 9)
//                .requires(ModBlocks.SAPPHIRE_BLOCK.get())
//                .unlockedBy(getHasName(ModBlocks.SAPPHIRE_BLOCK.get()), has(ModBlocks.SAPPHIRE_BLOCK.get()))
//                .save(pWriter);
    }

    protected static void oreSmelting(
            RecipeOutput pRecipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup
    ) {
        oreCooking(
                pRecipeOutput,
                RecipeSerializer.SMELTING_RECIPE,
                SmeltingRecipe::new,
                pIngredients,
                pCategory,
                pResult,
                pExperience,
                pCookingTime,
                pGroup,
                "_from_smelting"
        );
    }

    protected static void oreBlasting(
            RecipeOutput pRecipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup
    ) {
        oreCooking(
                pRecipeOutput,
                RecipeSerializer.BLASTING_RECIPE,
                BlastingRecipe::new,
                pIngredients,
                pCategory,
                pResult,
                pExperience,
                pCookingTime,
                pGroup,
                "_from_blasting"
        );
    }

    protected static <T extends AbstractCookingRecipe> void oreCooking(
            RecipeOutput pRecipeOutput,
            RecipeSerializer<T> pSerializer,
            AbstractCookingRecipe.Factory<T> pRecipeFactory,
            List<ItemLike> pIngredients,
            RecipeCategory pCategory,
            ItemLike pResult,
            float pExperience,
            int pCookingTime,
            String pGroup,
            String pSuffix
    ) {
        for(ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult, pExperience, pCookingTime, pSerializer, pRecipeFactory)
                    .group(pGroup)
                    .unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(pRecipeOutput, Skyrimcraft.MODID + ":" + getItemName(pResult) + pSuffix + "_" + getItemName(itemlike));
        }
    }
}