package com.minerarcana.wizardpunk.api.oppression;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;

public class OppressionHandlerIMCMessage<T extends LivingEntity> {
    private final EntityType<T> entityType;
    private final IOppressionHandler oppressionHandler;

    public OppressionHandlerIMCMessage(EntityType<T> entityType, IOppressionHandler oppressionHandler) {
        this.entityType = entityType;
        this.oppressionHandler = oppressionHandler;
    }

    public EntityType<T> getEntityType() {
        return entityType;
    }

    public IOppressionHandler getOppressionHandler() {
        return oppressionHandler;
    }
}
