package net.realfancymoo.thething.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.realfancymoo.thething.TheThing;

public class ModEffects {
    public static DeferredRegister<MobEffect> MOB_EFFECTS =
            DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, TheThing.MOD_ID);

    public static final RegistryObject<MobEffect> INFECTED = MOB_EFFECTS.register("infected",
            () -> new InfectedEffect(MobEffectCategory.HARMFUL, 11141120));

    public static void register(IEventBus bus)
    {
        MOB_EFFECTS.register(bus);
    }
}
