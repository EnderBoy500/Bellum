package net.enderboy500.bellum.data.providers;

import net.enderboy500.bellum.content.BellumItems;
import net.enderboy500.bellum.util.BellumTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Items;

import java.util.concurrent.CompletableFuture;

public class BellumEntityTagProvider extends FabricTagProvider.EntityTypeTagProvider {
    public BellumEntityTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        valueLookupBuilder(EntityTypeTags.SENSITIVE_TO_IMPALING)
                .add(EntityType.DROWNED)
        ;
        valueLookupBuilder(BellumTags.DROPS_SHOCKWAVE)
                .add(EntityType.DROWNED)
        ;
        valueLookupBuilder(BellumTags.DROPS_ATTUNING)
                .add(EntityType.ELDER_GUARDIAN)
        ;
    }
}
