package net.enderboy500.bellum.item;

import io.github.ciph3rj.cipherlib.item.component.CipherLibComponents;
import net.enderboy500.bellum.Bellum;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.component.ItemAttributeModifiers;

public class BattleAxeItem extends AxeItem {

    public BattleAxeItem(ToolMaterial toolMaterial, float f, float g, Properties properties) {
        super(toolMaterial, f, g, properties.component(CipherLibComponents.HAS_SWEEP_ATTACK, true).component(CipherLibComponents.HOLD_WITH_BOTH_HANDS, true));
    }
}
