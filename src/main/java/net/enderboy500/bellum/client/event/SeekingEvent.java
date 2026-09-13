package net.enderboy500.bellum.client.event;

import net.enderboy500.bellum.content.BellumItems;
import net.enderboy500.bellum.util.BellumDataComponents;
import net.enderboy500.bellum.util.BellumTags;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.util.TriState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import java.util.OptionalInt;

public class SeekingEvent {
    public static void init() {
        ClientTickEvents.END_WORLD_TICK.register(new Tick());
        OutlineEntityEvent.EVENT.register(new Outline());
    }

    private static final Minecraft client = Minecraft.getInstance();

    public static float xrayDistance = 0;

    private static class Tick implements ClientTickEvents.EndWorldTick {
        @Override
        public void onEndTick(ClientLevel level) {
            xrayDistance = client.player != null && !client.player.isSpectator() ? 16 : 0;
        }
    }

    private static class Outline implements OutlineEntityEvent {
        private static final OutlineData DATA = new OutlineData(TriState.TRUE, OptionalInt.empty());

        @Override
        public OutlineData getOutlineData(Entity entity) {
            if (xrayDistance > 0) {
                Player player = client.player;
                if (player != null && entity instanceof LivingEntity living && !living.isInvisible()) {
                    if (entity.distanceTo(player) < xrayDistance && player.getItemBySlot(EquipmentSlot.HEAD).is(BellumItems.GOGGLES) && player.getItemBySlot(EquipmentSlot.HEAD).get(BellumDataComponents.LENS) != null && player.getItemBySlot(EquipmentSlot.HEAD).get(BellumDataComponents.LENS).is(BellumItems.SEEKER_LENS) && living != player && !living.getType().is(BellumTags.UNSEEKABLE)) {
                        return DATA;
                    }
                }
            }
            return null;
        }
    }
}