package com.minerarcana.wizardpunk.datagen;

import com.minerarcana.wizardpunk.datagen.language.WizardpunkUSLanguageProvider;
import com.minerarcana.wizardpunk.datagen.loottable.WizardpunkLootTableProvider;
import com.minerarcana.wizardpunk.datagen.model.WizardpunkBlockStateProvider;
import com.minerarcana.wizardpunk.datagen.model.WizardpunkItemModelProvider;
import com.minerarcana.wizardpunk.datagen.tag.WizardpunkEntityTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

public class WizardpunkGenerator {
    public static void registerGenerators(GatherDataEvent event) {
        DataGenerator dataGenerator = event.getGenerator();

        if (event.includeClient()) {
            BlockStateProvider blockStateProvider = new WizardpunkBlockStateProvider(dataGenerator, event.getExistingFileHelper());
            dataGenerator.addProvider(blockStateProvider);
            dataGenerator.addProvider(new WizardpunkItemModelProvider(dataGenerator, blockStateProvider.models().existingFileHelper));
            dataGenerator.addProvider(new WizardpunkUSLanguageProvider(dataGenerator));
        }

        if (event.includeServer()) {
            dataGenerator.addProvider(new WizardpunkEntityTagsProvider(dataGenerator));
            dataGenerator.addProvider(new WizardpunkLootTableProvider(dataGenerator));
        }
    }
}
