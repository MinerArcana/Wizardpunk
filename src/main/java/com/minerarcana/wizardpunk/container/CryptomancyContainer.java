package com.minerarcana.wizardpunk.container;

import com.minerarcana.wizardpunk.Wizardpunk;
import com.minerarcana.wizardpunk.content.WizardpunkContainers;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class CryptomancyContainer extends Container {
    public CryptomancyContainer(@Nullable ContainerType<?> type, int id) {
        super(type, id);
    }

    @Override
    public boolean canInteractWith(@Nonnull PlayerEntity player) {
        return true;
    }

    public static CryptomancyContainer create(int id, PlayerInventory playerInventory) {
        return new CryptomancyContainer(WizardpunkContainers.CRYPTOMANCY.get(), id);
    }
}
