package net.realfancymoo.thething.effect;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

public class InfectedEffect extends MobEffect {
    public InfectedEffect(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor);
    }

    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        /*
        if(!pLivingEntity.level.isClientSide())
        {
            System.out.println("balls hurt!");
        }
         */
        super.applyEffectTick(pLivingEntity, pAmplifier);
    }

    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        return true;
    }

    @Override
    public Object getEffectRendererInternal() {
        return null;
    }
}
