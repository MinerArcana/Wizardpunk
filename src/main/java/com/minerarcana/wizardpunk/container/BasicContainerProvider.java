package com.minerarcana.wizardpunk.container;

import com.hrznstudio.titanium.client.screen.container.BasicContainerScreen;
import com.hrznstudio.titanium.container.BasicAddonContainer;
import com.hrznstudio.titanium.container.addon.IContainerAddonProvider;
import com.hrznstudio.titanium.network.locator.LocatorFactory;
import com.hrznstudio.titanium.network.locator.instance.TileEntityLocatorInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Objects;

public class BasicContainerProvider implements INamedContainerProvider {
    private final TileEntity tileEntity;
    private final TileEntityLocatorInstance tileEntityLocatorInstance;

    public BasicContainerProvider(TileEntity tileEntity) {
        this.tileEntity = tileEntity;
        tileEntityLocatorInstance = new TileEntityLocatorInstance(tileEntity.getPos());
    }

    @Override
    @Nonnull
    public ITextComponent getDisplayName() {
        return new TranslationTextComponent(tileEntity.getBlockState()
                .getBlock()
                .getTranslationKey())
                .applyTextStyle(TextFormatting.BLACK);
    }

    @Nullable
    @Override
    @ParametersAreNonnullByDefault
    public Container createMenu(int windowId, PlayerInventory playerInventory, PlayerEntity playerEntity) {
        return new BasicAddonContainer(tileEntity, new TileEntityLocatorInstance(tileEntity.getPos()),
                IWorldPosCallable.of(Objects.requireNonNull(tileEntity.getWorld()), tileEntity.getPos()),
                playerInventory, windowId);
    }

    public static void createAndOpen(TileEntity tileEntity, World world, PlayerEntity playerEntity) {
        if (tileEntity instanceof IContainerAddonProvider) {
            if (!world.isRemote() && playerEntity instanceof ServerPlayerEntity) {
                BasicContainerProvider basicContainerProvider = new BasicContainerProvider(tileEntity);
                NetworkHooks.openGui((ServerPlayerEntity) playerEntity, basicContainerProvider, packetBuffer ->
                        LocatorFactory.writePacketBuffer(packetBuffer, basicContainerProvider.tileEntityLocatorInstance));
            }
        }
    }
}
