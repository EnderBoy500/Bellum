package net.enderboy500.bellum.util;

import com.mojang.serialization.Codec;
import io.github.ciph3rj.cipherlib.helper.RegistryHelper;
import net.enderboy500.bellum.util.component.KunaiEffectComponent;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.resources.Identifier;

public class BellumDataComponents {
    public static final DataComponentType<Float> KUNAI_ATTACK_DAMAGE = RegistryHelper.registerDataComponent("kunai_attack_damage",
            builder -> builder.persistent(Codec.FLOAT).networkSynchronized(ByteBufCodecs.FLOAT));
    public static final DataComponentType<KunaiEffectComponent> KUNAI_EFFECT = RegistryHelper.registerDataComponent("kunai_effect",
            builder -> builder.persistent(KunaiEffectComponent.CODEC).networkSynchronized(KunaiEffectComponent.PACKET_CODEC));
    public static final DataComponentType<Identifier> ANCHOR_CHAIN = RegistryHelper.registerDataComponent("achor_chain",
            builder -> builder.persistent(Identifier.CODEC).networkSynchronized(Identifier.STREAM_CODEC));

    public static void loadDataComponents() {}
}
