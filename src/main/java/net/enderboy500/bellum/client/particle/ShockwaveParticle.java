package net.enderboy500.bellum.client.particle;


import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.HugeExplosionParticle;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import org.jspecify.annotations.Nullable;

import java.util.Random;

public class ShockwaveParticle extends HugeExplosionParticle {
    public ShockwaveParticle(ClientLevel world, double x, double y, double z, double size, SpriteSet sprites) {
        super(world, x, y, z, size, sprites);
        this.lifetime = 8;
        this.scale(8f);
        this.gravity = 0;
        this.setColor(1, 1, 1);
        this.setAlpha(0.5f);
        this.setSpriteFromAge(sprites);
    }

/*    @Override public float getSize(float tickDelta) {
        return this.scale * MathHelper.clamp((this.age + tickDelta) / this.lifetime, 0, 1);
    }*/


    @Override public void tick() {
        super.tick();
        this.setAlpha((float) Mth.lerp((float) this.age / this.lifetime, 0.5, 0));
    }

    public record Factory(SpriteSet sprites) implements ParticleProvider<SimpleParticleType> {
        @Override
        public @Nullable Particle createParticle(SimpleParticleType particleOptions, ClientLevel clientLevel, double d, double e, double f, double g, double h, double i, RandomSource randomSource) {
            return new ShockwaveParticle(clientLevel, d, e, f, g, sprites);
        }
    }
}
