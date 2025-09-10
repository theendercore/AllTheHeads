package org.teamvoided.all_the_heads.client

import com.mojang.blaze3d.vertex.PoseStack
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.Font
import net.minecraft.client.model.SkullModelBase
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.network.chat.Component
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.level.block.SkullBlock
import net.minecraft.world.level.block.entity.SkullBlockEntity

@JvmField
var models: MutableMap<SkullBlock.Type, SkullModelBase> = mutableMapOf()

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
