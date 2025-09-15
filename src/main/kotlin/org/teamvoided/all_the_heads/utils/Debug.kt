package org.teamvoided.all_the_heads.utils

import net.fabricmc.fabric.api.event.player.UseBlockCallback
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking
import net.minecraft.core.component.DataComponents
import net.minecraft.network.chat.Component
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.player.Player
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.entity.SkullBlockEntity
import net.minecraft.world.phys.BlockHitResult
import org.teamvoided.all_the_heads.AllTheHeads
import org.teamvoided.all_the_heads.CopyToClipboardPayload

fun initDebug() {
    if (!isDev()) return
    UseBlockCallback.EVENT.register { player, world, hand, hitResult ->
        if (debugUse(player, world, hand, hitResult)) InteractionResult.SUCCESS
        else InteractionResult.PASS
    }
}


@Suppress("UnstableApiUsage")
fun debugUse(
    player: Player,
    world: Level,
    hand: InteractionHand,
    hitResult: BlockHitResult?,
): Boolean {
    val stack = player.mainHandItem
    if (!stack.isEmpty) return false
    if (hitResult == null) return false
    val pos = hitResult.blockPos

    val be = world.getBlockEntity(pos)
    if (be !is SkullBlockEntity) return false

    return false

    if (player is ServerPlayer) {
        val text = getCopyText(be)
        if (text != null) {
            ServerPlayNetworking.send(player, text)
        }

        val data = be.getAttached(AllTheHeads.HEAD_ATTACHMENT)
        if (data != null) {
            player.sendSystemMessage(Component.literal(data.toString()))
        }
    }

    return true
}

fun getCopyText(be: SkullBlockEntity): CopyToClipboardPayload? {
    val profile = be.ownerProfile ?: return null
    val texture = profile.properties.get("textures").first().value
    val name = be.components().get(DataComponents.ITEM_NAME)
        ?.string?.trim()
        ?.replace(" ", "_")
        ?.replace("_Head", "")
        ?: "no_name"

    return CopyToClipboardPayload(name, "const val ${name.uppercase()} = \"$texture\"")
}