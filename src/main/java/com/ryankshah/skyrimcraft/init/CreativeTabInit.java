package com.ryankshah.skyrimcraft.init;

import com.ryankshah.skyrimcraft.Skyrimcraft;
import com.ryankshah.skyrimcraft.item.SkyrimIngredient;
import com.ryankshah.skyrimcraft.item.SkyrimNecklace;
import com.ryankshah.skyrimcraft.item.SkyrimRing;
import com.ryankshah.skyrimcraft.item.SpellBook;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class CreativeTabInit
{
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Skyrimcraft.MODID);

    // tab title
    public static String SKYRIMCRAFT_BLOCK_TAB_TITLE = "creativetab.skyrimcraft.blocks";
    public static String SKYRIMCRAFT_INGREDIENT_TAB_TITLE = "creativetab.skyrimcraft.ingredients";
    public static String SKYRIMCRAFT_FOOD_TAB_TITLE = "creativetab.skyrimcraft.food";
    public static String SKYRIMCRAFT_COMBAT_TAB_TITLE = "creativetab.skyrimcraft.combat";
    public static String SKYRIMCRAFT_MAGIC_TITLE = "creativetab.skyrimcraft.magic";
    public static String SKYRIMCRAFT_JEWELLERY_TAB_TITLE = "creativetab.skyrimcraft.jewellery";
    public static String SKYRIMCRAFT_ALL_TITLE = "creativetab.skyrimcraft.all";

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> SKYRIMCRAFT_BLOCKS_TAB = CREATIVE_MODE_TABS.register("skyrimcraft_blocks_tab", () -> {
        CreativeModeTab.Builder builder = CreativeModeTab.builder();

        builder.displayItems((itemDisplayParameters, output) -> {
            BlockInit.BLOCKS.getEntries()
                    .stream()
                    .map(DeferredHolder::get)
                    .forEach(output::accept);
        });

        builder.icon(() -> new ItemStack(BlockInit.SHOUT_BLOCK.get()));
        builder.title(Component.translatable(SKYRIMCRAFT_BLOCK_TAB_TITLE));

        return builder.build();
    });

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> SKYRIMCRAFT_COMBAT_TAB = CREATIVE_MODE_TABS.register("skyrimcraft_combat_tab", () -> {
        CreativeModeTab.Builder builder = CreativeModeTab.builder();

        builder.displayItems((itemDisplayParameters, output) -> {
            ItemInit.ITEMS.getEntries()
                    .stream()
                    .filter(item -> item.get() instanceof SwordItem || item.get() instanceof ShieldItem || item.get() instanceof BowItem || item.get() instanceof ArmorItem)
                    .map(DeferredHolder::get)
                    .forEach(output::accept);
        });

        builder.icon(() -> new ItemStack(ItemInit.DAEDRIC_SWORD.get()));
        builder.title(Component.translatable(SKYRIMCRAFT_COMBAT_TAB_TITLE));

        return builder.build();
    });

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> SKYRIMCRAFT_ALL_TAB = CREATIVE_MODE_TABS.register("skyrimcraft_all_tab", () -> {
        CreativeModeTab.Builder builder = CreativeModeTab.builder();

        builder.displayItems((itemDisplayParameters, output) -> {
            BlockInit.BLOCKS.getEntries()
                    .stream()
                    .map(DeferredHolder::get)
                    .forEach(output::accept);
        });

        builder.displayItems((itemDisplayParameters, output) -> {
            ItemInit.ITEMS.getEntries()
                    .stream()
                    .map(DeferredHolder::get)
                    .forEach(output::accept);
        });

        builder.icon(() -> new ItemStack(ItemInit.DAEDRA_HEART.get()));
        builder.title(Component.translatable(SKYRIMCRAFT_ALL_TITLE));

        return builder.build();
    });

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> SKYRIMCRAFT_MAGIC_TAB = CREATIVE_MODE_TABS.register("skyrimcraft_magic_tab", () -> {
        CreativeModeTab.Builder builder = CreativeModeTab.builder();

        builder.displayItems((itemDisplayParameters, output) -> {
            BlockInit.BLOCKS.getEntries()
                    .stream()
                    .map(DeferredHolder::get)
                    .forEach(output::accept);
        });

        builder.displayItems((itemDisplayParameters, output) -> {
            ItemInit.ITEMS.getEntries()
                    .stream()
                    .filter(item -> item.get() instanceof SpellBook)
                    .map(DeferredHolder::get)
                    .forEach(output::accept);
            output.accept(ItemInit.STAFF.get());
        });

        builder.icon(() -> new ItemStack(ItemInit.TURN_UNDEAD_SPELLBOOK.get()));
        builder.title(Component.translatable(SKYRIMCRAFT_MAGIC_TITLE));

        return builder.build();
    });

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> SKYRIMCRAFT_INGREDIENTS_TAB = CREATIVE_MODE_TABS.register("skyrimcraft_ingredients_tab", () -> {
        CreativeModeTab.Builder builder = CreativeModeTab.builder();

        builder.displayItems((itemDisplayParameters, output) -> {
            ItemInit.ITEMS.getEntries()
                    .stream()
                    .filter(item -> item.get() instanceof SkyrimIngredient)
                    .map(DeferredHolder::get)
                    .forEach(output::accept);
        });

        builder.icon(() -> new ItemStack(ItemInit.ORANGE_DARTWING.get()));
        builder.title(Component.translatable(SKYRIMCRAFT_INGREDIENT_TAB_TITLE));

        return builder.build();
    });

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> SKYRIMCRAFT_JEWELLERY_TAB = CREATIVE_MODE_TABS.register("skyrimcraft_jewellery_tab", () -> {
        CreativeModeTab.Builder builder = CreativeModeTab.builder();

        builder.displayItems((itemDisplayParameters, output) -> {ItemInit.ITEMS.getEntries()
                .stream()
                .filter(item -> item.get() instanceof SkyrimRing || item.get() instanceof SkyrimNecklace)
                .map(DeferredHolder::get)
                .forEach(output::accept);
        });

        builder.icon(() -> new ItemStack(ItemInit.RING_OF_NAMIRA.get()));
        builder.title(Component.translatable(SKYRIMCRAFT_JEWELLERY_TAB_TITLE));

        return builder.build();
    });

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> SKYRIMCRAFT_FOOD_TAB = CREATIVE_MODE_TABS.register("skyrimcraft_food_tab", () -> {
        CreativeModeTab.Builder builder = CreativeModeTab.builder();

        builder.displayItems((itemDisplayParameters, output) -> {
            output.accept(ItemInit.BUTTER);
            output.accept(ItemInit.FLOUR);
            ItemInit.ITEMS.getEntries()
                    .stream()
                    .filter(item -> item.get().isEdible() || item.get() instanceof PotionItem)
                    .map(DeferredHolder::get)
                    .forEach(output::accept);
        });

        builder.icon(() -> new ItemStack(ItemInit.SWEET_ROLL.get()));
        builder.title(Component.translatable(SKYRIMCRAFT_FOOD_TAB_TITLE));

        return builder.build();
    });
}
