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

    public static void bootstrap(BootstrapContext<Enchantment> context) {
        HolderGetter<Item> itemHolder = context.lookup(Registries.ITEM);

/*        register(context, REAPING, Enchantment.enchantment(Enchantment.definition(itemHolder.getOrThrow(BellumTags.REAPING_ENCHANTABLE), 10, 1, Enchantment.dynamicCost(1, 11),
                Enchantment.dynamicCost(21, 11), 1, EquipmentSlotGroup.MAINHAND)).withEffect(EnchantmentEffectComponents.POST_ATTACK, EnchantmentTarget.ATTACKER,
                EnchantmentTarget.VICTIM, new ReapEntity()));*/
    }

    private static void register(BootstrapContext<Enchantment> bootstrapContext, ResourceKey<Enchantment> resourceKey, Enchantment.Builder builder) {
        bootstrapContext.register(resourceKey, builder.build(resourceKey.registry()));
    }

    private static ResourceKey<Enchantment> register(String id) {
        return ResourceKey.create(Registries.ENCHANTMENT, Bellum.id(id));
    }

    public static void loadEnchantments() {}
}
