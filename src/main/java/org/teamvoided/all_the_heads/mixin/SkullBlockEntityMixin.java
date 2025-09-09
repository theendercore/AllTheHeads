package org.teamvoided.all_the_heads.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.SkullBlockEntity;
import net.minecraft.component.DataComponentMap;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static org.teamvoided.all_the_heads.AllTheHeads.*;
import static org.teamvoided.all_the_heads.utils.UtilsKt.getHeadData;

@SuppressWarnings("UnstableApiUsage")
@Mixin(SkullBlockEntity.class)
public abstract class SkullBlockEntityMixin extends BlockEntity {
    public SkullBlockEntityMixin(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @Inject(method = "readComponents", at = @At("TAIL"))
    void readCustomComponents(BlockEntity.ComponentAccess access, CallbackInfo ci) {
        this.setAttached(HEAD_DATA, getHeadData(access.get(DataComponentTypes.CUSTOM_DATA)));
    }

    @Inject(method = "addComponents", at = @At("TAIL"))
    void addCustomComponents(DataComponentMap.Builder builder, CallbackInfo ci) {
        var headData = this.getAttached(HEAD_DATA);
        if (headData != null) {
            var nbt = builder.getOrDefault(DataComponentTypes.CUSTOM_DATA, NbtComponent.of(new NbtCompound())).getNbt();
            nbt.putString(HEAD_ID.toString(), headData.toString());
            builder.put(DataComponentTypes.CUSTOM_DATA, NbtComponent.of(nbt));
        }
    }
}
