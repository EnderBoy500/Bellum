package net.enderboy500.bellum.content;

import net.enderboy500.bellum.Bellum;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.CreativeModeTab;

public class BellumCreativeModeTabs {
    public static final CreativeModeTab BELLUM_TAB = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB,
            Identifier.fromNamespaceAndPath(Bellum.MOD_ID, "bellum"), FabricItemGroup.builder()
                    .title(Component.translatable("itemgroup.bellum"))
                    .icon(BellumItems.NETHERITE_SICKLE::getDefaultInstance)
                    .displayItems(((itemDisplayParameters, output) -> {
                        output.accept(BellumItems.WOODEN_SICKLE);
                        output.accept(BellumItems.STONE_SICKLE);
                        output.accept(BellumItems.COPPER_SICKLE);
                        output.accept(BellumItems.IRON_SICKLE);
                        output.accept(BellumItems.GOLDEN_SICKLE);
                        output.accept(BellumItems.DIAMOND_SICKLE);
                        output.accept(BellumItems.NETHERITE_SICKLE);

                        output.accept(BellumItems.WOODEN_SCYTHE);
                        output.accept(BellumItems.STONE_SCYTHE);
                        output.accept(BellumItems.COPPER_SCYTHE);
                        output.accept(BellumItems.IRON_SCYTHE);
                        output.accept(BellumItems.GOLDEN_SCYTHE);
                        output.accept(BellumItems.DIAMOND_SCYTHE);
                        output.accept(BellumItems.NETHERITE_SCYTHE);

                        output.accept(BellumItems.WOODEN_BATTLE_AXE);
                        output.accept(BellumItems.STONE_BATTLE_AXE);
                        output.accept(BellumItems.COPPER_BATTLE_AXE);
                        output.accept(BellumItems.IRON_BATTLE_AXE);
                        output.accept(BellumItems.GOLDEN_BATTLE_AXE);
                        output.accept(BellumItems.DIAMOND_BATTLE_AXE);
                        output.accept(BellumItems.NETHERITE_BATTLE_AXE);

                        output.accept(BellumItems.WOODEN_DAGGER);
                        output.accept(BellumItems.STONE_DAGGER);
                        output.accept(BellumItems.COPPER_DAGGER);
                        output.accept(BellumItems.IRON_DAGGER);
                        output.accept(BellumItems.GOLDEN_DAGGER);
                        output.accept(BellumItems.DIAMOND_DAGGER);
                        output.accept(BellumItems.NETHERITE_DAGGER);

                        output.accept(BellumItems.WOODEN_KUNAI);
                        output.accept(BellumItems.STONE_KUNAI);
                        output.accept(BellumItems.COPPER_KUNAI);
                        output.accept(BellumItems.IRON_KUNAI);
                        output.accept(BellumItems.GOLDEN_KUNAI);
                        output.accept(BellumItems.DIAMOND_KUNAI);
                        output.accept(BellumItems.NETHERITE_KUNAI);

                        output.accept(BellumItems.PITCHFORK);
                        output.accept(BellumItems.HELLFORK_UPGRADE_SMITHING_TEMPLATE);
                        output.accept(BellumItems.HELLFORK);
                        output.accept(BellumItems.ANCHOR);
                    }
                    )).build());

    public static void loadCreativeModeTabs() {}
}
