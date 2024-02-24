package com.ryankshah.skyrimcraft.data.recipe.provider;

import com.google.common.collect.Sets;
import com.ryankshah.skyrimcraft.Skyrimcraft;
import com.ryankshah.skyrimcraft.data.recipe.ForgeRecipe;
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

import java.util.*;
import java.util.concurrent.CompletableFuture;

/**
 * TODO: Fix the XP rates for forging!
 */
public class ForgeRecipeProvider implements DataProvider, IConditionBuilder
{
    protected final PackOutput.PathProvider recipePathProvider;
    protected final CompletableFuture<HolderLookup.Provider> lookupProvider;

    public static Map<String, ForgeRecipe> RECIPES;

    public ForgeRecipeProvider(PackOutput p_248933_, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        this.recipePathProvider = p_248933_.createPathProvider(PackOutput.Target.DATA_PACK, "recipes");
        this.lookupProvider = lookupProvider;
        RECIPES = new HashMap<>();
    }

    protected void buildRecipes(RecipeOutput pRecipeOutput) {
        daedricRecipes(pRecipeOutput);
        dwarvenRecipes(pRecipeOutput);
        ebonyRecipes(pRecipeOutput);
        elvenRecipes(pRecipeOutput);
        glassRecipes(pRecipeOutput);
        ironRecipes(pRecipeOutput);
        orcishRecipes(pRecipeOutput);
        steelRecipes(pRecipeOutput);
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
                        list.add(DataProvider.saveStable(pOutput, Recipe.CONDITIONAL_CODEC, Optional.of(new WithConditions(p_312254_, conditions)), ForgeRecipeProvider.this.recipePathProvider.json(p_312039_)));
                    }
                }
            });
            return CompletableFuture.allOf(list.toArray(CompletableFuture[]::new));
        });
    }

    @Override
    public String getName() {
        return Skyrimcraft.MODID + " Forge Recipes";
    }

    private static void daedricRecipes(RecipeOutput consumer) {
//        RECIPES.put("daedric", ForgeRecipe.Builder.recipe().output(new ItemStack(ItemInit.DAEDRIC_ARROW.get(), 24)).level(90).xp(5)
//                .category("daedric")
//                .addRecipeItem(new ItemStack(Items.STICK, 1))
//                .addRecipeItem(new ItemStack(ItemInit.EBONY_INGOT.get(), 1))
//                .addRecipeItem(new ItemStack(ItemInit.DAEDRA_HEART.get(), 1))
//                .save(consumer, Skyrimcraft.MODID + ":recipes/forge/" + ItemInit.DAEDRIC_ARROW.getId().getPath()));
        consumer.accept(
                new ResourceLocation(Skyrimcraft.MODID + ":forge/" + BuiltInRegistries.ITEM.getKey(ItemInit.DAEDRIC_DAGGER.get()).getPath()),
                new ForgeRecipe("daedric", new ItemStack(ItemInit.DAEDRIC_DAGGER.get(), 1), 90, 5,
                        NonNullList.of(
                                Ingredient.of(new ItemStack(ItemInit.LEATHER_STRIPS.get(), 1)),
                                Ingredient.of(new ItemStack(ItemInit.EBONY_INGOT.get(), 1)),
                                Ingredient.of(new ItemStack(ItemInit.DAEDRA_HEART.get(), 1))
                        )),
                null
        );
        consumer.accept(
                new ResourceLocation(Skyrimcraft.MODID + ":forge/" + BuiltInRegistries.ITEM.getKey(ItemInit.DAEDRIC_SWORD.get()).getPath()),
                new ForgeRecipe("daedric", new ItemStack(ItemInit.DAEDRIC_SWORD.get(), 1), 90, 13,
                        NonNullList.of(
                                Ingredient.of(new ItemStack(ItemInit.LEATHER_STRIPS.get(), 1)),
                                Ingredient.of(new ItemStack(ItemInit.EBONY_INGOT.get(), 2)),
                                Ingredient.of(new ItemStack(ItemInit.DAEDRA_HEART.get(), 1))
                        )),
                null
        );
        consumer.accept(
                new ResourceLocation(Skyrimcraft.MODID + ":forge/" + BuiltInRegistries.ITEM.getKey(ItemInit.DAEDRIC_BATTLEAXE.get()).getPath()),
                new ForgeRecipe("daedric", new ItemStack(ItemInit.DAEDRIC_BATTLEAXE.get(), 1), 90, 27,
                        NonNullList.of(
                                Ingredient.of(new ItemStack(ItemInit.LEATHER_STRIPS.get(), 1)),
                                Ingredient.of(new ItemStack(ItemInit.EBONY_INGOT.get(), 5)),
                                Ingredient.of(new ItemStack(ItemInit.DAEDRA_HEART.get(), 1))
                        )),
                null
        );
        consumer.accept(
                new ResourceLocation(Skyrimcraft.MODID + ":forge/" + BuiltInRegistries.ITEM.getKey(ItemInit.DAEDRIC_BOW.get()).getPath()),
                new ForgeRecipe("daedric", new ItemStack(ItemInit.DAEDRIC_BOW.get(), 1), 90, 25,
                        NonNullList.of(
                                Ingredient.of(new ItemStack(ItemInit.LEATHER_STRIPS.get(), 1)),
                                Ingredient.of(new ItemStack(ItemInit.EBONY_INGOT.get(), 3)),
                                Ingredient.of(new ItemStack(ItemInit.DAEDRA_HEART.get(), 1))
                        )),
                null
        );
        consumer.accept(
                new ResourceLocation(Skyrimcraft.MODID + ":forge/" + BuiltInRegistries.ITEM.getKey(ItemInit.DAEDRIC_GREATSWORD.get()).getPath()),
                new ForgeRecipe("daedric", new ItemStack(ItemInit.DAEDRIC_GREATSWORD.get(), 1), 90, 25,
                        NonNullList.of(
                                Ingredient.of(new ItemStack(ItemInit.LEATHER_STRIPS.get(), 1)),
                                Ingredient.of(new ItemStack(ItemInit.EBONY_INGOT.get(), 5)),
                                Ingredient.of(new ItemStack(ItemInit.DAEDRA_HEART.get(), 1))
                        )),
                null
        );
        consumer.accept(
                new ResourceLocation(Skyrimcraft.MODID + ":forge/" + BuiltInRegistries.ITEM.getKey(ItemInit.DAEDRIC_MACE.get()).getPath()),
                new ForgeRecipe("daedric", new ItemStack(ItemInit.DAEDRIC_MACE.get(), 1), 90, 18,
                        NonNullList.of(
                                Ingredient.of(new ItemStack(ItemInit.LEATHER_STRIPS.get(), 1)),
                                Ingredient.of(new ItemStack(ItemInit.EBONY_INGOT.get(), 3)),
                                Ingredient.of(new ItemStack(ItemInit.DAEDRA_HEART.get(), 1))
                        )),
                null
        );
        consumer.accept(
                new ResourceLocation(Skyrimcraft.MODID + ":forge/" + BuiltInRegistries.ITEM.getKey(ItemInit.DAEDRIC_WAR_AXE.get()).getPath()),
                new ForgeRecipe("daedric", new ItemStack(ItemInit.DAEDRIC_WAR_AXE.get(), 1), 90, 15,
                        NonNullList.of(
                                Ingredient.of(new ItemStack(ItemInit.LEATHER_STRIPS.get(), 1)),
                                Ingredient.of(new ItemStack(ItemInit.EBONY_INGOT.get(), 2)),
                                Ingredient.of(new ItemStack(ItemInit.DAEDRA_HEART.get(), 1))
                        )),
                null
        );
        consumer.accept(
                new ResourceLocation(Skyrimcraft.MODID + ":forge/" + BuiltInRegistries.ITEM.getKey(ItemInit.DAEDRIC_WARHAMMER.get()).getPath()),
                new ForgeRecipe("daedric", new ItemStack(ItemInit.DAEDRIC_WARHAMMER.get(), 1), 90, 40,
                        NonNullList.of(
                                Ingredient.of(new ItemStack(ItemInit.LEATHER_STRIPS.get(), 3)),
                                Ingredient.of(new ItemStack(ItemInit.EBONY_INGOT.get(), 5)),
                                Ingredient.of(new ItemStack(ItemInit.DAEDRA_HEART.get(), 1))
                        )),
                null
        );
        consumer.accept(
                new ResourceLocation(Skyrimcraft.MODID + ":forge/" + BuiltInRegistries.ITEM.getKey(ItemInit.DAEDRIC_HELMET.get()).getPath()),
                new ForgeRecipe("daedric", new ItemStack(ItemInit.DAEDRIC_HELMET.get(), 1), 90, 16,
                        NonNullList.of(
                                Ingredient.of(new ItemStack(ItemInit.LEATHER_STRIPS.get(), 2)),
                                Ingredient.of(new ItemStack(ItemInit.EBONY_INGOT.get(), 3)),
                                Ingredient.of(new ItemStack(ItemInit.DAEDRA_HEART.get(), 1))
                        )),
                null
        );
        consumer.accept(
                new ResourceLocation(Skyrimcraft.MODID + ":forge/" + BuiltInRegistries.ITEM.getKey(ItemInit.DAEDRIC_CHESTPLATE.get()).getPath()),
                new ForgeRecipe("daedric", new ItemStack(ItemInit.DAEDRIC_CHESTPLATE.get(), 1), 90, 32,
                        NonNullList.of(
                                Ingredient.of(new ItemStack(ItemInit.LEATHER_STRIPS.get(), 3)),
                                Ingredient.of(new ItemStack(ItemInit.EBONY_INGOT.get(), 5)),
                                Ingredient.of(new ItemStack(ItemInit.DAEDRA_HEART.get(), 1))
                        )),
                null
        );
        consumer.accept(
                new ResourceLocation(Skyrimcraft.MODID + ":forge/" + BuiltInRegistries.ITEM.getKey(ItemInit.DAEDRIC_LEGGINGS.get()).getPath()),
                new ForgeRecipe("daedric", new ItemStack(ItemInit.DAEDRIC_LEGGINGS.get(), 1), 90, 32,
                        NonNullList.of(
                                Ingredient.of(new ItemStack(ItemInit.LEATHER_STRIPS.get(), 3)),
                                Ingredient.of(new ItemStack(ItemInit.EBONY_INGOT.get(), 5)),
                                Ingredient.of(new ItemStack(ItemInit.DAEDRA_HEART.get(), 1))
                        )),
                null
        );
        consumer.accept(
                new ResourceLocation(Skyrimcraft.MODID + ":forge/" + BuiltInRegistries.ITEM.getKey(ItemInit.DAEDRIC_BOOTS.get()).getPath()),
                new ForgeRecipe("daedric", new ItemStack(ItemInit.DAEDRIC_BOOTS.get(), 1), 90, 16,
                        NonNullList.of(
                                Ingredient.of(new ItemStack(ItemInit.LEATHER_STRIPS.get(), 2)),
                                Ingredient.of(new ItemStack(ItemInit.EBONY_INGOT.get(), 3)),
                                Ingredient.of(new ItemStack(ItemInit.DAEDRA_HEART.get(), 1))
                        )),
                null
        );
        consumer.accept(
                new ResourceLocation(Skyrimcraft.MODID + ":forge/" + BuiltInRegistries.ITEM.getKey(ItemInit.DAEDRIC_SHIELD.get()).getPath()),
                new ForgeRecipe("daedric", new ItemStack(ItemInit.DAEDRIC_SHIELD.get(), 1), 90, 16,
                        NonNullList.of(
                                Ingredient.of(new ItemStack(ItemInit.LEATHER_STRIPS.get(), 1)),
                                Ingredient.of(new ItemStack(ItemInit.EBONY_INGOT.get(), 4)),
                                Ingredient.of(new ItemStack(ItemInit.DAEDRA_HEART.get(), 1))
                        )),
                null
        );
    }

    private static void dwarvenRecipes(RecipeOutput consumer) {
//        RECIPES.put("dwarven", ForgeRecipe.Builder.recipe().output(new ItemStack(ItemInit.DWARVEN_ARROW.get(), 24)).level(30).xp(5)
//                .category("dwarven")
//                .addRecipeItem(new ItemStack(Items.STICK, 1))
//                .addRecipeItem(new ItemStack(ItemInit.DWARVEN_METAL_INGOT.get(), 1))
//                .save(consumer, Skyrimcraft.MODID + ":recipes/forge/" + ItemInit.DWARVEN_ARROW.getId().getPath()));
        consumer.accept(
                new ResourceLocation(Skyrimcraft.MODID + ":forge/" + BuiltInRegistries.ITEM.getKey(ItemInit.DWARVEN_DAGGER.get()).getPath()),
                new ForgeRecipe("dwarven", new ItemStack(ItemInit.DWARVEN_DAGGER.get(), 1), 30, 5,
                        NonNullList.of(
                                Ingredient.of(new ItemStack(ItemInit.DWARVEN_METAL_INGOT.get(), 1)),
                                Ingredient.of(new ItemStack(ItemInit.LEATHER_STRIPS.get(), 4)),
                                Ingredient.of(new ItemStack(Items.IRON_INGOT, 1)),
                                Ingredient.of(new ItemStack(ItemInit.STEEL_INGOT.get(), 1))
                        )),
                null
        );
        consumer.accept(
                new ResourceLocation(Skyrimcraft.MODID + ":forge/" + BuiltInRegistries.ITEM.getKey(ItemInit.DWARVEN_BATTLEAXE.get()).getPath()),
                new ForgeRecipe("dwarven", new ItemStack(ItemInit.DWARVEN_BATTLEAXE.get(), 1), 30, 5,
                        NonNullList.of(
                                Ingredient.of(new ItemStack(ItemInit.DWARVEN_METAL_INGOT.get(), 2)),
                                Ingredient.of(new ItemStack(ItemInit.LEATHER_STRIPS.get(), 2)),
                                Ingredient.of(new ItemStack(Items.IRON_INGOT, 1)),
                                Ingredient.of(new ItemStack(ItemInit.STEEL_INGOT.get(), 2))
                        )),
                null
        );
        consumer.accept(
                new ResourceLocation(Skyrimcraft.MODID + ":forge/" + BuiltInRegistries.ITEM.getKey(ItemInit.DWARVEN_BOW.get()).getPath()),
                new ForgeRecipe("dwarven", new ItemStack(ItemInit.DWARVEN_BOW.get(), 1), 30, 5,
                        NonNullList.of(
                                Ingredient.of(new ItemStack(ItemInit.DWARVEN_METAL_INGOT.get(), 2)),
                                Ingredient.of(new ItemStack(Items.IRON_INGOT, 1))
                        )),
                null
        );
        consumer.accept(
                new ResourceLocation(Skyrimcraft.MODID + ":forge/" + BuiltInRegistries.ITEM.getKey(ItemInit.DWARVEN_GREATSWORD.get()).getPath()),
                new ForgeRecipe("dwarven", new ItemStack(ItemInit.DWARVEN_GREATSWORD.get(), 1), 30, 5,
                        NonNullList.of(
                                Ingredient.of(new ItemStack(ItemInit.DWARVEN_METAL_INGOT.get(), 2)),
                                Ingredient.of(new ItemStack(ItemInit.LEATHER_STRIPS.get(), 3)),
                                Ingredient.of(new ItemStack(Items.IRON_INGOT, 2)),
                                Ingredient.of(new ItemStack(ItemInit.STEEL_INGOT.get(), 2))
                        )),
                null
        );
        consumer.accept(
                new ResourceLocation(Skyrimcraft.MODID + ":forge/" + BuiltInRegistries.ITEM.getKey(ItemInit.DWARVEN_MACE.get()).getPath()),
                new ForgeRecipe("dwarven", new ItemStack(ItemInit.DWARVEN_MACE.get(), 1), 30, 5,
                        NonNullList.of(
                                Ingredient.of(new ItemStack(ItemInit.DWARVEN_METAL_INGOT.get(), 2)),
                                Ingredient.of(new ItemStack(ItemInit.LEATHER_STRIPS.get(), 1)),
                                Ingredient.of(new ItemStack(Items.IRON_INGOT, 1)),
                                Ingredient.of(new ItemStack(ItemInit.STEEL_INGOT.get(), 1))
                        )),
                null
        );
        consumer.accept(
                new ResourceLocation(Skyrimcraft.MODID + ":forge/" + BuiltInRegistries.ITEM.getKey(ItemInit.DWARVEN_SWORD.get()).getPath()),
                new ForgeRecipe("dwarven", new ItemStack(ItemInit.DWARVEN_SWORD.get(), 1), 30, 5,
                        NonNullList.of(
                                Ingredient.of(new ItemStack(ItemInit.DWARVEN_METAL_INGOT.get(), 1)),
                                Ingredient.of(new ItemStack(ItemInit.LEATHER_STRIPS.get(), 1)),
                                Ingredient.of(new ItemStack(Items.IRON_INGOT, 1)),
                                Ingredient.of(new ItemStack(ItemInit.STEEL_INGOT.get(), 1))
                        )),
                null
        );
        consumer.accept(
                new ResourceLocation(Skyrimcraft.MODID + ":forge/" + BuiltInRegistries.ITEM.getKey(ItemInit.DWARVEN_WAR_AXE.get()).getPath()),
                new ForgeRecipe("dwarven", new ItemStack(ItemInit.DWARVEN_WAR_AXE.get(), 1), 30, 5,
                        NonNullList.of(
                                Ingredient.of(new ItemStack(ItemInit.DWARVEN_METAL_INGOT.get(), 1)),
                                Ingredient.of(new ItemStack(ItemInit.LEATHER_STRIPS.get(), 2)),
                                Ingredient.of(new ItemStack(Items.IRON_INGOT, 1)),
                                Ingredient.of(new ItemStack(ItemInit.STEEL_INGOT.get(), 1))
                        )),
                null
        );
        consumer.accept(
                new ResourceLocation(Skyrimcraft.MODID + ":forge/" + BuiltInRegistries.ITEM.getKey(ItemInit.DWARVEN_WARHAMMER.get()).getPath()),
                new ForgeRecipe("dwarven", new ItemStack(ItemInit.DWARVEN_WARHAMMER.get(), 1), 30, 5,
                        NonNullList.of(
                                Ingredient.of(new ItemStack(ItemInit.DWARVEN_METAL_INGOT.get(), 2)),
                                Ingredient.of(new ItemStack(ItemInit.LEATHER_STRIPS.get(), 3)),
                                Ingredient.of(new ItemStack(Items.IRON_INGOT, 1)),
                                Ingredient.of(new ItemStack(ItemInit.STEEL_INGOT.get(), 2))
                        )),
                null
        );
        consumer.accept(
                new ResourceLocation(Skyrimcraft.MODID + ":forge/" + BuiltInRegistries.ITEM.getKey(ItemInit.DWARVEN_HELMET.get()).getPath()),
                new ForgeRecipe("dwarven", new ItemStack(ItemInit.DWARVEN_HELMET.get(), 1), 30, 5,
                        NonNullList.of(
                                Ingredient.of(new ItemStack(ItemInit.DWARVEN_METAL_INGOT.get(), 2)),
                                Ingredient.of(new ItemStack(ItemInit.LEATHER_STRIPS.get(), 2)),
                                Ingredient.of(new ItemStack(Items.IRON_INGOT, 1)),
                                Ingredient.of(new ItemStack(ItemInit.STEEL_INGOT.get(), 1))
                        )),
                null
        );
        consumer.accept(
                new ResourceLocation(Skyrimcraft.MODID + ":forge/" + BuiltInRegistries.ITEM.getKey(ItemInit.DWARVEN_CHESTPLATE.get()).getPath()),
                new ForgeRecipe("dwarven", new ItemStack(ItemInit.DWARVEN_CHESTPLATE.get(), 1), 30, 5,
                        NonNullList.of(
                                Ingredient.of(new ItemStack(ItemInit.DWARVEN_METAL_INGOT.get(), 3)),
                                Ingredient.of(new ItemStack(ItemInit.LEATHER_STRIPS.get(), 3)),
                                Ingredient.of(new ItemStack(Items.IRON_INGOT, 1)),
                                Ingredient.of(new ItemStack(ItemInit.STEEL_INGOT.get(), 1))
                        )),
                null
        );
        consumer.accept(
                new ResourceLocation(Skyrimcraft.MODID + ":forge/" + BuiltInRegistries.ITEM.getKey(ItemInit.DWARVEN_LEGGINGS.get()).getPath()),
                new ForgeRecipe("dwarven", new ItemStack(ItemInit.DWARVEN_LEGGINGS.get(), 1), 30, 5,
                        NonNullList.of(
                                Ingredient.of(new ItemStack(ItemInit.DWARVEN_METAL_INGOT.get(), 3)),
                                Ingredient.of(new ItemStack(ItemInit.LEATHER_STRIPS.get(), 3)),
                                Ingredient.of(new ItemStack(Items.IRON_INGOT, 1)),
                                Ingredient.of(new ItemStack(ItemInit.STEEL_INGOT.get(), 1))
                        )),
                null
        );
        consumer.accept(
                new ResourceLocation(Skyrimcraft.MODID + ":forge/" + BuiltInRegistries.ITEM.getKey(ItemInit.DWARVEN_BOOTS.get()).getPath()),
                new ForgeRecipe("dwarven", new ItemStack(ItemInit.DWARVEN_BOOTS.get(), 1), 30, 5,
                        NonNullList.of(
                                Ingredient.of(new ItemStack(ItemInit.DWARVEN_METAL_INGOT.get(), 2)),
                                Ingredient.of(new ItemStack(ItemInit.LEATHER_STRIPS.get(), 2)),
                                Ingredient.of(new ItemStack(Items.IRON_INGOT, 1)),
                                Ingredient.of(new ItemStack(ItemInit.STEEL_INGOT.get(), 1))
                        )),
                null
        );
        consumer.accept(
                new ResourceLocation(Skyrimcraft.MODID + ":forge/" + BuiltInRegistries.ITEM.getKey(ItemInit.DWARVEN_SHIELD.get()).getPath()),
                new ForgeRecipe("dwarven", new ItemStack(ItemInit.DWARVEN_SHIELD.get(), 1), 30, 5,
                        NonNullList.of(
                                Ingredient.of(new ItemStack(ItemInit.DWARVEN_METAL_INGOT.get(), 2)),
                                Ingredient.of(new ItemStack(ItemInit.LEATHER_STRIPS.get(), 1)),
                                Ingredient.of(new ItemStack(Items.IRON_INGOT, 1)),
                                Ingredient.of(new ItemStack(ItemInit.STEEL_INGOT.get(), 1))
                        )),
                null
        );
    }

    private static void ebonyRecipes(RecipeOutput consumer) {
//        RECIPES.put("ebony", ForgeRecipe.Builder.recipe().output(new ItemStack(ItemInit.EBONY_ARROW.get(), 24)).level(80).xp(5)
//                .category("ebony")
//                .addRecipeItem(new ItemStack(Items.STICK, 1))
//                .addRecipeItem(new ItemStack(ItemInit.EBONY_INGOT.get(), 1))
//                .save(consumer, Skyrimcraft.MODID + ":recipes/forge/" + ItemInit.EBONY_ARROW.getId().getPath()));
        consumer.accept(
                new ResourceLocation(Skyrimcraft.MODID + ":forge/" + BuiltInRegistries.ITEM.getKey(ItemInit.EBONY_DAGGER.get()).getPath()),
                new ForgeRecipe("ebony", new ItemStack(ItemInit.EBONY_DAGGER.get(), 1), 80, 5,
                        NonNullList.of(
                                Ingredient.of(new ItemStack(ItemInit.LEATHER_STRIPS.get(), 1)),
                                Ingredient.of(new ItemStack(ItemInit.EBONY_INGOT.get(), 1))
                        )),
                null
        );
        consumer.accept(
                new ResourceLocation(Skyrimcraft.MODID + ":forge/" + BuiltInRegistries.ITEM.getKey(ItemInit.EBONY_SWORD.get()).getPath()),
                new ForgeRecipe("ebony", new ItemStack(ItemInit.EBONY_SWORD.get(), 1), 80, 5,
                        NonNullList.of(
                                Ingredient.of(new ItemStack(ItemInit.LEATHER_STRIPS.get(), 1)),
                                Ingredient.of(new ItemStack(ItemInit.EBONY_INGOT.get(), 2))
                        )),
                null
        );
        consumer.accept(
                new ResourceLocation(Skyrimcraft.MODID + ":forge/" + BuiltInRegistries.ITEM.getKey(ItemInit.EBONY_GREATSWORD.get()).getPath()),
                new ForgeRecipe("ebony", new ItemStack(ItemInit.EBONY_GREATSWORD.get(), 1), 80, 5,
                        NonNullList.of(
                                Ingredient.of(new ItemStack(ItemInit.LEATHER_STRIPS.get(), 3)),
                                Ingredient.of(new ItemStack(ItemInit.EBONY_INGOT.get(), 5))
                        )),
                null
        );
        consumer.accept(
                new ResourceLocation(Skyrimcraft.MODID + ":forge/" + BuiltInRegistries.ITEM.getKey(ItemInit.EBONY_WAR_AXE.get()).getPath()),
                new ForgeRecipe("ebony", new ItemStack(ItemInit.EBONY_WAR_AXE.get(), 1), 80, 5,
                        NonNullList.of(
                                Ingredient.of(new ItemStack(ItemInit.LEATHER_STRIPS.get(), 2)),
                                Ingredient.of(new ItemStack(ItemInit.EBONY_INGOT.get(), 2))
                        )),
                null
        );
        consumer.accept(
                new ResourceLocation(Skyrimcraft.MODID + ":forge/" + BuiltInRegistries.ITEM.getKey(ItemInit.EBONY_BATTLEAXE.get()).getPath()),
                new ForgeRecipe("ebony", new ItemStack(ItemInit.EBONY_BATTLEAXE.get(), 1), 80, 5,
                        NonNullList.of(
                                Ingredient.of(new ItemStack(ItemInit.LEATHER_STRIPS.get(), 2)),
                                Ingredient.of(new ItemStack(ItemInit.EBONY_INGOT.get(), 5))
                        )),
                null
        );
        consumer.accept(
                new ResourceLocation(Skyrimcraft.MODID + ":forge/" + BuiltInRegistries.ITEM.getKey(ItemInit.EBONY_MACE.get()).getPath()),
                new ForgeRecipe("ebony", new ItemStack(ItemInit.EBONY_MACE.get(), 1), 80, 5,
                        NonNullList.of(
                                Ingredient.of(new ItemStack(ItemInit.LEATHER_STRIPS.get(), 1)),
                                Ingredient.of(new ItemStack(ItemInit.EBONY_INGOT.get(), 3))
                        )),
                null
        );
        consumer.accept(
                new ResourceLocation(Skyrimcraft.MODID + ":forge/" + BuiltInRegistries.ITEM.getKey(ItemInit.EBONY_WARHAMMER.get()).getPath()),
                new ForgeRecipe("ebony", new ItemStack(ItemInit.EBONY_WARHAMMER.get(), 1), 80, 5,
                        NonNullList.of(
                                Ingredient.of(new ItemStack(ItemInit.LEATHER_STRIPS.get(), 3)),
                                Ingredient.of(new ItemStack(ItemInit.EBONY_INGOT.get(), 5))
                        )),
                null
        );
        consumer.accept(
                new ResourceLocation(Skyrimcraft.MODID + ":forge/" + BuiltInRegistries.ITEM.getKey(ItemInit.EBONY_BOW.get()).getPath()),
                new ForgeRecipe("ebony", new ItemStack(ItemInit.EBONY_BOW.get(), 1), 80, 5,
                        NonNullList.of(
                                Ingredient.of(new ItemStack(ItemInit.LEATHER_STRIPS.get(), 1)),
                                Ingredient.of(new ItemStack(ItemInit.EBONY_INGOT.get(), 3))
                        )),
                null
        );
        consumer.accept(
                new ResourceLocation(Skyrimcraft.MODID + ":forge/" + BuiltInRegistries.ITEM.getKey(ItemInit.EBONY_HELMET.get()).getPath()),
                new ForgeRecipe("ebony", new ItemStack(ItemInit.EBONY_HELMET.get(), 1), 80, 5,
                        NonNullList.of(
                                Ingredient.of(new ItemStack(ItemInit.LEATHER_STRIPS.get(), 2)),
                                Ingredient.of(new ItemStack(ItemInit.EBONY_INGOT.get(), 3))
                        )),
                null
        );
        consumer.accept(
                new ResourceLocation(Skyrimcraft.MODID + ":forge/" + BuiltInRegistries.ITEM.getKey(ItemInit.EBONY_CHESTPLATE.get()).getPath()),
                new ForgeRecipe("ebony", new ItemStack(ItemInit.EBONY_CHESTPLATE.get(), 1), 80, 5,
                        NonNullList.of(
                                Ingredient.of(new ItemStack(ItemInit.LEATHER_STRIPS.get(), 3)),
                                Ingredient.of(new ItemStack(ItemInit.EBONY_INGOT.get(), 5))
                        )),
                null
        );
        consumer.accept(
                new ResourceLocation(Skyrimcraft.MODID + ":forge/" + BuiltInRegistries.ITEM.getKey(ItemInit.EBONY_LEGGINGS.get()).getPath()),
                new ForgeRecipe("ebony", new ItemStack(ItemInit.EBONY_LEGGINGS.get(), 1), 80, 5,
                        NonNullList.of(
                                Ingredient.of(new ItemStack(ItemInit.LEATHER_STRIPS.get(), 3)),
                                Ingredient.of(new ItemStack(ItemInit.EBONY_INGOT.get(), 5))
                        )),
                null
        );
        consumer.accept(
                new ResourceLocation(Skyrimcraft.MODID + ":forge/" + BuiltInRegistries.ITEM.getKey(ItemInit.EBONY_BOOTS.get()).getPath()),
                new ForgeRecipe("ebony", new ItemStack(ItemInit.EBONY_BOOTS.get(), 1), 80, 5,
                        NonNullList.of(
                                Ingredient.of(new ItemStack(ItemInit.LEATHER_STRIPS.get(), 2)),
                                Ingredient.of(new ItemStack(ItemInit.EBONY_INGOT.get(), 3))
                        )),
                null
        );
        consumer.accept(
                new ResourceLocation(Skyrimcraft.MODID + ":forge/" + BuiltInRegistries.ITEM.getKey(ItemInit.EBONY_SHIELD.get()).getPath()),
                new ForgeRecipe("ebony", new ItemStack(ItemInit.EBONY_SHIELD.get(), 1), 80, 5,
                        NonNullList.of(
                                Ingredient.of(new ItemStack(ItemInit.LEATHER_STRIPS.get(), 1)),
                                Ingredient.of(new ItemStack(ItemInit.EBONY_INGOT.get(), 4))
                        )),
                null
        );
    }

    private static void elvenRecipes(RecipeOutput consumer) {
//        RECIPES.put("elven", ForgeRecipe.Builder.recipe().output(new ItemStack(ItemInit.ELVEN_ARROW.get(), 24)).level(30).xp(5)
//                .category("elven")
//                .addRecipeItem(new ItemStack(Items.STICK, 1))
//                .addRecipeItem(new ItemStack(ItemInit.MOONSTONE_INGOT.get(), 1))
//                .save(consumer, Skyrimcraft.MODID + ":recipes/forge/" + ItemInit.ELVEN_ARROW.getId().getPath()));
        consumer.accept(
                new ResourceLocation(Skyrimcraft.MODID + ":forge/" + BuiltInRegistries.ITEM.getKey(ItemInit.ELVEN_DAGGER.get()).getPath()),
                new ForgeRecipe("elven", new ItemStack(ItemInit.ELVEN_DAGGER.get(), 1), 30, 5,
                        NonNullList.of(
                                Ingredient.of(new ItemStack(ItemInit.LEATHER_STRIPS.get(), 1)),
                                Ingredient.of(new ItemStack(Items.IRON_INGOT, 1)),
                                Ingredient.of(new ItemStack(ItemInit.MOONSTONE_INGOT.get(), 1)),
                                Ingredient.of(new ItemStack(ItemInit.QUICKSILVER_INGOT.get(), 1))
                        )),
                null
        );
        consumer.accept(
                new ResourceLocation(Skyrimcraft.MODID + ":forge/" + BuiltInRegistries.ITEM.getKey(ItemInit.ELVEN_SWORD.get()).getPath()),
                new ForgeRecipe("elven", new ItemStack(ItemInit.ELVEN_SWORD.get(), 1), 30, 5,
                        NonNullList.of(
                                Ingredient.of(new ItemStack(ItemInit.LEATHER_STRIPS.get(), 1)),
                                Ingredient.of(new ItemStack(Items.IRON_INGOT, 1)),
                                Ingredient.of(new ItemStack(ItemInit.MOONSTONE_INGOT.get(), 1)),
                                Ingredient.of(new ItemStack(ItemInit.QUICKSILVER_INGOT.get(), 1))
                        )),
                null
        );
        consumer.accept(
                new ResourceLocation(Skyrimcraft.MODID + ":forge/" + BuiltInRegistries.ITEM.getKey(ItemInit.ELVEN_GREATSWORD.get()).getPath()),
                new ForgeRecipe("elven", new ItemStack(ItemInit.ELVEN_GREATSWORD.get(), 1), 30, 5,
                        NonNullList.of(
                                Ingredient.of(new ItemStack(ItemInit.LEATHER_STRIPS.get(), 3)),
                                Ingredient.of(new ItemStack(Items.IRON_INGOT, 2)),
                                Ingredient.of(new ItemStack(ItemInit.MOONSTONE_INGOT.get(), 2)),
                                Ingredient.of(new ItemStack(ItemInit.QUICKSILVER_INGOT.get(), 1))
                        )),
                null
        );
        consumer.accept(
                new ResourceLocation(Skyrimcraft.MODID + ":forge/" + BuiltInRegistries.ITEM.getKey(ItemInit.ELVEN_WAR_AXE.get()).getPath()),
                new ForgeRecipe("elven", new ItemStack(ItemInit.ELVEN_WAR_AXE.get(), 1), 30, 5,
                        NonNullList.of(
                                Ingredient.of(new ItemStack(ItemInit.LEATHER_STRIPS.get(), 2)),
                                Ingredient.of(new ItemStack(Items.IRON_INGOT, 1)),
                                Ingredient.of(new ItemStack(ItemInit.MOONSTONE_INGOT.get(), 1)),
                                Ingredient.of(new ItemStack(ItemInit.QUICKSILVER_INGOT.get(), 1))
                        )),
                null
        );
        consumer.accept(
                new ResourceLocation(Skyrimcraft.MODID + ":forge/" + BuiltInRegistries.ITEM.getKey(ItemInit.ELVEN_BATTLEAXE.get()).getPath()),
                new ForgeRecipe("elven", new ItemStack(ItemInit.ELVEN_BATTLEAXE.get(), 1), 30, 5,
                        NonNullList.of(
                                Ingredient.of(new ItemStack(ItemInit.LEATHER_STRIPS.get(), 2)),
                                Ingredient.of(new ItemStack(Items.IRON_INGOT, 2)),
                                Ingredient.of(new ItemStack(ItemInit.MOONSTONE_INGOT.get(), 2)),
                                Ingredient.of(new ItemStack(ItemInit.QUICKSILVER_INGOT.get(), 1))
                        )),
                null
        );
        consumer.accept(
                new ResourceLocation(Skyrimcraft.MODID + ":forge/" + BuiltInRegistries.ITEM.getKey(ItemInit.ELVEN_MACE.get()).getPath()),
                new ForgeRecipe("elven", new ItemStack(ItemInit.ELVEN_MACE.get(), 1), 30, 5,
                        NonNullList.of(
                                Ingredient.of(new ItemStack(ItemInit.LEATHER_STRIPS.get(), 1)),
                                Ingredient.of(new ItemStack(Items.IRON_INGOT, 1)),
                                Ingredient.of(new ItemStack(ItemInit.MOONSTONE_INGOT.get(), 2)),
                                Ingredient.of(new ItemStack(ItemInit.QUICKSILVER_INGOT.get(), 1))
                        )),
                null
        );
        consumer.accept(
                new ResourceLocation(Skyrimcraft.MODID + ":forge/" + BuiltInRegistries.ITEM.getKey(ItemInit.ELVEN_WARHAMMER.get()).getPath()),
                new ForgeRecipe("elven", new ItemStack(ItemInit.ELVEN_WARHAMMER.get(), 1), 30, 5,
                        NonNullList.of(
                                Ingredient.of(new ItemStack(ItemInit.LEATHER_STRIPS.get(), 3)),
                                Ingredient.of(new ItemStack(Items.IRON_INGOT, 2)),
                                Ingredient.of(new ItemStack(ItemInit.MOONSTONE_INGOT.get(), 2)),
                                Ingredient.of(new ItemStack(ItemInit.QUICKSILVER_INGOT.get(), 1))
                        )),
                null
        );
        consumer.accept(
                new ResourceLocation(Skyrimcraft.MODID + ":forge/" + BuiltInRegistries.ITEM.getKey(ItemInit.ELVEN_BOW.get()).getPath()),
                new ForgeRecipe("elven", new ItemStack(ItemInit.ELVEN_BOW.get(), 1), 30, 5,
                        NonNullList.of(
                                Ingredient.of(new ItemStack(ItemInit.MOONSTONE_INGOT.get(), 2)),
                                Ingredient.of(new ItemStack(ItemInit.QUICKSILVER_INGOT.get(), 1))
                        )),
                null
        );
        consumer.accept(
                new ResourceLocation(Skyrimcraft.MODID + ":forge/" + BuiltInRegistries.ITEM.getKey(ItemInit.ELVEN_HELMET.get()).getPath()),
                new ForgeRecipe("elven", new ItemStack(ItemInit.ELVEN_HELMET.get(), 1), 30, 5,
                        NonNullList.of(
                                Ingredient.of(new ItemStack(ItemInit.LEATHER_STRIPS.get(), 1)),
                                Ingredient.of(new ItemStack(Items.LEATHER, 1)),
                                Ingredient.of(new ItemStack(Items.IRON_INGOT, 1)),
                                Ingredient.of(new ItemStack(ItemInit.MOONSTONE_INGOT.get(), 2))
                        )),
                null
        );
        consumer.accept(
                new ResourceLocation(Skyrimcraft.MODID + ":forge/" + BuiltInRegistries.ITEM.getKey(ItemInit.ELVEN_CHESTPLATE.get()).getPath()),
                new ForgeRecipe("elven", new ItemStack(ItemInit.ELVEN_CHESTPLATE.get(), 1), 30, 5,
                        NonNullList.of(
                                Ingredient.of(new ItemStack(ItemInit.LEATHER_STRIPS.get(), 3)),
                                Ingredient.of(new ItemStack(Items.LEATHER, 1)),
                                Ingredient.of(new ItemStack(Items.IRON_INGOT, 1)),
                                Ingredient.of(new ItemStack(ItemInit.MOONSTONE_INGOT.get(), 4))
                        )),
                null
        );
        consumer.accept(
                new ResourceLocation(Skyrimcraft.MODID + ":forge/" + BuiltInRegistries.ITEM.getKey(ItemInit.ELVEN_LEGGINGS.get()).getPath()),
                new ForgeRecipe("elven", new ItemStack(ItemInit.ELVEN_LEGGINGS.get(), 1), 30, 5,
                        NonNullList.of(
                                Ingredient.of(new ItemStack(ItemInit.LEATHER_STRIPS.get(), 3)),
                                Ingredient.of(new ItemStack(Items.LEATHER, 1)),
                                Ingredient.of(new ItemStack(Items.IRON_INGOT, 1)),
                                Ingredient.of(new ItemStack(ItemInit.MOONSTONE_INGOT.get(), 4))
                        )),
                null
        );
        consumer.accept(
                new ResourceLocation(Skyrimcraft.MODID + ":forge/" + BuiltInRegistries.ITEM.getKey(ItemInit.ELVEN_BOOTS.get()).getPath()),
                new ForgeRecipe("elven", new ItemStack(ItemInit.ELVEN_BOOTS.get(), 1), 30, 5,
                        NonNullList.of(
                                Ingredient.of(new ItemStack(ItemInit.LEATHER_STRIPS.get(), 2)),
                                Ingredient.of(new ItemStack(Items.LEATHER, 1)),
                                Ingredient.of(new ItemStack(Items.IRON_INGOT, 1)),
                                Ingredient.of(new ItemStack(ItemInit.MOONSTONE_INGOT.get(), 2))
                        )),
                null
        );
        consumer.accept(
                new ResourceLocation(Skyrimcraft.MODID + ":forge/" + BuiltInRegistries.ITEM.getKey(ItemInit.ELVEN_SHIELD.get()).getPath()),
                new ForgeRecipe("elven", new ItemStack(ItemInit.ELVEN_SHIELD.get(), 1), 30, 5,
                        NonNullList.of(
                                Ingredient.of(new ItemStack(ItemInit.LEATHER_STRIPS.get(), 2)),
                                Ingredient.of(new ItemStack(Items.IRON_INGOT, 1)),
                                Ingredient.of(new ItemStack(ItemInit.MOONSTONE_INGOT.get(), 4))
                        )),
                null
        );
    }

    private static void glassRecipes(RecipeOutput consumer) {
//        RECIPES.put("glass", ForgeRecipe.Builder.recipe().output(new ItemStack(ItemInit.GLASS_ARROW.get(), 24)).level(70).xp(5)
//                .category("glass")
//                .addRecipeItem(new ItemStack(Items.STICK, 1))
//                .addRecipeItem(new ItemStack(ItemInit.MALACHITE_INGOT.get(), 1))
//                .save(consumer, Skyrimcraft.MODID + ":recipes/forge/" + ItemInit.GLASS_ARROW.getId().getPath()));
        consumer.accept(
                new ResourceLocation(Skyrimcraft.MODID + ":forge/" + BuiltInRegistries.ITEM.getKey(ItemInit.GLASS_DAGGER.get()).getPath()),
                new ForgeRecipe("glass", new ItemStack(ItemInit.GLASS_DAGGER.get(), 1), 70, 5,
                        NonNullList.of(
                                Ingredient.of(new ItemStack(ItemInit.LEATHER_STRIPS.get(), 1)),
                                Ingredient.of(new ItemStack(ItemInit.MOONSTONE_INGOT.get(), 1)),
                                Ingredient.of(new ItemStack(ItemInit.MALACHITE_INGOT.get(), 1))
                        )),
                null
        );
        consumer.accept(
                new ResourceLocation(Skyrimcraft.MODID + ":forge/" + BuiltInRegistries.ITEM.getKey(ItemInit.GLASS_SWORD.get()).getPath()),
                new ForgeRecipe("glass", new ItemStack(ItemInit.GLASS_SWORD.get(), 1), 70, 5,
                        NonNullList.of(
                                Ingredient.of(new ItemStack(ItemInit.LEATHER_STRIPS.get(), 1)),
                                Ingredient.of(new ItemStack(ItemInit.MOONSTONE_INGOT.get(), 1)),
                                Ingredient.of(new ItemStack(ItemInit.MALACHITE_INGOT.get(), 1))
                        )),
                null
        );
        consumer.accept(
                new ResourceLocation(Skyrimcraft.MODID + ":forge/" + BuiltInRegistries.ITEM.getKey(ItemInit.GLASS_GREATSWORD.get()).getPath()),
                new ForgeRecipe("glass", new ItemStack(ItemInit.GLASS_GREATSWORD.get(), 1), 70, 5,
                        NonNullList.of(
                                Ingredient.of(new ItemStack(ItemInit.LEATHER_STRIPS.get(), 3)),
                                Ingredient.of(new ItemStack(ItemInit.MOONSTONE_INGOT.get(), 2)),
                                Ingredient.of(new ItemStack(ItemInit.MALACHITE_INGOT.get(), 2))
                        )),
                null
        );
        consumer.accept(
                new ResourceLocation(Skyrimcraft.MODID + ":forge/" + BuiltInRegistries.ITEM.getKey(ItemInit.GLASS_WAR_AXE.get()).getPath()),
                new ForgeRecipe("glass", new ItemStack(ItemInit.GLASS_WAR_AXE.get(), 1), 70, 5,
                        NonNullList.of(
                                Ingredient.of(new ItemStack(ItemInit.LEATHER_STRIPS.get(), 2)),
                                Ingredient.of(new ItemStack(ItemInit.MOONSTONE_INGOT.get(), 1)),
                                Ingredient.of(new ItemStack(ItemInit.MALACHITE_INGOT.get(), 1))
                        )),
                null
        );
        consumer.accept(
                new ResourceLocation(Skyrimcraft.MODID + ":forge/" + BuiltInRegistries.ITEM.getKey(ItemInit.GLASS_BATTLEAXE.get()).getPath()),
                new ForgeRecipe("glass", new ItemStack(ItemInit.GLASS_BATTLEAXE.get(), 1), 70, 5,
                        NonNullList.of(
                                Ingredient.of(new ItemStack(ItemInit.LEATHER_STRIPS.get(), 2)),
                                Ingredient.of(new ItemStack(ItemInit.MOONSTONE_INGOT.get(), 2)),
                                Ingredient.of(new ItemStack(ItemInit.MALACHITE_INGOT.get(), 2))
                        )),
                null
        );
        consumer.accept(
                new ResourceLocation(Skyrimcraft.MODID + ":forge/" + BuiltInRegistries.ITEM.getKey(ItemInit.GLASS_MACE.get()).getPath()),
                new ForgeRecipe("glass", new ItemStack(ItemInit.GLASS_MACE.get(), 1), 70, 5,
                        NonNullList.of(
                                Ingredient.of(new ItemStack(ItemInit.LEATHER_STRIPS.get(), 1)),
                                Ingredient.of(new ItemStack(ItemInit.MOONSTONE_INGOT.get(), 1)),
                                Ingredient.of(new ItemStack(ItemInit.MALACHITE_INGOT.get(), 2))
                        )),
                null
        );
        consumer.accept(
                new ResourceLocation(Skyrimcraft.MODID + ":forge/" + BuiltInRegistries.ITEM.getKey(ItemInit.GLASS_WARHAMMER.get()).getPath()),
                new ForgeRecipe("glass", new ItemStack(ItemInit.GLASS_WARHAMMER.get(), 1), 70, 5,
                        NonNullList.of(
                                Ingredient.of(new ItemStack(ItemInit.LEATHER_STRIPS.get(), 3)),
                                Ingredient.of(new ItemStack(ItemInit.MOONSTONE_INGOT.get(), 2)),
                                Ingredient.of(new ItemStack(ItemInit.MALACHITE_INGOT.get(), 3))
                        )),
                null
        );
        consumer.accept(
                new ResourceLocation(Skyrimcraft.MODID + ":forge/" + BuiltInRegistries.ITEM.getKey(ItemInit.GLASS_BOW.get()).getPath()),
                new ForgeRecipe("glass", new ItemStack(ItemInit.GLASS_BOW.get(), 1), 70, 5,
                        NonNullList.of(
                                Ingredient.of(new ItemStack(ItemInit.MOONSTONE_INGOT.get(), 1)),
                                Ingredient.of(new ItemStack(ItemInit.MALACHITE_INGOT.get(), 2))
                        )),
                null
        );
        consumer.accept(
                new ResourceLocation(Skyrimcraft.MODID + ":forge/" + BuiltInRegistries.ITEM.getKey(ItemInit.GLASS_HELMET.get()).getPath()),
                new ForgeRecipe("glass", new ItemStack(ItemInit.GLASS_HELMET.get(), 1), 70, 5,
                        NonNullList.of(
                                Ingredient.of(new ItemStack(ItemInit.LEATHER_STRIPS.get(), 1)),
                                Ingredient.of(new ItemStack(Items.LEATHER, 1)),
                                Ingredient.of(new ItemStack(ItemInit.MOONSTONE_INGOT.get(), 1)),
                                Ingredient.of(new ItemStack(ItemInit.MALACHITE_INGOT.get(), 2))
                        )),
                null
        );
        consumer.accept(
                new ResourceLocation(Skyrimcraft.MODID + ":forge/" + BuiltInRegistries.ITEM.getKey(ItemInit.GLASS_CHESTPLATE.get()).getPath()),
                new ForgeRecipe("glass", new ItemStack(ItemInit.GLASS_CHESTPLATE.get(), 1), 70, 5,
                        NonNullList.of(
                                Ingredient.of(new ItemStack(ItemInit.LEATHER_STRIPS.get(), 3)),
                                Ingredient.of(new ItemStack(Items.LEATHER, 1)),
                                Ingredient.of(new ItemStack(ItemInit.MOONSTONE_INGOT.get(), 2)),
                                Ingredient.of(new ItemStack(ItemInit.MALACHITE_INGOT.get(), 4))
                        )),
                null
        );
        consumer.accept(
                new ResourceLocation(Skyrimcraft.MODID + ":forge/" + BuiltInRegistries.ITEM.getKey(ItemInit.GLASS_LEGGINGS.get()).getPath()),
                new ForgeRecipe("glass", new ItemStack(ItemInit.GLASS_LEGGINGS.get(), 1), 70, 5,
                        NonNullList.of(
                                Ingredient.of(new ItemStack(ItemInit.LEATHER_STRIPS.get(), 3)),
                                Ingredient.of(new ItemStack(Items.LEATHER, 1)),
                                Ingredient.of(new ItemStack(ItemInit.MOONSTONE_INGOT.get(), 2)),
                                Ingredient.of(new ItemStack(ItemInit.MALACHITE_INGOT.get(), 4))
                        )),
                null
        );
        consumer.accept(
                new ResourceLocation(Skyrimcraft.MODID + ":forge/" + BuiltInRegistries.ITEM.getKey(ItemInit.GLASS_BOOTS.get()).getPath()),
                new ForgeRecipe("glass", new ItemStack(ItemInit.GLASS_BOOTS.get(), 1), 70, 5,
                        NonNullList.of(
                                Ingredient.of(new ItemStack(ItemInit.LEATHER_STRIPS.get(), 2)),
                                Ingredient.of(new ItemStack(Items.LEATHER, 1)),
                                Ingredient.of(new ItemStack(ItemInit.MOONSTONE_INGOT.get(), 1)),
                                Ingredient.of(new ItemStack(ItemInit.MALACHITE_INGOT.get(), 2))
                        )),
                null
        );
        consumer.accept(
                new ResourceLocation(Skyrimcraft.MODID + ":forge/" + BuiltInRegistries.ITEM.getKey(ItemInit.GLASS_SHIELD.get()).getPath()),
                new ForgeRecipe("glass", new ItemStack(ItemInit.GLASS_SHIELD.get(), 1), 70, 5,
                        NonNullList.of(
                                Ingredient.of(new ItemStack(ItemInit.LEATHER_STRIPS.get(), 2)),
                                Ingredient.of(new ItemStack(ItemInit.MOONSTONE_INGOT.get(), 1)),
                                Ingredient.of(new ItemStack(ItemInit.MALACHITE_INGOT.get(), 4))
                        )),
                null
        );
    }

    private static void ironRecipes(RecipeOutput consumer) {
//        RECIPES.put("iron", ForgeRecipe.Builder.recipe().output(new ItemStack(ItemInit.IRON_ARROW.get(), 24)).level(1).xp(5)
//                .category("iron")
//                .addRecipeItem(new ItemStack(Items.STICK, 1))
//                .addRecipeItem(new ItemStack(Items.IRON_INGOT, 1))
//                .save(consumer, Skyrimcraft.MODID + ":recipes/forge/" + ItemInit.IRON_ARROW.getId().getPath()));
        consumer.accept(
                new ResourceLocation(Skyrimcraft.MODID + ":forge/" + BuiltInRegistries.ITEM.getKey(ItemInit.IRON_DAGGER.get()).getPath()),
                new ForgeRecipe("iron", new ItemStack(ItemInit.IRON_DAGGER.get(), 1), 1, 5,
                        NonNullList.of(
                                Ingredient.of(new ItemStack(ItemInit.LEATHER_STRIPS.get(), 1)),
                                Ingredient.of(new ItemStack(Items.IRON_INGOT, 1))
                        )),
                null
        );
        consumer.accept(
                new ResourceLocation(Skyrimcraft.MODID + ":forge/" + BuiltInRegistries.ITEM.getKey(ItemInit.IRON_SWORD.get()).getPath()),
                new ForgeRecipe("iron", new ItemStack(ItemInit.IRON_SWORD.get(), 1), 1, 5,
                        NonNullList.of(
                                Ingredient.of(new ItemStack(ItemInit.LEATHER_STRIPS.get(), 1)),
                                Ingredient.of(new ItemStack(Items.IRON_INGOT, 2))
                        )),
                null
        );
        consumer.accept(
                new ResourceLocation(Skyrimcraft.MODID + ":forge/" + BuiltInRegistries.ITEM.getKey(ItemInit.IRON_GREATSWORD.get()).getPath()),
                new ForgeRecipe("iron", new ItemStack(ItemInit.IRON_GREATSWORD.get(), 1), 1, 5,
                        NonNullList.of(
                                Ingredient.of(new ItemStack(ItemInit.LEATHER_STRIPS.get(), 2)),
                                Ingredient.of(new ItemStack(Items.IRON_INGOT, 4))
                        )),
                null
        );
        consumer.accept(
                new ResourceLocation(Skyrimcraft.MODID + ":forge/" + BuiltInRegistries.ITEM.getKey(ItemInit.IRON_WAR_AXE.get()).getPath()),
                new ForgeRecipe("iron", new ItemStack(ItemInit.IRON_WAR_AXE.get(), 1), 1, 5,
                        NonNullList.of(
                                Ingredient.of(new ItemStack(ItemInit.LEATHER_STRIPS.get(), 2)),
                                Ingredient.of(new ItemStack(Items.IRON_INGOT, 2))
                        )),
                null
        );
        consumer.accept(
                new ResourceLocation(Skyrimcraft.MODID + ":forge/" + BuiltInRegistries.ITEM.getKey(ItemInit.IRON_BATTLEAXE.get()).getPath()),
                new ForgeRecipe("iron", new ItemStack(ItemInit.IRON_BATTLEAXE.get(), 1), 1, 5,
                        NonNullList.of(
                                Ingredient.of(new ItemStack(ItemInit.LEATHER_STRIPS.get(), 2)),
                                Ingredient.of(new ItemStack(Items.IRON_INGOT, 4))
                        )),
                null
        );
        consumer.accept(
                new ResourceLocation(Skyrimcraft.MODID + ":forge/" + BuiltInRegistries.ITEM.getKey(ItemInit.IRON_MACE.get()).getPath()),
                new ForgeRecipe("iron", new ItemStack(ItemInit.IRON_MACE.get(), 1), 1, 5,
                        NonNullList.of(
                                Ingredient.of(new ItemStack(ItemInit.LEATHER_STRIPS.get(), 2)),
                                Ingredient.of(new ItemStack(Items.IRON_INGOT, 3))
                        )),
                null
        );
        consumer.accept(
                new ResourceLocation(Skyrimcraft.MODID + ":forge/" + BuiltInRegistries.ITEM.getKey(ItemInit.IRON_WARHAMMER.get()).getPath()),
                new ForgeRecipe("iron", new ItemStack(ItemInit.IRON_WARHAMMER.get(), 1), 1, 5,
                        NonNullList.of(
                                Ingredient.of(new ItemStack(ItemInit.LEATHER_STRIPS.get(), 3)),
                                Ingredient.of(new ItemStack(Items.IRON_INGOT, 4))
                        )),
                null
        );
        consumer.accept(
                new ResourceLocation(Skyrimcraft.MODID + ":forge/" + BuiltInRegistries.ITEM.getKey(ItemInit.IRON_HELMET.get()).getPath()),
                new ForgeRecipe("iron", new ItemStack(ItemInit.IRON_HELMET.get(), 1), 1, 5,
                        NonNullList.of(
                                Ingredient.of(new ItemStack(ItemInit.LEATHER_STRIPS.get(), 2)),
                                Ingredient.of(new ItemStack(Items.IRON_INGOT, 3))
                        )),
                null
        );
        consumer.accept(
                new ResourceLocation(Skyrimcraft.MODID + ":forge/" + BuiltInRegistries.ITEM.getKey(ItemInit.IRON_CHESTPLATE.get()).getPath()),
                new ForgeRecipe("iron", new ItemStack(ItemInit.IRON_CHESTPLATE.get(), 1), 1, 5,
                        NonNullList.of(
                                Ingredient.of(new ItemStack(ItemInit.LEATHER_STRIPS.get(), 3)),
                                Ingredient.of(new ItemStack(Items.IRON_INGOT, 5))
                        )),
                null
        );
        consumer.accept(
                new ResourceLocation(Skyrimcraft.MODID + ":forge/" + BuiltInRegistries.ITEM.getKey(ItemInit.IRON_LEGGINGS.get()).getPath()),
                new ForgeRecipe("iron", new ItemStack(ItemInit.IRON_LEGGINGS.get(), 1), 1, 5,
                        NonNullList.of(
                                Ingredient.of(new ItemStack(ItemInit.LEATHER_STRIPS.get(), 3)),
                                Ingredient.of(new ItemStack(Items.IRON_INGOT, 5))
                        )),
                null
        );
        consumer.accept(
                new ResourceLocation(Skyrimcraft.MODID + ":forge/" + BuiltInRegistries.ITEM.getKey(ItemInit.IRON_BOOTS.get()).getPath()),
                new ForgeRecipe("iron", new ItemStack(ItemInit.IRON_BOOTS.get(), 1), 1, 5,
                        NonNullList.of(
                                Ingredient.of(new ItemStack(ItemInit.LEATHER_STRIPS.get(), 2)),
                                Ingredient.of(new ItemStack(Items.IRON_INGOT, 3))
                        )),
                null
        );
        consumer.accept(
                new ResourceLocation(Skyrimcraft.MODID + ":forge/" + BuiltInRegistries.ITEM.getKey(ItemInit.IRON_SHIELD.get()).getPath()),
                new ForgeRecipe("iron", new ItemStack(ItemInit.IRON_SHIELD.get(), 1), 1, 5,
                        NonNullList.of(
                                Ingredient.of(new ItemStack(ItemInit.LEATHER_STRIPS.get(), 1)),
                                Ingredient.of(new ItemStack(Items.IRON_INGOT, 4))
                        )),
                null
        );
    }

    private static void orcishRecipes(RecipeOutput consumer) {
//        RECIPES.put("orcish", ForgeRecipe.Builder.recipe().output(new ItemStack(ItemInit.ORCISH_ARROW.get(), 24)).level(50).xp(5)
//                .category("orcish")
//                .addRecipeItem(new ItemStack(Items.STICK, 1))
//                .addRecipeItem(new ItemStack(ItemInit.ORICHALCUM_INGOT.get(), 1))
//                .save(consumer, Skyrimcraft.MODID + ":recipes/forge/" + ItemInit.ORCISH_ARROW.getId().getPath()));
        consumer.accept(
                new ResourceLocation(Skyrimcraft.MODID + ":forge/" + BuiltInRegistries.ITEM.getKey(ItemInit.ORCISH_DAGGER.get()).getPath()),
                new ForgeRecipe("orcish", new ItemStack(ItemInit.ORCISH_DAGGER.get(), 1), 50, 5,
                        NonNullList.of(
                                Ingredient.of(new ItemStack(ItemInit.LEATHER_STRIPS.get(), 1)),
                                Ingredient.of(new ItemStack(Items.IRON_INGOT, 1)),
                                Ingredient.of(new ItemStack(ItemInit.ORICHALCUM_INGOT.get(), 1))
                        )),
                null
        );
        consumer.accept(
                new ResourceLocation(Skyrimcraft.MODID + ":forge/" + BuiltInRegistries.ITEM.getKey(ItemInit.ORCISH_SWORD.get()).getPath()),
                new ForgeRecipe("orcish", new ItemStack(ItemInit.ORCISH_SWORD.get(), 1), 50, 5,
                        NonNullList.of(
                                Ingredient.of(new ItemStack(ItemInit.LEATHER_STRIPS.get(), 1)),
                                Ingredient.of(new ItemStack(Items.IRON_INGOT, 1)),
                                Ingredient.of(new ItemStack(ItemInit.ORICHALCUM_INGOT.get(), 2))
                        )),
                null
        );
        consumer.accept(
                new ResourceLocation(Skyrimcraft.MODID + ":forge/" + BuiltInRegistries.ITEM.getKey(ItemInit.ORCISH_GREATSWORD.get()).getPath()),
                new ForgeRecipe("orcish", new ItemStack(ItemInit.ORCISH_GREATSWORD.get(), 1), 50, 5,
                        NonNullList.of(
                                Ingredient.of(new ItemStack(ItemInit.LEATHER_STRIPS.get(), 3)),
                                Ingredient.of(new ItemStack(Items.IRON_INGOT, 2)),
                                Ingredient.of(new ItemStack(ItemInit.ORICHALCUM_INGOT.get(), 4))
                        )),
                null
        );
        consumer.accept(
                new ResourceLocation(Skyrimcraft.MODID + ":forge/" + BuiltInRegistries.ITEM.getKey(ItemInit.ORCISH_WAR_AXE.get()).getPath()),
                new ForgeRecipe("orcish", new ItemStack(ItemInit.ORCISH_WAR_AXE.get(), 1), 50, 5,
                        NonNullList.of(
                                Ingredient.of(new ItemStack(ItemInit.LEATHER_STRIPS.get(), 2)),
                                Ingredient.of(new ItemStack(Items.IRON_INGOT, 1)),
                                Ingredient.of(new ItemStack(ItemInit.ORICHALCUM_INGOT.get(), 2))
                        )),
                null
        );
        consumer.accept(
                new ResourceLocation(Skyrimcraft.MODID + ":forge/" + BuiltInRegistries.ITEM.getKey(ItemInit.ORCISH_BATTLEAXE.get()).getPath()),
                new ForgeRecipe("orcish", new ItemStack(ItemInit.ORCISH_BATTLEAXE.get(), 1), 50, 5,
                        NonNullList.of(
                                Ingredient.of(new ItemStack(ItemInit.LEATHER_STRIPS.get(), 2)),
                                Ingredient.of(new ItemStack(Items.IRON_INGOT, 1)),
                                Ingredient.of(new ItemStack(ItemInit.ORICHALCUM_INGOT.get(), 4))
                        )),
                null
        );
        consumer.accept(
                new ResourceLocation(Skyrimcraft.MODID + ":forge/" + BuiltInRegistries.ITEM.getKey(ItemInit.ORCISH_MACE.get()).getPath()),
                new ForgeRecipe("orcish", new ItemStack(ItemInit.ORCISH_MACE.get(), 1), 50, 5,
                        NonNullList.of(
                                Ingredient.of(new ItemStack(ItemInit.LEATHER_STRIPS.get(), 1)),
                                Ingredient.of(new ItemStack(Items.IRON_INGOT, 1)),
                                Ingredient.of(new ItemStack(ItemInit.ORICHALCUM_INGOT.get(), 3))
                        )),
                null
        );
        consumer.accept(
                new ResourceLocation(Skyrimcraft.MODID + ":forge/" + BuiltInRegistries.ITEM.getKey(ItemInit.ORCISH_WARHAMMER.get()).getPath()),
                new ForgeRecipe("orcish", new ItemStack(ItemInit.ORCISH_WARHAMMER.get(), 1), 50, 5,
                        NonNullList.of(
                                Ingredient.of(new ItemStack(ItemInit.LEATHER_STRIPS.get(), 3)),
                                Ingredient.of(new ItemStack(Items.IRON_INGOT, 1)),
                                Ingredient.of(new ItemStack(ItemInit.ORICHALCUM_INGOT.get(), 4))
                        )),
                null
        );
        consumer.accept(
                new ResourceLocation(Skyrimcraft.MODID + ":forge/" + BuiltInRegistries.ITEM.getKey(ItemInit.ORCISH_BOW.get()).getPath()),
                new ForgeRecipe("orcish", new ItemStack(ItemInit.ORCISH_BOW.get(), 1), 50, 5,
                        NonNullList.of(
                                Ingredient.of(new ItemStack(Items.IRON_INGOT, 1)),
                                Ingredient.of(new ItemStack(ItemInit.ORICHALCUM_INGOT.get(), 2))
                        )),
                null
        );
        consumer.accept(
                new ResourceLocation(Skyrimcraft.MODID + ":forge/" + BuiltInRegistries.ITEM.getKey(ItemInit.ORCISH_HELMET.get()).getPath()),
                new ForgeRecipe("orcish", new ItemStack(ItemInit.ORCISH_HELMET.get(), 1), 50, 5,
                        NonNullList.of(
                                Ingredient.of(new ItemStack(ItemInit.LEATHER_STRIPS.get(), 2)),
                                Ingredient.of(new ItemStack(Items.IRON_INGOT, 1)),
                                Ingredient.of(new ItemStack(ItemInit.ORICHALCUM_INGOT.get(), 2))
                        )),
                null
        );
        consumer.accept(
                new ResourceLocation(Skyrimcraft.MODID + ":forge/" + BuiltInRegistries.ITEM.getKey(ItemInit.ORCISH_CHESTPLATE.get()).getPath()),
                new ForgeRecipe("orcish", new ItemStack(ItemInit.ORCISH_CHESTPLATE.get(), 1), 50, 5,
                        NonNullList.of(
                                Ingredient.of(new ItemStack(ItemInit.LEATHER_STRIPS.get(), 3)),
                                Ingredient.of(new ItemStack(Items.IRON_INGOT, 1)),
                                Ingredient.of(new ItemStack(ItemInit.ORICHALCUM_INGOT.get(), 4))
                        )),
                null
        );
        consumer.accept(
                new ResourceLocation(Skyrimcraft.MODID + ":forge/" + BuiltInRegistries.ITEM.getKey(ItemInit.ORCISH_LEGGINGS.get()).getPath()),
                new ForgeRecipe("orcish", new ItemStack(ItemInit.ORCISH_LEGGINGS.get(), 1), 50, 5,
                        NonNullList.of(
                                Ingredient.of(new ItemStack(ItemInit.LEATHER_STRIPS.get(), 3)),
                                Ingredient.of(new ItemStack(Items.IRON_INGOT, 1)),
                                Ingredient.of(new ItemStack(ItemInit.ORICHALCUM_INGOT.get(), 4))
                        )),
                null
        );
        consumer.accept(
                new ResourceLocation(Skyrimcraft.MODID + ":forge/" + BuiltInRegistries.ITEM.getKey(ItemInit.ORCISH_BOOTS.get()).getPath()),
                new ForgeRecipe("orcish", new ItemStack(ItemInit.ORCISH_BOOTS.get(), 1), 50, 5,
                        NonNullList.of(
                                Ingredient.of(new ItemStack(ItemInit.LEATHER_STRIPS.get(), 2)),
                                Ingredient.of(new ItemStack(Items.IRON_INGOT, 1)),
                                Ingredient.of(new ItemStack(ItemInit.ORICHALCUM_INGOT.get(), 3))
                        )),
                null
        );
        consumer.accept(
                new ResourceLocation(Skyrimcraft.MODID + ":forge/" + BuiltInRegistries.ITEM.getKey(ItemInit.ORCISH_SHIELD.get()).getPath()),
                new ForgeRecipe("orcish", new ItemStack(ItemInit.ORCISH_SHIELD.get(), 1), 50, 5,
                        NonNullList.of(
                                Ingredient.of(new ItemStack(ItemInit.LEATHER_STRIPS.get(), 1)),
                                Ingredient.of(new ItemStack(Items.IRON_INGOT, 1)),
                                Ingredient.of(new ItemStack(ItemInit.ORICHALCUM_INGOT.get(), 3))
                        )),
                null
        );
    }

    private static void steelRecipes(RecipeOutput consumer) {
//        RECIPES.put("steel", ForgeRecipe.Builder.recipe().output(new ItemStack(ItemInit.STEEL_ARROW.get(), 24)).level(1).xp(5)
//                .category("steel")
//                .addRecipeItem(new ItemStack(Items.STICK, 1))
//                .addRecipeItem(new ItemStack(ItemInit.STEEL_INGOT.get(), 1))
//                .save(consumer, Skyrimcraft.MODID + ":recipes/forge/" + ItemInit.STEEL_ARROW.getId().getPath()));
        consumer.accept(
                new ResourceLocation(Skyrimcraft.MODID + ":forge/" + BuiltInRegistries.ITEM.getKey(ItemInit.STEEL_DAGGER.get()).getPath()),
                new ForgeRecipe("steel", new ItemStack(ItemInit.STEEL_DAGGER.get(), 1), 1, 5,
                        NonNullList.of(
                                Ingredient.of(new ItemStack(ItemInit.LEATHER_STRIPS.get(), 1)),
                                Ingredient.of(new ItemStack(Items.IRON_INGOT, 1)),
                                Ingredient.of(new ItemStack(ItemInit.STEEL_INGOT.get(), 1))
                        )),
                null
        );
        consumer.accept(
                new ResourceLocation(Skyrimcraft.MODID + ":forge/" + BuiltInRegistries.ITEM.getKey(ItemInit.STEEL_SWORD.get()).getPath()),
                new ForgeRecipe("steel", new ItemStack(ItemInit.STEEL_SWORD.get(), 1), 1, 5,
                        NonNullList.of(
                                Ingredient.of(new ItemStack(ItemInit.LEATHER_STRIPS.get(), 1)),
                                Ingredient.of(new ItemStack(Items.IRON_INGOT, 1)),
                                Ingredient.of(new ItemStack(ItemInit.STEEL_INGOT.get(), 2))
                        )),
                null
        );
        consumer.accept(
                new ResourceLocation(Skyrimcraft.MODID + ":forge/" + BuiltInRegistries.ITEM.getKey(ItemInit.STEEL_GREATSWORD.get()).getPath()),
                new ForgeRecipe("steel", new ItemStack(ItemInit.STEEL_GREATSWORD.get(), 1), 1, 5,
                        NonNullList.of(
                                Ingredient.of(new ItemStack(ItemInit.LEATHER_STRIPS.get(), 3)),
                                Ingredient.of(new ItemStack(Items.IRON_INGOT, 2)),
                                Ingredient.of(new ItemStack(ItemInit.STEEL_INGOT.get(), 4))
                        )),
                null
        );
        consumer.accept(
                new ResourceLocation(Skyrimcraft.MODID + ":forge/" + BuiltInRegistries.ITEM.getKey(ItemInit.STEEL_WAR_AXE.get()).getPath()),
                new ForgeRecipe("steel", new ItemStack(ItemInit.STEEL_WAR_AXE.get(), 1), 1, 5,
                        NonNullList.of(
                                Ingredient.of(new ItemStack(ItemInit.LEATHER_STRIPS.get(), 2)),
                                Ingredient.of(new ItemStack(Items.IRON_INGOT, 1)),
                                Ingredient.of(new ItemStack(ItemInit.STEEL_INGOT.get(), 2))
                        )),
                null
        );
        consumer.accept(
                new ResourceLocation(Skyrimcraft.MODID + ":forge/" + BuiltInRegistries.ITEM.getKey(ItemInit.STEEL_BATTLEAXE.get()).getPath()),
                new ForgeRecipe("steel", new ItemStack(ItemInit.STEEL_BATTLEAXE.get(), 1), 1, 5,
                        NonNullList.of(
                                Ingredient.of(new ItemStack(ItemInit.LEATHER_STRIPS.get(), 2)),
                                Ingredient.of(new ItemStack(Items.IRON_INGOT, 1)),
                                Ingredient.of(new ItemStack(ItemInit.STEEL_INGOT.get(), 4))
                        )),
                null
        );
        consumer.accept(
                new ResourceLocation(Skyrimcraft.MODID + ":forge/" + BuiltInRegistries.ITEM.getKey(ItemInit.STEEL_MACE.get()).getPath()),
                new ForgeRecipe("steel", new ItemStack(ItemInit.STEEL_MACE.get(), 1), 1, 5,
                        NonNullList.of(
                                Ingredient.of(new ItemStack(ItemInit.LEATHER_STRIPS.get(), 1)),
                                Ingredient.of(new ItemStack(Items.IRON_INGOT, 1)),
                                Ingredient.of(new ItemStack(ItemInit.STEEL_INGOT.get(), 3))
                        )),
                null
        );
        consumer.accept(
                new ResourceLocation(Skyrimcraft.MODID + ":forge/" + BuiltInRegistries.ITEM.getKey(ItemInit.STEEL_WARHAMMER.get()).getPath()),
                new ForgeRecipe("steel", new ItemStack(ItemInit.STEEL_WARHAMMER.get(), 1), 1, 5,
                        NonNullList.of(
                                Ingredient.of(new ItemStack(ItemInit.LEATHER_STRIPS.get(), 3)),
                                Ingredient.of(new ItemStack(Items.IRON_INGOT, 1)),
                                Ingredient.of(new ItemStack(ItemInit.STEEL_INGOT.get(), 4))
                        )),
                null
        );
        consumer.accept(
                new ResourceLocation(Skyrimcraft.MODID + ":forge/" + BuiltInRegistries.ITEM.getKey(ItemInit.STEEL_SHIELD.get()).getPath()),
                new ForgeRecipe("steel", new ItemStack(ItemInit.STEEL_SHIELD.get(), 1), 1, 5,
                        NonNullList.of(
                                Ingredient.of(new ItemStack(ItemInit.LEATHER_STRIPS.get(), 1)),
                                Ingredient.of(new ItemStack(Items.IRON_INGOT, 1)),
                                Ingredient.of(new ItemStack(ItemInit.STEEL_INGOT.get(), 3))
                        )),
                null
        );
    }
}
