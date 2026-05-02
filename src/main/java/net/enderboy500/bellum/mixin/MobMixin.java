package net.enderboy500.bellum.mixin;

import net.enderboy500.bellum.content.BellumMobEffects;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Mob.class)
public abstract class MobMixin extends LivingEntity {
    protected MobMixin(EntityType<? extends LivingEntity> entityType, Level level) {
        super(entityType, level);
    }

    @Inject(method = "doHurtTarget", at = @At("HEAD"), cancellable = true)
    public void bellum$preventStunnedEntitiesFromAttacking(ServerLevel serverLevel, Entity entity, CallbackInfoReturnable<Boolean> cir) {
        if (this.hasEffect(BellumMobEffects.STUN)) cir.setReturnValue(false);
    }
}
