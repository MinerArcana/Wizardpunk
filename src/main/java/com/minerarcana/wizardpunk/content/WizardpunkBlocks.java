package com.minerarcana.wizardpunk.content;

import com.minerarcana.wizardpunk.Wizardpunk;
import com.minerarcana.wizardpunk.block.OppressiveAtmosphere;
import com.minerarcana.wizardpunk.block.OppressiveEmitter;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static com.minerarcana.wizardpunk.Wizardpunk.ITEM_GROUP;

public class WizardpunkBlocks {

    private static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS,
            Wizardpunk.ID);
    private static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS,
            Wizardpunk.ID);

    public static final RegistryObject<Block> OPPRESSIVE_EMITTER = BLOCKS.register("oppressive_emitter", OppressiveEmitter::new);
    public static final RegistryObject<Block> OPPRESSIVE_ATMOSPHERE = BLOCKS.register("oppressive_atmosphere", OppressiveAtmosphere::new);

    public static void register(IEventBus modBus) {

        for(RegistryObject<Block> block : BLOCKS.getEntries()){
            ITEMS.register(block.getId().getPath(),()-> new BlockItem(block.get(),new Item.Properties().group(ITEM_GROUP)));
        }

        BLOCKS.register(modBus);
        ITEMS.register(modBus);
    }

}
