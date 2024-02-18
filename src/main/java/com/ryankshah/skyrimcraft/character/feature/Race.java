package com.ryankshah.skyrimcraft.character.feature;

import com.ryankshah.skyrimcraft.character.skill.ISkill;
import com.ryankshah.skyrimcraft.character.skill.SkillRegistry;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Race
{
    public static Race ALTMER = new Race(1, "High Elf", createStartingSkillsFromStartingLevels(15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 25, 20, 20, 20, 20, 20));
    public static Race ARGONIAN = new Race(2, "Argonian", createStartingSkillsFromStartingLevels(15, 15, 15, 15, 15, 15, 20, 20, 25, 20, 15, 15, 15, 15, 15, 20, 20, 15));
    public static Race BOSMER = new Race(3, "Wood Elf", createStartingSkillsFromStartingLevels(15, 15, 15, 15, 15, 25, 20, 20, 20, 20, 15, 15, 15, 15, 15, 20, 20, 15));
    public static Race BRETON = new Race(4, "Breton", createStartingSkillsFromStartingLevels(15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 20, 20, 20, 25, 15, 20, 20, 15));
    public static Race DUNMER = new Race(5, "Dark Elf", createStartingSkillsFromStartingLevels(15, 15, 15, 15, 15, 15, 20, 20, 15, 15, 15, 20, 20, 15, 25, 15, 20, 15));
    public static Race IMPERIAL = new Race(6, "Imperial", createStartingSkillsFromStartingLevels(15, 20, 20, 15, 20, 15, 15, 15, 15, 15, 15, 15, 15, 15, 20, 25, 15, 20));
    public static Race KHAJIIT = new Race(7, "Khajiit", createStartingSkillsFromStartingLevels(15, 15, 15, 15, 20, 20, 15, 25, 20, 20, 15, 20, 15, 15, 15, 15, 15, 15));
    public static Race NORD = new Race(8, "Nord", createStartingSkillsFromStartingLevels(20, 15, 20, 25, 20, 15, 20, 15, 15, 15, 20, 15, 15, 15, 15, 15, 15, 15));
    public static Race ORSIMER = new Race(9, "Orc", createStartingSkillsFromStartingLevels(20, 25, 20, 20, 20, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 20));
    public static Race REDGUARD = new Race(10, "Redguard", createStartingSkillsFromStartingLevels(20, 15, 20, 15, 25, 20, 15, 15, 15, 15, 15, 15, 15, 15, 20, 15, 20, 15));

    private int id;
    private String name;
    private Map<Integer, ISkill> startingSkills;

    public Race(int id, String name, Map<Integer, ISkill> startingSkills) {
        this.id = id;
        this.name = name;
        this.startingSkills = startingSkills;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Map<Integer, ISkill> getStartingSkills() {
        return startingSkills;
    }

    public static List<Race> getRaces() {
        return Arrays.asList(ALTMER, ARGONIAN, BOSMER, BRETON, DUNMER, IMPERIAL, KHAJIIT, NORD, ORSIMER, REDGUARD);
    }

    private static Map<Integer, ISkill> createStartingSkillsFromStartingLevels(
            int smithing, int heavyarmor, int block, int twohand, int onehand,
            int archery, int lightarmor, int sneak, int lockpick, int pickpocket,
            int speech, int alchemy, int illusion, int conj, int destruct,
            int restoration, int alteration, int enchanting
    ) {
        Map<Integer, ISkill> skills = new HashMap<>();
        ISkill SMITHING = new ISkill(SkillRegistry.SMITHING);
        ISkill HEAVY = new ISkill(SkillRegistry.HEAVY_ARMOR);
        ISkill BLOCK = new ISkill(SkillRegistry.BLOCK);
        ISkill TWOHAND = new ISkill(SkillRegistry.TWO_HANDED);
        ISkill ONEHAND = new ISkill(SkillRegistry.ONE_HANDED);
        ISkill ARCHERY = new ISkill(SkillRegistry.ARCHERY);
        ISkill LIGHT = new ISkill(SkillRegistry.LIGHT_ARMOR);
        ISkill SNEAK = new ISkill(SkillRegistry.SNEAK);
        ISkill LOCKPICK = new ISkill(SkillRegistry.LOCKPICKING);
        ISkill PICKPOCKET = new ISkill(SkillRegistry.PICKPOCKET);
        ISkill SPEECH = new ISkill(SkillRegistry.SPEECH);
        ISkill ALCHEMY = new ISkill(SkillRegistry.ALCHEMY);
        ISkill ILLUSION = new ISkill(SkillRegistry.ILLUSION);
        ISkill CONJ = new ISkill(SkillRegistry.CONJURATION);
        ISkill DESTRUCT = new ISkill(SkillRegistry.DESTRUCTION);
        ISkill RESTORATION = new ISkill(SkillRegistry.RESTORATION);
        ISkill ALTERATION = new ISkill(SkillRegistry.ALTERATION);
        ISkill ENCHANTING = new ISkill(SkillRegistry.ENCHANTING);

        SMITHING.setLevel(smithing);
        HEAVY.setLevel(heavyarmor);
        BLOCK.setLevel(block);
        TWOHAND.setLevel(twohand);
        ONEHAND.setLevel(onehand);
        ARCHERY.setLevel(archery);
        LIGHT.setLevel(lightarmor);
        SNEAK.setLevel(sneak);
        LOCKPICK.setLevel(lockpick);
        PICKPOCKET.setLevel(pickpocket);
        SPEECH.setLevel(speech);
        ALCHEMY.setLevel(alchemy);
        ILLUSION.setLevel(illusion);
        CONJ.setLevel(conj);
        DESTRUCT.setLevel(destruct);
        RESTORATION.setLevel(restoration);
        ALTERATION.setLevel(alteration);
        ENCHANTING.setLevel(enchanting);

        skills.put(ALTERATION.getID(), ALTERATION);
        skills.put(CONJ.getID(), CONJ);
        skills.put(DESTRUCT.getID(), DESTRUCT);
        skills.put(ILLUSION.getID(), ILLUSION);
        skills.put(RESTORATION.getID(), RESTORATION);
        skills.put(ENCHANTING.getID(), ENCHANTING);
        skills.put(ONEHAND.getID(), ONEHAND);
        skills.put(TWOHAND.getID(), TWOHAND);
        skills.put(ARCHERY.getID(), ARCHERY);
        skills.put(BLOCK.getID(), BLOCK);
        skills.put(SMITHING.getID(), SMITHING);
        skills.put(HEAVY.getID(), HEAVY);
        skills.put(LIGHT.getID(), LIGHT);
        skills.put(PICKPOCKET.getID(), PICKPOCKET);
        skills.put(LOCKPICK.getID(), LOCKPICK);
        skills.put(SNEAK.getID(), SNEAK);
        skills.put(ALCHEMY.getID(), ALCHEMY);
        skills.put(SPEECH.getID(), SPEECH);

        return skills;
    }
}