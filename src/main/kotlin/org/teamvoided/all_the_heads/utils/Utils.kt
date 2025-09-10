package org.teamvoided.all_the_heads.utils

import net.fabricmc.loader.api.FabricLoader
import net.minecraft.core.component.DataComponents
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.component.CustomData
import org.teamvoided.all_the_heads.AllTheHeads.HEAD_ID
import org.teamvoided.all_the_heads.AllTheHeads.tryParseId


fun getHeadData(stack: ItemStack): ResourceLocation? {
    val nbt = stack.get(DataComponents.CUSTOM_DATA) ?: return null
    return getHeadData(nbt)
}

fun getHeadData(comp: CustomData?): ResourceLocation? {
    if (comp == null || comp.isEmpty) return null
    if (!comp.contains(HEAD_ID)) return null
    val id = comp.copyTag().getString(HEAD_ID) ?: return null

    return tryParseId(id)
}

// (ender) In case the sync causes problem only send to players with mod
/*fun canSync(): AttachmentSyncPredicate =AttachmentSyncPredicate { target: AttachmentTarget?, player: ServerPlayer? ->
    ServerPlayNetworking.canSend()
    false
}*/

fun isDev() = FabricLoader.getInstance().isDevelopmentEnvironment