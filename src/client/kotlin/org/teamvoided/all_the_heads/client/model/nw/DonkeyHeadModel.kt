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

class DonkeyHeadModel(modelPart: ModelPart) : HeadModelBase() {
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
        val ID = AllTheHeads.id("donkey")
        fun head(): LayerDefinition {
            val mesh = MeshDefinition()
            val head = mesh.root.addOrReplaceChild(
                "head",
                CubeListBuilder.create().texOffs(0, 13).addBox(-3.0f, -11.0f, -2.0f, 6.0f, 5.0f, 7.0f, CubeDeformation.NONE),
                PartPose.ZERO
            )
            val cubeListBuilder2 = CubeListBuilder.create().texOffs(0, 12).addBox(-1.0f, -7.0f, 0.0f, 2.0f, 7.0f, 1.0f)
            head.addOrReplaceChild(
                "left_ear",
                cubeListBuilder2,
                PartPose.offsetAndRotation(
                    1.25f,
                    -10.0f,
                    4.0f,
                    (Math.PI / 12).toFloat(),
                    0.0f,
                    (Math.PI / 12).toFloat()
                )
            )
            head.addOrReplaceChild(
                "right_ear",
                cubeListBuilder2,
                PartPose.offsetAndRotation(
                    -1.25f,
                    -10.0f,
                    4.0f,
                    (Math.PI / 12).toFloat(),
                    0.0f,
                    (-Math.PI / 12).toFloat()
                )
            )
            return LayerDefinition.create(mesh, 64, 64)
        }
    }
}