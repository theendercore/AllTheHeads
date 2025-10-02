package org.teamvoided.all_the_heads.client.data.render

import com.mojang.serialization.MapCodec

fun interface HeadModelType<T : HeadModel> {
    fun codec(): MapCodec<T>
}