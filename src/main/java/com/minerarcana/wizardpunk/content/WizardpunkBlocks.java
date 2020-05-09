package com.minerarcana.wizardpunk.content;

import com.hrznstudio.titanium.registry.BlockRegistryObjectGroup;
import com.minerarcana.wizardpunk.Wizardpunk;
import com.minerarcana.wizardpunk.block.MilitaryCrateBlock;
import com.minerarcana.wizardpunk.block.OppressiveAtmosphere;
import com.minerarcana.wizardpunk.block.OppressiveEmitter;
import com.minerarcana.wizardpunk.tileentity.MilitaryCrateTileEntity;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WizardpunkBlocks {

    private static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS,
            Wizardpunk.ID);
    private static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS,
            Wizardpunk.ID);
    private static final DeferredRegister<TileEntityType<?>> TILE_ENTITY_TYPE = new DeferredRegister<>(
            ForgeRegistries.TILE_ENTITIES, Wizardpunk.ID);

    public static final BlockRegistryObjectGroup<OppressiveEmitter, BlockItem, ?> OPPRESSIVE_EMITTER =
            new BlockRegistryObjectGroup<>("oppressive_emitter", OppressiveEmitter::new, blockItemCreator())
                    .register(BLOCKS, ITEMS);
    public static final RegistryObject<OppressiveAtmosphere> OPPRESSIVE_ATMOSPHERE = BLOCKS.register(
            "oppressive_atmosphere", OppressiveAtmosphere::new);

    public static final Map<String, BlockRegistryObjectGroup<MilitaryCrateBlock, BlockItem, ?>> MILITARY_CRATES =
            Stream.of("desert")
                    .map(type -> Pair.of(type, WizardpunkBlocks.createMilitaryCrate(type)
                            .register(BLOCKS, ITEMS)))
                    .collect(Collectors.toMap(Pair::getKey, Pair::getValue));

    @SuppressWarnings("ConstantConditions")
    public static final RegistryObject<TileEntityType<MilitaryCrateTileEntity>> MILITARY_CRATE_TYPE = TILE_ENTITY_TYPE.register(
            "military_crate", () -> TileEntityType.Builder.create(MilitaryCrateTileEntity::new,
                    MILITARY_CRATES.values()
                            .stream()
                            .map(BlockRegistryObjectGroup::getBlock)
                            .toArray(Block[]::new))
                    .build(null));

    public static void register(IEventBus modBus) {
        BLOCKS.register(modBus);
        ITEMS.register(modBus);
    }

    private static <B extends Block> Function<B, BlockItem> blockItemCreator() {
        return block -> new BlockItem(block, new Item.Properties().group(Wizardpunk.ITEM_GROUP));
    }

    private static BlockRegistryObjectGroup<MilitaryCrateBlock, BlockItem, MilitaryCrateTileEntity> createMilitaryCrate(String type) {
        return new BlockRegistryObjectGroup<>(type + "_military_crate", () -> new MilitaryCrateBlock(type),
                blockItemCreator());
    }
}
