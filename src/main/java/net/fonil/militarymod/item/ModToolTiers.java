package net.fonil.militarymod.item;

import net.fonil.militarymod.util.ModTags;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.SimpleTier;

public class ModToolTiers {
    public static final Tier LEAD = new SimpleTier(ModTags.Blocks.INCORRECT_FOR_LEAD_TOOL,
            1400, 4f, 3f, 28, () -> Ingredient.of(ModItems.LEAD_INGOT));

}
