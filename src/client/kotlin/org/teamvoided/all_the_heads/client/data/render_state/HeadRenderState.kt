package org.teamvoided.all_the_heads.client.data.render_state

import com.mojang.blaze3d.vertex.PoseStack
import net.minecraft.client.renderer.MultiBufferSource

interface HeadRenderState {
    fun render(
        animationProgress: Float, yaw: Float, pitch: Float,
        matrices: PoseStack, vertexConsumers: MultiBufferSource, light: Int,
    )

    companion object {
        fun getLightOverride(light: Int, lightLevelOverride: Int?): Int {
            val override = lightLevelOverride ?: return light
            val sky = light shr 20
            val block = if (light > 256) 0 else light shr 4
            return if (sky < override || block < override) override shl 4 else light
        }
    }
}
