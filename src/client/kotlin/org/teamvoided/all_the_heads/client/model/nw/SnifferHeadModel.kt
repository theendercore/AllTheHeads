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

class SnifferHeadModel(modelPart: ModelPart) : HeadModelBase() {
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
        val ID = AllTheHeads.id("sniffer")
        fun head(): LayerDefinition {
            val mesh = MeshDefinition()
            val head = mesh.root.addOrReplaceChild(
                "head",
                CubeListBuilder.create()
                    .texOffs(8, 15)
                    .addBox(-6.5f, -7.5f, -11.5f, 13.0f, 18.0f, 11.0f, CubeDeformation(0.0f))
                    .texOffs(8, 4)
                    .addBox(-6.5f, 7.5f, -11.5f, 13.0f, 0.0f, 11.0f, CubeDeformation(0.0f)),
                PartPose.offset(0f, -7.5f, 0f)
            )
            head.addOrReplaceChild(
                "left_ear",
                CubeListBuilder.create().texOffs(2, 0)
                    .addBox(0.0f, 0.0f, -3.0f, 1.0f, 19.0f, 7.0f, CubeDeformation(0.0f)),
                PartPose.offset(6.51f, -7.5f, -4.51f)
            )
            head.addOrReplaceChild(
                "right_ear",
                CubeListBuilder.create().texOffs(48, 0)
                    .addBox(-1.0f, 0.0f, -3.0f, 1.0f, 19.0f, 7.0f, CubeDeformation(0.0f)),
                PartPose.offset(-6.51f, -7.5f, -4.51f)
            )
            head.addOrReplaceChild(
                "nose",
                CubeListBuilder.create().texOffs(10, 45)
                    .addBox(-6.5f, -2.0f, -9.0f, 13.0f, 2.0f, 9.0f, CubeDeformation(0.0f)),
                PartPose.offset(0.0f, -4.5f, -11.5f)
            )
            head.addOrReplaceChild(
                "lower_beak",
                CubeListBuilder.create().texOffs(10, 57)
                    .addBox(-6.5f, -7.0f, -8.0f, 13.0f, 12.0f, 9.0f, CubeDeformation(0.0f)),
                PartPose.offset(0.0f, 2.5f, -12.5f)
            )
            return LayerDefinition.create(mesh, 192, 192)
        }
    }
}
