package com.ryankshah.skyrimcraft.character.attachment;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.ryankshah.skyrimcraft.network.character.UpdateQuestManager;
import com.ryankshah.skyrimcraft.quest.Quest;
import net.minecraft.server.PlayerAdvancements;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.network.PacketDistributor;

import java.util.ArrayList;
import java.util.List;

public class QuestManager
{
    public static Codec<QuestManager> CODEC = RecordCodecBuilder.create(questManagerInstance -> questManagerInstance.group(
            Quest.CODEC.listOf().fieldOf("currentQuests").forGetter(QuestManager::getCurrentQuests),
            Quest.CODEC.listOf().fieldOf("completedQuests").forGetter(QuestManager::getCompletedQuests)
    ).apply(questManagerInstance, QuestManager::new));

    protected List<Quest> currentQuests;
    protected List<Quest> completedQuests;

    public QuestManager() {
        this(new ArrayList<>(), new ArrayList<>());
    }

    public QuestManager(List<Quest> currentQuests, List<Quest> completedQuests) {
        this.currentQuests = new ArrayList<>(currentQuests);
        this.completedQuests = new ArrayList<>(completedQuests);
    }

    public List<Quest> getCurrentQuests() {
        return currentQuests;
    }

    public List<Quest> getCompletedQuests() {
        return completedQuests;
    }


    public static QuestManager get(Player player) {
        return player.getData(PlayerAttachments.QUEST_MANAGER);
    }

    /**
     * EVENT RELATED BS
     **/
    public static void register() {
        NeoForge.EVENT_BUS.register(new QuestManager.QuestEvents());
    }

    private void syncToSelf(Player owner) {
        syncTo(owner);
    }

    protected void syncTo(Player player) {
        PacketDistributor.PLAYER.with((ServerPlayer) player).send(new UpdateQuestManager(this));
    }

    protected void syncTo(PacketDistributor.PacketTarget target)
    {
        target.send(new UpdateQuestManager(this));
    }

    private static class QuestEvents
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

        @SubscribeEvent
        public void playerDeath(LivingDeathEvent event) {
            if(event.getEntity() instanceof Player player) {
                var newHandler = get(player);

                player.setData(PlayerAttachments.QUEST_MANAGER, player.getData(PlayerAttachments.QUEST_MANAGER));
                PacketDistributor.PLAYER.with((ServerPlayer) player).send(new UpdateQuestManager(newHandler));
            }
        }

        @SubscribeEvent
        public void playerClone(PlayerEvent.Clone event) {
            if(!event.isWasDeath())
                return;

            Player player = event.getEntity();
            Player oldPlayer = event.getOriginal();
            oldPlayer.revive();
            QuestManager oldHandler = QuestManager.get(oldPlayer);
            player.setData(PlayerAttachments.QUEST_MANAGER, oldHandler);
            QuestManager newHandler = QuestManager.get(player);
            PacketDistributor.PLAYER.with((ServerPlayer) player).send(new UpdateQuestManager(newHandler));
        }
    }
}
