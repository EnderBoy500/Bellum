package net.enderboy500.bellum.util;

import io.github.ciph3rj.cipherlib.helper.RegistryHelper;
import net.enderboy500.bellum.Bellum;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;

public class BellumTags {
    public static final TagKey<Item> SICKLES = RegistryHelper.registerItemTags("sickles");
    public static final TagKey<Item> SCYTHES = RegistryHelper.registerItemTags("scythes");
    public static final TagKey<Item> DAGGERS = RegistryHelper.registerItemTags("daggers");
    public static final TagKey<Item> KUNAIS = RegistryHelper.registerItemTags("kunais");
    public static final TagKey<Item> BATTLE_AXE = RegistryHelper.registerItemTags("battle_axes");
    public static final TagKey<Item> REFLECT_ENCHANTABLE = RegistryHelper.registerItemTags("reflect_enchantable");

    public static final TagKey<Item> REAPING_ENCHANTABLE = RegistryHelper.registerItemTags("reaping_enchantable");

    public static final TagKey<Enchantment> INCOMPATIBLE_WITH_ATTUNING = TagKey.create(Registries.ENCHANTMENT, Bellum.id("incompatible_with_attuning"));
    public static final TagKey<Enchantment> DROPPED_BY_ELDER_GUARDIAN = TagKey.create(Registries.ENCHANTMENT, Bellum.id("dropped_by_elder_guardian"));

    public static void loadTags() {}
}
