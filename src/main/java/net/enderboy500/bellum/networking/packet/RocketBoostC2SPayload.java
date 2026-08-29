package net.enderboy500.bellum.networking.packet;

import net.enderboy500.bellum.Bellum;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.item.ItemStack;

public record RocketBoostC2SPayload(ItemStack stack) implements CustomPacketPayload {
    public static final Type<RocketBoostC2SPayload> TYPE = new Type<>(Bellum.id("rocket_boost"));
    public static final StreamCodec<RegistryFriendlyByteBuf, RocketBoostC2SPayload> STREAM_CODEC = StreamCodec.composite(ItemStack.STREAM_CODEC, RocketBoostC2SPayload::stack, RocketBoostC2SPayload::new);

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
