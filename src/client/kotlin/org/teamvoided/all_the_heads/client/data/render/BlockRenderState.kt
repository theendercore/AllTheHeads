package org.teamvoided.all_the_heads.client.data.render

import com.mojang.blaze3d.vertex.PoseStack
import com.mojang.math.Axis
import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.client.renderer.block.BlockRenderDispatcher
import net.minecraft.client.renderer.texture.OverlayTexture
import net.minecraft.world.level.block.state.BlockState
import org.teamvoided.all_the_heads.client.data.render.HeadRenderState.Companion.getLightOverride

data class BlockRenderState(
    val blockState: BlockState, val lightLevelOverride: Int? = null,
) : HeadRenderState {
    val blockRenderer: BlockRenderDispatcher = Minecraft.getInstance().blockRenderer
    override fun render(
        animationProgress: Float, yaw: Float, pitch: Float,
        matrices: PoseStack, vertexConsumers: MultiBufferSource, light: Int,
    ) {
        matrices.pushPose()
        matrices.translate(-.5f, 0f, -.5f)
        matrices.rotateAround(Axis.YP.rotationDegrees(yaw), .5f, 0f, .5f)
        matrices.rotateAround(Axis.XP.rotationDegrees(180f), .5f, 0f, .5f)
        blockRenderer.renderSingleBlock(
            blockState, matrices, vertexConsumers,
            getLightOverride(light, lightLevelOverride), OverlayTexture.NO_OVERLAY
        )
        matrices.popPose()
    }
}

