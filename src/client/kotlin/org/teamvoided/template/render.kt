package org.teamvoided.template

import net.minecraft.block.SkullBlock
import net.minecraft.client.render.OverlayTexture
import net.minecraft.client.render.RenderLayer
import net.minecraft.client.render.VertexConsumerProvider
import net.minecraft.client.render.block.entity.model.AbstractSkullBlockEntityModel
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.component.type.NbtComponent
import net.minecraft.nbt.NbtCompound
import net.minecraft.util.Identifier
import net.minecraft.util.math.Direction
import org.teamvoided.template.Template.id

@JvmField
var models: MutableMap<SkullBlock.SkullType, AbstractSkullBlockEntityModel> = mutableMapOf()

fun renderSkull(
    direction: Direction?,
    yaw: Float,
    animationProgress: Float,
    matrices: MatrixStack,
    vertexConsumers: VertexConsumerProvider,
    light: Int,
    model: AbstractSkullBlockEntityModel,
    renderLayer: RenderLayer,
    nbt: NbtComponent?,
) {
    matrices.push()
    if (direction == null) {
        matrices.translate(0.5f, 0.0f, 0.5f)
    } else {
        val f = 0.25f
        matrices.translate(0.5f - direction.offsetX * f, f, 0.5f - direction.offsetZ * f)
    }


    matrices.scale(-1.0f, -1.0f, 1.0f)
    val vertexConsumer = vertexConsumers.getBuffer(renderLayer)
    if (nbt != null && !nbt.isEmpty) {
        val data = nbt.nbt.getHeadData()
        if (data != null) {

            val customModel = models[SkullBlock.Type.DRAGON]
            customModel?.setHeadAngles(animationProgress, yaw, 0.0f)
            customModel?.method_60879(
                matrices,
                vertexConsumers.getBuffer(
                    RenderLayer.getEntityCutoutNoCullZOffset(
                        Identifier.ofDefault("textures/entity/enderdragon/dragon.png")
                    )
                ),
                light,
                OverlayTexture.DEFAULT_UV
            )
        }

    } else {
        model.setHeadAngles(animationProgress, yaw, 0.0f)
        model.method_60879(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV)
    }
    matrices.pop()
}

fun NbtCompound.getHeadData() = this.get(id("head").toString())
