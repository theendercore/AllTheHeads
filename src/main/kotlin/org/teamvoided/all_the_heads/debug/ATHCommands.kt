package org.teamvoided.all_the_heads.debug

import com.mojang.brigadier.context.CommandContext
import com.mojang.serialization.JsonOps
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback
import net.fabricmc.fabric.mixin.loot.LootPoolAccessor
import net.fabricmc.fabric.mixin.loot.LootTableAccessor
import net.minecraft.commands.CommandSourceStack
import net.minecraft.commands.Commands.literal
import net.minecraft.core.Registry
import net.minecraft.core.registries.Registries
import net.minecraft.network.chat.Component
import net.minecraft.world.entity.item.ItemEntity
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import net.minecraft.world.level.storage.loot.LootTable
import net.minecraft.world.level.storage.loot.entries.*
import net.minecraft.world.level.storage.loot.functions.LootItemFunction
import net.minecraft.world.level.storage.loot.functions.SetComponentsFunction
import org.teamvoided.all_the_heads.mixin.CompositeEntryBaseAccessor
import org.teamvoided.all_the_heads.mixin.LootItemAccessor
import org.teamvoided.all_the_heads.mixin.NestedLootTableAccessor
import org.teamvoided.all_the_heads.mixin.SetComponentsFunctionAccessor

object ATHCommands {
    fun init() = CommandRegistrationCallback.EVENT.register { dispatch, _, _ ->
        val root = literal("ath").build()
        dispatch.root.addChild(root)

        val reload = literal("reload").executes(::athReload).build()
        root.addChild(reload)

        val dumpHeads = literal("dump_heads").executes(::dumpHeads).build()
        root.addChild(dumpHeads)

        val custom = literal("custom").executes(::customCommand).build()
        root.addChild(custom)
    }


    var CLIENT_DISPATCHER = { _: CommandContext<CommandSourceStack> -> }

    fun athReload(ctx: CommandContext<CommandSourceStack>): Int {
        CLIENT_DISPATCHER(ctx)
        return 0
    }

    fun customCommand(ctx: CommandContext<CommandSourceStack>): Int {
        val src = ctx.source ?: return -1
        val world = src.level ?: return -1
        val server = src.server ?: return -1
        val player = src.player ?: return -1

//        i << 20 | j << 4
        repeat(16) {
            val sky = it shl 20
            val block = it shl 4
            println("Sky: $sky, Block: $block")
            println("Sky: ${sky shr 20}, Block: ${block shr 4}")
        }

        /* val tex =
             "iVBORw0KGgoAAAANSUhEUgAAAEAAAABACAYAAACqaXHeAAAAAXNSR0IArs4c6QAAAYRJREFUeJztmjFLxEAQhV82CRxYamdn428SK/H32YiNP8PW8sBOSyGwxFjIBW+5MLnk1jcx7+sGJneTt3mzk7AFDB6vnzsA+OzeAQB323sAQNu2AICyLPfih6sn/M6/eb0trP9gEsYkbdsXfHy99XHTNIgxDsZpvmdMAXYrCQBnxUW/0sDP6qdxmu+dakzSebjcu5m6rvtH/1Cc5nvG9Odms+mAYY/vbnSoR8QYl98DLI9bPcIzpgCWx60e4Z1RPcDyuNUjPFMdu8+nnk/jNN/qIew5IQDz9/klzwlh7j6/9DmhAubv80ueE4q5+7z1LuB9TghAfo97nhNCbo97nxMqIL/HPc8Jh/zXTbjmmOstpv7+pF4y6l3gPyMB2AWwKTDfs14Z1RNW/wRIAHYBbCQAuwA2EoBdABsJwC6AjQRgFyCEEEIIIYQQQgjx1+Q4n5PrfEGWs0Sr/yIkAdgFsDmFr1jnC07SE1b/BEgAdgFsJAC7ADYSgF0AGwnALoCNBGAXwOYbR7pEjQycuEAAAAAASUVORK5CYII="

         val gson = GsonBuilder().registerTypeAdapter(UUID::class.java, UUIDTypeAdapter()).create()

         val CREAMY_LLAMA =
             "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNGQ2N2ZkNGJmZjI5MzI2OWNiOTA4OTc0ZGNhODNjMzM0ODVlNDM1ZWQ1YThlMWRiZDY1MjFjNjE2ODcxNDAifX19"

         try {
             val json = String(Base64.getDecoder().decode(CREAMY_LLAMA), StandardCharsets.UTF_8)
             val result = gson.fromJson(json, MinecraftTexturesPayload::class.java)
             println(result)

         } catch (e: Exception) {
             log.error("Could not decode textures payload", e)
         }*/

        /*val props = PropertyMap()

        val stack = Items.PLAYER_HEAD.defaultInstance
        stack.set(DataComponents.PROFILE, ResolvableProfile(Optional.empty(), Optional.empty(), props))

        player.addItem(stack)*/
        return 0
    }

