package com.minerarcana.wizardpunk.renderer;

import com.minerarcana.wizardpunk.entity.oppressor.EnforcerEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.IllagerRenderer;
import net.minecraft.client.renderer.entity.layers.HeldItemLayer;
import net.minecraft.client.renderer.entity.model.IllagerModel;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;

public class EnforcerRenderer extends IllagerRenderer<EnforcerEntity> {
    private static final ResourceLocation TEXTURE = new ResourceLocation("textures/entity/illager/pillager.png");

    public EnforcerRenderer(EntityRendererManager rendererManager) {
        super(rendererManager, new IllagerModel<>(0.0F, 0.0F, 64, 64), 0.5F);
        this.addLayer(new HeldItemLayer<>(this));
    }

    @Override
    @Nonnull
    public ResourceLocation getEntityTexture(@Nonnull EnforcerEntity entity) {
        return TEXTURE;
    }
}
