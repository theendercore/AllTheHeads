package org.teamvoided.all_the_heads.client.init

import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder.createSimple
import net.minecraft.core.MappedRegistry
import net.minecraft.core.Registry
import net.minecraft.resources.ResourceKey
import org.teamvoided.all_the_heads.AllTheHeads.id
import org.teamvoided.all_the_heads.client.data.render.HeadModelType

object ATHBuiltInRegistries {
    val HEAD_RENDER_STATE_TYPE_KEY = key<HeadModelType<*>>("head_render_state_type")
    val HEAD_RENDER_STATE_TYPE: MappedRegistry<HeadModelType<*>> =
        createSimple(HEAD_RENDER_STATE_TYPE_KEY).buildAndRegister()

    fun init() = Unit
    private fun <T> key(id: String): ResourceKey<Registry<T>> = ResourceKey.createRegistryKey(id(id))
}