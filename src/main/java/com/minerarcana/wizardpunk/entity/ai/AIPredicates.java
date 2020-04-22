package com.minerarcana.wizardpunk.entity.ai;

import com.minerarcana.wizardpunk.content.tags.WizardpunkEntityTags;
import net.minecraft.entity.LivingEntity;

import java.util.function.Predicate;

public class AIPredicates {
    public static final Predicate<LivingEntity> NOT_OPPRESSED = (entity) ->
            !WizardpunkEntityTags.OPPRESSED.contains(entity.getType());

    public static final Predicate<LivingEntity> NOT_OPPRESSOR = (entity) ->
            !WizardpunkEntityTags.OPPRESSORS.contains(entity.getType());

    public static final Predicate<LivingEntity> NOT_OPPRESSION = (entity) -> NOT_OPPRESSED.test(entity) &&
            NOT_OPPRESSOR.test(entity);
}
