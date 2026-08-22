package net.enderboy500.bellum.mixin;

import io.github.ciph3rj.cipherlib.util.ItemUtils;
import net.enderboy500.bellum.content.BellumDamageTypes;
import net.minecraft.core.Holder;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TridentItem;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(TridentItem.class)
public abstract class TridentItemMixin {

    @Shadow
    public abstract int getUseDuration(ItemStack itemStack, LivingEntity livingEntity);


    @Inject(method = "releaseUsing", at = @At("HEAD"), cancellable = true)
    public void releaseUsing(ItemStack itemStack, Level level, LivingEntity livingEntity, int i, CallbackInfoReturnable<Boolean> cir) {
        if (livingEntity instanceof Player player) {
            int j = this.getUseDuration(itemStack, livingEntity) - i;
            boolean hasAttuning = ItemUtils.hasEnchantment(itemStack, "attuning");
            if (j >= 10) {
                Holder<SoundEvent> holder = EnchantmentHelper.pickHighestLevel(itemStack, EnchantmentEffectComponents.TRIDENT_SOUND).orElse(SoundEvents.TRIDENT_THROW);
                if (hasAttuning && (player.getHealth() > 0 || player.experienceLevel > 0)) {
                    if (player.experienceLevel > 0) player.experienceLevel = player.experienceLevel - 1;
                    else player.hurt(level.damageSources().source(BellumDamageTypes.DRAINED_SOUL), 1.5f);
                    int f = 2;
                    float g = player.getYRot();
                    float h = player.getXRot();
                    float k = -Mth.sin( (g * ((float) Math.PI / 180F))) * Mth.cos((h * ((float) Math.PI / 180F)));
                    float l = -Mth.sin( (h * ((float) Math.PI / 180F)));
                    float m = Mth.cos( (g * ((float) Math.PI / 180F))) * Mth.cos((h * ((float) Math.PI / 180F)));
                    float n = Mth.sqrt(k * k + l * l + m * m);
                    k *= f / n;
                    l *= f / n;
                    m *= f / n;
                    player.push( k, l, m);
                    player.startAutoSpinAttack(20, 8.0F, itemStack);
                    if (player.onGround()) {
                        float o = 1.1999999F;
                        player.move(MoverType.SELF, new Vec3(0.0F, 1.1999999F, 0.0F));
                    }
                    level.playSound(null, player, holder.value(), SoundSource.PLAYERS, 1.0F, 1.0F);
                    cir.setReturnValue(true);
                }
            }
        }
    }

    @Inject(method = "use", at = @At("RETURN"), cancellable = true)
    public void use(Level level, Player player, InteractionHand interactionHand, CallbackInfoReturnable<InteractionResult> cir) {
        ItemStack itemStack = player.getItemInHand(interactionHand);
        if (ItemUtils.hasEnchantment(itemStack, "attuning") && !(player.experienceLevel > 0 || player.getHealth() > 0)) {
            cir.setReturnValue(InteractionResult.FAIL);
        }
    }
}
