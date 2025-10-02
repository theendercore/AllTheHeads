package org.teamvoided.all_the_heads.client.rendering

import com.mojang.authlib.GameProfile
import com.mojang.authlib.properties.Property
import com.mojang.authlib.properties.PropertyMap
import com.mojang.blaze3d.vertex.PoseStack
import com.mojang.math.Axis
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.Font
import net.minecraft.client.gui.screens.Screen
import net.minecraft.client.model.SkullModelBase
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.core.Direction
import net.minecraft.network.chat.Component
import net.minecraft.util.Mth.DEG_TO_RAD
import net.minecraft.world.item.ItemDisplayContext
import net.minecraft.world.item.component.ResolvableProfile
import net.minecraft.world.level.block.SkullBlock
import net.minecraft.world.phys.BlockHitResult
import org.teamvoided.all_the_heads.client.AllTheHeadsClient.clientConfig
import org.teamvoided.all_the_heads.client.data.HeadRenderMode
import org.teamvoided.all_the_heads.client.data.RenderLocation
import org.teamvoided.all_the_heads.client.data.SkullRenderContext
import org.teamvoided.all_the_heads.client.data.render.HeadModel

@JvmField
var models: MutableMap<SkullBlock.Type, SkullModelBase> = mutableMapOf()

@Suppress("unused")
fun debugRenderer(
    direction: Direction?, yaw: Float, animationProgress: Float,
    matrices: PoseStack, vertexConsumers: MultiBufferSource, light: Int,
    ctx: SkullRenderContext, data: HeadModel?,
) {
    if (!clientConfig.enableDebugRendering) return
    val mc = Minecraft.getInstance()

    val font = mc.font
    if (clientConfig.noModelWarning && data == null && ctx.itemCtx == ItemDisplayContext.GUI && ctx.skullOwner != null) {
        matrices.pushPose()
        val scale = .06f
        matrices.scale(scale, -scale, scale)
        matrices.rotateAround(Axis.YN.rotation(45f * DEG_TO_RAD), 0f, 0f, 0f)
        matrices.rotateAround(Axis.XP.rotation(30f * DEG_TO_RAD), 0f, 0f, 0f)
        matrices.translate(4f, -14f, 8f)
        val text = Component.literal("❌")
        font.drawInBatch(
            text, 0f, 0f, 0xff_ff_00_00.toInt(),
            true, matrices.last().pose(), vertexConsumers,
            Font.DisplayMode.POLYGON_OFFSET, 0, 15728880
        )
        matrices.popPose()
    }

    if (clientConfig.lookAtMode && ctx.renderLocation == RenderLocation.IN_WORLD) {
        val hit = mc.hitResult
        if (hit !is BlockHitResult) return
        if (hit.blockPos != ctx.blockEntity?.blockPos) return
    }

    val textList = buildList {
        if (clientConfig.skullText) {
            add("Skull")
            if (clientConfig.headRenderMode.get() == HeadRenderMode.PROFILE)
                add(ctx.skullOwner?.readableString() ?: "[No Owner]")
            else
                add(ctx.skullId?.toString() ?: "[No Id]")

            if (Screen.hasShiftDown()) {
                add(ctx.renderLocation.toString())
                ctx.itemCtx?.let { add("ItemCtx: [ $it ]") }
            }
//            if (data != null) add(
//                "Model: ${
//                    data.model().nullCast<ATHModel>()?.getId()
//                        ?: data.model().javaClass.simpleName
//                }"
//            )
            add(buildString {
                append("Light: [Sky: ")
                append(light shr 20)
                append(", Block ")
                append(light shr 4)
                append(", Raw: ")
                append(light)
                append("]")
//                if (data?.lightLevelOverride != null) append(" Override{${data.lightLevelOverride}}")
            })

        }
    }

    matrices.pushPose()
    val yOffset = if (ctx.renderLocation == RenderLocation.ON_HEAD) 0.8f else 1.3f
    matrices.translate(.5f, yOffset, .5f)
    if (ctx.renderLocation == RenderLocation.IN_WORLD) {
        matrices.mulPose(mc.entityRenderDispatcher.cameraOrientation())
    }
    matrices.scale(0.025f, -0.025f, 0.025f)

    val color = 0xff_ff_ff_ff.toInt()
    for ((idx, rawText) in textList.reversed().withIndex()) {
        val text = Component.literal(rawText)
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
    if (Screen.hasAltDown()) {
        append("GameProfile: ${gameProfile.readableString()}")
    } else {
        append("Texture: ${properties.get("textures").first().value.substring(0, 8)}...")
    }
    append("]")
}

@Suppress("unused")
fun PropertyMap.readableString(): String = buildString {
    append("[")
    this@readableString.forEach { key, value ->
        append("$key: ${value.readableString()}")
    }
    append("]")
}

fun Property.readableString(): String = buildString {
    append("[")
    append("Name: $name")
    append("Value: ${value.substring(0, 3)}...")
    signature?.let { append("signature: ${signature?.substring(0, 3)}...") }
    append("]")
}


fun GameProfile.readableString(): String = buildString {
    append("[")
    append("Name: $name")
    append("]")
}
