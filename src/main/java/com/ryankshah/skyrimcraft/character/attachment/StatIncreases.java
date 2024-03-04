package com.ryankshah.skyrimcraft.character.attachment;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.ryankshah.skyrimcraft.character.feature.Race;
import com.ryankshah.skyrimcraft.character.magic.SpellRegistry;
import com.ryankshah.skyrimcraft.character.skill.SkillWrapper;
import com.ryankshah.skyrimcraft.init.AttributeInit;
import com.ryankshah.skyrimcraft.network.character.UpdateLevelUpdates;
import com.ryankshah.skyrimcraft.network.character.UpdateStatIncreases;
import com.ryankshah.skyrimcraft.util.CompassFeature;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.network.PacketDistributor;

public class StatIncreases
{
    public static Codec<StatIncreases> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.INT.fieldOf("healthIncrease").forGetter(StatIncreases::getHealthIncrease),
            Codec.INT.fieldOf("magickaIncrease").forGetter(StatIncreases::getMagickaIncrease),
            Codec.INT.fieldOf("staminaIncrease").forGetter(StatIncreases::getStaminaIncrease)
    ).apply(instance, StatIncreases::new));

    protected int healthIncrease, magickaIncrease, staminaIncrease;

    public StatIncreases() {
        this(0, 0, 0);
    }

    public StatIncreases(int healthIncrease, int magickaIncrease, int staminaIncrease) {
        this.healthIncrease = healthIncrease;
        this.magickaIncrease = magickaIncrease;
        this.staminaIncrease = staminaIncrease;
    }

    public int getHealthIncrease() {
        return healthIncrease;
    }

    public int getMagickaIncrease() {
        return magickaIncrease;
    }

    public int getStaminaIncrease() {
        return staminaIncrease;
    }

    public void increaseHealth() {
        this.healthIncrease++;
    }

    public void increaseMagicka() {
        this.magickaIncrease++;
    }

    public void increaseStamina() {
        this.staminaIncrease++;
    }

    public static void register() {
        NeoForge.EVENT_BUS.register(new StatIncreaseEvents());
    }

    private static void syncToSelf(Player owner) {
        syncTo(owner);
    }

    protected static void syncTo(Player player) {
        PacketDistributor.PLAYER.with((ServerPlayer) player).send(new UpdateStatIncreases(player.getData(PlayerAttachments.STAT_INCREASES)));
    }

    private static class StatIncreaseEvents
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

        @SubscribeEvent
        public void playerDeath(LivingDeathEvent event) {
            if(event.getEntity() instanceof Player player) {
                var old = player.getData(PlayerAttachments.STAT_INCREASES);
                player.setData(PlayerAttachments.STAT_INCREASES, player.getData(PlayerAttachments.STAT_INCREASES));
                PacketDistributor.PLAYER.with((ServerPlayer) player).send(new UpdateStatIncreases(old));
            }
        }

        @SubscribeEvent
        public void playerClone(PlayerEvent.Clone event) {
            if(!event.isWasDeath())
                return;

            Player player = event.getEntity();
            Player oldPlayer = event.getOriginal();
            oldPlayer.revive();
            StatIncreases oldHandler = oldPlayer.getData(PlayerAttachments.STAT_INCREASES);
            player.setData(PlayerAttachments.STAT_INCREASES, oldHandler);
            StatIncreases newHandler = player.getData(PlayerAttachments.STAT_INCREASES);
            PacketDistributor.PLAYER.with((ServerPlayer) player).send(new UpdateStatIncreases(newHandler));
        }
    }
}
