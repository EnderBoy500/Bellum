package net.enderboy500.bellum.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.enderboy500.bellum.client.event.OutlineEntityEvent;
import net.enderboy500.bellum.content.BellumItems;
import net.enderboy500.bellum.util.BellumDataComponents;
import net.enderboy500.bellum.util.OutlineDataAttachment;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class EntityMixin implements OutlineDataAttachment {
    @Unique
    private OutlineEntityEvent.@Nullable OutlineData outlineData = null;

    @Override
    public OutlineEntityEvent.@Nullable OutlineData getOutlineData() {
        return outlineData;
    }

    @Inject(method = "tick", at = @At("TAIL"))
    private void slib$outlineEntity(CallbackInfo ci) {
        outlineData = OutlineEntityEvent.EVENT.invoker().getOutlineData((Entity) (Object) this);
    }

    @ModifyReturnValue(method = "getTeamColor", at = @At("RETURN"))
    private int slib$outlineEntity(int original) {
        if (outlineData != null && outlineData.color().isPresent()) {
            return outlineData.color().getAsInt();
        }
        return original;
    }

    @Inject(method = "isInvisibleTo", at = @At("RETURN"), cancellable = true)
    public void showInvis(Player player, CallbackInfoReturnable<Boolean> cir) {
        if (cir.getReturnValue()) {
            ItemStack stack = player.getItemBySlot(EquipmentSlot.HEAD);
            if (stack.is(BellumItems.GOGGLES) && stack.get(BellumDataComponents.LENS) != null && stack.get(BellumDataComponents.LENS).is(BellumItems.TRUE_SIGHT_LENS)) cir.setReturnValue(false);
        }
    }
}

