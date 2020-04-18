package com.minerarcana.wizardpunk.datagen.language;

import com.minerarcana.wizardpunk.Wizardpunk;
import com.minerarcana.wizardpunk.content.WizardpunkBlocks;
import com.minerarcana.wizardpunk.content.WizardpunkEntities;
import com.minerarcana.wizardpunk.content.WizardpunkItems;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;

import javax.annotation.Nonnull;

public class WizardpunkUSLanguageProvider extends LanguageProvider {
    public WizardpunkUSLanguageProvider(DataGenerator gen) {
        super(gen, Wizardpunk.ID, "en_us");
    }

    @Override
    protected void addTranslations() {
        this.add(WizardpunkEntities.DRM_GOLEM.get(), "DRM Golem");
        this.add(WizardpunkEntities.WAR_PIG.get(), "War Pig");

        this.add(WizardpunkItems.PAMPHLET.get(), "Pamphlet");
        this.add(WizardpunkItems.NEKOMANTIC_AMULET.get(), "Nekomantic Amulet");

        this.add(WizardpunkBlocks.OPPRESSIVE_EMITTER.get(), "Oppressive Emitter");
        this.add(WizardpunkBlocks.OPPRESSIVE_ATMOSPHERE.get(), "Oppressive Atmosphere");

        this.add("itemGroup.wizardpunk", "Wizardpunk");
    }

    @Override
    @Nonnull
    public String getName() {
        return "Wizardpunk EN_US Language";
    }
}
