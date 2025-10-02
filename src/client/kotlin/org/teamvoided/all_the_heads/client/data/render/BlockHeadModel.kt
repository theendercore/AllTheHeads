package org.teamvoided.all_the_heads.client.data.render

import com.mojang.blaze3d.vertex.PoseStack
import com.mojang.math.Axis
import com.mojang.serialization.Codec
import com.mojang.serialization.MapCodec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.client.renderer.block.BlockRenderDispatcher
import net.minecraft.client.renderer.texture.OverlayTexture
import net.minecraft.world.level.block.state.BlockState
import org.teamvoided.all_the_heads.client.data.render.HeadModel.Companion.getLightOverride
import org.teamvoided.all_the_heads.client.init.ATHHeadModels
import java.util.*
import kotlin.jvm.optionals.getOrNull

open class BlockHeadModel(
    val blockState: BlockState, val lightLevelOverride: Int? = null,
) : HeadModel {
    val blockRenderer: BlockRenderDispatcher = Minecraft.getInstance().blockRenderer

    init {
        if (lightLevelOverride != null && lightLevelOverride !in 1..15) {
            error("LightLevelOverride [ $lightLevelOverride ] is outside of the accepted range (1..15)!")
        }
    }

    override fun getType(): HeadModelType<*> = ATHHeadModels.BLOCK
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

    companion object {
        val CODEC: MapCodec<BlockHeadModel> = RecordCodecBuilder.mapCodec {
            it.group(
                BlockState.CODEC.fieldOf("block_state").forGetter(BlockHeadModel::blockState),
                Codec.intRange(1, 15).optionalFieldOf("light_level_override")
                    .forGetter { obj -> Optional.ofNullable(obj.lightLevelOverride) },
            ).apply(it) { state, light -> BlockHeadModel(state, light.getOrNull()) }
        }
    }
}

