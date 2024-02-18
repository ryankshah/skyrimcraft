package com.ryankshah.skyrimcraft.character.magic.shout;

import com.ryankshah.skyrimcraft.Skyrimcraft;
import com.ryankshah.skyrimcraft.character.magic.ISpell;
import com.ryankshah.skyrimcraft.effect.ModEffects;
import com.ryankshah.skyrimcraft.util.RayTraceUtil;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.boss.enderdragon.phases.EnderDragonPhase;
import net.minecraft.world.level.Level;

import java.util.ArrayList;
import java.util.List;

public class ShoutDragonAspect extends ISpell //implements IForgeRegistryEntry<ISpell>
{
    public ShoutDragonAspect(int identifier) {
        super(identifier, "dragonrend");
    }

    @Override
    public String getName() {
        return "Dragonrend";
    }

    @Override
    public List<String> getDescription() {
        List<String> desc = new ArrayList<>();
        desc.add("Your voice lashes out at a");
        desc.add("dragon's very soul, forcing");
        desc.add("the beast to land");
        return desc;
    }

    @Override
    public SoundEvent getSound() {
        //return ModSounds.UNRELENTING_FORCE.get();
        return SoundEvents.ENDER_DRAGON_GROWL;
    }

    @Override
    public float getSoundLength() {
        return 0f;
    }

    @Override
    public ResourceLocation getDisplayAnimation() {
        return new ResourceLocation(Skyrimcraft.MODID, "spells/dragonrend.png");
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
        return 60.0f;
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

        int dur = 300;
        int power = 1;

        getCaster().addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, dur * power, 3, false, true, true));
        getCaster().addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, dur * power, 2, false, true, true));
        getCaster().addEffect(new MobEffectInstance(MobEffects.ABSORPTION, dur * power, 1, false, true, true));

        if(level instanceof ServerLevel serverLevel) {
            serverLevel.sendParticles(ParticleTypes.LAVA, getCaster().position().x, getCaster().position().y, getCaster().position().z, 100, 0 ,0, 0, 1.0f);
        }
    }
}