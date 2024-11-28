package net.realfancymoo.thething.entity.client;

import net.minecraft.resources.ResourceLocation;
import net.realfancymoo.thething.TheThing;
import net.realfancymoo.thething.entity.custom.ParasiteEntity;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ParasiteModel extends AnimatedGeoModel<ParasiteEntity> {

    @Override
    public ResourceLocation getModelResource(ParasiteEntity parasiteEntity) {
        return new ResourceLocation(TheThing.MOD_ID, "geo/parasitegeo.json");
    }

    @Override
    public ResourceLocation getTextureResource(ParasiteEntity parasiteEntity) {
        return new ResourceLocation(TheThing.MOD_ID, "textures/entity/parasite.png");
    }

    @Override
    public ResourceLocation getAnimationResource(ParasiteEntity parasiteEntity) {
        return new ResourceLocation(TheThing.MOD_ID, "animations/parasiteanimation.json");
    }
}
