package org.teamvoided.all_the_heads.utils

import java.io.File


val list = listOf(
    "Slime",
    "Sniffer",
    "Strider",
    "Tadpole",
    "Tropical Fish",
    "Villager",
    "Witch",
    "Wolf",
)

fun main() {
    val rootDir = File("src/client/kotlin/org/teamvoided/all_the_heads/client/model/nw")

    list.forEach {
        val newFile = rootDir.resolve("${it.replace(" ", "")}HeadModel.kt")
        newFile.createNewFile()
        newFile.writeText(fileText(it))

    }

}

fun fileText(name: String): String {
    return """
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

class ${name.replace(" ", "")}HeadModel(modelPart: ModelPart) : HeadModelBase() {
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
        val ID = AllTheHeads.id("${name.replace(" ", "_").lowercase()}")
        fun head(): LayerDefinition {
            val mesh = MeshDefinition()
            mesh.root.addOrReplaceChild(
                "head",
                CubeListBuilder.create().texOffs(0, 0)
                    .addBox(-2.5f, -5.0f, -2.5f, 5.0f, 5.0f, 5.0f, CubeDeformation(0.0f)),
                PartPose.ZERO
            )
            return LayerDefinition.create(mesh, 32, 32)
        }
    }
}
"""
}