package com.minerarcana.wizardpunk.item;

import com.minerarcana.wizardpunk.Wizardpunk;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import org.lwjgl.system.CallbackI;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

public class NekomanticAmuletItem extends Item {
    public NekomanticAmuletItem() {
        this(new Properties()
                .group(Wizardpunk.ITEM_GROUP)
                .maxStackSize(1));
    }

    public NekomanticAmuletItem(Properties properties) {
        super(properties);
    }

    @Override
    @Nonnull
    @ParametersAreNonnullByDefault
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
        ItemStack heldStack = player.getHeldItem(hand);

        if (world.dimension.hasSkyLight()) {
            if (isDayTime(world)) {
                player.getFoodStats().addStats(1, 10);
            }

            return ActionResult.resultSuccess(heldStack);
        }
        return ActionResult.resultPass(heldStack);
    }

    @Override
    @Nonnull
    public ActionResultType onItemUse(@Nonnull ItemUseContext context) {
        PlayerEntity playerEntity = context.getPlayer();
        if (playerEntity != null) {
            if (isDayTime(context.getWorld())) {
                playerEntity.addPotionEffect(new EffectInstance(Effects.SPEED, 12000, 1));
                playerEntity.addPotionEffect(new EffectInstance(Effects.JUMP_BOOST, 12000, 3));
            } else {
                playerEntity.addPotionEffect(new EffectInstance(Effects.INVISIBILITY, 12000));
                playerEntity.addPotionEffect(new EffectInstance(Effects.NIGHT_VISION, 12000));
            }
            return ActionResultType.SUCCESS;
        }

        return ActionResultType.PASS;
    }

    private boolean isDayTime(World world) {
        long dayTime = world.getDayTime() % 24000;
        return 1000 < dayTime && dayTime < 13000;
    }
}
