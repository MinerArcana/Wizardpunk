package com.minerarcana.wizardpunk.entity.ai;

import com.minerarcana.wizardpunk.entity.WarPigEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;

public class WarPigAttackGoal extends MeleeAttackGoal {
    public WarPigAttackGoal(WarPigEntity warPigEntity) {
        super(warPigEntity, 1.0D, true);
    }

    protected double getAttackReachSqr(LivingEntity attackTarget) {
        float f = super.attacker.getWidth() - 0.1F;
        return f * 2.0F * f * 2.0F + attackTarget.getWidth();
    }
}
