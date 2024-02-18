package com.ryankshah.skyrimcraft.curios.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.client.ICurioRenderer;

public class NecklaceRenderer implements ICurioRenderer {

    @Override
    public <T extends LivingEntity, M extends EntityModel<T>> void render(ItemStack stack,
                                                                          SlotContext slotContext,
                                                                          PoseStack matrixStack,
                                                                          RenderLayerParent<T, M> renderLayerParent,
                                                                          MultiBufferSource renderTypeBuffer,
                                                                          int light, float limbSwing,
                                                                          float limbSwingAmount,
                                                                          float partialTicks,
                                                                          float ageInTicks,
                                                                          float netHeadYaw,
                                                                          float headPitch) {
        matrixStack.pushPose();
        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
        LivingEntity living = slotContext.entity();

        if (living instanceof Player) {

//            matrixStack.translate(0.5F + (1 *limbSwingAmount), 0.5F, 0.0F);
            matrixStack.scale(0.625f, 0.625f, 0.625f);
//            matrixStack.translate(0.0F, 0.0F, 0.25F); // on the back...
            matrixStack.mulPose(Axis.XP.rotationDegrees(0F));
//            matrixStack.mulPose(Axis.XN.rotationDegrees(180f));
            matrixStack.mulPose(Axis.ZN.rotationDegrees(205f));
            matrixStack.mulPose(Axis.YP.rotationDegrees(90f));
            matrixStack.translate(0.29F + (living.getItemBySlot(EquipmentSlot.CHEST).isEmpty() ? 0.0 : 0.075f), -0.3F, -0.02f);

            ICurioRenderer.translateIfSneaking(matrixStack, living);
            ICurioRenderer.rotateIfSneaking(matrixStack, living);

            ICurioRenderer.followBodyRotations(living, (HumanoidModel<LivingEntity>) renderLayerParent.getModel());

            BakedModel bakedmodel = Minecraft.getInstance().getItemRenderer().getModel(stack, living.level(), living, 10);
            MultiBufferSource.BufferSource buffer = Minecraft.getInstance().renderBuffers().bufferSource();
            itemRenderer.render(stack, ItemDisplayContext.FIRST_PERSON_LEFT_HAND, true, matrixStack, buffer, light, OverlayTexture.NO_OVERLAY, bakedmodel);
        }
        matrixStack.popPose();
    }
}
