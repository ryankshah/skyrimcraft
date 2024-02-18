package com.ryankshah.skyrimcraft.character.magic;

import com.ryankshah.skyrimcraft.Skyrimcraft;
import com.ryankshah.skyrimcraft.character.magic.shout.*;
import com.ryankshah.skyrimcraft.character.magic.spell.*;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NewRegistryEvent;
import net.neoforged.neoforge.registries.RegistryBuilder;

import java.util.function.Supplier;

public class SpellRegistry
{
    public static final ResourceKey<Registry<ISpell>> SPELLS_KEY = ResourceKey.createRegistryKey(new ResourceLocation(Skyrimcraft.MODID + ":spells_key"));
    public static final Registry<ISpell> SPELLS_REGISTRY = new RegistryBuilder<>(SPELLS_KEY)
        // If you want the registry to sync its values.
        .sync(true)
    // The default key. Similar to minecraft:air for blocks. This is optional.
        .defaultKey(new ResourceLocation(Skyrimcraft.MODID, "empty"))
//        // Effectively limits the max count. Generally discouraged, but may make sense in settings such as networking.
//        .maxId(256)
    // Build the registry.
        .create();

    public static void registrySpellRegistry(NewRegistryEvent event) {
        event.register(SPELLS_REGISTRY);
    }

    public static final DeferredRegister<ISpell> SPELLS = DeferredRegister.create(SPELLS_REGISTRY, Skyrimcraft.MODID);

    // Shouts
    public static Supplier<ISpell> EMPTY_SPELL = SPELLS.register("empty_spell", EmptySpell::new);
    public static Supplier<ISpell> UNRELENTING_FORCE = SPELLS.register("unrelenting_force", () -> new ShoutUnrelentingForce(1));
    public static Supplier<ISpell> STORM_CALL = SPELLS.register("storm_call", () -> new ShoutStormCall(2));
    public static Supplier<ISpell> WHIRLWIND_SPRINT = SPELLS.register("whirlwind_sprint", () -> new ShoutWhirlwindSprint(3));
    public static Supplier<ISpell> ICE_FORM = SPELLS.register("ice_form", () -> new ShoutIceForm(4));
    public static Supplier<ISpell> DISARM = SPELLS.register("disarm", () -> new ShoutDisarm(5));
    public static Supplier<ISpell> BECOME_ETHEREAL = SPELLS.register("become_ethereal", () -> new ShoutBecomeEthereal(6));
    public static Supplier<ISpell> DRAGONREND = SPELLS.register("dragonrend", () -> new ShoutDragonrend(7));
    public static Supplier<ISpell> CLEAR_SKIES = SPELLS.register("clear_skies", () -> new ShoutClearSkies(8));
    public static Supplier<ISpell> FROST_BREATH = SPELLS.register("frost_breath", () -> new ShoutFrostBreath(9));
    public static Supplier<ISpell> DRAGON_ASPECT = SPELLS.register("dragon_aspect", () -> new ShoutDragonAspect(10));

    // Spells
    public static Supplier<ISpell> FIREBALL = SPELLS.register("fireball", () -> new SpellFireball(20));
    public static Supplier<ISpell> TURN_UNDEAD = SPELLS.register("turn_undead", () -> new SpellTurnUndead(21));
    public static Supplier<ISpell> CONJURE_FAMILIAR = SPELLS.register("conjure_familiar", () -> new SpellConjureFamiliar(22));
    public static Supplier<ISpell> HEALING = SPELLS.register("healing", () -> new SpellHealing(23));
    public static Supplier<ISpell> LIGHTNING = SPELLS.register("lightning", () -> new SpellLightning(24));
}