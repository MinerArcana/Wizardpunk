package com.minerarcana.wizardpunk.api;

import com.minerarcana.wizardpunk.api.cryptomancy.MiniGame;
import com.minerarcana.wizardpunk.api.oppression.OppressionWorldSavedData;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WizardpunkAPI {
    public static final Logger LOGGER = LogManager.getLogger("wizardpunk-api");

    public static final Lazy<IForgeRegistry<MiniGame>> MINI_GAMES = Lazy.of(() -> RegistryManager.ACTIVE.getRegistry(MiniGame.class));

    private static final OppressionWorldSavedData clientData = new OppressionWorldSavedData();

    public static OppressionWorldSavedData getOppressionData(World world) {
        if (world instanceof ServerWorld) {
            return ((ServerWorld) world).getSavedData().getOrCreate(OppressionWorldSavedData::new, OppressionWorldSavedData.NAME);
        } else {
            return clientData;
        }
    }
}
