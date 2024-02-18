package com.ryankshah.skyrimcraft.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ambient.AmbientCreature;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;

public class EffectFrozen extends MobEffect
{
    protected EffectFrozen(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor);
    }

    // Whether the effect should apply this tick. Used e.g. by the Regeneration effect that only applies
    // once every x ticks, depending on the tick count and amplifier.
    @Override
    public boolean shouldApplyEffectTickThisTick(int tickCount, int amplifier) {
        return true; //tickCount % 2 == 0; // replace this with whatever check you want
    }

    @Override
    public void applyEffectTick(LivingEntity livingEntity, int p_76394_2_) {
        if(livingEntity instanceof AmbientCreature)
            ((AmbientCreature) livingEntity).goalSelector.setControlFlag(Goal.Flag.MOVE, false);
        else if(livingEntity instanceof Monster)
            ((Monster) livingEntity).goalSelector.setControlFlag(Goal.Flag.MOVE, false);
        else if(livingEntity instanceof Player)
            ((Player) livingEntity).setDeltaMovement(0, 0 ,0);
        super.applyEffectTick(livingEntity, p_76394_2_);
    }
}
