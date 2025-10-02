package org.teamvoided.all_the_heads.client.data.render.type

import com.mojang.serialization.Codec
import net.minecraft.client.renderer.RenderType
import org.teamvoided.all_the_heads.client.init.ATHBuiltInRegistries.RENDER_TYPE_PROVIDER_TYPE

interface RenderTypeProvider {
    fun getType(): RenderTypeProviderType<*>
    fun get(): RenderType

    companion object {
        val CODEC: Codec<RenderTypeProvider> =
            RENDER_TYPE_PROVIDER_TYPE.byNameCodec().dispatch(RenderTypeProvider::getType, RenderTypeProviderType<*>::codec)
    }
}
