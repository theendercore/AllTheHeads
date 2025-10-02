package org.teamvoided.all_the_heads.client.data.render

import com.mojang.blaze3d.vertex.PoseStack
import com.mojang.serialization.Codec
import com.mojang.serialization.MapCodec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.client.renderer.texture.OverlayTexture
import net.minecraft.world.level.block.SkullBlock
import org.teamvoided.all_the_heads.client.data.render.ModelProvider.Companion.getLightOverride
import org.teamvoided.all_the_heads.client.data.render.type.RenderTypeProvider
import org.teamvoided.all_the_heads.client.init.ATHModelProviders
import org.teamvoided.all_the_heads.client.init.ModelsManager
import java.util.*
import kotlin.jvm.optionals.getOrNull

open class VanillaModelProvider(
    val skullType: SkullBlock.Type, val renderTypeProvider: RenderTypeProvider, val lightLevelOverride: Int? = null,
) : ModelProvider {
    override fun getType(): ModelProviderType<*> = ATHModelProviders.VANILLA
    override fun render(
        animationProgress: Float, yaw: Float, pitch: Float,
        matrices: PoseStack, vertexConsumers: MultiBufferSource, light: Int,
    ) {
        val model = ModelsManager.getVanillaModel(skullType)
        model.setupAnim(animationProgress, yaw, 0.0f)
        model.renderToBuffer(
            matrices,
            vertexConsumers.getBuffer(renderTypeProvider.get()),
            getLightOverride(light, lightLevelOverride),
            OverlayTexture.NO_OVERLAY
        )
    }

    companion object {
        val CODEC: MapCodec<VanillaModelProvider> = RecordCodecBuilder.mapCodec {
            it.group(
                SkullBlock.Types.CODEC.fieldOf("skull_type").forGetter(VanillaModelProvider::skullType),
                RenderTypeProvider.CODEC.fieldOf("render_type").forGetter(VanillaModelProvider::renderTypeProvider),
                Codec.intRange(1, 15).optionalFieldOf("light_level_override")
                    .forGetter { obj -> Optional.ofNullable(obj.lightLevelOverride) },
            ).apply(it) { skullType, renderType, light -> VanillaModelProvider(skullType, renderType, light.getOrNull()) }
        }
    }
}

