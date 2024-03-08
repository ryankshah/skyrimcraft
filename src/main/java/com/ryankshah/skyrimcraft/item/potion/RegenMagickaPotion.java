package com.ryankshah.skyrimcraft.item.potion;

import com.ryankshah.skyrimcraft.character.attachment.Character;
import com.ryankshah.skyrimcraft.effect.ModEffects;
import com.ryankshah.skyrimcraft.init.AttributeInit;
import com.ryankshah.skyrimcraft.init.BlockInit;
import com.ryankshah.skyrimcraft.init.ItemInit;
import com.ryankshah.skyrimcraft.item.SkyrimPotion;
import com.ryankshah.skyrimcraft.network.spell.ReplenishMagicka;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.network.PacketDistributor;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class RegenMagickaPotion extends SkyrimPotion
{
    private float modifierValue;
    private int duration;

    public RegenMagickaPotion(Properties properties, float modifierValue, int duration) {
        super(properties);
        this.modifierValue = modifierValue;
        this.duration = duration;
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level worldIn, LivingEntity entityLiving) {
        Player playerEntity = entityLiving instanceof Player ? (Player) entityLiving : null;

        if (!worldIn.isClientSide) {
            if(playerEntity instanceof ServerPlayer) {
                ServerPlayer serverPlayerEntity = (ServerPlayer) playerEntity;
                serverPlayerEntity.getAttribute(AttributeInit.MAGICKA_REGEN.value()).addTransientModifier(new AttributeModifier(AttributeInit.MODIFIER_ID_MAGICKA_REGEN.toString(), modifierValue, AttributeModifier.Operation.MULTIPLY_BASE));
                serverPlayerEntity.addEffect(new MobEffectInstance(ModEffects.MAGICKA_REGEN.get(), duration, 0, true, true, true));
            }
        }

        return super.finishUsingItem(stack, worldIn, entityLiving);
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> ingredients = NonNullList.create();
        if (this == ItemInit.LASTING_POTENCY_POTION.get()) {
            ingredients.add(Ingredient.of(new ItemStack(ItemInit.SALMON_ROE.get(), 1)));
            ingredients.add(Ingredient.of(new ItemStack(ItemInit.GARLIC.get(), 1)));
        } else if(this == ItemInit.DRAUGHT_LASTING_POTENCY_POTION.get()) {
            ingredients.add(Ingredient.of(new ItemStack(ItemInit.GARLIC.get(), 1)));
            ingredients.add(Ingredient.of(new ItemStack(ItemInit.SALT_PILE.get(), 1)));
        } else if (this == ItemInit.SOLUTION_LASTING_POTENCY_POTION.get()) {
            ingredients.add(Ingredient.of(new ItemStack(ItemInit.DWARVEN_OIL.get(), 1)));
            ingredients.add(Ingredient.of(new ItemStack(ItemInit.SALMON_ROE.get(), 1)));
        } else if (this == ItemInit.PHILTER_LASTING_POTENCY_POTION.get()) {
            ingredients.add(Ingredient.of(new ItemStack(ItemInit.SALT_PILE.get(), 1)));
            ingredients.add(Ingredient.of(new ItemStack(ItemInit.DWARVEN_OIL.get(), 1)));
        } else if (this == ItemInit.ELIXIR_LASTING_POTENCY_POTION.get()) {
            ingredients.add(Ingredient.of(new ItemStack(ItemInit.DWARVEN_OIL.get(), 1)));
            ingredients.add(Ingredient.of(new ItemStack(ItemInit.FIRE_SALTS.get(), 1)));
        }
        return ingredients;
    }

    @Override
    public PotionCategory getCategory() {
        return PotionCategory.REGENERATE_HEALTH;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
        String regenValue = "";

        Item item = stack.getItem();
        if (ItemInit.LASTING_POTENCY_POTION.get().equals(item)) {
            regenValue = "50%";
        } else if (ItemInit.DRAUGHT_LASTING_POTENCY_POTION.get().equals(item)) {
            regenValue = "60%";
        } else if (ItemInit.SOLUTION_LASTING_POTENCY_POTION.get().equals(item)) {
            regenValue = "70%";
        } else if (ItemInit.PHILTER_LASTING_POTENCY_POTION.get().equals(item)) {
            regenValue = "80%";
        } else if (ItemInit.ELIXIR_LASTING_POTENCY_POTION.get().equals(item)) {
            regenValue = "100%";
        }

        tooltip.add(Component.literal("Grants " + duration/20 + "s of " + regenValue + " magicka regen"));
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
    }
}