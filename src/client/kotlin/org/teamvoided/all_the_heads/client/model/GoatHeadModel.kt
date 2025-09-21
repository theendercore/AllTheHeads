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

class GoatHeadModel(modelPart: ModelPart) : HeadModelBase() {
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
        val ID = AllTheHeads.id("goat")
        fun head(): LayerDefinition {
            val mesh = MeshDefinition()

            val head = mesh.root.addOrReplaceChild(
                "head",
                CubeListBuilder.create()
                    .texOffs(2, 61)
                    .addBox("right ear", -6.0f, -11.0f, -10.0f, 3.0f, 2.0f, 1.0f)
                    .texOffs(2, 61)
                    .mirror()
                    .addBox("left ear", 2.0f, -11.0f, -10.0f, 3.0f, 2.0f, 1.0f)
                    .texOffs(23, 52)
                    .addBox("goatee", -0.5f, -3.0f, -14.0f, 0.0f, 7.0f, 5.0f),
                PartPose.offset(0.0f, 0.0f, 0.0f)
            )
            head.addOrReplaceChild(
                "left_horn",
                CubeListBuilder.create().texOffs(12, 55).addBox(-0.01f, -16.0f, -10.0f, 2.0f, 7.0f, 2.0f),
                PartPose.offset(0.0f, 0.0f, 0.0f)
            )
            head.addOrReplaceChild(
                "right_horn",
                CubeListBuilder.create().texOffs(12, 55).addBox(-2.99f, -16.0f, -10.0f, 2.0f, 7.0f, 2.0f),
                PartPose.offset(0.0f, 0.0f, 0.0f)
            )
            head.addOrReplaceChild(
                "nose",
                CubeListBuilder.create().texOffs(34, 46).addBox(-3.0f, -4.0f, -8.0f, 5.0f, 7.0f, 10.0f),
                PartPose.offsetAndRotation(0.0f, -8.0f, -8.0f, 0.9599f, 0.0f, 0.0f)
            )
            return LayerDefinition.create(mesh, 64, 64)
        }
    }
}