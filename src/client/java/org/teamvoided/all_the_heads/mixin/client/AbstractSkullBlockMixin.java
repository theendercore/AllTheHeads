package org.teamvoided.all_the_heads.mixin.client;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.world.level.block.AbstractSkullBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.SkullBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(AbstractSkullBlock.class)
public class AbstractSkullBlockMixin<T extends SkullBlockEntity> {
    @Nullable
    @ModifyReturnValue(method = "getTicker", at = @At("RETURN"))
    BlockEntityTicker<T> addPlayerHeadTicker(@Nullable BlockEntityTicker<T> original, @Local(argsOnly = true) BlockState state) {
        return state.is(Blocks.PLAYER_HEAD) || state.is(Blocks.PLAYER_WALL_HEAD) ? SkullBlockEntity::animation : original;
    }
}
