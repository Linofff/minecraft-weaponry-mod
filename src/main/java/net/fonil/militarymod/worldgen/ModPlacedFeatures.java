package net.fonil.militarymod.worldgen;

import java.util.List;
import net.fonil.militarymod.MilitaryMod;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

public class ModPlacedFeatures {

  public static final ResourceKey<PlacedFeature> LEAD_ORE_PLACED_KEY =
      registerKey("lead_ore_placed");

  public static void bootstrap(BootstrapContext<PlacedFeature> context) {
    var configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

    register(
        context,
        LEAD_ORE_PLACED_KEY,
        configuredFeatures.getOrThrow(ModConfiguredFeatures.OVERWORLD_LEAD_ORE_KEY),
        ModOrePlacement.commonOrePlacement(
            8,
            HeightRangePlacement.uniform(
                VerticalAnchor.absolute(-64), VerticalAnchor.absolute(80))));
  }

  private static ResourceKey<PlacedFeature> registerKey(String name) {
    return ResourceKey.create(
        Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(MilitaryMod.MODID, name));
  }

  private static void register(
      BootstrapContext<PlacedFeature> context,
      ResourceKey<PlacedFeature> key,
      Holder<ConfiguredFeature<?, ?>> configuration,
      List<PlacementModifier> modifiers) {
    context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
  }
}
