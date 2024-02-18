package com.ryankshah.skyrimcraft.character.attachment.serial;

import com.mojang.serialization.DynamicOps;
import com.ryankshah.skyrimcraft.character.attachment.SpellHandler;
import net.minecraft.nbt.Tag;
import net.neoforged.neoforge.attachment.IAttachmentSerializer;

public class SpellHandlerSerializer implements IAttachmentSerializer
{
    public <T> SpellHandlerSerializer(SpellHandler spellHandler, DynamicOps<T> tDynamicOps, T t) {
    }

    @Override
    public Object read(Tag tag) {
        return null;
    }

    @Override
    public Tag write(Object attachment) {
        return null;
    }
}
