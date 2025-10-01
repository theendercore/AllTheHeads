package org.teamvoided.all_the_heads.client.data.gen

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.minecraft.core.HolderLookup
import org.teamvoided.all_the_heads.client.data.gen.prov.ATHOverrideProvider
import java.util.concurrent.CompletableFuture

typealias Output = FabricDataOutput
typealias FutureLookup = CompletableFuture<HolderLookup.Provider>

@Suppress("unused")
object AllTheHeadsData : DataGeneratorEntrypoint {
    override fun onInitializeDataGenerator(gen: FabricDataGenerator) {
        val pack = gen.createPack()
        pack.addProvider(::ATHOverrideProvider)
    }
}