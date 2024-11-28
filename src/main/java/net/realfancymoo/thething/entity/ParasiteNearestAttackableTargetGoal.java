package net.realfancymoo.thething.entity;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.realfancymoo.thething.effect.ModEffects;
import org.jetbrains.annotations.Nullable;

import java.util.function.Predicate;

public class ParasiteNearestAttackableTargetGoal extends NearestAttackableTargetGoal {
    public ParasiteNearestAttackableTargetGoal(Mob pMob, Class pTargetType, boolean pMustSee) {
        super(pMob, pTargetType, pMustSee);
    }

    public ParasiteNearestAttackableTargetGoal(Mob pMob, Class pTargetType, boolean pMustSee, Predicate<LivingEntity> pTargetPredicate) {
        super(pMob, pTargetType, pMustSee, pTargetPredicate);
    }

    public ParasiteNearestAttackableTargetGoal(Mob pMob, Class pTargetType, boolean pMustSee, boolean pMustReach) {
        super(pMob, pTargetType, pMustSee, pMustReach);
    }

    public ParasiteNearestAttackableTargetGoal(Mob pMob, Class pTargetType, int pRandomInterval, boolean pMustSee, boolean pMustReach, @Nullable Predicate pTargetPredicate) {
        super(pMob, pTargetType, pRandomInterval, pMustSee, pMustReach, pTargetPredicate);
    }

    @Override
    public boolean canContinueToUse() {
        LivingEntity target = this.mob.getTarget();
        if(target != null) {
            if(target.getActiveEffects().stream().anyMatch(effect -> effect.getEffect() == ModEffects.INFECTED.get())) {
                findTarget();
                return false;
            }
        }
        return super.canContinueToUse();
    }

    @Override
    public boolean canUse() {
        LivingEntity target = this.mob.getTarget();
        if(target != null) {
            if(target.getActiveEffects().stream().anyMatch(effect -> effect.getEffect() == ModEffects.INFECTED.get())) {
                findTarget();
                return false;
            }
        }
        return super.canUse();
    }
}
