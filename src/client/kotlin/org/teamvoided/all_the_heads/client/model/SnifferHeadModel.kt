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

class SnifferHeadModel(modelPart: ModelPart) : HeadModelBase() {
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
        val ID = AllTheHeads.id("sniffer")
        fun head(): LayerDefinition {
            val mesh = MeshDefinition()
            val head = mesh.root.addOrReplaceChild(
                "head",
                CubeListBuilder.create()
                    .texOffs(8, 15).addBox(-6.5f, -15f, -5.5f, 13f, 18f, 11f)
                    .texOffs(8, 4).addBox(-6.5f, 0f, -5.5f, 13f, 0f, 11f),
                PartPose.ZERO
            )
            head.addOrReplaceChild(
                "left_ear",
                CubeListBuilder.create().texOffs(2, 0).addBox(6.5f, -15f, -1.5f, 1f, 19f, 7f),
                PartPose.ZERO
            )
            head.addOrReplaceChild(
                "right_ear",
                CubeListBuilder.create().texOffs(48, 0).addBox(-7.5f, -15f, -1.5f, 1f, 19f, 7f),
                PartPose.ZERO
            )
            head.addOrReplaceChild(
                "nose",
                CubeListBuilder.create().texOffs(10, 45).addBox(-6.5f, -14f, -14.5f, 13f, 2f, 9f),
                PartPose.ZERO
            )
            head.addOrReplaceChild(
                "lower_beak",
                CubeListBuilder.create().texOffs(10, 57).addBox(-6.5f, -12f, -14.5f, 13f, 12f, 9f),
                PartPose.ZERO
            )
            return LayerDefinition.create(mesh, 192, 192)
        }
    }
}
