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
import kotlin.math.abs
import kotlin.math.sin

class RavagerHeadModel(modelPart: ModelPart) : HeadModelBase() {
    override fun getId(): ResourceLocation = ID
    private val head: ModelPart = modelPart.getChild("head")
    private val mouth = head.getChild("mouth")


    override fun setupAnim(animTick: Float, g: Float, h: Float) {
        head.yRot = g * (Math.PI / 180.0).toFloat()
        head.xRot = h * (Math.PI / 180.0).toFloat()
        mouth.xRot = abs(sin(animTick * Math.PI.toFloat() * 0.085f)) * 0.4f
    }

    override fun renderToBuffer(poseStack: PoseStack, vertexConsumer: VertexConsumer, i: Int, j: Int, k: Int) {
        head.render(poseStack, vertexConsumer, i, j, k)
    }

    companion object {
        val ID = AllTheHeads.id("ravager")
        fun head(): LayerDefinition {
            val mesh = MeshDefinition()
            val head = mesh.root.addOrReplaceChild(
                "head", CubeListBuilder.create()
                    .texOffs(0, 0).addBox(-8f, -21f, -8f, 16f, 20f, 16f)
                    .texOffs(0, 0).addBox(-2f, -7f, -12f, 4f, 8f, 4f),
                PartPose.ZERO
            )
            head.addOrReplaceChild(
                "right_horn",
                CubeListBuilder.create().texOffs(74, 55).addBox(0f, -14f, -2f, 2f, 14f, 4f),
                PartPose.offsetAndRotation(-10f, -14f, -2f, 1.0995574f, 0f, 0f)
            )
            head.addOrReplaceChild(
                "left_horn",
                CubeListBuilder.create().texOffs(74, 55).mirror().addBox(0f, -14f, -2f, 2f, 14f, 4f),
                PartPose.offsetAndRotation(8f, -14f, -2f, 1.0995574f, 0f, 0f)
            )
            head.addOrReplaceChild(
                "mouth",
                CubeListBuilder.create().texOffs(0, 36).addBox(-8f, 0f, -16f, 16f, 3f, 16f),
                PartPose.offset(0f, -3f, 8f)
            )
            return LayerDefinition.create(mesh, 128, 128)
        }
    }
}