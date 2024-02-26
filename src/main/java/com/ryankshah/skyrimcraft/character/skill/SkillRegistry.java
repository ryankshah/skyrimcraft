package com.ryankshah.skyrimcraft.character.skill;

import com.ryankshah.skyrimcraft.Skyrimcraft;
import com.ryankshah.skyrimcraft.character.skill.type.*;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class SkillRegistry
{
    public static final ResourceKey<Registry<Skill>> SKILLS_KEY = ResourceKey.createRegistryKey(new ResourceLocation(Skyrimcraft.MODID + ":skills_key"));
    public static final DeferredRegister<Skill> SKILLS = DeferredRegister.create(SKILLS_KEY, Skyrimcraft.MODID);
    public static final Registry<Skill> SKILLS_REGISTRY = SKILLS.makeRegistry(builder -> builder.sync(true).defaultKey(new ResourceLocation(Skyrimcraft.MODID, "empty")));

    public static final int BASE_ARCHERY_XP = 4;
    public static final int BASE_BLOCK_XP = 4;
    public static final int BASE_PICKPOCKET_XP = 4;
    public static final int BASE_ALCHEMY_XP = 4;

    public static Supplier<Skill> ALTERATION = SKILLS.register("alteration", () -> new Alteration(0, "Alteration"));
    public static Supplier<Skill> CONJURATION = SKILLS.register("conjuration", () -> new Conjuration(1, "Conjuration"));
    public static Supplier<Skill> DESTRUCTION = SKILLS.register("destruction", () -> new Destruction(2, "Destruction"));
    public static Supplier<Skill> ILLUSION = SKILLS.register("illusion", () -> new Illusion(3, "Illusion"));
    public static Supplier<Skill> RESTORATION = SKILLS.register("restoration", () -> new Restoration(4, "Restoration"));
    public static Supplier<Skill> ENCHANTING = SKILLS.register("enchanting", () -> new Enchanting(5, "Enchanting"));
    public static Supplier<Skill> ONE_HANDED = SKILLS.register("one_handed", () -> new OneHanded(6, "One-Handed"));
    public static Supplier<Skill> TWO_HANDED = SKILLS.register("two_handed", () -> new TwoHanded(7, "Two-Handed"));
    public static Supplier<Skill> ARCHERY = SKILLS.register("archery", () -> new Archery(8, "Archery"));
    public static Supplier<Skill> BLOCK = SKILLS.register("block", () -> new Block(9, "Block"));
    public static Supplier<Skill> SMITHING = SKILLS.register("smithing", () -> new Smithing(10, "Smithing"));
    public static Supplier<Skill> HEAVY_ARMOR = SKILLS.register("heavy_armor", () -> new HeavyArmor(11, "Heavy Armor"));
    public static Supplier<Skill> LIGHT_ARMOR = SKILLS.register("light_armor", () -> new LightArmor(12, "Light Armor"));
    public static Supplier<Skill> PICKPOCKET = SKILLS.register("pickpocket", () -> new Pickpocket(13, "Pickpocket"));
    public static Supplier<Skill> LOCKPICKING = SKILLS.register("lockpicking", () -> new Lockpicking(14, "Lockpicking"));
    public static Supplier<Skill> SNEAK = SKILLS.register("sneak", () -> new Sneak(15, "Sneak"));
    public static Supplier<Skill> ALCHEMY = SKILLS.register("alchemy", () -> new Alchemy(16, "Alchemy"));
    public static Supplier<Skill> SPEECH = SKILLS.register("speech", () -> new Speech(17, "Speech"));
}