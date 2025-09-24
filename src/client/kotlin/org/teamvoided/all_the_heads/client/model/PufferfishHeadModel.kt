package org.teamvoided.all_the_heads.client.model

import com.mojang.blaze3d.vertex.PoseStack
import com.mojang.blaze3d.vertex.VertexConsumer
import net.minecraft.client.model.geom.ModelPart
import net.minecraft.client.model.geom.PartPose
import net.minecraft.client.model.geom.builders.CubeListBuilder
import net.minecraft.client.model.geom.builders.LayerDefinition
import net.minecraft.client.model.geom.builders.MeshDefinition
import net.minecraft.resources.ResourceLocation
import net.minecraft.util.Mth
import org.teamvoided.all_the_heads.AllTheHeads
import org.teamvoided.all_the_heads.client.model.utils.HeadModelBase

class PufferfishHeadModel(modelPart: ModelPart) : HeadModelBase() {
    override fun getId(): ResourceLocation = ID
    private val head: ModelPart = modelPart.getChild("head")
    private val leftBlueFin: ModelPart = head.getChild("left_blue_fin")
    private val rightBlueFin: ModelPart = head.getChild("right_blue_fin")

    override fun setupAnim(animTick: Float, g: Float, h: Float) {
        head.yRot = g * (Math.PI / 180.0).toFloat()
        head.xRot = h * (Math.PI / 180.0).toFloat()

        leftBlueFin.zRot = -0.2f + 0.4f * Mth.sin(animTick * 0.2f)
        rightBlueFin.zRot = 0.2f - 0.4f * Mth.sin(animTick * 0.2f)
    }

    override fun renderToBuffer(poseStack: PoseStack, vertexConsumer: VertexConsumer, i: Int, j: Int, k: Int) {
        head.render(poseStack, vertexConsumer, i, j, k)
    }

    companion object {
        val ID = AllTheHeads.id("pufferfish")
        fun head(): LayerDefinition {
            val mesh = MeshDefinition()
            val head = mesh.root.addOrReplaceChild(
                "head", CubeListBuilder.create().texOffs(0, 0).addBox(-4f, -8f, -4f, 8f, 8f, 8f),
                PartPose.ZERO
            )
            head.addOrReplaceChild(
                "right_blue_fin",
                CubeListBuilder.create().texOffs(24, 0).addBox(-2f, 0f, -1f, 2f, 1f, 2f),
                PartPose.offset(-4f, -7f, -2f)
            )
            head.addOrReplaceChild(
                "left_blue_fin",
                CubeListBuilder.create().texOffs(24, 3).addBox(0f, 0f, -1f, 2f, 1f, 2f),
                PartPose.offset(4f, -7f, -2f)
            )
            head.addOrReplaceChild(
                "top_front_fin",
                CubeListBuilder.create().texOffs(15, 17).addBox(-4f, -1f, 0f, 8f, 1f, 0f),
                PartPose.offsetAndRotation(0f, -8f, -4f, (Math.PI / 4).toFloat(), 0f, 0f)
            )
            head.addOrReplaceChild(
                "top_middle_fin",
                CubeListBuilder.create().texOffs(14, 16).addBox(-4f, -1f, 0f, 8f, 1f, 1f),
                PartPose.offset(0f, -8f, 0f)
            )
            head.addOrReplaceChild(
                "top_back_fin",
                CubeListBuilder.create().texOffs(23, 18).addBox(-4f, -1f, 0f, 8f, 1f, 0f),
                PartPose.offsetAndRotation(0f, -8f, 4f, (-Math.PI / 4).toFloat(), 0f, 0f)
            )
            head.addOrReplaceChild(
                "right_front_fin",
                CubeListBuilder.create().texOffs(5, 17).addBox(-1f, -8f, 0f, 1f, 8f, 0f),
                PartPose.offsetAndRotation(-4f, 0f, -4f, 0f, (-Math.PI / 4).toFloat(), 0f)
            )
            head.addOrReplaceChild(
                "left_front_fin",
                CubeListBuilder.create().texOffs(1, 17).addBox(0f, -8f, 0f, 1f, 8f, 0f),
                PartPose.offsetAndRotation(4f, 0f, -4f, 0f, (Math.PI / 4).toFloat(), 0f)
            )
            head.addOrReplaceChild(
                "bottom_front_fin",
                CubeListBuilder.create().texOffs(15, 20).addBox(-4f, 0f, 0f, 8f, 1f, 0f),
                PartPose.offsetAndRotation(0f, 0f, -4f, (-Math.PI / 4).toFloat(), 0f, 0f)
            )
            head.addOrReplaceChild(
                "bottom_middle_fin",
                CubeListBuilder.create().texOffs(15, 20).addBox(-4f, 0f, 0f, 8f, 1f, 0f),
                PartPose.offset(0f, 0f, 0f)
            )
            head.addOrReplaceChild(
                "bottom_back_fin",
                CubeListBuilder.create().texOffs(15, 20).addBox(-4f, 0f, 0f, 8f, 1f, 0f),
                PartPose.offsetAndRotation(0f, 0f, 4f, (Math.PI / 4).toFloat(), 0f, 0f)
            )
            head.addOrReplaceChild(
                "right_back_fin",
                CubeListBuilder.create().texOffs(9, 17).addBox(-1f, -8f, 0f, 1f, 8f, 0f),
                PartPose.offsetAndRotation(-4f, 0f, 4f, 0f, (Math.PI / 4).toFloat(), 0f)
            )
            head.addOrReplaceChild(
                "left_back_fin",
                CubeListBuilder.create().texOffs(9, 17).addBox(0f, -8f, 0f, 1f, 8f, 0f),
                PartPose.offsetAndRotation(4f, 0f, 4f, 0f, (-Math.PI / 4).toFloat(), 0f)
            )
            return LayerDefinition.create(mesh, 32, 32)
        }
    }
}