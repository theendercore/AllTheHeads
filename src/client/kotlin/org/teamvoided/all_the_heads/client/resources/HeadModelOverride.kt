package org.teamvoided.all_the_heads.client.resources

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import org.teamvoided.all_the_heads.client.data.render.HeadModel
import java.util.*
import kotlin.jvm.optionals.getOrNull

class HeadModelOverride(val renderConditions: RenderConditions, val model: HeadModel) {
    constructor(texture: String?, model: HeadModel) : this(RenderConditions(texture), model)

    class RenderConditions(val texture: String?) {
        companion object {
            val CODEC: Codec<RenderConditions> = RecordCodecBuilder.create {
                it.group(
                    Codec.STRING.optionalFieldOf("texture").forGetter { obj -> Optional.ofNullable(obj.texture) },
                ).apply(it) { texture -> RenderConditions(texture.getOrNull()) }
            }
        }
    }

    companion object {
        val CODEC: Codec<HeadModelOverride> = RecordCodecBuilder.create {
            it.group(
                RenderConditions.CODEC.fieldOf("render_conditions").forGetter(HeadModelOverride::renderConditions),
                HeadModel.CODEC.fieldOf("model").forGetter(HeadModelOverride::model)
            ).apply(it, ::HeadModelOverride)
        }

        const val FOLDER = "head_model_override"
    }
}