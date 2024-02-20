package com.ryankshah.skyrimcraft.network.character;

import com.ryankshah.skyrimcraft.Skyrimcraft;
import com.ryankshah.skyrimcraft.character.attachment.PlayerAttachments;
import com.ryankshah.skyrimcraft.character.attachment.SkillsHandler;
import com.ryankshah.skyrimcraft.character.attachment.SpellHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.PlayPayloadContext;

public record UpdateSkillHandlerOnClient(SkillsHandler skillHandler) implements CustomPacketPayload
{
    public static final ResourceLocation ID = new ResourceLocation(Skyrimcraft.MODID,"updateskillhandleronclient");

    public UpdateSkillHandlerOnClient(final FriendlyByteBuf buffer) {
        this(buffer.readJsonWithCodec(SkillsHandler.SKILL_HANDLER_CODEC));
    }

    @Override
    public void write(final FriendlyByteBuf buffer) {
        buffer.writeJsonWithCodec(SkillsHandler.SKILL_HANDLER_CODEC, skillHandler);
    }

    @Override
    public ResourceLocation id() {
        return ID;
    }

    public static void handleClient(final UpdateSkillHandlerOnClient data, final PlayPayloadContext context) {
        Minecraft minecraft = Minecraft.getInstance();
        minecraft.execute(() -> {
            Minecraft.getInstance().player.setData(PlayerAttachments.SKILLS, data.skillHandler);
        });
    }
}

