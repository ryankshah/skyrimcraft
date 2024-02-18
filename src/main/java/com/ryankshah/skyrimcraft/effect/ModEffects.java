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
            MobEffectCategory.BENEFICIAL,
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
    public static final Supplier<EffectWaterwalking> WATER_WALKING = MOB_EFFECTS.register("water_walking", () -> new EffectWaterwalking(
            MobEffectCategory.BENEFICIAL,
            0x0F5E9C
    ));
}