package com.ryankshah.skyrimcraft.character.skill.type;

import com.ryankshah.skyrimcraft.character.skill.Skill;

import java.util.AbstractMap;

public class Enchanting extends Skill
{
    public Enchanting(int id, String name) {
        super(id, name);
    }

    @Override
    public String getDescription() {
        return "The more powerful the enchanter, the stronger the magic he can bind to his weapons and armor.";
    }

    @Override
    public AbstractMap.SimpleEntry<Integer, Integer> getIconUV() {
        return new AbstractMap.SimpleEntry<>(320,0);
    }

    @Override
    public float getSkillUseMultiplier() {
        return 900f;
    }

    @Override
    public int getSkillUseOffset() {
        return 0;
    }

    @Override
    public float getSkillImproveMultiplier() {
        return 1f;
    }

    @Override
    public int getSkillImproveOffset() {
        return 170;
    }
}
