package org.teamvoided.all_the_heads.client.data

import net.minecraft.world.item.ItemDisplayContext

data class AdditionRenderData(val location: RenderLocation, val itemCtx: ItemDisplayContext?) {
    constructor(loc: RenderLocation) : this(loc, null)
}
