package com.minerarcana.wizardpunk.network;

import com.minerarcana.wizardpunk.entity.oppressor.DRMGolemEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.GolemEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.UUID;
import java.util.function.Supplier;

public class CryptomancyResultMessage {
    private final boolean hacked;
    private final UUID targetUniqueId;

    public CryptomancyResultMessage(boolean hacked, UUID targetUniqueId) {
        this.hacked = hacked;
        this.targetUniqueId = targetUniqueId;
    }

    public static CryptomancyResultMessage decode(PacketBuffer packetBuffer) {
        return new CryptomancyResultMessage(packetBuffer.readBoolean(), packetBuffer.readUniqueId());
    }

    public void encode(PacketBuffer packetBuffer) {
        packetBuffer.writeBoolean(hacked);
        packetBuffer.writeUniqueId(targetUniqueId);
    }

    public boolean consume(Supplier<NetworkEvent.Context> contextSupplier) {
        contextSupplier.get().enqueueWork(() -> {
            ServerPlayerEntity playerEntity = contextSupplier.get().getSender();
            if (playerEntity != null) {
                ServerWorld world = playerEntity.getServerWorld();
                Entity entity = world.getEntityByUuid(targetUniqueId);
                if (entity instanceof DRMGolemEntity) {
                    if (hacked) {
                        entity.remove();
                        IronGolemEntity ironGolemEntity = EntityType.IRON_GOLEM.create(world);
                        if (ironGolemEntity != null) {
                            ironGolemEntity.setPlayerCreated(true);
                            ironGolemEntity.setPosition(entity.getPosX(), entity.getPosY(), entity.getPosZ());
                            ironGolemEntity.setHealth(((DRMGolemEntity) entity).getHealth());
                            world.addEntity(ironGolemEntity);
                        }
                    } else {
                        ((DRMGolemEntity) entity).setAttackTarget(playerEntity);
                    }
                }
            }
        });
        return true;
    }
}
