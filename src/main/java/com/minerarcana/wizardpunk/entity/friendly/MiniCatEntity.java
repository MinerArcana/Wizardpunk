package com.minerarcana.wizardpunk.entity.friendly;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.world.World;

public class MiniCatEntity extends CatEntity {
    public MiniCatEntity(EntityType<? extends MiniCatEntity> entityType, World world) {
        super(entityType, world);
    }
}
