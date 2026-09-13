package net.enderboy500.bellum.mixin;

import net.enderboy500.bellum.content.BellumItems;
import net.enderboy500.bellum.util.BellumDataComponents;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.waypoints.TrackedWaypoint;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameRenderer.class)
public abstract class GameRendererMixin implements TrackedWaypoint.Projector, AutoCloseable{

    @Mutable
    @Final
    @Shadow
    private final Minecraft minecraft;


    @Shadow
    public abstract void clearPostEffect();

    @Shadow
    protected abstract void setPostEffect(Identifier identifier);

    protected GameRendererMixin(Minecraft minecraft) {
        this.minecraft = minecraft;
    }

    @Inject(at = @At("TAIL"), method = "renderLevel")
    private void init(CallbackInfo ci) {
        assert minecraft != null;
        LocalPlayer clientPlayerEntity = this.minecraft.player;
        assert clientPlayerEntity != null;
        if(clientPlayerEntity.getItemBySlot(EquipmentSlot.HEAD).is(BellumItems.GOGGLES)) {
            ItemStack stack = clientPlayerEntity.getItemBySlot(EquipmentSlot.HEAD);
            if (stack.get(BellumDataComponents.LENS) != null && stack.get(BellumDataComponents.LENS).get(BellumDataComponents.SHADER_EFFECT) != null) {
                this.setPostEffect(stack.get(BellumDataComponents.LENS).get(BellumDataComponents.SHADER_EFFECT));
            } else {
                this.clearPostEffect();
            }
        } else {
            this.clearPostEffect();
        }
    }
}