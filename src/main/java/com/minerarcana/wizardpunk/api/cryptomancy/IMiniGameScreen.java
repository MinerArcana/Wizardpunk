package com.minerarcana.wizardpunk.api.cryptomancy;

import net.minecraft.client.gui.screen.Screen;

public interface IMiniGameScreen {
    int getTop();

    int getLeft();

    int getHeight();

    int getWidth();

    Screen getScreen();
}
