package org.teamvoided.all_the_heads.client.model

import com.mojang.blaze3d.vertex.PoseStack
import com.mojang.blaze3d.vertex.VertexConsumer
import net.minecraft.client.model.geom.ModelPart
import net.minecraft.client.model.geom.PartPose
import net.minecraft.client.model.geom.builders.CubeListBuilder
import net.minecraft.client.model.geom.builders.LayerDefinition
import net.minecraft.client.model.geom.builders.MeshDefinition
import net.minecraft.client.model.geom.builders.PartDefinition
import net.minecraft.resources.ResourceLocation
import org.teamvoided.all_the_heads.AllTheHeads
import org.teamvoided.all_the_heads.client.model.utils.HeadModelBase
import kotlin.math.abs
import kotlin.math.sin

class FrogHeadModel(modelPart: ModelPart) : HeadModelBase() {
    override fun getId(): ResourceLocation = ID
    private val head: ModelPart = modelPart.getChild("head")
    private val croakingBody: ModelPart = head.getChild("croaking_body")

    override fun setupAnim(f: Float, g: Float, h: Float) {
        head.yRot = g * (Math.PI / 180.0).toFloat()
        head.xRot = h * (Math.PI / 180.0).toFloat()
        val scale = abs(sin(f * 0.2f)) * 1.5f
        croakingBody.xScale = scale * 0.85f
        croakingBody.yScale = scale
        croakingBody.zScale = scale
    }

    override fun renderToBuffer(poseStack: PoseStack, vertexConsumer: VertexConsumer, i: Int, j: Int, k: Int) {
        head.render(poseStack, vertexConsumer, i, j, k)
    }

    companion object {
        val ID = AllTheHeads.id("frog")
        fun head(): LayerDefinition {
            val mesh = MeshDefinition()

            val head: PartDefinition = mesh.root.addOrReplaceChild(
                "head",
                CubeListBuilder.create()
                    .texOffs(3, 1).addBox(-3.5f, -3f, -4.5f, 7f, 3f, 9f)
                    .texOffs(23, 22).addBox(-3.5f, -1f, -4.5f, 7f, 0f, 9f),
                PartPose.ZERO
            )
            head.addOrReplaceChild(
                "croaking_body",
                CubeListBuilder.create().texOffs(26, 5)
                    .addBox(-3.5f, -2.01f, -4f, 7f, 2f, 3f),
                PartPose.ZERO
            )


            val trueHead = head.addOrReplaceChild(
                "true_head",
                CubeListBuilder.create().texOffs(23, 13).addBox(-3.5f, -1f, -4.5f, 7f, 0f, 9f).texOffs(0, 13)
                    .addBox(-3.5f, -3f, -4.5f, 7f, 3f, 9f),
                PartPose.offset(0f, -2f, 0f)
            )
            val eyes =
                trueHead.addOrReplaceChild("eyes", CubeListBuilder.create(), PartPose.offset(0f, -3f, -2f))
            eyes.addOrReplaceChild(
                "right_eye",
                CubeListBuilder.create().texOffs(0, 0).addBox(-1.5f, -2f, -1.5f, 3f, 2f, 3f),
                PartPose.offset(-2f, 0f, 0f)
            )
            eyes.addOrReplaceChild(
                "left_eye",
                CubeListBuilder.create().texOffs(0, 5).addBox(-1.5f, -2f, -1.5f, 3f, 2f, 3f),
                PartPose.offset(2f, 0f, 0f)
            )
            return LayerDefinition.create(mesh, 48, 48)
        }
    }
}