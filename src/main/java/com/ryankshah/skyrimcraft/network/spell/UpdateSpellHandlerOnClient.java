package com.ryankshah.skyrimcraft.network.spell;

import com.ryankshah.skyrimcraft.Skyrimcraft;
import com.ryankshah.skyrimcraft.character.attachment.PlayerAttachments;
import com.ryankshah.skyrimcraft.character.attachment.SpellHandler;
import com.ryankshah.skyrimcraft.character.magic.SpellRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.handling.PlayPayloadContext;

public record UpdateSpellHandlerOnClient(SpellHandler spellHandler) implements CustomPacketPayload
{
    public static final ResourceLocation ID = new ResourceLocation(Skyrimcraft.MODID,"updatespellhandleronclient");

    public UpdateSpellHandlerOnClient(final FriendlyByteBuf buffer) {
        this(buffer.readJsonWithCodec(SpellHandler.SPELL_HANDLER_CODEC));
    }

    @Override
    public void write(final FriendlyByteBuf buffer) {
        buffer.writeJsonWithCodec(SpellHandler.SPELL_HANDLER_CODEC, spellHandler);
    }

    @Override
    public ResourceLocation id() {
        return ID;
    }

    public static void handleClient(final UpdateSpellHandlerOnClient data, final PlayPayloadContext context) {
        Minecraft minecraft = Minecraft.getInstance();
        minecraft.execute(() -> {
            Minecraft.getInstance().player.setData(PlayerAttachments.KNOWN_SPELLS, data.spellHandler);
        });
    }
}

