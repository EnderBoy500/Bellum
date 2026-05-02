package net.enderboy500.bellum.content;

import net.enderboy500.enderlib.helper.RegistryHelper;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;

public class BellumParticleTypes {
    public static final SimpleParticleType CIPHERED_SWEEP = RegistryHelper.registerParticleType("ciphered_sweep", FabricParticleTypes.simple(true));
    public static final SimpleParticleType SOUL_SWEEP = RegistryHelper.registerParticleType("soul_sweep", FabricParticleTypes.simple(true));

    public static void loadParticleTypes() {}
}
