package com.minerarcana.wizardpunk.api;

import com.google.common.collect.Maps;
import com.minerarcana.wizardpunk.api.cryptomancy.IMiniGameRenderer;
import com.minerarcana.wizardpunk.api.cryptomancy.MiniGame;
import com.minerarcana.wizardpunk.api.cryptomancy.MiniGameInstance;

import java.util.Map;

public class WizardpunkClientAPI {
    private static final Map<MiniGame, IMiniGameRenderer<MiniGameInstance>> MINI_GAME_RENDERS = Maps.newHashMap();

    public static IMiniGameRenderer<MiniGameInstance> getMiniGameRenderer(MiniGame miniGame) {
        return MINI_GAME_RENDERS.get(miniGame);
    }

    @SuppressWarnings("unchecked")
    public static <T extends MiniGameInstance> void registerMiniGameRenderer(MiniGame miniGame, IMiniGameRenderer<T> miniGameRenderer) {
        MINI_GAME_RENDERS.put(miniGame, (IMiniGameRenderer<MiniGameInstance>) miniGameRenderer);
    }
}
