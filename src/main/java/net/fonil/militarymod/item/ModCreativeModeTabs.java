package net.fonil.militarymod.item;

import java.util.function.Supplier;
import net.fonil.militarymod.MilitaryMod;
import net.fonil.militarymod.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component; // ADD THIS
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModCreativeModeTabs {
  public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
      DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MilitaryMod.MODID);

  public static final Supplier<CreativeModeTab> MILITARY_ITEMS_TAB =
      CREATIVE_MODE_TAB.register(
          "military_items_tab",
          () ->
              CreativeModeTab.builder()
                  .icon(() -> new ItemStack(ModItems.PISTOL.get()))
                  .title(Component.translatable("creativetab.militarymod.military_items"))
                  .displayItems(
                      (itemDisplayParameters, output) -> {
                        output.accept(ModItems.PISTOL);
                        output.accept(ModItems.PISTOL_AMMO);
                        output.accept(ModItems.RIFLE);
                        output.accept(ModItems.RIFLE_AMMO);
                        output.accept(ModItems.RAW_LEAD);
                        output.accept(ModItems.LEAD_INGOT);
                        output.accept(ModItems.LEAD_NUGGET);
                      })
                  .build());

  public static final Supplier<CreativeModeTab> MILITARY_BLOCK_TAB =
      CREATIVE_MODE_TAB.register(
          "military_blocks_tab",
          () ->
              CreativeModeTab.builder()
                  .icon(() -> new ItemStack(ModBlocks.LEAD_BLOCK))
                  .withTabsBefore(
                      ResourceLocation.fromNamespaceAndPath(MilitaryMod.MODID, "military_items_tab"))
                  .title(Component.translatable("creativetab.militarymod.military_blocks"))
                  .displayItems(
                      (itemDisplayParameters, output) -> {
                        output.accept(ModBlocks.LEAD_BLOCK);
                        output.accept(ModBlocks.LEAD_ORE);
                        output.accept(ModBlocks.LEAD_DEEPSLATE_ORE);
                      })
                  .build());

  public static void register(IEventBus eventBus) {
    CREATIVE_MODE_TAB.register(eventBus);
  }
}
