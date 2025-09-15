package org.teamvoided.all_the_heads.client.model

import com.mojang.blaze3d.vertex.PoseStack
import com.mojang.blaze3d.vertex.VertexConsumer
import net.minecraft.client.model.geom.ModelPart
import net.minecraft.client.model.geom.PartPose
import net.minecraft.client.model.geom.builders.CubeDeformation
import net.minecraft.client.model.geom.builders.CubeListBuilder.create
import net.minecraft.client.model.geom.builders.LayerDefinition
import net.minecraft.client.model.geom.builders.MeshDefinition
import net.minecraft.resources.ResourceLocation
import org.teamvoided.all_the_heads.AllTheHeads

class AxolotlHeadModel(modelPart: ModelPart) : HeadModelBase() {
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
        val ID = AllTheHeads.id("axolotl")
        fun head(): LayerDefinition {
            val mesh = MeshDefinition()
            val def = CubeDeformation(0.001f)
            val head = mesh.root.addOrReplaceChild(
                "head", create().texOffs(0, 1).addBox(-4.0f, -3.0f, -5.0f, 8.0f, 5.0f, 5.0f, def),
                PartPose.offset(0.0f, -2.0f, 0.0f)
            )
            head.addOrReplaceChild(
                "top_gills", create().texOffs(3, 37).addBox(-4.0f, -3.0f, 0.0f, 8.0f, 3.0f, 0.0f, def),
                PartPose.offset(0.0f, -3.0f, -1.0f)
            )
            head.addOrReplaceChild(
                "left_gills", create().texOffs(0, 40).addBox(-3.0f, -5.0f, 0.0f, 3.0f, 7.0f, 0.0f, def),
                PartPose.offset(-4.0f, 0.0f, -1.0f)
            )
            head.addOrReplaceChild(
                "right_gills", create().texOffs(11, 40).addBox(0.0f, -5.0f, 0.0f, 3.0f, 7.0f, 0.0f, def),
                PartPose.offset(4.0f, 0.0f, -1.0f)
            )
            return LayerDefinition.create(mesh, 64, 64)
        }
    }
}