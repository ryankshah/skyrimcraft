package com.ryankshah.skyrimcraft.init;

import com.ryankshah.skyrimcraft.Skyrimcraft;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredRegister;

public class BlockEntityInit
{
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, Skyrimcraft.MODID);

//    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<AlchemyTableBlockEntity>> ALCHEMY_TABLE = BLOCK_ENTITY_TYPES.register("alchemy_table",
//            () -> BlockEntityType.Builder.<AlchemyTableBlockEntity>of(AlchemyTableBlockEntity::new, BlockInit.ALCHEMY_TABLE.get()).build();
//
//
//    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
//        event.registerBlockEntityRenderer(ALCHEMY_TABLE.get(), AlchemyTableBlockEntityRenderer:new);
//    }

}