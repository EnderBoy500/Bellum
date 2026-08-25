package net.enderboy500.bellum.mixin;

import com.mojang.serialization.MapCodec;
import net.enderboy500.bellum.Bellum;
import net.enderboy500.bellum.client.entity.renderer.HellforkSpecialRenderer;
import net.enderboy500.bellum.client.entity.renderer.PitchforkSpecialRenderer;
import net.minecraft.client.renderer.special.SpecialModelRenderer;
import net.minecraft.client.renderer.special.SpecialModelRenderers;
import net.minecraft.resources.Identifier;
import net.minecraft.util.ExtraCodecs;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SpecialModelRenderers.class)
public abstract class ModelRenderingMixin {
    @Shadow
    @Final
    public static ExtraCodecs.LateBoundIdMapper<Identifier, MapCodec<? extends SpecialModelRenderer.Unbaked>> ID_MAPPER;

    @Inject(method = "bootstrap", at = @At("HEAD"))
    private static void addSpecialModel(CallbackInfo ci) {
        ID_MAPPER.put(Bellum.id("hellfork"), HellforkSpecialRenderer.Unbaked.MAP_CODEC);
        ID_MAPPER.put(Bellum.id("pitchfork"), PitchforkSpecialRenderer.Unbaked.MAP_CODEC);
    }
}
