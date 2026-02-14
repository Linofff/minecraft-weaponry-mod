package net.fonil.militarymod.worldgen;

import java.util.List;
import net.fonil.militarymod.MilitaryMod;
import net.fonil.militarymod.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

public class ModConfiguredFeatures {

  public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_LEAD_ORE_KEY =
      registerKey("lead_ore");

  // public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_LEAD_DEEPSLATE_ORE_KEY =
  // registerKey("lead_ore");

  public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
    RuleTest stoneReplaceables = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
    RuleTest deepslateReplaceables = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
    RuleTest netherrackReplaceables = new BlockMatchTest(Blocks.NETHERRACK);
    RuleTest endReplaceables = new BlockMatchTest(Blocks.END_STONE);

    List<OreConfiguration.TargetBlockState> overworldBismuthOres =
        List.of(
            OreConfiguration.target(
                stoneReplaceables, ModBlocks.LEAD_ORE.get().defaultBlockState()),
            OreConfiguration.target(
                deepslateReplaceables, ModBlocks.LEAD_DEEPSLATE_ORE.get().defaultBlockState()));

    register(
        context,
        OVERWORLD_LEAD_ORE_KEY,
        Feature.ORE,
        new OreConfiguration(overworldBismuthOres, 7));

  }

  public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
    return ResourceKey.create(
        Registries.CONFIGURED_FEATURE,
        ResourceLocation.fromNamespaceAndPath(MilitaryMod.MODID, name));
  }

  private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(
      BootstrapContext<ConfiguredFeature<?, ?>> context,
      ResourceKey<ConfiguredFeature<?, ?>> key,
      F feature,
      FC configuration) {
    context.register(key, new ConfiguredFeature<>(feature, configuration));
  }
}
