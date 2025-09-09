package org.teamvoided.template

import net.minecraft.block.SkullBlock
import net.minecraft.block.entity.SkullBlockEntity
import net.minecraft.client.MinecraftClient
import net.minecraft.client.font.TextRenderer
import net.minecraft.client.render.OverlayTexture
import net.minecraft.client.render.RenderLayer
import net.minecraft.client.render.VertexConsumerProvider
import net.minecraft.client.render.block.entity.model.AbstractSkullBlockEntityModel
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.text.Text
import net.minecraft.util.Identifier
import net.minecraft.util.math.Direction

@JvmField
var models: MutableMap<SkullBlock.SkullType, AbstractSkullBlockEntityModel> = mutableMapOf()

@Suppress("DEPRECATION")
fun renderSkull(
    direction: Direction?,
    yaw: Float,
    animationProgress: Float,
    matrices: MatrixStack,
    vertexConsumers: VertexConsumerProvider,
    light: Int,
    id: Identifier?,
    be: SkullBlockEntity?,
): Boolean {
    if (id == null) return true
    if (be != null) renderDebugText(matrices, vertexConsumers, be, id)
    val data = fetchSkullData(id) ?: return true

    matrices.push()
    if (direction == null) {
        matrices.translate(0.5f, 0.0f, 0.5f)
    } else {
        val f = 0.25f
        matrices.translate(0.5f - direction.offsetX * f, f, 0.5f - direction.offsetZ * f)
    }

    matrices.scale(-1.0f, -1.0f, 1.0f)
    val customModel = models[SkullBlock.Type.DRAGON]
    customModel?.setHeadAngles(animationProgress, yaw, 0.0f)

    val renderLayer =
        RenderLayer.getEntityCutoutNoCullZOffset(Identifier.ofDefault("textures/entity/enderdragon/dragon.png"))
    customModel?.method_60879(
        matrices,
        vertexConsumers.getBuffer(renderLayer),
        light,
        OverlayTexture.DEFAULT_UV
    )
    matrices.pop()
    return false
}

fun renderDebugText(
    matrices: MatrixStack,
    vertexConsumers: VertexConsumerProvider,
    be: SkullBlockEntity,
    id: Identifier?,
) {
    val font = MinecraftClient.getInstance().textRenderer
    val textList = mutableListOf(
        "Skull",
        id.toString(),
    )

    matrices.push()
    matrices.translate(.5f, 1.3f, .5f)
    matrices.rotate(MinecraftClient.getInstance().entityRenderDispatcher.rotation)
    matrices.scale(0.025f, -0.025f, 0.025f)


    val color = 0xff_ff_ff_ff.toInt()
    for ((idx, rawText) in textList.reversed().withIndex()) {
        val text = Text.literal(rawText)
//            .append(Text.literal("${rawText.second}").formatted(Formatting.GREEN))
        font.draw(
            text, font.getWidth(text) / -2f, idx * -(1f + font.fontHeight), color,
            true, matrices.peek().model, vertexConsumers,
            TextRenderer.TextLayerType.NORMAL, 0, 15728880
        )
    }
    matrices.pop()

}

fun fetchSkullData(id: Identifier): Identifier? = id
