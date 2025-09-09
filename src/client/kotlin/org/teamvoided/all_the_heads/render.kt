package org.teamvoided.all_the_heads

import com.mojang.blaze3d.vertex.PoseStack
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.Font
import net.minecraft.client.model.SkullModelBase
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.client.renderer.RenderType
import net.minecraft.client.renderer.texture.OverlayTexture
import net.minecraft.core.Direction
import net.minecraft.network.chat.Component
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.level.block.SkullBlock
import net.minecraft.world.level.block.entity.SkullBlockEntity

@JvmField
var models: MutableMap<SkullBlock.Type, SkullModelBase> = mutableMapOf()

@Suppress("DEPRECATION")
fun renderSkull(
    direction: Direction?,
    yaw: Float,
    animationProgress: Float,
    matrices: PoseStack,
    vertexConsumers: MultiBufferSource,
    light: Int,
    id: ResourceLocation?,
    be: SkullBlockEntity?,
): Boolean {
    if (id == null) return true
    if (be != null) renderDebugText(matrices, vertexConsumers, be, id)
    val data = fetchSkullData(id) ?: return true

    matrices.pushPose()
    if (direction == null) matrices.translate(0.5f, 0.0f, 0.5f)
    else {
        val f = 0.25f
        matrices.translate(0.5f - direction.stepX * f, f, 0.5f - direction.stepZ * f)
    }

    matrices.scale(-1.0f, -1.0f, 1.0f)
    val customModel = models[SkullBlock.Types.DRAGON]
    customModel?.setupAnim(animationProgress, yaw, 0.0f)
    val renderLayer =
        RenderType.entityCutoutNoCullZOffset(ResourceLocation.tryParse("textures/entity/enderdragon/dragon.png")!!)
    customModel?.renderToBuffer(
        matrices,
        vertexConsumers.getBuffer(renderLayer),
        light,
        OverlayTexture.NO_OVERLAY
    )
    matrices.popPose()
    return false
}

fun renderDebugText(
    matrices: PoseStack,
    vertexConsumers: MultiBufferSource,
    be: SkullBlockEntity,
    id: ResourceLocation?,
) {
    val font = Minecraft.getInstance().font
    val textList = mutableListOf(
        "Skull",
        id.toString(),
    )

    matrices.pushPose()
    matrices.translate(.5f, 1.3f, .5f)
    matrices.mulPose(Minecraft.getInstance().entityRenderDispatcher.cameraOrientation())
    matrices.scale(0.025f, -0.025f, 0.025f)


    val color = 0xff_ff_ff_ff.toInt()
    for ((idx, rawText) in textList.reversed().withIndex()) {
        val text = Component.literal(rawText)
//            .append(Text.literal("${rawText.second}").formatted(Formatting.GREEN))
        font.drawInBatch(
            text, font.width(text) / -2f, idx * -(1f + font.lineHeight), color,
            true, matrices.last().pose(), vertexConsumers,
            Font.DisplayMode.NORMAL, 0, 15728880
        )
    }
    matrices.popPose()

}

fun fetchSkullData(id: ResourceLocation): ResourceLocation? = id
