package net.enderboy500.bellum.item;

import net.enderboy500.bellum.Bellum;
import net.enderboy500.bellum.content.BellumMobEffects;
import net.enderboy500.enderlib.item.ToolFunction;
import net.enderboy500.enderlib.util.ItemUtils;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.context.UseOnContext;

import java.util.Random;

public class SickleItem extends Item implements ToolFunction {
    public SickleItem(ToolMaterial material, float attackDamage, float attackSpeed, Properties properties) {
        super(material.applyToolProperties(properties, BlockTags.SWORD_EFFICIENT, attackDamage, attackSpeed, 0));
    }

    @Override
    public void hurtEnemy(ItemStack itemStack, LivingEntity livingEntity, LivingEntity livingEntity2) {
        if (!ItemUtils.hasEnchantment(this.getDefaultInstance(), "reaping")) {
            Random random = new Random();
            int effectChance = random.nextInt(8) + 1;
            if (effectChance == 4) {
                livingEntity.addEffect(new MobEffectInstance(BellumMobEffects.BLEEDING, 400, 0));
            }
        }
        super.hurtEnemy(itemStack, livingEntity, livingEntity2);
    }

    @Override
    public InteractionResult useOn(UseOnContext useOnContext) {
        if (checkCrop(useOnContext)) {
            harvestFunction(useOnContext);
            return InteractionResult.SUCCESS;
        }
        return super.useOn(useOnContext);
    }
}
