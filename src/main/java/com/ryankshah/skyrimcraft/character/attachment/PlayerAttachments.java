package com.ryankshah.skyrimcraft.character.attachment;

import com.ryankshah.skyrimcraft.Skyrimcraft;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public class PlayerAttachments
{
    public static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES = DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, Skyrimcraft.MODID);

    public static final Supplier<AttachmentType<Character>> CHARACTER = ATTACHMENT_TYPES.register(
            "character", () -> AttachmentType.builder(Character::new).serialize(Character.CODEC).copyOnDeath().build());


    public static void registerSyncEvents() {
        Character.register();
    }
}