package org.teamvoided.all_the_heads.client.data.gen

import net.minecraft.resources.ResourceLocation

open class CreationBuilder<T> : CreationContext<T> {
    val entries = mutableMapOf<ResourceLocation, T>()
    override fun create(id: ResourceLocation, obj: T): ResourceLocation {
        if (entries.contains(id)) {
            error("Creation Builder already contains entry [ $id ]!")
        }
        entries[id] = obj
        return id
    }
}