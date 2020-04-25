package com.minerarcana.wizardpunk.api.cryptomancy;

import javax.annotation.Nonnull;

public abstract class MiniGameInstance {
    protected MiniGameInstance() {
    }

    public abstract void setup();

    public abstract void setDifficultyLevel(int difficultyLevel);

    public abstract void start();

    public abstract void tick();

    @Nonnull
    public abstract MiniGameStatus getStatus();
}
