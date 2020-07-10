package com.minerarcana.wizardpunk.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import static com.minerarcana.wizardpunk.content.WizardpunkBlocks.OPPRESSIVE_ATMOSPHERE;

public class OppressiveEmitter extends Block {

    public OppressiveEmitter() {
        super(Block.Properties.create(Material.REDSTONE_LIGHT)
                .lightValue(10)
                .hardnessAndResistance(3)
                .tickRandomly()
                .sound(SoundType.GLASS));
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (random.nextInt(10) == 0) {
            for (int x = 0; x < 7; x++) {
                for (int y = 0; y < 7; y++) {
                    for (int z = 0; z < 7; z++) {
                        BlockPos newPos = pos.add(x,y,z);
                        BlockState newstate = world.getBlockState(newPos);
                        if(newstate.getBlock().equals(OPPRESSIVE_ATMOSPHERE.get()) && getRandomDirectionToSpread(world, newPos) != null){
                            world.setBlockState(Objects.requireNonNull(getRandomDirectionToSpread(world, newPos)),OPPRESSIVE_ATMOSPHERE.get().getDefaultState());
                            return;
                        }
                    }
                }
            }
        }
    }


    private BlockPos getRandomDirectionToSpread(World world, BlockPos pos){
        List<BlockPos> availablePositions = new ArrayList<>();
        if(world.getBlockState(pos.up()).isAir()){
            availablePositions.add(pos.up());
        }
        if(world.getBlockState(pos.down()).isAir()){
            availablePositions.add(pos.down());
        }
        if(world.getBlockState(pos.east()).isAir()){
            availablePositions.add(pos.east());
        }
        if(world.getBlockState(pos.west()).isAir()){
            availablePositions.add(pos.west());
        }
        if(world.getBlockState(pos.north()).isAir()){
            availablePositions.add(pos.north());
        }
        if(world.getBlockState(pos.south()).isAir()){
            availablePositions.add(pos.south());
        }
        if(!availablePositions.isEmpty()){
            int p = availablePositions.size();
            int rand = world.rand.nextInt(p);
            return availablePositions.get(rand);
        }
        return null;
    }
}
