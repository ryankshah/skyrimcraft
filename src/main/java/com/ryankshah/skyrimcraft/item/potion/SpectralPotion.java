package com.ryankshah.skyrimcraft.item.potion;

import com.ryankshah.skyrimcraft.character.attachment.Character;
import com.ryankshah.skyrimcraft.effect.ModEffects;
import com.ryankshah.skyrimcraft.init.BlockInit;
import com.ryankshah.skyrimcraft.init.ItemInit;
import com.ryankshah.skyrimcraft.item.SkyrimPotion;
import com.ryankshah.skyrimcraft.network.spell.ReplenishMagicka;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.network.PacketDistributor;

import javax.annotation.Nullable;
import java.util.List;

public class SpectralPotion extends SkyrimPotion
{
    private int duration;

    public SpectralPotion(Properties properties, int duration) {
        super(properties);
        this.duration = duration;
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level worldIn, LivingEntity entityLiving) {
        Player playerEntity = entityLiving instanceof Player ? (Player) entityLiving : null;

        if(!worldIn.isClientSide) {
            if(playerEntity instanceof ServerPlayer) {
                playerEntity.addEffect(new MobEffectInstance(ModEffects.SPECTRAL.get(), duration, 0, true, true, true));
            }
        }

        return super.finishUsingItem(stack, worldIn, entityLiving);
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return NonNullList.create();
    }

    @Override
    public PotionCategory getCategory() {
        return PotionCategory.UNIQUE;
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltip, TooltipFlag pFlag) {
        pTooltip.add(Component.literal("Appear spectral for " + duration/20 + " seconds"));
        super.appendHoverText(pStack, pLevel, pTooltip, pFlag);
    }
}