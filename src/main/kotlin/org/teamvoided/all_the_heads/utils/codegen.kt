@file:Suppress("unused", "UnusedVariable")

package org.teamvoided.all_the_heads.utils

import java.io.File


val list = listOf(
    "entity Solid",
    "entity Cutout",
    "entity Cutout No Cull",
    "entity Cutout No Cull Z Offset",
    "item Entity Translucent Cull",
    "entity Translucent Cull",
    "entity Translucent",
    "entity Translucent Emissive",
    "entity Smooth Cutout",
    "beacon Beam",
    "entity Decal",
    "entity Shadow",
    "eyes",
    "breeze Eyes",
    "breeze Wind",
    "energy Swirl",
    "armor Entity Glint",
    "entity Glint",
    "end Portal",
    "end Gateway",
    "lines",
    "line Strip",
)


fun main() {
    val rootDir = File("src/client/kotlin/org/teamvoided/all_the_heads/client/")

    val newFile = rootDir.resolve("Types.kt")
    newFile.createNewFile()
    newFile.writeText(modelsFile())
}

@Suppress("CanBeVal")
fun modelsFile(): String {
    var text = "\n\n\n"
    var text2 = "\n\n\n"
    list.forEach { rawName ->
        val uppercase = rawName.replace(" ", "_").uppercase()
        val lowercase = uppercase.lowercase()
        val fn = rawName.replace(" ", "")
//        val ENTITY_CUTOUT_NO_CULL = addType(mc("entity_cutout_no_cull"), ::entityCutoutNoCull)
        text+= "val $uppercase = addType(mc(\"$lowercase\"), ::$fn)\n"
    }

    return text + text2
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