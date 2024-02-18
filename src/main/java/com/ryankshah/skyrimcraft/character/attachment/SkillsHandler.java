package com.ryankshah.skyrimcraft.character.attachment;

import com.ryankshah.skyrimcraft.character.feature.Race;
import com.ryankshah.skyrimcraft.character.skill.ISkill;
import net.minecraft.nbt.CompoundTag;
import net.neoforged.neoforge.common.util.INBTSerializable;

import java.util.HashMap;
import java.util.Map;

public class SkillsHandler implements INBTSerializable<CompoundTag>
{
    private Map<Integer, ISkill> skills;
    private Race race;

    public SkillsHandler() {
        this.skills = new HashMap<>();
        this.race = Race.NORD;
        skills = race.getStartingSkills();
    }

    public SkillsHandler(Map<Integer, ISkill> skillsList) {
        this.skills = skillsList;
    }

    public SkillsHandler(Race r) {
        this.race = r;
        this.skills = r.getStartingSkills();
    }

    public SkillsHandler(Map<Integer, ISkill> skillsList, Race r) {
        this.skills = skillsList;
        this.race = r;
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();

        tag.putInt("skillsSize", skills.size());
        for(Map.Entry<Integer, ISkill> skill : skills.entrySet()) {
            tag.put("skill"+skill.getKey(), skill.getValue().serialise());
        }

        tag.putInt("raceID", race.getId());
        tag.putString("raceName", race.getName());

        return tag;
    }

    public Map<Integer, ISkill> getSkills() {
        return skills;
    }

    public Race getRace() {
        return race;
    }

    @Override
    public void deserializeNBT(CompoundTag tag) {
        int raceID = tag.getInt("raceID");
        String raceName = tag.getString("raceName");

        Map<Integer, ISkill> skillsList = new HashMap<>();
        int skillsSize = tag.getInt("skillsSize");
        for(int i = 0; i < skillsSize; i++) {
            CompoundTag comp = tag.getCompound("skill" + i);
            skillsList.put(i, ISkill.deserialise(comp));
        }

        skills = skillsList;
        race = new Race(raceID, raceName, skillsList);
    }
}
