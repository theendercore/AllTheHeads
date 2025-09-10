package org.teamvoided.all_the_heads.client.rendering

import com.mojang.authlib.GameProfile
import com.mojang.blaze3d.vertex.PoseStack
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.Font
import net.minecraft.client.gui.screens.Screen
import net.minecraft.client.model.SkullModelBase
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.core.Direction
import net.minecraft.network.chat.Component
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.component.ResolvableProfile
import net.minecraft.world.level.block.SkullBlock
import org.teamvoided.all_the_heads.client.AllTheHeadsClient.clientConfig
import org.teamvoided.all_the_heads.client.data.RenderLocation
import org.teamvoided.all_the_heads.client.data.SkullRenderContext

@JvmField
var models: MutableMap<SkullBlock.Type, SkullModelBase> = mutableMapOf()

@Suppress("unused")
fun debugRenderer(
    direction: Direction?,
    yaw: Float,
    animationProgress: Float,
    matrices: PoseStack,
    vertexConsumers: MultiBufferSource,
    light: Int,
    ctx: SkullRenderContext,
) {
    if (!clientConfig.enableDebugRendering) return

    val font = Minecraft.getInstance().font
    val textList = mutableListOf(
        "Skull",
        ctx.skullId?.toString() ?: "[No Id]",
        ctx.skullOwner?.readableString() ?: "[No Owner]",
        ctx.renderLocation.toString(),
    )
    ctx.itemCtx?.let { textList.add("ItemCtx: [ $it ]") }

    matrices.pushPose()
    val yOffset = if (ctx.renderLocation == RenderLocation.ON_HEAD) 0.8f else 1.3f
    matrices.translate(.5f, yOffset, .5f)
    if (ctx.renderLocation == RenderLocation.IN_WORLD) {
        matrices.mulPose(Minecraft.getInstance().entityRenderDispatcher.cameraOrientation())
    }
    matrices.scale(0.025f, -0.025f, 0.025f)

    val color = 0xff_ff_ff_ff.toInt()
    for ((idx, rawText) in textList.reversed().withIndex()) {
        val text = Component.literal(rawText)
//            .append(Text.literal("${rawText.second}").formatted(Formatting.GREEN))
        font.drawInBatch(
            text, font.width(text) / -2f, idx * -(1f + font.lineHeight), color,
            true, matrices.last().pose(), vertexConsumers,
            Font.DisplayMode.POLYGON_OFFSET, 0, 15728880
        )
    }
    matrices.popPose()

}

fun ResolvableProfile.readableString(): String = buildString {
    append("[")
    if (name.isPresent) append("Name: ${name.get()}, ")
    if (Screen.hasAltDown() && id.isPresent) append("Id: ${id.get()}, ")
    append("GameProfile: ${gameProfile.readableString()}")
    append("]")
}

fun GameProfile.readableString(): String = buildString {
    append("[")
    append("Name: $name")
    if (Screen.hasAltDown()) append(", Id: $id")
    append("]")
}


fun fetchSkullData(id: ResourceLocation): ResourceLocation? = id
