package net.realfancymoo.thething.entity;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.realfancymoo.thething.TheThing;
import net.realfancymoo.thething.entity.custom.ParasiteEntity;

public class ModEntityTypes {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, TheThing.MOD_ID);

    public static final RegistryObject<EntityType<ParasiteEntity>> PARASITE = ENTITY_TYPES.register("parasite",
            () -> EntityType.Builder.of(ParasiteEntity::new, MobCategory.MONSTER)
                    .sized(0.65f, 0.55f)
                    .build(new ResourceLocation(TheThing.MOD_ID, "parasite").toString()));

    public static void register(IEventBus bus){
        ENTITY_TYPES.register(bus);
    }
}
