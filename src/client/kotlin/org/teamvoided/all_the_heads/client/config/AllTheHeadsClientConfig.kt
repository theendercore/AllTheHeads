@file:Suppress("unused")

package org.teamvoided.all_the_heads.client.config

import me.fzzyhmstrs.fzzy_config.annotations.Comment
import me.fzzyhmstrs.fzzy_config.config.Config
import me.fzzyhmstrs.fzzy_config.validation.misc.ValidatedEnum
import me.fzzyhmstrs.fzzy_config.validation.misc.ValidatedEnum.WidgetType
import org.teamvoided.all_the_heads.AllTheHeads.id
import org.teamvoided.all_the_heads.client.data.HeadRenderMode
import org.teamvoided.all_the_heads.utils.isDev

@Suppress("unused")
class AllTheHeadsClientConfig : Config(id("client")) {
//    var groupName = ConfigGroup("group_id", false)
//    @ConfigGroup.Pop

    @Comment(
        """Determines how the head heads are rendered. 
Name based is using the player head name and will work on any server. 
Data based one will only work if the mod is also installed one the server"""
    )
    var headRenderMode = ValidatedEnum(HeadRenderMode.NAME_BASED, WidgetType.CYCLING)



    @Comment("Enabled debug info. Not recommended in everyday use")
    var enableDebugRendering = isDev()
}