package org.teamvoided.all_the_heads.client

import me.fzzyhmstrs.fzzy_config.api.ConfigApi
import me.fzzyhmstrs.fzzy_config.api.RegisterType
import org.teamvoided.all_the_heads.AllTheHeads.log
import org.teamvoided.all_the_heads.client.config.AllTheHeadsClientConfig

@Suppress("unused")
object AllTheHeadsClient {
    @JvmField
    var clientConfig = ConfigApi.registerAndLoadConfig(::AllTheHeadsClientConfig, RegisterType.CLIENT)
    fun init() {
    }

    val errors = mutableSetOf<String>()

    fun sendError(err: String) {
        if (errors.contains(err)) return

        log.error(err)
        errors.add(err)
    }
}
