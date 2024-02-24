package com.ryankshah.skyrimcraft.init;

import com.ryankshah.skyrimcraft.Skyrimcraft;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.UUID;

public class AttributeInit
{
    public static final DeferredRegister<Attribute> ATTRIBUTES = DeferredRegister.create(Registries.ATTRIBUTE, Skyrimcraft.MODID);

    public static final Holder<Attribute> MAGICKA_REGEN = ATTRIBUTES.register("magicka_regen_attribute", () -> new RangedAttribute("skyrimcraft.character.attribute.magicka_regen", 1F, 1F, 2F).setSyncable(true));

    public static final UUID MAGICKA_REGEN_ID = UUID.fromString("26fcb349-bc96-4593-9b29-5ace7bdee19f");
    public static final UUID ELEMENTAL_FURY_SHOUT_ID = UUID.fromString("92837cbf-7285-41f5-9b12-811421af56fb");
}