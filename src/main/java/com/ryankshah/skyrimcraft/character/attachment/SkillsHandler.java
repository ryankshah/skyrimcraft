package com.ryankshah.skyrimcraft.character.attachment;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.ryankshah.skyrimcraft.character.feature.Race;
import com.ryankshah.skyrimcraft.character.skill.Skill;
import com.ryankshah.skyrimcraft.character.skill.SkillRegistry;
import com.ryankshah.skyrimcraft.network.character.UpdateSkillHandlerOnClient;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.common.util.INBTSerializable;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.network.PacketDistributor;

import java.util.HashMap;
import java.util.Map;

public class SkillsHandler implements INBTSerializable<CompoundTag>
{
    private Map<Integer, Skill> skills;
    private Race race;

    public static Codec<SkillsHandler> SKILL_HANDLER_CODEC = RecordCodecBuilder.create(skillsHandlerInstance -> skillsHandlerInstance.group(
            Codec.unboundedMap(Codec.INT, Skill.SKILL_CODEC).fieldOf("skills").forGetter(SkillsHandler::getSkills),
            Race.RACE_CODEC.fieldOf("race").forGetter(SkillsHandler::getRace)
    ).apply(skillsHandlerInstance, SkillsHandler::new));

    public SkillsHandler() {
        this(new HashMap<>(), Race.NORD);
    }

    public SkillsHandler(Map<Integer, Skill> skills, Race race) {
        this.skills = skills;
        this.race = race;
        this.skills = getStartingSkills(race);
//        skills = race.getStartingSkills();
    }

    public SkillsHandler(Race r) {
        this.race = r;
        this.skills = getStartingSkills(r);
    }

    public Map<Integer, Skill> getStartingSkills(Race race) {
        if (race.equals(Race.ALTMER)) return createStartingSkillsFromStartingLevels(15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 25, 20, 20, 20, 20, 20);
        else if (race.equals(Race.ARGONIAN)) return createStartingSkillsFromStartingLevels(15, 15, 15, 15, 15, 15, 20, 20, 25, 20, 15, 15, 15, 15, 15, 20, 20, 15);
        else if (race.equals(Race.BOSMER)) return createStartingSkillsFromStartingLevels(15, 15, 15, 15, 15, 25, 20, 20, 20, 20, 15, 15, 15, 15, 15, 20, 20, 15);
        else if (race.equals(Race.BRETON)) return createStartingSkillsFromStartingLevels(15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 20, 20, 20, 25, 15, 20, 20, 15);
        else if (race.equals(Race.DUNMER)) return createStartingSkillsFromStartingLevels(15, 15, 15, 15, 15, 15, 20, 20, 15, 15, 15, 20, 20, 15, 25, 15, 20, 15);
        else if (race.equals(Race.IMPERIAL)) return createStartingSkillsFromStartingLevels(15, 20, 20, 15, 20, 15, 15, 15, 15, 15, 15, 15, 15, 15, 20, 25, 15, 20);
        else if (race.equals(Race.KHAJIIT)) return createStartingSkillsFromStartingLevels(15, 15, 15, 15, 20, 20, 15, 25, 20, 20, 15, 20, 15, 15, 15, 15, 15, 15);
        else if (race.equals(Race.NORD)) return createStartingSkillsFromStartingLevels(20, 15, 20, 25, 20, 15, 20, 15, 15, 15, 20, 15, 15, 15, 15, 15, 15, 15);
        else if (race.equals(Race.ORSIMER)) return createStartingSkillsFromStartingLevels(20, 25, 20, 20, 20, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 20);
        else if (race.equals(Race.REDGUARD)) return createStartingSkillsFromStartingLevels(20, 15, 20, 15, 25, 20, 15, 15, 15, 15, 15, 15, 15, 15, 20, 15, 20, 15);
        else return createStartingSkillsFromStartingLevels(20, 15, 20, 25, 20, 15, 20, 15, 15, 15, 20, 15, 15, 15, 15, 15, 15, 15);
    }

