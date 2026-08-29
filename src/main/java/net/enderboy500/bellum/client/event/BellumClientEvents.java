package net.enderboy500.bellum.client.event;

import io.github.ciph3rj.cipherlib.util.ItemUtils;
import net.enderboy500.bellum.networking.packet.RocketBoostC2SPayload;
import net.enderboy500.bellum.util.BellumTags;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class BellumClientEvents {
    public static void onEndTick(Minecraft minecraft) {
        Player player = minecraft.player;
        if (player == null) return;
        ItemStack stack = player.getItemBySlot(EquipmentSlot.CHEST);
        if(stack.isEmpty()) return;

        while (minecraft.options.keyJump.consumeClick()) {
            if (stack.is(BellumTags.ROCKET_BOOST_ENCHANTABLE) && !player.getCooldowns().isOnCooldown(stack) && ItemUtils.hasEnchantment(stack, "rocket_boost")) {
                ClientPlayNetworking.send(new RocketBoostC2SPayload(stack));
                System.out.println("test2");
            }
        }
    }
}
