package com.ryankshah.skyrimcraft.character.magic.spell;

import com.ryankshah.skyrimcraft.Skyrimcraft;
import com.ryankshah.skyrimcraft.character.magic.Spell;
import com.ryankshah.skyrimcraft.effect.ModEffects;
import com.ryankshah.skyrimcraft.network.spell.DetectLife;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntList;
import it.unimi.dsi.fastutil.ints.IntLists;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.phys.AABB;
import net.neoforged.neoforge.network.PacketDistributor;

import java.util.ArrayList;
import java.util.List;

public class SpellDetectLife extends Spell
{
    public SpellDetectLife(int identifier) {
        super(identifier, "detect_life");
    }

    @Override
    public String getName() {
        return "Detect Life";
    }

    @Override
    public List<String> getDescription() {
        List<String> desc = new ArrayList<>();
        desc.add("Nearby living creatures can");
        desc.add("be seen through walls");
        return desc;
    }

    @Override
    public ResourceLocation getDisplayAnimation() {
        return new ResourceLocation(Skyrimcraft.MODID, "spells/detect_life.png");
    }

    @Override
    public ResourceLocation getIcon() {
        return new ResourceLocation(Skyrimcraft.MODID, "spells/icons/detect_life.png");
    }

    public SoundEvent getSound() {
        return SoundEvents.GLOW_SQUID_SQUIRT;
    }

    @Override
    public float getCost() {
        return 2;
    } //TODO: 289 cost..???

    @Override
    public float getCooldown() {
        return 0.0f;
    }

    @Override
    public SpellType getType() {
        return SpellType.ALTERATION;
    }

    @Override
    public SpellDifficulty getDifficulty() {
        return SpellDifficulty.ADEPT;
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
        float radius = 32.0f;
        if(getCaster().level() instanceof ServerLevel level) {
            IntList ids = IntArrayList.of();
            for(Entity entity : level.getEntities(getCaster(), new AABB(getCaster().position(), getCaster().position().multiply(radius, radius, radius)))) {
                if(entity instanceof LivingEntity target
                        && target.getMobType() != MobType.UNDEAD) {
                    ids.add(target.getId());
                    System.out.println(ids);
                }
            }
            PacketDistributor.PLAYER.with((ServerPlayer) getCaster()).send(new DetectLife(ids));
        }

        super.onCast();
    }
}