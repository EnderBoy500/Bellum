package net.enderboy500.bellum.util;

import net.enderboy500.bellum.Bellum;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Util;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SmithingTemplateItem;

import java.util.List;

public class SmithingTemplateUtil {
    private static final ChatFormatting TITLE_FORMAT = ChatFormatting.GRAY;
    private static final ChatFormatting DESCRIPTION_FORMAT = ChatFormatting.BLUE;
    private static final Component INGREDIENTS_TITLE = Component.translatable(Util.makeDescriptionId("item", Identifier.withDefaultNamespace("smithing_template.ingredients"))).withStyle(TITLE_FORMAT);
    private static final Component APPLIES_TO_TITLE = Component.translatable(Util.makeDescriptionId("item", Identifier.withDefaultNamespace("smithing_template.applies_to"))).withStyle(TITLE_FORMAT);
    private static final Component SMITHING_TEMPLATE_SUFFIX = Component.translatable(Util.makeDescriptionId("item", Identifier.withDefaultNamespace("smithing_template"))).withStyle(TITLE_FORMAT);
    private static final Component HELLFORK_UPGRADE_APPLIES_TO = Component.translatable(Util.makeDescriptionId("item", Bellum.id("smithing_template.hellfork_upgrade.applies_to"))).withStyle(DESCRIPTION_FORMAT);
    private static final Component NETHERITE_UPGRADE_INGREDIENTS = Component.translatable(Util.makeDescriptionId("item", Identifier.withDefaultNamespace("smithing_template.netherite_upgrade.ingredients"))).withStyle(DESCRIPTION_FORMAT);
    private static final Component NETHERITE_UPGRADE_BASE_SLOT_DESCRIPTION = Component.translatable(Util.makeDescriptionId("item", Bellum.id("smithing_template.hellfork_upgrade.base_slot_description")));
    private static final Component NETHERITE_UPGRADE_ADDITIONS_SLOT_DESCRIPTION = Component.translatable(Util.makeDescriptionId("item", Identifier.withDefaultNamespace("smithing_template.netherite_upgrade.additions_slot_description")));
    public static final Identifier EMPTY_SLOT_TRIDENT = Bellum.id("container/slot/trident");
    public static final Identifier EMPTY_SLOT_INGOT = Identifier.withDefaultNamespace("container/slot/ingot");

    private static List<Identifier> createHellforkUpgradeIconList() {
        return List.of(EMPTY_SLOT_TRIDENT);
    }
    private static List<Identifier> createNetheriteUpgradeMaterialList() {
        return List.of(EMPTY_SLOT_INGOT);
    }

    public static SmithingTemplateItem createHellforkUpgradeTemplate(Item.Properties properties) {
        return new SmithingTemplateItem(HELLFORK_UPGRADE_APPLIES_TO, NETHERITE_UPGRADE_INGREDIENTS, NETHERITE_UPGRADE_BASE_SLOT_DESCRIPTION, NETHERITE_UPGRADE_ADDITIONS_SLOT_DESCRIPTION, createHellforkUpgradeIconList(), createNetheriteUpgradeMaterialList(), properties);
    }
}
