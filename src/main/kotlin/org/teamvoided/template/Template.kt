package org.teamvoided.template

import me.fzzyhmstrs.fzzy_config.api.ConfigApi
import net.fabricmc.fabric.api.attachment.v1.AttachmentRegistry
import net.fabricmc.fabric.api.attachment.v1.AttachmentSyncPredicate
import net.fabricmc.fabric.api.attachment.v1.AttachmentType
import net.fabricmc.fabric.api.event.player.UseBlockCallback
import net.fabricmc.fabric.api.loot.v3.LootTableEvents
import net.minecraft.block.Blocks
import net.minecraft.block.entity.SkullBlockEntity
import net.minecraft.component.DataComponentTypes
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.loot.function.CopyComponentsLootFunction
import net.minecraft.text.Text
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand
import net.minecraft.util.Identifier
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.world.World
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.teamvoided.template.config.TemplateConfig

@Suppress("unused")
object Template {
    const val MODID = "all_the_heads"

    @JvmField
    val log: Logger = LoggerFactory.getLogger(Template::class.simpleName)

    @JvmField
    var config = ConfigApi.registerAndLoadConfig(::TemplateConfig)

    fun init() {
        log.info("Hello from Common")

        UseBlockCallback.EVENT.register { player, world, hand, hitResult ->
            if (debugUse(player, world, hand, hitResult)) ActionResult.SUCCESS
            else ActionResult.PASS
        }

        LootTableEvents.MODIFY.register { key, tableBuilder, source, registries ->
            if (key.equals(Blocks.PLAYER_HEAD.lootTableId)) {
                tableBuilder.modifyPools {
                    it.apply(
                        CopyComponentsLootFunction.method_57637(CopyComponentsLootFunction.C_zcqyfuyv.BLOCK_ENTITY)
                            .method_58730(DataComponentTypes.CUSTOM_DATA)
                    )
                }
            }
        }
    }

    private fun debugUse(
        player: PlayerEntity,
        world: World,
        hand: Hand,
        hitResult: BlockHitResult?,
    ): Boolean {
        val stack = player.mainHandStack
        if (!stack.isEmpty) return false
        if (hitResult == null) return false
        val pos = hitResult.blockPos

//        if (world.getBlockState(pos).isIn(BlockTags.OVERWORLD_CARVER_REPLACEABLES)){
//        }

        val be = world.getBlockEntity(pos)
        if (be !is SkullBlockEntity) return false

        if (!world.isClient) {
            val data = be.getAttached(HEAD_DATA)
            player.sendSystemMessage(Text.literal(data.toString()))
        } else {
            val ticks = be.getAnimationTicks(1f)
            player.sendSystemMessage(Text.literal("Ticks: $ticks"))
        }

        return true
    }

    @JvmField
    val HEAD_ID = id("head")

    @Suppress("UnstableApiUsage")
    @JvmField
    val HEAD_DATA: AttachmentType<Identifier> = AttachmentRegistry.create(HEAD_ID) { builder ->
        builder
            .persistent(Identifier.CODEC)
            .syncWith(Identifier.PACKET_CODEC, AttachmentSyncPredicate.all())

    }

    fun id(path: String): Identifier = Identifier.of(MODID, path)
}
