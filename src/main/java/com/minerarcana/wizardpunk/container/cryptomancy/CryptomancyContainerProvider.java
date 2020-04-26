package com.minerarcana.wizardpunk.container.cryptomancy;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.UUID;

public class CryptomancyContainerProvider implements INamedContainerProvider {
    private final UUID targetUniqueId;

    public CryptomancyContainerProvider(UUID targetUniqueId) {
        this.targetUniqueId = targetUniqueId;
    }

    @Override
    @Nonnull
    public ITextComponent getDisplayName() {
        return new TranslationTextComponent("item.wizardpunk.cryptomancy")
                .applyTextStyle(TextFormatting.BLACK);
    }

    @Nullable
    @Override
    @ParametersAreNonnullByDefault
    public Container createMenu(int windowId, PlayerInventory playerInventory, PlayerEntity playerEntity) {
        return new CryptomancyContainer(windowId, targetUniqueId);
    }
}