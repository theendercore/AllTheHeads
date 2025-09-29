package org.teamvoided.all_the_heads.client.resources

import com.google.gson.JsonElement
import com.mojang.serialization.JsonOps
import net.fabricmc.fabric.api.resource.IdentifiableResourceReloadListener
import net.minecraft.resources.ResourceLocation
import net.minecraft.server.packs.resources.ResourceManager
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener
import net.minecraft.util.profiling.ProfilerFiller
import org.teamvoided.all_the_heads.AllTheHeads.GSON
import org.teamvoided.all_the_heads.AllTheHeads.MODID
import org.teamvoided.all_the_heads.AllTheHeads.id
import org.teamvoided.all_the_heads.AllTheHeads.log

class SkullDataReloadListener : SimpleJsonResourceReloadListener(GSON, DIRECTORY), IdentifiableResourceReloadListener {
    override fun getFabricId(): ResourceLocation = id("skull_data")

    override fun apply(
        resources: MutableMap<ResourceLocation, JsonElement>, manager: ResourceManager, profiler: ProfilerFiller,
    ) {
        MOB_SKULL_SHADERS.clear()

        for ((id, json) in resources) {
            SkullData.CODEC.parse(JsonOps.INSTANCE, json)
                .resultOrPartial { log.error("Failed to decode mob skull shader with ID {} - Error: {}", id, it) }
                .ifPresent {
                    val value = validate(manager, id, it)
                    if (value != null) MOB_SKULL_SHADERS[id] = value
                }
        }
    }

    fun validate(manager: ResourceManager, id: ResourceLocation, data: SkullData): SkullData? {
        if (data.id.path == "invalid") {
            log.error("Data not valid!")
            return null
        }
        return data
    }


    companion object {
        const val DIRECTORY: String = "$MODID/skull_data"
        val MOB_SKULL_SHADERS: MutableMap<ResourceLocation, SkullData> = HashMap<ResourceLocation, SkullData>()

    }
}