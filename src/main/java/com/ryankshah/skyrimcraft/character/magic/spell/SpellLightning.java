package com.ryankshah.skyrimcraft.character.magic.spell;

import com.mojang.blaze3d.vertex.VertexConsumer;
import com.ryankshah.skyrimcraft.Skyrimcraft;
import com.ryankshah.skyrimcraft.character.magic.ISpell;
import com.ryankshah.skyrimcraft.effect.ModEffects;
import com.ryankshah.skyrimcraft.init.ParticleInit;
import com.ryankshah.skyrimcraft.particle.ZapParticleOption;
import com.ryankshah.skyrimcraft.util.RayTraceUtil;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.commands.arguments.EntityAnchorArgument;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import org.joml.Matrix3f;
import org.joml.Matrix4f;

import java.util.ArrayList;
import java.util.List;

public class SpellLightning extends ISpell
{
    public static final ResourceLocation LIGHTNING = new ResourceLocation(Skyrimcraft.MODID,"lightning");
    public static final ResourceLocation SPARK = new ResourceLocation(Skyrimcraft.MODID,"spark");

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
//        super.onCast();
        Level level = getCaster().level();
        Entity rayTracedEntity = RayTraceUtil.rayTrace(level, getCaster(), 20D);
        if(rayTracedEntity instanceof LivingEntity && level instanceof ServerLevel) {
            ServerLevel serverLevel = (ServerLevel) level;
            double x = rayTracedEntity.getX();
            double y = rayTracedEntity.getY();
            double z = rayTracedEntity.getZ();
            LivingEntity livingEntity = (LivingEntity)rayTracedEntity;
            // freeze player for 8s (160 ticks = 8 * 20)

//            ((LivingEntity) rayTracedEntity).addEffect(new MobEffectInstance(ModEffects.FROZEN.get(), 160, 0, false, true, true));


//            ChainLightning chainLightning = new ChainLightning(serverLevel, getCaster(), livingEntity);
//            chainLightning.setDamage(100);
//            chainLightning.range = 100;
//            chainLightning.maxConnections = 1;
//            serverLevel.addFreshEntity(chainLightning);

            Vec3 playerPos = getCaster().getEyePosition().subtract(new Vec3(0, 0.5f, 0));
            livingEntity.lookAt(EntityAnchorArgument.Anchor.EYES, playerPos);
            Vec3 enemyPos = livingEntity.position();

            Vec3 dist = playerPos.subtract(enemyPos);
            double distance = dist.length();
            double particleIntervalInBlocks = 0.5f; // every X blocks spawn a particle
            Vec3 unitVector = dist.normalize().multiply(-particleIntervalInBlocks , -particleIntervalInBlocks , -particleIntervalInBlocks );

            Vec3 startPos = playerPos;

            Vec3 start = getCaster().position().add(0, getCaster().getBbHeight() / 2, 0);
            var dest = livingEntity.position().add(0, livingEntity.getBbHeight() / 2, 0);
            serverLevel.sendParticles(new ZapParticleOption(dest), start.x, start.y, start.z, 1, 0, 0, 0, 0);

            // keep stepping forward until we go the whole distance
//            for(double step = 0; step < distance; step += particleIntervalInBlocks) {
//                startPos = startPos.add(unitVector);
//                serverLevel.sendParticles(ParticleInit.ELECTRICITY_PARTICLE.get(), startPos.x, startPos.y, startPos.z, 1, 0, 0, 0, 0);
//            }

            super.onCast();
        } else {
            getCaster().displayClientMessage(Component.translatable("skyrimcraft.shout.notarget"), false);
        }
    }

    public void vertex(VertexConsumer pConsumer, Matrix4f pPose, Matrix3f pNormal, float pX, float pY, float pZ, int pRed, int pGreen, int pBlue, float pU, float pV) {
        pConsumer.vertex(pPose, pX, pY, pZ).color(pRed, pGreen, pBlue, 255).uv(pU, pV).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(15728880).normal(pNormal, 0.0F, 1.0F, 0.0F).endVertex();
    }
}