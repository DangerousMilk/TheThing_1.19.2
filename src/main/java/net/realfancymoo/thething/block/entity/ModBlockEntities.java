package net.realfancymoo.thething.block.entity;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.realfancymoo.thething.TheThing;
import net.realfancymoo.thething.block.ModBlocks;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, TheThing.MOD_ID);

    public static final RegistryObject<BlockEntityType<IndustrialBarrelBlockEntity>> INDUSTRIAL_BARREL_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("industrialbarrelblockentity", () ->
                    BlockEntityType.Builder.of(IndustrialBarrelBlockEntity::new,
                            ModBlocks.INDUSTRIAL_BARREL.get()).build(null));

    public static void register(IEventBus bus){BLOCK_ENTITIES.register(bus);}
}
