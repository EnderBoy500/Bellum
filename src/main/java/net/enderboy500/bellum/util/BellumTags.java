package net.enderboy500.bellum.util;

import net.enderboy500.enderlib.helper.RegistryHelper;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class BellumTags {
    public static final TagKey<Item> SICKLES = RegistryHelper.registerItemTags("sickles");
    public static final TagKey<Item> SCYTHES = RegistryHelper.registerItemTags("scythes");
    public static final TagKey<Item> DAGGERS = RegistryHelper.registerItemTags("daggers");

    public static final TagKey<Item> REAPING_ENCHANTABLE = RegistryHelper.registerItemTags("reaping_enchantable");

    public static void loadTags() {}
}
