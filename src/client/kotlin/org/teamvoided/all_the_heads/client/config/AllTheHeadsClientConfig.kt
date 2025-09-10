@file:Suppress("unused")

package org.teamvoided.all_the_heads.client.config

import me.fzzyhmstrs.fzzy_config.annotations.Comment
import me.fzzyhmstrs.fzzy_config.config.Config
import org.teamvoided.all_the_heads.AllTheHeads.id

@Suppress("unused")
class AllTheHeadsClientConfig : Config(id("client")) {
//    var groupName = ConfigGroup("group_id", false)
//    @ConfigGroup.Pop

    @Comment("Determines how the head heads are rendered. Name based is using the player head name and will work on any server. But data base one will only work if the mod is also installed one the server.")
    var headRenderMode = HeadRenderMode.NAME_BASED

}

enum class HeadRenderMode { NAME_BASED, DATA_BASED }