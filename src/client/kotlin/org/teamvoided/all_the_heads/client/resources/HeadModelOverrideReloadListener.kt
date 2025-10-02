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
import org.teamvoided.all_the_heads.client.init.ModelsManager
import org.teamvoided.all_the_heads.client.init.ModelsManager.HEAD_MODELS
import org.teamvoided.all_the_heads.client.init.ModelsManager.TEXTURE_TO_MODEL

class HeadModelOverrideReloadListener : SimpleJsonResourceReloadListener(GSON, DIRECTORY),
    IdentifiableResourceReloadListener {
    override fun getFabricId(): ResourceLocation = id(HeadModelOverride.FOLDER)

    override fun apply(
        resources: MutableMap<ResourceLocation, JsonElement>, manager: ResourceManager, profiler: ProfilerFiller,
    ) {
        ModelsManager.onReload()

        for ((id, json) in resources) {
            log.info(id.toString())
            HeadModelOverride.CODEC.parse(JsonOps.INSTANCE, json)
                .resultOrPartial { log.error("Failed to decode mob skull shader with ID {} - Error: {}", id, it) }
                .ifPresent {
                    HEAD_MODELS[id] = it.model
                    if (it.renderConditions.texture?.isNotEmpty() == true) {
                        TEXTURE_TO_MODEL[it.renderConditions.texture] = id
                    }
                }
        }
        log.info("List of all loaded data: {}", HEAD_MODELS)
    }

    companion object {
        const val DIRECTORY: String = HeadModelOverride.FOLDER
    }
}