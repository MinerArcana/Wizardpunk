package com.minerarcana.wizardpunk.renderer.zephyrus;

import com.minerarcana.wizardpunk.Wizardpunk;
import com.minerarcana.wizardpunk.entity.neutral.ZephyrusEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.culling.ClippingHelperImpl;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.data.EmptyModelData;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

public class ZephyrusRenderer extends EntityRenderer<ZephyrusEntity> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(Wizardpunk.ID, "textures/entity/zephyrus.png");

    public ZephyrusRenderer(EntityRendererManager renderManager) {
        super(renderManager);
    }

    @Override
    @ParametersAreNonnullByDefault
    public void render(ZephyrusEntity entity, float entityYaw, float partialTicks, MatrixStack matrixStack,
                       IRenderTypeBuffer buffer, int packedLight) {
        matrixStack.push();
        Minecraft.getInstance().getBlockRendererDispatcher().renderBlock(Blocks.NETHER_PORTAL.getDefaultState(),
                matrixStack, buffer, packedLight, OverlayTexture.NO_OVERLAY, EmptyModelData.INSTANCE);
        matrixStack.pop();
    }

    @Override
    @Nonnull
    public ResourceLocation getEntityTexture(@Nonnull ZephyrusEntity entity) {
        return TEXTURE;
    }

    @Override
    @ParametersAreNonnullByDefault
    public boolean shouldRender(ZephyrusEntity livingEntity, ClippingHelperImpl camera, double camX, double camY,
                                double camZ) {
        return true;
    }
}
