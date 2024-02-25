package com.ryankshah.skyrimcraft.init;

import com.ryankshah.skyrimcraft.Skyrimcraft;
import com.ryankshah.skyrimcraft.character.magic.Spell;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

import javax.annotation.Nullable;

public class DamageSourceInit
{
    public static final ResourceKey<DamageType> SPELL = ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation(Skyrimcraft.MODID, "spell_attack"));


    public static SpellDamageSource indirectSpellAttack(Entity source, @Nullable LivingEntity indirect, @Nullable Spell spell) {
        RegistryAccess registry = source.level().registryAccess();
        Registry<DamageType> types = registry.registryOrThrow(Registries.DAMAGE_TYPE);
        return new SpellDamageSource(types.getHolderOrThrow(SPELL), source, indirect, spell);
    }

    public static SpellDamageSource directSpellAttack(LivingEntity source, @Nullable Spell spell) {
        RegistryAccess registry = source.level().registryAccess();
        Registry<DamageType> types = registry.registryOrThrow(Registries.DAMAGE_TYPE);
        return new SpellDamageSource(types.getHolderOrThrow(SPELL), source, spell);
    }

    public static class SpellDamageSource extends DamageSource {
        private final @Nullable Spell spell;

        public SpellDamageSource(Holder.Reference<DamageType> holderOrThrow, Entity source, @Nullable LivingEntity indirect, @Nullable Spell spell) {
            super(holderOrThrow, source, indirect);

            this.spell = spell;
        }

        public SpellDamageSource(Holder.Reference<DamageType> holderOrThrow, @Nullable LivingEntity mob, @Nullable Spell spell) {
            super(holderOrThrow, mob);

            this.spell = spell;
        }

        @Nullable
        public Spell getSpell() {
            return this.spell;
        }
    }
}