package com.minerarcana.wizardpunk.datagen;

import com.minerarcana.wizardpunk.datagen.language.WizardpunkUSLanguageProvider;
import com.minerarcana.wizardpunk.datagen.model.WizardpunkItemModelProvider;
import com.minerarcana.wizardpunk.datagen.tag.WizardpunkEntityTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

public class WizardpunkGenerator {
    public static void registerGenerators(GatherDataEvent event) {
        DataGenerator dataGenerator = event.getGenerator();

        if (event.includeClient()) {
            dataGenerator.addProvider(new WizardpunkItemModelProvider(dataGenerator, event.getExistingFileHelper()));
            dataGenerator.addProvider(new WizardpunkUSLanguageProvider(dataGenerator));
        }

        if (event.includeServer()) {
            dataGenerator.addProvider(new WizardpunkEntityTagsProvider(dataGenerator));
        }
    }
}
