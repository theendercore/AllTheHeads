package org.teamvoided.all_the_heads.client.init

import com.mojang.serialization.MapCodec
import net.minecraft.client.renderer.RenderType
import net.minecraft.client.renderer.RenderType.*
import net.minecraft.core.Registry
import net.minecraft.resources.ResourceLocation
import org.teamvoided.all_the_heads.AllTheHeads.id
import org.teamvoided.all_the_heads.AllTheHeads.mc
import org.teamvoided.all_the_heads.client.data.render.type.EncodableRenderType
import org.teamvoided.all_the_heads.client.data.render.type.SimpleRenderType
import org.teamvoided.all_the_heads.client.data.render.type.TexturedRenderType
import org.teamvoided.all_the_heads.client.init.ATHBuiltInRegistries.DATA_RENDER_TYPE

typealias RenderTypeCreator = (ResourceLocation) -> RenderType

object ATHRenderTypes {
    fun init() = Unit

    val SIMPLE = encodableType("simple", SimpleRenderType.CODEC)
    val TEXTURED = encodableType("textured", TexturedRenderType.CODEC)

    fun <T : EncodableRenderType> encodableType(id: String, codec: MapCodec<T>): EncodableRenderType.Type<T> =
        encodableType(id(id), codec)

    fun <T : EncodableRenderType> encodableType(id: ResourceLocation, codec: MapCodec<T>): EncodableRenderType.Type<T> =
        Registry.register(DATA_RENDER_TYPE, id, EncodableRenderType.Type { codec })


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