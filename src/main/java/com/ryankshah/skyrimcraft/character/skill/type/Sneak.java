package com.ryankshah.skyrimcraft.character.skill.type;

import com.ryankshah.skyrimcraft.character.skill.Skill;

import java.util.AbstractMap;

public class Sneak extends Skill
{
    public Sneak(int id, String name) {
        super(id, name);
    }

    @Override
    public String getDescription() {
        return "Sneaking is the art of moving unseen and unheard. Highly skilled sneaks can even hide in plain sight.";
    }

    @Override
    public AbstractMap.SimpleEntry<Integer, Integer> getIconUV() {
        return new AbstractMap.SimpleEntry<>(448, 64);
    }

    @Override
    public float getSkillUseMultiplier() {
        return 11.25f;
    }

    @Override
    public int getSkillUseOffset() {
        return 0;
    }

    @Override
    public float getSkillImproveMultiplier() {
        return 0.5f;
    }

    @Override
    public int getSkillImproveOffset() {
        return 120;
    }
}
