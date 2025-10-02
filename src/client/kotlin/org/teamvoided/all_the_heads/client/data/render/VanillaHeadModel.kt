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
import net.minecraft.world.level.block.SkullBlock
import org.teamvoided.all_the_heads.AllTheHeads.log
import org.teamvoided.all_the_heads.AllTheHeads.mc
import org.teamvoided.all_the_heads.client.data.render.HeadModel.Companion.getLightOverride
import org.teamvoided.all_the_heads.client.init.ATHHeadModels
import org.teamvoided.all_the_heads.client.init.ATHRenderTypes
import org.teamvoided.all_the_heads.client.init.ATHRenderTypes.getTypeOrDefault
import org.teamvoided.all_the_heads.client.init.ATHRenderTypes.getTypes
import org.teamvoided.all_the_heads.client.init.ModelsManager.VANILLA_MODEL_ACCESS
import org.teamvoided.all_the_heads.client.init.ModelsManager.getVanilla
import java.util.*
import kotlin.jvm.optionals.getOrNull

open class VanillaHeadModel(
    val model: () -> SkullModelBase, val renderType: RenderType, val lightLevelOverride: Int? = null,
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
            vertexConsumers.getBuffer(renderType),
            getLightOverride(light, lightLevelOverride),
            OverlayTexture.NO_OVERLAY
        )
    }

    companion object {
        val CODEC: MapCodec<VanillaHeadModel> = RecordCodecBuilder.mapCodec {
            it.group(
                SkullBlock.Types.CODEC.fieldOf("model").forGetter { SkullBlock.Types.CREEPER },
                ResourceLocation.CODEC.fieldOf("render_type").forGetter { ATHRenderTypes.ENTITY_CUTOUT },
                Codec.intRange(1, 15).optionalFieldOf("light_level_override")
                    .forGetter { obj -> Optional.ofNullable(obj.lightLevelOverride) },
            ).apply(it) { model, renderType, light ->
                var modelId = if (VANILLA_MODEL_ACCESS.contains(model)) model else {
                    log.error("No such model [ $model ]")
                    SkullBlock.Types.CREEPER
                }

                var renderTypeId = if (getTypes().contains(renderType)) renderType else {
                    log.error("No such render type [ $renderType ]")
                    ATHRenderTypes.ENTITY_CUTOUT
                }

                VanillaHeadModel(
                    getVanilla(modelId),
                    getTypeOrDefault(renderTypeId)(mc("textures/entity/allay/allay.png")),
                    light.getOrNull()
                )
            }
        }
    }
}

