package org.teamvoided.all_the_heads.client.resources

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.resources.ResourceLocation
import java.util.*
import kotlin.jvm.optionals.getOrNull

class HeadModelOverride(
    val modelId: ResourceLocation,
    val renderType: ResourceLocation,
    val texture: ResourceLocation,
    val lightLevelOverride: Int? = null,
) {
    init {
        if (lightLevelOverride != null && lightLevelOverride !in 1..15) {
            error("LightLevelOverride [ $lightLevelOverride ] is outside of the accepted range (1..15)!")
        }
    }

    companion object {
        @JvmField
        val CODEC: Codec<HeadModelOverride> = RecordCodecBuilder.create<HeadModelOverride> {
            it.group(
                ResourceLocation.CODEC.fieldOf("model_id").forGetter(HeadModelOverride::modelId),
                ResourceLocation.CODEC.fieldOf("render_type").forGetter(HeadModelOverride::renderType),
                ResourceLocation.CODEC.fieldOf("texture").forGetter(HeadModelOverride::texture),
                Codec.intRange(1, 15).optionalFieldOf("light_level_override").forGetter { obj ->
                    Optional.ofNullable(obj.lightLevelOverride)
                },
            ).apply(it) { model, type, texture, light -> HeadModelOverride(model, type, texture, light.getOrNull()) }
        }

        const val FOLDER = "head_model_override"
    }
}