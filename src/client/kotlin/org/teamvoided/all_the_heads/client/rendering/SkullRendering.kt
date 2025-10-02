package org.teamvoided.all_the_heads.client.rendering

import com.mojang.blaze3d.vertex.PoseStack
import com.terraformersmc.modmenu.util.mod.Mod
import net.minecraft.client.model.Model
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.client.renderer.RenderType
import net.minecraft.core.Direction
import net.minecraft.world.level.block.SkullBlock
import org.teamvoided.all_the_heads.AllTheHeads.sendError
import org.teamvoided.all_the_heads.AllTheHeads.tryParseId
import org.teamvoided.all_the_heads.client.AllTheHeadsClient.clientConfig
import org.teamvoided.all_the_heads.client.data.HeadRenderMode
import org.teamvoided.all_the_heads.client.data.ProfileDataMode
import org.teamvoided.all_the_heads.client.data.SkullRenderContext
import org.teamvoided.all_the_heads.client.data.gen.prov.ATHOverrideProvider.Companion.entityBasic
import org.teamvoided.all_the_heads.client.data.render.HeadModel
import org.teamvoided.all_the_heads.client.data.render.VanillaHeadModel
import org.teamvoided.all_the_heads.client.data.textures.MiscTextures.TEXTURE_BLACKLIST
import org.teamvoided.all_the_heads.client.init.ModelsManager
import org.teamvoided.all_the_heads.client.utils.getTexture

@Suppress("DEPRECATION")
fun renderSkull(
    direction: Direction?, yaw: Float, animationProgress: Float,
    matrices: PoseStack, vertexConsumers: MultiBufferSource, light: Int,
    ctx: SkullRenderContext,
): Boolean {
    val data = fetchSkullRenderInfo(ctx)
    debugRenderer(direction, yaw, animationProgress, matrices, vertexConsumers, light, ctx, data)
    if (data == null) return true

    matrices.pushPose()
    if (direction == null) matrices.translate(0.5f, 0.0f, 0.5f)
    else {
        val f = 0.25f
        matrices.translate(0.5f - direction.stepX * f, f, 0.5f - direction.stepZ * f)
    }

    matrices.scale(-1.0f, -1.0f, 1.0f)
    data.render(animationProgress, yaw, 0.0f, matrices, vertexConsumers, light)
    matrices.popPose()
    return false
}

fun fetchSkullRenderInfo(ctx: SkullRenderContext): HeadModel? {
    when (clientConfig.headRenderMode.get()) {
        HeadRenderMode.PROFILE -> when (clientConfig.profileDataMode.get()) {
            ProfileDataMode.TEXTURE -> {
                val texture = ctx.skullOwner?.getTexture() ?: return null
                val data = ModelsManager.TEXTURE_TO_MODEL[texture]?.let(ModelsManager.HEAD_MODELS::get)
                if (data == null) {
                    if (TEXTURE_BLACKLIST.contains(texture)) return null
                    sendError("Could not find model for texture $texture", texture)
                    return null
                }
                return data
            }

            ProfileDataMode.ID, ProfileDataMode.NAME -> return null
        }


        HeadRenderMode.CUSTOM_DATA -> {
//            val id = ctx.skullId ?: return null
            val model = models[SkullBlock.Types.DRAGON]
            if (model == null) {
                sendError("Could not find model for ${SkullBlock.Types.DRAGON}", "No thanks")
                return null
            }

            return VanillaHeadModel(SkullBlock.Types.DRAGON, entityBasic("enderdragon/dragon"))
        }
    }
}


fun rType(texture: String): RenderType = RenderType.entityCutoutNoCullZOffset(tryParseId(texture)!!)