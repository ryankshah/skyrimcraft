package com.ryankshah.skyrimcraft.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

public class SkyrimNecklace extends Item implements ICurioItem
{
    public SkyrimNecklace() {
        super(new Properties().stacksTo(1).defaultDurability(0));
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        // ticking logic here
    }
}
