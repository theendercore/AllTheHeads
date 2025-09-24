package org.teamvoided.all_the_heads.client.init

import net.minecraft.client.model.SkullModelBase
import net.minecraft.client.renderer.RenderType
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.level.block.SkullBlock
import org.teamvoided.all_the_heads.AllTheHeads
import org.teamvoided.all_the_heads.client.AllTheHeadsClient
import org.teamvoided.all_the_heads.client.data.SkullRenderData
import org.teamvoided.all_the_heads.client.data.textures.MiscTextures
import org.teamvoided.all_the_heads.client.data.textures.VTTextures
import org.teamvoided.all_the_heads.client.model.*

object ModelsManager {
    @JvmField
    var VANILLA_MODEL_ACCESS = mapOf<SkullBlock.Type, SkullModelBase>()

    @JvmField
    var BUILT_IN_MODELS = mutableMapOf<ResourceLocation, SkullModelBase>()

    val models = mapOf(
        VTTextures.ALLAY to builtIn(AllayHeadModel.ID, "allay/allay"),
        VTTextures.ARMADILLO to builtIn(ArmadilloHeadModel.ID, "armadillo"),
        // Axolotl
        VTTextures.LUCY_AXOLOTL to builtIn(AxolotlHeadModel.ID, "axolotl/axolotl_lucy"),
        VTTextures.WILD_AXOLOTL to builtIn(AxolotlHeadModel.ID, "axolotl/axolotl_wild"),
        VTTextures.GOLD_AXOLOTL to builtIn(AxolotlHeadModel.ID, "axolotl/axolotl_gold"),
        VTTextures.CYAN_AXOLOTL to builtIn(AxolotlHeadModel.ID, "axolotl/axolotl_cyan"),
        VTTextures.BLUE_AXOLOTL to builtIn(AxolotlHeadModel.ID, "axolotl/axolotl_blue"),

        VTTextures.BAT to builtIn(BatHeadModel.ID, "bat"),
        // Bee
        VTTextures.BEE to builtIn(BeeHeadModel.ID, "bee/bee"),
        VTTextures.POLLINATED_BEE to builtIn(BeeHeadModel.ID, "bee/bee_nectar"),
        VTTextures.ANGRY_BEE to builtIn(BeeHeadModel.ID, "bee/bee_angry"),
        VTTextures.ANGRY_POLLINATED_BEE to builtIn(BeeHeadModel.ID, "bee/bee_angry_nectar"),

        VTTextures.CAMEL to builtIn(CamelHeadModel.ID, "camel/camel"),
        // Cat
        VTTextures.TABBY_CAT to builtIn(OcelotHeadModel.ID, "cat/tabby"),
        VTTextures.TUXEDO_CAT to builtIn(OcelotHeadModel.ID, "cat/black"),
        VTTextures.GINGER_CAT to builtIn(OcelotHeadModel.ID, "cat/red"),
        VTTextures.SIAMESE_CAT to builtIn(OcelotHeadModel.ID, "cat/siamese"),
        VTTextures.BRITISH_SHORTHAIR_CAT to builtIn(OcelotHeadModel.ID, "cat/british_shorthair"),
        VTTextures.CALICO_CAT to builtIn(OcelotHeadModel.ID, "cat/calico"),
        VTTextures.PERSIAN_CAT to builtIn(OcelotHeadModel.ID, "cat/persian"),
        VTTextures.RAGDOLL_CAT to builtIn(OcelotHeadModel.ID, "cat/ragdoll"),
        VTTextures.WHITE_CAT to builtIn(OcelotHeadModel.ID, "cat/white"),
        VTTextures.JELLIE_CAT to builtIn(OcelotHeadModel.ID, "cat/jellie"),
        VTTextures.BLACK_CAT to builtIn(OcelotHeadModel.ID, "cat/all_black"),

        VTTextures.CHICKEN to builtIn(ChickenHeadModel.ID, "chicken"),
        VTTextures.COD to builtIn(CodHeadModel.ID, "fish/cod"),
        VTTextures.COW to builtIn(CowHeadModel.ID, "cow/cow"),
        VTTextures.DOLPHIN to builtIn(DolphinHeadModel.ID, "dolphin"),
        VTTextures.DONKEY to builtIn(ChestedHorseHeadModel.ID, "horse/donkey"),
        VTTextures.ENDERMITE to builtIn(EndermiteHeadModel.ID, "endermite"),
        VTTextures.EVOKER to builtIn(IllagerHeadModel.ID, "illager/evoker"),
        // Fox
        VTTextures.FOX to builtIn(FoxHeadModel.ID, "fox/fox"),
        VTTextures.SNOW_FOX to builtIn(FoxHeadModel.ID, "fox/snow_fox"),
        // Frog
        VTTextures.TEMPERATE_FROG to builtIn(FrogHeadModel.ID, "frog/temperate_frog"),
        VTTextures.WARM_FROG to builtIn(FrogHeadModel.ID, "frog/warm_frog"),
        VTTextures.COLD_FROG to builtIn(FrogHeadModel.ID, "frog/cold_frog"),

        VTTextures.GLOW_SQUID to builtIn(SquidHeadModel.ID, "squid/glow_squid"),
        VTTextures.GOAT to builtIn(GoatHeadModel.ID, "goat/goat"),
//        VTTextures.SCREAMING_GOAT to builtIn(GoatHeadModel.ID, "goat/goat"),
        VTTextures.HOGLIN to builtIn(HoglinHeadModel.ID, "hoglin/hoglin"),
        // Horse
        VTTextures.WHITE_HORSE to builtIn(HorseHeadModel.ID, "horse/horse_white"),
        VTTextures.CREAMY_HORSE to builtIn(HorseHeadModel.ID, "horse/horse_creamy"),
        VTTextures.CHESTNUT_HORSE to builtIn(HorseHeadModel.ID, "horse/horse_chestnut"),
        VTTextures.BROWN_HORSE to builtIn(HorseHeadModel.ID, "horse/horse_brown"),
        VTTextures.BLACK_HORSE to builtIn(HorseHeadModel.ID, "horse/horse_black"),
        VTTextures.GRAY_HORSE to builtIn(HorseHeadModel.ID, "horse/horse_gray"),
        VTTextures.DARK_BROWN_HORSE to builtIn(HorseHeadModel.ID, "horse/horse_darkbrown"),

        VTTextures.IRON_GOLEM to builtIn(IronGolemHeadModel.ID, "iron_golem/iron_golem"),
        VTTextures.ILLUSIONER to builtIn(IllagerHeadModel.ID, "illager/illusioner"),
        // Llama
        VTTextures.CREAMY_LLAMA to builtIn(LlamaHeadModel.ID, "llama/creamy"),
        VTTextures.WHITE_LLAMA to builtIn(LlamaHeadModel.ID, "llama/white"),
        VTTextures.BROWN_LLAMA to builtIn(LlamaHeadModel.ID, "llama/brown"),
        VTTextures.GRAY_LLAMA to builtIn(LlamaHeadModel.ID, "llama/gray"),

        VTTextures.MAGMA_CUBE to builtIn(MagmaCubeHeadModel.ID, "slime/magmacube"),
        VTTextures.RED_MOOSHROOM to builtIn(CowHeadModel.ID, "cow/red_mooshroom"),
        VTTextures.BROWN_MOOSHROOM to builtIn(CowHeadModel.ID, "cow/brown_mooshroom"),
        VTTextures.MULE to builtIn(ChestedHorseHeadModel.ID, "horse/mule"),
        VTTextures.OCELOT to builtIn(OcelotHeadModel.ID, "cat/ocelot"),
        // Panda
        VTTextures.PANDA to builtIn(PandaHeadModel.ID, "panda/panda"),
        VTTextures.LAZY_PANDA to builtIn(PandaHeadModel.ID, "panda/lazy_panda"),
        VTTextures.WORRIED_PANDA to builtIn(PandaHeadModel.ID, "panda/worried_panda"),
        VTTextures.PLAYFUL_PANDA to builtIn(PandaHeadModel.ID, "panda/playful_panda"),
        VTTextures.BROWN_PANDA to builtIn(PandaHeadModel.ID, "panda/brown_panda"),
        VTTextures.WEAK_PANDA to builtIn(PandaHeadModel.ID, "panda/weak_panda"),
        VTTextures.AGGRESSIVE_PANDA to builtIn(PandaHeadModel.ID, "panda/aggressive_panda"),
        // Parrot
        VTTextures.RED_PARROT to builtIn(ParrotHeadModel.ID, "parrot/parrot_red_blue"),
        VTTextures.BLUE_PARROT to builtIn(ParrotHeadModel.ID, "parrot/parrot_blue"),
        VTTextures.GREEN_PARROT to builtIn(ParrotHeadModel.ID, "parrot/parrot_green"),
        VTTextures.LIGHT_BLUE_PARROT to builtIn(ParrotHeadModel.ID, "parrot/parrot_yellow_blue"),
        VTTextures.GRAY_PARROT to builtIn(ParrotHeadModel.ID, "parrot/parrot_grey"),

        VTTextures.PHANTOM to builtIn(PhantomHeadModel.ID, "phantom"),
        VTTextures.PIG to builtIn(PigHeadModel.ID, "pig/pig"),
        VTTextures.PIGLIN_BRUTE to vanilla(SkullBlock.Types.PIGLIN, "piglin/piglin_brute"),
        VTTextures.PILLAGER to builtIn(IllagerHeadModel.ID, "illager/pillager"),
        VTTextures.POLAR_BEAR to builtIn(PolarBearHeadModel.ID, "bear/polarbear"),
        VTTextures.PUFFERFISH to builtIn(PufferfishHeadModel.ID, "fish/pufferfish"),
        // Rabbit
        VTTextures.BROWN_RABBIT to builtIn(RabbitHeadModel.ID, "rabbit/brown"),
        VTTextures.WHITE_RABBIT to builtIn(RabbitHeadModel.ID, "rabbit/white"),
        VTTextures.BLACK_RABBIT to builtIn(RabbitHeadModel.ID, "rabbit/black"),
        VTTextures.BLACK_AND_WHITE_RABBIT to builtIn(RabbitHeadModel.ID, "rabbit/white_splotched"),
        VTTextures.GOLD_RABBIT to builtIn(RabbitHeadModel.ID, "rabbit/gold"),
        VTTextures.SALT_AND_PEPPER_RABBIT to builtIn(RabbitHeadModel.ID, "rabbit/salt"),
        VTTextures.TOAST_RABBIT to builtIn(RabbitHeadModel.ID, "rabbit/toast"),
        VTTextures.THE_KILLER_BUNNY to builtIn(RabbitHeadModel.ID, "rabbit/caerbannog"),

        VTTextures.RAVAGER to builtIn(RavagerHeadModel.ID, "illager/ravager"),
        VTTextures.SALMON to builtIn(SalmonHeadModel.ID, "fish/salmon"),
        // TODO
        // Sheep
        VTTextures.WHITE_SHEEP to builtIn(SheepHeadModel.ID, "sheep/sheep"),
        VTTextures.LIGHT_GRAY_SHEEP to builtIn(SheepHeadModel.ID, "sheep/sheep"),
        VTTextures.GRAY_SHEEP to builtIn(SheepHeadModel.ID, "sheep/sheep"),
        VTTextures.BLACK_SHEEP to builtIn(SheepHeadModel.ID, "sheep/sheep"),
        VTTextures.BROWN_SHEEP to builtIn(SheepHeadModel.ID, "sheep/sheep"),
        VTTextures.RED_SHEEP to builtIn(SheepHeadModel.ID, "sheep/sheep"),
        VTTextures.ORANGE_SHEEP to builtIn(SheepHeadModel.ID, "sheep/sheep"),
        VTTextures.YELLOW_SHEEP to builtIn(SheepHeadModel.ID, "sheep/sheep"),
        VTTextures.LIME_SHEEP to builtIn(SheepHeadModel.ID, "sheep/sheep"),
        VTTextures.GREEN_SHEEP to builtIn(SheepHeadModel.ID, "sheep/sheep"),
        VTTextures.CYAN_SHEEP to builtIn(SheepHeadModel.ID, "sheep/sheep"),
        VTTextures.LIGHT_BLUE_SHEEP to builtIn(SheepHeadModel.ID, "sheep/sheep"),
        VTTextures.BLUE_SHEEP to builtIn(SheepHeadModel.ID, "sheep/sheep"),
        VTTextures.PURPLE_SHEEP to builtIn(SheepHeadModel.ID, "sheep/sheep"),
        VTTextures.MAGENTA_SHEEP to builtIn(SheepHeadModel.ID, "sheep/sheep"),
        VTTextures.PINK_SHEEP to builtIn(SheepHeadModel.ID, "sheep/sheep"),
        VTTextures.JEB__SHEEP to builtIn(SheepHeadModel.ID, "sheep/sheep"),

        VTTextures.SHULKER to builtIn(ShulkerHeadModel.ID, "shulker/shulker"),
        VTTextures.SILVERFISH to builtIn(SilverfishHeadModel.ID, "silverfish"),
        VTTextures.SKELETON_HORSE to builtIn(HorseHeadModel.ID, "horse/horse_skeleton"),
        VTTextures.SLIME to builtIn(SlimeHeadModel.ID, "slime/slime"),
        VTTextures.SNIFFER to builtIn(SnifferHeadModel.ID, "sniffer/sniffer"),
        VTTextures.SQUID to builtIn(SquidHeadModel.ID, "squid/squid"),
        VTTextures.STRIDER to builtIn(StriderHeadModel.ID, "strider/strider"),
        VTTextures.COLD_STRIDER to builtIn(StriderHeadModel.ID, "strider/strider_cold"),
        VTTextures.TADPOLE to builtIn(TadpoleHeadModel.ID, "tadpole/tadpole"),
        // Trader Llama
        VTTextures.CREAMY_TRADER_LLAMA to builtIn(LlamaHeadModel.ID, "llama/creamy"),
        VTTextures.WHITE_TRADER_LLAMA to builtIn(LlamaHeadModel.ID, "llama/white"),
        VTTextures.BROWN_TRADER_LLAMA to builtIn(LlamaHeadModel.ID, "llama/brown"),
        VTTextures.GRAY_TRADER_LLAMA to builtIn(LlamaHeadModel.ID, "llama/gray"),

        VTTextures.TROPICAL_FISH to builtIn(TropicalFishHeadModel.ID, "fish/tropical_b"),
        VTTextures.TURTLE to builtIn(TurtleHeadModel.ID, "turtle/big_sea_turtle"),
        // Vex
        VTTextures.VEX to builtIn(AllayHeadModel.ID, "illager/vex"),
        VTTextures.VEX_CHARGING to builtIn(AllayHeadModel.ID, "illager/vex_charging"),
        // TODO
        // Villager
//        VTTextures.VILLAGER to builtIn(VillagerHeadModel.ID, "villager"),

        VTTextures.VINDICATOR to builtIn(IllagerHeadModel.ID, "illager/vindicator"),
        VTTextures.WANDERING_TRADER to builtIn(VillagerHeadModel.ID, "wandering_trader"),
        VTTextures.WARDEN to builtIn(WardenHeadModel.ID, "warden/warden"),

        VTTextures.WITCH to builtIn(WitchHeadModel.ID, "witch"),
        // TODO
        // Wolf
        VTTextures.PALE_WOLF to builtIn(WolfHeadModel.ID, "wolf/wolf"),
        VTTextures.ANGRY_PALE_WOLF to builtIn(WolfHeadModel.ID, "wolf/wolf_angry"),
        VTTextures.SPOTTY_WOLF to builtIn(WolfHeadModel.ID, "wolf/wolf_spotted"),
        VTTextures.ANGRY_SPOTTY_WOLF to builtIn(WolfHeadModel.ID, "wolf/wolf_spotted_angry"),
        VTTextures.SNOWY_WOLF to builtIn(WolfHeadModel.ID, "wolf/wolf_snowy"),
        VTTextures.ANGRY_SNOWY_WOLF to builtIn(WolfHeadModel.ID, "wolf/wolf_snowy_angry"),
        VTTextures.BLACK_WOLF to builtIn(WolfHeadModel.ID, "wolf/wolf_black"),
        VTTextures.ANGRY_BLACK_WOLF to builtIn(WolfHeadModel.ID, "wolf/wolf_black_angry"),
        VTTextures.ASHEN_WOLF to builtIn(WolfHeadModel.ID, "wolf/wolf_ashen"),
        VTTextures.ANGRY_ASHEN_WOLF to builtIn(WolfHeadModel.ID, "wolf/wolf_ashen_angry"),
        VTTextures.RUSTY_WOLF to builtIn(WolfHeadModel.ID, "wolf/wolf_rusty"),
        VTTextures.ANGRY_RUSTY_WOLF to builtIn(WolfHeadModel.ID, "wolf/wolf_rusty_angry"),
        VTTextures.WOODS_WOLF to builtIn(WolfHeadModel.ID, "wolf/wolf_woods"),
        VTTextures.ANGRY_WOODS_WOLF to builtIn(WolfHeadModel.ID, "wolf/wolf_woods_angry"),
        VTTextures.CHESTNUT_WOLF to builtIn(WolfHeadModel.ID, "wolf/wolf_chestnut"),
        VTTextures.ANGRY_CHESTNUT_WOLF to builtIn(WolfHeadModel.ID, "wolf/wolf_chestnut_angry"),
        VTTextures.STRIPED_WOLF to builtIn(WolfHeadModel.ID, "wolf/wolf_striped"),
        VTTextures.ANGRY_STRIPED_WOLF to builtIn(WolfHeadModel.ID, "wolf/wolf_striped_angry"),

        VTTextures.ZOGLIN to builtIn(HoglinHeadModel.ID, "hoglin/zoglin"),
        VTTextures.ZOMBIE_HORSE to builtIn(HorseHeadModel.ID, "horse/horse_zombie"),
        VTTextures.ZOMBIFIED_PIGLIN to vanilla(SkullBlock.Types.PIGLIN, "piglin/zombified_piglin"),

        MiscTextures.TEST_TEX to builtIn(CamelWithNeckHeadModel.ID, "camel/camel"),
    )

