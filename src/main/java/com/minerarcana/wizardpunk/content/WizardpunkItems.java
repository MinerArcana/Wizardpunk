package com.minerarcana.wizardpunk.content;

import com.minerarcana.wizardpunk.Wizardpunk;
import com.minerarcana.wizardpunk.item.PamphletItem;
import net.minecraft.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class WizardpunkItems {
    private static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, Wizardpunk.ID);

    public static final RegistryObject<PamphletItem> PAMPHLET = ITEMS.register("pamphlet", PamphletItem::new);

    public static void register(IEventBus modBus) {
        ITEMS.register(modBus);
    }
}
