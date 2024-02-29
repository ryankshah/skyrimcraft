package com.ryankshah.skyrimcraft.event;

import com.mojang.blaze3d.platform.InputConstants;
import com.ryankshah.skyrimcraft.Skyrimcraft;
import net.minecraft.client.KeyMapping;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.client.settings.KeyConflictContext;
import net.neoforged.neoforge.client.settings.KeyModifier;
import net.neoforged.neoforge.common.util.Lazy;
import org.lwjgl.glfw.GLFW;

@Mod.EventBusSubscriber(modid = Skyrimcraft.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class KeyEvents
{
    public static final Lazy<KeyMapping> MENU_KEY = Lazy.of(() -> new KeyMapping(
            "key." + Skyrimcraft.MODID + ".toggle_menu", // Will be localized using this translation key
            InputConstants.Type.KEYSYM, // Default mapping is on the keyboard
            GLFW.GLFW_KEY_M, // Default key is P
            "key.categories.misc" // Mapping will be in the misc category
    ));
    public static final Lazy<KeyMapping> SPELL_SLOT_1_KEY = Lazy.of(() -> new KeyMapping(
            "key." + Skyrimcraft.MODID + ".toggle_spell_1", // Will be localized using this translation key
            InputConstants.Type.KEYSYM, // Default mapping is on the keyboard
            GLFW.GLFW_KEY_V, // Default key is P
            "key.categories.misc" // Mapping will be in the misc category
    ));
    public static final Lazy<KeyMapping> SPELL_SLOT_2_KEY = Lazy.of(() -> new KeyMapping(
            "key." + Skyrimcraft.MODID + ".toggle_spell_2", // Will be localized using this translation key
            InputConstants.Type.KEYSYM, // Default mapping is on the keyboard
            GLFW.GLFW_KEY_B, // Default key is P
            "key.categories.misc" // Mapping will be in the misc category
    ));
    public static final Lazy<KeyMapping> PICKPOCKET_KEY = Lazy.of(() -> new KeyMapping(
            "key." + Skyrimcraft.MODID + ".toggle_pickpocket", // Will be localized using this translation key
            KeyConflictContext.UNIVERSAL,
            InputConstants.Type.KEYSYM, // Default mapping is on the keyboard
            GLFW.GLFW_KEY_P, // Default key is P
            "key.categories.misc" // Mapping will be in the misc category
    ));

    public static final Lazy<KeyMapping> SKYRIM_MENU_ENTER = Lazy.of(() -> new KeyMapping(
            "key." + Skyrimcraft.MODID + ".menu.enter", // Will be localized using this translation key
            KeyConflictContext.GUI,
            InputConstants.Type.KEYSYM, // Default mapping is on the keyboard
            GLFW.GLFW_KEY_ENTER, // Default key is P
            "key.categories.misc" // Mapping will be in the misc category
    ));
    public static final Lazy<KeyMapping> SKYRIM_MENU_NORTH = Lazy.of(() -> new KeyMapping(
            "key." + Skyrimcraft.MODID + ".menu.north", // Will be localized using this translation key
            KeyConflictContext.GUI,
            InputConstants.Type.KEYSYM, // Default mapping is on the keyboard
            GLFW.GLFW_KEY_UP, // Default key is P
            "key.categories.misc" // Mapping will be in the misc category
    ));
    public static final Lazy<KeyMapping> SKYRIM_MENU_SOUTH = Lazy.of(() -> new KeyMapping(
            "key." + Skyrimcraft.MODID + ".menu.south", // Will be localized using this translation key
            KeyConflictContext.GUI,
            InputConstants.Type.KEYSYM, // Default mapping is on the keyboard
            GLFW.GLFW_KEY_DOWN, // Default key is P
            "key.categories.misc" // Mapping will be in the misc category
    ));
    public static final Lazy<KeyMapping> SKYRIM_MENU_EAST = Lazy.of(() -> new KeyMapping(
            "key." + Skyrimcraft.MODID + ".menu.east", // Will be localized using this translation key
            KeyConflictContext.GUI,
            InputConstants.Type.KEYSYM, // Default mapping is on the keyboard
            GLFW.GLFW_KEY_LEFT, // Default key is P
            "key.categories.misc" // Mapping will be in the misc category
    ));
    public static final Lazy<KeyMapping> SKYRIM_MENU_WEST = Lazy.of(() -> new KeyMapping(
            "key." + Skyrimcraft.MODID + ".menu.west", // Will be localized using this translation key
            KeyConflictContext.GUI,
            InputConstants.Type.KEYSYM, // Default mapping is on the keyboard
            GLFW.GLFW_KEY_RIGHT, // Default key is P
            "key.categories.misc" // Mapping will be in the misc category
    ));

    @SubscribeEvent
    public static void registerBindings(RegisterKeyMappingsEvent event) {
        event.register(MENU_KEY.get());
        event.register(SPELL_SLOT_1_KEY.get());
        event.register(SPELL_SLOT_2_KEY.get());
        event.register(PICKPOCKET_KEY.get());

        event.register(SKYRIM_MENU_ENTER.get());
        event.register(SKYRIM_MENU_NORTH.get());
        event.register(SKYRIM_MENU_SOUTH.get());
        event.register(SKYRIM_MENU_WEST.get());
        event.register(SKYRIM_MENU_EAST.get());
    }
}
