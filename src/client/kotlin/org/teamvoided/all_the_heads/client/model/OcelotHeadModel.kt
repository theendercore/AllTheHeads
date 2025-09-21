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

class OcelotHeadModel(modelPart: ModelPart) : HeadModelBase() {
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
        val ID = AllTheHeads.id("ocelot")
        fun head(): LayerDefinition {
            val mesh = MeshDefinition()
            val def = CubeDeformation.NONE
            mesh.root.addOrReplaceChild(
                "head",
                CubeListBuilder.create()
                    .addBox("main", -2.5f, -4f, -2.5f, 5f, 4f, 5f, def)
                    .addBox("nose", -1.5f, -2.001f, -3.5f, 3, 2, 2, def, 0, 24)
                    .addBox("ear1", -2f, -5f, 0.5f, 1, 1, 2, def, 0, 10)
                    .addBox("ear2", 1f, -5f, 0.5f, 1, 1, 2, def, 6, 10),
                PartPose.ZERO
            )
            return LayerDefinition.create(mesh, 64, 32)
        }
    }
}