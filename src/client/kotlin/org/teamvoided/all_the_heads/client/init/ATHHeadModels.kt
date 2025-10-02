package org.teamvoided.all_the_heads.client.init

import com.mojang.serialization.MapCodec
import net.minecraft.core.Registry
import net.minecraft.resources.ResourceLocation
import org.teamvoided.all_the_heads.AllTheHeads.id
import org.teamvoided.all_the_heads.client.data.render.BlockHeadModel
import org.teamvoided.all_the_heads.client.data.render.HeadModel
import org.teamvoided.all_the_heads.client.data.render.HeadModelType
import org.teamvoided.all_the_heads.client.data.render.ListHeadModel
import org.teamvoided.all_the_heads.client.data.render.BuiltInHeadModel
import org.teamvoided.all_the_heads.client.data.render.VanillaHeadModel

object ATHHeadModels {
    fun init() = Unit
    val BUILT_IN = register("built_in", BuiltInHeadModel.CODEC)
    val VANILLA = register("vanilla", VanillaHeadModel.CODEC)
    val BLOCK = register("block", BlockHeadModel.CODEC)
    val LIST = register("list", ListHeadModel.CODEC)

    fun <T : HeadModel> register(id: String, codec: MapCodec<T>): HeadModelType<T> = register(id(id), codec)
    fun <T : HeadModel> register(id: ResourceLocation, codec: MapCodec<T>): HeadModelType<T> =
        Registry.register(ATHBuiltInRegistries.HEAD_RENDER_STATE_TYPE, id, HeadModelType { codec })
}