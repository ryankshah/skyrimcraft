package com.ryankshah.skyrimcraft.character.skill;

import com.google.common.collect.ImmutableList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SkillRegistry
{
//    public static final DeferredRegister<ISkill> SKILL_REGISTRY = DeferredRegister.makeRegistry(() -> RegistrySetBuilder.);
//    public static final ResourceKey<Registry<ISkill>> SKILL_TYPES_KEY = ResourceKey.createRegistryKey(new ResourceLocation(Skyrimcraft.MODID, "skills"));
//    public static final Registry<ISkill> SKILL_TYPES = new RegistryBuilder<>(SKILL_TYPES_KEY).create();
//    public static final DeferredRegister<ISkill> SKILLS = DeferredRegister.create(SKILL_TYPES, Skyrimcraft.MODID);

    public static final int BASE_ARCHERY_XP = 4;
    public static final int BASE_BLOCK_XP = 4;
    public static final int BASE_PICKPOCKET_XP = 4;

    public static Skill ALTERATION = new Skill(0, "Alteration", 15, 3f, 0, 2f, 0);
    public static Skill CONJURATION = new Skill(1, "Conjuration", 15, 2.1f, 0, 2f, 0);
    public static Skill DESTRUCTION = new Skill(2, "Destruction", 15, 1.35f, 0, 2f, 0);
    public static Skill ILLUSION = new Skill(3, "Illusion", 15, 4.6f, 0, 2f, 0);
    public static Skill RESTORATION = new Skill(4, "Restoration", 15, 2.1f, 0, 2f, 0);
    public static Skill ENCHANTING = new Skill(5, "Enchanting", 15, 900f, 0, 1f, 170);
    public static Skill ONE_HANDED = new Skill(6, "One-Handed", 15, 6.3f, 0, 2f, 0);
    public static Skill TWO_HANDED = new Skill(7, "Two-Handed", 15, 5.95f, 0, 2f, 0);
    public static Skill ARCHERY = new Skill(8, "Archery", 15, 9.3f, 0, 2f, 0);
    public static Skill BLOCK = new Skill(9, "Block", 15, 8.1f, 0, 2f, 0);
    public static Skill SMITHING = new Skill(10, "Smithing", 15, 1f, 0, 0.25f, 300);
    public static Skill HEAVY_ARMOR = new Skill(11, "Heavy Armor", 15, 3.8f, 0, 2f, 0);
    public static Skill LIGHT_ARMOR = new Skill(12, "Light Armor", 15, 4f, 0, 2f, 0);
    public static Skill PICKPOCKET = new Skill(13, "Pickpocket", 15, 8.1f, 0, 0.25f, 250);
    public static Skill LOCKPICKING = new Skill(14, "Lockpicking", 15, 45f, 10, 0.25f, 300);
    public static Skill SNEAK = new Skill(15, "Sneak", 15, 11.25f, 0, 0.5f, 120);
    public static Skill ALCHEMY = new Skill(16, "Alchemy", 15, 0.75f, 0, 1.6f, 65);
    public static Skill SPEECH = new Skill(17, "Speech", 15, 0.36f, 0, 2f, 0);

    public static Map<Integer, Skill> getDefaults() {
        Map<Integer, Skill> skills = new HashMap<>();

        skills.put(ALTERATION.getID(), ALTERATION);
        skills.put(CONJURATION.getID(), CONJURATION);
        skills.put(DESTRUCTION.getID(), DESTRUCTION);
        skills.put(ILLUSION.getID(), ILLUSION);
        skills.put(RESTORATION.getID(), RESTORATION);
        skills.put(ENCHANTING.getID(), ENCHANTING);
        skills.put(ONE_HANDED.getID(), ONE_HANDED);
        skills.put(TWO_HANDED.getID(), TWO_HANDED);
        skills.put(ARCHERY.getID(), ARCHERY);
        skills.put(BLOCK.getID(), BLOCK);
        skills.put(SMITHING.getID(), SMITHING);
        skills.put(HEAVY_ARMOR.getID(), HEAVY_ARMOR);
        skills.put(LIGHT_ARMOR.getID(), LIGHT_ARMOR);
        skills.put(PICKPOCKET.getID(), PICKPOCKET);
        skills.put(LOCKPICKING.getID(), LOCKPICKING);
        skills.put(SNEAK.getID(), SNEAK);
        skills.put(ALCHEMY.getID(), ALCHEMY);
        skills.put(SPEECH.getID(), SPEECH);

        return skills;
    }

    public static List<Integer> getKnownSkillIds() {
        return ImmutableList.of(
                ALTERATION.getID(),
                CONJURATION.getID(),
                DESTRUCTION.getID(),
                ILLUSION.getID(),
                RESTORATION.getID(),
                ENCHANTING.getID(),
                ONE_HANDED.getID(),
                TWO_HANDED.getID(),
                ARCHERY.getID(),
                BLOCK.getID(),
                SMITHING.getID(),
                HEAVY_ARMOR.getID(),
                LIGHT_ARMOR.getID(),
                PICKPOCKET.getID(),
                LOCKPICKING.getID(),
                SNEAK.getID(),
                ALCHEMY.getID(),
                SPEECH.getID()
        );
    }
}