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

class SilverfishHeadModel(modelPart: ModelPart) : HeadModelBase() {
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
        val ID = AllTheHeads.id("silverfish")
        fun head(): LayerDefinition {
            val mesh = MeshDefinition()
            val head = mesh.root.addOrReplaceChild(
                "head",
                CubeListBuilder.create().texOffs(0, 0).addBox(0f, 0f, 0f, 0f, 0f, 0f),
                PartPose.ZERO
            )

            head.addOrReplaceChild(
                "segment0", // Eyes
                CubeListBuilder.create().texOffs(0, 0).addBox(
                    -1.5f, -2f, -2f,
                    3f, 2f, 2f
                ),
                PartPose.ZERO
            )

            head.addOrReplaceChild(
                "segment1",
                CubeListBuilder.create().texOffs(0, 4).addBox(
                    -2f, -3f, 0f,
                    4f, 3f, 2f
                ),
                PartPose.ZERO
            )

            head.addOrReplaceChild(
                "layer0",
                CubeListBuilder.create().texOffs(20, 18).addBox(
                    -3f, -5f, -0.5f,
                    6f, 5f, 2f
                ),
                PartPose.ZERO
            )
            return LayerDefinition.create(mesh, 64, 32)
        }
    }
}