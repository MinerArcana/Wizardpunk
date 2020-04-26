package com.minerarcana.wizardpunk.network;

import com.minerarcana.wizardpunk.Wizardpunk;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.PacketDistributor;
import net.minecraftforge.fml.network.simple.SimpleChannel;

import java.util.UUID;

public class NetworkHandler {
    private static final String VERSION = "1";
    private final SimpleChannel channel;

    public NetworkHandler() {
        this.channel = NetworkRegistry.newSimpleChannel(
                new ResourceLocation(Wizardpunk.ID, "network_handler"),
                () -> VERSION,
                VERSION::equals,
                VERSION::equals
        );

        this.channel.messageBuilder(CryptomancyResultMessage.class, 0)
                .decoder(CryptomancyResultMessage::decode)
                .encoder(CryptomancyResultMessage::encode)
                .consumer(CryptomancyResultMessage::consume)
                .add();
    }

    public void sendHackResult(boolean hacked, UUID targetUniqueId) {
        this.channel.send(PacketDistributor.SERVER.noArg(), new CryptomancyResultMessage(hacked, targetUniqueId));
    }
}
