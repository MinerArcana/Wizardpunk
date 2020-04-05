package com.minerarcana.wizardpunk;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

import static com.minerarcana.wizardpunk.content.WizardpunkBlocks.OPPRESSIVE_EMITTER;

public class WizardpunkGroup extends ItemGroup {

    public WizardpunkGroup(int index, String label) {
        super(index, label);
    }

    @Override
    @Nonnull
    public ItemStack createIcon() {
        return new ItemStack(OPPRESSIVE_EMITTER.get());
    }

}
