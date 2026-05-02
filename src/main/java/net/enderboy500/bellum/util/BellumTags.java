package net.enderboy500.bellum.util;

import net.enderboy500.enderlib.helper.RegistryHelper;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class BellumTags {
    public static final TagKey<Item> SICKLES = RegistryHelper.registerItemTags("sickles");
    public static final TagKey<Item> SCYTHES = RegistryHelper.registerItemTags("scythes");
    public static final TagKey<Item> DAGGERS = RegistryHelper.registerItemTags("daggers");

    public static final TagKey<Item> STACKABLE_TO_16 = RegistryHelper.registerItemTags("stackable_to_sixteen");
    public static final TagKey<Item> STACKABLE_TO_64 = RegistryHelper.registerItemTags("stackable_to_sixty_four");

    public static final TagKey<Item> REAPING_ENCHANTABLE = RegistryHelper.registerItemTags("reaping_enchantable");

    public static void loadTags() {}
}
