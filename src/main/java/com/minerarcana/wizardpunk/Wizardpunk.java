package com.minerarcana.wizardpunk;

import com.minerarcana.wizardpunk.api.WizardpunkAPI;
import com.minerarcana.wizardpunk.api.cryptomancy.MiniGame;
import com.minerarcana.wizardpunk.api.oppression.OppressionHandlerIMCMessage;
import com.minerarcana.wizardpunk.content.*;
import com.minerarcana.wizardpunk.datagen.WizardpunkGenerator;
import com.minerarcana.wizardpunk.entity.friendly.OppressedVillagerEntity;
import com.minerarcana.wizardpunk.event.ClientEventHandler;
import com.minerarcana.wizardpunk.event.EventHandler;
import com.minerarcana.wizardpunk.network.NetworkHandler;
import com.minerarcana.wizardpunk.world.VillageAdditions;
import net.minecraft.entity.EntityType;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.RegistryBuilder;

import java.util.UUID;

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
        modBus.addListener(this::queueIMC);
        modBus.addListener(this::processIMC);

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

    public void queueIMC(InterModEnqueueEvent event) {
        InterModComms.sendTo(ID, WizardpunkAPI.OPPRESSION_HANDLER_ADD, () -> new OppressionHandlerIMCMessage<>(
                EntityType.VILLAGER, (oppressed, oppressor) -> {
            World world = oppressed.getEntityWorld();
            if (!world.isRemote()) {
                CompoundNBT entityNBT = oppressed.writeWithoutTypeId(new CompoundNBT());
                OppressedVillagerEntity villagerEntity = WizardpunkEntities.OPPRESSED_VILLAGER.get().create(world);
                if (villagerEntity != null) {
                    UUID uniqueId = villagerEntity.getUniqueID();
                    villagerEntity.read(entityNBT);
                    villagerEntity.setUniqueId(uniqueId);
                    villagerEntity.setPosition(oppressed.getPosX(), oppressed.getPosY(), oppressed.getPosZ());
                    oppressed.remove();
                    world.addEntity(villagerEntity);
                }
            }
        }));
    }

    public void processIMC(InterModProcessEvent event) {
        event.getIMCStream(WizardpunkAPI.OPPRESSION_HANDLER_ADD::equals)
                .forEach(imcMessage -> {
                    Object object = imcMessage.getMessageSupplier().get();
                    if (object instanceof OppressionHandlerIMCMessage) {
                        OppressionHandlerIMCMessage<?> handlerIMCMessage = (OppressionHandlerIMCMessage<?>) object;
                        WizardpunkAPI.addOppressionHandler(handlerIMCMessage.getEntityType(),
                                handlerIMCMessage.getOppressionHandler());
                    }
                });
    }

}
