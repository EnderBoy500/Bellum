package net.enderboy500.bellum.item;

import io.github.ciph3rj.cipherlib.item.component.CipherLibComponents;
import io.github.ciph3rj.cipherlib.util.ItemUtils;
import net.enderboy500.bellum.content.BellumMobEffects;
import net.enderboy500.bellum.util.BellumTags;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Random;

public class SickleItem extends Item {
    public SickleItem(ToolMaterial material, float attackDamage, float attackSpeed, Properties properties) {
        super(material.applyToolProperties(properties, BlockTags.SWORD_EFFICIENT, attackDamage, attackSpeed, 0));
    }

    @Override
    public void hurtEnemy(ItemStack itemStack, LivingEntity livingEntity, LivingEntity livingEntity2) {
        if (!ItemUtils.hasEnchantment(itemStack, "reaping")) {
            Random random = new Random();
            int effectChance = random.nextInt(8) + 1;
            if (effectChance == 4) {
                livingEntity.addEffect(new MobEffectInstance(BellumMobEffects.BLEEDING, 400, 0));
            }
        }
        super.hurtEnemy(itemStack, livingEntity, livingEntity2);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level world = context.getLevel();
        BlockPos pos = context.getClickedPos();
        BlockState state = world.getBlockState(pos);
        ItemStack stack = context.getItemInHand();
        Player player = context.getPlayer();
        if (state.getBlock() instanceof CropBlock cropBlock && cropBlock.isMaxAge(state)) {
            world.destroyBlock(pos, true);
            world.setBlockAndUpdate(pos, state.getBlock().defaultBlockState());
            stack.hurtWithoutBreaking(1, player);
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.FAIL;
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack itemStack, Player player, LivingEntity livingEntity, InteractionHand hand) {
        if (hand == InteractionHand.MAIN_HAND) return InteractionResult.PASS;
        if (!player.getItemInHand(InteractionHand.MAIN_HAND).is(BellumTags.SICKLES))  return InteractionResult.PASS;
        if (player.getAttackStrengthScale(0)<0.5) return InteractionResult.PASS;
        if (player.getCooldowns().getCooldownPercent(player.getItemInHand(hand), 0)>0) return InteractionResult.PASS;
        player.getCooldowns().addCooldown(itemStack, 12);
        if (player.attackStrengthTicker>5) player.attackStrengthTicker = 5;
        if (player.level().isClientSide()) return InteractionResult.SUCCESS;

        int tt = player.attackStrengthTicker;
        swapHands(player);
        player.detectEquipmentUpdates();

        player.attackStrengthTicker = 1000;
        player.attack(livingEntity);
        swapHands(player);
        player.attackStrengthTicker = tt;
        return InteractionResult.SUCCESS;
    }

    private static void swapHands(Player user) {
        ItemStack itemStack = user.getItemInHand(InteractionHand.OFF_HAND);
        user.setItemInHand(InteractionHand.OFF_HAND, user.getItemInHand(InteractionHand.MAIN_HAND));
        user.setItemInHand(InteractionHand.MAIN_HAND, itemStack);
    }

}
