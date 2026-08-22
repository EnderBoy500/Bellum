package net.enderboy500.bellum.projectile;

import net.enderboy500.bellum.content.BellumItems;
import net.enderboy500.bellum.util.BellumDataComponents;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.Position;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.component.Tool;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.component.Weapon;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;

import java.util.List;
import java.util.function.Consumer;

public class KunaiItem extends Item implements ProjectileItem {
    public final float damage;

    public KunaiItem(Properties properties, float damage) {
        super(properties.component(BellumDataComponents.KUNAI_ATTACK_DAMAGE, damage).stacksTo(16)
                .component(DataComponents.ATTRIBUTE_MODIFIERS, ItemAttributeModifiers.builder().add(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_ID,damage - 1 , AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND).add(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_ID, -2.9f, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND).build()));
        this.damage = damage;
    }

    public static Tool createToolProperties() {
        return new Tool(List.of(), 1.0f, 2, false);
    }

    @Override
    public ItemUseAnimation getUseAnimation(ItemStack itemStack) {
        return ItemUseAnimation.TRIDENT;
    }

    @Override
    public int getUseDuration(ItemStack itemStack, LivingEntity livingEntity) {
        return 72000;
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand interactionHand) {
        ItemStack itemStack = player.getItemInHand(interactionHand);
        if (itemStack.nextDamageWillBreak()) {
            return InteractionResult.FAIL;
        }
        int j = this.getUseDuration(itemStack, player);
        if (j < 10) {
            return InteractionResult.FAIL;
        }
        if (itemStack.nextDamageWillBreak()) {
            return InteractionResult.FAIL;
        }
        Holder<SoundEvent> holder = EnchantmentHelper.pickHighestLevel(itemStack, EnchantmentEffectComponents.TRIDENT_SOUND).orElse(SoundEvents.TRIDENT_THROW);
        player.awardStat(Stats.ITEM_USED.get(this));
        if (level instanceof ServerLevel) {
            ServerLevel serverLevel = (ServerLevel)level;
            itemStack.hurtWithoutBreaking(1, player);
            ItemStack itemStack2 = itemStack.consumeAndReturn(1, player);
            ItemStack itemStack3 = itemStack.copy();
            itemStack3.setCount(1);
            ThrownKunaiEntity thrownTrident = Projectile.spawnProjectileFromRotation(ThrownKunaiEntity::new, serverLevel, itemStack2, player, 0.0f, 2.5f, 0.2f);
            thrownTrident.getInventory().addItem(itemStack3);
            if (player.hasInfiniteMaterials()) {
                thrownTrident.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
            }
            level.playSound(null, thrownTrident, holder.value(), SoundSource.PLAYERS, 1.0f, 1.0f);
            return InteractionResult.SUCCESS;
        }
        level.playSound(null, player, holder.value(), SoundSource.PLAYERS, 1.0f, 1.0f);
        return InteractionResult.CONSUME;
    }

    @Override
    public Projectile asProjectile(Level level, Position position, ItemStack itemStack, Direction direction) {
        ThrownKunaiEntity thrownKunai = new ThrownKunaiEntity(level, position.x(), position.y(), position.z(), itemStack.copyWithCount(1));
        thrownKunai.pickup = AbstractArrow.Pickup.ALLOWED;
        return thrownKunai;
    }

    @Override
    public void appendHoverText(ItemStack itemStack, TooltipContext tooltipContext, TooltipDisplay tooltipDisplay, Consumer<Component> consumer, TooltipFlag tooltipFlag) {
        if (itemStack.has(BellumDataComponents.KUNAI_EFFECT)) {
            consumer.accept(Component.translatable("tooltip.bellum.kunai.effect"));
            for (MobEffectInstance effectInstance : itemStack.get(BellumDataComponents.KUNAI_EFFECT).effects()) {
                consumer.accept(Component.translatable(effectInstance.getEffect().value().getDescriptionId()).withStyle(ChatFormatting.GOLD));
            }
        }
        super.appendHoverText(itemStack, tooltipContext, tooltipDisplay, consumer, tooltipFlag);
    }

    public static Item registerKunai(String id, float damage) {
        return BellumItems.register(id, properties -> new KunaiItem(properties, damage), (new Item.Properties()).useCooldown(100).component(DataComponents.TOOL, KunaiItem.createToolProperties()).component(DataComponents.WEAPON, new Weapon(1)));
    }
}
