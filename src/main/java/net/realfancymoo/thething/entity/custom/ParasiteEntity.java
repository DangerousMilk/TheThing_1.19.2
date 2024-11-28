package net.realfancymoo.thething.entity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.realfancymoo.thething.effect.ModEffects;
import net.realfancymoo.thething.entity.ParasiteMeleeAttackGoal;
import net.realfancymoo.thething.entity.ParasiteNearestAttackableTargetGoal;
import net.realfancymoo.thething.sound.ModSounds;
import software.bernie.geckolib3.core.AnimationState;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

public class ParasiteEntity extends Monster implements IAnimatable {
    private AnimationFactory animationFactory = GeckoLibUtil.createFactory(this);

    private boolean isAttacking = false;

    public ParasiteEntity(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public static AttributeSupplier setAttributes(){
        return Monster.createMobAttributes()
                .add(Attributes.MAX_HEALTH,10.0D)
                .add(Attributes.ATTACK_DAMAGE,2.0f)
                .add(Attributes.ATTACK_SPEED,1.0f)
                .add(Attributes.FOLLOW_RANGE, 40.0f)
                .add(Attributes.MOVEMENT_SPEED,0.25f).build();
    }

    private<E extends IAnimatable> PlayState predicate(AnimationEvent<E> event)
    {
        if(event.isMoving())
        {
            event.getController().setAnimation(new AnimationBuilder().loop("animation.parasite.move"));
            return PlayState.CONTINUE;
        }
        else
        {
            event.getController().setAnimation(new AnimationBuilder().loop("animation.parasite.idle"));
            return PlayState.CONTINUE;
        }
    }

    private<E extends IAnimatable> PlayState attackPredicate(AnimationEvent<E> event)
    {
        if(this.swinging && event.getController().getAnimationState().equals(AnimationState.Stopped))
        {
            event.getController().markNeedsReload();
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.parasite.attack"));
            this.swinging = false;
        }

        return PlayState.CONTINUE;
    }

    @Override
    public boolean doHurtTarget(Entity pEntity) {
        if (!super.doHurtTarget(pEntity)) {
            return false;
        } else {
            if (pEntity instanceof LivingEntity livingEntity) {
                if(random.nextInt(10) >= 7)
                {
                    livingEntity.addEffect(new MobEffectInstance(ModEffects.INFECTED.get(), 200), this);

                    if(livingEntity.isAlive()) {
                        this.remove(RemovalReason.DISCARDED);
                    }
                }
            }
            return true;
        }
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(2, new AvoidEntityGoal<>(this, Player.class, 16.0f, (double) 1.55f, (double) 2f));
        this.goalSelector.addGoal(3, new MeleeAttackGoal(this, 1.05D, true));
        this.goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, (double)1.0F));
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));

        this.targetSelector.addGoal(1, new ParasiteNearestAttackableTargetGoal(this, Animal.class, true,
                (entity)->!entity.getActiveEffects().stream().anyMatch(effect -> effect.getEffect() == ModEffects.INFECTED.get())));
        this.targetSelector.addGoal(1, new ParasiteNearestAttackableTargetGoal(this, AbstractVillager.class, true,
                (entity)->!entity.getActiveEffects().stream().anyMatch(effect -> effect.getEffect() == ModEffects.INFECTED.get())));
    }

    @Override
    public void registerControllers(AnimationData animationData) {
        animationData.addAnimationController(new AnimationController(this, "controller", 0, this::predicate));
        animationData.addAnimationController(new AnimationController(this, "attackController", 0, this::attackPredicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return animationFactory;
    }

    protected SoundEvent getAmbientSound() {
        return ModSounds.PARASITE_AMBIENT.get();
    }

    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return SoundEvents.SILVERFISH_HURT;
    }

    protected SoundEvent getDeathSound() {
        return ModSounds.PARASITE_DEATH.get();
    }

    protected SoundEvent getStepSound() {
        return SoundEvents.SILVERFISH_STEP;
    }

    protected void playStepSound(BlockPos pPos, BlockState pBlock) {
        this.playSound(this.getStepSound(), 0.15F, 1.0F);
    }
}
