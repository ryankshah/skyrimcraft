package com.ryankshah.skyrimcraft.init;

import com.mojang.serialization.MapCodec;
import com.ryankshah.skyrimcraft.Skyrimcraft;
import com.ryankshah.skyrimcraft.particle.EmittingLightningParticle;
import com.ryankshah.skyrimcraft.particle.LightningParticle;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ParticleInit
{
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(BuiltInRegistries.PARTICLE_TYPE, Skyrimcraft.MODID);

    public static DeferredHolder<ParticleType<?>,ParticleType<EmittingLightningParticle.EmittingLightningParticleOptions>> EMITTING_LIGHTNING = PARTICLE_TYPES.register("emitting_lightning", () ->
            new ParticleType<>(false) {
                @Override
                public MapCodec<EmittingLightningParticle.EmittingLightningParticleOptions> codec() {
                    return null;
                }

                @Override
                public StreamCodec<? super RegistryFriendlyByteBuf, EmittingLightningParticle.EmittingLightningParticleOptions> streamCodec() {
                    return null;
                }
            });

    public static DeferredHolder<ParticleType<?>,ParticleType<LightningParticle.LightningParticleOptions>> LIGHTNING = PARTICLE_TYPES.register("lightning", () ->
            new ParticleType<>(false) {
                @Override
                public MapCodec<LightningParticle.LightningParticleOptions> codec() {
                    return null;
                }

                @Override
                public StreamCodec<? super RegistryFriendlyByteBuf, LightningParticle.LightningParticleOptions> streamCodec() {
                    return null;
                }
            });

//    public static DeferredHolder<ParticleType<?>,ParticleType<FireParticle.FireParticleOptions>> FIRE = PARTICLE_TYPES.register("fire", () ->
//            new ParticleType<>(false, FireParticle.FireParticleOptions.DESERIALIZER) {
//                @Override
//                public Codec<FireParticle.FireParticleOptions> codec() {
//                    return null;
//                }
//
//                @Override
//                public StreamCodec<? super RegistryFriendlyByteBuf, FireParticle.FireParticleOptions> streamCodec() {
//                    return null;
//                }
//            });
}