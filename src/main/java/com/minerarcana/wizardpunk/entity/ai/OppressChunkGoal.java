package com.minerarcana.wizardpunk.entity.ai;

import com.minerarcana.wizardpunk.api.WizardpunkAPI;
import com.minerarcana.wizardpunk.api.oppression.OppressionWorldSavedData;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.goal.Goal;

public class OppressChunkGoal extends Goal {
    private final Entity oppressor;

    public OppressChunkGoal(Entity oppressor) {
        this.oppressor = oppressor;
    }

    @Override
    public boolean shouldExecute() {
        OppressionWorldSavedData oppressionWorldSavedData = WizardpunkAPI.getOppressionData(oppressor.getEntityWorld());
        if (oppressionWorldSavedData != null) {
            return !oppressionWorldSavedData.isOppressed(oppressor.chunkCoordX, oppressor.chunkCoordZ);
        }
        return false;
    }

    @Override
    public void startExecuting() {
        OppressionWorldSavedData oppressionWorldSavedData = WizardpunkAPI.getOppressionData(oppressor.getEntityWorld());
        if (oppressionWorldSavedData != null) {
            oppressionWorldSavedData.oppress(oppressor.chunkCoordX, oppressor.chunkCoordZ);
        }
    }
}
