package org.teamvoided.all_the_heads.utils

import net.fabricmc.fabric.api.event.player.UseBlockCallback
import net.minecraft.network.chat.Component
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.player.Player
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.entity.SkullBlockEntity
import net.minecraft.world.phys.BlockHitResult
import org.teamvoided.all_the_heads.AllTheHeads

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

//        if (world.getBlockState(pos).isIn(BlockTags.OVERWORLD_CARVER_REPLACEABLES)){
//        }

    val be = world.getBlockEntity(pos)
    if (be !is SkullBlockEntity) return false

    if (world.isClientSide) {
        val ticks = be.getAnimation(1f)
        player.sendSystemMessage(Component.literal("Ticks: $ticks"))
    } else {
        val data = be.getAttached(AllTheHeads.HEAD_ATTACHMENT)
        player.sendSystemMessage(Component.literal(data.toString()))
    }

    return true
}