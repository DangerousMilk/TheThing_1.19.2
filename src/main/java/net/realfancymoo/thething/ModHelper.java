package net.realfancymoo.thething;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.*;
import net.minecraft.world.entity.animal.horse.Horse;
import net.minecraft.world.entity.animal.horse.Mule;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.npc.Villager;

public class ModHelper {
    public static int getParasiteCount(LivingEntity entity)
    {
        if (entity instanceof Cow) {
            return 3;
        }
        else if  (entity instanceof Horse) {
            return 4;
        }
        else if  (entity instanceof Mule) {
            return 3;
        }
        else if  (entity instanceof Villager) {
            return 3;
        }
        else if  (entity instanceof PolarBear) {
            return 4;
        }
        else if  (entity instanceof Panda) {
            return 4;
        }
        else if  (entity instanceof EnderDragon) {
            return 15;
        }
        else {
            return 2;
        }
    }
}
