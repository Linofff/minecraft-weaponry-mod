package net.fonil.militarymod.item;

import net.fonil.militarymod.MilitaryMod;
import net.fonil.militarymod.item.custom.PistolItem;
import net.fonil.militarymod.item.custom.RifleItem;
import net.fonil.militarymod.item.custom.SniperRifleItem;
import net.minecraft.world.item.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
  public static final DeferredRegister.Items ITEMS =
      DeferredRegister.createItems(MilitaryMod.MODID);

  public static final DeferredItem<SwordItem> KNIFE =
      ITEMS.register(
          "knife",
          () ->
              new SwordItem(
                  ModToolTiers.LEAD,
new Item.Properties()
                      .attributes(SwordItem.createAttributes(ModToolTiers.LEAD, 10000, -2.4f))));

  public static final DeferredItem<Item> PISTOL =
      ITEMS.register("pistol", () -> new PistolItem(new Item.Properties().stacksTo(1)));
  public static final DeferredItem<Item> PISTOL_AMMO =
      ITEMS.register("pistol_ammo", () -> new Item(new Item.Properties()));

  public static final DeferredItem<Item> RIFLE =
      ITEMS.register("rifle", () -> new RifleItem(new Item.Properties().stacksTo(1)));
  public static final DeferredItem<Item> RIFLE_AMMO =
      ITEMS.register("rifle_ammo", () -> new Item(new Item.Properties()));

  public static final DeferredItem<Item> SNIPER_RIFLE =
      ITEMS.register("sniper_rifle", () -> new SniperRifleItem(new Item.Properties().stacksTo(1)));
  public static final DeferredItem<Item> SNIPER_RIFLE_AMMO =
      ITEMS.register("sniper_rifle_ammo", () -> new Item(new Item.Properties()));

  public static final DeferredItem<Item> RAW_LEAD =
      ITEMS.register("raw_lead", () -> new Item(new Item.Properties()));
  public static final DeferredItem<Item> LEAD_INGOT =
      ITEMS.register("lead_ingot", () -> new Item(new Item.Properties()));
  public static final DeferredItem<Item> LEAD_NUGGET =
      ITEMS.register("lead_nugget", () -> new Item(new Item.Properties()));

  public static final DeferredItem<ArmorItem> LEAD_HELMET =
      ITEMS.register(
          "lead_helmet",
          () ->
              new ArmorItem(
                  ModArmorMaterials.LEAD_ARMOR_MATERIAL,
                  ArmorItem.Type.HELMET,
                  new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(19))));
  public static final DeferredItem<ArmorItem> LEAD_CHESTPLATE =
      ITEMS.register(
          "lead_chestplate",
          () ->
              new ArmorItem(
                  ModArmorMaterials.LEAD_ARMOR_MATERIAL,
                  ArmorItem.Type.CHESTPLATE,
                  new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(19))));
  public static final DeferredItem<ArmorItem> LEAD_LEGGINGS =
      ITEMS.register(
          "lead_leggings",
          () ->
              new ArmorItem(
                  ModArmorMaterials.LEAD_ARMOR_MATERIAL,
                  ArmorItem.Type.LEGGINGS,
                  new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(19))));
  public static final DeferredItem<ArmorItem> LEAD_BOOTS =
      ITEMS.register(
          "lead_boots",
          () ->
              new ArmorItem(
                  ModArmorMaterials.LEAD_ARMOR_MATERIAL,
                  ArmorItem.Type.BOOTS,
                  new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(19))));

  public static void register(IEventBus eventBus) {
    ITEMS.register(eventBus);
  }
}
