package org.teamvoided.all_the_heads.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.SkullBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static org.teamvoided.all_the_heads.AllTheHeads.HEAD_ATTACHMENT;
import static org.teamvoided.all_the_heads.AllTheHeads.HEAD_ID;
import static org.teamvoided.all_the_heads.utils.UtilsKt.getHeadData;

@SuppressWarnings("UnstableApiUsage")
@Mixin(SkullBlockEntity.class)
public abstract class SkullBlockEntityMixin extends BlockEntity {
    public SkullBlockEntityMixin(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @Inject(method = "applyImplicitComponents", at = @At("TAIL"))
    void readCustomComponents(BlockEntity.DataComponentInput access, CallbackInfo ci) {
        this.setAttached(HEAD_ATTACHMENT, getHeadData(access.get(DataComponents.CUSTOM_DATA)));
    }

    @Inject(method = "collectImplicitComponents", at = @At("TAIL"))
    void addCustomComponents(DataComponentMap.Builder builder, CallbackInfo ci) {
        var headData = this.getAttached(HEAD_ATTACHMENT);
        if (headData != null) {
            var nbt = builder.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.of(new CompoundTag())).getUnsafe();
            nbt.putString(HEAD_ID.toString(), headData.toString());
            builder.set(DataComponents.CUSTOM_DATA, CustomData.of(nbt));
        }
    }
}
