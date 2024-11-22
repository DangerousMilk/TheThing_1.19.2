package net.realfancymoo.thething.item;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.realfancymoo.thething.TheThing;
import net.realfancymoo.thething.item.custom.CannedFoodItem;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, TheThing.MOD_ID);

    public static final RegistryObject<Item> PIZZA = ITEMS.register("pizza",
            () -> new Item(new Item.Properties().food(ModFoods.PIZZA).tab(ModCreativeModeTab.THETHING_TAB)));
    public static final RegistryObject<Item> CAN_OPENER = ITEMS.register("canopener",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.THETHING_TAB).defaultDurability(50)));
    public static final RegistryObject<Item> CANNED_MEAT = ITEMS.register("cannedmeat",
            () -> new CannedFoodItem(new Item.Properties().food(ModFoods.CANNED_MEAT).tab(ModCreativeModeTab.THETHING_TAB)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}
