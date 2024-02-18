package com.ryankshah.skyrimcraft.character.magic;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.ryankshah.skyrimcraft.Skyrimcraft;
import com.ryankshah.skyrimcraft.character.attachment.PlayerAttachments;
import com.ryankshah.skyrimcraft.character.attachment.SpellHandler;
import com.ryankshah.skyrimcraft.character.skill.SkillRegistry;
import com.ryankshah.skyrimcraft.effect.ModEffects;
import com.ryankshah.skyrimcraft.network.skill.AddXpToSkill;
import com.ryankshah.skyrimcraft.network.spell.ConsumeMagicka;
import com.ryankshah.skyrimcraft.network.spell.UpdateShoutCooldown;
import com.ryankshah.skyrimcraft.quest.Quest;
import net.minecraft.advancements.Criterion;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.PacketDistributor;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO:
 *   - Implement spell "stages" - specifically for shouts so that each
 *     individual part of the shout is learnable
 */
public class ISpell //extends ForgeRegistryEntry<ISpell>
{
    private int identifier;
    private Player caster;
    private String loc;
//    private ResourceLocation location;

    public static Codec<ISpell> CODEC = RecordCodecBuilder.create(spell -> spell.group(
            ExtraCodecs.strictOptionalField(Codec.INT, "identifier", -1).forGetter(q -> q.identifier),
            ExtraCodecs.strictOptionalField(Codec.STRING, "location", "").forGetter(q -> q.loc)

    ).apply(spell, ISpell::new));

    public ISpell() {
        this.identifier = -1;
        this.loc = "";
    }

    public ISpell(int identifier, String location) {
        this.identifier = identifier;
        this.loc = location;
//        this.location = new ResourceLocation(Skyrimcraft.MODID, this.loc);
    }

    /**
     * Get the ID of the spell
     *
     * @return spell ID
     */
    public int getID() {
        return identifier;
    }

    public ISpell getSpell() {
        return this;
    }

    /**
     * Get the name of the spell
     *
     * @return name
     */
    public String getName() {
        return "";
    }

    /**
     * Get the spell's description
     * @return description
     */
    public List<String> getDescription() {
        return new ArrayList<>();
    }

    public void setCaster(Player playerEntity) {
        this.caster = playerEntity;
    }

    /**
     * Get the player entity who casted the spell
     * @return {@link Player}
     */
    public Player getCaster() {
        return this.caster;
    }

    public ResourceLocation getDisplayAnimation() { return new ResourceLocation(""); }

    public ResourceLocation getIcon() { return new ResourceLocation(""); }

    /**
     * Get the magicka cost of the spell
     *
     * @return magicka cost
     */
    public float getCost() {
        return 0;
    }

    /**
     * Get the cooldown (seconds) of the spell
     *
     * @return cooldown
     */
    public float getCooldown() {
        return 0;
    }

    /**
     * Get the type of spell {@link SpellType}
     *
     * @return {@link SpellType}
     */
    public SpellType getType() {
        return SpellType.DESTRUCTION;
    }

    /**
     * Gets the sound to play before the spell is cast
     * @return {@link SoundEvent}
     */
    public SoundEvent getSound() {
        return null;
    }

    /**
     * Returns the length of the sound
     * @return sound length
     */
    public float getSoundLength() { return 0f; }

    /**
     * If true, spell can interrupt effects - i.e. EtherealEffect
     * @return can interrupt
     */
    public boolean canInterrupt() { return true; }

    /**
     * Get the spell difficulty {@link SpellDifficulty}
     *
     * @return {@link SpellDifficulty}
     */
    public SpellDifficulty getDifficulty() {
        return SpellDifficulty.NA;
    }

    private CastResult canCast() {
        if(getType() == SpellType.SHOUT) {
            return caster.getData(PlayerAttachments.KNOWN_SPELLS).getSpellCooldown(this) <= 0f ? CastResult.SUCCESS : CastResult.COOLDOWN;
        } else {
            return (caster.getData(PlayerAttachments.MAGICKA) >= getCost() || getCooldown() == 0f) ? CastResult.SUCCESS : CastResult.MAGICKA;
        }
    }

    public int getBaseXp() {
        return 0;
    }

