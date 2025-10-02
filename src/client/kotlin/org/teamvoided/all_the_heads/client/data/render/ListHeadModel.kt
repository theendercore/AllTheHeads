package org.teamvoided.all_the_heads.client.data.render

import com.mojang.blaze3d.vertex.PoseStack
import com.mojang.serialization.MapCodec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.client.renderer.MultiBufferSource
import org.teamvoided.all_the_heads.client.init.ATHHeadModels

open class ListHeadModel(val models: List<HeadModel>) : HeadModel {
    constructor(vararg model: HeadModel) : this(model.toList())

    override fun getType(): HeadModelType<*> = ATHHeadModels.LIST
    override fun render(
        animationProgress: Float, yaw: Float, pitch: Float,
        matrices: PoseStack, vertexConsumers: MultiBufferSource, light: Int,
    ) {
        for (model in models) {
            model.render(animationProgress, yaw, 0.0f, matrices, vertexConsumers, light)
        }
    }

    companion object {
        val CODEC: MapCodec<ListHeadModel> = RecordCodecBuilder.mapCodec {
            it.group(
                HeadModel.CODEC.listOf().fieldOf("models").forGetter(ListHeadModel::models)
            ).apply(it, ::ListHeadModel)
        }
    }
}