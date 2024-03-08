package com.ryankshah.skyrimcraft.effect;

import com.ryankshah.skyrimcraft.particle.FireParticle;
import com.ryankshah.skyrimcraft.particle.LightningParticle;
import com.ryankshah.skyrimcraft.util.ClientUtil;
import com.ryankshah.skyrimcraft.util.ParticleColors;
import com.ryankshah.skyrimcraft.util.ProjectileHelper;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.Set;

public class EffectFlameCloak extends MobEffect
{
    protected EffectFlameCloak(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor);
    }

    // Whether the effect should apply this tick. Used e.g. by the Regeneration effect that only applies
    // once every x ticks, depending on the tick count and amplifier.
    @Override
    public boolean shouldApplyEffectTickThisTick(int tickCount, int amplifier) {
        return tickCount % 2 == 0; //tickCount % 2 == 0; // replace this with whatever check you want
    }

    @Override
    public void applyEffectTick(LivingEntity livingEntity, int p_76394_2_) {
        if(livingEntity.level() instanceof ServerLevel) {
            ServerLevel level = (ServerLevel)livingEntity.level();
            Vec3 loc = livingEntity.position();
            double radius = 16D;

            Set<Vec3> sphereSet = ClientUtil.sphere(1000);
            for(Vec3 point : sphereSet) {
                level.sendParticles(ParticleTypes.FLAME, loc.x + point.x, loc.y * point.y, loc.z + point.z, 2, 0, 0, 0, 0);
                level.sendParticles(ParticleTypes.ASH, loc.x + point.x, loc.y + point.y, loc.z + point.z, 1, 0, 0, 0, 0);
            }

//            for (double phi = 0; phi <= 2 * Math.PI; phi += Math.PI / 6) {
//                double z = Math.cos(phi) * radius;
//                double x = Math.sin(phi) * radius;
//                level.sendParticles(ParticleTypes.FLAME, livingEntity.getX() + x, livingEntity.getY() + livingEntity.getEyeHeight(), livingEntity.getZ() + z, 1, 0D, 0D, 0D, 0); // set amount to 0 so particles don't fly off and stays in place
//                level.sendParticles(ParticleTypes.WHITE_ASH, livingEntity.getX() + x, livingEntity.getY() + livingEntity.getEyeHeight(), livingEntity.getZ() + z, 2, 0D, 0D, 0D, 0); // set amount to 0 so particles don't fly off and stays in place
//            }

//            for (int i = 0; i < 360; i++) {
//                int n = 6 - 30 + 1;
//                int in = level.random.nextInt() % 2;
//                int size =  in;
//
//                double angle = (i * size * Math.PI / 180);
//                double x = size * Math.cos(angle);
//                double z = size * Math.sin(angle);
//                Vec3 loc = livingEntity.position().add(x, 1, z);
//
//                level.sendParticles(ParticleTypes.FLAME, loc.x, loc.y, loc.z, 1, 0D, 0D, 0D, 0); // set amount to 0 so particles don't fly off and stays in place
//                level.sendParticles(ParticleTypes.WHITE_ASH, loc.x, loc.y, loc.z, 1, 0D, 0D, 0D, 0); // set amount to 0 so particles don't fly off and stays in place
//            }

//            for (double phi = 0; phi <= Math.PI * radius; phi += Math.PI * radius / 6) {
//                double z = Math.cos(phi) * 0.8;
//                double x = Math.sin(phi) * 0.8;
//                level.sendParticles(ParticleTypes.FLAME, livingEntity.getX() + x, livingEntity.getY() + livingEntity.getEyeHeight(), livingEntity.getZ() + z, 1, 0D, 0D, 0D, 0); // set amount to 0 so particles don't fly off and stays in place
//                level.sendParticles(ParticleTypes.WHITE_ASH, livingEntity.getX() + x, livingEntity.getY() + livingEntity.getEyeHeight(), livingEntity.getZ() + z, 2, 0D, 0D, 0D, 0); // set amount to 0 so particles don't fly off and stays in place
//            }

            for(Entity entity : level.getEntities(livingEntity, new AABB(livingEntity.position(), livingEntity.position().multiply(radius, radius, radius)))) {
                if(entity instanceof LivingEntity target) {
                    target.setSecondsOnFire(2);
//                    target.hurt(level.damageSources().onFire(), 2);
                }
            }
        }
        super.applyEffectTick(livingEntity, p_76394_2_);
    }
}
