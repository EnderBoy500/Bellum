package net.enderboy500.bellum.mixin;

import net.enderboy500.bellum.Bellum;
import net.minecraft.core.MappedRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.WritableRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = MappedRegistry.class, priority = 10)
public abstract class MappedRegistryMixin<T> implements WritableRegistry<T> {
    @Shadow
    private boolean frozen;

    @Inject(method = "freeze", at = @At("HEAD"))
    public void freeze(CallbackInfoReturnable<Registry<T>> cir) {
        if (this.frozen) return;

/*        try {
            this.addAlias(Bellum.id("kunai"), Bellum.id("iron_kunai"));
        } catch (Throwable e) {
            Bellum.LOGGER.info("Failed to set alias 'bellum:kunai' -> 'bellum:iron_kunai'");
        }*/
    }
}