    fun vanilla(id: SkullBlock.Type, texture: String) = SkullRenderData(getVanilla(id), entityBasic(texture))
    fun getVanilla(type: SkullBlock.Type): () -> SkullModelBase {
        if (type !is SkullBlock.Types) {
            AllTheHeadsClient.sendError("Supplied non vanilla SkullType! $type")
            return { VANILLA_MODEL_ACCESS[SkullBlock.Types.PLAYER]!! }
        }
        return { VANILLA_MODEL_ACCESS[type]!! }
    }

    fun builtIn(id: ResourceLocation, texture: String) = SkullRenderData(getBuiltIn(id), entityBasic(texture))
    fun getBuiltIn(id: ResourceLocation): () -> SkullModelBase = {
        val model = BUILT_IN_MODELS[id]
        if (model != null) model
        else {
            AllTheHeadsClient.sendError("Failed to load model for Id! $id")
            VANILLA_MODEL_ACCESS[SkullBlock.Types.PLAYER]!!
        }
    }

    fun basicType(texture: String): RenderType = RenderType.entityCutoutNoCullZOffset(AllTheHeads.tryParseId(texture)!!)
    fun entityBasic(texture: String): RenderType = basicType("textures/entity/${texture}.png")

    fun translucentType(texture: String): RenderType = RenderType.entityTranslucent(AllTheHeads.tryParseId(texture)!!)
    fun entityTranslucent(texture: String): RenderType = translucentType("textures/entity/${texture}.png")

}