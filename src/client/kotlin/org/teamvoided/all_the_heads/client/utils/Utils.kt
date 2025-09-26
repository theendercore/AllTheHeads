package org.teamvoided.all_the_heads.client.utils

import net.minecraft.world.item.component.ResolvableProfile

fun ResolvableProfile.getTexture(): String? = this.properties.get("textures").firstOrNull()?.value