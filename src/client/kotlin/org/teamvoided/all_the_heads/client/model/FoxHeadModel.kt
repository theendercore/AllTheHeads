package org.teamvoided.all_the_heads.client.model

import com.mojang.blaze3d.vertex.PoseStack
import com.mojang.blaze3d.vertex.VertexConsumer
import net.minecraft.client.model.geom.ModelPart
import net.minecraft.client.model.geom.PartPose
import net.minecraft.client.model.geom.builders.*
import net.minecraft.resources.ResourceLocation
import org.teamvoided.all_the_heads.AllTheHeads
import org.teamvoided.all_the_heads.client.model.utils.HeadModelBase

class FoxHeadModel(modelPart: ModelPart) : HeadModelBase() {
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
        val ID = AllTheHeads.id("fox")
        fun head(): LayerDefinition {
            val mesh = MeshDefinition()
            val head  = mesh.root.addOrReplaceChild(
                "head",
                CubeListBuilder.create().texOffs(1, 5).addBox(-4f, -6f, -3f, 8f, 6f, 6f),
                PartPose.ZERO
            )
            head.addOrReplaceChild(
                "right_ear",
                CubeListBuilder.create().texOffs(8, 1).addBox(-4f, -8f, -2f, 2f, 2f, 1f),
                PartPose.ZERO
            )
            head.addOrReplaceChild(
                "left_ear",
                CubeListBuilder.create().texOffs(15, 1).addBox(2f, -8f, -2f, 2f, 2f, 1f),
                PartPose.ZERO
            )
            head.addOrReplaceChild(
                "nose",
                CubeListBuilder.create().texOffs(6, 18).addBox(-2f, -2.01f, -6f, 4f, 2f, 3f),
                PartPose.ZERO
            )

            return LayerDefinition.create(mesh, 48, 32)
        }
    }
}