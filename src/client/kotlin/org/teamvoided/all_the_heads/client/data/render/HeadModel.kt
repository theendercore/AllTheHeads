package org.teamvoided.all_the_heads.client.data.render

import com.mojang.blaze3d.vertex.PoseStack
import com.mojang.serialization.Codec
import net.minecraft.client.renderer.MultiBufferSource
import org.teamvoided.all_the_heads.client.init.ATHBuiltInRegistries.HEAD_RENDER_STATE_TYPE

interface HeadModel {
    fun getType(): HeadModelType<*>
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

        val CODEC: Codec<HeadModel> =
            HEAD_RENDER_STATE_TYPE.byNameCodec().dispatch(HeadModel::getType, HeadModelType<*>::codec)
    }
}
