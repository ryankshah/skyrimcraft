package com.ryankshah.skyrimcraft.quest.registry;

import com.ryankshah.skyrimcraft.Skyrimcraft;
import com.ryankshah.skyrimcraft.quest.Quest;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

public class QuestRegistry
{
//    private static final DeferredRegister<QuestData> QUEST_REGISTRY = DeferredRegister.create(ResourceKey.createRegistryKey(new ResourceLocation("quests")), Skyrimcraft.MODID);
    public static final ResourceKey<Registry<Quest>> QUESTS_REGISTRY_KEY = ResourceKey.createRegistryKey(new ResourceLocation(Skyrimcraft.MODID, "quests"));
//    public static Registry<Quest> QUEST_REGISTRY = RegistryOps.create(JsonUtils.ImmutableListTypeAdapter.INSTANCE, RegistryAccess.registries());
//
////    public static final Supplier<QuestData> EXAMPLE_LOOT_ITEM_CONDITION_TYPE = REGISTER.register("example_loot_item_condition_type", () -> new LootItemConditionType(...));
//
//
//    @SubscribeEvent
//    public static void registerDatapackRegistries(DataPackRegistryEvent.NewRegistry event) {
//        event.dataPackRegistry(
//                // The registry key.
//                QUESTS_REGISTRY_KEY,
//                // The codec of the registry contents.
//                Quest.CODEC,
//                // The network codec of the registry contents. Often identical to the normal codec.
//                // May be a reduced variant of the normal codec that omits data that is not needed on the client.
//                // May be null. If null, registry entries will not be synced to the client at all.
//                // May be omitted, which is functionally identical to passing null (a method overload
//                // with two parameters is called that passes null to the normal three parameter method).
//                Quest.CODEC
//        );
//    }
}