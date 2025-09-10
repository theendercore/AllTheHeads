package org.teamvoided.all_the_heads.mixin.client;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import com.llamalad7.mixinextras.sugar.Local;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.SkullModelBase;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ResolvableProfile;
import net.minecraft.world.level.block.AbstractSkullBlock;
import net.minecraft.world.level.block.SkullBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.teamvoided.all_the_heads.client.data.AdditionRenderData;
import org.teamvoided.all_the_heads.client.data.RenderLocation;

import static org.teamvoided.all_the_heads.client.rendering.SkullRenderingKt.renderSkull;
import static org.teamvoided.all_the_heads.utils.UtilsKt.getHeadId;

@Mixin(BlockEntityWithoutLevelRenderer.class)
public class BlockEntityWithoutLevelRendererMixin {

    @WrapWithCondition(method = "renderByItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/blockentity/SkullBlockRenderer;renderSkull(Lnet/minecraft/core/Direction;FFLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;ILnet/minecraft/client/model/SkullModelBase;Lnet/minecraft/client/renderer/RenderType;)V"))
    boolean customSkullRendering(Direction direction, float yaw, float animationProgress, PoseStack matrices, MultiBufferSource vertexConsumers, int light, SkullModelBase model, RenderType renderType,
                                 @Local(argsOnly = true) ItemStack stack,@Local(argsOnly = true) ItemDisplayContext itemDisplayContext,
                                 @Local AbstractSkullBlock abstractSkullBlock, @Local ResolvableProfile profileComponent) {
        if (abstractSkullBlock.getType() == SkullBlock.Types.PLAYER) {
            return renderSkull(direction, yaw, animationProgress, matrices, vertexConsumers, light, getHeadId(stack), null, new AdditionRenderData(RenderLocation.INVENTORY, itemDisplayContext));
        }

        return true;
    }
}
