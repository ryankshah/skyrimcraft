package com.ryankshah.skyrimcraft.network.spell;

import com.ryankshah.skyrimcraft.Skyrimcraft;
import com.ryankshah.skyrimcraft.character.attachment.Character;
import com.ryankshah.skyrimcraft.character.magic.Spell;
import com.ryankshah.skyrimcraft.character.magic.SpellRegistry;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record UpdateSelectedSpell(int position, ResourceKey<Spell> spell) implements CustomPacketPayload
{
    public static final CustomPacketPayload.Type<UpdateSelectedSpell> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(Skyrimcraft.MODID, "updateselectedspells"));

    public static final StreamCodec<ByteBuf, UpdateSelectedSpell> CODEC = StreamCodec.composite(
            ByteBufCodecs.INT,
            UpdateSelectedSpell::position,
            ResourceKey.streamCodec(SpellRegistry.SPELLS_KEY),
            UpdateSelectedSpell::spell,
            UpdateSelectedSpell::new
    );

    public UpdateSelectedSpell(final FriendlyByteBuf buffer) {
        this(buffer.readInt(), buffer.readResourceKey(SpellRegistry.SPELLS_KEY));
    }

    public static void handleServer(final UpdateSelectedSpell data, final IPayloadContext context) {
        ServerPlayer player = (ServerPlayer) context.player();
        Character character = Character.get(player);
        if(!data.spell.equals(SpellRegistry.EMPTY_SPELL)) {
            Spell spell = SpellRegistry.SPELLS_REGISTRY.get(data.spell);
            if(data.position == 1)
                character.setSelectedSpell1(spell);
            else if(data.position == 2)
                character.setSelectedSpell2(spell);

            final UpdateSelectedSpell sendToClient = new UpdateSelectedSpell(data.position, data.spell);
            PacketDistributor.sendToPlayer(player, sendToClient);
//            PacketDistributor.PLAYER.with(player).send(sendToClient);
        }
    }

    public static void handleClient(final UpdateSelectedSpell data, final IPayloadContext context) {
        Minecraft minecraft = Minecraft.getInstance();
        minecraft.execute(() -> {
            Player player = Minecraft.getInstance().player;
            Character character = Character.get(player);
            if (!data.spell.equals(SpellRegistry.EMPTY_SPELL)) {
                Spell spell = SpellRegistry.SPELLS_REGISTRY.get(data.spell);
                if (data.position == 1)
                    character.setSelectedSpell1(spell);
                else if (data.position == 2)
                    character.setSelectedSpell2(spell);
            }
        });
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}

