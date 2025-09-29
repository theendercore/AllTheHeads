package org.teamvoided.all_the_heads.client.data.gen

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator
import net.minecraft.core.RegistrySetBuilder
import org.teamvoided.all_the_heads.AllTheHeads.log

@Suppress("unused")
object AllTheHeadsData : DataGeneratorEntrypoint {
    override fun onInitializeDataGenerator(gen: FabricDataGenerator) {
        log.info("GAY!")
        val pack = gen.createPack()

//        pack.addProvider(::TemplateWorldGenerator)
        // Steel FabricLanguageProvider to do custom datagen
    }

    override fun buildRegistry(registryBuilder: RegistrySetBuilder) {
//        gen.add(RegistryKeys.BIOME, TemplateBiomes::boostrap)
    }
}