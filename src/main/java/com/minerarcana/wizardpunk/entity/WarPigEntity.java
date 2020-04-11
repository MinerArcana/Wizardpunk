package com.minerarcana.wizardpunk.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.RavagerEntity;
import net.minecraft.world.World;

public class WarPigEntity extends RavagerEntity {
    public WarPigEntity(EntityType<? extends WarPigEntity> type, World world) {
        super(type, world);
    }
}
