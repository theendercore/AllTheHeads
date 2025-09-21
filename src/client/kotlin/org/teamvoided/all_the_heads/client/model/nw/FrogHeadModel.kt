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

class FrogHeadModel(modelPart: ModelPart) : HeadModelBase() {
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
        val ID = AllTheHeads.id("frog")
        fun head(): LayerDefinition {
            val mesh = MeshDefinition()
            val head = mesh.root.addOrReplaceChild(
                "head",
                CubeListBuilder.create().texOffs(23, 13).addBox(-3.5f, -1.0f, -7.0f, 7.0f, 0.0f, 9.0f).texOffs(0, 13)
                    .addBox(-3.5f, -2.0f, -7.0f, 7.0f, 3.0f, 9.0f),
                PartPose.offset(0.0f, -2.0f, -1.0f)
            )
            val partDefinition5 =
                head.addOrReplaceChild("eyes", CubeListBuilder.create(), PartPose.offset(-0.5f, 0.0f, 2.0f))
            partDefinition5.addOrReplaceChild(
                "right_eye",
                CubeListBuilder.create().texOffs(0, 0).addBox(-1.5f, -1.0f, -1.5f, 3.0f, 2.0f, 3.0f),
                PartPose.offset(-1.5f, -3.0f, -6.5f)
            )
            partDefinition5.addOrReplaceChild(
                "left_eye",
                CubeListBuilder.create().texOffs(0, 5).addBox(-1.5f, -1.0f, -1.5f, 3.0f, 2.0f, 3.0f),
                PartPose.offset(2.5f, -3.0f, -6.5f)
            )
            return LayerDefinition.create(mesh, 48, 48)
        }
    }
}