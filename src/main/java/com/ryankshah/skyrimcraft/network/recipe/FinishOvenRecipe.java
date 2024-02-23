package com.ryankshah.skyrimcraft.network.recipe;

import com.ryankshah.skyrimcraft.Skyrimcraft;
import com.ryankshah.skyrimcraft.character.attachment.Character;
import com.ryankshah.skyrimcraft.character.skill.SkillRegistry;
import com.ryankshah.skyrimcraft.data.recipe.AlchemyRecipe;
import com.ryankshah.skyrimcraft.data.recipe.OvenRecipe;
import com.ryankshah.skyrimcraft.network.skill.AddXpToSkill;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.handling.PlayPayloadContext;

import java.util.List;

public record FinishOvenRecipe(Recipe<?> recipe) implements CustomPacketPayload
{
    public static final ResourceLocation ID = new ResourceLocation(Skyrimcraft.MODID,"finishovenrecipe");

    public FinishOvenRecipe(final FriendlyByteBuf buffer) {
        this(buffer.readJsonWithCodec(OvenRecipe.CODEC));
    }

    @Override
    public void write(final FriendlyByteBuf buffer) {
        buffer.writeJsonWithCodec(OvenRecipe.CODEC, recipe);
    }

    @Override
    public ResourceLocation id() {
        return ID;
    }

    public static void handleServer(final FinishOvenRecipe data, final PlayPayloadContext context) {
        ServerPlayer player = (ServerPlayer) context.player().orElseThrow();
        Character character = Character.get(player);
        // TODO: use character to add xp to alchemy skill?
        if(data.recipe instanceof OvenRecipe currentRecipeObject) {
            List<Ingredient> recipe = currentRecipeObject.getRecipeItems();
            boolean hasAllItems = recipe.stream().allMatch(ingredient -> hasItem(player, ingredient.getItems()[0]));

            if(!hasAllItems) {
                player.displayClientMessage(Component.translatable("[Skyrimcraft] - You don't have the required items!"), false);
            } else {
                for (Ingredient ing : currentRecipeObject.getRecipeItems()) {
                    hasAndRemoveItem(player, ing.getItems()[0].copy());
                }

                player.getInventory().add(currentRecipeObject.getResult().copy());
                player.playSound(SoundEvents.GENERIC_EXTINGUISH_FIRE, 1.0F, 1.0F);
                player.giveExperiencePoints(currentRecipeObject.getXpGained());

//                final AddXpToSkill addAlchemyXp = new AddXpToSkill(SkillRegistry.ALCHEMY.getID(), SkillRegistry.BASE_ALCHEMY_XP);
//                PacketDistributor.SERVER.noArg().send(addAlchemyXp);
            }
        }
    }

    public static boolean hasItem(Player player, ItemStack is) {
        if (is != null) {
            int count = 0;
            for(int i = 0; i < player.inventoryMenu.slots.size(); ++i) {
                ItemStack stack = player.getInventory().getItem(i);

                if(stack.is(is.getItem())) {
                    count += stack.getCount();
                    if(count >= is.getCount())
                        return true;
                }
            }
        }
        return false;
    }


    //TODO: check why this doesnt actually change the player stacks (creates potion but items still there and potion eventually disappears)
    public static void hasAndRemoveItem(Player player, ItemStack is) {
        if (is != null) {
            player.getInventory().clearOrCountMatchingItems(
                    stack -> stack.is(is.getItem()),
                    is.getCount(),
                    player.getInventory()
            );
            player.getInventory().setChanged();
        }
    }

    public static void hasAndRemoveItems(Player player, ItemStack is) {
        if (is != null) {
            int count = is.getCount();

            for(int i = 0; i < player.inventoryMenu.slots.size(); ++i) {
                ItemStack stack =  player.getInventory().getItem(i);
                if(is.equals(stack) && ItemStack.matches(is, stack)) {
                    if(stack.getCount() >= count) {
                        player.getInventory().removeItem(i, count);
                        break;
                    } else {
                        count -= stack.getCount();
                        player.getInventory().removeItem(i, stack.getCount());
                    }
                }
            }
        }
    }
}
