package com.ryankshah.skyrimcraft.character.attachment;

import com.mojang.serialization.Codec;
import com.ryankshah.skyrimcraft.Skyrimcraft;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public class PlayerAttachments
{
    public static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES = DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, Skyrimcraft.MODID);

    public static final Supplier<AttachmentType<Boolean>> HAS_SETUP = ATTACHMENT_TYPES.register(
            "has_setup", () -> AttachmentType.builder(() -> false).serialize(Codec.BOOL).copyOnDeath().build());

    public static final Supplier<AttachmentType<Float>> MAGICKA = ATTACHMENT_TYPES.register(
            "magicka", () -> AttachmentType.builder(() -> 20.0f).serialize(Codec.FLOAT).build());
    public static final Supplier<AttachmentType<Float>> MAX_MAGICKA = ATTACHMENT_TYPES.register(
            "max_magicka", () -> AttachmentType.builder(() -> 20.0f).serialize(Codec.FLOAT).copyOnDeath().build());
    public static final Supplier<AttachmentType<Float>> MAGICKA_REGEN_MODIFIER = ATTACHMENT_TYPES.register(
            "magicka_regen_modifier", () -> AttachmentType.builder(() -> 1.0f).serialize(Codec.FLOAT).copyOnDeath().build());

    public static final Supplier<AttachmentType<Integer>> CHARACTER_LEVEL = ATTACHMENT_TYPES.register(
            "character_level", () -> AttachmentType.builder(() -> 1).serialize(Codec.INT).copyOnDeath().build());
    public static final Supplier<AttachmentType<Integer>> CHARACTER_TOTAL_XP = ATTACHMENT_TYPES.register(
            "character_total_xp", () -> AttachmentType.builder(() -> 0).serialize(Codec.INT).copyOnDeath().build());

    public static final Supplier<AttachmentType<SpellHandler>> KNOWN_SPELLS = ATTACHMENT_TYPES.register(
            "known_spells", () -> AttachmentType.builder(SpellHandler::new).serialize(SpellHandler.CODEC).copyOnDeath().build());

    public static final Supplier<AttachmentType<CompassFeatureHandler>> COMPASS_FEATURES = ATTACHMENT_TYPES.register(
            "compass_features", () -> AttachmentType.serializable(() -> new CompassFeatureHandler()).copyOnDeath().build());

    public static final Supplier<AttachmentType<PlayerTargetsHandler>> PLAYER_TARGETS = ATTACHMENT_TYPES.register(
            "player_targets", () -> AttachmentType.serializable(PlayerTargetsHandler::new).copyOnDeath().build());

    public static final Supplier<AttachmentType<SkillsHandler>> SKILLS = ATTACHMENT_TYPES.register(
            "skills", () -> AttachmentType.serializable(() -> new SkillsHandler()).copyOnDeath().build());

}