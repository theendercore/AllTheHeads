package org.teamvoided.all_the_heads.client.model

import com.mojang.blaze3d.vertex.PoseStack
import com.mojang.blaze3d.vertex.VertexConsumer
import net.minecraft.client.model.geom.ModelPart
import net.minecraft.client.model.geom.PartPose
import net.minecraft.client.model.geom.builders.*
import net.minecraft.resources.ResourceLocation
import org.teamvoided.all_the_heads.AllTheHeads
import org.teamvoided.all_the_heads.client.model.utils.HeadModelBase

class IllagerHeadModel(modelPart: ModelPart) : HeadModelBase() {
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
        val ID = AllTheHeads.id("illager")
        fun head(): LayerDefinition {
            val mesh = MeshDefinition()
            val head = mesh.root.addOrReplaceChild(
                "head",
                CubeListBuilder.create().texOffs(0, 0).addBox(-4f, -10f, -4f, 8f, 10f, 8f),
                PartPose.ZERO
            )
            head.addOrReplaceChild(
                "hat",
                CubeListBuilder.create().texOffs(32, 0).addBox(-4f, -10f, -4f, 8f, 12f, 8f, CubeDeformation(0.45f)),
                PartPose.ZERO
            )
            head.addOrReplaceChild(
                "nose",
                CubeListBuilder.create().texOffs(24, 0).addBox(-1f, -3f, -6f, 2f, 4f, 2f),
                PartPose.ZERO
            )
            return LayerDefinition.create(mesh, 64, 64)
        }
    }
}