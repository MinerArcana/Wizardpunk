package com.minerarcana.wizardpunk;

import com.minerarcana.wizardpunk.content.WizardpunkEntities;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Wizardpunk.ID)
public class Wizardpunk {
    public static final String ID = "wizardpunk";

    public Wizardpunk() {
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();

        WizardpunkEntities.register(modBus);

        DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> modBus.addListener(ClientEventHandler::clientSetup));
    }
}
