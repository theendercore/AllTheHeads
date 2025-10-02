package org.teamvoided.all_the_heads.client.init

import com.mojang.serialization.MapCodec
import net.minecraft.client.renderer.RenderType
import net.minecraft.client.renderer.RenderType.*
import net.minecraft.core.Registry
import net.minecraft.resources.ResourceLocation
import org.teamvoided.all_the_heads.AllTheHeads.id
import org.teamvoided.all_the_heads.AllTheHeads.mc
import org.teamvoided.all_the_heads.client.data.render.type.RenderTypeProvider
import org.teamvoided.all_the_heads.client.data.render.type.RenderTypeProviderType
import org.teamvoided.all_the_heads.client.data.render.type.SimpleRenderTypeProvider
import org.teamvoided.all_the_heads.client.data.render.type.TexturedRenderTypeProvider
import org.teamvoided.all_the_heads.client.init.ATHRegistries.RENDER_TYPE_PROVIDER_TYPE

typealias RenderTypeCreator = (ResourceLocation) -> RenderType

object ATHRenderTypes {
    fun init() = Unit

    val SIMPLE = encodableType("simple", SimpleRenderTypeProvider.CODEC)
    val TEXTURED = encodableType("textured", TexturedRenderTypeProvider.CODEC)

    fun <T : RenderTypeProvider> encodableType(id: String, codec: MapCodec<T>): RenderTypeProviderType<T> =
        encodableType(id(id), codec)

    fun <T : RenderTypeProvider> encodableType(id: ResourceLocation, codec: MapCodec<T>): RenderTypeProviderType<T> =
        Registry.register(RENDER_TYPE_PROVIDER_TYPE, id, RenderTypeProviderType { codec })


    private val TYPES = mutableMapOf<ResourceLocation, RenderTypeCreator>()

    val BEACON_BEAM = addVanillaType("beacon_beam") { beaconBeam(it, false) }
    val BREEZE_WIND = addVanillaType("breeze_wind") { breezeWind(it, 1f, 1f) }
    val ENERGY_SWIRL = addVanillaType("energy_swirl") { energySwirl(it, 1f, 1f) }

    fun getTypes() = TYPES
    fun getType(id: ResourceLocation) = TYPES[id]
    fun getTypeOrDefault(id: ResourceLocation) = TYPES[id] ?: ::entityCutout
    fun addVanillaType(id: String, creator: RenderTypeCreator) = addType(mc(id), creator)
    fun addType(id: ResourceLocation, creator: RenderTypeCreator): ResourceLocation {
        TYPES[id] = creator
        return id
    }
}