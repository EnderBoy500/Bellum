package net.enderboy500.bellum.networking;

import net.enderboy500.bellum.networking.packet.RocketBoostC2SPayload;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FireworkRocketEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;


public class ServerboundPackets {
    public static void handleRocketBoostPayload(RocketBoostC2SPayload payload, ServerPlayNetworking.Context context) {
        Player player = context.player();
        Level level = player.level();
        if (level instanceof ServerLevel serverLevel) {
            Projectile.spawnProjectile(new FireworkRocketEntity(level, Items.FIREWORK_ROCKET.getDefaultInstance(), player), serverLevel, Items.FIREWORK_ROCKET.getDefaultInstance());
            player.getCooldowns().addCooldown(payload.stack(), 20*40);
        }
    }
}
