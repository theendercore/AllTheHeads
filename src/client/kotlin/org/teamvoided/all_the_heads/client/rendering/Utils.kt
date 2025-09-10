package org.teamvoided.all_the_heads.client.rendering

import net.minecraft.world.item.component.ResolvableProfile

fun ResolvableProfile.getTexture(): String? {
    return this.properties.get("textures").first().value
}