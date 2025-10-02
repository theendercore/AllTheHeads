package org.teamvoided.all_the_heads.client.data.render

import com.mojang.blaze3d.vertex.PoseStack
import com.mojang.serialization.Codec
import com.mojang.serialization.MapCodec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.client.model.SkullModelBase
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.client.renderer.texture.OverlayTexture
import net.minecraft.world.level.block.SkullBlock
import org.teamvoided.all_the_heads.AllTheHeads.log
import org.teamvoided.all_the_heads.client.data.render.HeadModel.Companion.getLightOverride
import org.teamvoided.all_the_heads.client.data.render.type.RenderTypeProvider
import org.teamvoided.all_the_heads.client.init.ATHHeadModels
import org.teamvoided.all_the_heads.client.init.ModelsManager.VANILLA_MODEL_ACCESS
import org.teamvoided.all_the_heads.client.init.ModelsManager.getVanilla
import java.util.*
import kotlin.jvm.optionals.getOrNull

open class VanillaHeadModel(
    val model: () -> SkullModelBase, val renderTypeProvider: RenderTypeProvider, val lightLevelOverride: Int? = null,
) : HeadModel {
    override fun getType(): HeadModelType<*> = ATHHeadModels.VANILLA
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
            vertexConsumers.getBuffer(renderTypeProvider.get()),
            getLightOverride(light, lightLevelOverride),
            OverlayTexture.NO_OVERLAY
        )
    }

    companion object {
        val CODEC: MapCodec<VanillaHeadModel> = RecordCodecBuilder.mapCodec {
            it.group(
                SkullBlock.Types.CODEC.fieldOf("type").forGetter { SkullBlock.Types.CREEPER },
                RenderTypeProvider.CODEC.fieldOf("render_type").forGetter(VanillaHeadModel::renderTypeProvider),
                Codec.intRange(1, 15).optionalFieldOf("light_level_override")
                    .forGetter { obj -> Optional.ofNullable(obj.lightLevelOverride) },
            ).apply(it) { model, renderType, light ->
                var modelId = if (VANILLA_MODEL_ACCESS.contains(model)) model else {
                    log.error("No such model [ $model ]")
                    SkullBlock.Types.CREEPER
                }
                VanillaHeadModel(getVanilla(modelId), renderType, light.getOrNull())
            }
        }
    }
}

