package org.teamvoided.all_the_heads.client.init

import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry
import net.minecraft.client.model.geom.ModelLayerLocation
import org.teamvoided.all_the_heads.AllTheHeads
import org.teamvoided.all_the_heads.client.model.AllayHeadModel
import org.teamvoided.all_the_heads.client.model.PhantomHeadModel
import org.teamvoided.all_the_heads.client.model.TurtleHeadModel
import org.teamvoided.all_the_heads.client.model.WardenHeadModel

object ATHModelLayers {
    val ALLAY_HEAD = layer("allay_head", "main")
    val TURTLE_HEAD = layer("turtle_head", "main")
    val PHANTOM_HEAD = layer("phantom_head", "main")
    val WARDEN_HEAD = layer("warden_head", "main")
    val BOGGED_HEAD_OVERLAY = layer("bogged_head", "overlay")

    fun init() {
        register(ALLAY_HEAD, AllayHeadModel::head)
        register(TURTLE_HEAD, TurtleHeadModel::head)
        register(PHANTOM_HEAD, PhantomHeadModel::head)
        register(WARDEN_HEAD, WardenHeadModel::head)


    }

    private fun register(layer: ModelLayerLocation, provider: EntityModelLayerRegistry.TexturedModelDataProvider) =
        EntityModelLayerRegistry.registerModelLayer(layer, provider)

    private fun layer(id: String, layer: String): ModelLayerLocation = ModelLayerLocation(AllTheHeads.id(id), layer)

}