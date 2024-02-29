package com.ryankshah.skyrimcraft.data.recipe.provider;

import com.google.common.collect.Sets;
import com.ryankshah.skyrimcraft.Skyrimcraft;
import com.ryankshah.skyrimcraft.data.recipe.OvenRecipe;
import com.ryankshah.skyrimcraft.init.ItemInit;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.neoforged.neoforge.common.conditions.ICondition;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;
import net.neoforged.neoforge.common.conditions.WithConditions;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class OvenRecipeProvider implements DataProvider, IConditionBuilder
{
    protected final PackOutput.PathProvider recipePathProvider;
    protected final CompletableFuture<HolderLookup.Provider> lookupProvider;

    public OvenRecipeProvider(PackOutput p_248933_, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        this.recipePathProvider = p_248933_.createPathProvider(PackOutput.Target.DATA_PACK, "recipes");
        this.lookupProvider = lookupProvider;
    }

    protected void buildRecipes(RecipeOutput pRecipeOutput) {
        pRecipeOutput.accept(
                new ResourceLocation(Skyrimcraft.MODID + ":oven/" + BuiltInRegistries.ITEM.getKey(ItemInit.SWEET_ROLL.get()).getPath()),
                new OvenRecipe("food", new ItemStack(ItemInit.SWEET_ROLL.get(), 1), 1, 5,
                        NonNullList.of(
                                Ingredient.of(new ItemStack(ItemInit.BUTTER.get(), 1)),
                                Ingredient.of(new ItemStack(Items.EGG, 1)),
                                Ingredient.of(new ItemStack(ItemInit.SALT_PILE.get(), 1)),
                                Ingredient.of(new ItemStack(ItemInit.FLOUR.get(), 1)),
                                Ingredient.of(new ItemStack(Items.MILK_BUCKET, 1))
                        )),
                null
        );
        pRecipeOutput.accept(
                new ResourceLocation(Skyrimcraft.MODID + ":oven/" + BuiltInRegistries.ITEM.getKey(ItemInit.GARLIC_BREAD.get()).getPath()),
                new OvenRecipe("food", new ItemStack(ItemInit.GARLIC_BREAD.get(), 1), 1, 5,
                        NonNullList.of(
                                Ingredient.of(new ItemStack(ItemInit.BUTTER.get(), 1)),
                                Ingredient.of(new ItemStack(ItemInit.GARLIC.get(), 1)),
                                Ingredient.of(new ItemStack(ItemInit.FLOUR.get(), 1))
                        )),
                null
        );
        pRecipeOutput.accept(
                new ResourceLocation(Skyrimcraft.MODID + ":oven/" + BuiltInRegistries.ITEM.getKey(ItemInit.APPLE_PIE.get()).getPath()),
                new OvenRecipe("food", new ItemStack(ItemInit.APPLE_PIE.get(), 1), 1, 5,
                        NonNullList.of(
                                Ingredient.of(new ItemStack(ItemInit.FLOUR.get(), 1)),
                                Ingredient.of(new ItemStack(ItemInit.SALT_PILE.get(), 1)),
                                Ingredient.of(new ItemStack(ItemInit.BUTTER.get(), 1)),
                                Ingredient.of(new ItemStack(Items.EGG, 1)),
                                Ingredient.of(new ItemStack(Items.APPLE, 1))
                        )),
                null
        );
        pRecipeOutput.accept(
                new ResourceLocation(Skyrimcraft.MODID + ":oven/" + BuiltInRegistries.ITEM.getKey(ItemInit.POTATO_BREAD.get()).getPath()),
                new OvenRecipe("food", new ItemStack(ItemInit.POTATO_BREAD.get(), 1), 1, 5,
                        NonNullList.of(
                                Ingredient.of(new ItemStack(ItemInit.FLOUR.get(), 1)),
                                Ingredient.of(new ItemStack(ItemInit.SALT_PILE.get(), 1)),
                                Ingredient.of(new ItemStack(Items.MILK_BUCKET, 1)),
                                Ingredient.of(new ItemStack(Items.EGG, 1)),
                                Ingredient.of(new ItemStack(Items.POTATO, 1))
                        )),
                null
        );
        pRecipeOutput.accept(
                new ResourceLocation(Skyrimcraft.MODID + ":oven/" + BuiltInRegistries.ITEM.getKey(ItemInit.HORSE_HAUNCH.get()).getPath()),
                new OvenRecipe("food", new ItemStack(ItemInit.HORSE_HAUNCH.get(), 1), 1, 5,
                        NonNullList.of(
                                Ingredient.of(new ItemStack(ItemInit.HORSE_MEAT.get(), 1)),
                                Ingredient.of(new ItemStack(ItemInit.SALT_PILE.get()))
                        )),
                null
        );
        pRecipeOutput.accept(
                new ResourceLocation(Skyrimcraft.MODID + ":oven/" + BuiltInRegistries.ITEM.getKey(ItemInit.LEG_OF_GOAT_ROAST.get()).getPath()),
                new OvenRecipe("food", new ItemStack(ItemInit.LEG_OF_GOAT_ROAST.get(), 1), 1, 5,
                        NonNullList.of(
                                Ingredient.of(new ItemStack(ItemInit.LEG_OF_GOAT.get(), 1)),
                                Ingredient.of(new ItemStack(ItemInit.BUTTER.get(), 1))
                        )),
                null
        );
    }

    @Override
    public CompletableFuture<?> run(CachedOutput pOutput) {
        return this.lookupProvider.thenCompose((provider) -> {
            final Set<ResourceLocation> set = Sets.newHashSet();
            final List<CompletableFuture<?>> list = new ArrayList<>();
            this.buildRecipes(new RecipeOutput() {
                @Override
                public Advancement.Builder advancement() {
                    return null;
                }

                public void accept(ResourceLocation p_312039_, Recipe<?> p_312254_, @Nullable AdvancementHolder p_311794_, ICondition... conditions) {
                    if (!set.add(p_312039_)) {
                        throw new IllegalStateException("Duplicate recipe " + p_312039_);
                    } else {
                        list.add(DataProvider.saveStable(pOutput, Recipe.CONDITIONAL_CODEC, Optional.of(new WithConditions(p_312254_, conditions)), OvenRecipeProvider.this.recipePathProvider.json(p_312039_)));
                    }
                }
            });
            return CompletableFuture.allOf(list.toArray(CompletableFuture[]::new));
        });
    }

    @Override
    public String getName() {
        return Skyrimcraft.MODID + " Oven Recipes";
    }
}
