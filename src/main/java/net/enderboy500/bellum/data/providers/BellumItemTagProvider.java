package net.enderboy500.bellum.data.providers;

import net.enderboy500.bellum.content.BellumItems;
import net.enderboy500.bellum.util.BellumTags;
import net.enderboy500.enderlib.util.EnderlibTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.ItemTags;

import java.util.concurrent.CompletableFuture;

public class BellumItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public BellumItemTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        valueLookupBuilder(BellumTags.SICKLES)
                .add(BellumItems.WOODEN_SICKLE)
                .add(BellumItems.STONE_SICKLE)
                .add(BellumItems.COPPER_SICKLE)
                .add(BellumItems.IRON_SICKLE)
                .add(BellumItems.GOLDEN_SICKLE)
                .add(BellumItems.DIAMOND_SICKLE)
                .add(BellumItems.NETHERITE_SICKLE)
        ;
        valueLookupBuilder(BellumTags.SCYTHES)
                .add(BellumItems.WOODEN_SCYTHE)
                .add(BellumItems.STONE_SCYTHE)
                .add(BellumItems.COPPER_SCYTHE)
                .add(BellumItems.IRON_SCYTHE)
                .add(BellumItems.GOLDEN_SCYTHE)
                .add(BellumItems.DIAMOND_SCYTHE)
                .add(BellumItems.NETHERITE_SCYTHE)
        ;
        valueLookupBuilder(BellumTags.DAGGERS)
                .add(BellumItems.WOODEN_DAGGER)
                .add(BellumItems.STONE_DAGGER)
                .add(BellumItems.COPPER_DAGGER)
                .add(BellumItems.IRON_DAGGER)
                .add(BellumItems.GOLDEN_DAGGER)
                .add(BellumItems.DIAMOND_DAGGER)
                .add(BellumItems.NETHERITE_DAGGER)
        ;

        valueLookupBuilder(EnderlibTags.SKIN_INCOMPATIBILITY)
                .add(BellumItems.KUNAI)
        ;

        valueLookupBuilder(ItemTags.SWEEPING_ENCHANTABLE)
                .addTag(BellumTags.SICKLES)
                .addTag(BellumTags.DAGGERS)
        ;
        valueLookupBuilder(ItemTags.SHARP_WEAPON_ENCHANTABLE)
                .addTag(BellumTags.SICKLES)
                .addTag(BellumTags.DAGGERS)
        ;
        valueLookupBuilder(ItemTags.DURABILITY_ENCHANTABLE)
                .addTag(BellumTags.SICKLES)
                .addTag(BellumTags.DAGGERS)
        ;
        valueLookupBuilder(ItemTags.FIRE_ASPECT_ENCHANTABLE)
                .addTag(BellumTags.SICKLES)
                .addTag(BellumTags.DAGGERS)
        ;
        valueLookupBuilder(ItemTags.MELEE_WEAPON_ENCHANTABLE)
                .addTag(BellumTags.SICKLES)
                .addTag(BellumTags.DAGGERS)
        ;
        valueLookupBuilder(ItemTags.VANISHING_ENCHANTABLE)
                .addTag(BellumTags.SICKLES)
                .addTag(BellumTags.DAGGERS)
        ;
        valueLookupBuilder(BellumTags.REAPING_ENCHANTABLE)
                .addTag(BellumTags.SICKLES)
        ;
    }
}
