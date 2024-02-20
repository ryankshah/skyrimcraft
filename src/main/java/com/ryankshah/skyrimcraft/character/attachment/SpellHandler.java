package com.ryankshah.skyrimcraft.character.attachment;

import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.ryankshah.skyrimcraft.character.magic.Spell;
import com.ryankshah.skyrimcraft.character.magic.SpellRegistry;
import com.ryankshah.skyrimcraft.network.spell.UpdateSpellHandlerOnClient;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.network.PacketDistributor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//TODO: Check why selected spells are not syncing!!
public class SpellHandler
{
    private List<Spell> knownSpells;
    private Spell selectedSpell1;
    private Spell selectedSpell2;
    private Map<Spell, Float> spellsOnCooldown;

    public static Codec<SpellHandler> CODEC = RecordCodecBuilder.create(spellHandlerInstance -> spellHandlerInstance.group(
            SpellRegistry.SPELLS_REGISTRY.byNameCodec().listOf().fieldOf("knownSpells").forGetter(SpellHandler::getKnownSpells),
            SpellRegistry.SPELLS_REGISTRY.byNameCodec().fieldOf("selectedSpell1").forGetter(SpellHandler::getSelectedSpell1),
            SpellRegistry.SPELLS_REGISTRY.byNameCodec().fieldOf("selectedSpell1").forGetter(SpellHandler::getSelectedSpell2),
            Codec.unboundedMap(SpellRegistry.SPELLS_REGISTRY.byNameCodec(), Codec.FLOAT).fieldOf("spellsOnCooldown").forGetter(SpellHandler::getSpellsOnCooldown)

    ).apply(spellHandlerInstance, SpellHandler::new));

    public SpellHandler() {
        this(new ArrayList<>(), SpellRegistry.EMPTY_SPELL.get(), SpellRegistry.EMPTY_SPELL.get(), new HashMap<>());
    }

    public SpellHandler(List<Spell> spells, Spell selectedSpell1, Spell selectedSpell2, Map<Spell, Float> cooldowns) {
        this.knownSpells = spells;
        this.selectedSpell1 = selectedSpell1;
        this.selectedSpell2 = selectedSpell2;
        this.spellsOnCooldown = cooldowns;
    }


    public static void register(IEventBus modEventBus)
    {
        NeoForge.EVENT_BUS.register(new SpellHandlerEvents());
    }

    public void addNewSpell(Spell spell) {
        if(!this.knownSpells.contains(spell)) {
            List<Spell> copy = new ArrayList<>(knownSpells);
            copy.add(spell);
            this.knownSpells = copy;
        }
    }

    public void setSelectedSpell1(Spell spell) {
        this.selectedSpell1 = spell;
    }

    public void setSelectedSpell2(Spell spell) {
        this.selectedSpell2 = spell;
    }

    //TODO: fix this pls.
    public void addSpellAndCooldown(Spell spell, float cooldown) {
        Map<Spell, Float> cooldowns = new HashMap<>(spellsOnCooldown);
        cooldowns.put(spell, cooldown);
        this.spellsOnCooldown = cooldowns;
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

    public List<Spell> getKnownSpells() {
        return knownSpells;
    }

    public static SpellHandler get(LivingEntity player) {
        return player.getData(PlayerAttachments.KNOWN_SPELLS);
    }

    public Spell getSelectedSpell1() { return selectedSpell1; }
    public Spell getSelectedSpell2() { return selectedSpell2; }

    private void syncToSelf(Player owner)
    {
        syncTo(owner);
    }

    protected void syncTo(Player owner)
    {
        PacketDistributor.PLAYER.with((ServerPlayer) owner).send(new UpdateSpellHandlerOnClient(this));
    }

    protected void syncTo(PacketDistributor.PacketTarget target, Player owner)
    {
        target.send(new UpdateSpellHandlerOnClient(this));
    }

    public Map<Spell, Float> getSpellsOnCooldown() {
        return spellsOnCooldown;
    }

    public class SpellCooldown<L,R> {
        private L l;
        private R r;
        public SpellCooldown(L l, R r){
            this.l = l;
            this.r = r;
        }
        public L getL(){ return l; }
        public R getR(){ return r; }
        public void setL(L l){ this.l = l; }
        public void setR(R r){ this.r = r; }
    }

    private static class SpellHandlerEvents
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
        public void playerClone(PlayerEvent.Clone event)
        {
            Player oldPlayer = event.getOriginal();
            Player newPlayer = event.getEntity();

//            printDebugLog("Processing respawn for entity {}({})", newPlayer.getScoreboardName(), newPlayer.getUUID());
            var oldHandler = get(oldPlayer);
//            printDebugLog("Old entity has data, copying...");
            List<Spell> oldKnownSpells = oldHandler.getKnownSpells();
            Spell selected1 = oldHandler.getSelectedSpell1();
            Spell selected2 = oldHandler.getSelectedSpell2();
            Map<Spell, Float> oldCooldowns = oldHandler.getSpellsOnCooldown();
            var newHandler = get(newPlayer);
            newHandler.setKnownSpells(oldKnownSpells);
            newHandler.setSelectedSpell1(selected1);
            newHandler.setSelectedSpell2(selected2);
            newHandler.setSpellsOnCooldown(oldCooldowns);
        }
    }
}