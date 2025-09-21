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

class PandaHeadModel(modelPart: ModelPart) : HeadModelBase() {
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
        val ID = AllTheHeads.id("panda")
        fun head(): LayerDefinition {
            val mesh = MeshDefinition()
            mesh.root.addOrReplaceChild(
                "head",
                CubeListBuilder.create()
                    .texOffs(0, 6)
                    .addBox(-6.5F, -5.0F, -4.0F, 13.0F, 10.0F, 9.0F)
                    .texOffs(45, 16)
                    .addBox("nose", -3.5F, 0.0F, -6.0F, 7.0F, 5.0F, 2.0F)
                    .texOffs(52, 25)
                    .addBox("left_ear", 3.5F, -8.0F, -1.0F, 5.0F, 4.0F, 1.0F)
                    .texOffs(52, 25)
                    .addBox("right_ear", -8.5F, -8.0F, -1.0F, 5.0F, 4.0F, 1.0F),
                PartPose.offset(0.0F, -5F, 0.0F)
            )
            return LayerDefinition.create(mesh, 64, 64)
        }
    }
}