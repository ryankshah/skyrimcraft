package com.ryankshah.skyrimcraft.init;

import com.ryankshah.skyrimcraft.Skyrimcraft;
import com.ryankshah.skyrimcraft.data.recipe.AlchemyRecipe;
import com.ryankshah.skyrimcraft.data.recipe.ForgeRecipe;
import com.ryankshah.skyrimcraft.data.recipe.OvenRecipe;
import com.ryankshah.skyrimcraft.data.recipe.serial.AlchemyRecipeSerializer;
import com.ryankshah.skyrimcraft.data.recipe.serial.ForgeRecipeSerializer;
import com.ryankshah.skyrimcraft.data.recipe.serial.OvenRecipeSerializer;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class SerializerInit
{
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS = DeferredRegister.create(Registries.RECIPE_SERIALIZER, Skyrimcraft.MODID);

    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<AlchemyRecipe>> ALCHEMY_RECIPE_SERIALIZER = SERIALIZERS.register("alchemy", AlchemyRecipeSerializer::new);
    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<OvenRecipe>> OVEN_RECIPE_SERIALIZER = SERIALIZERS.register("oven", OvenRecipeSerializer::new);
    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<ForgeRecipe>> FORGE_RECIPE_SERIALIZER = SERIALIZERS.register("forge", ForgeRecipeSerializer::new);
}