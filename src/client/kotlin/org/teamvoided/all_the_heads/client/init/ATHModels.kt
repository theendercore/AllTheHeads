package org.teamvoided.all_the_heads.client.init

import net.minecraft.client.model.SkullModelBase
import net.minecraft.client.model.geom.EntityModelSet
import net.minecraft.resources.ResourceLocation
import org.teamvoided.all_the_heads.client.model.AllayHeadModel
import org.teamvoided.all_the_heads.client.model.PhantomHeadModel
import org.teamvoided.all_the_heads.client.model.TurtleHeadModel
import org.teamvoided.all_the_heads.client.model.WardenHeadModel

object ATHModels {
    private val MODELS_TO_LOAD = mutableMapOf<ResourceLocation, (EntityModelSet) -> SkullModelBase>()

    fun init() {
        register(AllayHeadModel.ID) { AllayHeadModel(it.bakeLayer(ATHModelLayers.ALLAY_HEAD)) }
        register(TurtleHeadModel.ID) { TurtleHeadModel(it.bakeLayer(ATHModelLayers.TURTLE_HEAD)) }
        register(PhantomHeadModel.ID) { PhantomHeadModel(it.bakeLayer(ATHModelLayers.PHANTOM_HEAD)) }
        register(WardenHeadModel.ID) { PhantomHeadModel(it.bakeLayer(ATHModelLayers.WARDEN_HEAD)) }
    }

    private fun register(id: ResourceLocation, loader: (EntityModelSet) -> SkullModelBase): ResourceLocation {
        MODELS_TO_LOAD[id] = loader
        return id
    }

    @JvmStatic
    internal fun loadModels(modelSet: EntityModelSet) {
        for ((id, model) in MODELS_TO_LOAD) {
            ModelsManager.BUILT_IN_MODELS[id] = model(modelSet)
        }
    }
}