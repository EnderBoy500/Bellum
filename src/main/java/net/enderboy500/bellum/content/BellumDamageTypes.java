package net.enderboy500.bellum.content;

import io.github.ciph3rj.cipherlib.helper.RegistryHelper;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageType;

public class BellumDamageTypes {
    public static final ResourceKey<DamageType> DRAINED_SOUL = RegistryHelper.registerDamageType("drained_soul");

    public static void loadDamageTypes() {}
}
