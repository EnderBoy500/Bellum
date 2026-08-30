package net.enderboy500.bellum;

import io.github.ciph3rj.cipherlib.Cipher;
import io.github.ciph3rj.cipherlib.events.CanEntityHealEvent;
import io.github.ciph3rj.cipherlib.helper.LootTableModificationHelper;
import io.github.ciph3rj.cipherlib.util.ItemUtils;
import io.github.ciph3rj.cipherlib.util.interfaces.VanillaChestLootTableList;
import net.enderboy500.bellum.content.*;
import net.enderboy500.bellum.networking.BellumPackets;
import net.enderboy500.bellum.util.BellumDataComponents;
import net.enderboy500.bellum.util.BellumTags;
import net.enderboy500.bellum.util.event.BellumSoundEvents;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.item.v1.DefaultItemComponentEvents;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.dispenser.DispenseItemBehavior;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.DispenserBlock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Bellum implements ModInitializer {
	public static final String MOD_ID = "bellum";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}

	public static final Identifier HELLFORK_TEXTURE = id("textures/entity/hellfork.png");
	public static final Identifier PITCHFORK_TEXTURE = id("textures/entity/pitchfork.png");

	public static final Identifier DEFAULT_ANCHOR_CHAIN = id("textures/entity/anchor_chain/default_chain.png");
	public static final Identifier SOUL_ANCHOR_CHAIN = id("textures/entity/anchor_chain/soul_chain.png");

	//TODO: Fix skins for battleaxes

	///Change Log 1.3.0:
	/// Changed how sickles work
	/// Lowered the damage and attack speed of sickles
	/// new look to the battle axe
	/// Added hellfork
	/// Added Pitchfork
	/// Drowned now are affected by Impaling
	/// Add the ability to use fire charge
	/// Add reaping to scythes and anchor
	/// added reeling and shockwave enchantment
	///	Moved the entity enchantment drops to a tag based system
	///	Decreased shield reflection ro 45% instead of 50%
	/// New Tags & Particles
	/// fixed sickle skins
	/// removed the mapping or 'bellum:kunai' -> 'bellum:iron_kunai'
	/// renamed tag 'bellum:reflect_enchantable' to 'bellum:shield_enchantable'
	/// added the second chance enchantment
	/// Kunai can be dispensed
	/// Fixed netherite item not being fire resistent
	/// Changed damage type of daggers
	/// Added New elytra enchantment (aside for now)

	@Override
	public void onInitialize() {
		LOGGER.info("Initializing Bellum");

		Cipher.addModId(MOD_ID);

		BellumItems.loadItems();
		BellumSkins.loadAndRegister();
		BellumCreativeModeTabs.loadCreativeModeTabs();

		BellumEnchantments.loadEnchantments();
		BellumEnchantmentEffects.loadEnchantmentEffects();

		BellumEntities.loadEntities();
		BellumMobEffects.loadMobEffects();
		BellumDamageTypes.loadDamageTypes();

		BellumParticleTypes.loadParticleTypes();

		BellumRecipes.loadRecipes();

		BellumPackets.load();

		BellumSoundEvents.loadSoundEvents();
		BellumTags.loadTags();
		BellumDataComponents.loadDataComponents();

		DispenserBlock.registerProjectileBehavior(BellumItems.WOODEN_KUNAI);
		DispenserBlock.registerProjectileBehavior(BellumItems.STONE_KUNAI);
		DispenserBlock.registerProjectileBehavior(BellumItems.COPPER_KUNAI);
		DispenserBlock.registerProjectileBehavior(BellumItems.IRON_KUNAI);
		DispenserBlock.registerProjectileBehavior(BellumItems.DIAMOND_KUNAI);
		DispenserBlock.registerProjectileBehavior(BellumItems.GOLDEN_KUNAI);
		DispenserBlock.registerProjectileBehavior(BellumItems.NETHERITE_KUNAI);

		///////////


		LootTableModificationHelper.addLootTableModification(VanillaChestLootTableList.BASTION_TREASURE, BellumItems.HELLFORK_UPGRADE_SMITHING_TEMPLATE, 1, 0, 1);
		LootTableModificationHelper.addLootTableModification(VanillaChestLootTableList.UNDERWATER_RUIN_BIG, BellumItems.ANCHOR, 1, 0, 1);

		///////////

		DefaultItemComponentEvents.MODIFY.register(modifyContext -> {
			for (Item item : ItemUtils.getAll(BuiltInRegistries.ITEM)) {
				if ((item instanceof BucketItem || item instanceof PotionItem || item.getDefaultInstance().is(Items.POWDER_SNOW_BUCKET) || item.getDefaultInstance().is(Items.MILK_BUCKET) || item.getDefaultInstance().is(Items.ENCHANTED_BOOK))) {
					modifyContext.modify(item, builder -> builder.set(DataComponents.MAX_STACK_SIZE, 16));
				}
				if ((item instanceof EnderpearlItem || item instanceof SnowballItem || item instanceof EggItem)) {
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