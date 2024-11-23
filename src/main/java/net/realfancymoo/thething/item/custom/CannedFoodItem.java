package net.realfancymoo.thething.item.custom;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.realfancymoo.thething.item.ModItems;

public class CannedFoodItem extends Item {

    public CannedFoodItem(Properties properties) {
        super(properties);
    }

    @Override
    public int getUseDuration(ItemStack itemStack) {
        if (itemStack.getItem().isEdible()) {
            return 100;
        } else {
            return 0;
        }
    }

    @Override
    public ItemStack finishUsingItem(ItemStack itemStack, Level level, LivingEntity livingEntity) {
        // Check if has can opener in offhand
        ItemStack offhandstack = livingEntity.getOffhandItem();
        if(offhandstack.is(ModItems.CAN_OPENER.get()))
        {
            if(!level.isClientSide()) {
                // Damage the can opener
                offhandstack.hurtAndBreak(1, livingEntity, (player) -> {
                    player.broadcastBreakEvent(InteractionHand.OFF_HAND);
                });
            }
        }
        return super.finishUsingItem(itemStack, level, livingEntity);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        ItemStack itemstackOffhand = player.getOffhandItem();

        if (itemstack.isEdible()) {
            // Check if holding can opener in offhand
            if (player.canEat(itemstack.getFoodProperties(player).canAlwaysEat()) && itemstackOffhand.is(ModItems.CAN_OPENER.get())) {
                // Eat the food
                player.startUsingItem(hand);
                return InteractionResultHolder.consume(itemstack);
            } else {
                return InteractionResultHolder.fail(itemstack);
            }
        } else {
            return InteractionResultHolder.pass(player.getItemInHand(hand));
        }
    }
}
