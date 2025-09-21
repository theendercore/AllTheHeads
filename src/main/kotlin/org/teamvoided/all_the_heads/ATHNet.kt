package org.teamvoided.all_the_heads

import com.mojang.brigadier.context.CommandContext
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry
import net.minecraft.commands.CommandSourceStack
import net.minecraft.commands.Commands.literal

object ATHNet {
    fun init() {
        AllTheHeads.log.error("If you see this then the mod has debug networking enabled! Please contact ender to disable it")
        PayloadTypeRegistry.playS2C().register(CopyToClipboardPayload.ID, CopyToClipboardPayload.CODEC)

        CommandRegistrationCallback.EVENT.register { dispatch, reg, env ->
            val root = literal("ath").build()
            dispatch.root.addChild(root)

            val reload = literal("reload").executes { athReload(it) }.build()
            root.addChild(reload)

        }
    }

    var CLIENT_DISPATCHER = { _: CommandContext<CommandSourceStack> -> }

    fun athReload(it: CommandContext<CommandSourceStack>): Int {
        CLIENT_DISPATCHER(it)
        return 0
    }
}