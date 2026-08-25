package net.enderboy500.bellum.projectile;

import io.github.ciph3rj.cipherlib.item.component.CipherLibComponents;
import net.enderboy500.bellum.Bellum;
import net.enderboy500.bellum.content.BellumParticleTypes;
import net.enderboy500.bellum.util.BellumDataComponents;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.Position;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
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
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;

import java.util.List;

public class AnchorItem extends Item implements ProjectileItem {
    public AnchorItem(Properties properties) {
        super(properties.pickaxe(ToolMaterial.NETHERITE, 5, -3).durability(1024).attributes(createAttributes())
                .component(BellumDataComponents.ANCHOR_CHAIN, Bellum.DEFAULT_ANCHOR_CHAIN).component(CipherLibComponents.SWEEP_ATTACK_PARTICLE, BellumParticleTypes.ANCHOR_SWEEP));
    }

    public static ItemAttributeModifiers createAttributes() {
        return ItemAttributeModifiers.builder().add(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_ID, (double)9F, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND).add(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_ID, (double)-2.9F, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND)
                .add(Attributes.SUBMERGED_MINING_SPEED, new AttributeModifier(Identifier.withDefaultNamespace("base_submerged_mine_speed"), 2, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND).build();
    }


    public ItemUseAnimation getUseAnimation(ItemStack itemStack) {
        return ItemUseAnimation.TRIDENT;
    }

    public int getUseDuration(ItemStack itemStack, LivingEntity livingEntity) {
        return 200;
    }

    public boolean releaseUsing(ItemStack itemStack, Level level, LivingEntity livingEntity, int i) {
        if (livingEntity instanceof Player player) {
            Holder<SoundEvent> holder = EnchantmentHelper.pickHighestLevel(itemStack, EnchantmentEffectComponents.TRIDENT_SOUND).orElse(SoundEvents.TRIDENT_THROW);
            player.awardStat(Stats.ITEM_USED.get(this));
            if (level instanceof ServerLevel) {
                ServerLevel serverLevel = (ServerLevel)level;
                itemStack.hurtWithoutBreaking(1, player);
                ItemStack itemStack2 = itemStack.consumeAndReturn(1, player);
                ThrownAnchor thrownAnchor = Projectile.spawnProjectileFromRotation(ThrownAnchor::new, serverLevel, itemStack2, player, 0.0F, 2.5F, 1.0F);
                thrownAnchor.setReturnSlot(player.getInventory().getSelectedSlot());
                if (player.hasInfiniteMaterials()) {
                    thrownAnchor.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
                }

                level.playSound(null, thrownAnchor, holder.value(), SoundSource.PLAYERS, 1.0F, 1.0F);
                return true;
            }
        }
        return false;
    }


    public InteractionResult use(Level level, Player player, InteractionHand interactionHand) {
        ItemStack itemStack = player.getItemInHand(interactionHand);
        if (itemStack.nextDamageWillBreak() && player.getCooldowns().isOnCooldown(itemStack)) {
            return InteractionResult.FAIL;
        } else {
                Holder<SoundEvent> holder = EnchantmentHelper.pickHighestLevel(itemStack, EnchantmentEffectComponents.TRIDENT_SOUND).orElse(SoundEvents.TRIDENT_THROW);
                player.awardStat(Stats.ITEM_USED.get(this));
                if (level instanceof ServerLevel) {
                    ServerLevel serverLevel = (ServerLevel) level;
                    itemStack.hurtWithoutBreaking(1, player);
                    player.getCooldowns().addCooldown(itemStack, 30);
                    ItemStack itemStack2 = itemStack.consumeAndReturn(1, player);
                    ThrownAnchor thrownAnchor = Projectile.spawnProjectileFromRotation(ThrownAnchor::new, serverLevel, itemStack2, player, 0.0F, 2.5F, 1.0F);
                    thrownAnchor.setReturnSlot(player.getInventory().getSelectedSlot());
                    if (player.hasInfiniteMaterials()) {
                        thrownAnchor.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
                    }

                    level.playSound(null, thrownAnchor, holder.value(), SoundSource.PLAYERS, 1.0F, 1.0F);
                    return InteractionResult.SUCCESS;
                }
        }
        return InteractionResult.FAIL;
    }

    @Override
    public void hurtEnemy(ItemStack itemStack, LivingEntity livingEntity, LivingEntity livingEntity2) {
        int bonusDamage = 5;
        DamageSource damageSource = livingEntity.damageSources().source(DamageTypes.GENERIC_KILL, livingEntity2);
        if (livingEntity.isInWaterOrRain() && livingEntity.level() != null) {
            livingEntity.hurt(damageSource, 9 + bonusDamage);
        } else {
            livingEntity.hurt(damageSource, 9);
        }
    }

    public Projectile asProjectile(Level level, Position position, ItemStack itemStack, Direction direction) {
        ThrownAnchor thrownAnchor = new ThrownAnchor(level, position.x(), position.y(), position.z(), itemStack.copyWithCount(1));
        thrownAnchor.pickup = AbstractArrow.Pickup.ALLOWED;
        if (thrownAnchor.getOwner() != null && thrownAnchor.getOwner() instanceof Player player) {
            thrownAnchor.setReturnSlot(player.getInventory().getSelectedSlot());
        }
        return thrownAnchor;
    }
}
