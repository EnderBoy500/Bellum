package net.enderboy500.bellum.data;

import net.enderboy500.bellum.content.BellumEnchantments;
import net.enderboy500.bellum.data.generators.BellumRecipeGenerator;
import net.enderboy500.bellum.data.providers.BellumDynamicRegistryProvider;
import net.enderboy500.bellum.data.providers.BellumItemTagProvider;
import net.enderboy500.bellum.data.providers.BellumModelProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.registries.VanillaRegistries;
import net.minecraft.util.Util;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiFunction;

public class BellumDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
		CompletableFuture<HolderLookup.Provider> completableFuture = CompletableFuture.supplyAsync(
				VanillaRegistries::createLookup, Util.backgroundExecutor()
		);

		pack.addProvider(BellumItemTagProvider::new);
		pack.addProvider(BellumModelProvider::new);
		pack.addProvider(BellumDynamicRegistryProvider::new);

		pack.addProvider(toFactory(BellumRecipeGenerator.Provider::new, completableFuture));
	}

	private static <T extends DataProvider> DataProvider.Factory<T> toFactory(BiFunction<PackOutput, CompletableFuture<HolderLookup.Provider>, T> baseFactory, CompletableFuture<HolderLookup.Provider> registriesFuture) {
		return output -> (T)baseFactory.apply(output, registriesFuture);
	}

	@Override
	public void buildRegistry(RegistrySetBuilder registryBuilder) {
		registryBuilder.add(Registries.ENCHANTMENT, BellumEnchantments::bootstrap);
	}
}
