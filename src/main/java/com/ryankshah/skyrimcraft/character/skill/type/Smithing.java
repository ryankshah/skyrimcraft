package com.ryankshah.skyrimcraft.character.skill.type;

import com.ryankshah.skyrimcraft.character.skill.Skill;

import java.util.AbstractMap;

public class Smithing extends Skill
{
    public Smithing(int id, String name) {
        super(id, name);
    }

    @Override
    public String getDescription() {
        return "The art of creating weapons and armor from raw materials, or improving non-magical weapons and armor.";
    }

    @Override
    public AbstractMap.SimpleEntry<Integer, Integer> getIconUV() {
        return new AbstractMap.SimpleEntry<>(448,0);
    }

    @Override
    public float getSkillUseMultiplier() {
        return 1f;
    }

    @Override
    public int getSkillUseOffset() {
        return 0;
    }

    @Override
    public float getSkillImproveMultiplier() {
        return 0.25f;
    }

    @Override
    public int getSkillImproveOffset() {
        return 300;
    }
}
