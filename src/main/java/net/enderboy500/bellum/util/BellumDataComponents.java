package net.enderboy500.bellum.util;

import com.mojang.serialization.Codec;
import net.enderboy500.bellum.Bellum;
import net.enderboy500.bellum.util.component.KunaiEffectComponent;
import net.enderboy500.enderlib.helper.RegistryHelper;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.network.codec.ByteBufCodecs;

public class BellumDataComponents {
    public static final DataComponentType<Float> KUNAI_ATTACK_DAMAGE = RegistryHelper.registerDataComponent("kunai_attack_damage",
            builder -> builder.persistent(Codec.FLOAT).networkSynchronized(ByteBufCodecs.FLOAT));
    public static final DataComponentType<KunaiEffectComponent> KUNAI_EFFECT = RegistryHelper.registerDataComponent("kunai_effect",
            builder -> builder.persistent(KunaiEffectComponent.CODEC).networkSynchronized(KunaiEffectComponent.PACKET_CODEC));

    public static void loadDataComponents() {}
}
