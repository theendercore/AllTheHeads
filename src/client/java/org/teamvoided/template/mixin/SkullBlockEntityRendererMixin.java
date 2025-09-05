package org.teamvoided.template.mixin;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.block.SkullBlock;
import net.minecraft.block.entity.SkullBlockEntity;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.SkullBlockEntityRenderer;
import net.minecraft.client.render.block.entity.model.AbstractSkullBlockEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Direction;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.teamvoided.template.RenderKt;

import java.util.Map;

import static org.teamvoided.template.RenderKt.renderSkull;

@Mixin(SkullBlockEntityRenderer.class)
public class SkullBlockEntityRendererMixin {

    @Shadow @Final private Map<SkullBlock.SkullType, AbstractSkullBlockEntityModel> models;

    @WrapWithCondition(method = "render(Lnet/minecraft/block/entity/SkullBlockEntity;FLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;II)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/block/entity/SkullBlockEntityRenderer;renderSkull(Lnet/minecraft/util/math/Direction;FFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;ILnet/minecraft/client/render/block/entity/model/AbstractSkullBlockEntityModel;Lnet/minecraft/client/render/RenderLayer;)V"))
    boolean customSkullRendering(Direction direction, float yaw, float animationProgress, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, AbstractSkullBlockEntityModel model, RenderLayer renderLayer,
                                 @Local(argsOnly = true) SkullBlockEntity skullBlockEntity, @Local SkullBlock.SkullType skullType
    ) {
        if (skullType == SkullBlock.Type.PLAYER) {
            RenderKt.models = models;
            renderSkull(direction, yaw, animationProgress, matrices, vertexConsumers, light, model, renderLayer, null);
            return false;
        }

        return true;
    }
}
