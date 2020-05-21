package com.minerarcana.wizardpunk.entity.friendly;

import com.minerarcana.wizardpunk.content.WizardpunkEntities;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.item.ExperienceOrbEntity;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.villager.IVillagerType;
import net.minecraft.item.MerchantOffer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class OppressedVillagerEntity extends VillagerEntity {

    public OppressedVillagerEntity(EntityType<? extends OppressedVillagerEntity> type, World world, IVillagerType villagerType) {
        super(type, world, villagerType);
    }

    public OppressedVillagerEntity(EntityType<? extends OppressedVillagerEntity> type, World world) {
        super(type, world);
    }

    @Override
    public void spawnGolems(long gameTime, int requiredPeers) {
        //NO GOLEMS!
    }

    @Override
    protected void recalculateSpecialPricesFor(@Nonnull PlayerEntity player) {
        float i = this.getPlayerReputation(player);
        if (i > 0) {
            i /= 2;
        }
        if (i != 0) {
            for (MerchantOffer merchantoffer : this.getOffers()) {
                merchantoffer.increaseSpecialPrice(-MathHelper.floor(i * merchantoffer.getPriceMultiplier()));
            }
        }
    }

    @Override
    public VillagerEntity createChild(@Nonnull AgeableEntity otherParent) {
        double d0 = this.rand.nextDouble();
        IVillagerType childType;
        if (d0 < 0.5D) {
            childType = IVillagerType.byBiome(this.world.getBiome(new BlockPos(this)));
        } else if (d0 < 0.75D) {
            childType = this.getVillagerData().getType();
        } else if (otherParent instanceof VillagerEntity) {
            childType = ((VillagerEntity) otherParent).getVillagerData().getType();
        } else {
            childType = IVillagerType.PLAINS;
        }

        VillagerEntity child = new OppressedVillagerEntity(WizardpunkEntities.OPPRESSED_VILLAGER.get(), this.world, childType);
        child.onInitialSpawn(this.world, this.world.getDifficultyForLocation(new BlockPos(child)),
                SpawnReason.BREEDING, null, null);
        return child;
    }


}
