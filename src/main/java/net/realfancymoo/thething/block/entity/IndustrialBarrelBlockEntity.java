package net.realfancymoo.thething.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class IndustrialBarrelBlockEntity extends RandomizableContainerBlockEntity {
    private NonNullList<ItemStack> itemStacks;

    public IndustrialBarrelBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(ModBlockEntities.INDUSTRIAL_BARREL_BLOCK_ENTITY.get(), blockPos, blockState);
        this.itemStacks = NonNullList.withSize(54, ItemStack.EMPTY);
    }

    @Override
    protected NonNullList<ItemStack> getItems() {
        return this.itemStacks;
    }

    @Override
    protected void setItems(NonNullList<ItemStack> pItems) {
        this.itemStacks = pItems;
    }

    @Override
    protected Component getDefaultName() {
        return Component.literal("Industrial Barrel");
    }

    @Override
    protected AbstractContainerMenu createMenu(int pId, Inventory inventory) {
        return ChestMenu.sixRows(pId, inventory, this);
    }

    @Override
    public int getContainerSize() {
        return this.itemStacks.size();
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        this.itemStacks = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        if (!this.tryLoadLootTable(pTag)) {
            ContainerHelper.loadAllItems(pTag, this.itemStacks);
        }
    }

    @Override
    public void startOpen(Player pPlayer) {
        super.startOpen(pPlayer);
        if(!pPlayer.isSpectator()){
            this.level.playSound((Player)null, this.worldPosition, SoundEvents.BARREL_OPEN, SoundSource.BLOCKS, 0.5F, this.level.random.nextFloat() * 0.1F + 0.9F);
        }
    }

    protected void saveAdditional(CompoundTag pTag) {
        super.saveAdditional(pTag);
        if (!this.trySaveLootTable(pTag)) {
            ContainerHelper.saveAllItems(pTag, this.itemStacks);
        }
    }
}
