package com.minerarcana.wizardpunk.entity.oppressor;

import com.minerarcana.wizardpunk.entity.ai.AIPredicates;
import com.minerarcana.wizardpunk.entity.ai.OppressChunkGoal;
import com.minerarcana.wizardpunk.entity.ai.OppressEntityGoal;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.merchant.villager.AbstractVillagerEntity;
import net.minecraft.entity.monster.PillagerEntity;
import net.minecraft.entity.monster.VindicatorEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public class EnforcerEntity extends VindicatorEntity {
    public EnforcerEntity(EntityType<? extends EnforcerEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new OppressChunkGoal(this));
        this.goalSelector.addGoal(2, new OppressEntityGoal<>(this));
        this.goalSelector.addGoal(8, new RandomWalkingGoal(this, 0.6D));
        this.goalSelector.addGoal(9, new LookAtGoal(this, PlayerEntity.class, 15.0F, 1.0F));
        this.goalSelector.addGoal(10, new LookAtGoal(this, MobEntity.class, 15.0F));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, AbstractVillagerEntity.class,
                10, false, false, AIPredicates.NOT_OPPRESSION));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolemEntity.class,
                10, true, false, AIPredicates.NOT_OPPRESSION));
    }
}
