package com.ryankshah.skyrimcraft.character.magic;

import com.ryankshah.skyrimcraft.Skyrimcraft;
import com.ryankshah.skyrimcraft.effect.ModEffects;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.phys.AABB;

import java.util.ArrayList;
import java.util.List;

public class EmptySpell extends ISpell
{
    public EmptySpell() {
        super(-1, "empty_spell");
    }

    @Override
    public String getName() {
        return "Conjure Familiar";
    }

    @Override
    public ResourceLocation getDisplayAnimation() {
        return null;
    }

    @Override
    public ResourceLocation getIcon() {
        return null;
    }

    @Override
    public SoundEvent getSound() {
        return null;
    }

    @Override
    public float getCost() {
        return 0f;
    }

    @Override
    public float getCooldown() {
        return 0f;
    }

    @Override
    public SpellType getType() {
        return SpellType.CONJURATION;
    }

    @Override
    public SpellDifficulty getDifficulty() {
        return SpellDifficulty.NA;
    }

    @Override
    public int getBaseXp() {
        return 0;
    }

    @Override
    public void onCast() {
        super.onCast();
    }
}
