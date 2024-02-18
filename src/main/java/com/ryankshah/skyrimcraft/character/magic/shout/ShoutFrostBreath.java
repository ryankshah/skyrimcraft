package com.ryankshah.skyrimcraft.character.magic.shout;

import com.ryankshah.skyrimcraft.Skyrimcraft;
import com.ryankshah.skyrimcraft.character.magic.Spell;
import com.ryankshah.skyrimcraft.effect.ModEffects;
import com.ryankshah.skyrimcraft.util.RayTraceUtil;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.ArrayList;
import java.util.List;

public class ShoutFrostBreath extends Spell //implements IForgeRegistryEntry<ISpell>
{
    public ShoutFrostBreath(int identifier) {
        super(identifier, "frost_breath");
    }

    @Override
    public String getName() {
        return "Frost Breath";
    }

    @Override
    public List<String> getDescription() {
        List<String> desc = new ArrayList<>();
        desc.add("Add a description");
        desc.add("...");
        return desc;
    }

    @Override
    public SoundEvent getSound() {
        return SoundEvents.SNOW_BREAK;
    }

    @Override
    public float getSoundLength() {
        return 0f;
    }

    @Override
    public ResourceLocation getDisplayAnimation() {
        return new ResourceLocation(Skyrimcraft.MODID, "spells/ice_form.png");
    }

    @Override
    public ResourceLocation getIcon() {
        return new ResourceLocation(Skyrimcraft.MODID, "spells/icons/fireball.png");
    }

    @Override
    public float getCost() {
        return 0f;
    }

    @Override
    public float getCooldown() {
        return 120.0f;
    }

    @Override
    public SpellType getType() {
        return SpellType.SHOUT;
    }

    @Override
    public SpellDifficulty getDifficulty() {
        return SpellDifficulty.NA;
    }

    @Override
    public void onCast() {
        Level level = getCaster().level();

        Entity rayTracedEntity = RayTraceUtil.rayTrace(level, getCaster(), 20D);
        if(rayTracedEntity instanceof LivingEntity livingEntity && level instanceof ServerLevel serverLevel) {
            Vec3 vec3 = getCaster().position();
            Vec3 vec31 = livingEntity.position();
            int i = 0;
            int maxDist = 128;
            List<LivingEntity> nearbyEntities = serverLevel.getNearbyEntities(LivingEntity.class, TargetingConditions.DEFAULT, getCaster(), AABB.ofSize(getCaster().position(), 15, 15, 15));

            for(LivingEntity entity : nearbyEntities) {
                entity.addEffect(new MobEffectInstance(ModEffects.FROZEN.get(), 160, 0, false, true, true));
            }

            if (vec31.distanceTo(vec3) <= maxDist) {
                serverLevel.addParticle(ParticleTypes.INSTANT_EFFECT, vec3.x + i, vec3.y + i, vec3.z + i, 1.0f, 1.0f, 1.0f);
            }

            // freeze player for 8s (160 ticks = 8 * 20)
//            ((LivingEntity) rayTracedEntity).addEffect(new MobEffectInstance(ModEffects.FROZEN.get(), 160, 0, false, true, true));
            super.onCast();
        } else {
            getCaster().displayClientMessage(Component.translatable("skyrimcraft.shout.notarget"), false);
        }
    }
}