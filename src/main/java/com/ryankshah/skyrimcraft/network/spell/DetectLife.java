package com.ryankshah.skyrimcraft.network.spell;

import com.ryankshah.skyrimcraft.Skyrimcraft;
import it.unimi.dsi.fastutil.ints.IntList;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record DetectLife(IntList ids) implements CustomPacketPayload
{
    public static final CustomPacketPayload.Type<DetectLife> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(Skyrimcraft.MODID, "detectlife"));

    public static final StreamCodec<FriendlyByteBuf, DetectLife> CODEC = StreamCodec.composite(
            StreamCodec.of(FriendlyByteBuf::writeIntIdList, FriendlyByteBuf::readIntIdList),
            DetectLife::ids,
            DetectLife::new
    );

    public DetectLife(final FriendlyByteBuf buffer) {
        this(buffer.readIntIdList());
    }

    public static void handle(final DetectLife data, final IPayloadContext context) {
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

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
