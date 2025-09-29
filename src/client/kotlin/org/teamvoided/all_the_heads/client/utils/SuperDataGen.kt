package org.teamvoided.all_the_heads.client.utils

import com.mojang.serialization.JsonOps
import net.minecraft.client.Minecraft
import org.teamvoided.all_the_heads.AllTheHeads.id
import org.teamvoided.all_the_heads.AllTheHeads.log
import org.teamvoided.all_the_heads.client.resources.SkullData
import org.teamvoided.creative_works.util.GSON
import java.io.File

fun run() {
    val exportDir = File("./export")
    if (exportDir.exists()) exportDir.deleteRecursively()
    exportDir.mkdirs()

    val test = SkullData(id("oh_god"))

    val client = Minecraft.getInstance()

    val ops = client.level!!.registryAccess().createSerializationContext(JsonOps.INSTANCE)
    SkullData.CODEC.encodeStart(ops, test)
        .ifError { log.error("Failed to encode! {}", it) }
        .ifSuccess { exportDir.resolve("test.json").writeText(GSON.toJson(it)) }

}