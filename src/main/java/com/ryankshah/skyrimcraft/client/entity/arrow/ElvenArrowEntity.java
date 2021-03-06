package com.ryankshah.skyrimcraft.client.entity.arrow;

import com.ryankshah.skyrimcraft.client.entity.ModEntityType;
import com.ryankshah.skyrimcraft.item.ModItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class ElvenArrowEntity extends AbstractArrowEntity
{
    public ElvenArrowEntity(EntityType<? extends ElvenArrowEntity> entityType, World world) {
        super(entityType, world);
    }

    public ElvenArrowEntity(World world, double p_i46757_2_, double p_i46757_4_, double p_i46757_6_) {
        super(ModEntityType.ELVEN_ARROW_ENTITY.get(), p_i46757_2_, p_i46757_4_, p_i46757_6_, world);
    }

    public ElvenArrowEntity(World world, LivingEntity livingEntity) {
        super(ModEntityType.ELVEN_ARROW_ENTITY.get(), livingEntity, world);
    }

    @Override
    public IPacket<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    protected ItemStack getPickupItem() {
        return new ItemStack(ModItems.ELVEN_ARROW.get());
    }
}