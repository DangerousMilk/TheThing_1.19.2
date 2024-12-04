package net.realfancymoo.thething.item;

import net.minecraft.world.food.Foods;
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
    public static final RegistryObject<Item> CAN_OPENER = ITEMS.register("can_opener",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.THETHING_TAB).defaultDurability(50)));
    public static final RegistryObject<Item> CANNED_MEAT = ITEMS.register("canned_meat",
            () -> new CannedFoodItem(new Item.Properties().food(ModFoods.CANNED_MEAT).tab(ModCreativeModeTab.THETHING_TAB)));
    public static final RegistryObject<Item> INFESTED_CANNED_MEAT = ITEMS.register("infested_canned_meat",
            () -> new CannedFoodItem(new Item.Properties().food(ModFoods.CANNED_MEAT).tab(ModCreativeModeTab.THETHING_TAB)));

    public static final RegistryObject<Item> INFESTED_PORKCHOP = ITEMS.register("infested_porkchop",
            () -> new Item(new Item.Properties().food(Foods.PORKCHOP).tab(ModCreativeModeTab.THETHING_TAB)));
    public static final RegistryObject<Item> INFESTED_COOKED_PORKCHOP = ITEMS.register("infested_cooked_porkchop",
            () -> new Item(new Item.Properties().food(Foods.COOKED_PORKCHOP).tab(ModCreativeModeTab.THETHING_TAB)));
    public static final RegistryObject<Item> INFESTED_BEEF = ITEMS.register("infested_beef",
            () -> new Item(new Item.Properties().food(Foods.BEEF).tab(ModCreativeModeTab.THETHING_TAB)));
    public static final RegistryObject<Item> INFESTED_COOKED_BEEF = ITEMS.register("infested_cooked_beef",
            () -> new Item(new Item.Properties().food(Foods.COOKED_BEEF).tab(ModCreativeModeTab.THETHING_TAB)));
    public static final RegistryObject<Item> INFESTED_CHICKEN = ITEMS.register("infested_chicken",
            () -> new Item(new Item.Properties().food(Foods.CHICKEN).tab(ModCreativeModeTab.THETHING_TAB)));
    public static final RegistryObject<Item> INFESTED_COOKED_CHICKEN = ITEMS.register("infested_cooked_chicken",
            () -> new Item(new Item.Properties().food(Foods.COOKED_CHICKEN).tab(ModCreativeModeTab.THETHING_TAB)));
    public static final RegistryObject<Item> INFESTED_MUTTON = ITEMS.register("infested_mutton",
            () -> new Item(new Item.Properties().food(Foods.MUTTON).tab(ModCreativeModeTab.THETHING_TAB)));
    public static final RegistryObject<Item> INFESTED_COOKED_MUTTON = ITEMS.register("infested_cooked_mutton",
            () -> new Item(new Item.Properties().food(Foods.COOKED_MUTTON).tab(ModCreativeModeTab.THETHING_TAB)));
    public static final RegistryObject<Item> INFESTED_RABBIT = ITEMS.register("infested_rabbit",
            () -> new Item(new Item.Properties().food(Foods.RABBIT).tab(ModCreativeModeTab.THETHING_TAB)));
    public static final RegistryObject<Item> INFESTED_COOKED_RABBIT = ITEMS.register("infested_cooked_rabbit",
            () -> new Item(new Item.Properties().food(Foods.COOKED_RABBIT).tab(ModCreativeModeTab.THETHING_TAB)));
    public static final RegistryObject<Item> INFESTED_COD = ITEMS.register("infested_cod",
            () -> new Item(new Item.Properties().food(Foods.COD).tab(ModCreativeModeTab.THETHING_TAB)));
    public static final RegistryObject<Item> INFESTED_COOKED_COD = ITEMS.register("infested_cooked_cod",
            () -> new Item(new Item.Properties().food(Foods.COOKED_COD).tab(ModCreativeModeTab.THETHING_TAB)));
    public static final RegistryObject<Item> INFESTED_SALMON = ITEMS.register("infested_salmon",
            () -> new Item(new Item.Properties().food(Foods.SALMON).tab(ModCreativeModeTab.THETHING_TAB)));
    public static final RegistryObject<Item> INFESTED_COOKED_SALMON = ITEMS.register("infested_cooked_salmon",
            () -> new Item(new Item.Properties().food(Foods.COOKED_SALMON).tab(ModCreativeModeTab.THETHING_TAB)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}
