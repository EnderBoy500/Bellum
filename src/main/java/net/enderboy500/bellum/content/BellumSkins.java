package net.enderboy500.bellum.content;

import io.github.ciph3rj.cipherlib.util.skin.*;
import net.enderboy500.bellum.Bellum;
import net.enderboy500.bellum.util.AnchorSkin;
import net.enderboy500.bellum.util.event.BellumSoundEvents;
import net.minecraft.network.chat.Component;

import java.util.List;

public class BellumSkins {
    public static final ItemSkin CIPHERED_SICKLE = new NamedWeaponSkin("ciphered_sickle", Bellum.id("ciphered_sickle"), WeaponSkin.Modifier.create(BellumParticleTypes.CIPHERED_SWEEP, BellumSoundEvents.SOUL_SWEEP_ATTACK), Component.translatable("item.skin.bellum.ciphered_sickle"));
    public static final ItemSkin SOUL_SICKLE = new NamedWeaponSkin("soul_sickle", Bellum.id("soul_sickle"), WeaponSkin.Modifier.create(BellumParticleTypes.SOUL_SWEEP, BellumSoundEvents.SOUL_SWEEP_ATTACK), Component.translatable("item.skin.bellum.soul_sickle"));

    public static final ItemSkin DIVINE_DAGGER = new ItemNameSkin("divine_dagger", Bellum.id("divine_dagger"), Component.translatable("item.skin.bellum.divine_dagger"));
    public static final ItemSkin MOTION_ICON = new ItemSkin("motion_icon", Bellum.id("motion_icon"));
    public static final ItemSkin MOTION_ICON_2D = new ItemSkin("motion_icon_2d", Bellum.id("motion_icon_2d"));

    public static final ItemSkin DIVINE_SCYTHE = new ItemSkin("divine_scythe", Bellum.id("divine_scythe"));

    public static final ItemSkin DIVINE_BATTLE_AXE = new ItemSkin("divine_battle_axe", Bellum.id("divine_battle_axe"));

    public static final ItemSkin SOUL_ANCHOR = new AnchorSkin("soul_anchor", Bellum.id("soul_anchor"), AnchorSkin.Modifier.create(BellumParticleTypes.SOUL_SWEEP, Bellum.SOUL_ANCHOR_CHAIN));

    public static void loadAndRegister() {
        ItemSkinRegistry.registerSkin(List.of(BellumItems.DIAMOND_SICKLE, BellumItems.NETHERITE_SICKLE), List.of(CIPHERED_SICKLE, SOUL_SICKLE));
        ItemSkinRegistry.registerSkin(List.of(BellumItems.DIAMOND_DAGGER, BellumItems.NETHERITE_DAGGER), List.of(DIVINE_DAGGER, MOTION_ICON, MOTION_ICON_2D));
        ItemSkinRegistry.registerSkin(List.of(BellumItems.DIAMOND_SCYTHE, BellumItems.NETHERITE_SCYTHE), DIVINE_SCYTHE);
        ItemSkinRegistry.registerSkin(List.of(BellumItems.DIAMOND_BATTLE_AXE, BellumItems.NETHERITE_BATTLE_AXE), DIVINE_BATTLE_AXE);
        ItemSkinRegistry.registerSkin(BellumItems.ANCHOR, SOUL_ANCHOR);
    }
}
