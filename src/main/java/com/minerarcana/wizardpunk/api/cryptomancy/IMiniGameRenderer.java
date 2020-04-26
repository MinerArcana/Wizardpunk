package com.minerarcana.wizardpunk.api.cryptomancy;

public interface IMiniGameRenderer<T extends MiniGameInstance> {
    void drawBackgroundLayer(IMiniGameScreen screen, T miniGameInstance, float partialTicks, int mouseX, int mouseY);

    void drawForegroundLayer(IMiniGameScreen screen, T miniGameInstance, int mouseX, int mouseY);
}
