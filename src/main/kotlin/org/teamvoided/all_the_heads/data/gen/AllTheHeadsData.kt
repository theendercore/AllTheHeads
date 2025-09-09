package org.teamvoided.all_the_heads.data.gen

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator
import net.minecraft.core.RegistrySetBuilder
import org.teamvoided.all_the_heads.AllTheHeads.log

@Suppress("unused")
object AllTheHeadsData : DataGeneratorEntrypoint {
    override fun onInitializeDataGenerator(gen: FabricDataGenerator) {
        log.info("Hello from DataGen")
        val pack = gen.createPack()

//        pack.addProvider(::TemplateWorldGenerator)
    }

    override fun buildRegistry(registryBuilder: RegistrySetBuilder) {
//        gen.add(RegistryKeys.BIOME, TemplateBiomes::boostrap)
    }
}
