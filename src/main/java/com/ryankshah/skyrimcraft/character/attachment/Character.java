package com.ryankshah.skyrimcraft.character.attachment;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.ryankshah.skyrimcraft.character.feature.Race;
import com.ryankshah.skyrimcraft.character.magic.Spell;
import com.ryankshah.skyrimcraft.character.magic.SpellRegistry;
import com.ryankshah.skyrimcraft.character.skill.Skill;
import com.ryankshah.skyrimcraft.character.skill.SkillRegistry;
import com.ryankshah.skyrimcraft.network.character.UpdateCharacter;
import com.ryankshah.skyrimcraft.util.CompassFeature;
import net.minecraft.nbt.NbtOps;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.attachment.IAttachmentHolder;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.network.PacketDistributor;

import javax.annotation.Nullable;
import java.util.*;

public class Character
{
    public static Codec<Character> CODEC = RecordCodecBuilder.create(characterInstance -> characterInstance.group(
            Codec.BOOL.fieldOf("hasSetup").forGetter(Character::getHasSetup),
            Codec.FLOAT.fieldOf("magicka").forGetter(Character::getMagicka),
            Codec.FLOAT.fieldOf("maxMagicka").forGetter(Character::getMaxMagicka),
            Codec.FLOAT.fieldOf("magickaRegenModifier").forGetter(Character::getMagickaRegenModifier),
            Codec.INT.fieldOf("characterLevel").forGetter(Character::getCharacterLevel),
            Codec.INT.fieldOf("characterTotalXp").forGetter(Character::getCharacterTotalXp),
            Codec.unboundedMap(Codec.STRING.comapFlatMap(
                    s -> s.matches("-?\\d+") ? DataResult.success(Integer.parseInt(s)) : DataResult.error(() -> "Key is not an integer"),
                    i -> Integer.toString(i)
                ), Skill.SKILL_CODEC).fieldOf("skills").forGetter(Character::getSkills),
            Race.RACE_CODEC.fieldOf("race").forGetter(Character::getRace),
            SpellRegistry.SPELLS_REGISTRY.byNameCodec().listOf().fieldOf("knownSpells").forGetter(Character::getKnownSpells),
            SpellRegistry.SPELLS_REGISTRY.byNameCodec().fieldOf("selectedSpell1").forGetter(Character::getSelectedSpell1),
            SpellRegistry.SPELLS_REGISTRY.byNameCodec().fieldOf("selectedSpell2").forGetter(Character::getSelectedSpell2),
            Codec.unboundedMap(SpellRegistry.SPELLS_REGISTRY.byNameCodec(), Codec.FLOAT).fieldOf("spellsOnCooldown").forGetter(Character::getSpellsOnCooldown),
            CompassFeature.CODEC.listOf().fieldOf("compassFeatures").forGetter(Character::getCompassFeatures),
            Codec.INT.listOf().fieldOf("targetingEntities").forGetter(Character::getTargets),
            Codec.INT.fieldOf("currentTarget").forGetter(Character::getCurrentTarget)
    ).apply(characterInstance, Character::new));

    private boolean hasSetup;
    private float magicka, maxMagicka, magickaRegenModifier;
    private int characterLevel, characterTotalXp;

    private Map<Integer, Skill> skills;
    private Race race;

    private List<Spell> knownSpells;
    private Spell selectedSpell1;
    private Spell selectedSpell2;
    private Map<Spell, Float> spellsOnCooldown;

    private List<CompassFeature> compassFeatures;

    private List<Integer> targetingEntities;
    private int currentTarget;

    public Character(IAttachmentHolder p) {
        this(
                false,
                20.0f,
                20.0f,
                1.0f,
                1,
                0,
                new HashMap<>(),
                Race.NORD,
                new ArrayList<>(),
                SpellRegistry.EMPTY_SPELL.get(),
                SpellRegistry.EMPTY_SPELL.get(),
                new HashMap<>(),
                new ArrayList<>(),
                new ArrayList<>(),
                -1
        );
    }

    public Character(
            boolean hasSetup,
            float magicka, float maxMagicka, float magickaRegenModifier,
            int characterLevel, int characterTotalXp,
            Map<Integer, Skill> skills, Race race,
            List<Spell> spells, Spell selectedSpell1, Spell selectedSpell2, Map<Spell, Float> cooldowns,
            List<CompassFeature> features,
            List<Integer> targets, int current
            ) {
        this.hasSetup = hasSetup;
        this.magicka = magicka;
        this.maxMagicka = maxMagicka;
        this.magickaRegenModifier = magickaRegenModifier;
        this.characterLevel = characterLevel;
        this.characterTotalXp = characterTotalXp;
        this.knownSpells = new ArrayList<>(spells);
        this.selectedSpell1 = selectedSpell1;
        this.selectedSpell2 = selectedSpell2;
        this.spellsOnCooldown = new HashMap<>(cooldowns);
        this.skills = new HashMap<>(skills);
        this.race = race;
//        this.skills = getStartingSkills(race);
        this.compassFeatures = new ArrayList<>(features);
        this.targetingEntities = new ArrayList<>(targets);
        this.currentTarget = current;
    }

