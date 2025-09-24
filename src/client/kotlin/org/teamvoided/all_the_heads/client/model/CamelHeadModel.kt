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

class CamelHeadModel(modelPart: ModelPart) : HeadModelBase() {
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
        val ID = AllTheHeads.id("camel")
        fun head(): LayerDefinition {
            val mesh = MeshDefinition()
            val head: PartDefinition = mesh.root.addOrReplaceChild(
                "head",
                CubeListBuilder.create()
                    .texOffs(21, 0)
                    .addBox(-3.5f, -7f, -3.5f, 7f, 7f, 7f)
                    .texOffs(50, 0)
                    .addBox(-2.5f, -7f, -9.5f, 5f, 5f, 6f),
                PartPose.ZERO
            )
            head.addOrReplaceChild(
                "neck",
                CubeListBuilder.create().texOffs(72, 24).addBox(-3.5f, 0f, -3.5f, 7f, 0.01f, 7f),
                PartPose.ZERO
            )
            head.addOrReplaceChild(
                "left_ear",
                CubeListBuilder.create().texOffs(45, 0).addBox(-0.5f, 0.5f, -1f, 3f, 1f, 2f),
                PartPose.offset(2.5f, -7f, 2f)
            )
            head.addOrReplaceChild(
                "right_ear",
                CubeListBuilder.create().texOffs(67, 0).addBox(-2.5f, 0.5f, -1f, 3f, 1f, 2f),
                PartPose.offset(-2.5f, -7f, 2f)
            )
            return LayerDefinition.create(mesh, 128, 128)
        }
    }
}