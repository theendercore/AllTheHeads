package org.teamvoided.all_the_heads.utils

import net.minecraft.core.component.DataComponents
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.component.CustomData
import org.teamvoided.all_the_heads.AllTheHeads.HEAD_ID


fun getHeadData(stack: ItemStack): ResourceLocation? {
    val nbt = stack.get(DataComponents.CUSTOM_DATA) ?: return null
    return getHeadData(nbt)
}

fun getHeadData(comp: CustomData?): ResourceLocation? {
    if (comp == null || comp.isEmpty) return null
    if (!comp.contains(HEAD_ID.toString())) return null
    val id = comp.copyTag().getString(HEAD_ID.toString()) ?: return null

    return ResourceLocation.tryParse(id)
}