package com.ryankshah.skyrimcraft.character.magic.spell;

import com.ryankshah.skyrimcraft.Skyrimcraft;
import com.ryankshah.skyrimcraft.character.magic.Spell;
import com.ryankshah.skyrimcraft.character.magic.entity.FireballEntity;
import com.ryankshah.skyrimcraft.character.magic.entity.LightballEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;

import java.util.ArrayList;
import java.util.List;

public class SpellCandlelight extends Spell
{
    public SpellCandlelight(int identifier) {
        super(identifier, "candlelight");
    }

    @Override
    public String getName() {
        return "Candlelight";
    }

    @Override
    public List<String> getDescription() {
        List<String> desc = new ArrayList<>();
        desc.add("Creates a hovering light");
        desc.add("that lasts for 60 seconds");
        return desc;
    }

    @Override
    public ResourceLocation getDisplayAnimation() {
        return new ResourceLocation(Skyrimcraft.MODID, "spells/candlelight.png");
    }

    @Override
    public ResourceLocation getIcon() {
        return new ResourceLocation(Skyrimcraft.MODID, "spells/icons/candlelight.png");
    }

    @Override
    public SoundEvent getSound() {
        return SoundEvents.SHROOMLIGHT_BREAK;
    }

    @Override
    public float getCost() {
        return 11f;
    }

    @Override
    public float getCooldown() {
        return 0f;
    }

    @Override
    public SpellType getType() {
        return SpellType.ALTERATION;
    }

    @Override
    public SpellDifficulty getDifficulty() {
        return SpellDifficulty.NOVICE;
    }

    @Override
    public int getBaseXp() {
        return 4;
    }

    @Override
    public void onCast() {
//        LightballEntity fireball = new LightballEntity(getCaster().level(), getCaster(), getCaster().getLookAngle().x * 1, getCaster().getLookAngle().y * 1, getCaster().getLookAngle().z * 1);
//        fireball.setPos(fireball.getX(), getCaster().getY() + getCaster().getEyeHeight(), getCaster().getZ());
//        getCaster().getCommandSenderWorld().addFreshEntity(fireball);

        super.onCast();
    }
}