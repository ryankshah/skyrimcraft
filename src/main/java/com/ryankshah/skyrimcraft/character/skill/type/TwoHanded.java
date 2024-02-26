package com.ryankshah.skyrimcraft.character.skill.type;

import com.ryankshah.skyrimcraft.character.skill.Skill;

import java.util.AbstractMap;

public class TwoHanded extends Skill
{
    public TwoHanded(int id, String name) {
        super(id, name);
    }

    @Override
    public String getDescription() {
        return "The art of combat using two-handed weapons, such as greatswords, battle axes, and warhammers. Those trained in this skill deliver deadlier blows.";
    }

    @Override
    public AbstractMap.SimpleEntry<Integer, Integer> getIconUV() {
        return new AbstractMap.SimpleEntry<>(0,64);
    }

    @Override
    public float getSkillUseMultiplier() {
        return 5.95f;
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