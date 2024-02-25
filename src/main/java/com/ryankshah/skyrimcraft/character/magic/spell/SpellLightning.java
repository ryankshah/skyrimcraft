package com.ryankshah.skyrimcraft.character.magic.spell;

import com.mojang.blaze3d.vertex.VertexConsumer;
import com.ryankshah.skyrimcraft.Skyrimcraft;
import com.ryankshah.skyrimcraft.character.magic.Spell;
import com.ryankshah.skyrimcraft.character.magic.entity.LightningEntity;
import com.ryankshah.skyrimcraft.init.DamageSourceInit;
import com.ryankshah.skyrimcraft.particle.EmittingLightningParticle;
import com.ryankshah.skyrimcraft.particle.LightningParticle;
import com.ryankshah.skyrimcraft.util.ParticleColors;
import com.ryankshah.skyrimcraft.util.ProjectileHelper;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import org.joml.Matrix3f;
import org.joml.Matrix4f;

import java.util.ArrayList;
import java.util.List;

public class SpellLightning extends Spell
{
    public SpellLightning(int identifier) {
        super(identifier, "lightning");
    }

    @Override
    public String getName() {
        return "Lightning";
    }

    @Override
    public List<String> getDescription() {
        List<String> desc = new ArrayList<>();
        desc.add("...");
        return desc;
    }

    @Override
    public ResourceLocation getDisplayAnimation() {
        return new ResourceLocation(Skyrimcraft.MODID, "spells/turn_undead.png");
    }

    @Override
    public ResourceLocation getIcon() {
        return new ResourceLocation(Skyrimcraft.MODID, "spells/icons/fireball.png");
    }

    public SoundEvent getSound() {
        return SoundEvents.LIGHTNING_BOLT_IMPACT;
    }

    @Override
    public float getCost() {
        return 4.0f;
    }

    @Override
    public float getCooldown() {
        return 0f;
    }

    @Override
    public SpellType getType() {
        return SpellType.DESTRUCTION;
    }

    @Override
    public SpellDifficulty getDifficulty() {
        return SpellDifficulty.NA;
    }

    @Override
    public int getBaseXp() {
        return 6;
    }

    @Override
    public void onCast() {
        HitResult result = ProjectileHelper.getLookAtHit(getCaster(), 16D);
        if(result.getType() == HitResult.Type.ENTITY) {
            Vec3 target = result.getLocation();
            getCaster().level().playSound(null, target.x, target.y, target.z,
                    SoundEvents.LIGHTNING_BOLT_IMPACT, SoundSource.MASTER, 1.0F, 0.5F + ProjectileHelper.RANDOM.nextFloat() * 0.2F);

            for (int i = 0; i < 32; i++) {
                double offsetX = ProjectileHelper.RANDOM.nextGaussian() * 1.5D;
                double offsetY = ProjectileHelper.RANDOM.nextGaussian() * 1.5D;
                double offsetZ = ProjectileHelper.RANDOM.nextGaussian() * 1.5D;
                ((ServerLevel) getCaster().level()).sendParticles(new LightningParticle.LightningParticleOptions(ParticleColors.PURPLE_LIGHTNING, 0.5F, 1),
                        target.x + offsetX, target.y + offsetY, target.z + offsetZ,
                        0, 0.0D, 0.0D, 0.0D, 0.0D);
            }
            super.onCast();
        }
    }

    public void vertex(VertexConsumer pConsumer, Matrix4f pPose, Matrix3f pNormal, float pX, float pY, float pZ, int pRed, int pGreen, int pBlue, float pU, float pV) {
        pConsumer.vertex(pPose, pX, pY, pZ).color(pRed, pGreen, pBlue, 255).uv(pU, pV).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(15728880).normal(pNormal, 0.0F, 1.0F, 0.0F).endVertex();
    }
}