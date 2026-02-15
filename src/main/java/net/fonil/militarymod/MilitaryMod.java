package net.fonil.militarymod;

import com.mojang.logging.LogUtils;
import net.fonil.militarymod.block.ModBlocks;
import net.fonil.militarymod.entity.ModEntities;
import net.fonil.militarymod.item.ModCreativeModeTabs;
import net.fonil.militarymod.item.ModItems;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import org.slf4j.Logger;

@Mod(MilitaryMod.MODID)
public class MilitaryMod {
  public static final String MODID = "militarymod";
  public static final Logger LOGGER = LogUtils.getLogger();

  public MilitaryMod(IEventBus modEventBus, ModContainer modContainer) {
    modEventBus.addListener(this::commonSetup);

    NeoForge.EVENT_BUS.register(this);

    // modEventBus.addListener(this::addCreative);

    ModCreativeModeTabs.register(modEventBus);
    ModEntities.register(modEventBus);
    ModItems.register(modEventBus);
    ModBlocks.register(modEventBus);

    modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
  }

  private void commonSetup(FMLCommonSetupEvent event) {
    LOGGER.info("HELLO FROM COMMON SETUP");
    if (Config.LOG_DIRT_BLOCK.getAsBoolean()) {
      LOGGER.info("DIRT BLOCK >> {}", BuiltInRegistries.BLOCK.getKey(Blocks.DIRT));
    }

    LOGGER.info("{}{}", Config.MAGIC_NUMBER_INTRODUCTION.get(), Config.MAGIC_NUMBER.getAsInt());

    Config.ITEM_STRINGS.get().forEach((item) -> LOGGER.info("ITEM >> {}", item));
  }

  // private void addCreative(BuildCreativeModeTabContentsEvent event) {
  // if (event.getTabKey() == CreativeModeTabs.COMBAT) {
  // 	event.accept(ModItems.PISTOL);
  // 	event.accept(ModItems.PISTOL_AMMO);
  // 	event.accept(ModItems.RIFLE);
  // 	event.accept(ModItems.RIFLE_AMMO);
  // }
  //
  // if (event.getTabKey() == CreativeModeTabs.NATURAL_BLOCKS) {
  // 	event.accept(ModBlocks.LEAD_ORE);
  // 	event.accept(ModBlocks.LEAD_DEEPSLATE_ORE);
  // 	event.accept(ModBlocks.LEAD_BLOCK);
  // }
  //
  // if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
  // 	event.accept(ModItems.RAW_LEAD);
  // 	event.accept(ModItems.LEAD_INGOT);
  // 	event.accept(ModItems.LEAD_NUGGET);
  // }
  //
  // }

  @SubscribeEvent
  public void onServerStarting(ServerStartingEvent event) {
    LOGGER.info("HELLO from server starting");
  }
}
