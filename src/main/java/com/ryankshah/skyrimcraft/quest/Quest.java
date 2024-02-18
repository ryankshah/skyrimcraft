package com.ryankshah.skyrimcraft.quest;

import com.google.gson.JsonObject;
import com.mojang.serialization.Codec;
import com.mojang.serialization.JsonOps;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.advancements.Criterion;
import net.minecraft.core.HolderLookup;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.ExtraCodecs;

import java.util.ArrayList;
import java.util.List;

public class Quest implements IQuest
{
    private ResourceLocation icon;
    private ResourceLocation reward;
    private String name;
    private Float delay;
    private List<QuestStep> steps;

    public static Codec<QuestStep> QUEST_STEP_CODEC = RecordCodecBuilder.create(quest -> quest.group(
            ExtraCodecs.strictOptionalField(Codec.STRING, "task", "").forGetter(q -> q.task),
            Criterion.CODEC
                    .listOf()
                    .fieldOf("triggers")
                    .forGetter(q -> q.triggers),
            ExtraCodecs.strictOptionalField(ResourceLocation.CODEC, "reward", new ResourceLocation("")).forGetter(q -> q.reward)

    ).apply(quest, QuestStep::new));

    public static Codec<Quest> CODEC = RecordCodecBuilder.create(quest -> quest.group(
            ExtraCodecs.strictOptionalField(ResourceLocation.CODEC, "icon", new ResourceLocation("")).forGetter(q -> q.icon),
            ExtraCodecs.strictOptionalField(Codec.STRING, "name", "").forGetter(q -> q.name),
            ExtraCodecs.strictOptionalField(ResourceLocation.CODEC, "reward", new ResourceLocation("")).forGetter(q -> q.reward),
            ExtraCodecs.strictOptionalField(Codec.FLOAT, "delay", 0f).forGetter(q -> q.delay),
            QUEST_STEP_CODEC
                    .listOf()
                    .fieldOf("steps")
                    .forGetter(q -> q.steps)
    ).apply(quest, Quest::new));

    public Quest() {
    }

    public Quest(ResourceLocation icon, String name, ResourceLocation reward, float delay, List<QuestStep> steps) {
        this.icon = icon;
        this.reward = reward;
        this.name = name;
        this.delay = delay;
        this.steps = steps;
    }

    @Override
    public IQuest lootTable(ResourceLocation reward) {
        this.reward = reward;
        return this;
    }

    @Override
    public IQuest repeatDelay(float delay) {
        this.delay = delay;
        return this;
    }

    @Override
    public IQuest name(String task) {
        this.name = task;
        return this;
    }

    @Override
    public Quest icon(ResourceLocation icon) {
        this.icon = icon;
        return this;
    }

    @Override
    public Quest addStep(QuestStep step) {

        if (this.steps == null) {
            this.steps = new ArrayList<>();
        }
        this.steps.add(step);
        return this;
    }

    @Override
    public Codec<Quest> codec() {
        return CODEC;
    }

    @Override
    public JsonObject serialize(HolderLookup.Provider provider) {
        JsonObject jsonObject = new JsonObject();
//
//        if (this.reward != null) {
//            jsonObject.addProperty("reward", this.reward.toString());
//        }
//
//        if (this.delay != null) {
//            jsonObject.addProperty("delay", this.delay);
//        }
//
//        if (this.name != null) {
//            jsonObject.addProperty("name", this.name);
//        }
//
//        if (this.icon != null) {
//            jsonObject.addProperty("icon", this.icon.toString());
//        }
//
//        // todo - fix because its replacing the existing trigger and not creating a list of them.
//        // perhaps we make these quest steps, where each step can potentially have a reward and has its own trigger
//        // -- also how do we handle killing "multiple spiders" as one example?
//        if (this.triggers != null) {
//            if (!triggers.isEmpty()) {
//                Criterion.CODEC.listOf().encodeStart(JsonOps.INSTANCE, this.triggers).result().ifPresent(result -> jsonObject.add("triggers", result));
//            }
//        }

        return jsonObject;
    }

    public static class QuestStep
    {
        private String task;
        private List<Criterion<?>> triggers;
        private ResourceLocation reward;

        public QuestStep() {}

        public QuestStep(String task, List<Criterion<?>> triggers, ResourceLocation reward) {
            this.task = task;
            this.triggers = triggers;
            this.reward = reward;
        }

        public QuestStep task(String task) {
            this.task = task;
            return this;
        }

        public QuestStep reward(ResourceLocation reward) {
            this.reward = reward;
            return this;
        }

        public QuestStep addTrigger(Criterion<?> trigger) {

            if (this.triggers == null) {
                this.triggers = new ArrayList<>();
            }
            this.triggers.add(trigger);
            return this;
        }

        public Codec<QuestStep> codec() {
            return QUEST_STEP_CODEC;
        }

        public String getTask() { return this.task; }

        public List<Criterion<?>> getTriggers() {
            return this.triggers;
        }

        public ResourceLocation getReward() {
            return this.reward;
        }
    }
}