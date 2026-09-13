package net.enderboy500.bellum.content;

import io.github.ciph3rj.cipherlib.helper.RegistryHelper;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;

public class BellumParticleTypes {
    public static final SimpleParticleType CIPHERED_SWEEP = RegistryHelper.registerParticleType("ciphered_sweep", FabricParticleTypes.simple(true));
    public static final SimpleParticleType SOUL_SWEEP = RegistryHelper.registerParticleType("soul_sweep", FabricParticleTypes.simple(true));
    public static final SimpleParticleType ANCHOR_SWEEP = RegistryHelper.registerParticleType("anchor_sweep", FabricParticleTypes.simple(true));
    public static final SimpleParticleType SHOCKWAVE = RegistryHelper.registerParticleType("shockwave", FabricParticleTypes.simple(true));
    public static final SimpleParticleType FOOTSTEPS = RegistryHelper.registerParticleType("footsteps", FabricParticleTypes.simple(true));
    public static final SimpleParticleType SAFE_INDICATOR = RegistryHelper.registerParticleType("safe_indicator", FabricParticleTypes.simple(true));
    public static final SimpleParticleType DANGER_INDICATOR = RegistryHelper.registerParticleType("danger_indicator", FabricParticleTypes.simple(true));

    public static void loadParticleTypes() {}
}
