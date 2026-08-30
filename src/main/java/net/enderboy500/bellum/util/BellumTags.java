package net.enderboy500.bellum.util;

import io.github.ciph3rj.cipherlib.helper.RegistryHelper;
import net.enderboy500.bellum.Bellum;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;

public class BellumTags {
    public static final TagKey<Item> SICKLES = RegistryHelper.registerItemTags("sickles");
    public static final TagKey<Item> SCYTHES = RegistryHelper.registerItemTags("scythes");
    public static final TagKey<Item> DAGGERS = RegistryHelper.registerItemTags("daggers");
    public static final TagKey<Item> KUNAIS = RegistryHelper.registerItemTags("kunais");
    public static final TagKey<Item> BATTLE_AXE = RegistryHelper.registerItemTags("battle_axes");
    public static final TagKey<Item> NAGINATA = RegistryHelper.registerItemTags("naginata");

    public static final TagKey<Item> SHIELD_ENCHANTABLE = RegistryHelper.registerItemTags("shield_enchantable");
    public static final TagKey<Item> REAPING_ENCHANTABLE = RegistryHelper.registerItemTags("reaping_enchantable");
    public static final TagKey<Item> REELING_ENCHANTABLE = RegistryHelper.registerItemTags("reeling_enchantable");
    public static final TagKey<Item> SHOCKWAVE_ENCHANTABLE = RegistryHelper.registerItemTags("shockwave_enchantable");
    public static final TagKey<Item> ROCKET_BOOST_ENCHANTABLE = RegistryHelper.registerItemTags("rocket_boost_enchantable");

    public static final TagKey<Item> ATTUNING_DROPPING_WEAPON = RegistryHelper.registerItemTags("attuning_dropping_weapon");
    public static final TagKey<Item> SHOCKWAVE_DROPPING_WEAPON = RegistryHelper.registerItemTags("shockwave_dropping_weapon");

    public static final TagKey<EntityType<?>> DROPS_ATTUNING = RegistryHelper.registerEntityTags("drops_attuning");
    public static final TagKey<EntityType<?>> DROPS_SHOCKWAVE = RegistryHelper.registerEntityTags("drop_shockwave");


    public static final TagKey<Enchantment> INCOMPATIBLE_WITH_ATTUNING = TagKey.create(Registries.ENCHANTMENT, Bellum.id("incompatible_with_attuning"));
    public static final TagKey<Enchantment> INCOMPATIBLE_WITH_REELING = TagKey.create(Registries.ENCHANTMENT, Bellum.id("incompatible_with_reeling"));
    public static final TagKey<Enchantment> INCOMPATIBLE_WITH_REAPING = TagKey.create(Registries.ENCHANTMENT, Bellum.id("incompatible_with_reaping"));
    public static final TagKey<Enchantment> INCOMPATIBLE_WITH_SHOCKWAVE = TagKey.create(Registries.ENCHANTMENT, Bellum.id("incompatible_with_shockwave"));
    public static final TagKey<Enchantment> DROPPED_BY_ELDER_GUARDIAN = TagKey.create(Registries.ENCHANTMENT, Bellum.id("dropped_by_elder_guardian"));
    public static final TagKey<Enchantment> DROPPED_BY_DROWNED = TagKey.create(Registries.ENCHANTMENT, Bellum.id("dropped_by_drowned"));

    public static void loadTags() {}
}
