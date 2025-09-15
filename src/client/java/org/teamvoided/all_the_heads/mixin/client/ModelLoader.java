package org.teamvoided.all_the_heads.mixin.client;

import net.minecraft.client.model.SkullModelBase;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.SkullBlockRenderer;
import net.minecraft.world.level.block.SkullBlock;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.teamvoided.all_the_heads.client.init.helpers.ModelRegistryKt;

import java.util.Map;

import static org.teamvoided.all_the_heads.client.init.ModelsManager.VANILLA_MODEL_ACCESS;

@Mixin(SkullBlockRenderer.class)
public abstract class ModelLoader {
    @Shadow
    @Final
    private Map<SkullBlock.Type, SkullModelBase> modelByType;

    @Inject(method = "<init>", at = @At("TAIL"))
    void x(BlockEntityRendererProvider.Context context, CallbackInfo ci) {
        VANILLA_MODEL_ACCESS = modelByType;
        ModelRegistryKt.loadModels(context.getModelSet());
    }
}
