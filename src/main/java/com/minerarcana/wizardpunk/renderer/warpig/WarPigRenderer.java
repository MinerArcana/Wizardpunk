package com.minerarcana.wizardpunk.renderer.warpig;

import com.minerarcana.wizardpunk.Wizardpunk;
import com.minerarcana.wizardpunk.entity.WarPigEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;

public class WarPigRenderer extends MobRenderer<WarPigEntity, WarPigModel> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(Wizardpunk.ID, "textures/entity/war_pig.png");

    public WarPigRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new WarPigModel(), 1.1F);
    }

    @Override
    @Nonnull
    public ResourceLocation getEntityTexture(@Nonnull WarPigEntity entity) {
        return TEXTURE;
    }
}