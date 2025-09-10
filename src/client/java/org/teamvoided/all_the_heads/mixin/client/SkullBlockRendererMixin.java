package org.teamvoided.all_the_heads.mixin.client;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.SkullModelBase;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.SkullBlockRenderer;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.SkullBlock;
import net.minecraft.world.level.block.entity.SkullBlockEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.teamvoided.all_the_heads.client.data.SkullRenderContext;
import org.teamvoided.all_the_heads.client.data.RenderLocation;
import org.teamvoided.all_the_heads.client.rendering.DebugRenderingKt;

import java.util.Map;

import static org.teamvoided.all_the_heads.AllTheHeads.HEAD_ATTACHMENT;
import static org.teamvoided.all_the_heads.client.rendering.SkullRenderingKt.renderSkull;

@Mixin(SkullBlockRenderer.class)
public abstract class SkullBlockRendererMixin {


    @Shadow @Final private Map<SkullBlock.Type, SkullModelBase> modelByType;

    @SuppressWarnings("UnstableApiUsage")
    @WrapWithCondition(method = "render(Lnet/minecraft/world/level/block/entity/SkullBlockEntity;FLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;II)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/blockentity/SkullBlockRenderer;renderSkull(Lnet/minecraft/core/Direction;FFLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;ILnet/minecraft/client/model/SkullModelBase;Lnet/minecraft/client/renderer/RenderType;)V"))
    boolean customSkullRendering(Direction direction, float yaw, float animationProgress, PoseStack matrices, MultiBufferSource bufferSource, int light, SkullModelBase model, RenderType renderType,
                                 @Local(argsOnly = true) SkullBlockEntity skullBlockEntity, @Local SkullBlock.Type skullType) {
        if (skullType == SkullBlock.Types.PLAYER) {
            DebugRenderingKt.models = modelByType;
            return renderSkull(direction, yaw, animationProgress, matrices, bufferSource, light, skullBlockEntity.getAttached(HEAD_ATTACHMENT), skullBlockEntity, new SkullRenderContext(RenderLocation.IN_WORLD));
        }

        return true;
    }
}
