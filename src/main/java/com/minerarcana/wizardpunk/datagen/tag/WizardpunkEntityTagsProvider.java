package com.minerarcana.wizardpunk.datagen.tag;

import com.minerarcana.wizardpunk.content.WizardpunkEntities;
import com.minerarcana.wizardpunk.content.tags.WizardpunkEntityTags;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.EntityTypeTagsProvider;

public class WizardpunkEntityTagsProvider extends EntityTypeTagsProvider {
    public WizardpunkEntityTagsProvider(DataGenerator dataGenerator) {
        super(dataGenerator);
    }

    @Override
    protected void registerTags() {
        this.getBuilder(WizardpunkEntityTags.OPPRESSORS).add(WizardpunkEntities.DRM_GOLEM.get(),
                WizardpunkEntities.WAR_PIG.get());
    }
}
