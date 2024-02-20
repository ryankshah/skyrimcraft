package com.ryankshah.skyrimcraft;

import com.ryankshah.skyrimcraft.character.attachment.PlayerAttachments;
import com.ryankshah.skyrimcraft.character.attachment.SpellHandler;
import com.ryankshah.skyrimcraft.character.feature.model.DunmerEarModel;
import com.ryankshah.skyrimcraft.character.feature.model.HighElfEarModel;
import com.ryankshah.skyrimcraft.character.feature.model.KhajiitHeadModel;
import com.ryankshah.skyrimcraft.character.magic.SpellRegistry;
import com.ryankshah.skyrimcraft.curios.render.NecklaceRenderer;
import com.ryankshah.skyrimcraft.curios.render.RingRenderer;
import com.ryankshah.skyrimcraft.data.DataGenerators;
import com.ryankshah.skyrimcraft.data.loot_table.SkyrimLootModifiers;
import com.ryankshah.skyrimcraft.effect.ModEffects;
import com.ryankshah.skyrimcraft.entity.boss.dragon.SkyrimDragonEntity;
import com.ryankshah.skyrimcraft.entity.creature.DwarvenSpiderEntity;
import com.ryankshah.skyrimcraft.entity.creature.GiantEntity;
import com.ryankshah.skyrimcraft.entity.creature.MammothEntity;
import com.ryankshah.skyrimcraft.entity.creature.SabreCatEntity;
import com.ryankshah.skyrimcraft.entity.passive.flying.*;
import com.ryankshah.skyrimcraft.init.*;
import net.minecraft.world.entity.EntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModList;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeModificationEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import software.bernie.geckolib.GeckoLib;
import top.theillusivec4.curios.api.client.CuriosRendererRegistry;

/**
 * TODO:
 *
 * FIXES:
 * - Fix spells not being able to cast if the player does not have the magicka to do so
 *
 * - Add necklaces and circlets to the corresponding tags...
 * - Add Circlet Jewellery (crowns - https://elderscrolls.fandom.com/wiki/Circlets)
 *   - Copper and Moonstone
 *   - Copper and Onyx
 *   - Copper and Ruby
 *   - Copper and Sapphire
 *   - Gold and Emerald
 *   - Gold and Ruby
 *   - Jade and Emerald
 *   - Jade and Sapphire
 *   - Silver and Moonstone
 *   - Silver and Sapphire
 *   - Aetherial Crown
 *   - Diadem of the Savant
 *   - Mage's Circlet
 *   - Wedding Wreath
 * - Add Necklaces and Amulets
 *   - Bone Hawk Amulet
 *   --- All of the above are craftable
 *   - Amulet of Akatosh
 *   - Amulet of Arkay
 *   - Amulet of Dibella
 *   - Amulet of Julianos
 *   - Amulet of Kynareth
 *   - Amulet of Mara
 *   - Amulet of Stendarr
 *   - Amulet of Talos
 *   - Amulet of Zenithar
 *   --- Add also the unique amulets but cba to add them now
 * - Some amulets have properties - look on wiki
 * - Some rings have properties:
 *   - ring of arcana -- ability to cast ingite and freeze
 *   - enchanted ring - health increase 20 points
 *   - muiris ring - created potions are 15% more powerful
 *   - nightweavers band - sneak and destruction increased by 10 points
 *   - ring of namira - stamina increase 50 points, increase health and health regen when eating
 * - Jewellery Crafting
 *   - Most non-specific jewellery can be crafted, look up the wiki pages and add their recipes!!
 *
 * - work on Dwarven Spider mechanics and figure out how we want them spawned (i.e. in crevices of caves etc.?)
 *
 * - Work on Dragon mechanics and then create variants of the dragon
 *
 * - Create giant camp structure - example: https://elderscrolls.fandom.com/wiki/Giant_Camps_(Skyrim)?file=Stonehillbluffs.png
 *   - Variants can be made later.
 *   - Spawn in up to three mammoths and three giants
 *   - Chest in there with mammoth cheese and other assorted items
 *
 * - Add trapped chests and locked chests (pickpocketing skill)
 *   - Perhaps add skyrim chests - example: https://static.wikia.nocookie.net/elderscrolls/images/3/3f/Skyrim-med-chest.png/revision/latest?cb=20120612185054
 *
 * - For food, make sure all the values are reasonable!
 * - For drink, make sure potion effects (if any) are implemented
 *
 * - Add potions!!
 *
 * - Work on how all the food, drink and ingredient items are to be obtainable (i.e. chest loots, mob loots, new plants, etc.)
 * - Textures are DONE for the following items, just create them!
 *  - Ingredients
 *    - -- all added --
 *    - --- there's more but CBA (start next from Fine Cut Void Salts - https://elderscrolls.fandom.com/wiki/Ingredients_(Skyrim)
 */
