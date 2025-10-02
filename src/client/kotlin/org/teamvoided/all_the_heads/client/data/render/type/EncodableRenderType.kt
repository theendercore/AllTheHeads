package org.teamvoided.all_the_heads.client.data.render.type

import com.mojang.serialization.Codec
import com.mojang.serialization.MapCodec
import net.minecraft.client.renderer.RenderType
import org.teamvoided.all_the_heads.client.init.ATHBuiltInRegistries.DATA_RENDER_TYPE

interface EncodableRenderType {
    fun getType(): Type<*>
    fun renderType(): RenderType

    fun interface Type<T : EncodableRenderType> {
        fun codec(): MapCodec<T>
    }

    companion object {
        val CODEC: Codec<EncodableRenderType> =
            DATA_RENDER_TYPE.byNameCodec().dispatch(EncodableRenderType::getType, Type<*>::codec)
    }
}
