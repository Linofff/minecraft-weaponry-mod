package net.fonil.militarymod.worldgen;

import net.fonil.militarymod.MilitaryMod;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.minecraft.world.level.levelgen.GenerationStep;

public class ModBiomeModifiers {

  public static final ResourceKey<BiomeModifier> ADD_LEAD_ORE = registerKey("add_lead_ore");

  public static void bootstrap(BootstrapContext<BiomeModifier> context) {
    var placedFeatures = context.lookup(Registries.PLACED_FEATURE);
    var biomes = context.lookup(Registries.BIOME);

    context.register(
        ADD_LEAD_ORE,
        new BiomeModifiers.AddFeaturesBiomeModifier(
            biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
            HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.LEAD_ORE_PLACED_KEY)),
            GenerationStep.Decoration.UNDERGROUND_ORES));
  }

  private static ResourceKey<BiomeModifier> registerKey(String name) {
    return ResourceKey.create(
        NeoForgeRegistries.Keys.BIOME_MODIFIERS,
        ResourceLocation.fromNamespaceAndPath(MilitaryMod.MODID, name));
  }
}
