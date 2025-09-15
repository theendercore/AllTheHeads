package org.teamvoided.all_the_heads.client.init

import org.teamvoided.all_the_heads.client.init.helpers.layer
import org.teamvoided.all_the_heads.client.init.helpers.mainLayer
import org.teamvoided.all_the_heads.client.init.helpers.register
import org.teamvoided.all_the_heads.client.model.*

object ATHModels {

    val ALLAY_HEAD = mainLayer("allay_head")
    val ARMADILLO_HEAD = mainLayer("armadillo_head")
    val AXOLOTL_HEAD = mainLayer("axolotl_head")

    val TURTLE_HEAD = mainLayer("turtle_head")
    val PHANTOM_HEAD = mainLayer("phantom_head")
    val WARDEN_HEAD = mainLayer("warden_head")

    // Temp
    val BOGGED_HEAD_OVERLAY = layer("bogged_head", "overlay")

    fun init() {
        register(AllayHeadModel.ID, ALLAY_HEAD, AllayHeadModel::head, ::AllayHeadModel)
        register(ArmadilloHeadModel.ID, ARMADILLO_HEAD, ArmadilloHeadModel::head, ::ArmadilloHeadModel)
        register(AxolotlHeadModel.ID, AXOLOTL_HEAD, AxolotlHeadModel::head, ::AxolotlHeadModel)

        register(TurtleHeadModel.ID, TURTLE_HEAD, TurtleHeadModel::head, ::TurtleHeadModel)
        register(PhantomHeadModel.ID, PHANTOM_HEAD, PhantomHeadModel::head, ::PhantomHeadModel)
        register(WardenHeadModel.ID, WARDEN_HEAD, WardenHeadModel::head, ::PhantomHeadModel)
    }
}