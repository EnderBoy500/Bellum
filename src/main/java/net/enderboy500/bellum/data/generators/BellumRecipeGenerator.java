package net.enderboy500.bellum.data.generators;

import io.github.ciph3rj.cipherlib.helper.CipherLibRecipeGenerator;
import net.enderboy500.bellum.content.BellumItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.SmithingTransformRecipeBuilder;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

import java.util.concurrent.CompletableFuture;

public class BellumRecipeGenerator extends CipherLibRecipeGenerator {
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

        this.shaped(RecipeCategory.COMBAT, BellumItems.WOODEN_DAGGER).define('#', ItemTags.PLANKS).define('/', Items.STICK).pattern("#").pattern("/")
                .unlockedBy(getHasName(BellumItems.WOODEN_DAGGER), has(ItemTags.PLANKS)).save(output);
        this.shaped(RecipeCategory.COMBAT, BellumItems.STONE_DAGGER).define('#', ItemTags.STONE_CRAFTING_MATERIALS).define('/', Items.STICK).pattern("#").pattern("/")
                .unlockedBy(getHasName(BellumItems.STONE_DAGGER), has(ItemTags.STONE_CRAFTING_MATERIALS)).save(output);
        generateDagger(Items.COPPER_INGOT, BellumItems.COPPER_DAGGER);
        generateDagger(Items.IRON_INGOT, BellumItems.IRON_DAGGER);
        generateDagger(Items.GOLD_INGOT, BellumItems.GOLDEN_DAGGER);
        generateDagger(Items.DIAMOND,  BellumItems.DIAMOND_DAGGER);

        this.shaped(RecipeCategory.COMBAT, BellumItems.WOODEN_KUNAI, 2).define('#', ItemTags.PLANKS).define('/', Items.STICK)
                .pattern("#").pattern("/").pattern("#")
                .unlockedBy(getHasName(BellumItems.WOODEN_KUNAI), has(ItemTags.PLANKS)).save(output);
        this.shaped(RecipeCategory.COMBAT, BellumItems.STONE_KUNAI, 2).define('#', ItemTags.STONE_CRAFTING_MATERIALS)
                .define('/', Items.STICK).pattern("#").pattern("/").pattern("#")
                .unlockedBy(getHasName(BellumItems.STONE_KUNAI), has(ItemTags.STONE_CRAFTING_MATERIALS)).save(output);
        generateKunai(Items.COPPER_INGOT, BellumItems.COPPER_KUNAI);
        generateKunai(Items.IRON_INGOT, BellumItems.IRON_KUNAI);
        generateKunai(Items.GOLD_INGOT, BellumItems.GOLDEN_KUNAI);
        generateKunai(Items.DIAMOND, BellumItems.DIAMOND_KUNAI);

        generateBattleAxe(BellumItems.COPPER_BATTLE_AXE, Items.COPPER_INGOT);
        generateBattleAxe(BellumItems.IRON_BATTLE_AXE, Items.IRON_INGOT);
        generateBattleAxe(BellumItems.GOLDEN_BATTLE_AXE, Items.GOLD_INGOT);
        generateBattleAxe(BellumItems.DIAMOND_BATTLE_AXE, Items.DIAMOND);

        generateNaginata(Items.WOODEN_SWORD, BellumItems.WOODEN_NAGINATA);
        generateNaginata(Items.STONE_SWORD, BellumItems.STONE_NAGINATA);
        generateNaginata(Items.COPPER_SWORD, BellumItems.COPPER_NAGINATA);
        generateNaginata(Items.IRON_SWORD, BellumItems.IRON_NAGINATA);
        generateNaginata(Items.GOLDEN_SWORD, BellumItems.GOLDEN_NAGINATA);
        generateNaginata(Items.DIAMOND_SWORD, BellumItems.DIAMOND_NAGINATA);

        netheriteSmithing(BellumItems.DIAMOND_NAGINATA, RecipeCategory.COMBAT, BellumItems.NETHERITE_NAGINATA);

