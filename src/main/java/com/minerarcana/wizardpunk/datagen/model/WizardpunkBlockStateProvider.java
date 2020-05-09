package com.minerarcana.wizardpunk.datagen.model;

import com.hrznstudio.titanium.registry.BlockRegistryObjectGroup;
import com.minerarcana.wizardpunk.Wizardpunk;
import com.minerarcana.wizardpunk.content.WizardpunkBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ExistingFileHelper;

public class WizardpunkBlockStateProvider extends BlockStateProvider {
    public WizardpunkBlockStateProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, Wizardpunk.ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        WizardpunkBlocks.MILITARY_CRATES.values()
                .stream()
                .map(BlockRegistryObjectGroup::getBlock)
                .forEach(militaryCrateBlock -> {
                    String typeString = militaryCrateBlock.getType() + "_military_crate";
                    this.horizontalBlock(militaryCrateBlock, models()
                            .cube(
                                    typeString,
                                    modLoc("block/" + typeString + "_bottom"),
                                    modLoc("block/" + typeString + "_top"),
                                    modLoc("block/" + typeString + "_front"),
                                    modLoc("block/" + typeString + "_back"),
                                    modLoc("block/" + typeString + "_right"),
                                    modLoc("block/" + typeString + "_left")
                            )
                            .texture("particle", modLoc("block/" + typeString + "_top"))
                    );
                });

        this.cubeAll(WizardpunkBlocks.OPPRESSIVE_EMITTER.getBlock());
    }
}
