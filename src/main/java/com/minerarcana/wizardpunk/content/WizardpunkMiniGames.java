package com.minerarcana.wizardpunk.content;

import com.minerarcana.wizardpunk.Wizardpunk;
import com.minerarcana.wizardpunk.api.WizardpunkAPI;
import com.minerarcana.wizardpunk.api.cryptomancy.MiniGame;
import com.minerarcana.wizardpunk.magic.cryptomancy.numeramancy.NumeramancyMiniGameInstance;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;

@SuppressWarnings("unused")
public class WizardpunkMiniGames {
    private static final DeferredRegister<MiniGame> MINI_GAMES = new DeferredRegister<>(WizardpunkAPI.MINI_GAMES.get(),
            Wizardpunk.ID);

    public static final RegistryObject<MiniGame> NUMERAMANCY = MINI_GAMES.register("numeramancy",
            () -> new MiniGame(NumeramancyMiniGameInstance::new));

    public static void register(IEventBus modBus) {
        MINI_GAMES.register(modBus);
    }
}
