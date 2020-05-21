package com.minerarcana.wizardpunk.api.oppression;

import net.minecraft.entity.LivingEntity;

public interface IOppressionHandler {
    void onOppressed(LivingEntity oppressed, LivingEntity oppressor);
}
