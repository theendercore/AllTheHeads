@file:Suppress("unused")

package org.teamvoided.all_the_heads.client.config

import me.fzzyhmstrs.fzzy_config.annotations.Comment
import me.fzzyhmstrs.fzzy_config.config.Config
import me.fzzyhmstrs.fzzy_config.validation.misc.ValidatedEnum
import me.fzzyhmstrs.fzzy_config.validation.misc.ValidatedEnum.WidgetType
import net.minecraft.network.chat.Component
import org.teamvoided.all_the_heads.AllTheHeads.id
import org.teamvoided.all_the_heads.client.data.HeadRenderMode
import org.teamvoided.all_the_heads.client.data.ProfileDataMode
import org.teamvoided.all_the_heads.utils.isDev

@Suppress("unused")
class AllTheHeadsClientConfig : Config(id("client")) {
//    var groupName = ConfigGroup("group_id", false)
//    @ConfigGroup.Pop

    @Comment(
        """Determines how the mod get the head model. 
Profile mode is using the vanilla head data and will work on any server. 
Custom Data mode is using the this mods custom ntb data and will only work if the server has to mod too"""
    )
    var headRenderMode = ValidatedEnum(HeadRenderMode.PROFILE, WidgetType.CYCLING)

    var profileDataMode = ValidatedEnum(ProfileDataMode.TEXTURE, WidgetType.CYCLING).toCondition(
        { headRenderMode.get() == HeadRenderMode.PROFILE }, { ProfileDataMode.TEXTURE },
        Component.translatable("Only used for Profile Mode!")
    )


    @Comment("Enabled debug info. Not recommended in everyday use")
    var enableDebugRendering = isDev()
    var lookAtMode = true
}