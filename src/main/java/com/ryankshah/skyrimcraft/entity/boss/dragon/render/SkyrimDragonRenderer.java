package com.ryankshah.skyrimcraft.entity.boss.dragon.render;

import com.ryankshah.skyrimcraft.entity.boss.dragon.SkyrimDragon;
import com.ryankshah.skyrimcraft.entity.boss.dragon.model.SkyrimDragonModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class SkyrimDragonRenderer extends GeoEntityRenderer<SkyrimDragon>
{
    public SkyrimDragonRenderer(EntityRendererProvider.Context ctx) {
        super(ctx, new SkyrimDragonModel());
        this.shadowRadius = 2.0F; //change 0.7 to the desired shadow size.
    }
}