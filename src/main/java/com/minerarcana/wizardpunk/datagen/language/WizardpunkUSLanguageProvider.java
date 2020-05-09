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
        this.add(WizardpunkEntities.MINI_CAT.get(), "Mini Cat");
        this.add(WizardpunkEntities.ENFORCER.get(), "Enforcer");

        this.add(WizardpunkItems.PAMPHLET.get(), "Pamphlet");
        this.add(WizardpunkItems.NEKOMANTIC_AMULET.get(), "Nekomantic Amulet");
        this.add(WizardpunkItems.CRYPTOMANCY.get(), "Scroll of Cryptomancy");

        this.add(WizardpunkBlocks.OPPRESSIVE_EMITTER.getBlock(), "Oppressive Emitter");
        this.add(WizardpunkBlocks.OPPRESSIVE_ATMOSPHERE.get(), "Oppressive Atmosphere");
        WizardpunkBlocks.MILITARY_CRATES.values().forEach(militaryCrate -> this.add(militaryCrate.getBlock(), "Military Crate"));

        this.add("itemGroup.wizardpunk", "Wizardpunk");

        this.add("text.wizardpunk.entering_oppressed_chunk", "Entering Oppressed Chunk");
        this.add("text.wizardpunk.cryptomancy", "%s: %s");
        this.add("text.wizardpunk.hack_complete", "Hack Complete");
        this.add("text.wizardpunk.hack_failed", "Hack Failed");
    }

    @Override
    @Nonnull
    public String getName() {
        return "Wizardpunk EN_US Language";
    }
}
