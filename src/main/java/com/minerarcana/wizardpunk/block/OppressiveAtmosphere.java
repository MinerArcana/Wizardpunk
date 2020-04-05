package com.minerarcana.wizardpunk.block;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class OppressiveAtmosphere extends Block {

    public OppressiveAtmosphere() {
        super(Properties.create(Material.MISCELLANEOUS).notSolid().noDrops().variableOpacity().sound(SoundType.CLOTH));
    }

}
