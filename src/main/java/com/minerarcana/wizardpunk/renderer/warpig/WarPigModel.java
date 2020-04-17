package com.minerarcana.wizardpunk.renderer.warpig;

import com.google.common.collect.ImmutableList;
import com.minerarcana.wizardpunk.entity.WarPigEntity;
import net.minecraft.client.renderer.entity.model.SegmentedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;

@OnlyIn(Dist.CLIENT)
public class WarPigModel extends SegmentedModel<WarPigEntity> {
    private final ModelRenderer head;
    private final ModelRenderer lowerJaw;
    private final ModelRenderer lowerBack;
    private final ModelRenderer rearRightLeg;
    private final ModelRenderer rearLeftLeg;
    private final ModelRenderer frontRightLeg;
    private final ModelRenderer frontLeftLeg;
    private final ModelRenderer neck;

    public WarPigModel() {
        this.textureWidth = 256;
        this.textureHeight = 256;
        this.neck = new ModelRenderer(this);
        this.neck.setRotationPoint(0.0F, -7.0F, -1.5F);
        this.neck.setTextureOffset(68, 73).addBox(-5.0F, -1.0F, -18.0F, 10.0F, 10.0F, 18.0F, 0.0F);
        this.head = new ModelRenderer(this);
        this.head.setRotationPoint(0.0F, 16.0F, -17.0F);
        this.head.setTextureOffset(0, 0).addBox(-8.0F, -20.0F, -14.0F, 16.0F, 20.0F, 16.0F, 0.0F);
        this.head.setTextureOffset(0, 0).addBox(-2.0F, -6.0F, -18.0F, 4.0F, 8.0F, 4.0F, 0.0F);
        ModelRenderer rightHorn = new ModelRenderer(this);
        rightHorn.setRotationPoint(-10.0F, -14.0F, -8.0F);
        rightHorn.setTextureOffset(74, 55).addBox(0.0F, -14.0F, -2.0F, 2.0F, 14.0F, 4.0F, 0.0F);
        rightHorn.rotateAngleX = 1.0995574F;
        this.head.addChild(rightHorn);
        ModelRenderer leftHorn = new ModelRenderer(this);
        leftHorn.mirror = true;
        leftHorn.setRotationPoint(8.0F, -14.0F, -8.0F);
        leftHorn.setTextureOffset(74, 55).addBox(0.0F, -14.0F, -2.0F, 2.0F, 14.0F, 4.0F, 0.0F);
        leftHorn.rotateAngleX = 1.0995574F;
        this.head.addChild(leftHorn);
        this.lowerJaw = new ModelRenderer(this);
        this.lowerJaw.setRotationPoint(0.0F, -2.0F, 2.0F);
        this.lowerJaw.setTextureOffset(0, 36).addBox(-8.0F, 0.0F, -16.0F, 16.0F, 3.0F, 16.0F, 0.0F);
        this.head.addChild(this.lowerJaw);
        this.neck.addChild(this.head);
        this.lowerBack = new ModelRenderer(this);
        this.lowerBack.setTextureOffset(0, 55).addBox(-7.0F, -10.0F, -7.0F, 14.0F, 16.0F, 20.0F, 0.0F);
        this.lowerBack.setTextureOffset(0, 91).addBox(-6.0F, 6.0F, -7.0F, 12.0F, 13.0F, 18.0F, 0.0F);
        this.lowerBack.setRotationPoint(0.0F, 1.0F, 2.0F);
        this.rearRightLeg = new ModelRenderer(this, 96, 0);
        this.rearRightLeg.addBox(-3.0F, 0.0F, -5.0F, 12.0F, 37.0F, 12.0F, 0.0F);
        this.rearRightLeg.setRotationPoint(-12.0F, -13.0F, 18.0F);
        this.rearLeftLeg = new ModelRenderer(this, 96, 0);
        this.rearLeftLeg.mirror = true;
        this.rearLeftLeg.addBox(-5.0F, 0.0F, -5.0F, 12.0F, 37.0F, 12.0F, 0.0F);
        this.rearLeftLeg.setRotationPoint(8.0F, -13.0F, 18.0F);
        this.frontRightLeg = new ModelRenderer(this, 64, 0);
        this.frontRightLeg.addBox(-8.0F, 0.0F, -6.0F, 12.0F, 37.0F, 12.0F, 0.0F);
        this.frontRightLeg.setRotationPoint(-8.0F, -13.0F, -5.0F);
        this.frontLeftLeg = new ModelRenderer(this, 64, 0);
        this.frontLeftLeg.mirror = true;
        this.frontLeftLeg.addBox(-8.0F, 0.0F, -6.0F, 12.0F, 37.0F, 12.0F, 0.0F);
        this.frontLeftLeg.setRotationPoint(12.0F, -13.0F, -5.0F);
    }

