package org.teamvoided.all_the_heads.client.init

import net.minecraft.client.model.SkullModelBase
import net.minecraft.client.renderer.RenderType
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.level.block.SkullBlock
import org.teamvoided.all_the_heads.AllTheHeads
import org.teamvoided.all_the_heads.client.AllTheHeadsClient
import org.teamvoided.all_the_heads.client.data.SkullRenderData
import org.teamvoided.all_the_heads.client.data.VTTextures
import org.teamvoided.all_the_heads.client.model.AllayHeadModel
import org.teamvoided.all_the_heads.client.model.PhantomHeadModel
import org.teamvoided.all_the_heads.client.model.TurtleHeadModel
import org.teamvoided.all_the_heads.client.model.WardenHeadModel

object ModelsManager {
    @JvmField
    var VANILLA_MODEL_ACCESS = mapOf<SkullBlock.Type, SkullModelBase>()

    @JvmField
    var BUILT_IN_MODELS = mutableMapOf<ResourceLocation, SkullModelBase>()

    val models = mapOf(
        VTTextures.ALLAY to builtIn(AllayHeadModel.ID, "allay/allay"),
        VTTextures.VEX to builtIn(AllayHeadModel.ID, "illager/vex"),
        VTTextures.VEX_CHARGING to builtIn(AllayHeadModel.ID, "illager/vex_charging"),
        VTTextures.PHANTOM to builtIn(PhantomHeadModel.ID, "phantom"),
        VTTextures.TURTLE to builtIn(TurtleHeadModel.ID, "turtle/big_sea_turtle"),
        VTTextures.WARDEN to builtIn(WardenHeadModel.ID, "warden/warden"),
        VTTextures.ZOMBIFIED_PIGLIN to vanilla(SkullBlock.Types.PIGLIN, "piglin/zombified_piglin"),
    )

    fun vanilla(id: SkullBlock.Type, texture: String) = SkullRenderData(getVanilla(id), entityType(texture))
    fun getVanilla(type: SkullBlock.Type): () -> SkullModelBase {
        if (type !is SkullBlock.Types) {
            AllTheHeadsClient.sendError("Supplied non vanilla SkullType! $type")
            return { VANILLA_MODEL_ACCESS[SkullBlock.Types.PLAYER]!! }
        }
        return { VANILLA_MODEL_ACCESS[type]!! }
    }

    fun builtIn(id: ResourceLocation, texture: String) = SkullRenderData(getBuiltIn(id), entityType(texture))
    fun getBuiltIn(id: ResourceLocation): () -> SkullModelBase = {
        val model = BUILT_IN_MODELS[id]
        if (model != null) model
        else {
            AllTheHeadsClient.sendError("Failed to load model for Id! $id")
            VANILLA_MODEL_ACCESS[SkullBlock.Types.PLAYER]!!
        }
    }

    fun basicType(texture: String): RenderType = RenderType.entityCutoutNoCullZOffset(AllTheHeads.tryParseId(texture)!!)
    fun entityType(texture: String): RenderType = basicType("textures/entity/${texture}.png")
}