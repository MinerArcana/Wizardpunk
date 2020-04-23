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
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Arrays;
import java.util.Map;

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
            //heldStack.damageItem(1, player, playerEntity -> playerEntity.sendBreakAnimation(hand));
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
            int[] types = nekomanticInfo.getIntArray("typeInteractions");
            boolean foundType = false;
            for (int type : types) {
                if (type == ((CatEntity) target).getCatType()) {
                    foundType = true;
                }
            }
            if (!foundType) {
                int[] newTypes = Arrays.copyOf(types, types.length + 1);
                newTypes[types.length] = ((CatEntity) target).getCatType();
                nekomanticInfo.putIntArray("typeInteractions", newTypes);
            }
        }
        return false;
    }

    @Override
    @Nonnull
    public ActionResultType onItemUse(@Nonnull ItemUseContext context) {
        PlayerEntity playerEntity = context.getPlayer();
        if (playerEntity != null) {
            Map<Effect, EffectInstance> activePotions = playerEntity.getActivePotionMap();
            int potionDuration = this.getMaxDamage(context.getItem()) * 600;
            if (isDayTime(context.getWorld())) {
                if (!activePotions.containsKey(Effects.SPEED) || !activePotions.containsKey(Effects.JUMP_BOOST)) {
                    playerEntity.addPotionEffect(new EffectInstance(Effects.SPEED, potionDuration, 1));
                    playerEntity.addPotionEffect(new EffectInstance(Effects.JUMP_BOOST, potionDuration, 3));
                    //context.getItem().damageItem(1, playerEntity, player -> player.sendBreakAnimation(context.getHand()));
                    return ActionResultType.SUCCESS;
                }
            } else {
                if (!activePotions.containsKey(Effects.INVISIBILITY) || !activePotions.containsKey(Effects.NIGHT_VISION)) {
                    playerEntity.addPotionEffect(new EffectInstance(Effects.INVISIBILITY, potionDuration));
                    playerEntity.addPotionEffect(new EffectInstance(Effects.NIGHT_VISION, potionDuration));
                    //context.getItem().damageItem(1, playerEntity, player -> player.sendBreakAnimation(context.getHand()));

                    return ActionResultType.SUCCESS;
                }
            }
        }

        return ActionResultType.PASS;
    }

    private boolean isDayTime(World world) {
        long dayTime = world.getDayTime() % 24000;
        return 1000 < dayTime && dayTime < 13000;
    }

    @Override
    public int getMaxDamage(ItemStack stack) {
        return this.getTypeInteractions(stack).length + 1;
    }

    private int[] getTypeInteractions(ItemStack itemStack) {
        if (itemStack.getTag() != null && itemStack.getTag().contains("nekomantic")) {
            CompoundNBT nekomanticInfo = itemStack.getOrCreateChildTag("nekomantic");
            return nekomanticInfo.getIntArray("typeInteractions");
        }

        return new int[0];
    }
}
