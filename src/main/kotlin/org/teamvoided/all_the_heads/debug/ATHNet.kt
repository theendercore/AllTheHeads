package org.teamvoided.all_the_heads.debug

import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry
import net.minecraft.world.item.ItemStack
import org.teamvoided.all_the_heads.AllTheHeads
import org.teamvoided.all_the_heads.CopyToClipboardPayload

typealias ItemSpawner = (stack: ItemStack) -> Unit

object ATHNet {
    fun init() {
        AllTheHeads.log.error("If you see this then the mod has debug networking enabled! Please contact ender to disable it")
        PayloadTypeRegistry.playS2C().register(CopyToClipboardPayload.ID, CopyToClipboardPayload.CODEC)
    }
}