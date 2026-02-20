package net.fonil.militarymod.datagen;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import net.fonil.militarymod.MilitaryMod;
import net.fonil.militarymod.block.ModBlocks;
import net.fonil.militarymod.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
  public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
    super(output, registries);
  }

  @Override
  protected void buildRecipes(RecipeOutput recipeOutput) {
    List<ItemLike> LEAD_SMELTABLES =
        List.of(
            ModItems.RAW_LEAD.get(), ModBlocks.LEAD_ORE.get(), ModBlocks.LEAD_DEEPSLATE_ORE.get());

    oreSmelting(
        recipeOutput,
        LEAD_SMELTABLES,
        RecipeCategory.MISC,
        ModItems.LEAD_INGOT.get(),
        0.7f,
        200,
        "lead");

    // --- ARMOR ---
    ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.LEAD_HELMET.get())
        .pattern("LLL")
        .pattern("LDL")
        .pattern("   ")
        .define('L', ModItems.LEAD_INGOT.get())
        .define('D', Items.DIAMOND)
        .unlockedBy("has_lead_ingot", has(ModItems.LEAD_INGOT.get()))
        .save(recipeOutput);

    ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.LEAD_CHESTPLATE.get())
        .pattern("LDL")
        .pattern("LLL")
        .pattern("LLL")
        .define('L', ModItems.LEAD_INGOT.get())
        .define('D', Items.DIAMOND)
        .unlockedBy("has_lead_ingot", has(ModItems.LEAD_INGOT.get()))
        .save(recipeOutput);

    ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.LEAD_LEGGINGS.get())
        .pattern("LLL")
        .pattern("LDL")
        .pattern("L L")
        .define('L', ModItems.LEAD_INGOT.get())
        .define('D', Items.DIAMOND)
        .unlockedBy("has_lead_ingot", has(ModItems.LEAD_INGOT.get()))
        .save(recipeOutput);

    ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.LEAD_BOOTS.get())
        .pattern("   ")
        .pattern("L L")
        .pattern("LDL")
        .define('L', ModItems.LEAD_INGOT.get())
        .define('D', Items.DIAMOND)
        .unlockedBy("has_lead_ingot", has(ModItems.LEAD_INGOT.get()))
        .save(recipeOutput);

    ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.BULLETPROOF_HELMET.get())
        .pattern("IKI")
        .pattern("K K")
        .pattern(" L ")
        .define('K', ModItems.KEVLAR_FIBER_PIECE.get())
        .define('I', Items.IRON_INGOT)
        .define('L', Items.LEATHER)
        .unlockedBy("has_kevlar_fiber_piece", has(ModItems.KEVLAR_FIBER_PIECE.get()))
        .save(recipeOutput);

    ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.BULLETPROOF_VEST.get())
        .pattern("L L")
        .pattern("KKK")
        .pattern("IKI")
        .define('K', ModItems.KEVLAR_FIBER_PIECE.get())
        .define('I', Items.IRON_INGOT)
        .define('L', Items.LEATHER)
        .unlockedBy("has_kevlar_fiber_piece", has(ModItems.KEVLAR_FIBER_PIECE.get()))
        .save(recipeOutput);

    ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.KEVLAR_FIBER_PIECE.get(), 1)
        .requires(Items.DIAMOND, 4)
        .requires(Items.NETHERITE_SCRAP, 4)
        .unlockedBy("has_diamod", has(Items.DIAMOND))
        .save(recipeOutput);

    // --- AMMO ---
    ShapelessRecipeBuilder.shapeless(RecipeCategory.COMBAT, ModItems.PISTOL_AMMO.get(), 4)
        .requires(Items.GUNPOWDER, 1)
        .requires(ModItems.LEAD_NUGGET.get())
        .unlockedBy("has_gunpowder", has(Items.GUNPOWDER))
        .save(recipeOutput);

    ShapelessRecipeBuilder.shapeless(RecipeCategory.COMBAT, ModItems.RIFLE_AMMO.get(), 4)
        .requires(Items.GUNPOWDER, 2)
        .requires(ModItems.LEAD_NUGGET.get())
        .unlockedBy("has_gunpowder", has(Items.GUNPOWDER))
        .save(recipeOutput);

    ShapelessRecipeBuilder.shapeless(RecipeCategory.COMBAT, ModItems.SNIPER_RIFLE_AMMO.get(), 4)
        .requires(Items.GUNPOWDER, 3)
        .requires(ModItems.LEAD_NUGGET.get())
        .unlockedBy("has_gunpowder", has(Items.GUNPOWDER))
        .save(recipeOutput);

    ShapelessRecipeBuilder.shapeless(
            RecipeCategory.COMBAT, ModItems.EXPLOSIVE_SNIPER_RIFLE_AMMO.get(), 4)
        .requires(Items.TNT, 1)
        .requires(Items.MAGMA_CREAM, 1)
        .requires(ModItems.LEAD_NUGGET.get())
        .unlockedBy("has_gunpowder", has(Items.GUNPOWDER))
        .save(recipeOutput);

    ShapelessRecipeBuilder.shapeless(RecipeCategory.COMBAT, ModItems.ROCKET.get(), 1)
        .requires(Items.TNT, 2)
        .requires(Items.MAGMA_CREAM, 1)
        .requires(ModItems.LEAD_INGOT.get())
        .unlockedBy("has_gunpowder", has(Items.GUNPOWDER))
        .save(recipeOutput);

    // --- BLOCKS & INGOTS ---
    ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.LEAD_BLOCK.get())
        .pattern("LLL")
        .pattern("LLL")
        .pattern("LLL")
        .define('L', ModItems.LEAD_INGOT.get())
        .unlockedBy("has_lead_ingot", has(ModItems.LEAD_INGOT.get()))
        .save(recipeOutput);

    ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.LEAD_INGOT.get(), 9)
        .requires(ModBlocks.LEAD_BLOCK.get())
        .unlockedBy("has_lead_block", has(ModBlocks.LEAD_BLOCK.get()))
        .save(recipeOutput, "militarymod:lead_ingot_from_lead_block");

    ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.LEAD_INGOT.get())
        .pattern("LLL")
        .pattern("LLL")
        .pattern("LLL")
        .define('L', ModItems.LEAD_NUGGET.get())
        .unlockedBy("has_lead_nugget", has(ModItems.LEAD_NUGGET.get()))
        .save(recipeOutput, "militarymod:lead_ingot_from_nugget");

    ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.LEAD_NUGGET.get(), 9)
        .requires(ModItems.LEAD_INGOT.get())
        .unlockedBy("has_lead_ingot", has(ModItems.LEAD_INGOT.get()))
        .save(recipeOutput);

    // --- GUN PARTS ---
    ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.GUN_SCOPE.get())
        .pattern("D  ")
        .pattern(" S ")
        .pattern("  L")
        .define('D', Items.DIAMOND)
        .define('L', ModItems.LEAD_INGOT.get())
        .define('S', Items.SPYGLASS)
        .unlockedBy("has_lead_ingot", has(ModItems.LEAD_INGOT.get()))
        .save(recipeOutput);

    ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.GUN_HANDLE.get())
        .pattern("   ")
        .pattern(" DL")
        .pattern("  L")
        .define('D', Items.DIAMOND)
        .define('L', ModItems.LEAD_INGOT.get())
        .unlockedBy("has_lead_ingot", has(ModItems.LEAD_INGOT.get()))
        .save(recipeOutput);

    ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.GUN_MAGAZINE.get())
        .pattern("   ")
        .pattern(" D ")
        .pattern(" L ")
        .define('D', Items.DIAMOND)
        .define('L', ModItems.LEAD_INGOT.get())
        .unlockedBy("has_lead_ingot", has(ModItems.LEAD_INGOT.get()))
        .save(recipeOutput);

    ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.GUN_BARREL.get())
        .pattern("   ")
        .pattern("LDL")
        .pattern("   ")
        .define('D', Items.DIAMOND)
        .define('L', ModItems.LEAD_INGOT.get())
        .unlockedBy("has_lead_ingot", has(ModItems.LEAD_INGOT.get()))
        .save(recipeOutput);

    // --- WEAPONS ---

    ShapelessRecipeBuilder.shapeless(RecipeCategory.COMBAT, ModItems.KNIFE.get(), 1)
        .requires(Items.STICK)
        .requires(ModItems.LEAD_INGOT.get())
        .unlockedBy("has_lead_ingot", has(ModItems.LEAD_INGOT.get()))
        .save(recipeOutput);

    ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.PISTOL.get())
        .pattern("   ")
        .pattern(" SB")
        .pattern(" HM")
        .define('S', ModItems.GUN_SCOPE.get())
        .define('B', ModItems.GUN_BARREL.get())
        .define('H', ModItems.GUN_HANDLE.get())
        .define('M', ModItems.GUN_MAGAZINE.get())
        .unlockedBy("has_lead_ingot", has(ModItems.LEAD_INGOT.get()))
        .save(recipeOutput);

    ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.RIFLE.get())
        .pattern("   ")
        .pattern("SBH")
        .pattern(" M ")
        .define('S', ModItems.GUN_SCOPE.get())
        .define('B', ModItems.GUN_BARREL.get())
        .define('H', ModItems.GUN_HANDLE.get())
        .define('M', ModItems.GUN_MAGAZINE.get())
        .unlockedBy("has_lead_ingot", has(ModItems.LEAD_INGOT.get()))
        .save(recipeOutput);

    ShapelessRecipeBuilder.shapeless(RecipeCategory.COMBAT, ModItems.SNIPER_RIFLE.get(), 1)
        .requires(ModItems.RIFLE.get())
        .requires(ModItems.GUN_SCOPE.get())
        .unlockedBy("has_rifle", has(ModItems.RIFLE.get()))
        .save(recipeOutput, "militarymod:sniper_rifle_upgrade");

    ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.SNIPER_RIFLE.get())
        .pattern(" S ")
        .pattern("BBH")
        .pattern(" M ")
        .define('S', ModItems.GUN_SCOPE.get())
        .define('B', ModItems.GUN_BARREL.get())
        .define('H', ModItems.GUN_HANDLE.get())
        .define('M', ModItems.GUN_MAGAZINE.get())
        .unlockedBy("has_lead_ingot", has(ModItems.LEAD_INGOT.get()))
        .save(recipeOutput);

    SmithingTransformRecipeBuilder.smithing(
            Ingredient.of(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE),
            Ingredient.of(ModItems.SNIPER_RIFLE.get()),
            Ingredient.of(Items.NETHERITE_INGOT),
            RecipeCategory.COMBAT,
            ModItems.EXPLOSIVE_SNIPER_RIFLE.get())
        .unlocks("has_sniper_rifle", has(ModItems.SNIPER_RIFLE.get()))
        .save(recipeOutput, MilitaryMod.MODID + ":explosive_sniper_rifle_smithing");

    SmithingTransformRecipeBuilder.smithing(
            Ingredient.of(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE),
            Ingredient.of(ModItems.EXPLOSIVE_SNIPER_RIFLE.get()),
            Ingredient.of(Items.NETHERITE_INGOT),
            RecipeCategory.COMBAT,
            ModItems.ROCKET_LAUNCHER.get())
        .unlocks("has_explosive_sniper_rifle", has(ModItems.EXPLOSIVE_SNIPER_RIFLE.get()))
        .save(recipeOutput, MilitaryMod.MODID + ":rocket_launcher_smithing");
  }

  protected static void oreSmelting(
      RecipeOutput recipeOutput,
      List<ItemLike> pIngredients,
      RecipeCategory pCategory,
      ItemLike pResult,
      float pExperience,
      int pCookingTIme,
      String pGroup) {
    oreCooking(
        recipeOutput,
        RecipeSerializer.SMELTING_RECIPE,
        SmeltingRecipe::new,
        pIngredients,
        pCategory,
        pResult,
        pExperience,
        pCookingTIme,
        pGroup,
        "_from_smelting");
  }

  protected static void oreBlasting(
      RecipeOutput recipeOutput,
      List<ItemLike> pIngredients,
      RecipeCategory pCategory,
      ItemLike pResult,
      float pExperience,
      int pCookingTime,
      String pGroup) {
    oreCooking(
        recipeOutput,
        RecipeSerializer.BLASTING_RECIPE,
        BlastingRecipe::new,
        pIngredients,
        pCategory,
        pResult,
        pExperience,
        pCookingTime,
        pGroup,
        "_from_blasting");
  }

  protected static <T extends AbstractCookingRecipe> void oreCooking(
      RecipeOutput recipeOutput,
      RecipeSerializer<T> pCookingSerializer,
      AbstractCookingRecipe.Factory<T> factory,
      List<ItemLike> pIngredients,
      RecipeCategory pCategory,
      ItemLike pResult,
      float pExperience,
      int pCookingTime,
      String pGroup,
      String pRecipeName) {
    for (ItemLike itemlike : pIngredients) {
      SimpleCookingRecipeBuilder.generic(
              Ingredient.of(itemlike),
              pCategory,
              pResult,
              pExperience,
              pCookingTime,
              pCookingSerializer,
              factory)
          .group(pGroup)
          .unlockedBy(getHasName(itemlike), has(itemlike))
          .save(
              recipeOutput,
              MilitaryMod.MODID
                  + ":"
                  + getItemName(pResult)
                  + pRecipeName
                  + "_"
                  + getItemName(itemlike));
    }
  }
}
