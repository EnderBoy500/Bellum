package net.enderboy500.bellum.content;

import net.enderboy500.enderlib.effect.BasicStatusEffect;
import net.enderboy500.enderlib.helper.RegistryHelper;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class BellumMobEffects {
    public static final Holder<MobEffect> BLEEDING = RegistryHelper.registerEffect("bleeding", new BasicStatusEffect(MobEffectCategory.HARMFUL, 0x780606));
    public static final Holder<MobEffect> STUN = RegistryHelper.registerEffect("stun", new BasicStatusEffect(MobEffectCategory.NEUTRAL, 0x89CFF0));

    public static void loadMobEffects() {}
}