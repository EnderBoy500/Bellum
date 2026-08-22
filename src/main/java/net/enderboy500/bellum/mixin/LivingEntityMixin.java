package net.enderboy500.bellum.mixin;

import io.github.ciph3rj.cipherlib.util.ItemUtils;
import net.enderboy500.bellum.content.BellumDamageTypes;
import net.enderboy500.bellum.util.BellumTags;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Optional;
import java.util.Random;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {
    @Shadow public abstract LivingEntity getLastAttacker();

    @Shadow
    public abstract @org.jspecify.annotations.Nullable ItemStack getItemBlockingWith();

    public LivingEntityMixin(EntityType<?> type, Level world) {
        super(type, world);
    }
    @Inject(method = "dropCustomDeathLoot", at = @At("HEAD"))
    private void drops(ServerLevel world, DamageSource source, boolean causedByPlayer, CallbackInfo ci) {
        Random random = new Random();
        int randomDropChance = random.nextInt(5) + 1;
        if (this.getLastAttacker() != null) {
            if (this.getLastAttacker().isHolding(Items.TRIDENT) && world instanceof ServerLevel serverLevel) {
                Optional<Holder<Enchantment>> optional = serverLevel.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getRandomElementOf(BellumTags.DROPPED_BY_ELDER_GUARDIAN, serverLevel.random);
                Holder<Enchantment> holder = optional.get();
                if (holder != null) {
                    ItemStack stack = EnchantmentHelper.createBook(new EnchantmentInstance(holder, 1));
                    if (this.getType() == EntityType.ELDER_GUARDIAN && randomDropChance <= 2)
                        this.spawnAtLocation(serverLevel, stack);
                }
            }
        }
    }

    @Inject(method = "blockUsingItem", at = @At("TAIL"))
    private void shield(ServerLevel serverLevel, LivingEntity livingEntity, CallbackInfo ci) {
        ItemStack stack = this.getItemBlockingWith();
        if (ItemUtils.hasEnchantment(stack, "reflect")) {
            livingEntity.hurt(serverLevel.damageSources().source(BellumDamageTypes.REFLECTED), (float) livingEntity.getAttribute(Attributes.ATTACK_DAMAGE).getValue() * 0.5f);
        }
    }
}
