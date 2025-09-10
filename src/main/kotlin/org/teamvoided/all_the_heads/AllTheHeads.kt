package org.teamvoided.all_the_heads

import me.fzzyhmstrs.fzzy_config.api.ConfigApi
import net.fabricmc.fabric.api.attachment.v1.AttachmentRegistry
import net.fabricmc.fabric.api.attachment.v1.AttachmentSyncPredicate
import net.fabricmc.fabric.api.attachment.v1.AttachmentType
import net.fabricmc.fabric.api.event.player.UseBlockCallback
import net.fabricmc.fabric.api.loot.v3.LootTableEvents
import net.fabricmc.loader.api.FabricLoader
import net.minecraft.core.component.DataComponents
import net.minecraft.network.chat.Component
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.player.Player
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.entity.SkullBlockEntity
import net.minecraft.world.level.storage.loot.functions.CopyComponentsFunction
import net.minecraft.world.phys.BlockHitResult

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.teamvoided.all_the_heads.config.AllTheHeadsConfig

@Suppress("unused")
object AllTheHeads {
    const val MODID = "all_the_heads"

    @JvmField
    val log: Logger = LoggerFactory.getLogger(AllTheHeads::class.simpleName)

    @JvmField
    var config = ConfigApi.registerAndLoadConfig(::AllTheHeadsConfig)

    fun init() {
        log.info("Hello from Common")

        UseBlockCallback.EVENT.register { player, world, hand, hitResult ->
            if (debugUse(player, world, hand, hitResult)) InteractionResult.SUCCESS
            else InteractionResult.PASS
        }

        LootTableEvents.MODIFY.register { key, tableBuilder, source, registries ->
            if (key.equals(Blocks.PLAYER_HEAD.lootTable)) tableBuilder.modifyPools {
                it.apply(
                    CopyComponentsFunction.copyComponents(CopyComponentsFunction.Source.BLOCK_ENTITY)
                        .include(DataComponents.CUSTOM_DATA)
                )
            }
        }
    }

    private fun debugUse(
        player: Player,
        world: Level,
        hand: InteractionHand,
        hitResult: BlockHitResult?,
    ): Boolean {
        val stack = player.mainHandItem
        if (!stack.isEmpty) return false
        if (hitResult == null) return false
        val pos = hitResult.blockPos

//        if (world.getBlockState(pos).isIn(BlockTags.OVERWORLD_CARVER_REPLACEABLES)){
//        }

        val be = world.getBlockEntity(pos)
        if (be !is SkullBlockEntity) return false

        if (world.isClientSide) {
            val ticks = be.getAnimation(1f)
            player.sendSystemMessage(Component.literal("Ticks: $ticks"))
        } else {
            val data = be.getAttached(HEAD_DATA)
            player.sendSystemMessage(Component.literal(data.toString()))
        }

        return true
    }

    @JvmField
    val HEAD_ID = id("head")

    @JvmField
    @Suppress("UnstableApiUsage")
    val HEAD_DATA: AttachmentType<ResourceLocation> = AttachmentRegistry.create(HEAD_ID) { builder ->
        builder
            .persistent(ResourceLocation.CODEC)
            .syncWith(ResourceLocation.STREAM_CODEC, AttachmentSyncPredicate.all())
    }

    fun id(path: String): ResourceLocation = ResourceLocation.fromNamespaceAndPath(MODID, path)


    fun isDev() = FabricLoader.getInstance().isDevelopmentEnvironment
}
