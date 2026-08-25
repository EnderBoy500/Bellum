package net.enderboy500.bellum.client;

import net.enderboy500.bellum.client.entity.BellumEntityModelLayers;
import net.enderboy500.bellum.client.entity.renderer.HellforkRenderer;
import net.enderboy500.bellum.client.entity.renderer.PitchforkRenderer;
import net.enderboy500.bellum.client.entity.renderer.ThrownAnchorRenderer;
import net.enderboy500.bellum.client.entity.renderer.ThrownKunaiRenderer;
import net.enderboy500.bellum.client.particle.ShockwaveParticle;
import net.enderboy500.bellum.content.BellumEntities;
import net.enderboy500.bellum.content.BellumParticleTypes;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.model.object.projectile.TridentModel;
import net.minecraft.client.particle.AttackSweepParticle;

public class BellumClient implements ClientModInitializer {
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
    }
}
