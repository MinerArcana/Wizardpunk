package com.minerarcana.wizardpunk.renderer;

import com.minerarcana.wizardpunk.entity.neutral.ZephyrusEntity;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;

public class ZephyrusRenderer extends EntityRenderer<ZephyrusEntity> {
    private ResourceLocation TEXTURE = new ResourceLocation("air");

    public ZephyrusRenderer(EntityRendererManager renderManager) {
        super(renderManager);
    }

    @Override
    @Nonnull
    public ResourceLocation getEntityTexture(@Nonnull ZephyrusEntity entity) {
        return TEXTURE;
    }
}
