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

class ChestedHorseHeadModel(modelPart: ModelPart) : HeadModelBase() {
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
        val ID = AllTheHeads.id("chested_horse")
        fun head(): LayerDefinition {
            val mesh = MeshDefinition()
            val head = mesh.root.addOrReplaceChild(
                "head",
                CubeListBuilder.create().texOffs(0, 13).addBox(-3f, -5f, -3.5f, 6f, 5f, 7f),
                PartPose.ZERO
            )
            val earBox = CubeListBuilder.create().texOffs(0, 12).addBox(-1f, -10.3f, 3.5f, 2f, 7f, 1f)
            val rot = (Math.PI / 12).toFloat()
            head.addOrReplaceChild("left_ear", earBox, PartPose.offsetAndRotation(0.25f, 0f, 0f, rot, 0f, rot))
            head.addOrReplaceChild(
                "right_ear", earBox, PartPose.offsetAndRotation(-0.25f, 0f, 0f, rot, 0f, (-Math.PI / 12f).toFloat())
            )
            head.addOrReplaceChild(
                "upper_mouth",
                CubeListBuilder.create().texOffs(0, 25).addBox(-2f, -5f, -8.5f, 4f, 5f, 5f),
                PartPose.ZERO
            )
            return LayerDefinition.create(mesh, 64, 64)
        }
    }
}