/*
package net.realfancymoo.thething.menus;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;

public class IndustrialBarrelMenu extends AbstractContainerMenu {
    private static final int CONTAINER_SIZE = 54;
    private Container container;
    private final int containerRows = 6;

    public IndustrialBarrelMenu(int containerID, Inventory playerInventory, FriendlyByteBuf extraData)
    {
        this(containerID, playerInventory, new SimpleContainer(54));
    }

    public IndustrialBarrelMenu(int containerID, Inventory playerInventory, Container container)
    {
        super(ModMenus.INDUSTRIAL_BARREL_MENU.get(), containerID);
        checkContainerSize(container, this.containerRows * 9);
        this.container = container;
        container.startOpen(playerInventory.player);
        int $$5 = (this.containerRows - 4) * 18;

        for(int i = 0; i < this.containerRows; ++i) {
            for(int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(container, j + i * 9, 8 + j * 18, 18 + i * 18));
            }
        }

        for(int k = 0; k < 3; ++k) {
            for(int h = 0; h < 9; ++h) {
                this.addSlot(new Slot(playerInventory, h + k * 9 + 9, 8 + h * 18, 103 + k * 18 + $$5));
            }
        }

        for(int t = 0; t < 9; ++t) {
            this.addSlot(new Slot(playerInventory, t, 8 + t * 18, 161 + $$5));
        }
    }

    @Override
    public ItemStack quickMoveStack(Player player, int pIndex) {
        ItemStack $$2 = ItemStack.EMPTY;
        Slot $$3 = (Slot)this.slots.get(pIndex);
        if ($$3 != null && $$3.hasItem()) {
            ItemStack $$4 = $$3.getItem();
            $$2 = $$4.copy();
            if (pIndex < this.containerRows * 9) {
                if (!this.moveItemStackTo($$4, this.containerRows * 9, this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo($$4, 0, this.containerRows * 9, false)) {
                return ItemStack.EMPTY;
            }

            if ($$4.isEmpty()) {
                $$3.set(ItemStack.EMPTY);
            } else {
                $$3.setChanged();
            }
        }

        return $$2;
    }

    @Override
    public boolean stillValid(Player player) {
        return this.container.stillValid(player);
    }

    public void removed(Player pPlayer) {
        super.removed(pPlayer);
    }
}

 */
