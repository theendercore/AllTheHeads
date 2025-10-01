package org.teamvoided.all_the_heads.client.data.gen.prov

import net.minecraft.core.HolderLookup
import org.teamvoided.all_the_heads.AllTheHeads.id
import org.teamvoided.all_the_heads.AllTheHeads.mc
import org.teamvoided.all_the_heads.client.data.gen.CreationContext
import org.teamvoided.all_the_heads.client.data.gen.FutureLookup
import org.teamvoided.all_the_heads.client.data.gen.HeadModelOverrideProvider
import org.teamvoided.all_the_heads.client.data.gen.Output
import org.teamvoided.all_the_heads.client.model.AllayHeadModel
import org.teamvoided.all_the_heads.client.model.HorseHeadModel
import org.teamvoided.all_the_heads.client.model.SlimeHeadModel
import org.teamvoided.all_the_heads.client.resources.HeadModelOverride

class ATHOverrideProvider(o: Output, r: FutureLookup) : HeadModelOverrideProvider(o, r) {
    override fun generateOverrides(lookup: HolderLookup.Provider, dataBuilder: CreationContext<HeadModelOverride>) {
        dataBuilder.create(
            id("skeleon"),
            HeadModelOverride(AllayHeadModel.ID, mc("entity_cutout_no_cull"), mc("textures/entity/allay.png"), 15)
        )
        dataBuilder.create(
            id("skeleon2"),
            HeadModelOverride(SlimeHeadModel.ID, mc("entity_cutout_no_cull"), mc("textures/entity/slime/slime.png"))
        )

        dataBuilder.create(
            mc("test"),
            HeadModelOverride(HorseHeadModel.ID, mc("entity_cutout_no_cull"), mc("textures/entity/horse/horse_skeleton.png"))
        )
    }
}