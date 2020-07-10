package com.minerarcana.wizardpunk.entity.ai;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

import static com.minerarcana.wizardpunk.content.WizardpunkBlocks.OPPRESSIVE_ATMOSPHERE;

public class SmokeStackGoal extends Goal {
    private final Entity oppressor;

    public SmokeStackGoal(Entity oppressor) {
        this.oppressor = oppressor;
    }

    @Override
    public boolean shouldExecute() {
        World world = oppressor.world;
        Random random = world.rand;
        return random.nextInt(60) == 0;
    }

    @Override
    public void startExecuting() {
        World world = oppressor.world;
        BlockPos entityPos = oppressor.getPosition();
        BlockPos targetPos = entityPos.up(3);
        BlockState state = world.getBlockState(targetPos);
        while(!state.isAir() && targetPos.getY() < 240){
            targetPos = targetPos.up();
        }
        world.setBlockState(targetPos,OPPRESSIVE_ATMOSPHERE.get().getDefaultState());
    }
}
