package com.minerarcana.wizardpunk.renderer.drmgolem;

import com.minerarcana.wizardpunk.Wizardpunk;
import com.minerarcana.wizardpunk.entity.DRMGolemEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.IronGolemModel;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;

public class DRMGolemRenderer extends MobRenderer<DRMGolemEntity, IronGolemModel<DRMGolemEntity>> {
    private static final ResourceLocation DRM_GOLEM_TEXTURES = new ResourceLocation(Wizardpunk.ID,
            "textures/entity/drm_golem.png");

    public DRMGolemRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new IronGolemModel<>(), 0.7F);
        this.addLayer(new DRMGolemPamphletLayer(this));
    }

    @Override
    @Nonnull
    public ResourceLocation getEntityTexture(@Nonnull DRMGolemEntity entity) {
        return DRM_GOLEM_TEXTURES;
    }

    @Override
    protected void applyRotations(@Nonnull DRMGolemEntity entityLiving, @Nonnull MatrixStack matrixStack, float ageInTicks,
                                  float rotationYaw, float partialTicks) {
        super.applyRotations(entityLiving, matrixStack, ageInTicks, rotationYaw, partialTicks);
        if (!((double) entityLiving.limbSwingAmount < 0.01D)) {
            float f1 = entityLiving.limbSwing - entityLiving.limbSwingAmount * (1.0F - partialTicks) + 6.0F;
            float f2 = (Math.abs(f1 % 13.0F - 6.5F) - 3.25F) / 3.25F;
            matrixStack.rotate(Vector3f.ZP.rotationDegrees(6.5F * f2));
        }
    }
}
