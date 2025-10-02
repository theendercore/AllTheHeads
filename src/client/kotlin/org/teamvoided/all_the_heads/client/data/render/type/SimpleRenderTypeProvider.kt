package org.teamvoided.all_the_heads.client.data.render.type

import com.mojang.serialization.MapCodec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.client.renderer.RenderType
import net.minecraft.client.renderer.RenderType.*
import net.minecraft.resources.ResourceLocation
import org.teamvoided.all_the_heads.AllTheHeads.mc
import org.teamvoided.all_the_heads.client.init.ATHRenderTypes
import java.util.function.Supplier

open class SimpleRenderTypeProvider(val id: ResourceLocation) : RenderTypeProvider {
    override fun getType(): RenderTypeProviderType<*> = ATHRenderTypes.SIMPLE
    override fun renderType(): RenderType = TYPES[id]?.get() ?: lines()

    companion object {
        val TYPES = mutableMapOf<ResourceLocation, Supplier<RenderType>>()

        val ARMOR_ENTITY_GLINT = type("armor_entity_glint", ::armorEntityGlint)
        val ENTITY_GLINT = type("entity_glint", ::entityGlint)
        val END_PORTAL = type("end_portal", ::endPortal)
        val END_GATEWAY = type("end_gateway", ::endGateway)
        val LINES = type("lines", ::lines)
        val LINE_STRIP = type("line_strip", ::lineStrip)

        fun type(id: String, type: Supplier<RenderType>) = type(mc(id), type)
        fun type(id: ResourceLocation, type: Supplier<RenderType>): ResourceLocation {
            TYPES[id] = type
            return id
        }

        val CODEC: MapCodec<SimpleRenderTypeProvider> = RecordCodecBuilder.mapCodec {
            it.group(ResourceLocation.CODEC.fieldOf("id").forGetter(SimpleRenderTypeProvider::id)).apply(it) { id ->
                if (!TYPES.contains(id)) error("No such SimpleRenderType register [ $id ]")
                SimpleRenderTypeProvider(id)
            }
        }
    }
}