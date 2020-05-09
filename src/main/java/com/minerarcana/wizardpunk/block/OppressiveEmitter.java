package com.minerarcana.wizardpunk.block;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class OppressiveEmitter extends Block {

    public OppressiveEmitter() {
        super(Block.Properties.create(Material.REDSTONE_LIGHT)
                .lightValue(10)
                .hardnessAndResistance(3)
                .sound(SoundType.GLASS));
    }

}
