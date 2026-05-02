package net.enderboy500.bellum;

import net.enderboy500.bellum.content.*;
import net.enderboy500.bellum.entity.TrainingDummyEntity;
import net.enderboy500.bellum.util.BellumDataComponents;
import net.enderboy500.bellum.util.BellumTags;
import net.enderboy500.bellum.util.event.BellumSoundEvents;
import net.enderboy500.enderlib.ELib;
import net.enderboy500.enderlib.events.CanEntityHealEvent;
import net.enderboy500.enderlib.registry.ToolFuntionRegistry;
import net.enderboy500.enderlib.util.ItemUtils;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.item.v1.DefaultItemComponentEvents;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Bellum implements ModInitializer {
	public static final String MOD_ID = "bellum";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}

	@Override
	public void onInitialize() {
		LOGGER.info("Initializing Bellum");

		ELib.addModId(MOD_ID);

		BellumItems.loadItems();
		BellumSkins.loadAndRegister();
		BellumCreativeModeTabs.loadCreativeModeTabs();

		BellumEnchantments.loadEnchantments();
		BellumEnchantmentEffects.loadEnchantmentEffects();

		BellumEntities.loadEntities();
		BellumMobEffects.loadMobEffects();

		BellumParticleTypes.loadParticleTypes();

		BellumRecipes.loadRecipes();

		BellumSoundEvents.loadSoundEvents();
		BellumTags.loadTags();
		BellumDataComponents.loadDataComponents();

		///////////

		//FabricDefaultAttributeRegistry.register(BellumEntities.TRAINING_DUMMY, TrainingDummyEntity.createAttributes());

		///////////

		DefaultItemComponentEvents.MODIFY.register(modifyContext -> {
			for (Item item : ItemUtils.getAll(BuiltInRegistries.ITEM)) {
				if (item instanceof BucketItem || item instanceof PotionItem || item.getDefaultInstance().is(Items.POWDER_SNOW_BUCKET) || item.getDefaultInstance().is(Items.MILK_BUCKET) || item.getDefaultInstance().is(Items.ENCHANTED_BOOK)) {
					modifyContext.modify(item, builder -> builder.set(DataComponents.MAX_STACK_SIZE, 16));
				}
				if (item instanceof EnderpearlItem || item instanceof SnowballItem || item instanceof EggItem) {
					modifyContext.modify(item, builder -> builder.set(DataComponents.MAX_STACK_SIZE, 64));
				}
			}
		});

		CanEntityHealEvent.EVENT.register(livingEntity -> {
            return !livingEntity.hasEffect(BellumMobEffects.BLEEDING);
        });

		LOGGER.info("Finished Initializing Bellum");
	}
}