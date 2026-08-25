package net.enderboy500.bellum.projectile;

import io.github.ciph3rj.cipherlib.item.component.CipherLibComponents;
import io.github.ciph3rj.cipherlib.util.ItemUtils;
import net.enderboy500.bellum.Bellum;
import net.enderboy500.bellum.content.BellumDamageTypes;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.Position;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.item.ProjectileItem;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.component.Tool;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class HellforkItem extends Item implements ProjectileItem {
    public HellforkItem(Item.Properties properties) {
        super(properties.fireResistant().component(CipherLibComponents.CUSTOM_RIPTIDE_TEXTURE, Bellum.id("textures/entity/hellfork_riptide.png")));
    }

    public static ItemAttributeModifiers createAttributes() {
        return ItemAttributeModifiers.builder().add(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_ID, (double)9F, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND).add(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_ID, (double)-2.9F, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND).build();
    }

    public static Tool createToolProperties() {
        return new Tool(List.of(), 1.0F, 2, false);
    }

    public ItemUseAnimation getUseAnimation(ItemStack itemStack) {
        return ItemUseAnimation.TRIDENT;
    }

    public int getUseDuration(ItemStack itemStack, LivingEntity livingEntity) {
        return 72000;
    }

    public boolean releaseUsing(ItemStack itemStack, Level level, LivingEntity livingEntity, int i) {
        if (livingEntity instanceof Player player) {
            int j = this.getUseDuration(itemStack, livingEntity) - i;
            boolean hasAttuning = ItemUtils.hasEnchantment(itemStack, "attuning");
            if (j >= 10) {
                Holder<SoundEvent> holder = EnchantmentHelper.pickHighestLevel(itemStack, EnchantmentEffectComponents.TRIDENT_SOUND).orElse(SoundEvents.TRIDENT_THROW);
                if (hasAttuning && (player.getHealth() > 0 || player.experienceLevel > 0)) {
                    if (player.experienceLevel > 0) player.experienceLevel = player.experienceLevel - 1;
                    else player.hurt(level.damageSources().source(BellumDamageTypes.DRAINED_SOUL), 1.5f);
                    int f = 2;
                    float g = player.getYRot();
                    float h = player.getXRot();
                    float k = -Mth.sin((g * ((float) Math.PI / 180F))) * Mth.cos((h * ((float) Math.PI / 180F)));
                    float l = -Mth.sin((h * ((float) Math.PI / 180F)));
                    float m = Mth.cos((g * ((float) Math.PI / 180F))) * Mth.cos((h * ((float) Math.PI / 180F)));
                    float n = Mth.sqrt(k * k + l * l + m * m);
                    k *= f / n;
                    l *= f / n;
                    m *= f / n;
                    player.push(k, l, m);
                    player.startAutoSpinAttack(20, 8.0F, itemStack);
                    if (player.onGround()) {
                        player.move(MoverType.SELF, new Vec3(0.0F, 1.1999999F, 0.0F));
                    }
                    level.playSound(null, player, holder.value(), SoundSource.PLAYERS, 1.0F, 1.0F);
                    return true;
                }
            }
            if (j < 10) {
                return false;
            } else {
                float f = EnchantmentHelper.getTridentSpinAttackStrength(itemStack, player);
                if (f > 0.0F && !player.isOnFire()) {
                    return false;
                } else if (itemStack.nextDamageWillBreak()) {
                    return false;
                } else {
                    Holder<SoundEvent> holder = EnchantmentHelper.pickHighestLevel(itemStack, EnchantmentEffectComponents.TRIDENT_SOUND).orElse(SoundEvents.TRIDENT_THROW);
                    player.awardStat(Stats.ITEM_USED.get(this));
                    if (level instanceof ServerLevel) {
                        ServerLevel serverLevel = (ServerLevel)level;
                        itemStack.hurtWithoutBreaking(1, player);
                        if (f == 0.0F) {
                            ItemStack itemStack2 = itemStack.consumeAndReturn(1, player);
                            ThrownHellfork thrownHellfork = Projectile.spawnProjectileFromRotation(ThrownHellfork::new, serverLevel, itemStack2, player, 0.0F, 2.5F, 1.0F);
                            if (player.hasInfiniteMaterials()) {
                                thrownHellfork.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
                            }

                            level.playSound(null, thrownHellfork, holder.value(), SoundSource.PLAYERS, 1.0F, 1.0F);
                            return true;
                        }
                    }

                    if (f > 0.0F) {
                        float g = player.getYRot();
                        float h = player.getXRot();
                        float k = -Mth.sin((g * ((float)Math.PI / 180F))) * Mth.cos((double)(h * ((float)Math.PI / 180F)));
                        float l = -Mth.sin((h * ((float)Math.PI / 180F)));
                        float m = Mth.cos((g * ((float)Math.PI / 180F))) * Mth.cos((double)(h * ((float)Math.PI / 180F)));
                        float n = Mth.sqrt(k * k + l * l + m * m);
                        k *= f / n;
                        l *= f / n;
                        m *= f / n;
                        player.push(k, l, m);
                        player.startAutoSpinAttack(20, 8.0F, itemStack);
                        if (player.onGround()) {
                            player.move(MoverType.SELF, new Vec3(0.0F, 1.1999999F, 0.0F));
                        }

                        level.playSound(null, player, holder.value(), SoundSource.PLAYERS, 1.0F, 1.0F);
                        return true;
                    } else {
                        return false;
                    }
                }
            }
        } else {
            return false;
        }
    }

    public InteractionResult use(Level level, Player player, InteractionHand interactionHand) {
        ItemStack itemStack = player.getItemInHand(interactionHand);
        if (itemStack.nextDamageWillBreak()) {
            return InteractionResult.FAIL;
        } else if (EnchantmentHelper.getTridentSpinAttackStrength(itemStack, player) > 0.0F && !player.isOnFire()) {
            return InteractionResult.FAIL;
        } else {
            player.startUsingItem(interactionHand);
            return InteractionResult.CONSUME;
        }
    }

    @Override
    public void hurtEnemy(ItemStack itemStack, LivingEntity livingEntity, LivingEntity livingEntity2) {
        livingEntity.setRemainingFireTicks(100);
        super.postHurtEnemy(itemStack, livingEntity, livingEntity2);
    }

    public Projectile asProjectile(Level level, Position position, ItemStack itemStack, Direction direction) {
        ThrownHellfork thrownHellfork = new ThrownHellfork(level, position.x(), position.y(), position.z(), itemStack.copyWithCount(1));
        thrownHellfork.pickup = AbstractArrow.Pickup.ALLOWED;
        return thrownHellfork;
    }
}
