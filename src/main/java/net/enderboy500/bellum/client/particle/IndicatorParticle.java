package net.enderboy500.bellum.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SingleQuadParticle;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.RandomSource;
import org.jspecify.annotations.Nullable;

public class IndicatorParticle extends SingleQuadParticle {
    float i = 1;
    public IndicatorParticle(ClientLevel clientLevel, double d, double e, double f, SpriteSet textureAtlasSprite) {
        super(clientLevel, d, e, f, textureAtlasSprite.first());
        setLifetime(30);
        this.gravity = 0;
        this.friction = 0;
        this.onGround = true;
        this.quadSize = 0.01F;
    }

    @Override
    protected int getLightColor(float f) {
        return 255;
    }

    @Override
    public FacingCameraMode getFacingCameraMode() {
        return (quaternionf, camera, f) -> {
            quaternionf.rotateX((float) -Math.PI / 2);
        };
    }

    @Override
    public void tick() {
        super.tick();
        if (i < 1.4f) {
            scale(i);
            i = i + 0.02f;
        }
    }

    @Override
    protected Layer getLayer() {
        return Layer.TRANSLUCENT;
    }

    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprite;

        public Provider(SpriteSet sprite) {
            this.sprite = sprite;
        }

        @Override
        public @Nullable Particle createParticle(SimpleParticleType particleOptions, ClientLevel clientLevel, double d, double e, double f, double g, double h, double i, RandomSource randomSource) {
            return new IndicatorParticle(clientLevel, d, e, f, sprite);
        }
    }
}
