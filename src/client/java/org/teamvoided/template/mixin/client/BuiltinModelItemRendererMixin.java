package org.teamvoided.template.mixin.client;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.block.AbstractSkullBlock;
import net.minecraft.block.SkullBlock;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.model.AbstractSkullBlockEntityModel;
import net.minecraft.client.render.item.BuiltinModelItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.component.type.ProfileComponent;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Direction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import static org.teamvoided.template.utils.UtilsKt.getHeadData;
import static org.teamvoided.template.RenderKt.renderSkull;

@Mixin(BuiltinModelItemRenderer.class)
public class BuiltinModelItemRendererMixin {

    @WrapWithCondition(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/block/entity/SkullBlockEntityRenderer;renderSkull(Lnet/minecraft/util/math/Direction;FFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;ILnet/minecraft/client/render/block/entity/model/AbstractSkullBlockEntityModel;Lnet/minecraft/client/render/RenderLayer;)V"))
    boolean customSkullRendering(Direction direction, float yaw, float animationProgress, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, AbstractSkullBlockEntityModel model, RenderLayer renderLayer,
                                 @Local(argsOnly = true) ItemStack stack, @Local AbstractSkullBlock abstractSkullBlock, @Local ProfileComponent profileComponent) {
        if (abstractSkullBlock.getSkullType() == SkullBlock.Type.PLAYER) {
            return renderSkull(direction, yaw, animationProgress, matrices, vertexConsumers, light, getHeadData(stack), null);
        }

        return true;
    }
}