    private static Map<Integer, Skill> createStartingSkillsFromStartingLevels(
            int smithing, int heavyarmor, int block, int twohand, int onehand,
            int archery, int lightarmor, int sneak, int lockpick, int pickpocket,
            int speech, int alchemy, int illusion, int conj, int destruct,
            int restoration, int alteration, int enchanting
    ) {
        Map<Integer, Skill> skills = new HashMap<>();
        Skill SMITHING = new Skill(SkillRegistry.SMITHING);
        Skill HEAVY = new Skill(SkillRegistry.HEAVY_ARMOR);
        Skill BLOCK = new Skill(SkillRegistry.BLOCK);
        Skill TWOHAND = new Skill(SkillRegistry.TWO_HANDED);
        Skill ONEHAND = new Skill(SkillRegistry.ONE_HANDED);
        Skill ARCHERY = new Skill(SkillRegistry.ARCHERY);
        Skill LIGHT = new Skill(SkillRegistry.LIGHT_ARMOR);
        Skill SNEAK = new Skill(SkillRegistry.SNEAK);
        Skill LOCKPICK = new Skill(SkillRegistry.LOCKPICKING);
        Skill PICKPOCKET = new Skill(SkillRegistry.PICKPOCKET);
        Skill SPEECH = new Skill(SkillRegistry.SPEECH);
        Skill ALCHEMY = new Skill(SkillRegistry.ALCHEMY);
        Skill ILLUSION = new Skill(SkillRegistry.ILLUSION);
        Skill CONJ = new Skill(SkillRegistry.CONJURATION);
        Skill DESTRUCT = new Skill(SkillRegistry.DESTRUCTION);
        Skill RESTORATION = new Skill(SkillRegistry.RESTORATION);
        Skill ALTERATION = new Skill(SkillRegistry.ALTERATION);
        Skill ENCHANTING = new Skill(SkillRegistry.ENCHANTING);

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

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();

        tag.putInt("skillsSize", skills.size());
        for(Map.Entry<Integer, Skill> skill : skills.entrySet()) {
            tag.put("skill"+skill.getKey(), skill.getValue().serialise());
        }

        tag.putInt("raceID", race.getId());
        tag.putString("raceName", race.getName());

        return tag;
    }

    public static void register(IEventBus modEventBus) {
        NeoForge.EVENT_BUS.register(new SkillHandlerEvents());
    }

    public Map<Integer, Skill> getSkills() {
        return skills;
    }

    public Race getRace() {
        return race;
    }

    @Override
    public void deserializeNBT(CompoundTag tag) {
        int raceID = tag.getInt("raceID");
        String raceName = tag.getString("raceName");

        Map<Integer, Skill> skillsList = new HashMap<>();
        int skillsSize = tag.getInt("skillsSize");
        for(int i = 0; i < skillsSize; i++) {
            CompoundTag comp = tag.getCompound("skill" + i);
            skillsList.put(i, Skill.deserialise(comp));
        }

        skills = skillsList;
        race = new Race(raceID, raceName); //, skillsList);
    }

    public static SkillsHandler get(LivingEntity player) {
        return player.getData(PlayerAttachments.SKILLS);
    }

    private void syncToSelf(Player owner) {
        syncTo(owner);
    }

    protected void syncTo(Player owner) {
        PacketDistributor.PLAYER.with((ServerPlayer) owner).send(new UpdateSkillHandlerOnClient(this));
    }

    protected void syncTo(PacketDistributor.PacketTarget target, Player owner) {
        target.send(new UpdateSkillHandlerOnClient(this));
    }

    private static class SkillHandlerEvents
    {
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
            get(target).syncToSelf(target);
        }

        @SubscribeEvent
        public void joinWorld(PlayerEvent.PlayerChangedDimensionEvent event)
        {
            Player target = event.getEntity();
            if (target.level().isClientSide)
                return;
            get(target).syncToSelf(target);
        }

        @SubscribeEvent
        public void track(PlayerEvent.StartTracking event)
        {
            Entity target = event.getTarget();
            if (target.level().isClientSide)
                return;
            if (target instanceof Player)
            {
                get((Player)target).syncToSelf((Player)target);
            }
        }

        @SubscribeEvent
        public void playerClone(PlayerEvent.Clone event) {
            Player oldPlayer = event.getOriginal();
            Player newPlayer = event.getEntity();
            var oldHandler = get(oldPlayer);
            var newHandler = get(newPlayer);

            newHandler.race = oldHandler.race;
            newHandler.skills = oldHandler.skills;

            newPlayer.setData(PlayerAttachments.SKILLS, newHandler);
            PacketDistributor.PLAYER.with((ServerPlayer) newPlayer).send(new UpdateSkillHandlerOnClient(newHandler));
        }
    }
}
