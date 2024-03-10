package com.ryankshah.skyrimcraft.effect;

import com.ryankshah.skyrimcraft.Skyrimcraft;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModEffects
{
    public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(Registries.MOB_EFFECT, Skyrimcraft.MODID);

    public static final Supplier<EffectEthereal> ETHEREAL = MOB_EFFECTS.register("ethereal", () -> new EffectEthereal(
            MobEffectCategory.BENEFICIAL,
            0x8A416C
    ));
    public static final Supplier<EffectFrozen> FROZEN = MOB_EFFECTS.register("frozen", () -> new EffectFrozen(
            MobEffectCategory.HARMFUL,
            0xA5F2F3
    ));
    public static final Supplier<EffectMagickaRegen> MAGICKA_REGEN = MOB_EFFECTS.register("magicka_regen", () -> new EffectMagickaRegen(
            MobEffectCategory.BENEFICIAL,
            0xAA3792CB
    ));
    public static final Supplier<EffectSpectral> SPECTRAL = MOB_EFFECTS.register("spectral", () -> new EffectSpectral(
            MobEffectCategory.BENEFICIAL,
            0xFDB515
    ));
    public static final Supplier<EffectUndeadFlee> UNDEAD_FLEE = MOB_EFFECTS.register("undead_flee", () -> new EffectUndeadFlee(
            MobEffectCategory.BENEFICIAL,
            0xAA222222
    ));
    public static final Supplier<EffectDismay> DISMAY = MOB_EFFECTS.register("dismay", () -> new EffectDismay(
            MobEffectCategory.BENEFICIAL,
            0xAA222222
    ));
    public static final Supplier<EffectWaterwalking> WATER_WALKING = MOB_EFFECTS.register("water_walking", () -> new EffectWaterwalking(
            MobEffectCategory.NEUTRAL,
            0x0F5E9C
    ));

    public static final Supplier<EffectParalysis> PARALYSIS = MOB_EFFECTS.register("paralysis", () -> new EffectParalysis(
            MobEffectCategory.HARMFUL,
            0xA5F2F3
    ));

    public static final Supplier<EffectCureDisease> CURE_DISEASE = MOB_EFFECTS.register("cure_disease", () -> new EffectCureDisease(
            MobEffectCategory.BENEFICIAL,
            0xA5F2F3
    ));

    public static final Supplier<EffectCurePoison> CURE_POISON = MOB_EFFECTS.register("cure_poison", () -> new EffectCurePoison(
            MobEffectCategory.BENEFICIAL,
            0xA5F2F3
    ));

    public static final Supplier<EffectFlameCloak> FLAME_CLOAK = MOB_EFFECTS.register("flame_cloak", () -> new EffectFlameCloak(
            MobEffectCategory.BENEFICIAL,
            0xAA222222
    ));

    public static final Supplier<EffectHist> HIST = MOB_EFFECTS.register("hist", () -> new EffectHist(
            MobEffectCategory.BENEFICIAL,
            0xAA222222
    ));

    public static final Supplier<EffectAdrenalineRush> ADRENALINE_RUSH = MOB_EFFECTS.register("adrenaline_rush", () -> new EffectAdrenalineRush(
            MobEffectCategory.BENEFICIAL,
            0xAA222222
    ));

    public static final Supplier<EffectBattlecry> BATTLE_CRY = MOB_EFFECTS.register("battle_cry", () -> new EffectBattlecry(
            MobEffectCategory.BENEFICIAL,
            0xAA222222
    ));
}