package com.minerarcana.wizardpunk.entity.ai;

import com.minerarcana.wizardpunk.content.tags.WizardpunkEntityTags;
import net.minecraft.entity.LivingEntity;

import java.util.function.Predicate;

public class AIPredicates {
    public static final Predicate<LivingEntity> NOT_OPPRESSED = (entity) ->
                !WizardpunkEntityTags.OPPRESSORS.contains(entity.getType()) &&
                    !WizardpunkEntityTags.OPPRESSED.contains(entity.getType());
}
