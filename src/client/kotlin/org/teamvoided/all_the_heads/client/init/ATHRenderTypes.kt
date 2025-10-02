package org.teamvoided.all_the_heads.client.init

import net.minecraft.client.renderer.RenderType
import net.minecraft.client.renderer.RenderType.*
import net.minecraft.resources.ResourceLocation
import org.teamvoided.all_the_heads.AllTheHeads.mc

typealias RenderTypeCreator = (ResourceLocation) -> RenderType

@Suppress("unused")
object ATHRenderTypes {
    fun init() = Unit
    private val TYPES = mutableMapOf<ResourceLocation, RenderTypeCreator>()

    val ENTITY_SOLID = addVanillaType("entity_solid", ::entitySolid)
    val ENTITY_CUTOUT = addVanillaType("entity_cutout", ::entityCutout)
    val ENTITY_CUTOUT_NO_CULL = addVanillaType("entity_cutout_no_cull", ::entityCutoutNoCull)
    val ENTITY_CUTOUT_NO_CULL_Z_OFFSET = addVanillaType("entity_cutout_no_cull_z_offset", ::entityCutoutNoCullZOffset)
    val ITEM_ENTITY_TRANSLUCENT_CULL = addVanillaType("item_entity_translucent_cull", ::itemEntityTranslucentCull)
    val ENTITY_TRANSLUCENT_CULL = addVanillaType("entity_translucent_cull", ::entityTranslucentCull)
    val ENTITY_TRANSLUCENT = addVanillaType("entity_translucent", ::entityTranslucent)
    val ENTITY_TRANSLUCENT_EMISSIVE = addVanillaType("entity_translucent_emissive", ::entityTranslucentEmissive)
    val ENTITY_SMOOTH_CUTOUT = addVanillaType("entity_smooth_cutout", ::entitySmoothCutout)
    val BEACON_BEAM = addVanillaType("beacon_beam") { beaconBeam(it, false) }
    val ENTITY_DECAL = addVanillaType("entity_decal", ::entityDecal)
    val ENTITY_SHADOW = addVanillaType("entity_shadow", ::entityShadow)
    val EYES = addVanillaType("eyes", ::eyes)
    val BREEZE_EYES = addVanillaType("breeze_eyes", ::breezeEyes)
    val BREEZE_WIND = addVanillaType("breeze_wind") { breezeWind(it, 1f, 1f) }
    val ENERGY_SWIRL = addVanillaType("energy_swirl") { energySwirl(it, 1f, 1f) }
    val ARMOR_ENTITY_GLINT = addVanillaType("armor_entity_glint") { armorEntityGlint() }
    val ENTITY_GLINT = addVanillaType("entity_glint") { entityGlint() }
    val END_PORTAL = addVanillaType("end_portal") { endPortal() }
    val END_GATEWAY = addVanillaType("end_gateway") { endGateway() }
    val LINES = addVanillaType("lines") { lines() }
    val LINE_STRIP = addVanillaType("line_strip") { lineStrip() }


    fun getTypes() = TYPES
    fun getType(id: ResourceLocation) = TYPES[id]
    fun getTypeOrDefault(id: ResourceLocation) = TYPES[id] ?: ::entityCutout
    fun addVanillaType(id: String, creator: RenderTypeCreator) = addType(mc(id), creator)
    fun addType(id: ResourceLocation, creator: RenderTypeCreator): ResourceLocation {
        TYPES[id] = creator
        return id
    }
}