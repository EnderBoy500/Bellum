package net.enderboy500.bellum.util;

import io.github.ciph3rj.cipherlib.item.component.CipherLibComponents;
import io.github.ciph3rj.cipherlib.util.ModifiedComponent;
import io.github.ciph3rj.cipherlib.util.skin.ComponentSkin;
import io.github.ciph3rj.cipherlib.util.skin.ItemSkin;
import io.github.ciph3rj.cipherlib.util.skin.WeaponSkin;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class AnchorSkin extends ComponentSkin {
    private final AnchorSkin.Modifier modifier;

    public AnchorSkin(String id, Identifier modelId, Modifier modifier) {
        super(id, modelId);
        this.modifier = modifier;
    }

    @Override
    public <T> List<ModifiedComponent<T>> modifiedComponents(ItemStack itemStack) {
        return modifier.modifiedComponents();
    }

    public static class Modifier {
        private final ParticleOptions particleEffect;
        private final Identifier chain;

        public Modifier(ParticleOptions particleEffect, Identifier chain) {
            this.particleEffect = particleEffect;
            this.chain = chain;
        }

        public <T> List<ModifiedComponent<T>> modifiedComponents() {
            List<ModifiedComponent<T>> list = new ArrayList();
            if (this.particleEffect != null) {
                list.add(ModifiedComponent.create(CipherLibComponents.SWEEP_ATTACK_PARTICLE, this.particleEffect));
            }

            if (this.chain != null) {
                list.add(ModifiedComponent.create(BellumDataComponents.ANCHOR_CHAIN, this.chain));
            }

            return list;
        }

        public static AnchorSkin.Modifier create(ParticleOptions particleEffect, Identifier identifier) {
            return new AnchorSkin.Modifier(particleEffect, identifier);
        }

        public static AnchorSkin.Modifier create(ParticleOptions particleEffect) {
            return new AnchorSkin.Modifier(particleEffect, (Identifier)null);
        }

        public static AnchorSkin.Modifier create(Identifier identifier) {
            return new AnchorSkin.Modifier((ParticleOptions)null, identifier);
        }
    }
}
