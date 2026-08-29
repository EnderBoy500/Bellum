package net.enderboy500.bellum.networking;

import net.enderboy500.bellum.networking.packet.RocketBoostC2SPayload;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.RegistryFriendlyByteBuf;

public class BellumPackets {
    private static void registerServerboundPackets(PayloadTypeRegistry<RegistryFriendlyByteBuf> registry) {
        registry.register(RocketBoostC2SPayload.TYPE, RocketBoostC2SPayload.STREAM_CODEC);

        ServerPlayNetworking.registerGlobalReceiver(RocketBoostC2SPayload.TYPE, ServerboundPackets::handleRocketBoostPayload);
    }

    public static void load() {
        registerServerboundPackets(PayloadTypeRegistry.playC2S());
    }
}
