package com.ryankshah.skyrimcraft.item.potion;

import com.ryankshah.skyrimcraft.character.attachment.Character;
import com.ryankshah.skyrimcraft.character.attachment.PlayerAttachments;
import com.ryankshah.skyrimcraft.init.BlockInit;
import com.ryankshah.skyrimcraft.init.ItemInit;
import com.ryankshah.skyrimcraft.item.SkyrimPotion;
import com.ryankshah.skyrimcraft.network.spell.ReplenishMagicka;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.network.PacketDistributor;

import javax.annotation.Nullable;
import java.util.List;

public class MagickaPotion extends SkyrimPotion
{
    private float replenishValue;

    public MagickaPotion(Properties properties, float replenishValue) {
        super(properties);
        this.replenishValue = replenishValue;
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level worldIn, LivingEntity entityLiving) {
        Player playerEntity = entityLiving instanceof Player ? (Player) entityLiving : null;

        if(!worldIn.isClientSide) {
            ServerPlayer player = (ServerPlayer) playerEntity;
            Character character = Character.get(player);
            if(character.getMagicka() != character.getMaxMagicka()) {
                final ReplenishMagicka replenishMagicka = new ReplenishMagicka((int) replenishValue);
                PacketDistributor.SERVER.noArg().send(replenishMagicka);
            }
        }

        return super.finishUsingItem(stack, worldIn, entityLiving);
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> ingredients = NonNullList.create();
        if (this == ItemInit.MINOR_MAGICKA_POTION.get()) {
            ingredients.add(Ingredient.of(new ItemStack(ItemInit.GRASS_POD.get(), 1)));
            ingredients.add(Ingredient.of(new ItemStack(BlockInit.RED_MOUNTAIN_FLOWER.get(), 1)));
        } else if(this == ItemInit.MAGICKA_POTION.get()) {
            ingredients.add(Ingredient.of(new ItemStack(ItemInit.GRASS_POD.get(), 1)));
            ingredients.add(Ingredient.of(new ItemStack(ItemInit.BRIAR_HEART.get(), 1)));
        } else if (this == ItemInit.PLENTIFUL_MAGICKA_POTION.get()) {
            ingredients.add(Ingredient.of(new ItemStack(ItemInit.VAMPIRE_DUST.get(), 1)));
            ingredients.add(Ingredient.of(new ItemStack(ItemInit.BRIAR_HEART.get(), 1)));
        } else if (this == ItemInit.VIGOROUS_MAGICKA_POTION.get()) {
            ingredients.add(Ingredient.of(new ItemStack(ItemInit.CREEP_CLUSTER.get(), 1)));
            ingredients.add(Ingredient.of(new ItemStack(BlockInit.RED_MOUNTAIN_FLOWER.get(), 1)));
        } else if (this == ItemInit.EXTREME_MAGICKA_POTION.get()) {
            ingredients.add(Ingredient.of(new ItemStack(ItemInit.CREEP_CLUSTER.get(), 1)));
            ingredients.add(Ingredient.of(new ItemStack(ItemInit.MORA_TAPINELLA.get(), 1)));
        } else if (this == ItemInit.ULTIMATE_MAGICKA_POTION.get()) {
            ingredients.add(Ingredient.of(new ItemStack(ItemInit.MORA_TAPINELLA.get(), 1)));
            ingredients.add(Ingredient.of(new ItemStack(BlockInit.RED_MOUNTAIN_FLOWER.get(), 1)));
        }
        return ingredients;
    }

    @Override
    public PotionCategory getCategory() {
        return PotionCategory.RESTORE_MAGICKA;
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltip, TooltipFlag pFlag) {
        pTooltip.add(Component.literal((int)replenishValue == 20 ? "Completely replenishes your magicka" : "Replenishes " + (int)replenishValue + " magicka"));
        super.appendHoverText(pStack, pLevel, pTooltip, pFlag);
    }
}