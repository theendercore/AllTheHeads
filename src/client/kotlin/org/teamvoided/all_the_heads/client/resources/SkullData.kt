package org.teamvoided.all_the_heads.client.resources

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.resources.ResourceLocation

class SkullData(val id: ResourceLocation) {
    companion object {
        @JvmField
        val CODEC: Codec<SkullData> = RecordCodecBuilder.create<SkullData> {
            it.group(
                ResourceLocation.CODEC.fieldOf("id").forGetter(SkullData::id),
            ).apply(it, ::SkullData)
        }
    }
}