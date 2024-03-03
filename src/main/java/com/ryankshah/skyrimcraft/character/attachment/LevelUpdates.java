package com.ryankshah.skyrimcraft.character.attachment;

import com.ryankshah.skyrimcraft.network.character.UpdateLevelUpdates;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.network.PacketDistributor;

public class LevelUpdates
{
    public static void register() {
        NeoForge.EVENT_BUS.register(new LevelUpdateEvents());
    }

    private static void syncToSelf(Player owner) {
        syncTo(owner);
    }

    protected static void syncTo(Player player) {
        PacketDistributor.PLAYER.with((ServerPlayer) player).send(new UpdateLevelUpdates(player.getData(PlayerAttachments.LEVEL_UPDATES)));
    }

    private static class LevelUpdateEvents
    {
        @SubscribeEvent
        public void entityJoinLevel(EntityJoinLevelEvent event) {
            Entity target = event.getEntity();
            if (target.level().isClientSide)
                return;
            if (target instanceof Player player)
            {
                syncToSelf(player);
            }
        }

        @SubscribeEvent
        public void loggedIn(PlayerEvent.PlayerLoggedInEvent event)
        {
            Player target = event.getEntity();
            if (target.level().isClientSide)
                return;
            syncToSelf(target);
        }

        @SubscribeEvent
        public void changedDimension(PlayerEvent.PlayerChangedDimensionEvent event)
        {
            Player target = event.getEntity();
            if (target.level().isClientSide)
                return;
            syncToSelf(target);
        }

        @SubscribeEvent
        public void track(PlayerEvent.StartTracking event)
        {
            Entity target = event.getTarget();
            if (target.level().isClientSide)
                return;
            if (target instanceof Player player)
            {
                syncToSelf(player);
            }
        }

        //TODO: Check if this is how we do it...
        @SubscribeEvent
        public void playerDeath(LivingDeathEvent event) {
            if(event.getEntity() instanceof Player player) {
                var old = player.getData(PlayerAttachments.LEVEL_UPDATES);
                player.setData(PlayerAttachments.LEVEL_UPDATES, player.getData(PlayerAttachments.LEVEL_UPDATES));
                PacketDistributor.PLAYER.with((ServerPlayer) player).send(new UpdateLevelUpdates(old));
            }
        }

        @SubscribeEvent
        public void playerClone(PlayerEvent.Clone event) {
            if(!event.isWasDeath())
                return;

            Player player = event.getEntity();
            Player oldPlayer = event.getOriginal();
            oldPlayer.revive();
            int oldHandler = oldPlayer.getData(PlayerAttachments.LEVEL_UPDATES);
            player.setData(PlayerAttachments.LEVEL_UPDATES, oldHandler);
            int newHandler = player.getData(PlayerAttachments.LEVEL_UPDATES);
            PacketDistributor.PLAYER.with((ServerPlayer) player).send(new UpdateLevelUpdates(newHandler));
        }
    }
}
