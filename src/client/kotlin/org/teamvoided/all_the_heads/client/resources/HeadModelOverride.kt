package org.teamvoided.all_the_heads.client.resources

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.resources.ResourceLocation
import org.teamvoided.all_the_heads.client.data.render.HeadModel

class HeadModelOverride(val check: ResourceLocation, val model: HeadModel) {
    companion object {
        @JvmField
        val CODEC: Codec<HeadModelOverride> = RecordCodecBuilder.create<HeadModelOverride> {
            it.group(
                ResourceLocation.CODEC.fieldOf("check").forGetter(HeadModelOverride::check),
                HeadModel.CODEC.fieldOf("model").forGetter(HeadModelOverride::model)
            ).apply(it, ::HeadModelOverride)
        }

        const val FOLDER = "head_model_override"
    }
}