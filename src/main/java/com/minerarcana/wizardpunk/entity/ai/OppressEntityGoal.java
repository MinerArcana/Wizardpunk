package com.minerarcana.wizardpunk.entity.ai;

import com.minerarcana.wizardpunk.api.WizardpunkAPI;
import com.minerarcana.wizardpunk.api.oppression.IOppressionHandler;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.util.Hand;

import javax.annotation.Nonnull;

public class OppressEntityGoal<T extends CreatureEntity> extends MeleeAttackGoal {
    private final T oppressor;

    public OppressEntityGoal(T oppressor) {
        super(oppressor,1.0D, false);
        this.oppressor = oppressor;
    }

    @Override
    public boolean shouldExecute() {
        return super.shouldExecute() && oppressor.getAttackTarget() != null && WizardpunkAPI.getOppressionHandler(
                oppressor.getAttackTarget().getType()) != null;
    }

    @Override
    protected void checkAndPerformAttack(@Nonnull LivingEntity enemy, double distToEnemySqr) {
        double d0 = this.getAttackReachSqr(enemy);
        if (distToEnemySqr <= d0 && this.attackTick <= 0) {
            this.attackTick = 20;
            this.attacker.swingArm(Hand.MAIN_HAND);
            IOppressionHandler oppressionHandler = WizardpunkAPI.getOppressionHandler(enemy.getType());
            if (oppressionHandler != null) {
                oppressionHandler.onOppressed(enemy, oppressor);
            }
        }

    }
}
