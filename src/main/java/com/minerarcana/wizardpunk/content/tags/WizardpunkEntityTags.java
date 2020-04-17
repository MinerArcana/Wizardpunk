package com.minerarcana.wizardpunk.content.tags;

import com.minerarcana.wizardpunk.Wizardpunk;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.util.ResourceLocation;

public class WizardpunkEntityTags {
    public static final EntityTypeTags.Wrapper OPPRESSORS = new EntityTypeTags.Wrapper(
            new ResourceLocation(Wizardpunk.ID, "oppressors"));

    public static final EntityTypeTags.Wrapper OPPRESSED = new EntityTypeTags.Wrapper(
            new ResourceLocation(Wizardpunk.ID, "oppressed"));
}
