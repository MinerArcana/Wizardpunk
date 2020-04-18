package com.minerarcana.wizardpunk;

import com.minerarcana.wizardpunk.content.WizardpunkBlocks;
import com.minerarcana.wizardpunk.content.WizardpunkEntities;
import com.minerarcana.wizardpunk.content.WizardpunkItems;
import com.minerarcana.wizardpunk.datagen.WizardpunkGenerator;
import com.minerarcana.wizardpunk.world.VillageAdditions;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Wizardpunk.ID)
public class Wizardpunk {

    public static final String ID = "wizardpunk";

    public static final WizardpunkGroup ITEM_GROUP = new WizardpunkGroup(ID);

    public Wizardpunk() {
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::commonSetupEvent);
        WizardpunkEntities.register(modBus);
        WizardpunkBlocks.register(modBus);
        WizardpunkItems.register(modBus);

        modBus.addListener(WizardpunkGenerator::registerGenerators);

        DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> modBus.addListener(ClientEventHandler::clientSetup));

    }

    public void commonSetupEvent(FMLCommonSetupEvent event) {
        VillageAdditions.init();
    }

}
