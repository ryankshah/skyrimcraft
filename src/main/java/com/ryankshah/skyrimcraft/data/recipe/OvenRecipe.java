package com.ryankshah.skyrimcraft.data.recipe;

import com.mojang.serialization.Codec;
import com.ryankshah.skyrimcraft.block.inventory.IOvenInventory;
import com.ryankshah.skyrimcraft.data.recipe.serial.OvenRecipeSerializer;
import com.ryankshah.skyrimcraft.init.RecipeTypeInit;
import com.ryankshah.skyrimcraft.init.SerializerInit;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.conditions.ConditionalOps;
import net.neoforged.neoforge.common.conditions.WithConditions;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class OvenRecipe implements Recipe<IOvenInventory>
{
    protected final String category;
    protected final ItemStack stackToCreate;
    protected final NonNullList<Ingredient> recipeItems;
    protected final int level;
    protected final int xp;

    public static Codec<Optional<WithConditions<OvenRecipe>>> CONDITIONAL_CODEC = ConditionalOps.createConditionalCodecWithConditions(OvenRecipeSerializer.CODEC);

    public OvenRecipe(String category, ItemStack stackToCreate, int level, int xp, NonNullList<Ingredient> recipeItems) {
        this.category = category;
        this.stackToCreate = stackToCreate;
        this.recipeItems = recipeItems;
        this.level = level;
        this.xp = xp;
//        RecipeTypeInit.ALCHEMY.get();
    }

//    public AlchemyRecipe(ResourceLocation id, String category, ItemStack stackToCreate, int level, int xp, ItemStack... recipeItems) {
//        this(id, category, stackToCreate, level, xp, Arrays.asList(recipeItems));
//    }

    public OvenRecipe(String category, ItemStack itemStack, NonNullList<Ingredient> itemStacks, int level, int xp) {
        this(category, itemStack, level, xp, itemStacks);
    }

    @Override
    public boolean matches(IOvenInventory p_77569_1_, Level p_77569_2_) {
        return this.recipeItems.stream().allMatch(item -> item.test(p_77569_1_.getItem(0))); // todo: check this?
    }

    @Override
    public @NotNull ItemStack assemble(IOvenInventory pContainer, RegistryAccess pRegistryAccess) {
        return this.stackToCreate.copy();
    }

    @Override
    public boolean canCraftInDimensions(int p_194133_1_, int p_194133_2_) {
        return true;
    }

    @Override
    public @NotNull ItemStack getResultItem(RegistryAccess pRegistryAccess) {
        return this.stackToCreate;
    }

//    public ResourceLocation getId() {
//        return this.id;
//    }

    @Override
    public @NotNull RecipeSerializer<OvenRecipe> getSerializer() {
        return SerializerInit.OVEN_RECIPE_SERIALIZER.get();
    }

    @Override
    public @NotNull RecipeType<OvenRecipe> getType() {
        return RecipeTypeInit.OVEN.get();
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public @NotNull NonNullList<Ingredient> getIngredients() {
        return this.recipeItems; //NonNullList.copyOf(getRecipeItems().stream().map(Ingredient::of).toList());
    }

    public ItemStack getResult() {
        return this.stackToCreate;
    }

    public String getCategory() {
        return this.category;
    }

    public NonNullList<Ingredient> getRecipeItems() {
        return this.recipeItems;
    }

    public int getRequiredLevel() {
        return this.level;
    }

    public int getXpGained() {
        return this.xp;
    }

    public OvenRecipe.Builder deconstruct() {
        return new OvenRecipe.Builder(this.category, this.stackToCreate, this.level, this.xp, this.recipeItems);
    }

//    public boolean equals(Object obj) {
//        if (this == obj) {
//            return true;
//        } else if (!(obj instanceof AlchemyRecipe)) {
//            return false;
//        } else {
//            AlchemyRecipe recipe = (AlchemyRecipe)obj;
//            return this
//        }
//    }

//    public int hashCode() {
//        return this.id.hashCode();
//    }

    public static class Builder {
        private String category;
        private ItemStack stackToCreate;
        private NonNullList<Ingredient> recipeItems = NonNullList.create();
        private int level;
        private int xp;

        public Builder(String category, ItemStack stackToCreate, int level, int xp, NonNullList<Ingredient> recipeItems) {
            this.stackToCreate = stackToCreate;
            this.recipeItems = recipeItems;
            this.level = level;
            this.xp = xp;
            this.category = category;
        }

        public Builder() {
        }

        public static OvenRecipe.Builder recipe() {
            return new OvenRecipe.Builder();
        }

        public OvenRecipe.Builder output(ItemStack stack) {
            this.stackToCreate = stack;
            return this;
        }

        public OvenRecipe.Builder level(int level) {
            this.level = level;
            return this;
        }

        public OvenRecipe.Builder category(String category) {
            this.category = category;
            return this;
        }

        public OvenRecipe.Builder xp(int xp) {
            this.xp = xp;
            return this;
        }

        public OvenRecipe.Builder addRecipeItem(ItemStack stack) {
            if(this.recipeItems.contains(stack))
                throw new IllegalArgumentException("Duplicate recipe Ingredient " + stack);
            else {
                this.recipeItems.add(Ingredient.of(stack));
                return this;
            }
        }

//        public AlchemyRecipe build(ResourceLocation p_192056_1_) {
//            if (this.recipeItems == null || this.recipeItems.isEmpty()) {
//                throw new IllegalStateException("Tried to build incomplete alchemy recipe!");
//            } else {
//                return new AlchemyRecipe(this.category, this.stackToCreate, this.level, this.xp, this.recipeItems);
//            }
//        }

        public OvenRecipe build(String category, ItemStack create, int level, int xp, NonNullList<Ingredient> recipe) {
            return new OvenRecipe(category, create, level, xp, recipe);
        }

//        public JsonObject serializeToJson() {
//            JsonObject jsonobject = new JsonObject();
//
//            jsonobject.addProperty("type", Skyrimcraft.MODID+":alchemy");
//
//            jsonobject.addProperty("category", category);
//
//            JsonObject output = new JsonObject();
//            output.addProperty("item", BuiltInRegistries.ITEM.getKey(stackToCreate.getItem()).toString());
//            output.addProperty("amount", stackToCreate.getCount());
//            jsonobject.add("output", output);
//
//            JsonArray recipeItems = new JsonArray();
//            for(ItemStack ing : this.recipeItems) {
//                ItemStack stack = ing.getItems();
//                JsonObject stackObj = new JsonObject();
//                stackObj.addProperty("item", BuiltInRegistries.ITEM.getKey(stack.getItem()).toString());
//                stackObj.addProperty("amount", stack.getCount());
//                recipeItems.add(stackObj);
//            }
//            jsonobject.add("recipe", recipeItems);
//
//            jsonobject.addProperty("levelToCreate", level);
//            jsonobject.addProperty("xp", xp);
//            return jsonobject;
//        }

        public String toString() {
            return "AlchemyRecipe{stackToCreate=" + this.stackToCreate + ", level=" + this.level + ", xp=" + this.xp + ", recipeItems=" + this.recipeItems + '}';
        }

        public NonNullList<Ingredient> getRecipeItems() {
            return this.recipeItems;
        }
    }
}