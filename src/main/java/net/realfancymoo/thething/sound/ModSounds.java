package net.realfancymoo.thething.sound;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.realfancymoo.thething.TheThing;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, TheThing.MOD_ID);

    public static final RegistryObject<SoundEvent> PARASITE_DEATH = registerSoundEvent("parasite_death");
    public static final RegistryObject<SoundEvent> PARASITE_AMBIENT = registerSoundEvent("parasite_ambient");

    private static RegistryObject<SoundEvent> registerSoundEvent(String name)
    {
        ResourceLocation id = new ResourceLocation(TheThing.MOD_ID, name);
        return SOUND_EVENTS.register(name, ()->new SoundEvent(id));
    }

    public static final void register(IEventBus bus)
    {
        SOUND_EVENTS.register(bus);
    }
}
