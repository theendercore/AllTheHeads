package org.teamvoided.all_the_heads.client.data

import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.ItemDisplayContext
import net.minecraft.world.item.component.ResolvableProfile
import net.minecraft.world.level.block.entity.SkullBlockEntity

data class SkullRenderContext(
    val skullId: ResourceLocation?,
    val skullOwner: ResolvableProfile?,
    val renderLocation: RenderLocation,
    val itemCtx: ItemDisplayContext?,
    val blockEntity : SkullBlockEntity?
)
