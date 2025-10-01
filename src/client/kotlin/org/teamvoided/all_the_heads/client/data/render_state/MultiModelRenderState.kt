package org.teamvoided.all_the_heads.client.data.render_state

import com.mojang.blaze3d.vertex.PoseStack
import net.minecraft.client.renderer.MultiBufferSource

open class MultiModelRenderState(val models: List<HeadRenderState>) : HeadRenderState {
    constructor(vararg model: HeadRenderState) : this(model.toList())

    override fun render(
        animationProgress: Float, yaw: Float, pitch: Float,
        matrices: PoseStack, vertexConsumers: MultiBufferSource, light: Int,
    ) {
        for (model in models) {
            model.render(animationProgress, yaw, 0.0f, matrices, vertexConsumers, light)
        }
    }
}