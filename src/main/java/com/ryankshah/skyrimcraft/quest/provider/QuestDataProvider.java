package com.ryankshah.skyrimcraft.quest.provider;

import com.ryankshah.skyrimcraft.Skyrimcraft;
import com.ryankshah.skyrimcraft.quest.IQuest;
import com.ryankshah.skyrimcraft.quest.Quest;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import javax.annotation.Nonnull;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public abstract class QuestDataProvider implements DataProvider
{
    private final PackOutput.PathProvider questsProvider;
//    private final PackOutput.PathProvider advancedQuestsProvider;
    private final CompletableFuture<HolderLookup.Provider> registries;
    private final String modId;
    private final Map<String, Quest> questBuilders = new HashMap<>();
//    private final Map<String, IEntitiesData> entitiesBuilders = new HashMap<>();
    private final ExistingFileHelper fileHelper;

    public QuestDataProvider(String modId, PackOutput output, ExistingFileHelper fileHelper,
                             CompletableFuture<HolderLookup.Provider> registries) {
        this.modId = modId;
        this.fileHelper = fileHelper;
        this.registries = registries;
        this.questsProvider =
                output.createPathProvider(PackOutput.Target.DATA_PACK, "quests"); // Skyrimcraft.MODID + "/quests"
//        this.advancedQuestsProvider =
//                output.createPathProvider(PackOutput.Target.DATA_PACK, "quests/advanced");
    }

    public abstract void generate(HolderLookup.Provider registries, ExistingFileHelper fileHelper);

    public final IQuest copyQuest(String id, String copyId) {
        if (id.equals(copyId)) {
            return createQuest(id);
        }
        return this.questBuilders.computeIfAbsent(id,
                (k) -> this.questBuilders.getOrDefault(copyId, createQuestData()));
    }

    @Nonnull
    public CompletableFuture<?> run(@Nonnull CachedOutput pOutput) {
        return this.registries.thenCompose((p_255484_) -> {
            List<CompletableFuture<?>> list = new ArrayList<>();
            this.generate(p_255484_, this.fileHelper);
            this.questBuilders.forEach((quest, questBuilder) -> {
                Path path = this.questsProvider.json(new ResourceLocation(this.modId, quest));
                list.add(DataProvider.saveStable(pOutput, questBuilder.codec(), questBuilder, path));
//                        DataProvider.saveStable(pOutput, questBuilder.serialize(p_255484_), path));
            });
//            this.entitiesBuilders.forEach((entities, entitiesBuilder) -> {
//                Path path = this.entitiesPathProvider.json(new ResourceLocation(this.modId, entities));
//                list.add(
//                        DataProvider.saveStable(pOutput, entitiesBuilder.serialize(p_255484_), path));
//            });
            return CompletableFuture.allOf(list.toArray(CompletableFuture[]::new));
        });
    }

    public final Quest createQuest(String id) {
        return this.questBuilders.computeIfAbsent(id, (k) -> createQuestData());
    }

    private static Quest createQuestData() {
//        Skyrimcraft.logger.error("Missing Skyrimcraft implementation!");
        return new Quest();
    }

    @Override
    public String getName() {
        return Skyrimcraft.MODID + " Quests";
    }
}
