package org.teamvoided.template.mixin.client;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.block.AbstractSkullBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.SkullBlockEntity;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(AbstractSkullBlock.class)
public class AbstractSkullBlockMixin<T extends SkullBlockEntity> {
    @Nullable
    @ModifyReturnValue(method = "getTicker", at = @At("RETURN"))
    BlockEntityTicker<T> addPlayerHeadTicker(@Nullable BlockEntityTicker<T> original, @Local(argsOnly = true) BlockState state) {
        return state.isOf(Blocks.PLAYER_HEAD) || state.isOf(Blocks.PLAYER_WALL_HEAD) ? SkullBlockEntity::clientTick : original;
    }
}
