package com.ryankshah.skyrimcraft.character.skill.type;

import com.ryankshah.skyrimcraft.character.skill.Skill;

import java.util.AbstractMap;

public class Conjuration extends Skill
{
    public Conjuration(int id, String name) {
        super(id, name);
    }

    @Override
    public String getDescription() {
        return "The School of Conjuration governs raising the dead or summoning creations from Oblivion. This skill makes it easier to cast these spells as well as Soul Trap and bindings.";
    }

    @Override
    public AbstractMap.SimpleEntry<Integer, Integer> getIconUV() {
        return new AbstractMap.SimpleEntry<>(64,0);
    }

    @Override
    public float getSkillUseMultiplier() {
        return 2.1f;
    }

    @Override
    public int getSkillUseOffset() {
        return 0;
    }

    @Override
    public float getSkillImproveMultiplier() {
        return 2f;
    }

    @Override
    public int getSkillImproveOffset() {
        return 0;
    }
}
