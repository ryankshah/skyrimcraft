package com.ryankshah.skyrimcraft.character.magic;

import com.ryankshah.skyrimcraft.Skyrimcraft;
import com.ryankshah.skyrimcraft.character.magic.shout.*;
import com.ryankshah.skyrimcraft.character.magic.spell.*;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class SpellRegistry
{
    public static final ResourceKey<Registry<Spell>> SPELLS_KEY = ResourceKey.createRegistryKey(new ResourceLocation(Skyrimcraft.MODID + ":spells_key"));
    public static final DeferredRegister<Spell> SPELLS = DeferredRegister.create(SPELLS_KEY, Skyrimcraft.MODID);
    public static final Registry<Spell> SPELLS_REGISTRY = SPELLS.makeRegistry(builder -> builder.sync(true).defaultKey(new ResourceLocation(Skyrimcraft.MODID, "empty")));

    // Shouts
    public static Supplier<Spell> EMPTY_SPELL = SPELLS.register("empty_spell", EmptySpell::new);
    public static Supplier<Spell> UNRELENTING_FORCE = SPELLS.register("unrelenting_force", () -> new ShoutUnrelentingForce(1));
    public static Supplier<Spell> STORM_CALL = SPELLS.register("storm_call", () -> new ShoutStormCall(2));
    public static Supplier<Spell> WHIRLWIND_SPRINT = SPELLS.register("whirlwind_sprint", () -> new ShoutWhirlwindSprint(3));
    public static Supplier<Spell> ICE_FORM = SPELLS.register("ice_form", () -> new ShoutIceForm(4));
    public static Supplier<Spell> DISARM = SPELLS.register("disarm", () -> new ShoutDisarm(5));
    public static Supplier<Spell> BECOME_ETHEREAL = SPELLS.register("become_ethereal", () -> new ShoutBecomeEthereal(6));
    public static Supplier<Spell> DRAGONREND = SPELLS.register("dragonrend", () -> new ShoutDragonrend(7));
    public static Supplier<Spell> CLEAR_SKIES = SPELLS.register("clear_skies", () -> new ShoutClearSkies(8));
    public static Supplier<Spell> FROST_BREATH = SPELLS.register("frost_breath", () -> new ShoutFrostBreath(9));
    public static Supplier<Spell> DRAGON_ASPECT = SPELLS.register("dragon_aspect", () -> new ShoutDragonAspect(10));

    // Spells
    public static Supplier<Spell> FIREBALL = SPELLS.register("fireball", () -> new SpellFireball(20));
    public static Supplier<Spell> TURN_UNDEAD = SPELLS.register("turn_undead", () -> new SpellTurnUndead(21));
    public static Supplier<Spell> CONJURE_FAMILIAR = SPELLS.register("conjure_familiar", () -> new SpellConjureFamiliar(22));
    public static Supplier<Spell> HEALING = SPELLS.register("healing", () -> new SpellHealing(23));
    public static Supplier<Spell> LIGHTNING = SPELLS.register("lightning", () -> new SpellLightning(24));
}