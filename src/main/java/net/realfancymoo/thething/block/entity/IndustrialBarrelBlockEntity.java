package net.realfancymoo.thething.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.realfancymoo.thething.menus.IndustrialBarrelMenu;

public class IndustrialBarrelBlockEntity extends BaseContainerBlockEntity {
    private NonNullList<ItemStack> itemStacks;

    public IndustrialBarrelBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(ModBlockEntities.INDUSTRIAL_BARREL_BLOCK_ENTITY.get(), blockPos, blockState);
        this.itemStacks = NonNullList.withSize(54, ItemStack.EMPTY);
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        //this.inventory.deserializeNBT(nbt.getCompound("inventory"));
    }

    @Override
    protected void saveAdditional(CompoundTag nbt) {
        super.saveAdditional(nbt);
        //nbt.put("inventory", this.inventory.serializeNBT());
    }

    @Override
    protected Component getDefaultName() {
        return Component.translatable("container.industrialbarrel");
    }

    protected AbstractContainerMenu createMenu(int pId, Inventory pPlayer) {
        return new IndustrialBarrelMenu(pId, pPlayer, this);
    }

    @Override
    public int getContainerSize() {
        return this.itemStacks.size();
    }

    @Override
    public boolean isEmpty() {
        return this.getItems().stream().allMatch(ItemStack::isEmpty);
    }

    @Override
    public void setItem(int pIndex, ItemStack pStack) {
        this.getItems().set(pIndex, pStack);
        if (pStack.getCount() > this.getMaxStackSize()) {
            pStack.setCount(this.getMaxStackSize());
        }

        this.setChanged();
    }

    @Override
    public ItemStack getItem(int pIndex) {
        return (ItemStack)this.getItems().get(pIndex);
    }

    @Override
    public ItemStack removeItem(int pIndex, int pCount) {
        ItemStack itemStack = ContainerHelper.removeItem(this.getItems(), pIndex, pCount);
        if (!itemStack.isEmpty()) {
            this.setChanged();
        }

        return itemStack;
    }

    @Override
    public ItemStack removeItemNoUpdate(int pIndex) {
        return ContainerHelper.takeItem(this.getItems(), pIndex);
    }

    public NonNullList<ItemStack> getItems() {return this.itemStacks;}

    public void setItemStacks(NonNullList<ItemStack> itemStacks) {
        this.itemStacks = itemStacks;
    }

    @Override
    public boolean stillValid(Player player) {
        if (this.level.getBlockEntity(this.worldPosition) != this) {
            return false;
        } else {
            return !(player.distanceToSqr((double)this.worldPosition.getX() + (double)0.5F, (double)this.worldPosition.getY() + (double)0.5F, (double)this.worldPosition.getZ() + (double)0.5F) > (double)64.0F);
        }
    }

    @Override
    public void clearContent() {
        this.getItems().clear();
    }
}
