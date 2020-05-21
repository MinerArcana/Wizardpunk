package com.minerarcana.wizardpunk.api;

import com.google.common.collect.Maps;
import com.minerarcana.wizardpunk.api.cryptomancy.MiniGame;
import com.minerarcana.wizardpunk.api.oppression.IOppressionHandler;
import com.minerarcana.wizardpunk.api.oppression.OppressionWorldSavedData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

public class WizardpunkAPI {
    public static final String OPPRESSION_HANDLER_ADD = "OPPRESSION_HANDLER_ADD";

    public static final Logger LOGGER = LogManager.getLogger("wizardpunk-api");

    public static final Lazy<IForgeRegistry<MiniGame>> MINI_GAMES = Lazy.of(() -> RegistryManager.ACTIVE.getRegistry(MiniGame.class));

    private static final OppressionWorldSavedData clientData = new OppressionWorldSavedData();
    private static final Map<EntityType<? extends LivingEntity>, IOppressionHandler> oppressionHandlers = Maps.newHashMap();

    public static OppressionWorldSavedData getOppressionData(World world) {
        if (world instanceof ServerWorld) {
            return ((ServerWorld) world).getSavedData().getOrCreate(OppressionWorldSavedData::new, OppressionWorldSavedData.NAME);
        } else {
            return clientData;
        }
    }

    public static void addOppressionHandler(EntityType<? extends LivingEntity> entityType, IOppressionHandler oppressionHandler) {
        oppressionHandlers.put(entityType, oppressionHandler);
    }

    public static IOppressionHandler getOppressionHandler(EntityType<?> entityType) {
        return oppressionHandlers.get(entityType);
    }
}
