package com.ryankshah.skyrimcraft.character.attachment;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.ryankshah.skyrimcraft.character.feature.Race;
import com.ryankshah.skyrimcraft.character.magic.SpellRegistry;
import com.ryankshah.skyrimcraft.character.skill.SkillWrapper;
import com.ryankshah.skyrimcraft.network.character.UpdateCharacter;
import com.ryankshah.skyrimcraft.network.character.UpdateExtraCharacter;
import com.ryankshah.skyrimcraft.util.CompassFeature;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.attachment.IAttachmentHolder;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.network.PacketDistributor;

public class ExtraCharacter
{
    public static Codec<ExtraCharacter> CODEC = RecordCodecBuilder.create(characterInstance -> characterInstance.group(
            Codec.INT.fieldOf("dragonSouls").forGetter(ExtraCharacter::getDragonSouls)
    ).apply(characterInstance, ExtraCharacter::new));

    protected int dragonSouls;

    public ExtraCharacter(int dragonSouls) {
        this.dragonSouls = dragonSouls;
    }

    public ExtraCharacter() {
        this(
                0
        );
    }

    public int getDragonSouls() {
        return dragonSouls;
    }
    public void setDragonSouls(int amount) {
        this.dragonSouls = amount;
    }
    public void addDragonSoul() {
        this.dragonSouls++;
    }
    public void removeDragonSoul() {
        this.dragonSouls--;
    }

    public static ExtraCharacter get(Player player) {
        return player.getData(PlayerAttachments.EXTRA_CHARACTER);
    }

    /**
     * EVENT RELATED BS
     **/
    public static void register() {
        NeoForge.EVENT_BUS.register(new ExtraCharacter.CharacterEvents());
    }

    private void syncToSelf(Player owner) {
        syncTo(owner);
    }

    protected void syncTo(Player player) {
        PacketDistributor.PLAYER.with((ServerPlayer) player).send(new UpdateExtraCharacter(this));
    }

    protected void syncTo(PacketDistributor.PacketTarget target)
    {
        target.send(new UpdateExtraCharacter(this));
    }

    private static class CharacterEvents
    {
        @SubscribeEvent
        public void entityJoinLevel(EntityJoinLevelEvent event) {
            Entity target = event.getEntity();
            if (target.level().isClientSide)
                return;
            if (target instanceof Player player)
            {
                get(player).syncToSelf(player);
            }
        }

        @SubscribeEvent
        public void joinWorld(PlayerEvent.PlayerLoggedInEvent event)
        {
            Player target = event.getEntity();
            if (target.level().isClientSide)
                return;
            get(target).syncToSelf(target);
        }

        @SubscribeEvent
        public void changedDimension(PlayerEvent.PlayerChangedDimensionEvent event)
        {
            Player target = event.getEntity();
            if (target.level().isClientSide)
                return;
            get(target).syncToSelf(target);
        }

        @SubscribeEvent
        public void track(PlayerEvent.StartTracking event)
        {
            Entity target = event.getTarget();
            if (target.level().isClientSide)
                return;
            if (target instanceof Player player)
            {
                get(player).syncToSelf(player);
            }
        }

        //TODO: Check if this is how we do it...
        @SubscribeEvent
        public void playerDeath(LivingDeathEvent event) {
            if(event.getEntity() instanceof Player player) {
                var newHandler = get(player);

                player.setData(PlayerAttachments.EXTRA_CHARACTER, player.getData(PlayerAttachments.EXTRA_CHARACTER));
                PacketDistributor.PLAYER.with((ServerPlayer) player).send(new UpdateExtraCharacter(newHandler));
            }
        }

        @SubscribeEvent
        public void playerClone(PlayerEvent.Clone event) {
            if(!event.isWasDeath())
                return;

            Player player = event.getEntity();
            Player oldPlayer = event.getOriginal();
            oldPlayer.revive();
            ExtraCharacter oldHandler = ExtraCharacter.get(oldPlayer);
            player.setData(PlayerAttachments.EXTRA_CHARACTER, oldHandler);
            ExtraCharacter newHandler = ExtraCharacter.get(player);
            PacketDistributor.PLAYER.with((ServerPlayer) player).send(new UpdateExtraCharacter(newHandler));
        }
    }
}
