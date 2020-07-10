package com.minerarcana.wizardpunk.entity.oppressor;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.AbstractIllagerEntity;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class GovernorIllagerEntity extends AbstractIllagerEntity {
    public GovernorIllagerEntity(EntityType<? extends GovernorIllagerEntity> type, World world) {
        super(type, world);
    }


    @Override
    public void applyWaveBonus(int p_213660_1_, boolean p_213660_2_) {

    }

    @Override
    @Nonnull
    public SoundEvent getRaidLossSound() {
        return SoundEvents.ENTITY_VINDICATOR_CELEBRATE;
    }
}
