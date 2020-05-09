package com.minerarcana.wizardpunk.tileentity;

import com.minerarcana.wizardpunk.Wizardpunk;
import com.minerarcana.wizardpunk.content.WizardpunkBlocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;

public class MilitaryCrateTileEntity extends TileEntity {

    public MilitaryCrateTileEntity() {
        this(WizardpunkBlocks.MILITARY_CRATE_TYPE.get());
    }

    public MilitaryCrateTileEntity(TileEntityType<? extends MilitaryCrateTileEntity> tileEntityType) {
        super(tileEntityType);
    }
}
