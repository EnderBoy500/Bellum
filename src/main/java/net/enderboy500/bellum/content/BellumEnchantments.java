package net.enderboy500.bellum.content;

import net.enderboy500.bellum.Bellum;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;

public class BellumEnchantments {
    public static final ResourceKey<Enchantment> REAPING = register("reaping");
    public static final ResourceKey<Enchantment> ATTUNING = register("attuning");

    public static void bootstrap(BootstrapContext<Enchantment> context) {
        HolderGetter<Item> itemHolder = context.lookup(Registries.ITEM);
        var enchantment = context.lookup(Registries.ENCHANTMENT);
    }

    private static void register(BootstrapContext<Enchantment> bootstrapContext, ResourceKey<Enchantment> resourceKey, Enchantment.Builder builder) {
        bootstrapContext.register(resourceKey, builder.build(resourceKey.registry()));
    }

    private static ResourceKey<Enchantment> register(String id) {
        return ResourceKey.create(Registries.ENCHANTMENT, Bellum.id(id));
    }

    public static void loadEnchantments() {}
}
