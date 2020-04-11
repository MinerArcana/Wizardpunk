package com.minerarcana.wizardpunk.world;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.feature.jigsaw.JigsawManager;
import net.minecraft.world.gen.feature.jigsaw.JigsawPattern;
import net.minecraft.world.gen.feature.jigsaw.JigsawPattern.PlacementBehaviour;
import net.minecraft.world.gen.feature.jigsaw.JigsawPiece;
import net.minecraft.world.gen.feature.jigsaw.SingleJigsawPiece;
import net.minecraft.world.gen.feature.structure.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.minerarcana.wizardpunk.Wizardpunk.ID;

public class VillageAdditions {

    public static void init() {
        PlainsVillagePools.init();
        SnowyVillagePools.init();
        SavannaVillagePools.init();
        DesertVillagePools.init();
        TaigaVillagePools.init();
        for (String biome : new String[]{"plains", "snowy", "savanna", "desert", "taiga"}) {
            addToPool(new ResourceLocation("village/" + biome + "/houses"),
                    new ResourceLocation(ID, "guardpost_" + biome), 3);
            addToPool(new ResourceLocation("village/" + biome + "/decor"),
                    new ResourceLocation(ID, "oppressive_emitter_" + biome), 1);
        }

    }

    private static void addToPool(ResourceLocation pool, ResourceLocation toAdd, int weight) {
        JigsawPattern old = JigsawManager.REGISTRY.get(pool);
        List<JigsawPiece> shuffled = old.getShuffledPieces(new Random());
        List<Pair<JigsawPiece, Integer>> newPieces = new ArrayList<>();
        newPieces.add(new Pair<>(new SingleJigsawPiece(toAdd.toString(), ImmutableList.of(), PlacementBehaviour.RIGID), weight));
        for (JigsawPiece p : shuffled) {
            newPieces.add(new Pair<>(p, 1));
        }
        ResourceLocation something = old.func_214948_a();
        JigsawManager.REGISTRY.register(new JigsawPattern(pool, something, newPieces, PlacementBehaviour.RIGID));
    }

}
