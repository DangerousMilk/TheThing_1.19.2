package net.realfancymoo.thething.menus;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;

public class IndustrialBarrelMenu extends AbstractContainerMenu {
    private final ContainerLevelAccess levelAccess;

    protected IndustrialBarrelMenu(int id, Inventory playerInventory, IItemHandler slots, BlockPos pos)
    {
        super(ModMenus.INDUSTRIAL_BARREL_MENU.get(), id);
        this.levelAccess = ContainerLevelAccess.create(playerInventory.player.getLevel(), pos);
    }

    @Override
    public ItemStack quickMoveStack(Player p_38941_, int p_38942_) {
        return null;
    }

    @Override
    public boolean stillValid(Player p_38874_) {
        return false;
    }
}
