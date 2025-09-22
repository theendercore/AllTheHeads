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

class RabbitHeadModel(modelPart: ModelPart) : HeadModelBase() {
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
        val ID = AllTheHeads.id("rabbit")
        fun head(): LayerDefinition {
            val mesh = MeshDefinition()
            val head = mesh.root.addOrReplaceChild(
                "head",
                CubeListBuilder.create().texOffs(32, 0).addBox(
                    -2.5F, -4f, -2.5f,
                    5f, 4f, 5f
                ),
                PartPose.ZERO
            )
            head.addOrReplaceChild(
                "right_ear",
                CubeListBuilder.create().texOffs(52, 0).addBox(
                    -2.5f, -9f, -1f,
                    2f, 5f, 1f
                ),
                PartPose.offsetAndRotation(0f, 0f, 2.5f, 0f, (-Math.PI / 12f).toFloat(), 0f)
            )
            head.addOrReplaceChild(
                "left_ear",
                CubeListBuilder.create().texOffs(58, 0).addBox(
                    0.5f, -9f, -1f,
                    2f, 5f, 1f
                ),
                PartPose.offsetAndRotation(0f, 0f, 2.5f, 0f, (Math.PI / 12f).toFloat(), 0f)
            )
            head.addOrReplaceChild(
                "nose",
                CubeListBuilder.create().texOffs(32, 9).addBox(
                    -0.5f, -2.5f, -3f,
                    1f, 1f, 1f
                ),
                PartPose.ZERO
            )
            return LayerDefinition.create(mesh, 64, 32)
        }
    }
}