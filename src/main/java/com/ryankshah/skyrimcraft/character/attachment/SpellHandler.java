package com.ryankshah.skyrimcraft.character.attachment;

import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.mojang.serialization.codecs.SimpleMapCodec;
import com.ryankshah.skyrimcraft.character.magic.EmptySpell;
import com.ryankshah.skyrimcraft.character.magic.ISpell;
import com.ryankshah.skyrimcraft.character.magic.SpellRegistry;
import net.minecraft.util.ExtraCodecs;

import java.util.ArrayList;
import java.util.List;

public class SpellHandler //implements INBTSerializable<CompoundTag>
{
    private List<ISpell> knownSpells;
    private ISpell selectedSpell1;
    private ISpell selectedSpell2;
    private List<Pair<ISpell, Float>> spellsOnCooldown;

    public static Codec<SpellHandler> CODEC = RecordCodecBuilder.create(spellHandlerInstance -> spellHandlerInstance.group(
            SpellRegistry.SPELLS_REGISTRY.byNameCodec().listOf().fieldOf("knownSpells").forGetter(s -> s.knownSpells),
            SpellRegistry.SPELLS_REGISTRY.byNameCodec().fieldOf("selectedSpell1").forGetter(s -> s.selectedSpell1),
            SpellRegistry.SPELLS_REGISTRY.byNameCodec().fieldOf("selectedSpell1").forGetter(s -> s.selectedSpell2),
            Codec.pair(SpellRegistry.SPELLS_REGISTRY.byNameCodec(), Codec.FLOAT).listOf().fieldOf("spellsOnCooldown").forGetter(s -> s.spellsOnCooldown)

    ).apply(spellHandlerInstance, SpellHandler::new));

    public SpellHandler() {
        this(new ArrayList<>(), new EmptySpell(), new EmptySpell(), new ArrayList<>());
    }

    public SpellHandler(List<ISpell> spells, ISpell selectedSpell1, ISpell selectedSpell2, List<Pair<ISpell, Float>> cooldowns) {
        this.knownSpells = spells;
        this.selectedSpell1 = selectedSpell1;
        this.selectedSpell2 = selectedSpell2;
        this.spellsOnCooldown = cooldowns;
    }

    public float getSpellCooldown(ISpell shout) {
        return spellsOnCooldown.stream().filter(p -> p.getFirst().getID() == shout.getID()).findFirst().get().getSecond();
    }

    public List<ISpell> getKnownSpells() {
        return knownSpells;
    }

    public ISpell getSelectedSpell1() { return selectedSpell1; }
    public ISpell getSelectedSpell2() { return selectedSpell2; }

//    public Map<Integer, ISpell> getSelectedSpells() {
//        return selectedSpells;
//    }

    public List<Pair<ISpell, Float>> getSpellsOnCooldown() {
        return spellsOnCooldown;
    }
//
//    @Override
//    public CompoundTag serializeNBT() {
//        CompoundTag tag = new CompoundTag();
//
//        ListTag knownSpellsNBT = new ListTag();
//        for (ISpell spell : knownSpells) {
//            knownSpellsNBT.add(IntTag.valueOf(spell.getID()));
//        }
//        tag.put("knownSpellList", knownSpellsNBT);
//
//        for (Map.Entry<Integer, ISpell> entry : selectedSpells.entrySet()) {
//            tag.put("selected" + entry.getKey(), entry.getValue() == null ? IntTag.valueOf(-1) : IntTag.valueOf(entry.getValue().getID()));
//        }
//
//        CompoundTag spellsAndCooldowns = new CompoundTag();
//        for (Map.Entry<ISpell, Float> entry : spellsOnCooldown.entrySet()) {
//            spellsAndCooldowns.put(String.valueOf(entry.getKey().getID()), FloatTag.valueOf(entry.getValue()));
//        }
//        tag.put("spellsAndCooldowns", spellsAndCooldowns);
//
//        return tag;
//    }
//
//    @Override
//    public void deserializeNBT(CompoundTag tag) {
//        ListTag knownSpellsNBT = tag.getList("knownSpellList", ListTag.TAG_LIST);
//        List<ISpell> spellsList = new ArrayList<>();
//        for (Tag inbt : knownSpellsNBT) {
//            spellsList.add(SpellRegistry.SPELLS_REGISTRY.stream().filter(s -> s.getID() == Integer.parseInt(inbt.getAsString())).findFirst().orElseThrow());
//        }
//
//        this.knownSpells = spellsList;
//        selectedSpells.put(0, tag.getInt("selected0") == -1 ? null : SpellRegistry.SPELLS_REGISTRY.stream().filter(s -> s.getID() == tag.getInt("selected0")).findFirst().orElseThrow());
//        selectedSpells.put(1, tag.getInt("selected1") == -1 ? null : SpellRegistry.SPELLS_REGISTRY.stream().filter(s -> s.getID() == tag.getInt("selected1")).findFirst().orElseThrow());
//
//        CompoundTag spellsAndCooldownsNBT = tag.getCompound("spellsAndCooldowns"); // todo:  Non [a-z0-9/._-] character in path of location: skyrimcraft:Unrelenting Force (line 85
//        for (String key : spellsAndCooldownsNBT.getAllKeys()) {
//            spellsOnCooldown.put(SpellRegistry.SPELLS_REGISTRY.stream().filter(spell -> spell.getID() == Integer.parseInt(key)).findFirst().orElseThrow(), spellsAndCooldownsNBT.getFloat(key));
//        }
//    }
}