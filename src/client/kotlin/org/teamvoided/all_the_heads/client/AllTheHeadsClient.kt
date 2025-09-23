package org.teamvoided.all_the_heads.client

import me.fzzyhmstrs.fzzy_config.api.ConfigApi
import me.fzzyhmstrs.fzzy_config.api.RegisterType
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking
import net.minecraft.client.Minecraft
import net.minecraft.network.chat.Component
import org.teamvoided.all_the_heads.AllTheHeads.log
import org.teamvoided.all_the_heads.CopyToClipboardPayload
import org.teamvoided.all_the_heads.client.config.AllTheHeadsClientConfig
import org.teamvoided.all_the_heads.client.init.ATHModels
import org.teamvoided.all_the_heads.debug.ATHCommands.CLIENT_DISPATCHER
import org.teamvoided.all_the_heads.utils.isDev

@Suppress("unused")
object AllTheHeadsClient {
    @JvmField
    var clientConfig = ConfigApi.registerAndLoadConfig(::AllTheHeadsClientConfig, RegisterType.CLIENT)
    fun init() {
        ATHModels.init()
        ClientPlayNetworking.registerGlobalReceiver(CopyToClipboardPayload.ID, ::copyClipboard)

        CLIENT_DISPATCHER = {
            if (isDev()) {
                ATHModels.init()
                it.source.player?.sendSystemMessage(Component.literal("Reload your resources, NOW!"))
                Minecraft.getInstance().reloadResourcePacks()
            }
        }
    }

    fun copyClipboard(payload: CopyToClipboardPayload, ctx: ClientPlayNetworking.Context) {
        val mc = ctx.client() ?: return
        mc.keyboardHandler.clipboard = payload.content
        mc.player?.displayClientMessage(Component.literal("Copied ${payload.message} Head Texture!"), true)
    }

    val errors = mutableSetOf<String>()

    fun sendError(err: String) {
        if (errors.contains(err)) return

        log.error(err)
        errors.add(err)
    }
}
