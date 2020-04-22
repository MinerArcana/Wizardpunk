package com.minerarcana.wizardpunk.event;

import com.minerarcana.wizardpunk.api.WizardpunkAPI;
import com.minerarcana.wizardpunk.api.oppression.OppressionWorldSavedData;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.event.entity.EntityEvent;

public class EventHandler {
    public static void onEnterChunk(EntityEvent.EnteringChunk enteringChunk) {
        if (enteringChunk.getEntity() instanceof PlayerEntity && enteringChunk.getEntity().world != null) {
            OppressionWorldSavedData oppressionData = WizardpunkAPI.getOppressionData(enteringChunk.getEntity().world);
            if (oppressionData != null && oppressionData.isOppressed(enteringChunk.getNewChunkX(), enteringChunk.getNewChunkZ())) {
                ((PlayerEntity) enteringChunk.getEntity()).sendStatusMessage(new TranslationTextComponent(
                        "text.wizardpunk.entering_oppressed_chunk"), true);
            }
        }
    }
}
