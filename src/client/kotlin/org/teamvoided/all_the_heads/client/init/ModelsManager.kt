package org.teamvoided.all_the_heads.client.init

import net.minecraft.client.model.SkullModelBase
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.level.block.SkullBlock
import org.teamvoided.all_the_heads.AllTheHeads
import org.teamvoided.all_the_heads.client.data.render.HeadModel

object ModelsManager {
    @JvmField
    var VANILLA_MODEL_ACCESS = mapOf<SkullBlock.Type, SkullModelBase>()

    @JvmField
    var BUILT_IN_MODELS = mutableMapOf<ResourceLocation, SkullModelBase>()

    fun getVanillaModel(skull: SkullBlock.Type): SkullModelBase {
        if (skull !is SkullBlock.Types) {
            AllTheHeads.sendError("Supplied non vanilla SkullType! $skull", skull)
            return VANILLA_MODEL_ACCESS[SkullBlock.Types.PLAYER]!!
        }
        return VANILLA_MODEL_ACCESS[skull]!!
    }

    fun getBuiltInModel(id: ResourceLocation): SkullModelBase {
        val model = BUILT_IN_MODELS[id]
        return if (model != null) model else {
            AllTheHeads.sendError("Failed to load model for Id! $id", id)
            VANILLA_MODEL_ACCESS[SkullBlock.Types.PLAYER]!!
        }
    }

    val TEXTURE_TO_MODEL = mutableMapOf<String, ResourceLocation>()
    val HEAD_MODELS = mutableMapOf<ResourceLocation, HeadModel>()

    fun onReload() {
        TEXTURE_TO_MODEL.clear()
        HEAD_MODELS.clear()
    }
}