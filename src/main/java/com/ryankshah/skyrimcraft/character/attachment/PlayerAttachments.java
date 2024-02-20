package com.ryankshah.skyrimcraft.character.attachment;

import com.mojang.serialization.Codec;
import com.ryankshah.skyrimcraft.Skyrimcraft;
import com.ryankshah.skyrimcraft.character.magic.Spell;
import com.ryankshah.skyrimcraft.network.character.UpdateCharacter;
import com.ryankshah.skyrimcraft.network.spell.UpdateMagicka;
import com.ryankshah.skyrimcraft.network.spell.UpdateSpellHandlerOnClient;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class PlayerAttachments
{
    public static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES = DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, Skyrimcraft.MODID);

    public static final Supplier<AttachmentType<Character>> CHARACTER = ATTACHMENT_TYPES.register(
            "character", () -> AttachmentType.builder(Character::new).serialize(Character.CODEC).copyOnDeath().build());



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
            "known_spells", () -> AttachmentType.builder(SpellHandler::new).serialize(SpellHandler.SPELL_HANDLER_CODEC).copyOnDeath().build());

    public static final Supplier<AttachmentType<CompassFeatureHandler>> COMPASS_FEATURES = ATTACHMENT_TYPES.register(
            "compass_features", () -> AttachmentType.serializable(() -> new CompassFeatureHandler()).copyOnDeath().build());

    public static final Supplier<AttachmentType<PlayerTargetsHandler>> PLAYER_TARGETS = ATTACHMENT_TYPES.register(
            "player_targets", () -> AttachmentType.serializable(PlayerTargetsHandler::new).copyOnDeath().build());

    public static final Supplier<AttachmentType<SkillsHandler>> SKILLS = ATTACHMENT_TYPES.register(
            "skills", () -> AttachmentType.serializable(() -> new SkillsHandler()).copyOnDeath().build());


    public static void registerSyncEvents(IEventBus modEventBus) {
        Character.register(modEventBus);
//        NeoForge.EVENT_BUS.register(new AttachmentEvents());
//
//        SpellHandler.register(modEventBus);
//        SkillsHandler.register(modEventBus);
//        CompassFeatureHandler.register(modEventBus);
    }

    private static class AttachmentEvents
    {
        protected void sync(Player player)
        {

        }

        @SubscribeEvent
        public void attachCapabilities(EntityJoinLevelEvent event)
        {
        }

        @SubscribeEvent
        public void joinWorld(PlayerEvent.PlayerLoggedInEvent event)
        {
            Player target = event.getEntity();
            if (target.level().isClientSide)
                return;
            sync(target);
        }

        @SubscribeEvent
        public void joinWorld(PlayerEvent.PlayerChangedDimensionEvent event)
        {
            Player target = event.getEntity();
            if (target.level().isClientSide)
                return;
            sync(target);
        }

        @SubscribeEvent
        public void track(PlayerEvent.StartTracking event)
        {
            Entity target = event.getTarget();
            if (target.level().isClientSide)
                return;
            if (target instanceof Player player)
                sync(player);
        }

        @SubscribeEvent
        public void playerClone(PlayerEvent.Clone event) {
            Player oldPlayer = event.getOriginal();
            Player newPlayer = event.getEntity();

            newPlayer.setData(PlayerAttachments.MAGICKA, oldPlayer.getData(PlayerAttachments.MAGICKA));
            newPlayer.setData(PlayerAttachments.MAX_MAGICKA, oldPlayer.getData(PlayerAttachments.MAX_MAGICKA));
            newPlayer.setData(PlayerAttachments.MAGICKA_REGEN_MODIFIER, oldPlayer.getData(PlayerAttachments.MAGICKA_REGEN_MODIFIER));

            PacketDistributor.PLAYER.with((ServerPlayer) newPlayer).send(new UpdateMagicka(
                    newPlayer.getData(PlayerAttachments.MAGICKA),
                    newPlayer.getData(PlayerAttachments.MAX_MAGICKA),
                    newPlayer.getData(PlayerAttachments.MAGICKA_REGEN_MODIFIER)
            ));

            PacketDistributor.PLAYER.with((ServerPlayer) newPlayer).send(new UpdateCharacter(
                    newPlayer.getData(PlayerAttachments.HAS_SETUP),
                    newPlayer.getData(PlayerAttachments.CHARACTER_LEVEL),
                    newPlayer.getData(PlayerAttachments.CHARACTER_TOTAL_XP)
            ));
        }
    }
}