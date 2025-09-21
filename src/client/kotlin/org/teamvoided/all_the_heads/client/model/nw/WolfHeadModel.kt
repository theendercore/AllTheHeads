package org.teamvoided.all_the_heads.client.model.nw

import com.mojang.blaze3d.vertex.PoseStack
import com.mojang.blaze3d.vertex.VertexConsumer
import net.minecraft.client.model.geom.ModelPart
import net.minecraft.client.model.geom.PartPose
import net.minecraft.client.model.geom.builders.CubeDeformation
import net.minecraft.client.model.geom.builders.CubeListBuilder
import net.minecraft.client.model.geom.builders.LayerDefinition
import net.minecraft.client.model.geom.builders.MeshDefinition
import net.minecraft.resources.ResourceLocation
import org.teamvoided.all_the_heads.AllTheHeads
import org.teamvoided.all_the_heads.client.model.HeadModelBase

class WolfHeadModel(modelPart: ModelPart) : HeadModelBase() {
    override fun getId(): ResourceLocation = ID
    private val head: ModelPart = modelPart.getChild("head")

    override fun setupAnim(f: Float, g: Float, h: Float) {
        head.yRot = g * (Math.PI / 180.0).toFloat()
        head.xRot = h * (Math.PI / 180.0).toFloat()
    }

    override fun renderToBuffer(poseStack: PoseStack, vertexConsumer: VertexConsumer, i: Int, j: Int, k: Int) {
        head.render(poseStack, vertexConsumer, i, j, k)
    }

    companion object {
        val ID = AllTheHeads.id("wolf")
        fun head(): LayerDefinition {
            val mesh = MeshDefinition()
            val def = CubeDeformation.NONE
            val head =
                mesh.root.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.offset(-1.0f, 13.5f, -7.0f))
            head.addOrReplaceChild(
                "real_head",
                CubeListBuilder.create()
                    .texOffs(0, 0)
                    .addBox(-2.0f, -3.0f, -2.0f, 6.0f, 6.0f, 4.0f, def)
                    .texOffs(16, 14)
                    .addBox(-2.0f, -5.0f, 0.0f, 2.0f, 2.0f, 1.0f, def)
                    .texOffs(16, 14)
                    .addBox(2.0f, -5.0f, 0.0f, 2.0f, 2.0f, 1.0f, def)
                    .texOffs(0, 10)
                    .addBox(-0.5f, -0.001f, -5.0f, 3.0f, 3.0f, 4.0f, def),
                PartPose.ZERO
            )
            return LayerDefinition.create(mesh, 64, 32)
        }
    }
}
