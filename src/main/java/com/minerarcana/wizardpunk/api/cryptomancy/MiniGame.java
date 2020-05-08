package com.minerarcana.wizardpunk.api.cryptomancy;

import net.minecraftforge.registries.ForgeRegistryEntry;

import java.util.function.Supplier;

public class MiniGame extends ForgeRegistryEntry<MiniGame> {
    private final Supplier<MiniGameInstance> miniGameInstanceSupplier;

    public MiniGame(Supplier<MiniGameInstance> miniGameInstanceSupplier) {
        this.miniGameInstanceSupplier = miniGameInstanceSupplier;
    }

    public MiniGameInstance createInstance() {
        return miniGameInstanceSupplier.get();
    }
}
