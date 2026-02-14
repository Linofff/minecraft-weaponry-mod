package net.fonil.militarymod.item;

import net.fonil.militarymod.MilitaryMod;
import net.fonil.militarymod.item.custom.PistolItem;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
  public static final DeferredRegister.Items ITEMS =
      DeferredRegister.createItems(MilitaryMod.MODID);

  public static final DeferredItem<Item> PISTOL =
      ITEMS.register("pistol", () -> new PistolItem(new Item.Properties().stacksTo(1)));
  public static final DeferredItem<Item> PISTOL_AMMO =
      ITEMS.register("pistol_ammo", () -> new Item(new Item.Properties()));

  public static final DeferredItem<Item> RIFLE =
      ITEMS.register("rifle", () -> new Item(new Item.Properties().stacksTo(1)));
  public static final DeferredItem<Item> RIFLE_AMMO =
      ITEMS.register("rifle_ammo", () -> new Item(new Item.Properties()));

  public static final DeferredItem<Item> RAW_LEAD =
      ITEMS.register("raw_lead", () -> new Item(new Item.Properties()));
  public static final DeferredItem<Item> LEAD_INGOT =
      ITEMS.register("lead_ingot", () -> new Item(new Item.Properties()));
  public static final DeferredItem<Item> LEAD_NUGGET =
      ITEMS.register("lead_nugget", () -> new Item(new Item.Properties()));

  public static void register(IEventBus eventBus) {
    ITEMS.register(eventBus);
  }
}
