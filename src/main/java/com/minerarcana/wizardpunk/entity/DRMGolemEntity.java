package com.minerarcana.wizardpunk.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.world.World;

public class DRMGolemEntity extends IronGolemEntity {
    public DRMGolemEntity(EntityType<? extends DRMGolemEntity> type, World worldIn) {
        super(type, worldIn);
    }

    @Override
    public boolean isPlayerCreated() {
        return false;
    }

    @Override
    public void setPlayerCreated(boolean playerCreated) {
    }
}
