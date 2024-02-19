package com.ryankshah.skyrimcraft.quest;

import com.google.gson.JsonObject;
import com.mojang.serialization.Codec;
import net.minecraft.core.HolderLookup;
import net.minecraft.resources.ResourceLocation;

public interface IQuest
{
    IQuest lootTable(ResourceLocation table);

    IQuest repeatDelay(float delay);

    IQuest name(String name);

    IQuest icon(ResourceLocation icon);

    IQuest addStep(Quest.QuestStep step);

    Codec<Quest> codec();

    JsonObject serialize(HolderLookup.Provider provider);
}