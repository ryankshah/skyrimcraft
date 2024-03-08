package com.ryankshah.skyrimcraft.character.magic.power;

import com.ryankshah.skyrimcraft.Skyrimcraft;
import com.ryankshah.skyrimcraft.character.magic.Spell;
import com.ryankshah.skyrimcraft.character.magic.SpellRegistry;
import com.ryankshah.skyrimcraft.effect.ModEffects;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;

import java.util.ArrayList;
import java.util.List;

public class PowerHistskin extends Spell
{
    public PowerHistskin(int identifier) {
        super(identifier, "histskin");
    }

    @Override
    public String getName() {
        return "Histskin";
    }

    @Override
    public List<String> getDescription() {
        List<String> desc = new ArrayList<>();
        desc.add("Invoke the power of the Hist to recover");
        desc.add("health 10x faster for 60 seconds.");
        return desc;
    }

    @Override
    public ResourceLocation getDisplayAnimation() {
        return new ResourceLocation(Skyrimcraft.MODID, "spells/turn_undead.png");
    }

    @Override
    public ResourceLocation getIcon() {
        return new ResourceLocation(Skyrimcraft.MODID, "spells/icons/fireball.png");
    }

    public SoundEvent getSound() {
        return SoundEvents.BEACON_ACTIVATE;
    }

    @Override
    public float getCost() {
        return 0.0f;
    }

    @Override
    public float getCooldown() {
        return SpellRegistry.DAY_COOLDOWN;
    } // cooldown of one full minecraft day

    @Override
    public SpellType getType() {
        return SpellType.POWERS;
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
    public boolean isContinuousCast() {
        return false;
    }

    @Override
    public void onCast() {
        getCaster().addEffect(new MobEffectInstance(MobEffects.REGENERATION, 1200, 1, true, true, true));

        super.onCast();
    }
}