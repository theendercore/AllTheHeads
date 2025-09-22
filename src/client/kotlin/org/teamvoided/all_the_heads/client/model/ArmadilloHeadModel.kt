package org.teamvoided.all_the_heads.client.model

import com.mojang.blaze3d.vertex.PoseStack
import com.mojang.blaze3d.vertex.VertexConsumer
import net.minecraft.client.model.geom.ModelPart
import net.minecraft.client.model.geom.PartPose
import net.minecraft.client.model.geom.builders.CubeListBuilder
import net.minecraft.client.model.geom.builders.LayerDefinition
import net.minecraft.client.model.geom.builders.MeshDefinition
import net.minecraft.resources.ResourceLocation
import org.teamvoided.all_the_heads.AllTheHeads
import org.teamvoided.all_the_heads.client.model.utils.HeadModelBase

class ArmadilloHeadModel(modelPart: ModelPart) : HeadModelBase() {
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
        val ID = AllTheHeads.id("armadillo")
        fun head(): LayerDefinition {
            val mesh = MeshDefinition()

            val head = mesh.root.addOrReplaceChild(
                "head",
                CubeListBuilder.create(),
                PartPose.offset(0f, -4f, 0f)
            )
            head.addOrReplaceChild(
                "head_cube",
                CubeListBuilder.create().texOffs(43, 15).addBox(-1.5f, -1f, -1f, 3f, 5f, 2f),
                PartPose.offsetAndRotation(0f, 0f, 0f, -0.3927f, 0f, 0f)
            )
            val rightEar = head.addOrReplaceChild(
                "right_ear",
                CubeListBuilder.create(),
                PartPose.offset(-1f, -1f, 0f)
            )
            rightEar.addOrReplaceChild(
                "right_ear_cube",
                CubeListBuilder.create().texOffs(43, 10).addBox(-2f, -3f, 0f, 2f, 5f, 0f),
                PartPose.offsetAndRotation(-0.5f, 0f, -0.6f, 0.1886f, -0.3864f, -0.0718f)
            )
            val leftEar = head.addOrReplaceChild(
                "left_ear",
                CubeListBuilder.create(),
                PartPose.offset(1f, -2f, 0f)
            )
            leftEar.addOrReplaceChild(
                "left_ear_cube",
                CubeListBuilder.create().texOffs(47, 10).addBox(0f, -3f, 0f, 2f, 5f, 0f),
                PartPose.offsetAndRotation(0.5f, 1f, -0.6f, 0.1886f, 0.3864f, 0.0718f)
            )

            return LayerDefinition.create(mesh, 64, 64)
        }
    }
}