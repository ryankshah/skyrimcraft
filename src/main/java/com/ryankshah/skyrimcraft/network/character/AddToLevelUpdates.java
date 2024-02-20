package com.ryankshah.skyrimcraft.network.character;

import com.ryankshah.skyrimcraft.Skyrimcraft;
import com.ryankshah.skyrimcraft.screen.SkyrimGuiOverlay;
import com.ryankshah.skyrimcraft.util.LevelUpdate;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.handling.PlayPayloadContext;

public record AddToLevelUpdates(String updateName, int level, int levelUpRenderTime) implements CustomPacketPayload
{
    public static final ResourceLocation ID = new ResourceLocation(Skyrimcraft.MODID,"addtolevelupdates");

    public AddToLevelUpdates(final FriendlyByteBuf buffer) {
        this(buffer.readUtf(), buffer.readInt(), buffer.readInt());
    }

    @Override
    public void write(final FriendlyByteBuf buffer) {
        buffer.writeUtf(updateName);
        buffer.writeInt(level);
        buffer.writeInt(levelUpRenderTime);
    }

    @Override
    public ResourceLocation id() {
        return ID;
    }

    public static void handleServer(final AddToLevelUpdates data, final PlayPayloadContext context) {
        final AddToLevelUpdates updates = new AddToLevelUpdates(data.updateName, data.level, data.levelUpRenderTime);
        PacketDistributor.PLAYER.with((ServerPlayer) context.player().orElseThrow()).send(updates);
    }

    public static void handleClient(final AddToLevelUpdates data, final PlayPayloadContext context) {
        Player player = context.player().orElseThrow();
        player.playSound(SoundEvents.UI_TOAST_CHALLENGE_COMPLETE, 1.0f, 1.0f);
        SkyrimGuiOverlay.SkyrimLevelUpdates.LEVEL_UPDATES.add(new LevelUpdate(data.updateName, data.level, data.levelUpRenderTime));
    }
}

