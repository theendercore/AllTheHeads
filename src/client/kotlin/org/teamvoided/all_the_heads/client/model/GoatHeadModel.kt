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
                    .addBox(
                        "right ear", -5.5f, -11f, 0f,
                        3f, 2f, 1f
                    )
                    .texOffs(2, 61)
                    .mirror()
                    .addBox(
                        "left ear", 2.5f, -11f, 0f,
                        3f, 2f, 1f
                    )
                    .texOffs(23, 52)
                    .addBox(
                        "goatee", 0f, -3f, -4f,
                        0f, 7f, 5f
                    ),
                PartPose.ZERO
            )
            head.addOrReplaceChild(
                "left_horn",
                CubeListBuilder.create().texOffs(12, 55).addBox(
                    .49f, -16f, 0f,
                    2f, 7f, 2f
                ),
                PartPose.ZERO
            )
            head.addOrReplaceChild(
                "right_horn",
                CubeListBuilder.create().texOffs(12, 55).addBox(
                    -2.49f, -16f, 0f,
                    2f, 7f, 2f
                ),
                PartPose.ZERO
            )
            head.addOrReplaceChild(
                "nose",
                CubeListBuilder.create().texOffs(34, 46).addBox(
                    -3f, -4f, -8f,
                    5f, 7f, 10f
                ),
                PartPose.offsetAndRotation(.5f, -8f, 2f, 0.9599f, 0f, 0f)
            )
            return LayerDefinition.create(mesh, 64, 64)
        }
    }
}