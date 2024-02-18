package com.ryankshah.skyrimcraft.init;

import com.ryankshah.skyrimcraft.Skyrimcraft;
import com.ryankshah.skyrimcraft.character.magic.entity.DisarmEntity;
import com.ryankshah.skyrimcraft.character.magic.entity.FireballEntity;
import com.ryankshah.skyrimcraft.character.magic.entity.UnrelentingForceEntity;
import com.ryankshah.skyrimcraft.character.magic.entity.render.DisarmRenderer;
import com.ryankshah.skyrimcraft.character.magic.entity.render.FireballRenderer;
import com.ryankshah.skyrimcraft.character.magic.entity.render.UnrelentingForceRenderer;
import com.ryankshah.skyrimcraft.entity.boss.dragon.SkyrimDragonEntity;
import com.ryankshah.skyrimcraft.entity.boss.dragon.render.SkyrimDragonRenderer;
import com.ryankshah.skyrimcraft.entity.creature.DwarvenSpiderEntity;
import com.ryankshah.skyrimcraft.entity.creature.GiantEntity;
import com.ryankshah.skyrimcraft.entity.creature.MammothEntity;
import com.ryankshah.skyrimcraft.entity.creature.SabreCatEntity;
import com.ryankshah.skyrimcraft.entity.creature.render.DwarvenSpiderRenderer;
import com.ryankshah.skyrimcraft.entity.creature.render.GiantRenderer;
import com.ryankshah.skyrimcraft.entity.creature.render.MammothRenderer;
import com.ryankshah.skyrimcraft.entity.creature.render.SabreCatRenderer;
import com.ryankshah.skyrimcraft.entity.passive.flying.*;
import com.ryankshah.skyrimcraft.entity.passive.flying.render.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class EntityInit
{
    public static final String PICKPOCKET_TAG = "pickpocketable";
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(Registries.ENTITY_TYPE, Skyrimcraft.MODID);

    // Spell entity types
    public static final DeferredHolder<EntityType<?>, EntityType<FireballEntity>> SPELL_FIREBALL_ENTITY = ENTITY_TYPES.register("spell_fireball",
            () -> EntityType.Builder.<FireballEntity>of(FireballEntity::new, MobCategory.MISC)
                    .sized(1.25f, 1.25f) // Hitbox Size
                    .setTrackingRange(64)
                    .setShouldReceiveVelocityUpdates(true)
                    .setUpdateInterval(2)
                    .build(new ResourceLocation(Skyrimcraft.MODID, "spell_fireball").toString()));

    // Shout entity types
    public static final DeferredHolder<EntityType<?>, EntityType<UnrelentingForceEntity>> SHOUT_UNRELENTING_FORCE_ENTITY = ENTITY_TYPES.register(
            "shout_unrelenting_force_entity",
            () -> EntityType.Builder.<UnrelentingForceEntity>of(UnrelentingForceEntity::new, MobCategory.MISC)
                    .sized(4f, 4f) // Hitbox Size
                    .setTrackingRange(64)
                    .setShouldReceiveVelocityUpdates(true)
                    .setUpdateInterval(2).build(new ResourceLocation(Skyrimcraft.MODID, "shout_unrelenting_force").toString()));
    public static final DeferredHolder<EntityType<?>, EntityType<DisarmEntity>> SHOUT_DISARM_ENTITY = ENTITY_TYPES.register(
            "shout_disarm_entity",
            () -> EntityType.Builder.<DisarmEntity>of(DisarmEntity::new, MobCategory.MISC)
                    .sized(4f, 4f) // Hitbox Size
                    .setTrackingRange(64)
                    .setShouldReceiveVelocityUpdates(true)
                    .setUpdateInterval(2).build(new ResourceLocation(Skyrimcraft.MODID, "shout_disarm").toString()));

    // Mobs
    public static final DeferredHolder<EntityType<?>, EntityType<SabreCatEntity>> SABRE_CAT = ENTITY_TYPES.register("sabre_cat",
            () -> EntityType.Builder.of(SabreCatEntity::new, MobCategory.MONSTER)
                    .sized(1.0f, 1.0f) // Hitbox Size
                    .build(new ResourceLocation(Skyrimcraft.MODID, "sabre_cat").toString()));
    public static final DeferredHolder<EntityType<?>, EntityType<GiantEntity>> GIANT = ENTITY_TYPES.register("giant",
            () -> EntityType.Builder.of(GiantEntity::new, MobCategory.MONSTER)
                    .sized(1.0f, 3.0f) // Hitbox Size
                    .build(new ResourceLocation(Skyrimcraft.MODID, "giant").toString()));
    public static final DeferredHolder<EntityType<?>, EntityType<MammothEntity>> MAMMOTH = ENTITY_TYPES.register("mammoth",
            () -> EntityType.Builder.of(MammothEntity::new, MobCategory.MONSTER)
                    .sized(2.0f, 4.0f) // Hitbox Size
                    .build(new ResourceLocation(Skyrimcraft.MODID, "mammoth").toString()));
    public static final DeferredHolder<EntityType<?>, EntityType<SkyrimDragonEntity>> DRAGON = ENTITY_TYPES.register("dragon",
            () -> EntityType.Builder.of(SkyrimDragonEntity::new, MobCategory.MONSTER)
                    .sized(4.0f, 2.0f) // Hitbox Size
                    .build(new ResourceLocation(Skyrimcraft.MODID, "dragon").toString()));
    public static final DeferredHolder<EntityType<?>, EntityType<DwarvenSpiderEntity>> DWARVEN_SPIDER = ENTITY_TYPES.register("dwarven_spider",
            () -> EntityType.Builder.of(DwarvenSpiderEntity::new, MobCategory.MONSTER)
                    .sized(2.0f, 4.0f) // Hitbox Size
                    .build(new ResourceLocation(Skyrimcraft.MODID, "dwarven_spider").toString()));
    public static final DeferredHolder<EntityType<?>, EntityType<BlueButterfly>> BLUE_BUTTERFLY = ENTITY_TYPES.register("blue_butterfly",
            () -> EntityType.Builder.of(BlueButterfly::new, MobCategory.AMBIENT)
                    .sized(1.0f, 3.0f) // Hitbox Size
                    .build(new ResourceLocation(Skyrimcraft.MODID, "blue_butterfly").toString()));
    public static final DeferredHolder<EntityType<?>, EntityType<MonarchButterfly>> MONARCH_BUTTERFLY = ENTITY_TYPES.register("monarch_butterfly",
            () -> EntityType.Builder.of(MonarchButterfly::new, MobCategory.AMBIENT)
                    .sized(1.0f, 3.0f) // Hitbox Size
                    .build(new ResourceLocation(Skyrimcraft.MODID, "monarch_butterfly").toString()));
    public static final DeferredHolder<EntityType<?>, EntityType<OrangeDartwing>> ORANGE_DARTWING = ENTITY_TYPES.register("orange_dartwing",
            () -> EntityType.Builder.of(OrangeDartwing::new, MobCategory.AMBIENT)
                    .sized(1.0f, 3.0f) // Hitbox Size
                    .build(new ResourceLocation(Skyrimcraft.MODID, "orange_dartwing").toString()));
    public static final DeferredHolder<EntityType<?>, EntityType<BlueDartwing>> BLUE_DARTWING = ENTITY_TYPES.register("blue_dartwing",
            () -> EntityType.Builder.of(BlueDartwing::new, MobCategory.AMBIENT)
                    .sized(1.0f, 3.0f) // Hitbox Size
                    .build(new ResourceLocation(Skyrimcraft.MODID, "blue_dartwing").toString()));
    public static final DeferredHolder<EntityType<?>, EntityType<LunarMoth>> LUNAR_MOTH = ENTITY_TYPES.register("lunar_moth",
            () -> EntityType.Builder.of(LunarMoth::new, MobCategory.AMBIENT)
                    .sized(1.0f, 3.0f) // Hitbox Size
                    .build(new ResourceLocation(Skyrimcraft.MODID, "lunar_moth").toString()));
    public static final DeferredHolder<EntityType<?>, EntityType<TorchBug>> TORCHBUG = ENTITY_TYPES.register("torchbug",
            () -> EntityType.Builder.of(TorchBug::new, MobCategory.AMBIENT)
                    .sized(1.0f, 3.0f) // Hitbox Size
                    .build(new ResourceLocation(Skyrimcraft.MODID, "torchbug").toString()));

    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
//        RenderingRegistry.registerEntityRenderingHandler((EntityType<AncientNordArrowEntity>) ModEntityType.ANCIENT_NORD_ARROW_ENTITY.get(), AncientNordArrowRenderer::new);
//        RenderingRegistry.registerEntityRenderingHandler((EntityType<GlassArrowEntity>) ModEntityType.GLASS_ARROW_ENTITY.get(), GlassArrowRenderer::new);
//        RenderingRegistry.registerEntityRenderingHandler((EntityType<DaedricArrowEntity>) ModEntityType.DAEDRIC_ARROW_ENTITY.get(), DaedricArrowRenderer::new);
//        RenderingRegistry.registerEntityRenderingHandler((EntityType<SteelArrowEntity>) ModEntityType.STEEL_ARROW_ENTITY.get(), SteelArrowRenderer::new);
//        RenderingRegistry.registerEntityRenderingHandler((EntityType<OrcishArrowEntity>) ModEntityType.ORCISH_ARROW_ENTITY.get(), OrcishArrowRenderer::new);
//        RenderingRegistry.registerEntityRenderingHandler((EntityType<IronArrowEntity>) ModEntityType.IRON_ARROW_ENTITY.get(), IronArrowRenderer::new);
//        RenderingRegistry.registerEntityRenderingHandler((EntityType<FalmerArrowEntity>) ModEntityType.FALMER_ARROW_ENTITY.get(), FalmerArrowRenderer::new);
//        RenderingRegistry.registerEntityRenderingHandler((EntityType<ElvenArrowEntity>) ModEntityType.ELVEN_ARROW_ENTITY.get(), ElvenArrowRenderer::new);
//        RenderingRegistry.registerEntityRenderingHandler((EntityType<EbonyArrowEntity>) ModEntityType.EBONY_ARROW_ENTITY.get(), EbonyArrowRenderer::new);
//        RenderingRegistry.registerEntityRenderingHandler((EntityType<DwarvenArrowEntity>) ModEntityType.DWARVEN_ARROW_ENTITY.get(), DwarvenArrowRenderer::new);
//        RenderingRegistry.registerEntityRenderingHandler((EntityType<DragonboneArrowEntity>) ModEntityType.DRAGONBONE_ARROW_ENTITY.get(), DragonboneArrowRenderer::new);

        event.registerEntityRenderer(SHOUT_UNRELENTING_FORCE_ENTITY.value(), UnrelentingForceRenderer::new);
        event.registerEntityRenderer(SPELL_FIREBALL_ENTITY.value(), FireballRenderer::new);
        event.registerEntityRenderer(SHOUT_DISARM_ENTITY.value(), DisarmRenderer::new);

        // Mobs
        event.registerEntityRenderer(SABRE_CAT.get(), SabreCatRenderer::new);
        event.registerEntityRenderer(GIANT.get(), GiantRenderer::new);
        event.registerEntityRenderer(MAMMOTH.get(), MammothRenderer::new);
        event.registerEntityRenderer(DRAGON.get(), SkyrimDragonRenderer::new);
        event.registerEntityRenderer(DWARVEN_SPIDER.get(), DwarvenSpiderRenderer::new);
        event.registerEntityRenderer(BLUE_BUTTERFLY.get(), BlueButterflyRenderer::new);
        event.registerEntityRenderer(MONARCH_BUTTERFLY.get(), MonarchButterflyRenderer::new);
        event.registerEntityRenderer(ORANGE_DARTWING.get(), OrangeDartwingRenderer::new);
        event.registerEntityRenderer(BLUE_DARTWING.get(), BlueDartwingRenderer::new);
        event.registerEntityRenderer(LUNAR_MOTH.get(), LunarMothRenderer::new);
        event.registerEntityRenderer(TORCHBUG.get(), TorchBugRenderer::new);
    }

}