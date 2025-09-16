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

class CowHeadModel(modelPart: ModelPart) : HeadModelBase() {
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
        val ID = AllTheHeads.id("allay")
        fun head(): LayerDefinition {
            val mesh = MeshDefinition()
            mesh.root.addOrReplaceChild(
                "head",
                CubeListBuilder.create()
                    .texOffs(0, 0)
                    .addBox(-4.0f, -4.0f, -6.0f, 8.0f, 8.0f, 6.0f)
                    .texOffs(22, 0)
                    .addBox("right_horn", -5.0f, -5.0f, -4.0f, 1.0f, 3.0f, 1.0f)
                    .texOffs(22, 0)
                    .addBox("left_horn", 4.0f, -5.0f, -4.0f, 1.0f, 3.0f, 1.0f),
                PartPose.offset(0.0f, 4.0f, -8.0f)
            )
            return LayerDefinition.create(mesh, 32, 32)
        }
    }
}