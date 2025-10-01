package org.teamvoided.all_the_heads.client.model

//import com.mojang.blaze3d.vertex.PoseStack
//import com.mojang.blaze3d.vertex.VertexConsumer
//import net.minecraft.client.model.geom.ModelPart
//import net.minecraft.client.model.geom.PartPose
//import net.minecraft.client.model.geom.builders.CubeDeformation
//import net.minecraft.client.model.geom.builders.CubeListBuilder
//import net.minecraft.client.model.geom.builders.LayerDefinition
//import net.minecraft.client.model.geom.builders.MeshDefinition
//import net.minecraft.resources.ResourceLocation
//import org.teamvoided.all_the_heads.AllTheHeads
//import org.teamvoided.all_the_heads.client.model.utils.HeadModelBase
//
//class FullSilverfishModel(modelPart: ModelPart) : HeadModelBase() {
//    override fun getId(): ResourceLocation = ID
//    private val head: ModelPart = modelPart.getChild("head")
//
//    override fun setupAnim(f: Float, g: Float, h: Float) {
//        head.yRot = g * (Math.PI / 180.0).toFloat()
//        head.xRot = h * (Math.PI / 180.0).toFloat()
//    }
//
//    override fun renderToBuffer(poseStack: PoseStack, vertexConsumer: VertexConsumer, i: Int, j: Int, k: Int) {
//        head.render(poseStack, vertexConsumer, i, j, k)
//    }
//
//    companion object {
//        val ID = AllTheHeads.id("full_silverfish")
//        fun head(): LayerDefinition {
//            val mesh = MeshDefinition()
//            val root = mesh.root.addOrReplaceChild(
//                "head",
//                CubeListBuilder.create().texOffs(0, 0)
//                    .addBox(0f, 0f, 0f, 0.0f, 0.0f, 0.0f, CubeDeformation(0.0f)),
//                PartPose.offset(0f, -24f, 0f)
//            )
//            val bodySizes = arrayOf(
//                intArrayOf(3, 2, 2),
//                intArrayOf(4, 3, 2),
//                intArrayOf(6, 4, 3),
//                intArrayOf(3, 3, 3),
//                intArrayOf(2, 2, 3),
//                intArrayOf(2, 1, 2),
//                intArrayOf(1, 1, 2)
//            )
//            val bodyTex = arrayOf(
//                intArrayOf(0, 0),
//                intArrayOf(0, 4),
//                intArrayOf(0, 9),
//                intArrayOf(0, 16),
//                intArrayOf(0, 22),
//                intArrayOf(11, 0),
//                intArrayOf(13, 4)
//            )
//
//
//            val fs = FloatArray(7)
//            var f = -3.5f
//
//            for (i in 0..6) {
//                root.addOrReplaceChild(
//                    "segment$i",
//                    CubeListBuilder.create()
//                        .texOffs(bodyTex[i][0], bodyTex[i][1])
//                        .addBox(
//                            bodySizes[i][0] * -0.5f,
//                            0.0f,
//                            bodySizes[i][2] * -0.5f,
//                            bodySizes[i][0].toFloat(),
//                            bodySizes[i][1].toFloat(),
//                            bodySizes[i][2].toFloat()
//                        ),
//                    PartPose.offset(0.0f, (24 - bodySizes[i][1]).toFloat(), f)
//                )
//                fs[i] = f
//                if (i < 6) {
//                    f += (bodySizes[i][2] + bodySizes[i + 1][2]) * 0.5f
//                }
//            }
//
//            root.addOrReplaceChild(
//                "layer0",
//                CubeListBuilder.create().texOffs(20, 0).addBox(
//                    -5.0f,
//                    0.0f,
//                    bodySizes[2][2] * -0.5f,
//                    10.0f,
//                    8.0f,
//                    bodySizes[2][2].toFloat()
//                ),
//                PartPose.offset(0.0f, 16.0f, fs[2])
//            )
//            root.addOrReplaceChild(
//                "layer1",
//                CubeListBuilder.create().texOffs(20, 11).addBox(
//                    -3.0f,
//                    0.0f,
//                    bodySizes[4][2] * -0.5f,
//                    6.0f,
//                    4.0f,
//                    bodySizes[4][2].toFloat()
//                ),
//                PartPose.offset(0.0f, 20.0f, fs[4])
//            )
//            root.addOrReplaceChild(
//                "layer2",
//                CubeListBuilder.create().texOffs(20, 18).addBox(
//                    -3.0f,
//                    0.0f,
//                    bodySizes[4][2] * -0.5f,
//                    6.0f,
//                    5.0f,
//                    bodySizes[1][2].toFloat()
//                ),
//                PartPose.offset(0.0f, 19.0f, fs[1])
//            )
//            return LayerDefinition.create(mesh, 64, 32)
//        }
//    }
//}