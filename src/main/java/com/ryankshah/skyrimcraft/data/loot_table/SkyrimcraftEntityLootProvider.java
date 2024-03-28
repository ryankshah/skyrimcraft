package com.ryankshah.skyrimcraft.data.loot_table;

import com.ryankshah.skyrimcraft.init.EntityInit;
import com.ryankshah.skyrimcraft.init.ItemInit;
import net.minecraft.data.loot.EntityLootSubProvider;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.LootingEnchantFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemKilledByPlayerCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceWithLootingCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.neoforged.neoforge.event.entity.living.LivingEntityUseItemEvent;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.stream.Stream;

public class SkyrimcraftEntityLootProvider extends EntityLootSubProvider
{
    public SkyrimcraftEntityLootProvider() {
        super(FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    public void generate() {
        this.add(EntityInit.BLUE_DARTWING.get(), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1)).add(LootItem.lootTableItem(ItemInit.BLUE_DARTWING.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 1.0F))).apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(1.0F, 2.0F))))));
        this.add(EntityInit.BLUE_BUTTERFLY.get(), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1)).add(LootItem.lootTableItem(ItemInit.BLUE_BUTTERFLY_WING.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 1.0F))).apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(1.0F, 2.0F))))));
        this.add(EntityInit.ORANGE_DARTWING.get(), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1)).add(LootItem.lootTableItem(ItemInit.ORANGE_DARTWING.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 1.0F))).apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(1.0F, 2.0F))))));
        this.add(EntityInit.LUNAR_MOTH.get(), LootTable.lootTable());
        this.add(EntityInit.MONARCH_BUTTERFLY.get(), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1)).add(LootItem.lootTableItem(ItemInit.BUTTERFLY_WING.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 1.0F))).apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(1.0F, 2.0F))))));
        this.add(EntityInit.TORCHBUG.get(), LootTable.lootTable());//.withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1)).add(LootItem.lootTableItem(ModItems.TORCHBUG_THORAX.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))).apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(1.0F, 2.0F))))));
        this.add(EntityInit.SABRE_CAT.get(), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1)).add(LootItem.lootTableItem(ItemInit.SABRE_CAT_TOOTH.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(0.25F, 1.0F))).apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(1.0F, 2.0F))))).withPool(LootPool.lootPool().setRolls(UniformGenerator.between(1.0F, 2.0F)).add(LootItem.lootTableItem(Items.BONE).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))).apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F))))).withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1)).add(LootItem.lootTableItem(ItemInit.EYE_OF_SABRE_CAT.get())).when(LootItemKilledByPlayerCondition.killedByPlayer()).when(LootItemRandomChanceWithLootingCondition.randomChanceAndLootingBoost(0.1F, 0.2F))));
        this.add(EntityInit.GIANT.get(), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1)).add(LootItem.lootTableItem(ItemInit.GIANTS_TOE.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))).apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(1.0F, 2.0F))))));
        this.add(EntityInit.MAMMOTH.get(), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1)).add(LootItem.lootTableItem(ItemInit.MAMMOTH_SNOUT.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))).apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(1.0F, 2.0F))))));
        this.add(EntityInit.DRAGON.get(), LootTable.lootTable());
        this.add(EntityInit.DWARVEN_SPIDER.get(), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1)).add(LootItem.lootTableItem(ItemInit.DWARVEN_METAL_INGOT.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 1.0F))).apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(1.0F, 2.0F))))));

        this.add(EntityInit.KHAJIIT.get(), LootTable.lootTable());

        LootPool.Builder falmerPool = LootPool.lootPool()
                .name("falmerPool")
                .setRolls(ConstantValue.exactly(1.0F))
                .add(LootItem.lootTableItem(ItemInit.FALMER_EAR)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.25F, 1.0F)))
                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(1.0F, 2.0F)))
                )
                .add(LootItem.lootTableItem(ItemInit.FALMER_BOOTS.get())
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.25F, 1.0F)))
                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(1.0F, 2.0F)))
                )
                .add(LootItem.lootTableItem(ItemInit.FALMER_BOW.get())
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.25F, 1.0F)))
                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(1.0F, 2.0F)))
                )
                .add(LootItem.lootTableItem(ItemInit.FALMER_HELMET.get())
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.25F, 1.0F)))
                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(1.0F, 2.0F)))
                )
                .add(LootItem.lootTableItem(ItemInit.FALMER_CHESTPLATE.get())
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.25F, 1.0F)))
                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(1.0F, 2.0F)))
                )
                .add(LootItem.lootTableItem(ItemInit.FALMER_LEGGINGS.get())
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.25F, 1.0F)))
                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(1.0F, 2.0F)))
                )
                .add(LootItem.lootTableItem(ItemInit.FALMER_SWORD.get())
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.25F, 1.0F)))
                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(1.0F, 2.0F)))
                )
                .add(LootItem.lootTableItem(ItemInit.FALMER_WAR_AXE.get())
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.25F, 1.0F)))
                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(1.0F, 2.0F)))
                )
                .add(LootItem.lootTableItem(ItemInit.VAMPIRE_DUST.get())
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.25F, 1.0F)))
                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(1.0F, 2.0F)))
                )
                .add(LootItem.lootTableItem(ItemInit.MORA_TAPINELLA.get())
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.25F, 1.0F)))
                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(1.0F, 2.0F)))
                )
                .add(LootItem.lootTableItem(ItemInit.BRIAR_HEART.get())
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.25F, 1.0F)))
                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(1.0F, 2.0F)))
                )
                .add(LootItem.lootTableItem(ItemInit.FIRE_SALTS.get())
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.25F, 1.0F)))
                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(1.0F, 2.0F)))
                )
                .add(LootItem.lootTableItem(ItemInit.TROLL_FAT.get())
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.25F, 1.0F)))
                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(1.0F, 2.0F)))
                )
                .add(LootItem.lootTableItem(ItemInit.CHAURUS_EGGS.get())
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.25F, 1.0F)))
                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(1.0F, 2.0F)))
                )
                .add(LootItem.lootTableItem(ItemInit.ELVES_EAR.get())
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.25F, 1.0F)))
                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(1.0F, 2.0F)))
                )
                .add(LootItem.lootTableItem(ItemInit.TAPROOT.get())
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.25F, 1.0F)))
                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(1.0F, 2.0F)))
                )
                .add(LootItem.lootTableItem(ItemInit.BEAR_CLAWS.get())
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.25F, 1.0F)))
                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(1.0F, 2.0F)))
                )
                .add(LootItem.lootTableItem(ItemInit.BERITS_ASHES.get())
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.25F, 1.0F)))
                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(1.0F, 2.0F)))
                )
                .add(LootItem.lootTableItem(ItemInit.CRIMSON_NIRNROOT.get())
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.25F, 1.0F)))
                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(1.0F, 2.0F)))
                )
                .add(LootItem.lootTableItem(ItemInit.DEATHBELL.get())
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.25F, 1.0F)))
                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(1.0F, 2.0F)))
                )
                .add(LootItem.lootTableItem(ItemInit.DRAGONS_TONGUE.get())
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.25F, 1.0F)))
                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(1.0F, 2.0F)))
                )
                .add(LootItem.lootTableItem(ItemInit.ECTOPLASM.get())
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.25F, 1.0F)))
                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(1.0F, 2.0F)))
                );
        this.add(EntityInit.FALMER.get(), LootTable.lootTable().withPool(falmerPool));

        this.add(EntityInit.ABECEAN_LONGFIN.get(), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1)).add(LootItem.lootTableItem(ItemInit.ABECEAN_LONGFIN.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 1.0F))).apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(1.0F, 2.0F))))));
        this.add(EntityInit.CYRODILIC_SPADETAIL.get(), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1)).add(LootItem.lootTableItem(ItemInit.CYRODILIC_SPADETAIL.get()).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 1.0F))).apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(1.0F, 2.0F))))));

        LootPool.Builder draugrPool = LootPool.lootPool()
                .name("draugrPool")
                .setRolls(UniformGenerator.between(1.0F, 3.0F))
                .add(LootItem.lootTableItem(ItemInit.FLAWED_RUBY)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.25F, 1.0F)))
                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(1.0F, 2.0F)))
                )
                .add(LootItem.lootTableItem(ItemInit.FLAWED_EMERALD)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.25F, 1.0F)))
                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(1.0F, 2.0F)))
                )
                .add(LootItem.lootTableItem(ItemInit.FLAWED_DIAMOND)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.25F, 1.0F)))
                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(1.0F, 2.0F)))
                )
                .add(LootItem.lootTableItem(ItemInit.FLAWLESS_RUBY)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.25F, 1.0F)))
                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(1.0F, 2.0F)))
                )
                .add(LootItem.lootTableItem(ItemInit.FLAWED_GARNET)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.25F, 1.0F)))
                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(1.0F, 2.0F)))
                )
                .add(LootItem.lootTableItem(ItemInit.FLAWLESS_GARNET)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.25F, 1.0F)))
                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(1.0F, 2.0F)))
                )
                .add(LootItem.lootTableItem(ItemInit.FLAWED_AMETHYST)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.25F, 1.0F)))
                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(1.0F, 2.0F)))
                );
        this.add(EntityInit.DRAUGR.get(), LootTable.lootTable().withPool(draugrPool));
    }

    @Override
    protected Stream<EntityType<?>> getKnownEntityTypes() {
        return EntityInit.ENTITY_TYPES.getEntries().stream().map(DeferredHolder::get);
    }
}
