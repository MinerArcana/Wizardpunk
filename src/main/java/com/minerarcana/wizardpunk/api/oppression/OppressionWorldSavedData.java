package com.minerarcana.wizardpunk.api.oppression;

import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;
import com.google.common.collect.Sets;
import com.minerarcana.wizardpunk.api.WizardpunkAPI;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.storage.WorldSavedData;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public class OppressionWorldSavedData extends WorldSavedData {
    public static final String NAME = "wizardpunk_oppression";

    private final Multimap<Integer, Integer> oppressionLocations;

    public OppressionWorldSavedData() {
        super(NAME);
        this.oppressionLocations = Multimaps.newSetMultimap(Maps.newHashMap(), Sets::newHashSet);
    }

    public void oppress(BlockPos blockPos) {
        oppress(new ChunkPos(blockPos));
    }

    public void oppress(ChunkPos chunkPos) {
        oppress(chunkPos.x, chunkPos.z);
    }

    public void oppress(int chunkX, int chunkZ) {
        oppressionLocations.put(chunkX, chunkZ);
    }

    public void save(ChunkPos chunkPos) {
        oppressionLocations.remove(chunkPos.x, chunkPos.z);
    }

    public boolean isOppressed(BlockPos blockPos) {
        return this.isOppressed(new ChunkPos(blockPos));
    }

    public boolean isOppressed(ChunkPos chunkPos) {
        return isOppressed(chunkPos.x, chunkPos.z);
    }

    public boolean isOppressed(int chunkX, int chunkZ) {
        return oppressionLocations.containsEntry(chunkX, chunkZ);
    }

    @Override
    public void read(@Nonnull CompoundNBT compoundNBT) {
        for (String tagName : compoundNBT.keySet()) {
            try {
                Integer chunkX = Integer.valueOf(tagName);
                for (int chunkZ : compoundNBT.getIntArray(tagName)) {
                    oppressionLocations.get(chunkX).add(chunkZ);
                }
            } catch (Exception exception) {
                WizardpunkAPI.LOGGER.warn("Failed to create Oppression Data for Chunk", exception);
            }
        }
    }

    @Override
    @Nonnull
    public CompoundNBT write(@Nonnull CompoundNBT compoundNBT) {
        for (Map.Entry<Integer, Collection<Integer>> entry : oppressionLocations.asMap().entrySet()) {
            compoundNBT.putIntArray(String.valueOf(entry.getKey()), new ArrayList<>(entry.getValue()));
        }
        return compoundNBT;
    }
}
