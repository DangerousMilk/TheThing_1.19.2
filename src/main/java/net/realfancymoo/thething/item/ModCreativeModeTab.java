package net.realfancymoo.thething.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeModeTab {
    public static final CreativeModeTab THETHING_TAB = new CreativeModeTab("thethingtab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.PIZZA.get());
        }
    };
}
