package org.teamvoided.all_the_heads.client.rendering

import com.mojang.blaze3d.vertex.PoseStack
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.client.renderer.RenderType
import net.minecraft.client.renderer.texture.OverlayTexture
import net.minecraft.core.Direction
import net.minecraft.world.level.block.SkullBlock
import org.teamvoided.all_the_heads.AllTheHeads.tryParseId
import org.teamvoided.all_the_heads.client.AllTheHeadsClient.clientConfig
import org.teamvoided.all_the_heads.client.AllTheHeadsClient.sendError
import org.teamvoided.all_the_heads.client.data.HeadRenderMode
import org.teamvoided.all_the_heads.client.data.SkullRenderContext
import org.teamvoided.all_the_heads.client.data.SkullRenderData

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
    debugRenderer(direction, yaw, animationProgress, matrices, vertexConsumers, light, ctx)
    val data = fetchSkullRenderInfo(ctx) ?: return true

    matrices.pushPose()
    if (direction == null) matrices.translate(0.5f, 0.0f, 0.5f)
    else {
        val f = 0.25f
        matrices.translate(0.5f - direction.stepX * f, f, 0.5f - direction.stepZ * f)
    }

    matrices.scale(-1.0f, -1.0f, 1.0f)
    val customModel = data.model
    customModel.setupAnim(animationProgress, yaw, 0.0f)
    customModel.renderToBuffer(
        matrices,
        vertexConsumers.getBuffer(data.renderType),
        light,
        OverlayTexture.NO_OVERLAY
    )
    matrices.popPose()
    return false
}

fun fetchSkullRenderInfo(ctx: SkullRenderContext): SkullRenderData? {
    when (clientConfig.headRenderMode.get()) {
        HeadRenderMode.PROFILE -> {
            val profile = ctx.skullOwner?.gameProfile
            if (profile == null) return null
            val model = models[SkullBlock.Types.PIGLIN]
            if (model == null) {
                sendError("Could not find model for ${SkullBlock.Types.PIGLIN}")

                return null
            }

            return SkullRenderData(model, rType("textures/entity/piglin/piglin.png"))
        }

        HeadRenderMode.CUSTOM_DATA -> {
            val id = ctx.skullId
            if (id == null) return null
            val model = models[SkullBlock.Types.DRAGON]
            if (model == null) {
                sendError("Could not find model for ${SkullBlock.Types.DRAGON}")

                return null
            }
            return SkullRenderData(model, rType("textures/entity/enderdragon/dragon.png"))
        }
    }
}


fun rType(texture: String): RenderType = RenderType.entityCutoutNoCullZOffset(tryParseId(texture)!!)