package org.teamvoided.all_the_heads

import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry

object ATHNet {
    fun init() {
        AllTheHeads.log.error("If you see this then the mod has debug networking enabled! Please contact ender to disable it")
        PayloadTypeRegistry.playS2C().register(CopyToClipboardPayload.ID, CopyToClipboardPayload.CODEC)
    }
}