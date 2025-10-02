package org.teamvoided.all_the_heads.client.init

import com.mojang.serialization.MapCodec
import net.minecraft.core.Registry
import net.minecraft.resources.ResourceLocation
import org.teamvoided.all_the_heads.AllTheHeads.id
import org.teamvoided.all_the_heads.client.data.render.*

object ATHModelProviders {
    fun init() = Unit
    val BUILT_IN = register("built_in", BuiltInModelProvider.CODEC)
    val VANILLA = register("vanilla", VanillaModelProvider.CODEC)
    val BLOCK = register("block", BlockModelProvider.CODEC)
    val LIST = register("list", ListModelProvider.CODEC)

    fun <T : ModelProvider> register(id: String, codec: MapCodec<T>): ModelProviderType<T> = register(id(id), codec)
    fun <T : ModelProvider> register(id: ResourceLocation, codec: MapCodec<T>): ModelProviderType<T> =
        Registry.register(ATHBuiltInRegistries.MODEL_PROVIDER_TYPE, id, ModelProviderType { codec })
}