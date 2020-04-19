package com.minerarcana.wizardpunk.renderer.layer;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.minerarcana.wizardpunk.content.WizardpunkEntities;
import com.minerarcana.wizardpunk.entity.friendly.MiniCatEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Quaternion;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.World;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class MiniCatLayer<T extends PlayerEntity> extends LayerRenderer<T, PlayerModel<T>> {
    private final Cache<UUID, MiniCatEntity> miniCats;

    public MiniCatLayer(IEntityRenderer<T, PlayerModel<T>> renderer) {
        super(renderer);
        this.miniCats = CacheBuilder.newBuilder()
                .expireAfterAccess(20, TimeUnit.SECONDS)
                .build();
    }

    @Override
    @ParametersAreNonnullByDefault
    public void render(MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, T entityLiving,
                       float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw,
                       float headPitch) {
        this.renderCat(matrixStack, buffer, packedLight, entityLiving, limbSwing, limbSwingAmount, netHeadYaw, headPitch, true);
        this.renderCat(matrixStack, buffer, packedLight, entityLiving, limbSwing, limbSwingAmount, netHeadYaw, headPitch, false);
    }

    private void renderCat(MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, T entityLiving,
                           float limbSwing, float limbSwingAmount, float netHeadYaw, float headPitch, boolean leftShoulder) {
        CompoundNBT compoundNBT = leftShoulder ? entityLiving.getLeftShoulderEntity() : entityLiving.getRightShoulderEntity();
        EntityType.byKey(compoundNBT.getString("id")).filter((p_215344_0_) -> p_215344_0_ == WizardpunkEntities.MINI_CAT.get()).ifPresent((p_229137_11_) -> {
            matrixStack.push();
            matrixStack.rotate(new Quaternion(new Vector3f(1F, 0F, 0F), 180, true));
            matrixStack.scale(0.35F, 0.35F, 0.35F);
            matrixStack.translate(leftShoulder ? (double) 1.2F : (double) -1.2F, entityLiving.isCrouching() ? (double) .1F : .5D, 0.0D);

            try {
                MiniCatEntity miniCatEntity = miniCats.get(compoundNBT.getUniqueId("uniqueId"), () -> this.loadMiniCat(compoundNBT));
                Minecraft.getInstance().getRenderManager().renderEntityStatic(miniCatEntity, 0.0D, 0.0D, 0.0D,
                        0.0F, Minecraft.getInstance().getRenderPartialTicks(), matrixStack, buffer, packedLight);
            } catch (ExecutionException e) {
                //Do nothing.
            }
            matrixStack.pop();
        });
    }

    private MiniCatEntity loadMiniCat(CompoundNBT compoundNBT) {
        World world = Minecraft.getInstance().world;
        if (world != null) {
            return EntityType.loadEntityUnchecked(compoundNBT, world)
                    .filter(entity -> entity instanceof MiniCatEntity)
                    .map(entity -> (MiniCatEntity) entity)
                    .map(miniCatEntity -> {
                        miniCatEntity.setNoAI(true);
                        miniCatEntity.setSitting(true);
                        miniCatEntity.setTamed(true);
                        return miniCatEntity;
                    })
                    .orElse(null);
        }

        return null;
    }
}
