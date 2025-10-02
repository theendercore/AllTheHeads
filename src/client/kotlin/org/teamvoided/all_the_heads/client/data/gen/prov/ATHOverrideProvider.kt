package org.teamvoided.all_the_heads.client.data.gen.prov

import net.minecraft.core.HolderLookup
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.SkullBlock
import org.teamvoided.all_the_heads.AllTheHeads.id
import org.teamvoided.all_the_heads.AllTheHeads.mc
import org.teamvoided.all_the_heads.client.data.gen.CreationContext
import org.teamvoided.all_the_heads.client.data.gen.FutureLookup
import org.teamvoided.all_the_heads.client.data.gen.HeadModelOverrideProvider
import org.teamvoided.all_the_heads.client.data.gen.Output
import org.teamvoided.all_the_heads.client.data.render.*
import org.teamvoided.all_the_heads.client.data.render.type.RenderTypeProvider
import org.teamvoided.all_the_heads.client.data.render.type.TexturedRenderTypeProvider
import org.teamvoided.all_the_heads.client.data.textures.MiscTextures
import org.teamvoided.all_the_heads.client.data.textures.VTTextures
import org.teamvoided.all_the_heads.client.model.*
import org.teamvoided.all_the_heads.client.resources.HeadModelOverride

class ATHOverrideProvider(o: Output, r: FutureLookup) : HeadModelOverrideProvider(o, r) {
    override fun generateOverrides(lookup: HolderLookup.Provider, dataBuilder: CreationContext<HeadModelOverride>) {
        val modelMap = mapOf(
            id("allay") to HeadModelOverride(VTTextures.ALLAY, builtIn(AllayHeadModel.ID, "allay/allay", 15)),
            id("armadillo") to HeadModelOverride(VTTextures.ARMADILLO, builtIn(ArmadilloHeadModel.ID, "armadillo")),
            // Axolotl
            id("axolotl/lucy") to HeadModelOverride(
                VTTextures.LUCY_AXOLOTL,
                builtIn(AxolotlHeadModel.ID, "axolotl/axolotl_lucy")
            ),
            id("axolotl/wild") to HeadModelOverride(
                VTTextures.WILD_AXOLOTL,
                builtIn(AxolotlHeadModel.ID, "axolotl/axolotl_wild")
            ),
            id("axolotl/gold") to HeadModelOverride(
                VTTextures.GOLD_AXOLOTL,
                builtIn(AxolotlHeadModel.ID, "axolotl/axolotl_gold")
            ),
            id("axolotl/cyan") to HeadModelOverride(
                VTTextures.CYAN_AXOLOTL,
                builtIn(AxolotlHeadModel.ID, "axolotl/axolotl_cyan")
            ),
            id("axolotl/blue") to HeadModelOverride(
                VTTextures.BLUE_AXOLOTL,
                builtIn(AxolotlHeadModel.ID, "axolotl/axolotl_blue")
            ),

            id("bat") to HeadModelOverride(VTTextures.BAT, builtIn(BatHeadModel.ID, "bat")),
            // Bee
            id("bee") to HeadModelOverride(VTTextures.BEE, builtIn(BeeHeadModel.ID, "bee/bee")),
            id("bee/nectar") to HeadModelOverride(
                VTTextures.POLLINATED_BEE,
                builtIn(BeeHeadModel.ID, "bee/bee_nectar")
            ),
            id("bee/angry") to HeadModelOverride(VTTextures.ANGRY_BEE, builtIn(BeeHeadModel.ID, "bee/bee_angry")),
            id("bee/angry_nectar") to HeadModelOverride(
                VTTextures.ANGRY_POLLINATED_BEE,
                builtIn(BeeHeadModel.ID, "bee/bee_angry_nectar")
            ),

            id("blaze") to HeadModelOverride(VTTextures.BLAZE, vanilla(SkullBlock.Types.CREEPER, "blaze", 15)),
            id("bogged") to HeadModelOverride(VTTextures.BOGGED, vanilla(SkullBlock.Types.SKELETON, "skeleton/bogged")),
            id("breeze") to HeadModelOverride(VTTextures.BREEZE, builtIn(BreezeHeadModel.ID, "breeze/breeze")),

            id("camel") to HeadModelOverride(VTTextures.CAMEL, builtIn(CamelHeadModel.ID, "camel/camel")),
            // Cat
            id("cat/tabby") to HeadModelOverride(VTTextures.TABBY_CAT, builtIn(OcelotHeadModel.ID, "cat/tabby")),
            id("cat/tuxedo") to HeadModelOverride(VTTextures.TUXEDO_CAT, builtIn(OcelotHeadModel.ID, "cat/black")),
            id("cat/ginger") to HeadModelOverride(VTTextures.GINGER_CAT, builtIn(OcelotHeadModel.ID, "cat/red")),
            id("cat/siamese") to HeadModelOverride(VTTextures.SIAMESE_CAT, builtIn(OcelotHeadModel.ID, "cat/siamese")),
            id("cat/british_shorthair") to HeadModelOverride(
                VTTextures.BRITISH_SHORTHAIR_CAT,
                builtIn(OcelotHeadModel.ID, "cat/british_shorthair")
            ),
            id("cat/calico") to HeadModelOverride(VTTextures.CALICO_CAT, builtIn(OcelotHeadModel.ID, "cat/calico")),
            id("cat/persian") to HeadModelOverride(VTTextures.PERSIAN_CAT, builtIn(OcelotHeadModel.ID, "cat/persian")),
            id("cat/ragdoll") to HeadModelOverride(VTTextures.RAGDOLL_CAT, builtIn(OcelotHeadModel.ID, "cat/ragdoll")),
            id("cat/white") to HeadModelOverride(VTTextures.WHITE_CAT, builtIn(OcelotHeadModel.ID, "cat/white")),
            id("cat/jellie") to HeadModelOverride(VTTextures.JELLIE_CAT, builtIn(OcelotHeadModel.ID, "cat/jellie")),
            id("cat/black") to HeadModelOverride(VTTextures.BLACK_CAT, builtIn(OcelotHeadModel.ID, "cat/all_black")),

            id("cave_spider") to HeadModelOverride(
                VTTextures.CAVE_SPIDER, list(
                    builtIn(SpiderHeadModel.ID, "spider/cave_spider"),
                    BuiltInHeadModel(SpiderHeadModel.ID, eyes("spider_eyes"))
                )
            ),
            id("chicken") to HeadModelOverride(VTTextures.CHICKEN, builtIn(ChickenHeadModel.ID, "chicken")),
            id("cod") to HeadModelOverride(VTTextures.COD, builtIn(CodHeadModel.ID, "fish/cod")),
            id("cow") to HeadModelOverride(VTTextures.COW, builtIn(CowHeadModel.ID, "cow/cow")),
            id("creeper") to HeadModelOverride(
                VTTextures.CHARGED_CREEPER,
                vanilla(SkullBlock.Types.CREEPER, "creeper/creeper")
            ),
            id("dolphin") to HeadModelOverride(VTTextures.DOLPHIN, builtIn(DolphinHeadModel.ID, "dolphin")),
            id("enderman") to HeadModelOverride(
                VTTextures.ENDERMAN,
                builtIn(EndermanHeadModel.ID, "enderman/enderman")
            ),
            id("donkey") to HeadModelOverride(VTTextures.DONKEY, builtIn(ChestedHorseHeadModel.ID, "horse/donkey")),
            id("drowned") to HeadModelOverride(VTTextures.DROWNED, vanilla(SkullBlock.Types.ZOMBIE, "zombie/drowned")),
            id("endermite") to HeadModelOverride(VTTextures.ENDERMITE, builtIn(EndermiteHeadModel.ID, "endermite")),
            id("evoker") to HeadModelOverride(VTTextures.EVOKER, builtIn(IllagerHeadModel.ID, "illager/evoker")),
            // Fox
            id("fox") to HeadModelOverride(VTTextures.FOX, builtIn(FoxHeadModel.ID, "fox/fox")),
            id("snow_fox") to HeadModelOverride(VTTextures.SNOW_FOX, builtIn(FoxHeadModel.ID, "fox/snow_fox")),
            // Frog
            id("temperate_frog") to HeadModelOverride(
                VTTextures.TEMPERATE_FROG,
                builtIn(FrogHeadModel.ID, "frog/temperate_frog")
            ),
            id("warm_frog") to HeadModelOverride(VTTextures.WARM_FROG, builtIn(FrogHeadModel.ID, "frog/warm_frog")),
            id("cold_frog") to HeadModelOverride(VTTextures.COLD_FROG, builtIn(FrogHeadModel.ID, "frog/cold_frog")),

            id("ghast") to HeadModelOverride(VTTextures.GHAST, builtIn(GhastHeadModel.ID, "ghast/ghast")),
            id("glow_squid") to HeadModelOverride(
                VTTextures.GLOW_SQUID,
                builtIn(SquidHeadModel.ID, "squid/glow_squid", 15)
            ),
            id("goat") to HeadModelOverride(VTTextures.GOAT, builtIn(GoatHeadModel.ID, "goat/goat")),
//        id("screaming_goat") to HeadModelOverride(VTTextures.SCREAMING_GOAT, builtIn(GoatHeadModel.ID, "goat/goat")),
            id("hoglin") to HeadModelOverride(VTTextures.HOGLIN, builtIn(HoglinHeadModel.ID, "hoglin/hoglin")),
            // Horse
            id("horse/white") to HeadModelOverride(
                VTTextures.WHITE_HORSE,
                builtIn(HorseHeadModel.ID, "horse/horse_white")
            ),
            id("horse/creamy") to HeadModelOverride(
                VTTextures.CREAMY_HORSE,
                builtIn(HorseHeadModel.ID, "horse/horse_creamy")
            ),
            id("horse/chestnut") to HeadModelOverride(
                VTTextures.CHESTNUT_HORSE,
                builtIn(HorseHeadModel.ID, "horse/horse_chestnut")
            ),
            id("horse/brown") to HeadModelOverride(
                VTTextures.BROWN_HORSE,
                builtIn(HorseHeadModel.ID, "horse/horse_brown")
            ),
            id("horse/black") to HeadModelOverride(
                VTTextures.BLACK_HORSE,
                builtIn(HorseHeadModel.ID, "horse/horse_black")
            ),
            id("horse/gray") to HeadModelOverride(
                VTTextures.GRAY_HORSE,
                builtIn(HorseHeadModel.ID, "horse/horse_gray")
            ),
            id("horse/darkbrown") to HeadModelOverride(
                VTTextures.DARK_BROWN_HORSE,
                builtIn(HorseHeadModel.ID, "horse/horse_darkbrown")
            ),

            id("husk") to HeadModelOverride(VTTextures.HUSK, vanilla(SkullBlock.Types.ZOMBIE, "zombie/husk")),
            id("iron_golem") to HeadModelOverride(
                VTTextures.IRON_GOLEM,
                builtIn(IronGolemHeadModel.ID, "iron_golem/iron_golem")
            ),
            id("illusioner") to HeadModelOverride(
                VTTextures.ILLUSIONER,
                builtIn(IllagerHeadModel.ID, "illager/illusioner")
            ),
            id("magma_cube") to HeadModelOverride(
                VTTextures.MAGMA_CUBE,
                builtIn(MagmaCubeHeadModel.ID, "slime/magmacube", 15)
            ),
            // Llama
            id("llama/creamy") to HeadModelOverride(
                VTTextures.CREAMY_LLAMA,
                builtIn(LlamaHeadModel.ID, "llama/creamy")
            ),
            id("llama/white") to HeadModelOverride(VTTextures.WHITE_LLAMA, builtIn(LlamaHeadModel.ID, "llama/white")),
            id("llama/brown") to HeadModelOverride(VTTextures.BROWN_LLAMA, builtIn(LlamaHeadModel.ID, "llama/brown")),
            id("llama/gray") to HeadModelOverride(VTTextures.GRAY_LLAMA, builtIn(LlamaHeadModel.ID, "llama/gray")),

            id("red_mooshroom") to HeadModelOverride(
                VTTextures.RED_MOOSHROOM, list(
                    builtIn(CowHeadModel.ID, "cow/red_mooshroom"),
                    BlockHeadModel(Blocks.RED_MUSHROOM.defaultBlockState())
                )
            ),
            id("brown_mooshroom") to HeadModelOverride(
                VTTextures.BROWN_MOOSHROOM, list(
                    builtIn(CowHeadModel.ID, "cow/brown_mooshroom"),
                    BlockHeadModel(Blocks.BROWN_MUSHROOM.defaultBlockState())
                )
            ),


            id("horse/mule") to HeadModelOverride(VTTextures.MULE, builtIn(ChestedHorseHeadModel.ID, "horse/mule")),
            id("cat/ocelot") to HeadModelOverride(VTTextures.OCELOT, builtIn(OcelotHeadModel.ID, "cat/ocelot")),
            // Panda
            id("panda/panda") to HeadModelOverride(VTTextures.PANDA, builtIn(PandaHeadModel.ID, "panda/panda")),
            id("panda/lazy") to HeadModelOverride(
                VTTextures.LAZY_PANDA,
                builtIn(PandaHeadModel.ID, "panda/lazy_panda")
            ),
            id("panda/worried") to HeadModelOverride(
                VTTextures.WORRIED_PANDA,
                builtIn(PandaHeadModel.ID, "panda/worried_panda")
            ),
            id("panda/playful") to HeadModelOverride(
                VTTextures.PLAYFUL_PANDA,
                builtIn(PandaHeadModel.ID, "panda/playful_panda")
            ),
            id("panda/brown") to HeadModelOverride(
                VTTextures.BROWN_PANDA,
                builtIn(PandaHeadModel.ID, "panda/brown_panda")
            ),
            id("panda/weak") to HeadModelOverride(
                VTTextures.WEAK_PANDA,
                builtIn(PandaHeadModel.ID, "panda/weak_panda")
            ),
            id("panda/aggressive") to HeadModelOverride(
                VTTextures.AGGRESSIVE_PANDA,
                builtIn(PandaHeadModel.ID, "panda/aggressive_panda")
            ),
            // Parrot
            id("parrot/red_blue") to HeadModelOverride(
                VTTextures.RED_PARROT,
                builtIn(ParrotHeadModel.ID, "parrot/parrot_red_blue")
            ),
            id("parrot/blue") to HeadModelOverride(
                VTTextures.BLUE_PARROT,
                builtIn(ParrotHeadModel.ID, "parrot/parrot_blue")
            ),
            id("parrot/green") to HeadModelOverride(
                VTTextures.GREEN_PARROT,
                builtIn(ParrotHeadModel.ID, "parrot/parrot_green")
            ),
            id("parrot/yellow_blue") to HeadModelOverride(
                VTTextures.LIGHT_BLUE_PARROT,
                builtIn(ParrotHeadModel.ID, "parrot/parrot_yellow_blue")
            ),
            id("parrot/grey") to HeadModelOverride(
                VTTextures.GRAY_PARROT,
                builtIn(ParrotHeadModel.ID, "parrot/parrot_grey")
            ),

            id("phantom") to HeadModelOverride(
                VTTextures.PHANTOM, list(
                    builtIn(PhantomHeadModel.ID, "phantom"),
                    BuiltInHeadModel(PhantomHeadModel.ID, eyes("phantom_eyes"))
                )
            ),
            id("pig") to HeadModelOverride(VTTextures.PIG, builtIn(PigHeadModel.ID, "pig/pig")),
            id("piglin_brute") to HeadModelOverride(
                VTTextures.PIGLIN_BRUTE,
                vanilla(SkullBlock.Types.PIGLIN, "piglin/piglin_brute")
            ),
            id("illager/pillager") to HeadModelOverride(
                VTTextures.PILLAGER,
                builtIn(IllagerHeadModel.ID, "illager/pillager")
            ),
            id("polarbear") to HeadModelOverride(
                VTTextures.POLAR_BEAR,
                builtIn(PolarBearHeadModel.ID, "bear/polarbear")
            ),
            id("pufferfish") to HeadModelOverride(
                VTTextures.PUFFERFISH,
                builtIn(PufferfishHeadModel.ID, "fish/pufferfish")
            ),
            // Rabbit
            id("rabbit/brown") to HeadModelOverride(
                VTTextures.BROWN_RABBIT,
                builtIn(RabbitHeadModel.ID, "rabbit/brown")
            ),
            id("rabbit/white") to HeadModelOverride(
                VTTextures.WHITE_RABBIT,
                builtIn(RabbitHeadModel.ID, "rabbit/white")
            ),
            id("rabbit/black") to HeadModelOverride(
                VTTextures.BLACK_RABBIT,
                builtIn(RabbitHeadModel.ID, "rabbit/black")
            ),
            id("rabbit/white_splotched") to HeadModelOverride(
                VTTextures.BLACK_AND_WHITE_RABBIT,
                builtIn(RabbitHeadModel.ID, "rabbit/white_splotched")
            ),
            id("rabbit/gold") to HeadModelOverride(VTTextures.GOLD_RABBIT, builtIn(RabbitHeadModel.ID, "rabbit/gold")),
            id("rabbit/salt") to HeadModelOverride(
                VTTextures.SALT_AND_PEPPER_RABBIT,
                builtIn(RabbitHeadModel.ID, "rabbit/salt")
            ),
            id("rabbit/toast") to HeadModelOverride(
                VTTextures.TOAST_RABBIT,
                builtIn(RabbitHeadModel.ID, "rabbit/toast")
            ),
            id("rabbit/caerbannog") to HeadModelOverride(
                VTTextures.THE_KILLER_BUNNY,
                builtIn(RabbitHeadModel.ID, "rabbit/caerbannog")
            ),

            id("illager/ravager") to HeadModelOverride(
                VTTextures.RAVAGER,
                builtIn(RavagerHeadModel.ID, "illager/ravager")
            ),
            id("salmon") to HeadModelOverride(VTTextures.SALMON, builtIn(SalmonHeadModel.ID, "fish/salmon")),
            // Sheep
            id("sheep/white") to HeadModelOverride(VTTextures.WHITE_SHEEP, builtIn(SheepHeadModel.ID, "sheep/sheep")),
            id("sheep/light_gray") to HeadModelOverride(
                VTTextures.LIGHT_GRAY_SHEEP,
                builtIn(SheepHeadModel.ID, "sheep/sheep")
            ),
            id("sheep/gray") to HeadModelOverride(VTTextures.GRAY_SHEEP, builtIn(SheepHeadModel.ID, "sheep/sheep")),
            id("sheep/black") to HeadModelOverride(VTTextures.BLACK_SHEEP, builtIn(SheepHeadModel.ID, "sheep/sheep")),
            id("sheep/brown") to HeadModelOverride(VTTextures.BROWN_SHEEP, builtIn(SheepHeadModel.ID, "sheep/sheep")),
            id("sheep/red") to HeadModelOverride(VTTextures.RED_SHEEP, builtIn(SheepHeadModel.ID, "sheep/sheep")),
            id("sheep/orange") to HeadModelOverride(VTTextures.ORANGE_SHEEP, builtIn(SheepHeadModel.ID, "sheep/sheep")),
            id("sheep/yellow") to HeadModelOverride(VTTextures.YELLOW_SHEEP, builtIn(SheepHeadModel.ID, "sheep/sheep")),
            id("sheep/lime") to HeadModelOverride(VTTextures.LIME_SHEEP, builtIn(SheepHeadModel.ID, "sheep/sheep")),
            id("sheep/green") to HeadModelOverride(VTTextures.GREEN_SHEEP, builtIn(SheepHeadModel.ID, "sheep/sheep")),
            id("sheep/cyan") to HeadModelOverride(VTTextures.CYAN_SHEEP, builtIn(SheepHeadModel.ID, "sheep/sheep")),
            id("sheep/light_blue") to HeadModelOverride(
                VTTextures.LIGHT_BLUE_SHEEP,
                builtIn(SheepHeadModel.ID, "sheep/sheep")
            ),
            id("sheep/blue") to HeadModelOverride(VTTextures.BLUE_SHEEP, builtIn(SheepHeadModel.ID, "sheep/sheep")),
            id("sheep/purple") to HeadModelOverride(VTTextures.PURPLE_SHEEP, builtIn(SheepHeadModel.ID, "sheep/sheep")),
            id("sheep/magenta") to HeadModelOverride(
                VTTextures.MAGENTA_SHEEP,
                builtIn(SheepHeadModel.ID, "sheep/sheep")
            ),
            id("sheep/pink") to HeadModelOverride(VTTextures.PINK_SHEEP, builtIn(SheepHeadModel.ID, "sheep/sheep")),
            id("sheep/jeb_") to HeadModelOverride(VTTextures.JEB__SHEEP, builtIn(SheepHeadModel.ID, "sheep/sheep")),

            id("shulker") to HeadModelOverride(VTTextures.SHULKER, builtIn(ShulkerHeadModel.ID, "shulker/shulker")),
            id("silverfish") to HeadModelOverride(VTTextures.SILVERFISH, builtIn(SilverfishHeadModel.ID, "silverfish")),
            id("horse/skeleton") to HeadModelOverride(
                VTTextures.SKELETON_HORSE,
                builtIn(HorseHeadModel.ID, "horse/horse_skeleton")
            ),

            id("slime") to HeadModelOverride(
                VTTextures.SLIME, list(
                    builtIn(SlimeHeadModel.ID, "slime/slime"),
                    VanillaHeadModel(SkullBlock.Types.SKELETON, entityTranslucent("slime/slime"))
                )
            ),

            id("sniffer") to HeadModelOverride(VTTextures.SNIFFER, builtIn(SnifferHeadModel.ID, "sniffer/sniffer")),
            id("snow_golem") to HeadModelOverride(VTTextures.SNOW_GOLEM, builtIn(SnowGolemHeadModel.ID, "snow_golem")),
            id("spider") to HeadModelOverride(
                VTTextures.SPIDER, list(
                    builtIn(SpiderHeadModel.ID, "spider/spider"),
                    BuiltInHeadModel(SpiderHeadModel.ID, eyes("spider_eyes"))
                )
            ),
            id("squid") to HeadModelOverride(VTTextures.SQUID, builtIn(SquidHeadModel.ID, "squid/squid")),

            id("skeleton/stray") to HeadModelOverride(
                VTTextures.STRAY, list(
                    vanilla(SkullBlock.Types.SKELETON, "skeleton/stray"),
                    vanilla(SkullBlock.Types.SKELETON, "skeleton/stray_overlay")
                )
            ),

            id("strider") to HeadModelOverride(VTTextures.STRIDER, builtIn(StriderHeadModel.ID, "strider/strider")),
            id("strider_cold") to HeadModelOverride(
                VTTextures.COLD_STRIDER,
                builtIn(StriderHeadModel.ID, "strider/strider_cold")
            ),
            id("tadpole") to HeadModelOverride(VTTextures.TADPOLE, builtIn(TadpoleHeadModel.ID, "tadpole/tadpole")),
            // Trader Llama
            id("trader_llama/creamy") to HeadModelOverride(
                VTTextures.CREAMY_TRADER_LLAMA,
                builtIn(LlamaHeadModel.ID, "llama/creamy")
            ),
            id("trader_llama/white") to HeadModelOverride(
                VTTextures.WHITE_TRADER_LLAMA,
                builtIn(LlamaHeadModel.ID, "llama/white")
            ),
            id("trader_llama/brown") to HeadModelOverride(
                VTTextures.BROWN_TRADER_LLAMA,
                builtIn(LlamaHeadModel.ID, "llama/brown")
            ),
            id("trader_llama/gray") to HeadModelOverride(
                VTTextures.GRAY_TRADER_LLAMA,
                builtIn(LlamaHeadModel.ID, "llama/gray")
            ),

            id("tropical") to HeadModelOverride(
                VTTextures.TROPICAL_FISH,
                builtIn(TropicalFishHeadModel.ID, "fish/tropical_b")
            ),
            id("sea_turtle") to HeadModelOverride(
                VTTextures.TURTLE,
                builtIn(TurtleHeadModel.ID, "turtle/big_sea_turtle")
            ),
            // Vex
            id("illager/vex") to HeadModelOverride(VTTextures.VEX, builtIn(AllayHeadModel.ID, "illager/vex", 15)),
            id("illager/vex_charging") to HeadModelOverride(
                VTTextures.VEX_CHARGING,
                builtIn(AllayHeadModel.ID, "illager/vex_charging", 15)
            ),
            // Villager
            id("villager/villager") to HeadModelOverride(
                VTTextures.VILLAGER,
                builtIn(VillagerHeadModel.ID, "villager/villager")
            ),
            id("villager/armorer") to HeadModelOverride(
                VTTextures.ARMORER_VILLAGER,
                builtIn(VillagerHeadModel.ID, "villager/villager")
            ),
            id("villager/butcher") to HeadModelOverride(
                VTTextures.BUTCHER_VILLAGER,
                builtIn(VillagerHeadModel.ID, "villager/villager")
            ),
            id("villager/cartographer") to HeadModelOverride(
                VTTextures.CARTOGRAPHER_VILLAGER,
                builtIn(VillagerHeadModel.ID, "villager/villager")
            ),
            id("villager/cleric") to HeadModelOverride(
                VTTextures.CLERIC_VILLAGER,
                builtIn(VillagerHeadModel.ID, "villager/villager")
            ),
            id("villager/farmer") to HeadModelOverride(
                VTTextures.FARMER_VILLAGER,
                builtIn(VillagerHeadModel.ID, "villager/villager")
            ),
            id("villager/fisherman") to HeadModelOverride(
                VTTextures.FISHERMAN_VILLAGER,
                builtIn(VillagerHeadModel.ID, "villager/villager")
            ),
            id("villager/fletcher") to HeadModelOverride(
                VTTextures.FLETCHER_VILLAGER,
                builtIn(VillagerHeadModel.ID, "villager/villager")
            ),
            id("villager/leatherworker") to HeadModelOverride(
                VTTextures.LEATHERWORKER_VILLAGER,
                builtIn(VillagerHeadModel.ID, "villager/villager")
            ),
            id("villager/librarian") to HeadModelOverride(
                VTTextures.LIBRARIAN_VILLAGER,
                builtIn(VillagerHeadModel.ID, "villager/villager")
            ),
            id("villager/mason") to HeadModelOverride(
                VTTextures.MASON_VILLAGER,
                builtIn(VillagerHeadModel.ID, "villager/villager")
            ),
            id("villager/nitwit") to HeadModelOverride(
                VTTextures.NITWIT_VILLAGER,
                builtIn(VillagerHeadModel.ID, "villager/villager")
            ),
            id("villager/shepherd") to HeadModelOverride(
                VTTextures.SHEPHERD_VILLAGER,
                builtIn(VillagerHeadModel.ID, "villager/villager")
            ),
            id("villager/toolsmith") to HeadModelOverride(
                VTTextures.TOOLSMITH_VILLAGER,
                builtIn(VillagerHeadModel.ID, "villager/villager")
            ),
            id("villager/weaponsmith") to HeadModelOverride(
                VTTextures.WEAPONSMITH_VILLAGER,
                builtIn(VillagerHeadModel.ID, "villager/villager")
            ),

            id("illager/vindicator") to HeadModelOverride(
                VTTextures.VINDICATOR,
                builtIn(IllagerHeadModel.ID, "illager/vindicator")
            ),
            id("wandering_trader") to HeadModelOverride(
                VTTextures.WANDERING_TRADER,
                builtIn(VillagerHeadModel.ID, "wandering_trader")
            ),
            id("warden") to HeadModelOverride(VTTextures.WARDEN, builtIn(WardenHeadModel.ID, "warden/warden")),

            id("witch") to HeadModelOverride(VTTextures.WITCH, builtIn(WitchHeadModel.ID, "witch")),
            // Wolf
            id("wolf/wolf") to HeadModelOverride(VTTextures.PALE_WOLF, builtIn(WolfHeadModel.ID, "wolf/wolf")),
            id("wolf/wolf_angry") to HeadModelOverride(
                VTTextures.ANGRY_PALE_WOLF,
                builtIn(WolfHeadModel.ID, "wolf/wolf_angry")
            ),
            id("wolf/wolf_spotted") to HeadModelOverride(
                VTTextures.SPOTTY_WOLF,
                builtIn(WolfHeadModel.ID, "wolf/wolf_spotted")
            ),
            id("wolf/wolf_spotted_angry") to HeadModelOverride(
                VTTextures.ANGRY_SPOTTY_WOLF,
                builtIn(WolfHeadModel.ID, "wolf/wolf_spotted_angry")
            ),
            id("wolf/wolf_snowy") to HeadModelOverride(
                VTTextures.SNOWY_WOLF,
                builtIn(WolfHeadModel.ID, "wolf/wolf_snowy")
            ),
            id("wolf/wolf_snowy_angry") to HeadModelOverride(
                VTTextures.ANGRY_SNOWY_WOLF,
                builtIn(WolfHeadModel.ID, "wolf/wolf_snowy_angry")
            ),
            id("wolf/wolf_black") to HeadModelOverride(
                VTTextures.BLACK_WOLF,
                builtIn(WolfHeadModel.ID, "wolf/wolf_black")
            ),
            id("wolf/wolf_black_angry") to HeadModelOverride(
                VTTextures.ANGRY_BLACK_WOLF,
                builtIn(WolfHeadModel.ID, "wolf/wolf_black_angry")
            ),
            id("wolf/wolf_ashen") to HeadModelOverride(
                VTTextures.ASHEN_WOLF,
                builtIn(WolfHeadModel.ID, "wolf/wolf_ashen")
            ),
            id("wolf/wolf_ashen_angry") to HeadModelOverride(
                VTTextures.ANGRY_ASHEN_WOLF,
                builtIn(WolfHeadModel.ID, "wolf/wolf_ashen_angry")
            ),
            id("wolf/wolf_rusty") to HeadModelOverride(
                VTTextures.RUSTY_WOLF,
                builtIn(WolfHeadModel.ID, "wolf/wolf_rusty")
            ),
            id("wolf/wolf_rusty_angry") to HeadModelOverride(
                VTTextures.ANGRY_RUSTY_WOLF,
                builtIn(WolfHeadModel.ID, "wolf/wolf_rusty_angry")
            ),
            id("wolf/wolf_woods") to HeadModelOverride(
                VTTextures.WOODS_WOLF,
                builtIn(WolfHeadModel.ID, "wolf/wolf_woods")
            ),
            id("wolf/wolf_woods_angry") to HeadModelOverride(
                VTTextures.ANGRY_WOODS_WOLF,
                builtIn(WolfHeadModel.ID, "wolf/wolf_woods_angry")
            ),
            id("wolf/wolf_chestnut") to HeadModelOverride(
                VTTextures.CHESTNUT_WOLF,
                builtIn(WolfHeadModel.ID, "wolf/wolf_chestnut")
            ),
            id("wolf/wolf_chestnut_angry") to HeadModelOverride(
                VTTextures.ANGRY_CHESTNUT_WOLF,
                builtIn(WolfHeadModel.ID, "wolf/wolf_chestnut_angry")
            ),
            id("wolf/wolf_striped") to HeadModelOverride(
                VTTextures.STRIPED_WOLF,
                builtIn(WolfHeadModel.ID, "wolf/wolf_striped")
            ),
            id("wolf/wolf_striped_angry") to HeadModelOverride(
                VTTextures.ANGRY_STRIPED_WOLF,
                builtIn(WolfHeadModel.ID, "wolf/wolf_striped_angry")
            ),

            id("zoglin") to HeadModelOverride(VTTextures.ZOGLIN, builtIn(HoglinHeadModel.ID, "hoglin/zoglin")),
            id("horse/zombie") to HeadModelOverride(
                VTTextures.ZOMBIE_HORSE,
                builtIn(HorseHeadModel.ID, "horse/horse_zombie")
            ),
            // Zombie Villager
            id("zombie_villager/villager") to HeadModelOverride(
                VTTextures.ZOMBIE_VILLAGER,
                builtIn(VillagerHeadModel.ID, "zombie_villager/zombie_villager")
            ),
            id("zombie_villager/armorer") to HeadModelOverride(
                VTTextures.ZOMBIE_ARMORER,
                builtIn(VillagerHeadModel.ID, "zombie_villager/zombie_villager")
            ),
            id("zombie_villager/butcher") to HeadModelOverride(
                VTTextures.ZOMBIE_BUTCHER,
                builtIn(VillagerHeadModel.ID, "zombie_villager/zombie_villager")
            ),
            id("zombie_villager/cartographer") to HeadModelOverride(
                VTTextures.ZOMBIE_CARTOGRAPHER,
                builtIn(VillagerHeadModel.ID, "zombie_villager/zombie_villager")
            ),
            id("zombie_villager/cleric") to HeadModelOverride(
                VTTextures.ZOMBIE_CLERIC,
                builtIn(VillagerHeadModel.ID, "zombie_villager/zombie_villager")
            ),
            id("zombie_villager/farmer") to HeadModelOverride(
                VTTextures.ZOMBIE_FARMER,
                builtIn(VillagerHeadModel.ID, "zombie_villager/zombie_villager")
            ),
            id("zombie_villager/fisherman") to HeadModelOverride(
                VTTextures.ZOMBIE_FISHERMAN,
                builtIn(VillagerHeadModel.ID, "zombie_villager/zombie_villager")
            ),
            id("zombie_villager/fletcher") to HeadModelOverride(
                VTTextures.ZOMBIE_FLETCHER,
                builtIn(VillagerHeadModel.ID, "zombie_villager/zombie_villager")
            ),
            id("zombie_villager/leatherworker") to HeadModelOverride(
                VTTextures.ZOMBIE_LEATHERWORKER,
                builtIn(VillagerHeadModel.ID, "zombie_villager/zombie_villager")
            ),
            id("zombie_villager/librarian") to HeadModelOverride(
                VTTextures.ZOMBIE_LIBRARIAN,
                builtIn(VillagerHeadModel.ID, "zombie_villager/zombie_villager")
            ),
            id("zombie_villager/mason") to HeadModelOverride(
                VTTextures.ZOMBIE_MASON,
                builtIn(VillagerHeadModel.ID, "zombie_villager/zombie_villager")
            ),
            id("zombie_villager/nitwit") to HeadModelOverride(
                VTTextures.ZOMBIE_NITWIT,
                builtIn(VillagerHeadModel.ID, "zombie_villager/zombie_villager")
            ),
            id("zombie_villager/shepherd") to HeadModelOverride(
                VTTextures.ZOMBIE_SHEPHERD,
                builtIn(VillagerHeadModel.ID, "zombie_villager/zombie_villager")
            ),
            id("zombie_villager/toolsmith") to HeadModelOverride(
                VTTextures.ZOMBIE_TOOLSMITH,
                builtIn(VillagerHeadModel.ID, "zombie_villager/zombie_villager")
            ),
            id("zombie_villager/weaponsmith") to HeadModelOverride(
                VTTextures.ZOMBIE_WEAPONSMITH,
                builtIn(VillagerHeadModel.ID, "zombie_villager/zombie_villager")
            ),

            id("zombified_piglin") to HeadModelOverride(
                VTTextures.ZOMBIFIED_PIGLIN,
                vanilla(SkullBlock.Types.PIGLIN, "piglin/zombified_piglin")
            ),

            id("debug") to HeadModelOverride(MiscTextures.TEST_TEX, builtIn(CamelWithNeckHeadModel.ID, "camel/camel")),
        )
        modelMap.forEach(dataBuilder::create)
    }

    companion object {
        fun list(vararg model: HeadModel) = ListHeadModel(*model)

        fun vanilla(type: SkullBlock.Type, texture: String, lightLevel: Int? = null) =
            VanillaHeadModel(type, entityBasic(texture), lightLevel)

        fun builtIn(id: ResourceLocation, texture: String, lightLevel: Int? = null) =
            BuiltInHeadModel(id, entityBasic(texture), lightLevel)

        fun textured(texture: String, id: ResourceLocation): RenderTypeProvider =
            TexturedRenderTypeProvider(id, mc("textures/entity/${texture}"))

        fun entityBasic(texture: String): RenderTypeProvider =
            textured(texture, TexturedRenderTypeProvider.ENTITY_CUTOUT_NO_CULL_Z_OFFSET)

        fun entityTranslucent(texture: String): RenderTypeProvider =
            textured(texture, TexturedRenderTypeProvider.ENTITY_TRANSLUCENT)

        fun eyes(texture: String): RenderTypeProvider =
            textured(texture, TexturedRenderTypeProvider.EYES)
    }
}