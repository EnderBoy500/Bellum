package net.enderboy500.bellum.content;

import com.mojang.serialization.MapCodec;
import net.enderboy500.bellum.Bellum;
import net.enderboy500.bellum.enchantment.ReapEntity;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;

public class BellumEnchantmentEffects {
    public static final MapCodec<? extends EnchantmentEntityEffect> REAPING = register("reaping", ReapEntity.CODEC);

    private static MapCodec<? extends EnchantmentEntityEffect> register(String id, MapCodec<? extends EnchantmentEntityEffect> codec) {
        return Registry.register(BuiltInRegistries.ENCHANTMENT_ENTITY_EFFECT_TYPE, Bellum.id(id), codec);
    }

    public static void loadEnchantmentEffects() {}
}
