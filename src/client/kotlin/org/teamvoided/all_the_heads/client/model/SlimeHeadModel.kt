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

class SlimeHeadModel(modelPart: ModelPart) : HeadModelBase() {
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
        val ID = AllTheHeads.id("slime")
        fun head(): LayerDefinition {
            val mesh = MeshDefinition()
            val head = mesh.root.addOrReplaceChild(
                "head",
                CubeListBuilder.create().texOffs(0, 16).addBox(-3.0f, 0.0f, -3.0f, 6.0f, 6.0f, 6.0f),
                PartPose.offset(0f, -7f, 0f)
            )
            head.addOrReplaceChild(
                "right_eye",
                CubeListBuilder.create().texOffs(32, 0).addBox(-3.25f, 1.0f, -3.5f, 2.0f, 2.0f, 2.0f),
                PartPose.ZERO
            )
            head.addOrReplaceChild(
                "left_eye",
                CubeListBuilder.create().texOffs(32, 4).addBox(1.25f, 1.0f, -3.5f, 2.0f, 2.0f, 2.0f),
                PartPose.ZERO
            )
            head.addOrReplaceChild(
                "mouth",
                CubeListBuilder.create().texOffs(32, 8).addBox(0.0f, 4.0f, -3.5f, 1.0f, 1.0f, 1.0f),
                PartPose.ZERO
            )

            return LayerDefinition.create(mesh, 64, 32)
        }
    }
}
