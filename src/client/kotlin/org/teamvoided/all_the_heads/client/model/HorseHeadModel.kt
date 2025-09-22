package org.teamvoided.all_the_heads.client.model

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
import org.teamvoided.all_the_heads.client.model.utils.HeadModelBase

class HorseHeadModel(modelPart: ModelPart) : HeadModelBase() {
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
        val ID = AllTheHeads.id("horse")
        fun head(): LayerDefinition {
            val mesh = MeshDefinition()
            val head = mesh.root.addOrReplaceChild(
                "head",
                CubeListBuilder.create().texOffs(0, 13)
                    .addBox(-3f, -5f, -3.5f, 6f, 5f, 7f, CubeDeformation.NONE),
                PartPose.ZERO
            )
            head.addOrReplaceChild(
                "left_ear",
                CubeListBuilder.create().texOffs(19, 16)
                    .addBox(0.55f, -7f, 2.5f, 2f, 3f, 1f, CubeDeformation(-0.001f)),
                PartPose.ZERO
            )
            head.addOrReplaceChild(
                "right_ear",
                CubeListBuilder.create().texOffs(19, 16)
                    .addBox(-2.55f, -7f, 2.5f, 2f, 3f, 1f, CubeDeformation(-0.001f)),
                PartPose.ZERO
            )
            head.addOrReplaceChild(
                "upper_mouth",
                CubeListBuilder.create().texOffs(0, 25).addBox(-2f, -5f, -8.5f, 4f, 5f, 5f),
                PartPose.ZERO
            )
            head.addOrReplaceChild(
                "mane",
                CubeListBuilder.create().texOffs(56, 36).addBox(-1f, -5f, 3.51f, 2f, 5f, 2f),
                PartPose.ZERO
            )
            return LayerDefinition.create(mesh, 64, 64)
        }
    }
}