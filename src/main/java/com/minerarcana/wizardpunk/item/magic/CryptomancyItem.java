package com.minerarcana.wizardpunk.item.magic;

import com.minerarcana.wizardpunk.Wizardpunk;
import com.minerarcana.wizardpunk.container.cryptomancy.CryptomancyContainerProvider;
import com.minerarcana.wizardpunk.entity.oppressor.DRMGolemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.ParametersAreNonnullByDefault;

public class CryptomancyItem extends Item {
    public CryptomancyItem() {
        this(new Properties()
                .group(Wizardpunk.ITEM_GROUP)
                .maxStackSize(1));
    }

    public CryptomancyItem(Properties properties) {
        super(properties);
    }

    @Override
    @ParametersAreNonnullByDefault
    public boolean itemInteractionForEntity(ItemStack stack, PlayerEntity playerEntity, LivingEntity target, Hand hand) {
        if (target instanceof DRMGolemEntity) {
            if (playerEntity instanceof ServerPlayerEntity) {
                NetworkHooks.openGui((ServerPlayerEntity) playerEntity, new CryptomancyContainerProvider(target.getUniqueID()),
                        packetBuffer -> packetBuffer.writeUniqueId(target.getUniqueID()));
            }
            return true;
        }
        return super.itemInteractionForEntity(stack, playerEntity, target, hand);
    }
}