    public void setHasSetup(boolean hasSetup) {
        this.hasSetup = hasSetup;
    }
    public boolean getHasSetup() {
        return this.hasSetup;
    }

    public float getMagicka() {
        return this.magicka;
    }
    public void setMagicka(float magicka) {
        this.magicka = magicka;
    }

    public float getMaxMagicka() {
        return this.maxMagicka;
    }
    public void setMaxMagicka(float maxMagicka) {
        this.maxMagicka = maxMagicka;
    }

    public float getMagickaRegenModifier() {
        return this.magickaRegenModifier;
    }
    public void setMagickaRegenModifier(float modifier) {
        this.magickaRegenModifier = modifier;
    }

    public int getCharacterLevel() {
        return this.characterLevel;
    }
    public void setCharacterLevel(int level) {
        this.characterLevel = level;
    }

    public int getCharacterTotalXp() {
        return this.characterTotalXp;
    }
    public void setCharacterTotalXp(int xp) {
        this.characterTotalXp = xp;
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

    public void setSkills(Map<Integer, Skill> skills) {
        this.skills = skills;
    }
    public Map<Integer, Skill> getSkills() {
        return skills;
    }
    public void addSkill(int key, Skill value) {
        this.skills.put(key, value);
    }

    public void setRace(Race race) {
        this.race = race;
    }
    public Race getRace() {
        return race;
    }

    public void addNewSpell(Spell spell) {
        if(!this.knownSpells.contains(spell)) {
            this.knownSpells.add(spell);
        }
    }

    public Spell getSelectedSpell1() { return selectedSpell1; }
    public Spell getSelectedSpell2() { return selectedSpell2; }

    public void setSelectedSpell1(Spell spell) {
        this.selectedSpell1 = spell;
    }

    public void setSelectedSpell2(Spell spell) {
        this.selectedSpell2 = spell;
    }

    public void addSpellAndCooldown(Spell spell, float cooldown) {
        this.spellsOnCooldown.put(spell, cooldown);
    }

    public float getSpellCooldown(Spell shout) {
        return spellsOnCooldown.getOrDefault(shout, 0f);
    }

    public void setKnownSpells(List<Spell> spells) {
        this.knownSpells = spells;
    }

    public void setSpellsOnCooldown(Map<Spell, Float> cooldowns) {
        this.spellsOnCooldown = cooldowns;
    }

    public Map<Spell, Float> getSpellsOnCooldown() {
        return spellsOnCooldown;
    }

    public List<Spell> getKnownSpells() {
        return knownSpells;
    }

    public List<CompassFeature> getCompassFeatures() {
        return compassFeatures;
    }
    public void setCompassFeatures(List<CompassFeature> features) {
        this.compassFeatures = features;
    }
    public void addCompassFeature(CompassFeature feature) {
        this.compassFeatures.add(feature);
    }

    public void addTarget(int id) {
        this.targetingEntities.add(id);
        setCurrentTarget(id);
    }
    public void removeTarget(int id) {
        this.targetingEntities.remove(id);
    }
    public void setTargets(List<Integer> ids) {
        this.targetingEntities = ids;
    }
    public List<Integer> getTargets() {
        return targetingEntities;
    }

    public int getCurrentTarget() { return currentTarget; }

    public void setCurrentTarget(int id) { this.currentTarget = id; }

    public static Character get(Player player) {
        return player.getData(PlayerAttachments.CHARACTER);
    }




    /**
     * EVENT RELATED BS
     **/
    public static void register() {
        NeoForge.EVENT_BUS.register(new CharacterEvents());
    }

    private void syncToSelf(Player owner) {
        syncTo(owner);
    }

    protected void syncTo(Player player) {
        PacketDistributor.PLAYER.with((ServerPlayer) player).send(new UpdateCharacter(this));
    }

    protected void syncTo(PacketDistributor.PacketTarget target)
    {
        target.send(new UpdateCharacter(this));
    }

    private static class CharacterEvents
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
            if (target instanceof Player player)
            {
                get(player).syncToSelf(player);
            }
        }

        @SubscribeEvent
        public void playerClone(PlayerEvent.Clone event) {
            Player oldPlayer = event.getOriginal();
            Player newPlayer = event.getEntity();
            var oldHandler = get(oldPlayer);
            var newHandler = get(newPlayer);

            newPlayer.setData(PlayerAttachments.CHARACTER, oldPlayer.getData(PlayerAttachments.CHARACTER));
            PacketDistributor.PLAYER.with((ServerPlayer) newPlayer).send(new UpdateCharacter(newHandler));
        }
    }
}