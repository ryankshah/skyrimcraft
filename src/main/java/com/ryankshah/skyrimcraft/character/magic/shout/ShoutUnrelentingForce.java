package com.ryankshah.skyrimcraft.character.magic.shout;

import com.ryankshah.skyrimcraft.Skyrimcraft;
import com.ryankshah.skyrimcraft.character.magic.ISpell;
import com.ryankshah.skyrimcraft.character.magic.entity.UnrelentingForceEntity;
import com.ryankshah.skyrimcraft.init.SoundsInit;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;

import java.util.ArrayList;
import java.util.List;

public class ShoutUnrelentingForce extends ISpell //implements IForgeRegistryEntry<ISpell>
{
    public ShoutUnrelentingForce(int identifier) {
        super(identifier, "unrelenting_force");
    }

    @Override
    public String getName() {
        return "Unrelenting Force";
    }

    @Override
    public List<String> getDescription() {
        List<String> desc = new ArrayList<>();
        desc.add("Your voice is raw power,");
        desc.add("pushing aside anything that");
        desc.add("stands in your path");
        return desc;
    }

    @Override
    public SoundEvent getSound() {
        //return ModSounds.UNRELENTING_FORCE.get();
        return SoundsInit.UNRELENTING_FORCE_CAST.get();
//        return SoundEvents.LIGHTNING_BOLT_IMPACT;
    }

    @Override
    public float getSoundLength() {
        return 2.2f;
    }

    @Override
    public ResourceLocation getDisplayAnimation() {
        return new ResourceLocation(Skyrimcraft.MODID, "spells/unrelenting_force.png");
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
        return 20.0f;
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
        UnrelentingForceEntity entity = new UnrelentingForceEntity(getCaster().getCommandSenderWorld(), getCaster(), getCaster().getLookAngle().x * 1, getCaster().getLookAngle().y * 1, getCaster().getLookAngle().z * 1);
        entity.setPos(entity.getX(), getCaster().getY() + getCaster().getEyeHeight(), entity.getZ());
        getCaster().getCommandSenderWorld().addFreshEntity(entity);

        super.onCast();
    }
}