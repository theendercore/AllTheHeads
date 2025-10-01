package org.teamvoided.all_the_heads.client.data.gen.prov

import net.minecraft.core.HolderLookup
import org.teamvoided.all_the_heads.AllTheHeads.id
import org.teamvoided.all_the_heads.AllTheHeads.mc
import org.teamvoided.all_the_heads.client.data.gen.CreationContext
import org.teamvoided.all_the_heads.client.data.gen.FutureLookup
import org.teamvoided.all_the_heads.client.data.gen.HeadModelOverrideProvider
import org.teamvoided.all_the_heads.client.data.gen.Output
import org.teamvoided.all_the_heads.client.init.ATHRenderTypes.ENTITY_CUTOUT_NO_CULL
import org.teamvoided.all_the_heads.client.model.AllayHeadModel
import org.teamvoided.all_the_heads.client.model.HorseHeadModel
import org.teamvoided.all_the_heads.client.model.SlimeHeadModel
import org.teamvoided.all_the_heads.client.resources.HeadModelOverride

class ATHOverrideProvider(o: Output, r: FutureLookup) : HeadModelOverrideProvider(o, r) {
    override fun generateOverrides(lookup: HolderLookup.Provider, dataBuilder: CreationContext<HeadModelOverride>) {
        dataBuilder.create(id("ske"), HeadModelOverride(AllayHeadModel.ID, ENTITY_CUTOUT_NO_CULL, entity("allay"), 15))
        dataBuilder.create(
            id("ske2"), HeadModelOverride(SlimeHeadModel.ID, ENTITY_CUTOUT_NO_CULL, entity("slime/slime"))
        )
        dataBuilder.create(
            mc("test"), HeadModelOverride(HorseHeadModel.ID, ENTITY_CUTOUT_NO_CULL, entity("horse/horse_skeleton"))
        )
    }

    fun entity(tex: String) = mc("textures/entity/$tex.png")
}