package org.teamvoided.all_the_heads.mixin;

import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.entity.SkullBlockEntity;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(SkullBlockEntity.class)
public interface SkullBlockEntityAccessor {
    @Accessor("customName")
    @Nullable Component endDebug_getCustomName();
}
