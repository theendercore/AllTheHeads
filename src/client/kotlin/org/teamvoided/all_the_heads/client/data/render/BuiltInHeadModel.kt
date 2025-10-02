package org.teamvoided.all_the_heads.client.data.render

import com.mojang.blaze3d.vertex.PoseStack
import com.mojang.serialization.Codec
import com.mojang.serialization.MapCodec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.client.renderer.texture.OverlayTexture
import net.minecraft.resources.ResourceLocation
import org.teamvoided.all_the_heads.client.data.render.HeadModel.Companion.getLightOverride
import org.teamvoided.all_the_heads.client.data.render.type.RenderTypeProvider
import org.teamvoided.all_the_heads.client.init.ATHHeadModels
import org.teamvoided.all_the_heads.client.init.ModelsManager
import java.util.*
import kotlin.jvm.optionals.getOrNull

open class BuiltInHeadModel(
    val model: ResourceLocation, val renderTypeProvider: RenderTypeProvider, val lightLevelOverride: Int? = null,
) : HeadModel {
    override fun getType(): HeadModelType<*> = ATHHeadModels.BUILT_IN
    override fun render(
        animationProgress: Float, yaw: Float, pitch: Float,
        matrices: PoseStack, vertexConsumers: MultiBufferSource, light: Int,
    ) {
        val model = ModelsManager.getBuiltInModel(model)
        model.setupAnim(animationProgress, yaw, 0.0f)
        model.renderToBuffer(
            matrices,
            vertexConsumers.getBuffer(renderTypeProvider.get()),
            getLightOverride(light, lightLevelOverride),
            OverlayTexture.NO_OVERLAY
        )
    }

    companion object {
        val CODEC: MapCodec<BuiltInHeadModel> = RecordCodecBuilder.mapCodec {
            it.group(
                ResourceLocation.CODEC.fieldOf("model").forGetter(BuiltInHeadModel::model),
                RenderTypeProvider.CODEC.fieldOf("render_type").forGetter(BuiltInHeadModel::renderTypeProvider),
                Codec.intRange(1, 15).optionalFieldOf("light_level_override")
                    .forGetter { obj -> Optional.ofNullable(obj.lightLevelOverride) },
            ).apply(it) { model, renderType, light -> BuiltInHeadModel(model, renderType, light.getOrNull()) }
        }
    }
}

