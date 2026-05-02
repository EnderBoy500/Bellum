package net.enderboy500.bellum.enchantment;

import com.mojang.serialization.MapCodec;
import net.enderboy500.bellum.content.BellumMobEffects;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

public record ReapEntity() implements EnchantmentEntityEffect {
    public static final MapCodec<ReapEntity> CODEC = MapCodec.unit(ReapEntity::new);

    @Override
    public void apply(ServerLevel serverLevel, int i, EnchantedItemInUse enchantedItemInUse, Entity entity, Vec3 vec3) {
        if (entity instanceof LivingEntity mob && mob.getLastAttacker() != null) {
            double reapingStrength = 0.75;
            Vec3 direction = mob.getLastAttacker().position().subtract(mob.position()).normalize();
            mob.addEffect(new MobEffectInstance(BellumMobEffects.STUN, 50));
            mob.push(direction.x * reapingStrength, direction.y * reapingStrength * 0.5, direction.z * reapingStrength);
            mob.hurtMarked = true;
        }
    }

    @Override
    public @NotNull MapCodec<? extends EnchantmentEntityEffect> codec() {
        return CODEC;
    }
}
