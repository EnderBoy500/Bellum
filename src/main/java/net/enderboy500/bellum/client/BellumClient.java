package net.enderboy500.bellum.client;

import net.enderboy500.bellum.client.entity.BellumEntityModelLayers;
import net.enderboy500.bellum.client.entity.renderer.*;
import net.enderboy500.bellum.client.event.BellumClientEvents;
import net.enderboy500.bellum.client.event.SeekingEvent;
import net.enderboy500.bellum.client.particle.FootstepParticle;
import net.enderboy500.bellum.client.particle.IndicatorParticle;
import net.enderboy500.bellum.client.particle.ShockwaveParticle;
import net.enderboy500.bellum.content.BellumEntities;
import net.enderboy500.bellum.content.BellumItems;
import net.enderboy500.bellum.content.BellumParticleTypes;
import net.enderboy500.bellum.item.GogglesItem;
import net.enderboy500.bellum.util.BellumDataComponents;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.model.object.projectile.TridentModel;
import net.minecraft.client.particle.AttackSweepParticle;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;

public class BellumClient implements ClientModInitializer {
    private int tick = 0;

    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(BellumEntities.THROWN_KUNAI, ThrownKunaiRenderer::new);
        EntityRendererRegistry.register(BellumEntities.ANCHOR_ENTITY, ThrownAnchorRenderer::new);
        EntityRendererRegistry.register(BellumEntities.HELLFORK_ENTITY, HellforkRenderer::new);
        EntityRendererRegistry.register(BellumEntities.PITCHFORK_ENTITY, PitchforkRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(BellumEntityModelLayers.HELLFORK, TridentModel::createLayer);
        EntityModelLayerRegistry.registerModelLayer(BellumEntityModelLayers.PITCHFORK, TridentModel::createLayer);

        ParticleFactoryRegistry.getInstance().register(BellumParticleTypes.CIPHERED_SWEEP, AttackSweepParticle.Provider::new);
        ParticleFactoryRegistry.getInstance().register(BellumParticleTypes.SOUL_SWEEP, AttackSweepParticle.Provider::new);
        ParticleFactoryRegistry.getInstance().register(BellumParticleTypes.ANCHOR_SWEEP, AttackSweepParticle.Provider::new);
        ParticleFactoryRegistry.getInstance().register(BellumParticleTypes.SHOCKWAVE, ShockwaveParticle.Provider::new);
        ParticleFactoryRegistry.getInstance().register(BellumParticleTypes.FOOTSTEPS, FootstepParticle.Provider::new);
        ParticleFactoryRegistry.getInstance().register(BellumParticleTypes.SAFE_INDICATOR, IndicatorParticle.Provider::new);
        ParticleFactoryRegistry.getInstance().register(BellumParticleTypes.DANGER_INDICATOR, IndicatorParticle.Provider::new);

        ClientTickEvents.END_CLIENT_TICK.register(BellumClientEvents::onEndTick);

        SeekingEvent.init();

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player != null && client.level != null) {
                ItemStack stack = client.player.getItemBySlot(EquipmentSlot.HEAD);
                if (stack.is(BellumItems.GOGGLES) && stack.get(BellumDataComponents.LENS) != null && stack.get(BellumDataComponents.LENS).is(BellumItems.TRACKING_LENS)) {
                    for (AbstractClientPlayer player : client.level.players()) {
                        boolean isMoving = Math.abs(player.getX() - player.xo) > 0.05 || Math.abs(player.getZ() - player.zo) > 0.05;
                        double offset = (double) player.getRandom().nextInt(-30, 30) /100;
                        int amount = player.getRandom().nextInt(1, 2);
                        if (player.onGround() && isMoving) {
                            for (int i = 0; i <= amount; i++) {
                                client.level.addAlwaysVisibleParticle(BellumParticleTypes.FOOTSTEPS, player.getX() + offset, player.getY() + 0.001, player.getZ() + offset, 0, 0, 0);
                            }
                        }
                    }
                }
            }
        });

        ClientTickEvents.START_CLIENT_TICK.register(client -> {
            int checkRange = 24;
            if (client.player == null || client.level == null) return;
            ItemStack stack = client.player.getItemBySlot(EquipmentSlot.HEAD);
            BlockPos pos = client.player.blockPosition();
            boolean isWearingGoggles = stack.is(BellumItems.GOGGLES) && stack.get(BellumDataComponents.LENS) != null && stack.get(BellumDataComponents.LENS).is(BellumItems.RECONNAISSANCE_LENS);
            if (!isWearingGoggles) return;

            tick++;
            if (tick < 35) return;
            tick = 0;

            for (int x = -checkRange; x <= checkRange; x++) {
                for (int y = -12; y <= 12; y++) {
                    for (int z = -checkRange; z <= checkRange; z++) {
                        BlockPos checkPos = pos.offset(x,y,z);
                        if (GogglesItem.isDarkBlock(client,checkPos)) {
                            client.level.addParticle(BellumParticleTypes.DANGER_INDICATOR, checkPos.getX() + 0.5, checkPos.getY() + 1.05, checkPos.getZ() + 0.5,0, 0, 0);
                        } else if (!GogglesItem.isDarkBlock(client,checkPos) && client.level.getBlockState(checkPos).isValidSpawn(client.level, checkPos, EntityType.ZOMBIE) && client.level.getBlockState(checkPos.above()).getCollisionShape(client.level, checkPos.above()).isEmpty()) {
                            client.level.addParticle(BellumParticleTypes.SAFE_INDICATOR, checkPos.getX() + 0.5, checkPos.getY() + 1.05, checkPos.getZ() + 0.5,0, 0, 0);
                        }
                    }
                }
            }
        });
    }
}
