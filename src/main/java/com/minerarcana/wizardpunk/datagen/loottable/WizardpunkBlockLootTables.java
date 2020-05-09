package com.minerarcana.wizardpunk.datagen.loottable;

import com.hrznstudio.titanium.registry.BlockRegistryObjectGroup;
import com.minerarcana.wizardpunk.Wizardpunk;
import com.minerarcana.wizardpunk.block.MilitaryCrateBlock;
import com.minerarcana.wizardpunk.content.WizardpunkBlocks;
import net.minecraft.block.Block;
import net.minecraft.data.loot.BlockLootTables;
import net.minecraft.world.storage.loot.ItemLootEntry;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.LootTable;
import net.minecraft.world.storage.loot.conditions.SurvivesExplosion;
import net.minecraft.world.storage.loot.functions.CopyNbt;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nonnull;
import java.util.Optional;
import java.util.stream.Collectors;

public class WizardpunkBlockLootTables extends BlockLootTables {

    @Override
    @Nonnull
    protected Iterable<Block> getKnownBlocks() {
        return ForgeRegistries.BLOCKS
                .getValues()
                .stream()
                .filter(block -> Optional.ofNullable(block.getRegistryName())
                        .filter(registryName -> registryName.getNamespace().equals(Wizardpunk.ID))
                        .isPresent()
                ).collect(Collectors.toList());
    }

    @Override
    protected void addTables() {
        WizardpunkBlocks.MILITARY_CRATES.values()
                .stream()
                .map(BlockRegistryObjectGroup::getBlock)
                .forEach(this::registerMilitaryCrate);

        this.registerSilkTouch(WizardpunkBlocks.OPPRESSIVE_EMITTER.getBlock());
    }

    private void registerMilitaryCrate(MilitaryCrateBlock militaryCrateBlock) {
        this.registerLootTable(militaryCrateBlock, LootTable.builder()
                .addLootPool(LootPool.builder()
                        .acceptCondition(SurvivesExplosion.builder())
                        .addEntry(ItemLootEntry.builder(militaryCrateBlock)
                                .acceptFunction(CopyNbt.builder(CopyNbt.Source.BLOCK_ENTITY)
                                        .replaceOperation("inventory", "BlockEntityTag.inventory")
                                )
                        )
                )
        );
    }
}
