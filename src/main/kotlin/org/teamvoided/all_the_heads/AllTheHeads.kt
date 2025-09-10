package org.teamvoided.all_the_heads

import me.fzzyhmstrs.fzzy_config.api.ConfigApi
import net.fabricmc.fabric.api.attachment.v1.AttachmentRegistry
import net.fabricmc.fabric.api.attachment.v1.AttachmentSyncPredicate
import net.fabricmc.fabric.api.attachment.v1.AttachmentType
import net.fabricmc.fabric.api.loot.v3.LootTableEvents
import net.minecraft.core.component.DataComponents
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.storage.loot.functions.CopyComponentsFunction
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.teamvoided.all_the_heads.config.AllTheHeadsConfig
import org.teamvoided.all_the_heads.utils.initDebug

@Suppress("unused")
object AllTheHeads {
    const val MODID = "all_the_heads"
    const val HEAD_ID = "$MODID:head"

    @JvmField
    val log: Logger = LoggerFactory.getLogger(AllTheHeads::class.simpleName)

    @JvmField
    var config = ConfigApi.registerAndLoadConfig(::AllTheHeadsConfig)

    fun init() {
        initDebug()
        LootTableEvents.MODIFY.register { key, tableBuilder, source, registries ->
            if (key.equals(Blocks.PLAYER_HEAD.lootTable)) tableBuilder.modifyPools {
                it.apply(
                    CopyComponentsFunction.copyComponents(CopyComponentsFunction.Source.BLOCK_ENTITY)
                        .include(DataComponents.CUSTOM_DATA)
                )
            }
        }
    }

    @JvmField
    @Suppress("UnstableApiUsage")
    val HEAD_ATTACHMENT: AttachmentType<ResourceLocation> = AttachmentRegistry.create(tryParseId(HEAD_ID)) { builder ->
        builder
            .persistent(ResourceLocation.CODEC)
            .syncWith(ResourceLocation.STREAM_CODEC, AttachmentSyncPredicate.all())
    }

    fun id(path: String): ResourceLocation = ResourceLocation.fromNamespaceAndPath(MODID, path)
    fun id(namespace: String, path: String): ResourceLocation = ResourceLocation.fromNamespaceAndPath(namespace, path)
    fun tryParseId(id: String) = ResourceLocation.tryParse(id)
}
