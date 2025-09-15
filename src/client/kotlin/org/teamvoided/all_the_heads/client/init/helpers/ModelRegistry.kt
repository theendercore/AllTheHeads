package org.teamvoided.all_the_heads.client.init.helpers

import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry
import net.minecraft.client.model.SkullModelBase
import net.minecraft.client.model.geom.EntityModelSet
import net.minecraft.client.model.geom.ModelLayerLocation
import net.minecraft.client.model.geom.ModelPart
import net.minecraft.resources.ResourceLocation
import org.teamvoided.all_the_heads.AllTheHeads
import org.teamvoided.all_the_heads.client.init.ModelsManager
import org.teamvoided.all_the_heads.client.model.HeadModelBase


// Layer Registry
fun mainLayer(name: String) = layer(name, "main")
fun layer(id: String, layer: String): ModelLayerLocation = ModelLayerLocation(AllTheHeads.id(id), layer)

fun registerLayer(layer: ModelLayerLocation, provider: EntityModelLayerRegistry.TexturedModelDataProvider) =
    EntityModelLayerRegistry.registerModelLayer(layer, provider)

// Model Registry
private val MODELS_TO_LOAD = mutableMapOf<ResourceLocation, (EntityModelSet) -> SkullModelBase>()
internal fun loadModels(modelSet: EntityModelSet) {
    for ((id, model) in MODELS_TO_LOAD) {
        ModelsManager.BUILT_IN_MODELS[id] = model(modelSet)
    }
}

fun registerModel(id: ResourceLocation, loader: (EntityModelSet) -> SkullModelBase): ResourceLocation {
    MODELS_TO_LOAD[id] = loader
    return id
}


fun register(
    id: ResourceLocation, layer: ModelLayerLocation,
    layerProvider: EntityModelLayerRegistry.TexturedModelDataProvider, headCreator: (ModelPart) -> HeadModelBase,
) {
    registerLayer(layer, layerProvider)
    registerModel(id) { headCreator(it.bakeLayer(layer)) }
}