@Mod(Skyrimcraft.MODID)
public class Skyrimcraft
{
    // Define mod id in a common place for everything to reference
    public static final String MODID = "skyrimcraft";
    public static Logger logger = LoggerFactory.getLogger(Skyrimcraft.class);


    public Skyrimcraft(IEventBus bus) {
        ModLoadingContext.get().getActiveContainer().getEventBus().addListener(FMLClientSetupEvent.class, (fmlClientSetupEvent -> {
            fmlClientSetupEvent.enqueueWork(() -> {
                ModList.get().getModContainerById(MODID).ifPresent(modContainer -> {
                    logger.info("Loaded {}, using version {}", modContainer.getModInfo().getDisplayName(), modContainer.getModInfo().getVersion());
                });
            });
        }));

        GeckoLib.initialize(bus);

        SkyrimLootModifiers.GLOBAL_LOOT_MODIFIER_SERIALIZERS.register(bus);
        ParticleInit.PARTICLE_TYPES.register(bus);
        ModEffects.MOB_EFFECTS.register(bus);
        AttributeInit.ATTRIBUTES.register(bus);
        EntityInit.ENTITY_TYPES.register(bus);
        SpellRegistry.SPELLS.register(bus);

        PlayerAttachments.ATTACHMENT_TYPES.register(bus);
        PlayerAttachments.registerSyncEvents(bus);
//        SpellHandler.register(bus);

        StructureInit.STRUCTURES.register(bus);

        ItemInit.ITEMS.register(bus);
        BlockInit.BLOCKS.register(bus);
        CreativeTabInit.CREATIVE_MODE_TABS.register(bus);

        RecipeTypeInit.RECIPE_TYPES.register(bus);
        SerializerInit.SERIALIZERS.register(bus);

        SoundsInit.SOUNDS.register(bus);

        // listeners
//        bus.addListener(this::dataPackRegistry);
        bus.addListener(DataGenerators::gatherData);
        bus.addListener(this::createEntityAttributes);
        bus.addListener(this::addEntityAttributes);
        bus.addListener(this::registerLayerDefinitions);
        bus.addListener(EntityInit::registerRenderers);
        bus.addListener(this::renderCurios);
    }

//    public void dataPackRegistry(DataPackRegistryEvent.NewRegistry event) {
//        event.dataPackRegistry(QuestRegistry.QUESTS_REGISTRY_KEY, Quest.CODEC, Quest.CODEC);
//    }

    public void createEntityAttributes(EntityAttributeCreationEvent event) {
        event.put(EntityInit.SABRE_CAT.get(), SabreCatEntity.createAttributes().build());
        event.put(EntityInit.GIANT.get(), GiantEntity.createAttributes().build());
        event.put(EntityInit.MAMMOTH.get(), MammothEntity.createAttributes().build());
        event.put(EntityInit.DRAGON.get(), SkyrimDragonEntity.createAttributes().build());
        event.put(EntityInit.DWARVEN_SPIDER.get(), DwarvenSpiderEntity.createAttributes().build());
        event.put(EntityInit.BLUE_BUTTERFLY.get(), BlueButterfly.createAttributes().build());
        event.put(EntityInit.MONARCH_BUTTERFLY.get(), MonarchButterfly.createAttributes().build());
        event.put(EntityInit.ORANGE_DARTWING.get(), OrangeDartwing.createAttributes().build());
        event.put(EntityInit.BLUE_DARTWING.get(), BlueDartwing.createAttributes().build());
        event.put(EntityInit.LUNAR_MOTH.get(), LunarMoth.createAttributes().build());
        event.put(EntityInit.TORCHBUG.get(), TorchBug.createAttributes().build());

//        event.put(EntityInit.KHAJIIT.get(), KhajiitEntity.createMobAttributes().build());
    }

