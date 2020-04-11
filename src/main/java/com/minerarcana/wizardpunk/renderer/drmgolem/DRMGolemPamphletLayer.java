package com.minerarcana.wizardpunk.renderer.drmgolem;

import com.minerarcana.wizardpunk.content.WizardpunkItems;
import com.minerarcana.wizardpunk.entity.DRMGolemEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.IronGolemModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.ParametersAreNonnullByDefault;

@OnlyIn(Dist.CLIENT)
public class DRMGolemPamphletLayer extends LayerRenderer<DRMGolemEntity, IronGolemModel<DRMGolemEntity>> {
    private final ItemStack pamphletStack;

    public DRMGolemPamphletLayer(IEntityRenderer<DRMGolemEntity, IronGolemModel<DRMGolemEntity>> entityRenderer) {
        super(entityRenderer);
        this.pamphletStack = new ItemStack(WizardpunkItems.PAMPHLET.get());
    }

    @Override
    @ParametersAreNonnullByDefault
    public void render(MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLightIn, DRMGolemEntity golemEntity,
                       float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw,
                       float headPitch) {
        if (golemEntity.getHoldRoseTick() != 0) {
            matrixStack.push();
            ModelRenderer modelrenderer = this.getEntityModel().getArmHoldingRose();
            modelrenderer.translateRotate(matrixStack);
            matrixStack.translate(-1.1875D, 1.0625D, -0.9375D);
            matrixStack.translate(0.5D, 0.5D, 0.5D);
            float f = 0.5F;
            matrixStack.scale(0.5F, 0.5F, 0.5F);
            matrixStack.rotate(Vector3f.XP.rotationDegrees(-90.0F));
            matrixStack.translate(-0.5D, -0.5D, -0.5D);
            Minecraft.getInstance().getItemRenderer().renderItem(pamphletStack, TransformType.FIRST_PERSON_RIGHT_HAND,
                    packedLightIn, OverlayTexture.NO_OVERLAY, matrixStack, buffer);
            matrixStack.pop();
        }
    }
}
