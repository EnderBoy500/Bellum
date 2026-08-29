package net.enderboy500.bellum.item;

import io.github.ciph3rj.cipherlib.item.component.CipherLibComponents;
import net.enderboy500.bellum.Bellum;
import net.minecraft.core.component.DataComponents;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.*;
import net.minecraft.world.level.Level;

import java.util.Optional;

public class NaginataItem extends Item {
    public NaginataItem(ToolMaterial toolMaterial, Properties properties) {
        super(toolMaterial.applySwordProperties(properties.component(CipherLibComponents.HAS_SWEEP_ATTACK, true).stacksTo(1)
                ,3, -2.6f).attributes(ItemAttributeModifiers.builder()
                .add(Attributes.ENTITY_INTERACTION_RANGE, new AttributeModifier(Bellum.id("extra_reach"), 1.5f, AttributeModifier.Operation.ADD_VALUE),
                        EquipmentSlotGroup.MAINHAND).add(
                        Attributes.ATTACK_DAMAGE,
                        new AttributeModifier(Item.BASE_ATTACK_DAMAGE_ID, 2.5 + toolMaterial.attackDamageBonus(), AttributeModifier.Operation.ADD_VALUE),
                        EquipmentSlotGroup.MAINHAND
                )
                .add(Attributes.ATTACK_SPEED, new AttributeModifier(Item.BASE_ATTACK_SPEED_ID, -2.8, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND).build()
        ));
    }
}
