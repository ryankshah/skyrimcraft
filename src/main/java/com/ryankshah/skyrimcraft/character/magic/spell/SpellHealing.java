package com.ryankshah.skyrimcraft.character.magic.spell;

import com.ryankshah.skyrimcraft.Skyrimcraft;
import com.ryankshah.skyrimcraft.character.magic.ISpell;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

import java.util.ArrayList;
import java.util.List;

public class SpellHealing extends ISpell
{
    public SpellHealing(int identifier) {
        super(identifier, "healing");
    }

    @Override
    public String getName() {
        return "Healing";
    }

    @Override
    public List<String> getDescription() {
        List<String> desc = new ArrayList<>();
        desc.add("Heals the caster half a");
        desc.add("heart point per second");
        return desc;
    }

    @Override
    public ResourceLocation getDisplayAnimation() {
        return new ResourceLocation(Skyrimcraft.MODID, "spells/fireball.png");
    }

    @Override
    public ResourceLocation getIcon() {
        return new ResourceLocation(Skyrimcraft.MODID, "spells/icons/fireball.png");
    }

    @Override
    public SoundEvent getSound() {
        return null;
    }

    @Override
    public float getCost() {
        return 2.0f;
    }

    @Override
    public float getCooldown() {
        return 0f;
    }

    @Override
    public SpellType getType() {
        return SpellType.RESTORATION;
    }

    @Override
    public SpellDifficulty getDifficulty() {
        return SpellDifficulty.NOVICE;
    }

    @Override
    public int getBaseXp() {
        return 4;
    }

    @Override
    public void onCast() {
        getCaster().heal(0.5f);

        super.onCast();
    }
}