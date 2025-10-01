package org.teamvoided.all_the_heads.client.data.gen

import net.minecraft.resources.ResourceLocation

interface CreationContext<T> {
    fun create(id: ResourceLocation, obj: T): ResourceLocation
}