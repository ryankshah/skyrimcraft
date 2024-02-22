package com.ryankshah.skyrimcraft.data.recipe.serial;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.ryankshah.skyrimcraft.data.recipe.OvenRecipe;
import com.ryankshah.skyrimcraft.data.recipe.OvenRecipe;
import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import org.jetbrains.annotations.NotNull;

public class OvenRecipeSerializer implements RecipeSerializer<OvenRecipe>
{
    public static final Codec<OvenRecipe> CODEC = RecordCodecBuilder.create(
            p_311738_ -> p_311738_.group(
                            ExtraCodecs.strictOptionalField(Codec.STRING, "category", "alchemy").forGetter(OvenRecipe::getCategory),
                            ItemStack.RESULT_CODEC.forGetter(OvenRecipe::getResult),
                            ExtraCodecs.strictOptionalField(Codec.INT, "level", 0).forGetter(OvenRecipe::getRequiredLevel),
                            ExtraCodecs.strictOptionalField(Codec.INT, "xp", 0).forGetter(OvenRecipe::getXpGained),
                            Ingredient.CODEC_NONEMPTY
                                    .listOf()
                                    .fieldOf("ingredients")
                                    .flatXmap(
                                            p_301021_ -> {
                                                Ingredient[] ingredient = p_301021_.toArray(Ingredient[]::new);
                                                if(ingredient.length == 0) {
                                                    return DataResult.error(() -> "No ingredients for Oven Recipe");
                                                } else {
                                                    return DataResult.success(NonNullList.of(Ingredient.EMPTY, ingredient));
                                                }
                                            },
                                            DataResult::success
                                    )
                                    .forGetter(OvenRecipe::getIngredients)
                    )
                    .apply(p_311738_, OvenRecipe::new)
    );

    public OvenRecipeSerializer() {
    }

    @Override
    public @NotNull Codec<OvenRecipe> codec() {
        return CODEC;
    }

    @Override
    public @NotNull OvenRecipe fromNetwork(FriendlyByteBuf buf) {
        NonNullList<Ingredient> recipeItems = NonNullList.create();
        String category = buf.readUtf();
        ItemStack stackToCreate = buf.readItem();

        int length = buf.readInt();
        for (int i = 0; i < length; i++) {
            int ingLen = buf.readInt();
            ItemStack[] stacks = new ItemStack[ingLen];
            for(int j = 0; j < ingLen; j++)
                stacks[i] = buf.readItem();
            recipeItems.add(Ingredient.of(stacks));
        }

        int level = buf.readInt();
        int xp = buf.readInt();

        return new OvenRecipe(category, stackToCreate, xp, level, recipeItems);
    }

    @Override
    public void toNetwork(FriendlyByteBuf buf, OvenRecipe OvenRecipe) {
        buf.writeUtf(OvenRecipe.getCategory());

        if (OvenRecipe.getResult() != null) {
            buf.writeItem(OvenRecipe.getResult());
        }

        if (OvenRecipe.getRecipeItems() != null && !OvenRecipe.getRecipeItems().isEmpty()) {
            buf.writeInt(OvenRecipe.getRecipeItems().size());
            for (Ingredient ing : OvenRecipe.getRecipeItems()) {
                buf.writeInt(ing.getItems().length);
                for(int i = 0; i < ing.getItems().length; i++)
                    buf.writeItem(ing.getItems()[i]);
            }
        }

        buf.writeInt(OvenRecipe.getRequiredLevel());
        buf.writeInt(OvenRecipe.getXpGained());
    }
}