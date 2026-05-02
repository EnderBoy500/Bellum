package net.enderboy500.bellum.item;

import net.enderboy500.bellum.Bellum;
import net.enderboy500.enderlib.item.ToolFunction;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.context.UseOnContext;

public class ScytheItem extends Item implements ToolFunction {
    public ScytheItem(ToolMaterial material, Properties properties) {
        super(material.applyToolProperties(properties, BlockTags.MINEABLE_WITH_HOE, 3, -3, 0).attributes(ItemAttributeModifiers.builder()
                .add(Attributes.ENTITY_INTERACTION_RANGE, new AttributeModifier(Bellum.id("extra_reach"), 1.25f, AttributeModifier.Operation.ADD_VALUE),
                        EquipmentSlotGroup.MAINHAND).add(
                        Attributes.ATTACK_DAMAGE,
                        new AttributeModifier(Item.BASE_ATTACK_DAMAGE_ID, 3 + material.attackDamageBonus(), AttributeModifier.Operation.ADD_VALUE),
                        EquipmentSlotGroup.MAINHAND
                )
                .add(Attributes.ATTACK_SPEED, new AttributeModifier(Item.BASE_ATTACK_SPEED_ID, -3, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND).build()));
    }

    @Override
    public InteractionResult useOn(UseOnContext useOnContext) {
        if (canTill(useOnContext)) {
            tillFuntion(useOnContext);
            return InteractionResult.SUCCESS;
        }
        return super.useOn(useOnContext);
    }
}
