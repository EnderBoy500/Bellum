package net.enderboy500.bellum.item;

import io.github.ciph3rj.cipherlib.item.component.CipherLibComponents;
import io.github.ciph3rj.cipherlib.util.ItemUtils;
import net.enderboy500.bellum.content.BellumMobEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
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
}
