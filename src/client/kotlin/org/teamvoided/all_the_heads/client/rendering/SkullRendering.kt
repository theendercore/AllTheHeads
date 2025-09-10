package org.teamvoided.all_the_heads.client.rendering

import com.mojang.blaze3d.vertex.PoseStack
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.client.renderer.RenderType
import net.minecraft.client.renderer.texture.OverlayTexture
import net.minecraft.core.Direction
import net.minecraft.world.level.block.SkullBlock
import org.teamvoided.all_the_heads.AllTheHeads.tryParseId
import org.teamvoided.all_the_heads.client.AllTheHeadsClient.clientConfig
import org.teamvoided.all_the_heads.client.data.HeadRenderMode
import org.teamvoided.all_the_heads.client.data.SkullRenderContext

@Suppress("DEPRECATION")
fun renderSkull(
    direction: Direction?,
    yaw: Float,
    animationProgress: Float,
    matrices: PoseStack,
    vertexConsumers: MultiBufferSource,
    light: Int,
    ctx: SkullRenderContext,
): Boolean {
    if (ctx.skullId == null) return true
    val data = fetchSkullData(ctx.skullId) ?: return true
    debugRenderer(direction, yaw, animationProgress, matrices, vertexConsumers, light, ctx)

    val customModel =
        if (clientConfig.headRenderMode.get() == HeadRenderMode.NAME_BASED) models[SkullBlock.Types.PIGLIN]
        else models[SkullBlock.Types.DRAGON]
    val renderLayer = RenderType.entityCutoutNoCullZOffset(
        tryParseId(
            if (clientConfig.headRenderMode.get() == HeadRenderMode.NAME_BASED) "textures/entity/piglin/piglin.png"
            else "textures/entity/enderdragon/dragon.png"
        )!!
    )

    matrices.pushPose()
    if (direction == null) matrices.translate(0.5f, 0.0f, 0.5f)
    else {
        val f = 0.25f
        matrices.translate(0.5f - direction.stepX * f, f, 0.5f - direction.stepZ * f)
    }

    matrices.scale(-1.0f, -1.0f, 1.0f)
    customModel?.setupAnim(animationProgress, yaw, 0.0f)
    customModel?.renderToBuffer(
        matrices,
        vertexConsumers.getBuffer(renderLayer),
        light,
        OverlayTexture.NO_OVERLAY
    )
    matrices.popPose()
    return false
}