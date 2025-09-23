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
    }


    var CLIENT_DISPATCHER = { _: CommandContext<CommandSourceStack> -> }

    fun athReload(ctx: CommandContext<CommandSourceStack>): Int {
        CLIENT_DISPATCHER(ctx)
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