package com.ryankshah.skyrimcraft.data.recipe.serial;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.ryankshah.skyrimcraft.Skyrimcraft;
import com.ryankshah.skyrimcraft.data.recipe.AlchemyRecipe;
import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import org.jetbrains.annotations.NotNull;

public class AlchemyRecipeSerializer implements RecipeSerializer<AlchemyRecipe>
{
    public static final Codec<AlchemyRecipe> CODEC = RecordCodecBuilder.create(
            p_311738_ -> p_311738_.group(
                            ExtraCodecs.strictOptionalField(Codec.STRING, "category", "alchemy").forGetter(AlchemyRecipe::getCategory),
                            ItemStack.RESULT_CODEC.forGetter(AlchemyRecipe::getResult),
                            ExtraCodecs.strictOptionalField(Codec.INT, "level", 0).forGetter(AlchemyRecipe::getRequiredLevel),
                            ExtraCodecs.strictOptionalField(Codec.INT, "xp", 0).forGetter(AlchemyRecipe::getXpGained),
                            Ingredient.CODEC_NONEMPTY
                                    .listOf()
                                    .fieldOf("ingredients")
                                    .flatXmap(
                                            p_301021_ -> {
                                                Ingredient[] ingredient = p_301021_.toArray(Ingredient[]::new);
                                                if(ingredient.length == 0) {
                                                    return DataResult.error(() -> "No ingredients for Alchemy Recipe");
                                                } else {
                                                    return DataResult.success(NonNullList.of(Ingredient.EMPTY, ingredient));
                                                }
                                            },
                                            DataResult::success
                                    )
                                    .forGetter(AlchemyRecipe::getIngredients)
                    )
                    .apply(p_311738_, AlchemyRecipe::new)
    );

    public AlchemyRecipeSerializer() {
    }

    @Override
    public @NotNull Codec<AlchemyRecipe> codec() {
        return CODEC;
    }

    @Override
    public @NotNull AlchemyRecipe fromNetwork(FriendlyByteBuf buf) {
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

        return new AlchemyRecipe(category, stackToCreate, xp, level, recipeItems);
    }

    @Override
    public void toNetwork(FriendlyByteBuf buf, AlchemyRecipe alchemyRecipe) {
        buf.writeUtf(alchemyRecipe.getCategory());

        if (alchemyRecipe.getResult() != null) {
            buf.writeItem(alchemyRecipe.getResult());
        }

        if (alchemyRecipe.getRecipeItems() != null && !alchemyRecipe.getRecipeItems().isEmpty()) {
            buf.writeInt(alchemyRecipe.getRecipeItems().size());
            for (Ingredient ing : alchemyRecipe.getRecipeItems()) {
                buf.writeInt(ing.getItems().length);
                for(int i = 0; i < ing.getItems().length; i++)
                    buf.writeItem(ing.getItems()[i]);
            }
        }

        buf.writeInt(alchemyRecipe.getRequiredLevel());
        buf.writeInt(alchemyRecipe.getXpGained());
    }
}