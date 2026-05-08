package net.enderboy500.bellum.content;

import net.enderboy500.bellum.Bellum;
import net.enderboy500.bellum.util.event.BellumSoundEvents;
import net.enderboy500.enderlib.util.skin.*;
import net.minecraft.network.chat.Component;

import java.util.List;

public class BellumSkins {
    public static final ItemSkin CIPHERED_SICKLE = new NamedWeaponSkin("ciphered_sickle", Bellum.id("ciphered_sickle"), WeaponSkin.Modifier.create(BellumParticleTypes.CIPHERED_SWEEP, BellumSoundEvents.SOUL_SWEEP_ATTACK), Component.translatable("item.skin.bellum.ciphered_sickle"));
    public static final ItemSkin SOUL_SICKLE = new NamedWeaponSkin("soul_sickle", Bellum.id("soul_sickle"), WeaponSkin.Modifier.create(BellumParticleTypes.SOUL_SWEEP, BellumSoundEvents.SOUL_SWEEP_ATTACK), Component.translatable("item.skin.bellum.soul_sickle"));

    public static final ItemSkin DIVINE_DAGGER = new NamedWeaponSkin("divine_dagger", Bellum.id("divine_dagger"), WeaponSkin.Modifier.create(BellumSoundEvents.SOUL_SWEEP_ATTACK), Component.translatable("item.skin.bellum.divine_dagger"));
    public static final ItemSkin MOTION_ICON = new ItemSkin("motion_icon", Bellum.id("motion_icon"));
    public static final ItemSkin MOTION_ICON_2D = new ItemSkin("motion_icon_2d", Bellum.id("motion_icon_2d"));

    public static void loadAndRegister() {
        ItemSkinRegistry.registerSkin(List.of(BellumItems.DIAMOND_SICKLE, BellumItems.NETHERITE_SICKLE), List.of(CIPHERED_SICKLE, SOUL_SICKLE));
        ItemSkinRegistry.registerSkin(List.of(BellumItems.DIAMOND_DAGGER, BellumItems.NETHERITE_DAGGER), List.of(DIVINE_DAGGER, MOTION_ICON, MOTION_ICON_2D));
    }
}
