package com.ryankshah.skyrimcraft.item;

import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;

public class SkyrimArmor extends ArmorItem
{
    private boolean isHeavy;

    public SkyrimArmor(ArmorMaterial pMaterial, Type pType, Properties pProperties, boolean isHeavy) {
        super(pMaterial, pType, pProperties);
        this.isHeavy = isHeavy;
    }

    public boolean isHeavy() {
        return isHeavy;
    }
}
