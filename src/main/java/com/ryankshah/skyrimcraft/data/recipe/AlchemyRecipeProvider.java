package com.ryankshah.skyrimcraft.data.recipe;

import com.google.common.collect.Sets;
import com.ryankshah.skyrimcraft.Skyrimcraft;
import com.ryankshah.skyrimcraft.init.ItemInit;
import com.ryankshah.skyrimcraft.init.RecipeTypeInit;
import com.ryankshah.skyrimcraft.item.SkyrimPotion;
import com.ryankshah.skyrimcraft.item.potion.IPotion;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
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

public class AlchemyRecipeProvider implements DataProvider, IConditionBuilder
{
    protected final PackOutput.PathProvider recipePathProvider;
    protected final CompletableFuture<HolderLookup.Provider> lookupProvider;

    public AlchemyRecipeProvider(PackOutput p_248933_, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        this.recipePathProvider = p_248933_.createPathProvider(PackOutput.Target.DATA_PACK, "recipes");
        this.lookupProvider = lookupProvider;
    }

    protected void buildRecipes(RecipeOutput pRecipeOutput) {
        List<SkyrimPotion> potions = ItemInit.ITEMS.getEntries().stream().filter(item -> item.get() instanceof SkyrimPotion).map(item -> (SkyrimPotion)item.get()).toList();
        for(SkyrimPotion potion : potions) {
            if(potion.getCategory() == IPotion.PotionCategory.UNIQUE || potion.getIngredients().isEmpty())
                continue;

            NonNullList<Ingredient> ings = potion.getIngredients();
            AlchemyRecipe recipe = new AlchemyRecipe(potion.getCategory().toString(), new ItemStack(potion, 1), 2, 1, ings);

            pRecipeOutput.accept(new ResourceLocation(Skyrimcraft.MODID + ":alchemy/" + BuiltInRegistries.ITEM.getKey(potion).getPath()), recipe, null);
        }
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
                    } else { // AlchemyRecipe.CONDITIONAL_CODEC
                        list.add(DataProvider.saveStable(pOutput, Recipe.CONDITIONAL_CODEC, Optional.of(new WithConditions(p_312254_, conditions)), AlchemyRecipeProvider.this.recipePathProvider.json(p_312039_)));
//                        if (p_311794_ != null) {
//                            list.add(DataProvider.saveStable(pOutput, Advancement.CONDITIONAL_CODEC, Optional.of(new WithConditions(p_311794_.value(), conditions)), AlchemyRecipeProvider.this.advancementPathProvider.json(p_311794_.id())));
//                        }

                    }
                }
//                public Advancement.Builder advancement() {
//                    return Advancement.Builder.recipeAdvancement().parent(RecipeBuilder.ROOT_RECIPE_ADVANCEMENT);
//                }
            });
            return CompletableFuture.allOf(list.toArray(CompletableFuture[]::new));
        });
    }

    @Override
    public String getName() {
        return Skyrimcraft.MODID + " Alchemy Recipes";
    }
}
