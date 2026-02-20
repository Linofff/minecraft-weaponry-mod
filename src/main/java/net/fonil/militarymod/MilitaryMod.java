package net.fonil.militarymod;

import com.mojang.logging.LogUtils;
import net.fonil.militarymod.block.ModBlocks;
import net.fonil.militarymod.entity.ModEntities;
import net.fonil.militarymod.item.ModCreativeModeTabs;
import net.fonil.militarymod.item.ModItems;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import org.slf4j.Logger;

@Mod(MilitaryMod.MODID)
public class MilitaryMod {
  public static final String MODID = "militarymod";
  public static final Logger LOGGER = LogUtils.getLogger();

  public MilitaryMod(IEventBus modEventBus, ModContainer modContainer) {
    NeoForge.EVENT_BUS.register(this);

    ModCreativeModeTabs.register(modEventBus);
    ModEntities.register(modEventBus);
    ModItems.register(modEventBus);
    ModBlocks.register(modEventBus);

    modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
  }

  @SubscribeEvent
  public void onServerStarting(ServerStartingEvent event) {
    LOGGER.info("HELLO from server starting");
  }
}
