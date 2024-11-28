package net.realfancymoo.thething.event;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.living.MobEffectEvent;
import net.minecraftforge.event.server.ServerStartedEvent;
import net.minecraftforge.event.server.ServerStoppedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.realfancymoo.thething.ModHelper;
import net.realfancymoo.thething.TheThing;
import net.realfancymoo.thething.effect.ModEffects;
import net.realfancymoo.thething.entity.ModEntityTypes;
import net.realfancymoo.thething.entity.custom.ParasiteEntity;

public class ModEvents {
    @Mod.EventBusSubscriber(modid = TheThing.MOD_ID)
    public static class ForgeEvents
    {
        @SubscribeEvent
        public static void onItemUse(LivingEntityUseItemEvent.Finish event) {
            // Return if client side
            if(event.getEntity().getLevel().isClientSide()){return;}

            // If player ate
            if (event.getEntity() instanceof Player player) {
                ItemStack itemStack = event.getItem();

                // Check if the item is food and has an "infected" tag
                if (itemStack.isEdible()) {
                    // Check if the item has the "infected" tag
                    if (itemStack.getTag() != null && itemStack.getTag().getBoolean("infected")) {
                        player.addEffect(new MobEffectInstance(ModEffects.INFECTED.get(), 500));
                        player.sendSystemMessage(Component.literal("I don't feel so good..."));
                    }
                }
            }
        }

        @SubscribeEvent
        public static void onEntityStatusEffect(MobEffectEvent.Added event) {
            event.setCanceled(true);
        }

        @SubscribeEvent
        public static void serverStarted(ServerStartedEvent event)
        {
            TheThing.hiveMindSingleton.initializeHiveMind(event);
            System.out.println("[THE THING] Server started");
        }

        @SubscribeEvent
        public static void serverTick(TickEvent.ServerTickEvent event)
        {
            TheThing.hiveMindSingleton.tick(event);
        }

        @SubscribeEvent
        public static void serverStopped(ServerStoppedEvent event)
        {
            TheThing.hiveMindSingleton.saveData();
            System.out.println("[THE THING] Server Stopped");
        }

        @SubscribeEvent
        public static void entityDied(LivingDeathEvent event)
        {
            if(event.getEntity().getType() == ModEntityTypes.PARASITE.get())
            {
                TheThing.hiveMindSingleton.addEvolutionPoints(-2);
                System.out.println("[HIVE MIND] Parasite Died! Points Decreased");
            }
        }

        @SubscribeEvent
        public static void mobDrops(LivingDropsEvent event)
        {
            // Cancel default event
            event.setCanceled(true);

            var entity = event.getEntity();

            if (entity.getLevel() instanceof ServerLevel serverLevel) {
                boolean infected = entity.getActiveEffects().stream()
                        .anyMatch((effectInstance) -> effectInstance.getEffect() == ModEffects.INFECTED.get());

                var drops = event.getDrops();

                for (var drop : drops) {
                    // Create a new ItemEntity at the entity's position with the same item stack
                    ItemStack itemStack = drop.getItem();

                    if(infected && itemStack.isEdible()){
                        itemStack.getOrCreateTag().putBoolean("infected", true);
                    }

                    ItemEntity customItemEntity = new ItemEntity(serverLevel, entity.getX(), entity.getY(), entity.getZ(), itemStack);

                    // Optionally, set the velocity (movement) of the item drop
                    customItemEntity.setDeltaMovement(0, 0, 0);  // Change this to set a custom velocity if needed

                    // Add the custom item drop to the world
                    serverLevel.addFreshEntity(customItemEntity);

                    customItemEntity.setDeltaMovement(
                            (Math.random() - 0.5) * 0.1,  // Random horizontal motion (x-axis)
                            0.2,                          // Slight upward motion (gravity will handle fall)
                            (Math.random() - 0.5) * 0.1   // Random horizontal motion (z-axis)
                    );
                }

            }
        }

        @SubscribeEvent
        public static void effect(MobEffectEvent.Expired event)
        {
            var instance = event.getEffectInstance();
            if(instance.getEffect() == ModEffects.INFECTED.get())
            {
                var entity = event.getEntity();
                var level = entity.level;

                // Spawn Parasites
                int parasites = ModHelper.getParasiteCount(entity);
                for(int i = 0; i < parasites; i++)
                {
                    var parasiteEntity = new ParasiteEntity(ModEntityTypes.PARASITE.get(), level);
                    parasiteEntity.setPos(entity.position());
                    level.addFreshEntity(parasiteEntity);
                }

                // Particles
                entity.level.addParticle(ParticleTypes.EXPLOSION,
                        entity.position().x,
                        entity.position().y,
                        entity.position().z,
                        0D,
                        0D,
                        0D);

                // Add Evolution Points
                TheThing.hiveMindSingleton.addEvolutionPoints(parasites);

                // Kill the entity
                entity.kill();
            }
        }
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
