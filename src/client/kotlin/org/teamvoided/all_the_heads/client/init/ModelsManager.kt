package org.teamvoided.all_the_heads.client.init

import net.minecraft.client.model.SkullModelBase
import net.minecraft.client.renderer.RenderType
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.level.block.SkullBlock
import org.teamvoided.all_the_heads.AllTheHeads
import org.teamvoided.all_the_heads.client.AllTheHeadsClient
import org.teamvoided.all_the_heads.client.data.SkullRenderData
import org.teamvoided.all_the_heads.client.model.AllayHeadModel
import org.teamvoided.all_the_heads.client.model.PhantomHeadModel
import org.teamvoided.all_the_heads.client.model.TurtleHeadModel
import org.teamvoided.all_the_heads.client.model.WardenHeadModel

object ModelsManager {
    @JvmField
    var VANILLA_MODEL_ACCESS = mapOf<SkullBlock.Type, SkullModelBase>()

    @JvmField
    var BUILT_IN_MODELS = mutableMapOf<ResourceLocation, SkullModelBase>()

    val models = mapOf(
        "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvY2MwMzg5MTc3ZGJhYTkyZjBkNWZmZGY4NDg4NjJjN2Y5YjM2ZGYyMjJmYmZkNzM3ZTI2MzlkYzMwNTllMGNmMyJ9fX0=" to SkullRenderData(
            getBuiltIn(AllayHeadModel.ID), basicType("textures/entity/allay/allay.png")
        ),
        "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjk1MzhmMjgzMGM0ZGVhNjk5NmVkNzQ0Nzg1NTA0ZTMyZTBlMjBkODY2M2VkYWI2YjAyMjJmMmMwMjIwNzdiZCJ9fX0=" to SkullRenderData(
            getBuiltIn(AllayHeadModel.ID), basicType("textures/entity/illager/vex.png")
        ),
        "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNGE0ZTUxOGUxNmU0YjVjMTE0YWNiZDljNjFjZDE4MjkyZGE5ZWY2MDU1MGE0ZmNhZTI3ZDM5YWUyOTNlNDc3YSJ9fX0=" to SkullRenderData(
            getBuiltIn(AllayHeadModel.ID), basicType("textures/entity/illager/vex_charging.png")
        ),
        "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2U5NTE1M2VjMjMyODRiMjgzZjAwZDE5ZDI5NzU2ZjI0NDMxM2EwNjFiNzBhYzAzYjk3ZDIzNmVlNTdiZDk4MiJ9fX0=" to SkullRenderData(
            getBuiltIn(PhantomHeadModel.ID), basicType("textures/entity/phantom.png")
        ),
        "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzA0OTMxMjAwYWQ0NjBiNjUwYTE5MGU4ZDQxMjI3YzM5OTlmYmViOTMzYjUxY2E0OWZkOWU1OTIwZDFmOGU3ZCJ9fX0=" to SkullRenderData(
            getBuiltIn(TurtleHeadModel.ID), basicType("textures/entity/turtle/big_sea_turtle.png")
        ),
        "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjJmMzg3OWI3MzcxMjc0ODVlYjM1ZGRlZTc0OGQwNmNmOTE0YjE5M2Q5Nzc1M2FlMzRlOTIyMzA4NDI4MzFmYiJ9fX0=" to SkullRenderData(
            getBuiltIn(WardenHeadModel.ID), basicType("textures/entity/warden/warden.png")
        ),
        "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMmRmMDMxMjhiMDAyYTcwNzA4ZDY4MjVlZDZjZjU0ZGRmNjk0YjM3NjZkNzhkNTY0OTAzMGIxY2I4YjM0YzZmYSJ9fX0=" to SkullRenderData(
            getVanilla(SkullBlock.Types.PIGLIN), basicType("textures/entity/piglin/zombified_piglin.png")
        ),
    )

    fun getVanilla(type: SkullBlock.Type): () -> SkullModelBase {
        if (type !is SkullBlock.Types) {
            AllTheHeadsClient.sendError("Supplied non vanilla SkullType! $type")
            return { VANILLA_MODEL_ACCESS[SkullBlock.Types.PLAYER]!! }
        }
        return { VANILLA_MODEL_ACCESS[type]!! }
    }

    fun getBuiltIn(id: ResourceLocation): () -> SkullModelBase = {
        val model = BUILT_IN_MODELS[id]
        if (model != null) model
        else {
            AllTheHeadsClient.sendError("Failed to load model for Id! $id")
            VANILLA_MODEL_ACCESS[SkullBlock.Types.PLAYER]!!
        }
    }

    fun basicType(texture: String): RenderType = RenderType.entityCutoutNoCullZOffset(AllTheHeads.tryParseId(texture)!!)
}