package com.ryankshah.skyrimcraft.character.skill.type;

import com.ryankshah.skyrimcraft.character.skill.Skill;

import java.util.AbstractMap;

public class Pickpocket extends Skill
{
    public Pickpocket(int id, String name) {
        super(id, name);
    }

    @Override
    public String getDescription() {
        return "The stealthy art of picking an unsuspecting target's pockets. A skilled pickpocketer is less likely to be caught and is more likely to loot valuables.";
    }

    @Override
    public AbstractMap.SimpleEntry<Integer, Integer> getIconUV() {
        return new AbstractMap.SimpleEntry<>(256, 64);
    }

    @Override
    public float getSkillUseMultiplier() {
        return 8.1f;
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
        return 250;
    }
}
