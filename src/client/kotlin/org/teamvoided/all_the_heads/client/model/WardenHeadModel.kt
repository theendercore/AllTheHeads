package org.teamvoided.all_the_heads.client.model

import com.mojang.blaze3d.vertex.PoseStack
import com.mojang.blaze3d.vertex.VertexConsumer
import net.minecraft.client.model.SkullModelBase
import net.minecraft.client.model.geom.ModelPart
import net.minecraft.client.model.geom.PartPose
import net.minecraft.client.model.geom.builders.CubeListBuilder
import net.minecraft.client.model.geom.builders.LayerDefinition
import net.minecraft.client.model.geom.builders.MeshDefinition
import org.teamvoided.all_the_heads.AllTheHeads
import kotlin.math.cos

class WardenHeadModel(modelPart: ModelPart) : SkullModelBase() {
     val head: ModelPart = modelPart.getChild("head")
     val rightTendril: ModelPart = this@WardenHeadModel.head.getChild("right_tendril")
     val leftTendril: ModelPart = this@WardenHeadModel.head.getChild("left_tendril")

    override fun setupAnim(f: Float, g: Float, h: Float) {
        this.head.yRot = g * (Math.PI / 180.0).toFloat()
        this.head.xRot = h * (Math.PI / 180.0).toFloat()
        // (ender) animate tendrils later
       /* val h: Float = warden.getTendrilAnimation(g) * (cos(f * 2.25) * Math.PI * 0.1f).toFloat()
        this.leftTendril.xRot = h
        this.rightTendril.xRot = -h*/
    }

    override fun renderToBuffer(poseStack: PoseStack, vertexConsumer: VertexConsumer, i: Int, j: Int, k: Int) {
        this.head.render(poseStack, vertexConsumer, i, j, k)
    }

    companion object {
        val ID = AllTheHeads.id("warden")
        fun head(): LayerDefinition {
            val mesh = MeshDefinition()
            val head = mesh.root.addOrReplaceChild(
                "head",
                CubeListBuilder.create().texOffs(0, 32).addBox(-8.0f, -16.0f, -5.0f, 16.0f, 16.0f, 10.0f),
                PartPose.ZERO
            );
            head.addOrReplaceChild(
                "right_tendril",
                CubeListBuilder.create().texOffs(52, 32).addBox(-16.0f, -13.0f, 0.0f, 16.0f, 16.0f, 0.0f),
                PartPose.offset(-8.0f, -12.0f, 0.0f)
            )
            head.addOrReplaceChild(
                "left_tendril",
                CubeListBuilder.create().texOffs(58, 0).addBox(0.0f, -13.0f, 0.0f, 16.0f, 16.0f, 0.0f),
                PartPose.offset(8.0f, -12.0f, 0.0f)
            )
            return LayerDefinition.create(mesh, 128, 128)
        }
    }
}