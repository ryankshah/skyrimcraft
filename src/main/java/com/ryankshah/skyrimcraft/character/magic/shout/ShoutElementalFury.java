package com.ryankshah.skyrimcraft.character.magic.shout;

import com.ryankshah.skyrimcraft.Skyrimcraft;
import com.ryankshah.skyrimcraft.character.magic.Spell;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.Level;

import java.util.ArrayList;
import java.util.List;

public class ShoutElementalFury extends Spell //implements IForgeRegistryEntry<ISpell>
{
    public ShoutElementalFury(int identifier) {
        super(identifier, "elemental_fury");
    }

    @Override
    public String getName() {
        return "Elemental Fury";
    }

    @Override
    public List<String> getDescription() {
        List<String> desc = new ArrayList<>();
        desc.add("The Thu'um imbues your arms");
        desc.add("with the speed of wind, allowing");
        desc.add("for faster weapon strikes");
        return desc;
    }

    @Override
    public SoundEvent getSound() {
        return SoundEvents.DRAGON_FIREBALL_EXPLODE;
    }

    @Override
    public float getSoundLength() {
        return 0f;
    }

    @Override
    public ResourceLocation getDisplayAnimation() {
        return new ResourceLocation(Skyrimcraft.MODID, "spells/elemental_fury.png");
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

        //TODO: Enchant the held weapon (if exists) and increase attack speed of player while using the weapon temporarily.
        //TODO: If its a pickaxe, enable faster mining with this item temporarily

//        getCaster().getAttribute(Attributes.ATTACK_SPEED).addTransientModifier(
//                new AttributeModifier(AttributeInit.ELEMENTAL_FURY_SHOUT_ID, "Elemental Fury Shout Bonus", 2.0f, AttributeModifier.Operation.MULTIPLY_BASE)
//        );
    }
}