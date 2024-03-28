package com.ryankshah.skyrimcraft.character.magic.power;

import com.ryankshah.skyrimcraft.Skyrimcraft;
import com.ryankshah.skyrimcraft.character.magic.Spell;
import com.ryankshah.skyrimcraft.character.magic.SpellRegistry;
import com.ryankshah.skyrimcraft.effect.ModEffects;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;

import java.util.ArrayList;
import java.util.List;

public class PowerAdrenalineRush extends Spell
{
    public PowerAdrenalineRush(int identifier) {
        super(identifier, "adrenaline_rush");
    }

    @Override
    public String getName() {
        return "Adrenaline Rush";
    }

    @Override
    public List<String> getDescription() {
        List<String> desc = new ArrayList<>();
        desc.add("Stamina Regenerates 10 times");
        desc.add("faster for 60 seconds");
        return desc;
    }

    @Override
    public ResourceLocation getDisplayAnimation() {
        return new ResourceLocation(Skyrimcraft.MODID, "spells/adrenaline_rush.png");
    }

    @Override
    public ResourceLocation getIcon() {
        return new ResourceLocation(Skyrimcraft.MODID, "spells/icons/adrenaline.png");
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
        getCaster().addEffect(new MobEffectInstance(ModEffects.ADRENALINE_RUSH.get(), 1200, 0, false, true, true));
        super.onCast();
    }
}