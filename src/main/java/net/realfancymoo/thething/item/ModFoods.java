package net.realfancymoo.thething.item;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.food.Foods;

public class ModFoods {
    public static final FoodProperties PIZZA = new FoodProperties.Builder().nutrition(8).saturationMod(0.5f).build();
    public static final FoodProperties CANNED_MEAT = new FoodProperties.Builder().nutrition(14).saturationMod(1f).build();

    public static final FoodProperties INFESTED_PORKCHOP = Foods.PORKCHOP;
    public static final FoodProperties INFESTED_COOKED_PORKCHOP = Foods.COOKED_PORKCHOP;
    public static final FoodProperties INFESTED_BEEF = Foods.BEEF;
    public static final FoodProperties INFESTED_COOKED_BEEF = Foods.COOKED_BEEF;
    public static final FoodProperties INFESTED_CHICKEN = Foods.CHICKEN;
    public static final FoodProperties INFESTED_COOKED_CHICKEN = Foods.COOKED_CHICKEN;
    public static final FoodProperties INFESTED_MUTTON = Foods.MUTTON;
    public static final FoodProperties INFESTED_COOKED_MUTTON = Foods.COOKED_MUTTON;
    public static final FoodProperties INFESTED_RABBIT = Foods.RABBIT;
    public static final FoodProperties INFESTED_COOKED_RABBIT = Foods.COOKED_RABBIT;
    public static final FoodProperties INFESTED_COD = Foods.COD;
    public static final FoodProperties INFESTED_COOKED_COD = Foods.COOKED_COD;
    public static final FoodProperties INFESTED_SALMON = Foods.SALMON;
    public static final FoodProperties INFESTED_COOKED_SALMON = Foods.COOKED_SALMON;
}
