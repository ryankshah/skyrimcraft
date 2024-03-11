package com.ryankshah.skyrimcraft.network.spell;

import com.ryankshah.skyrimcraft.Skyrimcraft;
import com.ryankshah.skyrimcraft.character.magic.Spell;
import com.ryankshah.skyrimcraft.character.magic.SpellRegistry;
import it.unimi.dsi.fastutil.ints.IntList;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.handling.PlayPayloadContext;

import java.util.List;

public record DetectLife(IntList ids) implements CustomPacketPayload
{
    public static final ResourceLocation ID = new ResourceLocation(Skyrimcraft.MODID,"detectlife");

    public DetectLife(final FriendlyByteBuf buffer) {
        this(buffer.readIntIdList());
    }

    @Override
    public void write(final FriendlyByteBuf buffer) {
        buffer.writeIntIdList(ids);
    }

    @Override
    public ResourceLocation id() {
        return ID;
    }

    public static void handle(final DetectLife data, final PlayPayloadContext context) {
        Minecraft minecraft = Minecraft.getInstance();
        minecraft.execute(() -> {
            Player player = Minecraft.getInstance().player;
            for(int id : data.ids) {
                Entity entity = player.level().getEntity(id);
                if(entity instanceof LivingEntity livingEntity)
                    livingEntity.addEffect(new MobEffectInstance(MobEffects.GLOWING, 2, 1, true, false, false));
            }
        });
    }
}