    @Override
    @Nonnull
    public Iterable<ModelRenderer> getParts() {
        return ImmutableList.of(this.neck, this.lowerBack, this.rearRightLeg, this.rearLeftLeg, this.frontRightLeg, this.frontLeftLeg);
    }

    /**
     * Sets this entity's model rotation angles
     */
    @Override
    public void setRotationAngles(@Nonnull WarPigEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks,
                                  float netHeadYaw, float headPitch) {
        this.head.rotateAngleX = headPitch * ((float)Math.PI / 180F);
        this.head.rotateAngleY = netHeadYaw * ((float)Math.PI / 180F);
        this.lowerBack.rotateAngleX = ((float)Math.PI / 2F);
        float f = 0.4F * limbSwingAmount;
        this.rearRightLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * f;
        this.rearLeftLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * f;
        this.frontRightLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * f;
        this.frontLeftLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * f;
    }

    @Override
    public void setLivingAnimations(@Nonnull WarPigEntity entity, float limbSwing, float limbSwingAmount, float partialTick) {
        super.setLivingAnimations(entity, limbSwing, limbSwingAmount, partialTick);
        int i = entity.func_213684_dX();
        int j = entity.func_213687_eg();
        int l = entity.func_213683_l();
        if (l > 0) {
            float f = this.func_217167_a((float)l - partialTick);
            float f1 = (1.0F + f) * 0.5F;
            float f2 = f1 * f1 * f1 * 12.0F;
            float f3 = f2 * MathHelper.sin(this.neck.rotateAngleX);
            this.neck.rotationPointZ = -6.5F + f2;
            this.neck.rotationPointY = -7.0F - f3;
            float f4 = MathHelper.sin(((float)l - partialTick) / 10.0F * (float)Math.PI * 0.25F);
            this.lowerJaw.rotateAngleX = ((float)Math.PI / 2F) * f4;
            if (l > 5) {
                this.lowerJaw.rotateAngleX = MathHelper.sin(((float)(-4 + l) - partialTick) / 4.0F) * (float)Math.PI * 0.4F;
            } else {
                this.lowerJaw.rotateAngleX = 0.15707964F * MathHelper.sin((float)Math.PI * ((float)l - partialTick) / 10.0F);
            }
        } else {
            float f6 = -1.0F * MathHelper.sin(this.neck.rotateAngleX);
            this.neck.rotationPointX = 0.0F;
            this.neck.rotationPointY = -7.0F - f6;
            this.neck.rotationPointZ = 5.5F;
            boolean flag = i > 0;
            this.neck.rotateAngleX = flag ? 0.21991149F : 0.0F;
            this.lowerJaw.rotateAngleX = (float)Math.PI * (flag ? 0.05F : 0.01F);
            if (flag) {
                double d0 = (double)i / 40.0D;
                this.neck.rotationPointX = (float)Math.sin(d0 * 10.0D) * 3.0F;
            } else if (j > 0) {
                float f7 = MathHelper.sin(((float)(20 - j) - partialTick) / 20.0F * (float)Math.PI * 0.25F);
                this.lowerJaw.rotateAngleX = ((float)Math.PI / 2F) * f7;
            }
        }

    }

    private float func_217167_a(float p_217167_1_) {
        return (Math.abs(p_217167_1_ % (float) 10.0 - (float) 10.0 * 0.5F) - (float) 10.0 * 0.25F) / ((float) 10.0 * 0.25F);
    }
}
