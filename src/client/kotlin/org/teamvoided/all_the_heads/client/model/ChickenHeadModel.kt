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

class ChickenHeadModel(modelPart: ModelPart) : HeadModelBase() {
    override fun getId(): ResourceLocation = ID
    private val head: ModelPart = modelPart.getChild("head")
    private val beak = modelPart.getChild("beak")
    private val redThing = modelPart.getChild("red_thing")

    override fun setupAnim(f: Float, g: Float, h: Float) {
        head.yRot = g * (Math.PI / 180.0).toFloat()
        head.xRot = h * (Math.PI / 180.0).toFloat()

        beak.xRot = this.head.xRot
        beak.yRot = this.head.yRot
        redThing.xRot = this.head.xRot
        redThing.yRot = this.head.yRot
    }

    override fun renderToBuffer(poseStack: PoseStack, vertexConsumer: VertexConsumer, i: Int, j: Int, k: Int) {
        head.render(poseStack, vertexConsumer, i, j, k)
        beak.render(poseStack, vertexConsumer, i, j, k)
        redThing.render(poseStack, vertexConsumer, i, j, k)
    }

    companion object {
        val ID = AllTheHeads.id("chicken")
        fun head(): LayerDefinition {
            val mesh = MeshDefinition()
            val root = mesh.root
            root.addOrReplaceChild(
                "head",
                CubeListBuilder.create().texOffs(0, 0).addBox(-2.0f, -6.0f, -2.0f, 4.0f, 6.0f, 3.0f),
                PartPose.ZERO
            )
            root.addOrReplaceChild(
                "beak",
                CubeListBuilder.create().texOffs(14, 0).addBox(-2.0f, -4.0f, -4.0f, 4.0f, 2.0f, 2.0f),
                PartPose.ZERO
            )
            root.addOrReplaceChild(
                "red_thing",
                CubeListBuilder.create().texOffs(14, 4).addBox(-1.0f, -2.0f, -3.0f, 2.0f, 2.0f, 2.0f),
                PartPose.ZERO
            )
            return LayerDefinition.create(mesh, 64, 32)
        }
    }
}