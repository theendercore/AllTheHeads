package org.teamvoided.all_the_heads.client.data.render_state

import com.mojang.blaze3d.vertex.PoseStack
import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.client.renderer.block.BlockRenderDispatcher
import net.minecraft.client.renderer.texture.OverlayTexture
import net.minecraft.world.level.block.state.BlockState
import org.teamvoided.all_the_heads.client.data.render_state.HeadRenderState.Companion.getLightOverride

data class BlockRenderState(
    val blockState: BlockState, val lightLevelOverride: Int? = null,
) : HeadRenderState {
    val blockRenderer: BlockRenderDispatcher = Minecraft.getInstance().blockRenderer
    override fun render(
        animationProgress: Float, yaw: Float, pitch: Float,
        matrices: PoseStack, vertexConsumers: MultiBufferSource, light: Int,
    ) {
        blockRenderer.renderSingleBlock(
            blockState, matrices, vertexConsumers,
            getLightOverride(light, lightLevelOverride), OverlayTexture.NO_OVERLAY
        )
    }
}

