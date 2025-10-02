package org.teamvoided.all_the_heads.client.data.gen.prov

import net.minecraft.core.HolderLookup
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.SkullBlock
import org.teamvoided.all_the_heads.AllTheHeads.id
import org.teamvoided.all_the_heads.AllTheHeads.mc
import org.teamvoided.all_the_heads.client.data.gen.CreationContext
import org.teamvoided.all_the_heads.client.data.gen.FutureLookup
import org.teamvoided.all_the_heads.client.data.gen.HeadModelOverrideProvider
import org.teamvoided.all_the_heads.client.data.gen.Output
import org.teamvoided.all_the_heads.client.data.render.*
import org.teamvoided.all_the_heads.client.data.render.type.TexturedRenderType
import org.teamvoided.all_the_heads.client.data.render.type.TexturedRenderType.Companion.ENTITY_CUTOUT_NO_CULL
import org.teamvoided.all_the_heads.client.init.ModelsManager.getBuiltIn
import org.teamvoided.all_the_heads.client.init.ModelsManager.getVanilla
import org.teamvoided.all_the_heads.client.model.AllayHeadModel
import org.teamvoided.all_the_heads.client.resources.HeadModelOverride

class ATHOverrideProvider(o: Output, r: FutureLookup) : HeadModelOverrideProvider(o, r) {
    override fun generateOverrides(lookup: HolderLookup.Provider, dataBuilder: CreationContext<HeadModelOverride>) {
        dataBuilder.create(
            id("test1"),
            BuiltInHeadModel(
                getBuiltIn(AllayHeadModel.ID),
                TexturedRenderType.TYPES[ENTITY_CUTOUT_NO_CULL]?.invoke(entity("allay/allay"))!!,
                15
            )
        )
        dataBuilder.create(
            id("test2"),
            VanillaHeadModel(
                getVanilla(SkullBlock.Types.SKELETON),
                TexturedRenderType.TYPES[ENTITY_CUTOUT_NO_CULL]?.invoke(entity("slime/slime"))!!,
            )
        )
        dataBuilder.create(
            id("test3"),
            ListHeadModel(
                BlockHeadModel(Blocks.RED_STAINED_GLASS.defaultBlockState()),
                BlockHeadModel(Blocks.OAK_FENCE.defaultBlockState())
            )
        )
    }

    fun CreationContext<HeadModelOverride>.create(id: ResourceLocation, model: HeadModel) {
        this.create(id, HeadModelOverride(id, model))
    }

    fun entity(tex: String) = mc("textures/entity/$tex")
}