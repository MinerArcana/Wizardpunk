package com.minerarcana.wizardpunk.item.magic;

import com.minerarcana.wizardpunk.Wizardpunk;
import com.minerarcana.wizardpunk.content.WizardpunkEntities;
import com.minerarcana.wizardpunk.entity.friendly.MiniCatEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Arrays;

public class NekomanticAmuletItem extends Item {
    public NekomanticAmuletItem() {
        this(new Properties()
                .group(Wizardpunk.ITEM_GROUP)
                .maxStackSize(1)
                .maxDamage(1));
    }

    public NekomanticAmuletItem(Properties properties) {
        super(properties);
    }

    @Override
    @Nonnull
    @ParametersAreNonnullByDefault
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
        ItemStack heldStack = player.getHeldItem(hand);

        if (world.dimension.hasSkyLight() && heldStack.getDamage() < heldStack.getMaxDamage()) {
            heldStack.damageItem(1, player, playerEntity -> playerEntity.sendBreakAnimation(hand));
            if (isDayTime(world)) {
                player.getFoodStats().addStats(1, 10);
            } else {
                if (player.getRightShoulderEntity().isEmpty() && player.getLeftShoulderEntity().isEmpty()) {
                    MiniCatEntity miniCatEntity = WizardpunkEntities.MINI_CAT.get().create(world);
                    if (!world.isRemote() && miniCatEntity != null) {
                        if (world.getCurrentMoonPhaseFactor() > 0.9F) {
                            miniCatEntity.setCatType(world.rand.nextInt(11));
                        } else {
                            miniCatEntity.setCatType(world.rand.nextInt(10));
                        }
                        player.addShoulderEntity(miniCatEntity.serializeNBT());
                    }
                }

            }

            return ActionResult.resultSuccess(heldStack);
        }

        return ActionResult.resultPass(heldStack);
    }

    @Override
    @ParametersAreNonnullByDefault
    public boolean itemInteractionForEntity(ItemStack stack, PlayerEntity playerEntity, LivingEntity target, Hand hand) {
        if (target instanceof CatEntity) {
            CompoundNBT nekomanticInfo = stack.getOrCreateChildTag("nekomantic");
            int[] types = nekomanticInfo.getIntArray("type-interactions");
            boolean foundType = false;
            for (int type : types) {
                if (type == ((CatEntity) target).getCatType()) {
                    foundType = true;
                }
            }
            if (!foundType) {
                int[] newTypes = Arrays.copyOf(types, types.length + 1);
                newTypes[types.length] = ((CatEntity) target).getCatType();
                nekomanticInfo.putIntArray("type-interactions", newTypes);
            }
        }
        return false;
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

    @Override
    public int getMaxDamage(ItemStack stack) {
        if (stack.getTag() != null && stack.getTag().contains("nekomantic")) {
            CompoundNBT nekomanticInfo = stack.getOrCreateChildTag("nekomantic");
            return nekomanticInfo.getIntArray("type-interactions").length + 1;
        }
        return 1;
    }
}
