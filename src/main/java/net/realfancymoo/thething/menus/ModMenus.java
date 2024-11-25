package net.realfancymoo.thething.menus;

import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.realfancymoo.thething.TheThing;

public class ModMenus {
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(ForgeRegistries.MENU_TYPES, TheThing.MOD_ID);

    public static final RegistryObject<MenuType<IndustrialBarrelMenu>> INDUSTRIAL_BARREL_MENU = MENUS.register("industrialbarrelmenu",
            ()->new MenuType(IndustrialBarrelMenu::new));

    public static void register(IEventBus eventBus){
        MENUS.register(eventBus);
    }
}