    public void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(DunmerEarModel.LAYER_LOCATION, DunmerEarModel::createBodyLayer);
        event.registerLayerDefinition(HighElfEarModel.LAYER_LOCATION, HighElfEarModel::createBodyLayer);
        event.registerLayerDefinition(KhajiitHeadModel.LAYER_LOCATION, KhajiitHeadModel::createBodyLayer);
//        event.registerLayerDefinition(KhajiitModel.LAYER_LOCATION, KhajiitModel::createBodyLayer);
    }

    public void addEntityAttributes(EntityAttributeModificationEvent event) {
        event.add(EntityType.PLAYER, AttributeInit.MAGICKA_REGEN.value());
    }

    public void renderCurios(final FMLClientSetupEvent event) {
        CuriosRendererRegistry.register(ItemInit.GOLD_RING.get(), () -> new RingRenderer());
        CuriosRendererRegistry.register(ItemInit.GOLD_SAPPHIRE_RING.get(), () -> new RingRenderer());
        CuriosRendererRegistry.register(ItemInit.GOLD_EMERALD_RING.get(), () -> new RingRenderer());
        CuriosRendererRegistry.register(ItemInit.GOLD_DIAMOND_RING.get(), () -> new RingRenderer());
        CuriosRendererRegistry.register(ItemInit.SILVER_RING.get(), () -> new RingRenderer());
        CuriosRendererRegistry.register(ItemInit.SILVER_AMETHYST_RING.get(), () -> new RingRenderer());
        CuriosRendererRegistry.register(ItemInit.SILVER_GARNET_RING.get(), () -> new RingRenderer());
        CuriosRendererRegistry.register(ItemInit.SILVER_RUBY_RING.get(), () -> new RingRenderer());
        CuriosRendererRegistry.register(ItemInit.ASGEIRS_WEDDING_BAND.get(), () -> new RingRenderer());
        CuriosRendererRegistry.register(ItemInit.AHZIDALS_RING_OF_ARCANA.get(), () -> new RingRenderer());
        CuriosRendererRegistry.register(ItemInit.BALWENS_ORNAMENTAL_RING.get(), () -> new RingRenderer());
        CuriosRendererRegistry.register(ItemInit.BONE_HAWK_RING.get(), () -> new RingRenderer());
        CuriosRendererRegistry.register(ItemInit.CALCELMOS_RING.get(), () -> new RingRenderer());
        CuriosRendererRegistry.register(ItemInit.ENCHANTED_RING.get(), () -> new RingRenderer());
        CuriosRendererRegistry.register(ItemInit.FJOLAS_WEDDING_BAND.get(), () -> new RingRenderer());
        CuriosRendererRegistry.register(ItemInit.ILAS_TEIS_RING.get(), () -> new RingRenderer());
        CuriosRendererRegistry.register(ItemInit.KATARINAS_ORNAMENTAL_RING.get(), () -> new RingRenderer());
        CuriosRendererRegistry.register(ItemInit.MADESIS_SILVER_RING.get(), () -> new RingRenderer());
        CuriosRendererRegistry.register(ItemInit.MUIRIS_RING.get(), () -> new RingRenderer());
        CuriosRendererRegistry.register(ItemInit.NIGHTWEAVERS_BAND.get(), () -> new RingRenderer());
        CuriosRendererRegistry.register(ItemInit.PITHIS_ORNAMENTAL_RING.get(), () -> new RingRenderer());
        CuriosRendererRegistry.register(ItemInit.RING_OF_NAMIRA.get(), () -> new RingRenderer());
        CuriosRendererRegistry.register(ItemInit.TREOYS_ORNAMENTAL_RING.get(), () -> new RingRenderer());

        CuriosRendererRegistry.register(ItemInit.GOLD_NECKLACE.get(), () -> new NecklaceRenderer());
        CuriosRendererRegistry.register(ItemInit.GOLD_DIAMOND_NECKLACE.get(), () -> new NecklaceRenderer());
        CuriosRendererRegistry.register(ItemInit.GOLD_JEWELLED_NECKLACE.get(), () -> new NecklaceRenderer());
        CuriosRendererRegistry.register(ItemInit.GOLD_RUBY_NECKLACE.get(), () -> new NecklaceRenderer());
        CuriosRendererRegistry.register(ItemInit.SILVER_NECKLACE.get(), () -> new NecklaceRenderer());
        CuriosRendererRegistry.register(ItemInit.SILVER_JEWELLED_NECKLACE.get(), () -> new NecklaceRenderer());
        CuriosRendererRegistry.register(ItemInit.SILVER_EMERALD_NECKLACE.get(), () -> new NecklaceRenderer());
        CuriosRendererRegistry.register(ItemInit.SILVER_SAPPHIRE_NECKLACE.get(), () -> new NecklaceRenderer());
    }
}
