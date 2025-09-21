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

class LlamaHeadModel(modelPart: ModelPart) : HeadModelBase() {
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
        val ID = AllTheHeads.id("llama")
        fun head(): LayerDefinition {
            val mesh = MeshDefinition()
            val def = CubeDeformation.NONE
            mesh.root.addOrReplaceChild(
                "head",
                CubeListBuilder.create()
                    .texOffs(0, 0)
                    .addBox(-2.0F, -14.0F, -10.0F, 4.0F, 4.0F, 9.0F, def)
                    .texOffs(0, 14)
                    .addBox("neck", -4.0F, -16.0F, -6.0F, 8.0F, 18.0F, 6.0F, def)
                    .texOffs(17, 0)
                    .addBox("ear", -4.0F, -19.0F, -4.0F, 3.0F, 3.0F, 2.0F, def)
                    .texOffs(17, 0)
                    .addBox("ear", 1.0F, -19.0F, -4.0F, 3.0F, 3.0F, 2.0F, def),
                PartPose.offset(0.0F, -2.0F, 0.0F)
            );
            return LayerDefinition.create(mesh, 128, 64)
        }
    }
}