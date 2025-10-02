package org.teamvoided.all_the_heads.client.init

import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder.createSimple
import net.minecraft.core.MappedRegistry
import net.minecraft.core.Registry
import net.minecraft.resources.ResourceKey
import org.teamvoided.all_the_heads.AllTheHeads.id
import org.teamvoided.all_the_heads.client.data.render.ModelProviderType
import org.teamvoided.all_the_heads.client.data.render.type.RenderTypeProviderType

object ATHRegistries {
    val MODEL_PROVIDER_TYPE_KEY = key<ModelProviderType<*>>("model_provider_type")
    val MODEL_PROVIDER_TYPE: MappedRegistry<ModelProviderType<*>> = create(MODEL_PROVIDER_TYPE_KEY)

    val RENDER_TYPE_PROVIDER_TYPE_KEY = key<RenderTypeProviderType<*>>("render_type_provider_type")
    val RENDER_TYPE_PROVIDER_TYPE: MappedRegistry<RenderTypeProviderType<*>> = create(RENDER_TYPE_PROVIDER_TYPE_KEY)

    fun init() = Unit
    private fun <T> key(id: String): ResourceKey<Registry<T>> = ResourceKey.createRegistryKey(id(id))
    fun <T> create(key: ResourceKey<Registry<T>>): MappedRegistry<T> = createSimple(key).buildAndRegister()
}