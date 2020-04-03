package com.minerarcana.wizardpunk.content;

import com.minerarcana.wizardpunk.Wizardpunk;
import com.minerarcana.wizardpunk.entity.DRMGolemEntity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class WizardpunkEntities {
    private static final DeferredRegister<EntityType<?>> ENTITIES = new DeferredRegister<>(ForgeRegistries.ENTITIES,
            Wizardpunk.ID);

    public static final RegistryObject<EntityType<DRMGolemEntity>> DRM_GOLEM = ENTITIES.register("drm_golem",
            () -> EntityType.Builder.<DRMGolemEntity>create(DRMGolemEntity::new, EntityClassification.MISC)
                    .size(1.4F, 2.7F)
                    .build("drm_golem"));

    public static void register(IEventBus modBus) {
        ENTITIES.register(modBus);
    }
}
