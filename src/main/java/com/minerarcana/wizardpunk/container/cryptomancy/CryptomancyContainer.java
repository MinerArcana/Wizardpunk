package com.minerarcana.wizardpunk.container.cryptomancy;

import com.minerarcana.wizardpunk.content.WizardpunkContainers;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.network.PacketBuffer;

import javax.annotation.Nonnull;
import java.util.UUID;

public class CryptomancyContainer extends Container {
    private final UUID targetUniqueId;

    public CryptomancyContainer(int id, UUID targetUniqueId) {
        super(WizardpunkContainers.CRYPTOMANCY.get(), id);
        this.targetUniqueId = targetUniqueId;
    }

    @Override
    public boolean canInteractWith(@Nonnull PlayerEntity player) {
        return true;
    }

    public UUID getTargetUniqueId() {
        return targetUniqueId;
    }

    public static CryptomancyContainer create(int id, PlayerInventory playerInventory, PacketBuffer packetBuffer) {
        return new CryptomancyContainer(id, packetBuffer.readUniqueId());
    }
}
