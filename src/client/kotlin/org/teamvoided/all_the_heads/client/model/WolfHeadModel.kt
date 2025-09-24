package org.teamvoided.all_the_heads.client.model

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
import org.teamvoided.all_the_heads.client.model.utils.HeadModelBase

class WolfHeadModel(modelPart: ModelPart) : HeadModelBase() {
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
        val ID = AllTheHeads.id("wolf")
        fun head(): LayerDefinition {
            val mesh = MeshDefinition()
            val def = CubeDeformation.NONE
            mesh.root.addOrReplaceChild(
                "head",
                CubeListBuilder.create()
                    .texOffs(0, 0).addBox(-3f, -6f, -2f, 6f, 6f, 4f, def)
                    .texOffs(16, 14).addBox(-3f, -8f, 0f, 2f, 2f, 1f, def)
                    .texOffs(16, 14).addBox(1f, -8f, 0f, 2f, 2f, 1f, def)
                    .texOffs(0, 10).addBox(-1.5f, -3.001f, -5f, 3f, 3f, 4f, def),
                PartPose.ZERO
            )
            return LayerDefinition.create(mesh, 64, 32)
        }
    }
}
