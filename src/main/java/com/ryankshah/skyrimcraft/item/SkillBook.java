package com.ryankshah.skyrimcraft.item;

import com.ryankshah.skyrimcraft.character.attachment.Character;
import com.ryankshah.skyrimcraft.character.skill.Skill;
import com.ryankshah.skyrimcraft.character.skill.SkillRegistry;
import com.ryankshah.skyrimcraft.character.skill.SkillWrapper;
import com.ryankshah.skyrimcraft.network.skill.AddXpToSkill;
import net.minecraft.ChatFormatting;
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
import java.util.function.Supplier;

public class SkillBook extends Item
{
    private final Supplier<Skill> skill;

    public SkillBook(Properties properties, Supplier<Skill> skill) {
        super(properties);
        this.skill = skill;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) {
        ItemStack itemstack = playerIn.getItemInHand(handIn);

        // Dont run on server side
        if (!worldIn.isClientSide) {
            return InteractionResultHolder.pass(itemstack);
        }
        Character character = Character.get(playerIn);
        if (this.skill != null) {
            SkillWrapper skillWrapper = character.getSkill(skill.get().getID());
            //TODO: Need to check if we should keep the same xp progress or something?
            double xp = skillWrapper.getXpProgress() / skillWrapper.getXpNeededForNextLevel();
            final AddXpToSkill addXpToSkill = new AddXpToSkill(SkillRegistry.SKILLS_REGISTRY.getResourceKey(skill.get()).get(), (int)xp);
            PacketDistributor.SERVER.noArg().send(addXpToSkill);
            playerIn.displayClientMessage(Component.translatable("skillbook.learn", Component.translatable(skillWrapper.getName()).withStyle(ChatFormatting.RED)), false);
            playerIn.awardStat(Stats.ITEM_USED.get(this));
            itemstack.shrink(1);
        } else {
            playerIn.displayClientMessage(Component.translatable("skillbook.fail"), false);
        }


        return InteractionResultHolder.sidedSuccess(itemstack, worldIn.isClientSide());
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        if(skill != null)
            pTooltipComponents.add(Component.translatable("skillbook.tooltip", Component.translatable(
                            skill.get().getName())
                    .withStyle(ChatFormatting.RED)));
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }
}
