package net.fonil.militarymod.datagen;

import net.fonil.militarymod.MilitaryMod;
import net.fonil.militarymod.item.ModItems;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ModItemModelProvider extends ItemModelProvider {
  public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
    super(output, MilitaryMod.MODID, existingFileHelper);
  }

  @Override
  protected void registerModels() {
    basicItem(ModItems.LEAD_INGOT.get());
    basicItem(ModItems.LEAD_NUGGET.get());
    basicItem(ModItems.RAW_LEAD.get());
    basicItem(ModItems.RIFLE.get());
    basicItem(ModItems.RIFLE_AMMO.get());
		basicItem(ModItems.SNIPER_RIFLE.get());
    basicItem(ModItems.SNIPER_RIFLE_AMMO.get());
		basicItem(ModItems.EXPLOSIVE_SNIPER_RIFLE.get());
    basicItem(ModItems.EXPLOSIVE_SNIPER_RIFLE_AMMO.get());
		basicItem(ModItems.ROCKET_LAUNCHER.get());
		basicItem(ModItems.ROCKET.get());
    basicItem(ModItems.PISTOL.get());
    basicItem(ModItems.PISTOL_AMMO.get());
    basicItem(ModItems.LEAD_HELMET.get());
    basicItem(ModItems.LEAD_CHESTPLATE.get());
    basicItem(ModItems.LEAD_LEGGINGS.get());
    basicItem(ModItems.LEAD_BOOTS.get());
		basicItem(ModItems.KNIFE.get());
		basicItem(ModItems.GUN_SCOPE.get());
		basicItem(ModItems.GUN_BARREL.get());
		basicItem(ModItems.GUN_HANDLE.get());
		basicItem(ModItems.GUN_MAGAZINE.get());
		basicItem(ModItems.KEVLAR_FIBER_PIECE.get());
		basicItem(ModItems.BULLETPROOF_VEST.get());
		basicItem(ModItems.BULLETPROOF_HELMET.get());
  }
}
