package net.enderboy500.bellum.item;

import net.enderboy500.bellum.util.BellumTags;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.pattern.BlockInWorld;
import net.minecraft.world.level.gameevent.GameEvent;

public class ScribeItem extends Item {
    public ScribeItem(ToolMaterial material, Properties properties) {
        super(properties.tool(material, BellumTags.SCRIBE_MINEABLE,0,0,0).repairable(Items.AMETHYST_SHARD).attributes(ItemAttributeModifiers.builder().build()));
    }

    @Override
    public boolean mineBlock(ItemStack itemStack, Level level, BlockState blockState, BlockPos blockPos, LivingEntity livingEntity) {
        if (!level.isClientSide() && blockState.getDestroySpeed(level, blockPos) != 0F) {
            itemStack.hurtAndBreak(1, livingEntity, livingEntity.getUsedItemHand());
        }
        return true;
    }

    @Override
    public float getDestroySpeed(ItemStack stack, BlockState state) {
        if (state.is(BellumTags.SCRIBE_MINEABLE)) return 32F;
        else if (isCorrectToolForDrops(stack, state)) return 0.3F;
        return super.getDestroySpeed(stack, state);
    }

    public boolean dropsLikeSilktouch(ItemStack stack, BlockState state) {
        return isCorrectToolForDrops(stack, state) && !shouldNotSilktouch(stack, state);
    }

    private boolean shouldNotSilktouch(ItemStack stack, BlockState state) {
        return state.is(BellumTags.SILKTOUCH_WITH_SCRIBE_BLACKLIST);
    }

}
