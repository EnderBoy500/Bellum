package net.enderboy500.bellum.mixin;

import net.enderboy500.bellum.content.BellumMobEffects;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Player.class)
public class PlayerMixin {
    @Inject(method = "isMobilityRestricted", at = @At("HEAD"), cancellable = true)
    public void enderlib$sprint(CallbackInfoReturnable<Boolean> cir) {
        Player player = (Player) (Object) this;
        if (player.hasEffect(BellumMobEffects.BLEEDING)) cir.setReturnValue(true);
    }
}
