package org.teamvoided.all_the_heads.mixin.client;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.renderer.entity.layers.CustomHeadLayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.SkullModelBase;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.SkullBlock;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ResolvableProfile;
import org.teamvoided.all_the_heads.client.data.RenderLocation;

import static org.teamvoided.all_the_heads.client.SkullRenderingKt.renderSkull;
import static org.teamvoided.all_the_heads.utils.UtilsKt.getHeadData;

@Mixin(CustomHeadLayer.class)
public class CustomHeadLayerMixin {

    @WrapWithCondition(method = "render(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;ILnet/minecraft/world/entity/LivingEntity;FFFFFF)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/blockentity/SkullBlockRenderer;renderSkull(Lnet/minecraft/core/Direction;FFLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;ILnet/minecraft/client/model/SkullModelBase;Lnet/minecraft/client/renderer/RenderType;)V"))
    boolean customSkullRendering(Direction direction, float yaw, float animationProgress, PoseStack matrices, MultiBufferSource vertexConsumers, int light, SkullModelBase model, RenderType renderType,
                                 @Local ItemStack stack, @Local SkullBlock.Type skullType, @Local ResolvableProfile profileComponent) {
        if (skullType == SkullBlock.Types.PLAYER) {
            return renderSkull(direction, yaw, animationProgress, matrices, vertexConsumers, light, getHeadData(stack), null, RenderLocation.ON_HEAD);
        }
        return true;
    }
}
