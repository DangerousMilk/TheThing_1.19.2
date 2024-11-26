package net.realfancymoo.thething.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.realfancymoo.thething.TheThing;
import net.realfancymoo.thething.entity.custom.ParasiteEntity;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class ParasiteRenderer extends GeoEntityRenderer<ParasiteEntity> {
    public ParasiteRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new ParasiteModel());
        shadowRadius = 0.3f;
    }

    @Override
    public ResourceLocation getTextureLocation(ParasiteEntity animatable) {
        return new ResourceLocation(TheThing.MOD_ID, "textures/entity/parasite.png");
    }

    @Override
    public RenderType getRenderType(ParasiteEntity animatable, float partialTick, PoseStack poseStack,
                                    @Nullable MultiBufferSource bufferSource,
                                    @Nullable VertexConsumer buffer, int packedLight, ResourceLocation texture) {
        return super.getRenderType(animatable, partialTick, poseStack, bufferSource, buffer, packedLight, texture);
    }
}
