package org.teamvoided.all_the_heads.client.resources

import com.google.gson.JsonElement
import com.mojang.serialization.JsonOps
import net.fabricmc.fabric.api.resource.IdentifiableResourceReloadListener
import net.minecraft.resources.ResourceLocation
import net.minecraft.server.packs.resources.ResourceManager
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener
import net.minecraft.util.profiling.ProfilerFiller
import org.teamvoided.all_the_heads.AllTheHeads.GSON
import org.teamvoided.all_the_heads.AllTheHeads.id
import org.teamvoided.all_the_heads.AllTheHeads.log
import org.teamvoided.all_the_heads.client.data.SkullRenderData
import org.teamvoided.all_the_heads.client.init.ATHRenderTypes.getTypes
import org.teamvoided.all_the_heads.client.init.ModelsManager
import org.teamvoided.all_the_heads.client.init.ModelsManager.getBuiltIn

class HeadModelOverrideReloadListener : SimpleJsonResourceReloadListener(GSON, DIRECTORY),
    IdentifiableResourceReloadListener {
    override fun getFabricId(): ResourceLocation = id(HeadModelOverride.FOLDER)

    override fun apply(
        resources: MutableMap<ResourceLocation, JsonElement>, manager: ResourceManager, profiler: ProfilerFiller,
    ) {
        HEAD_OVERRIDES.clear()

        for ((id, json) in resources) {
            log.info(id.toString())
            HeadModelOverride.CODEC.parse(JsonOps.INSTANCE, json)
                .resultOrPartial { log.error("Failed to decode mob skull shader with ID {} - Error: {}", id, it) }
                .ifPresent {
                    val value = load(id, it)
                    if (value != null) HEAD_OVERRIDES[id] = value
                }
        }
        log.info("List of all loaded data: {}", HEAD_OVERRIDES)
    }

    fun load(id: ResourceLocation, data: HeadModelOverride): SkullRenderData? {
        val modelId = data.modelId
        if (!ModelsManager.BUILT_IN_MODELS.contains(modelId)) {
            log.error("No such model [ {} ] for Head Override [ {} ]!", modelId, id)
            return null
        }
        val renderTypeFn = getTypes().getOrDefault(data.renderType, null)
        if (renderTypeFn == null) {
            log.error("No such render type [ {} ] for Head Override [ {} ]!", data.renderType, id)
            return null
        }

        return SkullRenderData(getBuiltIn(modelId), renderTypeFn(data.texture), data.lightLevelOverride)
    }


    companion object {
        const val DIRECTORY: String = HeadModelOverride.FOLDER
        val HEAD_OVERRIDES = HashMap<ResourceLocation, SkullRenderData>()
    }
}