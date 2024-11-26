package net.realfancymoo.thething.event;

import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.realfancymoo.thething.TheThing;
import net.realfancymoo.thething.entity.ModEntityTypes;
import net.realfancymoo.thething.entity.custom.ParasiteEntity;

public class ModEvents {
    @Mod.EventBusSubscriber(modid = TheThing.MOD_ID)
    public static class ForgeEvents
    {

    }

    @Mod.EventBusSubscriber(modid = TheThing.MOD_ID, bus=Mod.EventBusSubscriber.Bus.MOD)
    public static class ModEventBusEvents
    {
        @SubscribeEvent
        public static void entityAttributeEvent(EntityAttributeCreationEvent event)
        {
            event.put(ModEntityTypes.PARASITE.get(), ParasiteEntity.setAttributes());
        }
    }
}
