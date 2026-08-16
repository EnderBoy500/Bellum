package net.enderboy500.bellum.content;

import net.enderboy500.bellum.Bellum;
import net.enderboy500.bellum.util.BellumTags;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;

public class BellumEnchantments {
    public static final ResourceKey<Enchantment> REAPING = register("reaping");
    public static final ResourceKey<Enchantment> ATTUNING = register("attuning");

    public static void bootstrap(BootstrapContext<Enchantment> context) {
        HolderGetter<Item> itemHolder = context.lookup(Registries.ITEM);
        var enchantment = context.lookup(Registries.ENCHANTMENT);

/*        register(context, REAPING, Enchantment.enchantment(Enchantment.definition(itemHolder.getOrThrow(BellumTags.REAPING_ENCHANTABLE), 10, 1, Enchantment.dynamicCost(1, 11),
                Enchantment.dynamicCost(21, 11), 1, EquipmentSlotGroup.MAINHAND)).withEffect(EnchantmentEffectComponents.POST_ATTACK, EnchantmentTarget.ATTACKER,
                EnchantmentTarget.VICTIM, new ReapEntity()));*/
/*        register(context, ATTUNING, Enchantment.enchantment(Enchantment.definition(itemHolder.getOrThrow(ItemTags.TRIDENT_ENCHANTABLE), 10, 1, Enchantment.dynamicCost(1, 11),
                Enchantment.dynamicCost(21, 11), 1, EquipmentSlotGroup.MAINHAND)).exclusiveWith(enchantment.getOrThrow(BellumTags.INCOMPATIBLE_WITH_ATTUNING)));*/
    }

    private static void register(BootstrapContext<Enchantment> bootstrapContext, ResourceKey<Enchantment> resourceKey, Enchantment.Builder builder) {
        bootstrapContext.register(resourceKey, builder.build(resourceKey.registry()));
    }

    private static ResourceKey<Enchantment> register(String id) {
        return ResourceKey.create(Registries.ENCHANTMENT, Bellum.id(id));
    }

    public static void loadEnchantments() {}
}
