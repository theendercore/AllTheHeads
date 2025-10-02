package org.teamvoided.all_the_heads.client.data.render.type

import com.mojang.serialization.MapCodec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.client.renderer.RenderType
import net.minecraft.client.renderer.RenderType.*
import net.minecraft.resources.ResourceLocation
import org.teamvoided.all_the_heads.AllTheHeads.mc
import org.teamvoided.all_the_heads.client.init.ATHRenderTypes
import org.teamvoided.all_the_heads.client.init.RenderTypeCreator

open class TexturedRenderType(val id: ResourceLocation, val texture: ResourceLocation) : EncodableRenderType {
    override fun getType(): EncodableRenderType.Type<*> = ATHRenderTypes.TEXTURED
    override fun renderType(): RenderType = TYPES[id]?.invoke(texture.withSuffix(".png")) ?: lines()

    companion object {
        val TYPES = mutableMapOf<ResourceLocation, RenderTypeCreator>()

        val ENTITY_SOLID = type("entity_solid", ::entitySolid)
        val ENTITY_CUTOUT = type("entity_cutout", ::entityCutout)
        val ENTITY_CUTOUT_NO_CULL = type("entity_cutout_no_cull", ::entityCutoutNoCull)
        val ENTITY_CUTOUT_NO_CULL_Z_OFFSET = type("entity_cutout_no_cull_z_offset", ::entityCutoutNoCullZOffset)
        val ITEM_ENTITY_TRANSLUCENT_CULL = type("item_entity_translucent_cull", ::itemEntityTranslucentCull)
        val ENTITY_TRANSLUCENT_CULL = type("entity_translucent_cull", ::entityTranslucentCull)
        val ENTITY_TRANSLUCENT = type("entity_translucent", ::entityTranslucent)
        val ENTITY_TRANSLUCENT_EMISSIVE = type("entity_translucent_emissive", ::entityTranslucentEmissive)
        val ENTITY_SMOOTH_CUTOUT = type("entity_smooth_cutout", ::entitySmoothCutout)
        val ENTITY_DECAL = type("entity_decal", ::entityDecal)
        val ENTITY_SHADOW = type("entity_shadow", ::entityShadow)
        val EYES = type("eyes", ::eyes)
        val BREEZE_EYES = type("breeze_eyes", ::breezeEyes)

        fun type(id: String, type: RenderTypeCreator) = type(mc(id), type)
        fun type(id: ResourceLocation, type: RenderTypeCreator): ResourceLocation {
            TYPES[id] = type
            return id
        }

        val CODEC: MapCodec<TexturedRenderType> = RecordCodecBuilder.mapCodec {
            it.group(
                ResourceLocation.CODEC.fieldOf("id").forGetter(TexturedRenderType::id),
                ResourceLocation.CODEC.fieldOf("texture").forGetter(TexturedRenderType::texture)
            ).apply(it) { id, texture ->
                if (!TYPES.contains(id)) error("No such TexturedRenderType register [ $id ]")
                TexturedRenderType(id, texture)
            }
        }
    }
}