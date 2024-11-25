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
        checkContainerSize(container, 54);
        this.container = container;

        //int $$5 = (this.containerRows - 4) * 18;

        for(int $$5 = 0; $$5 < 3; ++$$5) {
            for(int $$6 = 0; $$6 < 9; ++$$6) {
                this.addSlot(new ShulkerBoxSlot(container, $$6 + $$5 * 9, 8 + $$6 * 18, 18 + $$5 * 18));
            }
        }

        for(int $$7 = 0; $$7 < 3; ++$$7) {
            for(int $$8 = 0; $$8 < 9; ++$$8) {
                this.addSlot(new Slot(playerInventory, $$8 + $$7 * 9 + 9, 8 + $$8 * 18, 84 + $$7 * 18));
            }
        }

        for(int $$9 = 0; $$9 < 9; ++$$9) {
            this.addSlot(new Slot(playerInventory, $$9, 8 + $$9 * 18, 142));
        }

        /*
        for(int i = 0; i < 6; ++i) {
            for(int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(container, j + i * 9, 8 + j * 18, 18 + i * 18));
            }
        }

        // Player Inventory
        for(int i = 0; i < 3; ++i) {
            for(int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 103 + i * 18 + $$5));
            }
        }

        // Player Hotbar
        for(int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 161 + $$5));
        }

         */
    }

    public IndustrialBarrelMenu(int containerId, Inventory playerInv) {
        super(ModMenus.INDUSTRIAL_BARREL_MENU.get(), containerId);
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
