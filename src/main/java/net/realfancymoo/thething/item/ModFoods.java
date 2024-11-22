package net.realfancymoo.thething.item;

import net.minecraft.world.food.FoodProperties;

public class ModFoods {
    public static final FoodProperties PIZZA = new FoodProperties.Builder().nutrition(6).saturationMod(0.5f).build();
    public static final FoodProperties CANNED_MEAT = new FoodProperties.Builder().nutrition(8).saturationMod(0.8f).build();
}