    fun dumpHeads(ctx: CommandContext<CommandSourceStack>): Int {
        val src = ctx.source ?: return -1
        val world = src.level ?: return -1
        val server = src.server ?: return -1
        val player = src.player ?: return -1

        val tableRegistry = server.reloadableRegistries().get().registry(Registries.LOOT_TABLE).get()

        val lootTables = tableRegistry.holders()
            .filter { it.key().location().namespace == "more_mob_heads" } //.toList().slice(0..9)

        var items = 0
        val pos = player.position()
        val spawner: ItemSpawner = { item ->
            world.addFreshEntity(ItemEntity(world, pos.x, pos.y, pos.z, item))
            items++
        }

        val ops = world.registryAccess().createSerializationContext(JsonOps.INSTANCE)
        for (holder in lootTables) {
            val table = holder.value()
            LootTable.CODEC.encodeStart(ops, holder)
                .ifSuccess { /*println(it)*/ }
                .ifError(::println)

            resolveTable(tableRegistry, table, spawner)
        }

        src.sendSystemMessage(Component.literal("Spawned $items items!"))
        return items
    }

    @Suppress("UnstableApiUsage")
    fun resolveTable(tableRegistry: Registry<LootTable>, table: LootTable, spawnItem: ItemSpawner) {
        for (pool in (table as LootTableAccessor).fabric_getPools()) {
            for (entry in (pool as LootPoolAccessor).fabric_getEntries()) {
                resolveEntry(tableRegistry, entry, spawnItem)
            }
        }
    }


    fun resolveEntry(tableRegistry: Registry<LootTable>, entry: LootPoolEntryContainer, spawnItem: ItemSpawner) {
        when (entry) {
            is LootItem -> {
                val item = (entry as LootItemAccessor).ath_getItem().value()
                if (item == Items.PLAYER_HEAD) entry.ath_getFunctions().forEach {
                    spawnItem(resolveFunction(it, item.defaultInstance))
                }
            }

            is CompositeEntryBase -> {
                for (container in (entry as CompositeEntryBaseAccessor).ath_getChildren()) {
                    resolveEntryRecursionFx(tableRegistry, container, spawnItem)
                }
            }

            is NestedLootTable -> {
                (entry as NestedLootTableAccessor).ath_getContents()
                    .ifRight { resolveTable(tableRegistry, it, spawnItem) }
                    .ifLeft {
                        val table = tableRegistry.get(it)
                        if (table != null) resolveTable(tableRegistry, table, spawnItem)
                        else println("Failed to fetch loot table : $it")
                    }
            }

            is TagEntry -> println("TagEntry")
            is EmptyLootItem -> Unit
            else -> println("Unknow Class: ${entry::class.simpleName}")
        }
    }

    fun resolveEntryRecursionFx(
        tableRegistry: Registry<LootTable>, entry: LootPoolEntryContainer, spawnItem: ItemSpawner,
    ): Unit = resolveEntry(tableRegistry, entry, spawnItem)


    fun resolveFunction(func: LootItemFunction, stack: ItemStack): ItemStack {
        when (func) {
            is SetComponentsFunction -> stack.applyComponentsAndValidate((func as SetComponentsFunctionAccessor).ath_getComponents())
            else -> println("Unknow Class: ${func::class.simpleName}")
        }
        return stack
    }
}