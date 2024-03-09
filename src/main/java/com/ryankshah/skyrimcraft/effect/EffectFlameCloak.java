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
            Vec3 loc = livingEntity.position().add(0, 1, 0);
            double radius = 16D;

            Set<Vec3> sphereSet = ClientUtil.sphere(100);
            for(Vec3 point : sphereSet) {
                level.sendParticles(ParticleTypes.SMALL_FLAME, loc.x + point.x, loc.y + point.y, loc.z + point.z, 1, 0, 0, 0, 0);
                level.sendParticles(ParticleTypes.WHITE_ASH, loc.x + point.x, loc.y + point.y, loc.z + point.z, 1, 0, 0, 0, 0);
            }

            for(Entity entity : level.getEntities(livingEntity, new AABB(livingEntity.position(), livingEntity.position().multiply(radius, radius, radius)))) {
                if(entity instanceof LivingEntity target) {
                    target.setSecondsOnFire(2);
                }
            }
        }
        super.applyEffectTick(livingEntity, p_76394_2_);
    }
}
