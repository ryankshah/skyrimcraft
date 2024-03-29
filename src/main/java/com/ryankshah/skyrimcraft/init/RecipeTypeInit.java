package com.ryankshah.skyrimcraft.init;

import com.ryankshah.skyrimcraft.Skyrimcraft;
import com.ryankshah.skyrimcraft.data.recipe.AlchemyRecipe;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class RecipeTypeInit
{
    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(BuiltInRegistries.RECIPE_TYPE, Skyrimcraft.MODID);

    public static final DeferredHolder<RecipeType<?>, RecipeType<AlchemyRecipe>> ALCHEMY = RECIPE_TYPES.register("alchemy", () -> RecipeType.simple(new ResourceLocation(Skyrimcraft.MODID, "alchemy"))); //)() -> Registry.register(BuiltInRegistries.RECIPE_TYPE, new ResourceLocation(Skyrimcraft.MODID, "alchemy"));
}