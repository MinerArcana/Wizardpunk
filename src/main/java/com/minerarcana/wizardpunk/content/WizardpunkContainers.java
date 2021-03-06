package com.minerarcana.wizardpunk.content;

import com.minerarcana.wizardpunk.Wizardpunk;
import com.minerarcana.wizardpunk.container.cryptomancy.CryptomancyContainer;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class WizardpunkContainers {
    private static final DeferredRegister<ContainerType<?>> CONTAINERS = new DeferredRegister<>(ForgeRegistries.CONTAINERS,
            Wizardpunk.ID);

    public static final RegistryObject<ContainerType<CryptomancyContainer>> CRYPTOMANCY = CONTAINERS.register(
            "cryptomancy", () -> IForgeContainerType.create(CryptomancyContainer::create));

    public static void register(IEventBus modBus) {
        CONTAINERS.register(modBus);
    }
}
