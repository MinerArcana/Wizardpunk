package com.minerarcana.wizardpunk.entity.friendly;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public class MiniCatEntity extends CatEntity {
    public MiniCatEntity(EntityType<? extends MiniCatEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public void tick() {
        super.tick();
        if (!this.world.isRemote()) {
            if (this.getOwner() instanceof PlayerEntity) {
                ((PlayerEntity) this.getOwner()).addShoulderEntity(this.serializeNBT());
                this.remove();
            }
        }
    }
}
