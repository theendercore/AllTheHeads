package org.teamvoided.all_the_heads.client.data.render

import com.mojang.blaze3d.vertex.PoseStack
import com.mojang.serialization.MapCodec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.client.renderer.MultiBufferSource
import org.teamvoided.all_the_heads.client.init.ATHModelProviders

open class ListModelProvider(val modelProviders: List<ModelProvider>) : ModelProvider {
    constructor(vararg model: ModelProvider) : this(model.toList())

    override fun getType(): ModelProviderType<*> = ATHModelProviders.LIST
    override fun render(
        animationProgress: Float, yaw: Float, pitch: Float,
        matrices: PoseStack, vertexConsumers: MultiBufferSource, light: Int,
    ) {
        for (model in modelProviders) {
            model.render(animationProgress, yaw, 0.0f, matrices, vertexConsumers, light)
        }
    }

    companion object {
        val CODEC: MapCodec<ListModelProvider> = RecordCodecBuilder.mapCodec {
            it.group(
                ModelProvider.CODEC.listOf().fieldOf("model_providers").forGetter(ListModelProvider::modelProviders)
            ).apply(it, ::ListModelProvider)
        }
    }
}