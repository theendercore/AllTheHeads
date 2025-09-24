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

class HoglinHeadModel(modelPart: ModelPart) : HeadModelBase() {
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
        val ID = AllTheHeads.id("hoglin")
        fun head(): LayerDefinition {
            val mesh = MeshDefinition()
            val head = mesh.root.addOrReplaceChild(
                "head",
                CubeListBuilder.create().texOffs(61, 1).addBox(-7f, -3f, -9.5f, 14f, 6f, 19f),
                PartPose.offset(0f, -3f, 0f)
            )
            head.addOrReplaceChild(
                "right_ear",
                CubeListBuilder.create().texOffs(1, 1).addBox(-6f, -1f, 7.5f, 6f, 1f, 4f),
                PartPose.offsetAndRotation(-6f, -2f, -3f, 0f, 0f, (-Math.PI * 2.0 / 9.0).toFloat())
            )
            head.addOrReplaceChild(
                "left_ear",
                CubeListBuilder.create().texOffs(1, 6).addBox(0f, -1f, 7.5f, 6f, 1f, 4f),
                PartPose.offsetAndRotation(6f, -2f, -3f, 0f, 0f, (Math.PI * 2.0 / 9.0).toFloat())
            )
            head.addOrReplaceChild(
                "right_horn",
                CubeListBuilder.create().texOffs(10, 13).addBox(-1f, -11f, 8.5f, 2f, 11f, 2f),
                PartPose.offset(-7f, 2f, -12f)
            )
            head.addOrReplaceChild(
                "left_horn",
                CubeListBuilder.create().texOffs(1, 13).addBox(-1f, -11f, 8.5f, 2f, 11f, 2f),
                PartPose.offset(7f, 2f, -12f)
            )
            return LayerDefinition.create(mesh, 128, 64)
        }
    }
}