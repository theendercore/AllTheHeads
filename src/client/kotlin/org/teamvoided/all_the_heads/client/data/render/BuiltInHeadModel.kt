package org.teamvoided.all_the_heads.client.data.render

import com.mojang.blaze3d.vertex.PoseStack
import com.mojang.serialization.Codec
import com.mojang.serialization.MapCodec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.client.model.SkullModelBase
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.client.renderer.RenderType
import net.minecraft.client.renderer.texture.OverlayTexture
import net.minecraft.resources.ResourceLocation
import org.teamvoided.all_the_heads.AllTheHeads.mc
import org.teamvoided.all_the_heads.client.data.render.HeadModel.Companion.getLightOverride
import org.teamvoided.all_the_heads.client.data.render.type.TexturedRenderType
import org.teamvoided.all_the_heads.client.init.ATHHeadModels
import org.teamvoided.all_the_heads.client.init.ModelsManager.getBuiltIn
import org.teamvoided.all_the_heads.client.model.AllayHeadModel
import java.util.*
import kotlin.jvm.optionals.getOrNull

open class BuiltInHeadModel(
    val model: () -> SkullModelBase, val renderType: RenderType, val lightLevelOverride: Int? = null,
) : HeadModel {
    override fun getType(): HeadModelType<*> = ATHHeadModels.BUILT_IN
    override fun render(
        animationProgress: Float, yaw: Float, pitch: Float,
        matrices: PoseStack,
        vertexConsumers: MultiBufferSource,
        light: Int,
    ) {
        val model = model()
        model.setupAnim(animationProgress, yaw, 0.0f)
        model.renderToBuffer(
            matrices,
            vertexConsumers.getBuffer(renderType),
            getLightOverride(light, lightLevelOverride),
            OverlayTexture.NO_OVERLAY
        )
    }

    companion object {
        val CODEC: MapCodec<BuiltInHeadModel> = RecordCodecBuilder.mapCodec {
            it.group(
                ResourceLocation.CODEC.fieldOf("model").forGetter { AllayHeadModel.ID },
                ResourceLocation.CODEC.fieldOf("render_type").forGetter { TexturedRenderType.ENTITY_CUTOUT },
                Codec.intRange(1, 15).optionalFieldOf("light_level_override")
                    .forGetter { obj -> Optional.ofNullable(obj.lightLevelOverride) },
            ).apply(it) { model, renderType, light ->
                BuiltInHeadModel(
                    getBuiltIn(model),
                    TexturedRenderType.TYPES[renderType]?.invoke(mc("textures/entity/allay/allay.png"))
                        ?: error("No such render type [ $renderType ]"),
                    light.getOrNull()
                )
            }
        }
    }
}

