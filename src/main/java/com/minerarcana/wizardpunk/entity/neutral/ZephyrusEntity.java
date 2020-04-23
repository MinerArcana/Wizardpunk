package com.minerarcana.wizardpunk.entity.neutral;

import com.minerarcana.wizardpunk.Wizardpunk;
import com.minerarcana.wizardpunk.content.WizardpunkItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.*;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nonnull;

public class ZephyrusEntity extends Entity {
    private static final DataParameter<Integer> OWNER_ID = EntityDataManager.createKey(ZephyrusEntity.class,
            DataSerializers.VARINT);

    private int timeWithoutOwner = 0;

    public ZephyrusEntity(EntityType<? extends ZephyrusEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public void tick() {
        super.tick();
        LivingEntity owner = this.getOwner();
        if (owner != null) {
            timeWithoutOwner = 0;
            ItemStack itemStack = owner.getActiveItemStack();
            if (!itemStack.isEmpty() && itemStack.getItem() == WizardpunkItems.ZEPHYRUS.get()) {
                Vec3d lookLocation = owner.getPositionVec().add(owner.getLook(1.0F).scale(50F));
                Vec3d ownerLocation = new Vec3d(this.getPosX(), this.getPosYEye(), this.getPosZ());
                BlockRayTraceResult rayTraceResult = this.world.rayTraceBlocks(new RayTraceContext(ownerLocation,
                        lookLocation, RayTraceContext.BlockMode.COLLIDER, RayTraceContext.FluidMode.ANY, owner));
                if (rayTraceResult.getType() == RayTraceResult.Type.MISS) {
                    this.setPosition(lookLocation.x, lookLocation.y, lookLocation.z);
                } else if (rayTraceResult.getType() == RayTraceResult.Type.BLOCK) {
                    BlockPos newPos = rayTraceResult.getPos().offset(rayTraceResult.getFace());
                    this.setPosition(newPos.getX(), newPos.getY(), newPos.getZ());
                }
            } else {
                owner.setPosition(this.getPosX(), this.getPosY(), this.getPosZ());
                this.remove();
            }
        } else {
            timeWithoutOwner++;
            if (timeWithoutOwner >= 20) {
                this.remove();
            }
        }
    }

    @Override
    protected void registerData() {
        this.dataManager.register(OWNER_ID, -1);
    }

    @Override
    protected void readAdditional(@Nonnull CompoundNBT compoundNBT) {

    }

    @Override
    protected void writeAdditional(@Nonnull CompoundNBT compoundNBT) {

    }

    public void setOwner(LivingEntity livingEntity) {
        this.dataManager.set(OWNER_ID, livingEntity.getEntityId());
    }

    public LivingEntity getOwner() {
        int entityId = this.dataManager.get(OWNER_ID);
        if (entityId > 0) {
            Entity entity = this.getEntityWorld().getEntityByID(entityId);
            if (entity instanceof LivingEntity) {
                return (LivingEntity) entity;
            }
        }
        return null;
    }

    @Override
    @Nonnull
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
