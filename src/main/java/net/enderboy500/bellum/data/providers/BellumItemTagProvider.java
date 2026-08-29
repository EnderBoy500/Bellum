package net.enderboy500.bellum.data.providers;

import net.enderboy500.bellum.content.BellumItems;
import net.enderboy500.bellum.util.BellumTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;

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
        valueLookupBuilder(BellumTags.KUNAIS)
                .add(BellumItems.WOODEN_KUNAI)
                .add(BellumItems.STONE_KUNAI)
                .add(BellumItems.COPPER_KUNAI)
                .add(BellumItems.IRON_KUNAI)
                .add(BellumItems.GOLDEN_KUNAI)
                .add(BellumItems.DIAMOND_KUNAI)
                .add(BellumItems.NETHERITE_KUNAI)
        ;

        valueLookupBuilder(BellumTags.BATTLE_AXE)
                .add(BellumItems.WOODEN_BATTLE_AXE)
                .add(BellumItems.STONE_BATTLE_AXE)
                .add(BellumItems.COPPER_BATTLE_AXE)
                .add(BellumItems.IRON_BATTLE_AXE)
                .add(BellumItems.GOLDEN_BATTLE_AXE)
                .add(BellumItems.DIAMOND_BATTLE_AXE)
                .add(BellumItems.NETHERITE_BATTLE_AXE)
        ;

        valueLookupBuilder(ItemTags.TRIDENT_ENCHANTABLE)
                .add(BellumItems.HELLFORK)
                .add(BellumItems.PITCHFORK)
        ;

        valueLookupBuilder(ItemTags.SWEEPING_ENCHANTABLE)
                .addTag(BellumTags.SICKLES)
                .addTag(BellumTags.DAGGERS)
                .addTag(BellumTags.SCYTHES)
                .addTag(BellumTags.BATTLE_AXE)
                .add(BellumItems.ANCHOR)
        ;
        valueLookupBuilder(ItemTags.SHARP_WEAPON_ENCHANTABLE)
                .addTag(BellumTags.SICKLES)
                .addTag(BellumTags.DAGGERS)
                .addTag(BellumTags.SCYTHES)
                .addTag(BellumTags.BATTLE_AXE)
        ;
        valueLookupBuilder(ItemTags.DURABILITY_ENCHANTABLE)
                .addTag(BellumTags.SICKLES)
                .addTag(BellumTags.DAGGERS)
                .addTag(BellumTags.SCYTHES)
                .addTag(BellumTags.BATTLE_AXE)
                .add(BellumItems.ANCHOR)
        ;
        valueLookupBuilder(ItemTags.MINING_ENCHANTABLE)
                .addTag(BellumTags.BATTLE_AXE)
                .addTag(BellumTags.SCYTHES)
                .add(BellumItems.ANCHOR)
        ;
        valueLookupBuilder(ItemTags.MINING_LOOT_ENCHANTABLE)
                .addTag(BellumTags.BATTLE_AXE)
                .addTag(BellumTags.SCYTHES)
                .add(BellumItems.ANCHOR)
        ;
        valueLookupBuilder(ItemTags.FIRE_ASPECT_ENCHANTABLE)
                .addTag(BellumTags.SICKLES)
                .addTag(BellumTags.DAGGERS)
                .addTag(BellumTags.SCYTHES)
                .addTag(BellumTags.BATTLE_AXE)
                .add(BellumItems.ANCHOR)
        ;
        valueLookupBuilder(ItemTags.MELEE_WEAPON_ENCHANTABLE)
                .addTag(BellumTags.SICKLES)
                .addTag(BellumTags.DAGGERS)
                .addTag(BellumTags.SCYTHES)
                .addTag(BellumTags.BATTLE_AXE)
        ;
        valueLookupBuilder(ItemTags.WEAPON_ENCHANTABLE)
                .addTag(BellumTags.SICKLES)
                .addTag(BellumTags.DAGGERS)
                .addTag(BellumTags.SCYTHES)
                .addTag(BellumTags.BATTLE_AXE)
        ;
        valueLookupBuilder(BellumTags.SHIELD_ENCHANTABLE)
                .add(Items.SHIELD)
        ;
        valueLookupBuilder(BellumTags.ATTUNING_DROPPING_WEAPON)
                .add(Items.TRIDENT)
        ;
        valueLookupBuilder(BellumTags.REELING_ENCHANTABLE)
                .add(BellumItems.ANCHOR)
        ;
        valueLookupBuilder(BellumTags.SHOCKWAVE_ENCHANTABLE)
                .add(BellumItems.ANCHOR)
        ;
        valueLookupBuilder(BellumTags.SHOCKWAVE_DROPPING_WEAPON)
                .add(BellumItems.ANCHOR)
        ;
        valueLookupBuilder(BellumTags.ROCKET_BOOST_ENCHANTABLE)
                .add(Items.ELYTRA)
        ;
        valueLookupBuilder(ItemTags.VANISHING_ENCHANTABLE)
                .addTag(BellumTags.SICKLES)
                .addTag(BellumTags.DAGGERS)
                .addTag(BellumTags.SCYTHES)
                .addTag(BellumTags.BATTLE_AXE)
                .add(BellumItems.ANCHOR)
        ;
        valueLookupBuilder(BellumTags.REAPING_ENCHANTABLE)
                .addTag(BellumTags.SICKLES)
                .addTag(BellumTags.SCYTHES)
                .add(BellumItems.ANCHOR)
        ;
    }
}
