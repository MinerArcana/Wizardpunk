package com.minerarcana.wizardpunk;

import com.minerarcana.wizardpunk.content.WizardpunkBlocks;
import com.minerarcana.wizardpunk.content.WizardpunkEntities;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import static com.minerarcana.wizardpunk.world.VillageAdditions.init;

@Mod(Wizardpunk.ID)
public class Wizardpunk {

    public static final String ID = "wizardpunk";

    public static final WizardpunkGroup ITEM_GROUP = new WizardpunkGroup(ItemGroup.GROUPS.length, "wizardpunk_group");

    public Wizardpunk() {
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::commonSetupEvent);
        WizardpunkEntities.register(modBus);
        WizardpunkBlocks.register(modBus);
        DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> modBus.addListener(ClientEventHandler::clientSetup));
    }

    public void commonSetupEvent(FMLCommonSetupEvent event) {
        init();
    }

}
