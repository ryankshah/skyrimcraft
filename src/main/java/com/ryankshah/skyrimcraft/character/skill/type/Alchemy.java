package com.ryankshah.skyrimcraft.character.skill.type;

import com.ryankshah.skyrimcraft.character.skill.Skill;

import java.util.AbstractMap;

public class Alchemy extends Skill
{
    public Alchemy(int id, String name) {
        super(id, name);
    }

    @Override
    public String getDescription() {
        return "An alchemist can create magical potions and deadly poisons.";
    }

    @Override
    public AbstractMap.SimpleEntry<Integer, Integer> getIconUV() {
        return new AbstractMap.SimpleEntry<>(64, 128);
    }

    @Override
    public float getSkillUseMultiplier() {
        return 0.75f;
    }

    @Override
    public int getSkillUseOffset() {
        return 0;
    }

    @Override
    public float getSkillImproveMultiplier() {
        return 1.6f;
    }

    @Override
    public int getSkillImproveOffset() {
        return 65;
    }
}
