package com.minerarcana.wizardpunk;

import com.minerarcana.wizardpunk.api.cryptomancy.MiniGame;
import com.minerarcana.wizardpunk.content.*;
import com.minerarcana.wizardpunk.datagen.WizardpunkGenerator;
import com.minerarcana.wizardpunk.event.ClientEventHandler;
import com.minerarcana.wizardpunk.event.EventHandler;
import com.minerarcana.wizardpunk.network.NetworkHandler;
import com.minerarcana.wizardpunk.world.VillageAdditions;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistry;
import net.minecraftforge.registries.RegistryBuilder;

@Mod(Wizardpunk.ID)
public class Wizardpunk {

    public static final String ID = "wizardpunk";

    public static final WizardpunkGroup ITEM_GROUP = new WizardpunkGroup(ID);

    public final NetworkHandler networkHandler;

    public static Wizardpunk instance;

    public Wizardpunk() {
        instance = this;
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        modBus.addListener(WizardpunkGenerator::registerGenerators);
        modBus.addListener(this::commonSetupEvent);
        modBus.addListener(this::newRegistryEvent);

        MinecraftForge.EVENT_BUS.addListener(EventHandler::onEnterChunk);

        DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> modBus.addListener(ClientEventHandler::clientSetup));
        networkHandler = new NetworkHandler();
    }

    public void commonSetupEvent(FMLCommonSetupEvent event) {
        VillageAdditions.init();
    }

    public void newRegistryEvent(RegistryEvent.NewRegistry newRegistryEvent) {
        new RegistryBuilder<MiniGame>()
                .setName(new ResourceLocation(Wizardpunk.ID, "mini_games"))
                .setType(MiniGame.class)
                .create();

        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();

        WizardpunkEntities.register(modBus);
        WizardpunkBlocks.register(modBus);
        WizardpunkItems.register(modBus);
        WizardpunkMiniGames.register(modBus);
        WizardpunkContainers.register(modBus);
    }

}
