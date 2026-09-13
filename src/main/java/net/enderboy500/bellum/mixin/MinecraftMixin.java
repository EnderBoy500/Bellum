package net.enderboy500.bellum.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.enderboy500.bellum.client.event.OutlineEntityEvent;
import net.enderboy500.bellum.util.OutlineDataAttachment;
import net.fabricmc.fabric.api.util.TriState;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.Entity;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Minecraft.class)
public abstract class MinecraftMixin {

    @ModifyReturnValue(method = "shouldEntityAppearGlowing", at = @At("RETURN"))
    private boolean outlineEntity(boolean original, Entity entity) {
        OutlineEntityEvent.@Nullable OutlineData outlineData = ((OutlineDataAttachment) entity).getOutlineData();
        if (outlineData != null && outlineData.state() != TriState.DEFAULT) {
            return outlineData.state().get();
        }
        return original;
    }
}