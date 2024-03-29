package com.ryankshah.skyrimcraft.item;

import com.ryankshah.skyrimcraft.character.attachment.PlayerAttachments;
import com.ryankshah.skyrimcraft.character.magic.ISpell;
import com.ryankshah.skyrimcraft.network.spell.AddToKnownSpells;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.network.PacketDistributor;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SpellBook extends Item
{
    private final Holder<ISpell> spell;

    public SpellBook(Properties properties, Holder<ISpell> spell) {
        super(properties);
        this.spell = spell;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) {
        ItemStack itemstack = playerIn.getItemInHand(handIn);

        // Dont run on server side
        if (!worldIn.isClientSide) {
            return InteractionResultHolder.pass(itemstack);
        }
        List<ISpell> knownSpells = playerIn.getData(PlayerAttachments.KNOWN_SPELLS).getKnownSpells();
        if (this.spell != null && !knownSpells.contains(spell.value())) {
            final AddToKnownSpells addSpell = new AddToKnownSpells(spell.value().getID());
            PacketDistributor.SERVER.noArg().send(addSpell);
            playerIn.displayClientMessage(Component.translatable("spellbook.learn", Component.translatable(spell.value().getName()).withStyle(ChatFormatting.RED)), false);
            playerIn.awardStat(Stats.ITEM_USED.get(this));
            itemstack.shrink(1);
        } else {
            playerIn.displayClientMessage(Component.translatable("spellbook.known"), false);
        }


        return InteractionResultHolder.sidedSuccess(itemstack, worldIn.isClientSide());
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        if(spell != null)
            pTooltipComponents.add(Component.translatable("spellbook.tooltip", Component.translatable(spell.value().getName()).withStyle(ChatFormatting.RED)));
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }
}
