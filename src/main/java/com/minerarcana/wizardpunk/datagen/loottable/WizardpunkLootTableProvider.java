package com.minerarcana.wizardpunk.datagen.loottable;

import com.google.common.collect.Lists;
import com.mojang.datafixers.util.Pair;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.LootTableProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootParameterSet;
import net.minecraft.world.storage.loot.LootParameterSets;
import net.minecraft.world.storage.loot.LootTable;
import net.minecraft.world.storage.loot.ValidationTracker;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class WizardpunkLootTableProvider extends LootTableProvider {
    public WizardpunkLootTableProvider(DataGenerator dataGenerator) {
        super(dataGenerator);
    }

    @Override
    @Nonnull
    public String getName() {
        return "Wizardpunk Loot Tables";
    }

    @Override
    @Nonnull
    protected List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootParameterSet>> getTables() {
        return Lists.newArrayList(
                Pair.of(WizardpunkBlockLootTables::new, LootParameterSets.BLOCK)
        );
    }

    @Override
    @ParametersAreNonnullByDefault
    protected void validate(Map<ResourceLocation, LootTable> map, ValidationTracker validationtracker) {

    }
}
