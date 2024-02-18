package com.ryankshah.skyrimcraft.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.Vec3;

public class EffectWaterwalking extends MobEffect
{
    private final Vec3 vec = new Vec3(1.02d, 0d, 1.02d);

    protected EffectWaterwalking(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor);
    }

    @Override
    public void applyEffectTick(LivingEntity livingEntity, int p_76394_2_) {
        if (livingEntity.level().getBlockState(livingEntity.blockPosition().below()).getFluidState().is(Fluids.WATER)) {
            Vec3 motion = livingEntity.getDeltaMovement().multiply(vec);
            livingEntity.setDeltaMovement(motion);
        }

        super.applyEffectTick(livingEntity, p_76394_2_);
    }
}
