package org.teamvoided.all_the_heads

import net.minecraft.network.FriendlyByteBuf
import net.minecraft.network.codec.StreamCodec
import net.minecraft.network.protocol.common.custom.CustomPacketPayload

class CopyToClipboardPayload(val message: String, val content: String) : CustomPacketPayload {
    constructor(buf: FriendlyByteBuf) : this(buf.readUtf(), buf.readUtf())

    override fun type() = ID
    fun write(buf: FriendlyByteBuf) {
        buf.writeUtf(message)
        buf.writeUtf(content)
    }

    companion object {
        val CODEC: StreamCodec<FriendlyByteBuf, CopyToClipboardPayload> =
            CustomPacketPayload.codec(CopyToClipboardPayload::write, ::CopyToClipboardPayload)
        val ID = CustomPacketPayload.Type<CopyToClipboardPayload>(AllTheHeads.id("copy_to_clipboard"))
    }
}