    public void cast() {
        if(canCast() == CastResult.SUCCESS) {
            onCast();
        } else if(canCast() == CastResult.FAIL) {
            getCaster().displayClientMessage(Component.literal("Failed to Cast Shout"), false);
        } else {
            getCaster().displayClientMessage(Component.literal("" + (canCast() == CastResult.MAGICKA ? "Not enough magicka!" : "This shout is still on cooldown!")), false);
        }
    }

    /**
     * Specifies what happens on spell cast
     */
    public void onCast() {
        if(getType() == SpellType.DESTRUCTION) {
            final AddXpToSkill xpToSkill = new AddXpToSkill(SkillRegistry.DESTRUCTION.getID(), getBaseXp());
            PacketDistributor.PLAYER.with((ServerPlayer) caster).send(xpToSkill);
        } else if(getType() == SpellType.ALTERATION) {
            final AddXpToSkill xpToSkill = new AddXpToSkill(SkillRegistry.ALTERATION.getID(), getBaseXp());
            PacketDistributor.PLAYER.with((ServerPlayer) caster).send(xpToSkill);
        } else if(getType() == SpellType.RESTORATION) {
            final AddXpToSkill xpToSkill = new AddXpToSkill(SkillRegistry.RESTORATION.getID(), getBaseXp());
            PacketDistributor.PLAYER.with((ServerPlayer) caster).send(xpToSkill);
        } else if(getType() == SpellType.ILLUSION) {
            final AddXpToSkill xpToSkill = new AddXpToSkill(SkillRegistry.ILLUSION.getID(), getBaseXp());
            PacketDistributor.PLAYER.with((ServerPlayer) caster).send(xpToSkill);
        } else if(getType() == SpellType.CONJURATION) {
            final AddXpToSkill xpToSkill = new AddXpToSkill(SkillRegistry.CONJURATION.getID(), getBaseXp());
            PacketDistributor.PLAYER.with((ServerPlayer) caster).send(xpToSkill);
        }
        if(caster.hasEffect(ModEffects.ETHEREAL.get()) && canInterrupt())
            caster.removeEffect(ModEffects.ETHEREAL.get());

        if(!caster.isCreative()) {
            if (getType() == SpellType.SHOUT) {
                final UpdateShoutCooldown updateShoutCooldown = new UpdateShoutCooldown(getID(), getCooldown());
                PacketDistributor.SERVER.noArg().send(updateShoutCooldown);
//                Networking.sendToServer(new PacketUpdateShoutCooldownOnServer(this, getCooldown()));
            } else {
                final ConsumeMagicka consumeMagicka = new ConsumeMagicka(getCost());
                PacketDistributor.SERVER.noArg().send(consumeMagicka);
//                Networking.sendToServer(new ConsumeMagicka(getCost()));
            }
        }
        // player var was null, check it
        caster.getCommandSenderWorld().playSound(null, caster.getX(), caster.getY(), caster.getZ(), getSound(), SoundSource.PLAYERS, 1f, 1f);
    }

    public enum SpellType {
        ALL(-1, "ALL"),
        ALTERATION(0, "ALTERATION"),
        DESTRUCTION(1, "DESTRUCTION"),
        RESTORATION(2, "RESTORATION"),
        SHOUT(3, "SHOUTS"),
        POWERS(4, "POWERS"),
        ILLUSION(5, "ILLUSION"),
        CONJURATION(6, "CONJURATION");

        private int typeID;
        private String displayName;

        SpellType(int typeID, String displayName) {
            this.typeID = typeID;
            this.displayName = displayName;
        }

        public int getTypeID() {
            return this.typeID;
        }


        @Override
        public String toString() {
            return this.displayName;
        }
    }
    public enum SpellDifficulty {
        NA(0),
        NOVICE(1),
        APPRENTICE(2),
        ADEPT(3),
        EXPERT(4),
        MASTER(5);

        private int difficulty;

        SpellDifficulty(int difficulty) {
            this.difficulty = difficulty;
        }

        public int getDifficulty() {
            return this.difficulty;
        }
    }

    private enum CastResult {
        SUCCESS(0),
        COOLDOWN(1),
        MAGICKA(2),
        FAIL(3);

        private int id;

        CastResult(int id) { this.id = id; }

        public int getId() { return this.id; }
    }

//    @Override
//    public int hashCode() {
//        return getID();
//    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof ISpell && getID() == ((ISpell) obj).getID();
    }
}