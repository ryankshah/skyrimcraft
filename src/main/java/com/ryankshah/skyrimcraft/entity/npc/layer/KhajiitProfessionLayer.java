package com.ryankshah.skyrimcraft.entity.npc.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.ryankshah.skyrimcraft.Skyrimcraft;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.Util;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.npc.VillagerData;
import net.minecraft.world.entity.npc.VillagerDataHolder;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerType;

public class KhajiitProfessionLayer <T extends LivingEntity & VillagerDataHolder, M extends EntityModel<T>> extends RenderLayer<T, M>
{
    private static final Int2ObjectMap<ResourceLocation> LEVEL_LOCATIONS = Util.make(new Int2ObjectOpenHashMap<>(), p_117657_ -> {
        p_117657_.put(1, new ResourceLocation("stone"));
        p_117657_.put(2, new ResourceLocation("iron"));
        p_117657_.put(3, new ResourceLocation("gold"));
        p_117657_.put(4, new ResourceLocation("emerald"));
        p_117657_.put(5, new ResourceLocation("diamond"));
    });
    private final ResourceManager resourceManager;
    private final String path;

    public KhajiitProfessionLayer(RenderLayerParent<T, M> pRenderer, ResourceManager pResourceManager, String pPath) {
        super(pRenderer);
        this.resourceManager = pResourceManager;
        this.path = pPath;
    }

    public void render(
            PoseStack pPoseStack,
            MultiBufferSource pBuffer,
            int pPackedLight,
            T pLivingEntity,
            float pLimbSwing,
            float pLimbSwingAmount,
            float pPartialTicks,
            float pAgeInTicks,
            float pNetHeadYaw,
            float pHeadPitch
    ) {
        if (!pLivingEntity.isInvisible()) {
            VillagerData villagerdata = pLivingEntity.getVillagerData();
            VillagerType villagertype = villagerdata.getType();
            VillagerProfession villagerprofession = villagerdata.getProfession();
            M m = this.getParentModel();
            ResourceLocation resourcelocation = this.getResourceLocation("type", BuiltInRegistries.VILLAGER_TYPE.getKey(villagertype));
            renderColoredCutoutModel(m, resourcelocation, pPoseStack, pBuffer, pPackedLight, pLivingEntity, 1.0F, 1.0F, 1.0F);
            if (villagerprofession != VillagerProfession.NONE && !pLivingEntity.isBaby()) {
                ResourceLocation resourcelocation1 = this.getResourceLocation("profession", BuiltInRegistries.VILLAGER_PROFESSION.getKey(villagerprofession));
                renderColoredCutoutModel(m, resourcelocation1, pPoseStack, pBuffer, pPackedLight, pLivingEntity, 1.0F, 1.0F, 1.0F);
                if (villagerprofession != VillagerProfession.NITWIT) {
                    ResourceLocation resourcelocation2 = this.getResourceLocation(
                            "profession_level", LEVEL_LOCATIONS.get(Mth.clamp(villagerdata.getLevel(), 1, LEVEL_LOCATIONS.size()))
                    );
                    renderColoredCutoutModel(m, resourcelocation2, pPoseStack, pBuffer, pPackedLight, pLivingEntity, 1.0F, 1.0F, 1.0F);
                }
            }
        }
    }

    private ResourceLocation getResourceLocation(String pFolder, ResourceLocation pLocation) {
        return new ResourceLocation(Skyrimcraft.MODID, "textures/entity/" + this.path + "/" + pFolder + "/" + pLocation.getPath() + ".png");
    }
}