        this.shaped(RecipeCategory.COMBAT, BellumItems.NETHERITE_KUNAI, 8).define('#', BellumItems.DIAMOND_KUNAI)
                .define('N', Items.NETHERITE_INGOT).pattern("###").pattern("#N#").pattern("###")
                .unlockedBy(getHasName(BellumItems.NETHERITE_KUNAI), has(Items.NETHERITE_INGOT)).save(output);

        this.shaped(RecipeCategory.COMBAT, BellumItems.WOODEN_BATTLE_AXE).define('#', ItemTags.PLANKS).define('/', Items.STICK).pattern("###").pattern("#/#").pattern(" / ")
                .unlockedBy(getHasName(BellumItems.WOODEN_BATTLE_AXE), has(ItemTags.PLANKS)).save(output);
        this.shaped(RecipeCategory.COMBAT, BellumItems.STONE_BATTLE_AXE).define('#', ItemTags.STONE_CRAFTING_MATERIALS).define('/', Items.STICK).pattern("###").pattern("#/#").pattern(" / ")
                .unlockedBy(getHasName(BellumItems.STONE_BATTLE_AXE), has(ItemTags.STONE_CRAFTING_MATERIALS)).save(output);
        copySmithingTemplate(BellumItems.HELLFORK_UPGRADE_SMITHING_TEMPLATE, Items.NETHERITE_INGOT);

        SmithingTransformRecipeBuilder.smithing(Ingredient.of(BellumItems.HELLFORK_UPGRADE_SMITHING_TEMPLATE), Ingredient.of(Items.TRIDENT), tag(ItemTags.NETHERITE_TOOL_MATERIALS), RecipeCategory.COMBAT, BellumItems.HELLFORK).unlocks("has_netherite_ingot", this.has(ItemTags.NETHERITE_TOOL_MATERIALS)).save(this.output, getItemName(BellumItems.HELLFORK) + "_smithing");

        this.shaped(RecipeCategory.COMBAT, BellumItems.PITCHFORK).define('/', Items.STICK).define('#', Items.IRON_INGOT)
                .pattern(" ##").pattern(" /#").pattern("/  ").unlockedBy(getHasName(BellumItems.PITCHFORK), has(Items.IRON_INGOT)).save(output);
    }

    public void generateDagger(ItemLike base, Item outputItem) {
        this.shaped(RecipeCategory.COMBAT, outputItem).define('#', base).define('/', Items.STICK).pattern("#").pattern("/")
                .unlockedBy(getHasName(outputItem), has(base)).save(output);
    }

    public void generateSickle(ItemLike base, Item outputItem) {
        this.shaped(RecipeCategory.COMBAT, outputItem).define('#', base).define('/', Items.STICK).pattern(" # ").pattern("  #").pattern("/# ")
                .unlockedBy(getHasName(outputItem), has(base)).save(output);
    }

    public void generateScythe(ItemLike base, ItemLike hoe, Item outputItem) {
        this.shaped(RecipeCategory.COMBAT, outputItem).define('#', base).define('|', hoe).define('/', Items.STICK).pattern("## ").pattern(" |#").pattern("/  ")
                .unlockedBy(getHasName(outputItem), has(base)).save(output);
    }

    public void generateBattleAxe(Item outputItem,ItemLike base) {
        this.shaped(RecipeCategory.COMBAT, outputItem).define('#', base).define('/', Items.STICK).pattern("###").pattern("#/#").pattern(" / ")
                .unlockedBy(getHasName(outputItem), has(base)).save(output);
    }

    public void generateKunai(ItemLike base, Item outputItem) {
        this.shaped(RecipeCategory.COMBAT, outputItem, 2).define('#', base).define('/', Items.STICK).pattern("#")
                .pattern("/").pattern("#").unlockedBy(getHasName(outputItem), has(base)).save(output);
    }

    public void generateNaginata(ItemLike base, Item outputItem) {
        this.shaped(RecipeCategory.COMBAT, outputItem).define('#', base).define('/', Items.STICK).pattern("  #")
                .pattern(" / ").pattern("/  ").unlockedBy(getHasName(outputItem), has(base)).save(output);
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
