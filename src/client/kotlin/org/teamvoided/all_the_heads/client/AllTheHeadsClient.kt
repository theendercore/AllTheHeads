package org.teamvoided.all_the_heads.client

import me.fzzyhmstrs.fzzy_config.api.ConfigApi
import me.fzzyhmstrs.fzzy_config.api.RegisterType
import net.minecraft.client.Minecraft
import org.teamvoided.all_the_heads.AllTheHeads.log
import org.teamvoided.all_the_heads.client.config.AllTheHeadsClientConfig
import org.teamvoided.all_the_heads.client.init.ATHModelLayers
import org.teamvoided.all_the_heads.client.init.ATHModels
import org.teamvoided.all_the_heads.utils.CLIENT_SUPPLIER

@Suppress("unused")
object AllTheHeadsClient {
    @JvmField
    var clientConfig = ConfigApi.registerAndLoadConfig(::AllTheHeadsClientConfig, RegisterType.CLIENT)
    fun init() {
        ATHModelLayers.init()
        ATHModels.init()
        CLIENT_SUPPLIER = { profile ->
            if (profile != null) Minecraft.getInstance().keyboardHandler.clipboard =
                profile.properties.get("textures").first().value
        }
    }

    val errors = mutableSetOf<String>()

    fun sendError(err: String) {
        if (errors.contains(err)) return

        log.error(err)
        errors.add(err)
    }
}
