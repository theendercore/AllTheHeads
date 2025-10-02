package org.teamvoided.all_the_heads.client.data.render.type

import com.mojang.serialization.MapCodec

fun interface RenderTypeProviderType<T : RenderTypeProvider> {
    fun codec(): MapCodec<T>
}