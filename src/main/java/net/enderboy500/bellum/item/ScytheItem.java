package net.enderboy500.bellum.item;

import io.github.ciph3rj.cipherlib.item.component.CipherLibComponents;
import net.enderboy500.bellum.Bellum;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.context.UseOnContext;

public class ScytheItem extends Item {
    public ScytheItem(ToolMaterial material, Properties properties) {
        super(material.applyToolProperties(properties.component(CipherLibComponents.CAN_TILL, true), BlockTags.MINEABLE_WITH_HOE, 3, -3, 0).attributes(ItemAttributeModifiers.builder()
                .add(Attributes.ENTITY_INTERACTION_RANGE, new AttributeModifier(Bellum.id("extra_reach"), 1.25f, AttributeModifier.Operation.ADD_VALUE),
                        EquipmentSlotGroup.MAINHAND).add(
                        Attributes.ATTACK_DAMAGE,
                        new AttributeModifier(Item.BASE_ATTACK_DAMAGE_ID, 3 + material.attackDamageBonus(), AttributeModifier.Operation.ADD_VALUE),
                        EquipmentSlotGroup.MAINHAND
                )
                .add(Attributes.ATTACK_SPEED, new AttributeModifier(Item.BASE_ATTACK_SPEED_ID, -3, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND).build()));
    }

}
