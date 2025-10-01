package org.teamvoided.all_the_heads.client.data.render_state

import com.mojang.blaze3d.vertex.PoseStack
import net.minecraft.client.model.SkullModelBase
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.client.renderer.RenderType
import net.minecraft.client.renderer.texture.OverlayTexture
import org.teamvoided.all_the_heads.client.data.render_state.HeadRenderState.Companion.getLightOverride

data class SingleModelRenderState(
    val model: () -> SkullModelBase, val renderType: RenderType, val lightLevelOverride: Int?,
) : HeadRenderState {
    override fun render(
        animationProgress: Float, yaw: Float, pitch: Float,
        matrices: PoseStack,
        vertexConsumers: MultiBufferSource,
        light: Int,
    ) {
        val model = model()
        model.setupAnim(animationProgress, yaw, 0.0f)
        model.renderToBuffer(
            matrices,
            vertexConsumers.getBuffer(renderType),
            getLightOverride(light, lightLevelOverride),
            OverlayTexture.NO_OVERLAY
        )
    }
}

