package com.minerarcana.wizardpunk.item.magic;

import com.minerarcana.wizardpunk.Wizardpunk;
import com.minerarcana.wizardpunk.container.CryptomancyContainer;
import com.minerarcana.wizardpunk.entity.oppressor.DRMGolemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

public class CryptomancyItem extends Item implements INamedContainerProvider {
    public CryptomancyItem() {
        this(new Properties()
                .group(Wizardpunk.ITEM_GROUP)
                .maxStackSize(1));
    }

    public CryptomancyItem(Properties properties) {
        super(properties);
    }

    @Override
    @ParametersAreNonnullByDefault
    public boolean itemInteractionForEntity(ItemStack stack, PlayerEntity playerEntity, LivingEntity target, Hand hand) {
        if (target instanceof DRMGolemEntity) {
            playerEntity.openContainer(this);
        }
        return super.itemInteractionForEntity(stack, playerEntity, target, hand);
    }

    @Override
    @Nonnull
    public ITextComponent getDisplayName() {
        return new TranslationTextComponent(this.getTranslationKey());
    }

    @Nullable
    @Override
    @ParametersAreNonnullByDefault
    public Container createMenu(int windowId, PlayerInventory playerInventory, PlayerEntity playerEntity) {
        return CryptomancyContainer.create(windowId, playerInventory);
    }
}
