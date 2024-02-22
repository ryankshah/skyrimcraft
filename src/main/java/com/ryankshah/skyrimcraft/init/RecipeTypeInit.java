package com.ryankshah.skyrimcraft.init;

import com.ryankshah.skyrimcraft.Skyrimcraft;
import com.ryankshah.skyrimcraft.data.recipe.AlchemyRecipe;
import com.ryankshah.skyrimcraft.data.recipe.ForgeRecipe;
import com.ryankshah.skyrimcraft.data.recipe.OvenRecipe;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class RecipeTypeInit
{
    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(BuiltInRegistries.RECIPE_TYPE, Skyrimcraft.MODID);

    public static final DeferredHolder<RecipeType<?>, RecipeType<AlchemyRecipe>> ALCHEMY = RECIPE_TYPES.register("alchemy",
            () -> RecipeType.simple(new ResourceLocation(Skyrimcraft.MODID, "alchemy")));
    public static final DeferredHolder<RecipeType<?>, RecipeType<OvenRecipe>> OVEN = RECIPE_TYPES.register("oven",
            () -> RecipeType.simple(new ResourceLocation(Skyrimcraft.MODID, "oven")));
    public static final DeferredHolder<RecipeType<?>, RecipeType<ForgeRecipe>> FORGE = RECIPE_TYPES.register("forge",
            () -> RecipeType.simple(new ResourceLocation(Skyrimcraft.MODID, "forge")));
}