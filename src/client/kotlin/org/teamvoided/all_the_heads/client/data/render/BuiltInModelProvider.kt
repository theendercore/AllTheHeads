package org.teamvoided.all_the_heads.client.data.render

import com.mojang.blaze3d.vertex.PoseStack
import com.mojang.serialization.Codec
import com.mojang.serialization.MapCodec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.client.renderer.texture.OverlayTexture
import net.minecraft.resources.ResourceLocation
import org.teamvoided.all_the_heads.client.data.render.ModelProvider.Companion.getLightOverride
import org.teamvoided.all_the_heads.client.data.render.type.RenderTypeProvider
import org.teamvoided.all_the_heads.client.init.ATHModelProviders
import org.teamvoided.all_the_heads.client.init.ModelsManager
import java.util.*
import kotlin.jvm.optionals.getOrNull

open class BuiltInModelProvider(
    val modelId: ResourceLocation, val renderTypeProvider: RenderTypeProvider, val lightLevelOverride: Int? = null,
) : ModelProvider {
    override fun getType(): ModelProviderType<*> = ATHModelProviders.BUILT_IN
    override fun render(
        animationProgress: Float, yaw: Float, pitch: Float,
        matrices: PoseStack, vertexConsumers: MultiBufferSource, light: Int,
    ) {
        val model = ModelsManager.getBuiltInModel(modelId)
        model.setupAnim(animationProgress, yaw, 0.0f)
        model.renderToBuffer(
            matrices,
            vertexConsumers.getBuffer(renderTypeProvider.get()),
            getLightOverride(light, lightLevelOverride),
            OverlayTexture.NO_OVERLAY
        )
    }

    companion object {
        val CODEC: MapCodec<BuiltInModelProvider> = RecordCodecBuilder.mapCodec {
            it.group(
                ResourceLocation.CODEC.fieldOf("model_id").forGetter(BuiltInModelProvider::modelId),
                RenderTypeProvider.CODEC.fieldOf("render_type").forGetter(BuiltInModelProvider::renderTypeProvider),
                Codec.intRange(1, 15).optionalFieldOf("light_level_override")
                    .forGetter { obj -> Optional.ofNullable(obj.lightLevelOverride) },
            ).apply(it) { model, renderType, light -> BuiltInModelProvider(model, renderType, light.getOrNull()) }
        }
    }
}

