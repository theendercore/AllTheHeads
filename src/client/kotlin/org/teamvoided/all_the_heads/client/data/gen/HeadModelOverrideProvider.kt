package org.teamvoided.all_the_heads.client.data.gen

import net.minecraft.core.HolderLookup
import net.minecraft.data.CachedOutput
import net.minecraft.data.DataProvider
import net.minecraft.data.PackOutput
import net.minecraft.resources.ResourceLocation
import org.teamvoided.all_the_heads.client.resources.HeadModelOverride
import java.nio.file.Path
import java.util.concurrent.CompletableFuture


abstract class HeadModelOverrideProvider(val dataOutput: Output, val registryLookup: FutureLookup) : DataProvider {
    override fun getName(): String = "Skull Data Provider"
    fun getFilePath(id: ResourceLocation): Path = dataOutput
        .createPathProvider(PackOutput.Target.RESOURCE_PACK, HeadModelOverride.FOLDER)
        .json(id)

    override fun run(writer: CachedOutput): CompletableFuture<*> {
        val list = mutableListOf<CompletableFuture<*>>()
        return this.registryLookup.thenCompose { lookup ->
            val builder = CreationBuilder<HeadModelOverride>()
            generateOverrides(lookup, builder)

            builder.entries.forEach { (key, value) ->
                list.add(DataProvider.saveStable(writer, lookup, HeadModelOverride.CODEC, value, getFilePath(key)))
            }
            CompletableFuture.allOf(*list.toTypedArray())
        }

    }

    abstract fun generateOverrides(lookup: HolderLookup.Provider, dataBuilder: CreationContext<HeadModelOverride>)
}
