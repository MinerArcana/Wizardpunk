package com.minerarcana.wizardpunk.item.magic;

import com.minerarcana.wizardpunk.Wizardpunk;
import com.minerarcana.wizardpunk.content.WizardpunkEntities;
import com.minerarcana.wizardpunk.entity.neutral.ZephyrusEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

public class ZephyrusItem extends Item {
    public ZephyrusItem() {
        this(new Properties()
                .maxStackSize(1)
                .group(Wizardpunk.ITEM_GROUP));
    }

    public ZephyrusItem(Properties properties) {
        super(properties);
    }

    @Override
    @Nonnull
    @ParametersAreNonnullByDefault
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getHeldItem(hand);
        if (hand == Hand.MAIN_HAND) {

            ZephyrusEntity zephyrusEntity = WizardpunkEntities.ZEPHYRUS.get().create(world);
            if (zephyrusEntity != null) {
                player.setActiveHand(hand);
                zephyrusEntity.setPosition(player.getPosX(), player.getPosY() + 2, player.getPosZ());
                zephyrusEntity.setOwner(player);
                world.addEntity(zephyrusEntity);
                return ActionResult.resultSuccess(itemStack);
            }
        }

        return ActionResult.resultPass(itemStack);
    }

    @Override
    public int getUseDuration(@Nonnull ItemStack stack) {
        return 10000;
    }

    @Override
    public boolean canContinueUsing(ItemStack oldStack, ItemStack newStack) {
        return !newStack.isEmpty() && newStack.getItem() == this;
    }
}
