package net.fonil.militarymod.datagen;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import net.fonil.militarymod.MilitaryMod;
import net.fonil.militarymod.block.ModBlocks;
import net.fonil.militarymod.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
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

    ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.LEAD_BLOCK.get())
        .pattern("BBB")
        .pattern("BBB")
        .pattern("BBB")
        .define('B', ModItems.LEAD_INGOT.get())
        .unlockedBy("has_lead_ingot", has(ModItems.LEAD_INGOT.get()))
        .save(recipeOutput);

    ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.LEAD_INGOT.get(), 9)
        .requires(ModBlocks.LEAD_BLOCK.get())
        .unlockedBy("has_lead_block", has(ModBlocks.LEAD_BLOCK.get()))
        .save(recipeOutput, "militarymod:lead_ingot_from_lead_block");

    ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.LEAD_INGOT.get())
        .pattern("BBB")
        .pattern("BBB")
        .pattern("BBB")
        .define('B', ModItems.LEAD_NUGGET.get())
        .unlockedBy("has_lead_nugget", has(ModItems.LEAD_NUGGET.get()))
        .save(recipeOutput, "militarymod:lead_ingot_from_nugget");

    ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.LEAD_NUGGET.get(), 9)
        .requires(ModItems.LEAD_INGOT.get())
        .unlockedBy("has_lead_ingot", has(ModItems.LEAD_INGOT.get()))
        .save(recipeOutput);
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
