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
import org.teamvoided.all_the_heads.client.data.ProfileDataMode
import org.teamvoided.all_the_heads.client.data.SkullRenderContext
import org.teamvoided.all_the_heads.client.data.SkullRenderData
import org.teamvoided.all_the_heads.client.data.textures.MiscTextures.TEXTURE_BLACKLIST
import org.teamvoided.all_the_heads.client.init.ModelsManager
import org.teamvoided.all_the_heads.client.utils.getTexture

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
    val data = fetchSkullRenderInfo(ctx)
    debugRenderer(direction, yaw, animationProgress, matrices, vertexConsumers, light, ctx, data)
    if (data == null) return true

    matrices.pushPose()
    if (direction == null) matrices.translate(0.5f, 0.0f, 0.5f)
    else {
        val f = 0.25f
        matrices.translate(0.5f - direction.stepX * f, f, 0.5f - direction.stepZ * f)
    }

    matrices.scale(-1.0f, -1.0f, 1.0f)
    val customModel = data.model()
    customModel.setupAnim(animationProgress, yaw, 0.0f)
    customModel.renderToBuffer(
        matrices,
        vertexConsumers.getBuffer(data.renderType),
        getLightOverride(light, data),
        OverlayTexture.NO_OVERLAY
    )
    matrices.popPose()
    return false
}

fun getLightOverride(light: Int, data: SkullRenderData): Int {
    val override = data.lightLevelOverride ?: return light
    val sky = light shr 20
    val block = if (light > 256) 0 else light shr 4
    return if (sky < override || block < override) override shl 4 else light
}

fun fetchSkullRenderInfo(ctx: SkullRenderContext): SkullRenderData? {
    when (clientConfig.headRenderMode.get()) {
        HeadRenderMode.PROFILE -> when (clientConfig.profileDataMode.get()) {
            ProfileDataMode.TEXTURE -> {
                val texture = ctx.skullOwner?.getTexture() ?: return null
                val data = ModelsManager.models[texture]
                if (data == null) {
                    if (TEXTURE_BLACKLIST.contains(texture)) return null
                    sendError("Could not find model for texture $texture")
                    return null
                }
                return data
            }

            ProfileDataMode.ID, ProfileDataMode.NAME -> return null
        }


        HeadRenderMode.CUSTOM_DATA -> {
            val id = ctx.skullId ?: return null
            val model = models[SkullBlock.Types.DRAGON]
            if (model == null) {
                sendError("Could not find model for ${SkullBlock.Types.DRAGON}")

                return null
            }
            return SkullRenderData({ model }, rType("textures/entity/enderdragon/dragon.png"), null)
        }
    }
}


fun rType(texture: String): RenderType = RenderType.entityCutoutNoCullZOffset(tryParseId(texture)!!)