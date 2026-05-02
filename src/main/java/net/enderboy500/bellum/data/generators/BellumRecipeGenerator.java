package net.enderboy500.bellum.data.generators;

import net.enderboy500.bellum.content.BellumItems;
import net.enderboy500.enderlib.helper.RecipeGeneratorHelper;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;

import java.util.concurrent.CompletableFuture;

public class BellumRecipeGenerator extends RecipeGeneratorHelper {
    public BellumRecipeGenerator(HolderLookup.Provider registries, RecipeOutput exporter) {
        super(registries, exporter);
    }

    @Override
    public void buildRecipes() {
        super.buildRecipes();

        this.shaped(RecipeCategory.COMBAT, BellumItems.WOODEN_SICKLE).define('#', ItemTags.PLANKS).define('/', Items.STICK).pattern(" # ").pattern("  #").pattern("/# ")
                .unlockedBy(getHasName(BellumItems.WOODEN_SICKLE), has(ItemTags.PLANKS)).save(output);
        this.shaped(RecipeCategory.COMBAT, BellumItems.STONE_SICKLE).define('#', ItemTags.STONE_CRAFTING_MATERIALS).define('/', Items.STICK).pattern(" # ").pattern("  #").pattern("/# ")
                .unlockedBy(getHasName(BellumItems.STONE_SICKLE), has(ItemTags.STONE_CRAFTING_MATERIALS)).save(output);
        generateSickle(Items.COPPER_INGOT, BellumItems.COPPER_SICKLE);
        generateSickle(Items.IRON_INGOT, BellumItems.IRON_SICKLE);
        generateSickle(Items.GOLD_INGOT, BellumItems.GOLDEN_SICKLE);
        generateSickle(Items.DIAMOND, BellumItems.DIAMOND_SICKLE);

        this.shaped(RecipeCategory.COMBAT, BellumItems.WOODEN_SCYTHE).define('#', ItemTags.PLANKS).define('|', Items.WOODEN_HOE).define('/', Items.STICK).pattern("## ").pattern(" |#").pattern("/  ")
                .unlockedBy(getHasName(BellumItems.WOODEN_SCYTHE), has(ItemTags.PLANKS)).save(output);
        this.shaped(RecipeCategory.COMBAT, BellumItems.STONE_SCYTHE).define('#', ItemTags.STONE_CRAFTING_MATERIALS).define('|', Items.STONE_HOE).define('/', Items.STICK).pattern("## ").pattern(" |#").pattern("/  ")
                .unlockedBy(getHasName(BellumItems.STONE_SCYTHE), has(ItemTags.STONE_CRAFTING_MATERIALS)).save(output);
        generateScythe(Items.COPPER_INGOT, Items.COPPER_HOE, BellumItems.COPPER_SCYTHE);
        generateScythe(Items.IRON_INGOT, Items.IRON_HOE, BellumItems.IRON_SCYTHE);
        generateScythe(Items.GOLD_INGOT, Items.GOLDEN_HOE, BellumItems.GOLDEN_SCYTHE);
        generateScythe(Items.DIAMOND, Items.DIAMOND_HOE, BellumItems.DIAMOND_SCYTHE);

        this.shaped(RecipeCategory.COMBAT, BellumItems.KUNAI, 2).define('#', Items.IRON_INGOT).define('N', Items.IRON_NUGGET)
                .define('/', Items.STICK).pattern("#").pattern("/").pattern("N")
                .unlockedBy(getHasName(BellumItems.KUNAI), has(Items.IRON_INGOT)).save(output);
    }

    public void generateSickle(ItemLike base, Item outputItem) {
        this.shaped(RecipeCategory.COMBAT, outputItem).define('#', base).define('/', Items.STICK).pattern(" # ").pattern("  #").pattern("/# ")
                .unlockedBy(getHasName(outputItem), has(base)).save(output);
    }

    public void generateScythe(ItemLike base, ItemLike hoe, Item outputItem) {
        this.shaped(RecipeCategory.COMBAT, outputItem).define('#', base).define('|', hoe).define('/', Items.STICK).pattern("## ").pattern(" |#").pattern("/  ")
                .unlockedBy(getHasName(outputItem), has(base)).save(output);
    }

    public static class Provider extends RecipeProvider.Runner {
        public Provider(PackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
            super(output, registriesFuture);
        }

        @Override
        protected RecipeProvider createRecipeProvider(HolderLookup.Provider registries, RecipeOutput exporter) {
            return new BellumRecipeGenerator(registries, exporter);
        }

        @Override
        public String getName() {
            return "Recipe Provider";
        }
    }
}
