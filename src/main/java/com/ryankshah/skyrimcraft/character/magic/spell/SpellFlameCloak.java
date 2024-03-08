package com.ryankshah.skyrimcraft.character.magic.spell;

import com.mojang.blaze3d.vertex.VertexConsumer;
import com.ryankshah.skyrimcraft.Skyrimcraft;
import com.ryankshah.skyrimcraft.character.magic.Spell;
import com.ryankshah.skyrimcraft.effect.ModEffects;
import com.ryankshah.skyrimcraft.particle.LightningParticle;
import com.ryankshah.skyrimcraft.util.ParticleColors;
import com.ryankshah.skyrimcraft.util.ProjectileHelper;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import org.joml.Matrix3f;
import org.joml.Matrix4f;

import java.util.ArrayList;
import java.util.List;

public class SpellFlameCloak extends Spell
{
    public SpellFlameCloak(int identifier) {
        super(identifier, "flame_cloak");
    }

    @Override
    public String getName() {
        return "Flame Cloak";
    }

    @Override
    public List<String> getDescription() {
        List<String> desc = new ArrayList<>();
        desc.add("Opponents in melee range");
        desc.add("take 1 heart of fire damage");
        desc.add("per second");
        return desc;
    }

    @Override
    public ResourceLocation getDisplayAnimation() {
        return new ResourceLocation(Skyrimcraft.MODID, "spells/spell_spritesheet.png");
    }

    @Override
    public ResourceLocation getIcon() {
        return new ResourceLocation(Skyrimcraft.MODID, "spells/icons/flame_spell_icon.png");
    }

    public SoundEvent getSound() {
        return SoundEvents.FIRE_AMBIENT;
    }

    @Override
    public float getCost() {
        return 18.9f;
    } //TODO: 289 cost..???

    @Override
    public float getCooldown() {
        return 60.0f;
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
    public boolean isContinuousCast() {
        return true;
    }

    @Override
    public void onCast() {
        getCaster().addEffect(new MobEffectInstance(ModEffects.FLAME_CLOAK.get(), 600, 0, false, true, true));
        super.onCast();
    }
}