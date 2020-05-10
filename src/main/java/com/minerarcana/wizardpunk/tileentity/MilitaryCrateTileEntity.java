package com.minerarcana.wizardpunk.tileentity;

import com.hrznstudio.titanium.api.IFactory;
import com.hrznstudio.titanium.api.client.IScreenAddon;
import com.hrznstudio.titanium.api.client.IScreenAddonProvider;
import com.hrznstudio.titanium.component.inventory.InventoryComponent;
import com.hrznstudio.titanium.container.addon.IContainerAddon;
import com.hrznstudio.titanium.container.addon.IContainerAddonProvider;
import com.minerarcana.wizardpunk.content.WizardpunkBlocks;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class MilitaryCrateTileEntity extends TileEntity implements IContainerAddonProvider, IScreenAddonProvider {
    private final InventoryComponent<?> inventory;
    private final LazyOptional<IItemHandler> lazyInventory;

    public MilitaryCrateTileEntity() {
        this(WizardpunkBlocks.MILITARY_CRATE_TYPE.get());
    }

    public MilitaryCrateTileEntity(TileEntityType<? extends MilitaryCrateTileEntity> tileEntityType) {
        super(tileEntityType);
        this.inventory = new InventoryComponent<>("inventory", 44, 26, 15)
                .setRange(5, 3);
        this.lazyInventory = LazyOptional.of(() -> this.inventory);
    }

    @Override
    @Nonnull
    public CompoundNBT write(@Nonnull CompoundNBT compound) {
        CompoundNBT compoundNBT = super.write(compound);
        compoundNBT.put("inventory", inventory.serializeNBT());
        return compoundNBT;
    }

    @Override
    public void read(@Nonnull CompoundNBT compound) {
        super.read(compound);
        inventory.deserializeNBT(compound.getCompound("inventory"));
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return lazyInventory.cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    protected void invalidateCaps() {
        super.invalidateCaps();
        this.lazyInventory.invalidate();
    }

    @Override
    public List<IFactory<? extends IScreenAddon>> getScreenAddons() {
        return inventory.getScreenAddons();
    }

    @Override
    public List<IFactory<? extends IContainerAddon>> getContainerAddons() {
        return inventory.getContainerAddons();
    }
}
