package net.enderboy500.bellum.mixin;

import io.github.ciph3rj.cipherlib.util.ItemUtils;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.BlocksAttacks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(BlocksAttacks.class)
public class BlockAttacksMixin {

    @Inject(method = "disable", at = @At("HEAD"), cancellable = true)
    public void enchantment(ServerLevel serverLevel, LivingEntity livingEntity, float f, ItemStack itemStack, CallbackInfo ci) {
        if (ItemUtils.hasEnchantment(itemStack, "second_chance") && livingEntity.getRandom().nextBoolean()) ci.cancel();
    }
}
