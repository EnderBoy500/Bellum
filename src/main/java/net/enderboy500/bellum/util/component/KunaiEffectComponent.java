package net.enderboy500.bellum.util.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;

import java.util.List;

public record KunaiEffectComponent(List<MobEffectInstance> effects) {
    public static final Codec<KunaiEffectComponent> CODEC = RecordCodecBuilder.create((instance) -> instance.group(MobEffectInstance.CODEC.listOf().fieldOf("effects").forGetter(KunaiEffectComponent::effects)).apply(instance, KunaiEffectComponent::new));
    public static final StreamCodec<RegistryFriendlyByteBuf, KunaiEffectComponent> PACKET_CODEC;

    public KunaiEffectComponent(MobEffectInstance effect) {
        this(List.of(effect));
    }

    public void applyEffect(LivingEntity target) {
        boolean bl = false;

        for(MobEffectInstance statusEffectInstance : this.effects) {
            if (target.addEffect(new MobEffectInstance(statusEffectInstance))) {
                bl = true;
            }
        }

    }

    static {
        PACKET_CODEC = StreamCodec.composite(MobEffectInstance.STREAM_CODEC.apply(ByteBufCodecs.list()), KunaiEffectComponent::effects, KunaiEffectComponent::new);
    }
}
