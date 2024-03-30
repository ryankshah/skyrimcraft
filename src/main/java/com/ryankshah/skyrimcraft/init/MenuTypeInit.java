package com.ryankshah.skyrimcraft.init;

import com.ryankshah.skyrimcraft.Skyrimcraft;
import com.ryankshah.skyrimcraft.screen.menu.WeaponRackMenu;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class MenuTypeInit
{
    public static final DeferredRegister<MenuType<?>> MENU_TYPES = DeferredRegister.create(Registries.MENU, Skyrimcraft.MODID);

    public static final Supplier<MenuType<WeaponRackMenu>> WEAPON_RACK_MENU = MENU_TYPES.register("weapon_rack_menu", () -> new MenuType(WeaponRackMenu::new, FeatureFlags.DEFAULT_FLAGS));
}
