package com.ryankshah.skyrimcraft.character.magic;

import com.ryankshah.skyrimcraft.Skyrimcraft;
import com.ryankshah.skyrimcraft.character.magic.shout.*;
import com.ryankshah.skyrimcraft.character.magic.spell.*;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.registries.DeferredRegister;

public class SpellRegistry
{
    public static final ResourceKey<Registry<ISpell>> SPELLS_KEY = ResourceKey.createRegistryKey(new ResourceLocation(Skyrimcraft.MODID + ":spells_key"));
    public static final DeferredRegister<ISpell> SPELLS = DeferredRegister.create(SPELLS_KEY, Skyrimcraft.MODID);

    public static final Registry<ISpell> SPELLS_REGISTRY = SPELLS.makeRegistry(builder -> {});

    // Shouts
    public static Holder<ISpell> EMPTY_SPELL = SPELLS.register("empty_spell", EmptySpell::new);
    public static Holder<ISpell> UNRELENTING_FORCE = SPELLS.register("unrelenting_force", () -> new ShoutUnrelentingForce(1));
    public static Holder<ISpell> STORM_CALL = SPELLS.register("storm_call", () -> new ShoutStormCall(2));
    public static Holder<ISpell> WHIRLWIND_SPRINT = SPELLS.register("whirlwind_sprint", () -> new ShoutWhirlwindSprint(3));
    public static Holder<ISpell> ICE_FORM = SPELLS.register("ice_form", () -> new ShoutIceForm(4));
    public static Holder<ISpell> DISARM = SPELLS.register("disarm", () -> new ShoutDisarm(5));
    public static Holder<ISpell> BECOME_ETHEREAL = SPELLS.register("become_ethereal", () -> new ShoutBecomeEthereal(6));
    public static Holder<ISpell> DRAGONREND = SPELLS.register("dragonrend", () -> new ShoutDragonrend(7));
    public static Holder<ISpell> CLEAR_SKIES = SPELLS.register("clear_skies", () -> new ShoutClearSkies(8));
    public static Holder<ISpell> FROST_BREATH = SPELLS.register("frost_breath", () -> new ShoutFrostBreath(9));
    public static Holder<ISpell> DRAGON_ASPECT = SPELLS.register("dragon_aspect", () -> new ShoutDragonAspect(10));

    // Spells
    public static Holder<ISpell> FIREBALL = SPELLS.register("fireball", () -> new SpellFireball(20));
    public static Holder<ISpell> TURN_UNDEAD = SPELLS.register("turn_undead", () -> new SpellTurnUndead(21));
    public static Holder<ISpell> CONJURE_FAMILIAR = SPELLS.register("conjure_familiar", () -> new SpellConjureFamiliar(22));
    public static Holder<ISpell> HEALING = SPELLS.register("healing", () -> new SpellHealing(23));
    public static Holder<ISpell> LIGHTNING = SPELLS.register("lightning", () -> new SpellLightning(24));
}