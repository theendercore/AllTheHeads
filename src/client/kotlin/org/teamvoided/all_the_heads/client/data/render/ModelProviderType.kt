package org.teamvoided.all_the_heads.client.data.render

import com.mojang.serialization.MapCodec

fun interface ModelProviderType<T : ModelProvider> {
    fun codec(): MapCodec<T>
}