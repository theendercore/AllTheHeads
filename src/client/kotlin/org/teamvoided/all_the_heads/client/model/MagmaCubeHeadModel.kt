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
import kotlin.math.abs
import kotlin.math.sin

class MagmaCubeHeadModel(modelPart: ModelPart) : HeadModelBase() {
    override fun getId(): ResourceLocation = ID
    private val head: ModelPart = modelPart.getChild("head")
    private val bodyCubes = Array(8) { head.getChild(getSegmentName(it)) }

    override fun setupAnim(animTick: Float, g: Float, h: Float) {
        head.yRot = g * (Math.PI / 180.0).toFloat()
        head.xRot = h * (Math.PI / 180.0).toFloat()

        val i = abs(sin(animTick * Math.PI.toFloat() * 0.08f)) * 0.5f
        for (idx in bodyCubes.indices) {
            bodyCubes[idx]!!.y = -(4 - idx) * i * 1.7f
        }
    }

    override fun renderToBuffer(poseStack: PoseStack, vertexConsumer: VertexConsumer, i: Int, j: Int, k: Int) {
        head.render(poseStack, vertexConsumer, i, j, k)
    }

    companion object {
        val ID = AllTheHeads.id("magma_cube")
        fun head(): LayerDefinition {
            val mesh = MeshDefinition()
            val head = mesh.root.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.offset(0f, -24f, 0f))

            for (i in 0..7) {
                var j = 0
                var k = i

                if (i == 2) {
                    j = 24
                    k = 10
                } else if (i == 3) {
                    j = 24
                    k = 19
                }

                head.addOrReplaceChild(
                    getSegmentName(i),
                    CubeListBuilder.create().texOffs(j, k).addBox(-4.0f, (16 + i).toFloat(), -4.0f, 8.0f, 1.0f, 8.0f),
                    PartPose.ZERO
                )
            }

            head.addOrReplaceChild(
                "inside_cube",
                CubeListBuilder.create().texOffs(0, 16).addBox(-2.0f, 18.0f, -2.0f, 4.0f, 4.0f, 4.0f),
                PartPose.ZERO
            )

            return LayerDefinition.create(mesh, 64, 32)
        }

        fun getSegmentName(i: Int): String = "cube$i"
    }
}