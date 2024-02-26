package com.ryankshah.skyrimcraft.character.skill.type;

import com.ryankshah.skyrimcraft.character.skill.Skill;

import java.util.AbstractMap;

public class Archery extends Skill
{
    public Archery(int id, String name) {
        super(id, name);
    }

    @Override
    public String getDescription() {
        return "An archer is trained in the use of bows and arrows. The greater the skill, the more deadly the shot.";
    }

    @Override
    public AbstractMap.SimpleEntry<Integer, Integer> getIconUV() {
        return new AbstractMap.SimpleEntry<>(192, 64);
    }

    @Override
    public float getSkillUseMultiplier() {
        return 9.3f;
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
