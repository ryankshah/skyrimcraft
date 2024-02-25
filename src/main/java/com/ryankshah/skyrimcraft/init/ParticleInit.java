package com.ryankshah.skyrimcraft.init;

import com.mojang.serialization.Codec;
import com.ryankshah.skyrimcraft.Skyrimcraft;
import com.ryankshah.skyrimcraft.particle.EmittingLightningParticle;
import com.ryankshah.skyrimcraft.particle.FireParticle;
import com.ryankshah.skyrimcraft.particle.LightningParticle;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ParticleInit
{
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(BuiltInRegistries.PARTICLE_TYPE, Skyrimcraft.MODID);

    public static DeferredHolder<ParticleType<?>,ParticleType<EmittingLightningParticle.EmittingLightningParticleOptions>> EMITTING_LIGHTNING = PARTICLE_TYPES.register("emitting_lightning", () ->
            new ParticleType<>(false, EmittingLightningParticle.EmittingLightningParticleOptions.DESERIALIZER) {
                @Override
                public Codec<EmittingLightningParticle.EmittingLightningParticleOptions> codec() {
                    return null;
                }
            });

    public static DeferredHolder<ParticleType<?>,ParticleType<LightningParticle.LightningParticleOptions>> LIGHTNING = PARTICLE_TYPES.register("lightning", () ->
            new ParticleType<>(false, LightningParticle.LightningParticleOptions.DESERIALIZER) {
                @Override
                public Codec<LightningParticle.LightningParticleOptions> codec() {
                    return null;
                }
            });

    public static DeferredHolder<ParticleType<?>,ParticleType<FireParticle.FireParticleOptions>> FIRE = PARTICLE_TYPES.register("fire", () ->
            new ParticleType<>(false, FireParticle.FireParticleOptions.DESERIALIZER) {
                @Override
                public Codec<FireParticle.FireParticleOptions> codec() {
                    return null;
                }
            });
}