package org.teamvoided.template.utils

import net.minecraft.component.DataComponentTypes
import net.minecraft.component.type.NbtComponent
import net.minecraft.item.ItemStack
import net.minecraft.util.Identifier
import org.teamvoided.template.Template
import org.teamvoided.template.Template.HEAD_ID

fun getHeadData(stack: ItemStack): Identifier? {
    val nbt = stack.get(DataComponentTypes.CUSTOM_DATA) ?: return null
    return getHeadData(nbt)
}

fun getHeadData(comp: NbtComponent?): Identifier? {
    if (comp == null || comp.isEmpty) return null
    val nbt = comp.nbt
    if (!nbt.contains(HEAD_ID.toString())) return null
    val id = nbt.getString(HEAD_ID.toString()) ?: return null

    return Identifier.tryParse(id)
}