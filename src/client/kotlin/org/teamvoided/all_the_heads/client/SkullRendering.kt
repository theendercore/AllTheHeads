package org.teamvoided.all_the_heads.client

import com.mojang.blaze3d.vertex.PoseStack
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.client.renderer.RenderType
import net.minecraft.client.renderer.texture.OverlayTexture
import net.minecraft.core.Direction
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.level.block.SkullBlock
import net.minecraft.world.level.block.entity.SkullBlockEntity

@Suppress("DEPRECATION")
fun renderSkull(
    direction: Direction?,
    yaw: Float,
    animationProgress: Float,
    matrices: PoseStack,
    vertexConsumers: MultiBufferSource,
    light: Int,
    id: ResourceLocation?,
    be: SkullBlockEntity?,
): Boolean {
    if (id == null) return true
    val data = fetchSkullData(id) ?: return true
    debugRenderer(direction, yaw, animationProgress, matrices, vertexConsumers, light, id, be)

    matrices.pushPose()
    if (direction == null) matrices.translate(0.5f, 0.0f, 0.5f)
    else {
        val f = 0.25f
        matrices.translate(0.5f - direction.stepX * f, f, 0.5f - direction.stepZ * f)
    }

    matrices.scale(-1.0f, -1.0f, 1.0f)
    val customModel = models[SkullBlock.Types.DRAGON]
    customModel?.setupAnim(animationProgress, yaw, 0.0f)
    val renderLayer =
        RenderType.entityCutoutNoCullZOffset(ResourceLocation.tryParse("textures/entity/enderdragon/dragon.png")!!)
    customModel?.renderToBuffer(
        matrices,
        vertexConsumers.getBuffer(renderLayer),
        light,
        OverlayTexture.NO_OVERLAY
    )
    matrices.popPose()
    return false
}