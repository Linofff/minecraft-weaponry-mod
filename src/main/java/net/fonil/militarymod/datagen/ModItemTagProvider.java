package net.fonil.militarymod.datagen;

import net.fonil.militarymod.MilitaryMod;
import net.fonil.militarymod.item.ModItems;
import net.fonil.militarymod.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends ItemTagsProvider {
    public ModItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,
                              CompletableFuture<TagLookup<Block>> blockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags, MilitaryMod.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(ModTags.Items.TRANSFORMABLE_ITEMS)
                .add(ModItems.LEAD_INGOT.get())
                .add(ModItems.LEAD_NUGGET.get())
                .add(ModItems.RAW_LEAD.get())
                .add(Items.COAL)
                .add(Items.STICK)
                .add(Items.COMPASS);

    }
}
