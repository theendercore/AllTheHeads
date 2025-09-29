package org.teamvoided.all_the_heads.client.utils

import com.mojang.brigadier.context.CommandContext
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource
import net.minecraft.network.chat.Component
import org.teamvoided.all_the_heads.utils.isDev

object ClientDebug {
    fun init() {
        if (!isDev()) return
        ClientCommandRegistrationCallback.EVENT.register { dispatcher, ctx ->
            val root = literal("cath").build()
            dispatcher.root.addChild(root)

            val runDataGen = literal("data_gen").executes(::runDataGen).build()
            root.addChild(runDataGen)
        }
    }
}

fun runDataGen(ctx: CommandContext<FabricClientCommandSource>): Int {
    val src = ctx.source ?: return -1
    val client = src.client ?: return -1
    val player = src.player ?: return -1
    val level = src.world ?: return -1

    run()

    src.sendFeedback(Component.literal("Ran Super Data Gen!"))
    return 0
}
