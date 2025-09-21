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

class BeeHeadModel(modelPart: ModelPart) : HeadModelBase() {
    override fun getId(): ResourceLocation = ID
    private val bone: ModelPart = modelPart.getChild("bone")
    val body: ModelPart = bone.getChild("body")
    private val rightWing: ModelPart = bone.getChild("right_wing")
    private val leftWing: ModelPart = bone.getChild("left_wing")
    private val frontLeg: ModelPart = bone.getChild("front_legs")
    private val midLeg: ModelPart = bone.getChild("middle_legs")
    private val backLeg: ModelPart = bone.getChild("back_legs")
    private val stinger: ModelPart = body.getChild("stinger")
    private val leftAntenna: ModelPart = body.getChild("left_antenna")
    private val rightAntenna: ModelPart = body.getChild("right_antenna")

    override fun setupAnim(f: Float, g: Float, h: Float) {
        bone.yRot = g * (Math.PI / 180.0).toFloat()
        bone.xRot = h * (Math.PI / 180.0).toFloat()

        rightWing.xRot = 0.0f
        leftAntenna.xRot = 0.0f
        rightAntenna.xRot = 0.0f
    }

    override fun renderToBuffer(poseStack: PoseStack, vertexConsumer: VertexConsumer, i: Int, j: Int, k: Int) {
        bone.render(poseStack, vertexConsumer, i, j, k)
    }

    companion object {
        val ID = AllTheHeads.id("bee")
        fun head(): LayerDefinition {
            val mesh = MeshDefinition()
            val bone = mesh.root.addOrReplaceChild("bone", CubeListBuilder.create(), PartPose.offset(0.0f, -3.0f, 0.0f))
            val body = bone.addOrReplaceChild(
                "body",
                CubeListBuilder.create().texOffs(0, 0).addBox(-3.5f, -4.0f, -5.0f, 7.0f, 7.0f, 10.0f),
                PartPose.ZERO
            )
            body.addOrReplaceChild(
                "stinger",
                CubeListBuilder.create().texOffs(26, 7).addBox(0.0f, -1.0f, 5.0f, 0.0f, 1.0f, 2.0f),
                PartPose.ZERO
            )
            body.addOrReplaceChild(
                "left_antenna",
                CubeListBuilder.create().texOffs(2, 0).addBox(1.5f, -2.0f, -3.0f, 1.0f, 2.0f, 3.0f),
                PartPose.offset(0.0f, -2.0f, -5.0f)
            )
            body.addOrReplaceChild(
                "right_antenna",
                CubeListBuilder.create().texOffs(2, 3).addBox(-2.5f, -2.0f, -3.0f, 1.0f, 2.0f, 3.0f),
                PartPose.offset(0.0f, -2.0f, -5.0f)
            )
            val cubeDeformation = CubeDeformation(0.001f)
            bone.addOrReplaceChild(
                "right_wing",
                CubeListBuilder.create().texOffs(0, 18).addBox(-9.0f, 0.0f, 0.0f, 9.0f, 0.0f, 6.0f, cubeDeformation),
                PartPose.offsetAndRotation(-1.5f, -4.0f, -3.0f, 0.0f, -0.2618f, 0.0f)
            )
            bone.addOrReplaceChild(
                "left_wing",
                CubeListBuilder.create().texOffs(0, 18).mirror()
                    .addBox(0.0f, 0.0f, 0.0f, 9.0f, 0.0f, 6.0f, cubeDeformation),
                PartPose.offsetAndRotation(1.5f, -4.0f, -3.0f, 0.0f, 0.2618f, 0.0f)
            )
            bone.addOrReplaceChild(
                "front_legs",
                CubeListBuilder.create().addBox("front_legs", -5.0f, 0.0f, 0.0f, 7, 2, 0, 26, 1),
                PartPose.offset(1.5f, 3.0f, -2.0f)
            )
            bone.addOrReplaceChild(
                "middle_legs",
                CubeListBuilder.create().addBox("middle_legs", -5.0f, 0.0f, 0.0f, 7, 2, 0, 26, 3),
                PartPose.offset(1.5f, 3.0f, 0.0f)
            )
            bone.addOrReplaceChild(
                "back_legs",
                CubeListBuilder.create().addBox("back_legs", -5.0f, 0.0f, 0.0f, 7, 2, 0, 26, 5),
                PartPose.offset(1.5f, 3.0f, 2.0f)
            )
            return LayerDefinition.create(mesh, 64, 64)
        }
    }
}