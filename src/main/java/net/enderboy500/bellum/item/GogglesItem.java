package net.enderboy500.bellum.item;

import net.enderboy500.bellum.util.BellumDataComponents;
import net.enderboy500.bellum.util.BellumTags;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.equipment.Equippable;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.block.state.BlockState;

import java.util.function.Consumer;

public class GogglesItem extends Item {
    public GogglesItem(Properties properties) {
        super(properties.component(DataComponents.EQUIPPABLE, Equippable.builder(EquipmentSlot.HEAD).setSwappable(true).build()));
    }

    @Override
    public boolean overrideOtherStackedOnMe(ItemStack itemStack, ItemStack itemStack2, Slot slot, ClickAction clickAction, Player player, SlotAccess slotAccess) {
        if (itemStack2.is(BellumTags.LENS) && clickAction == ClickAction.SECONDARY && !itemStack.has(BellumDataComponents.LENS)) {
            ItemStack stack = itemStack2.copyWithCount(1);
            itemStack.set(BellumDataComponents.LENS, stack);
            itemStack2.shrink(1);
            return true;
        } else if (itemStack2.is(Items.AIR) && clickAction == ClickAction.SECONDARY) {
            if (itemStack.get(BellumDataComponents.LENS) != null) {
                player.addItem(itemStack.get(BellumDataComponents.LENS).copy());
                itemStack.remove(BellumDataComponents.LENS);
                return true;
            } else return false;
        }
        else return false;
    }

    @Override
    public void appendHoverText(ItemStack itemStack, TooltipContext tooltipContext, TooltipDisplay tooltipDisplay, Consumer<Component> consumer, TooltipFlag tooltipFlag) {
        if (itemStack.get(BellumDataComponents.LENS) != null) consumer.accept(Component.translatable("tooltip.bellum.goggles.lens").append(Component.translatable(itemStack.get(BellumDataComponents.LENS).getItem().getDescriptionId())).withStyle(ChatFormatting.GRAY));
        super.appendHoverText(itemStack, tooltipContext, tooltipDisplay, consumer, tooltipFlag);
    }

    public static boolean isDarkBlock(Minecraft client, BlockPos blockPos) {
        if (client.level == null) return false;
        int blockLight = client.level.getBrightness(LightLayer.BLOCK, blockPos.above());

        if (blockLight > 0) return false;

        BlockState thisBlockState = client.level.getBlockState(blockPos);
        BlockState aboveThisBlockState = client.level.getBlockState(blockPos.above());

        boolean hasASolidTop = thisBlockState.isRedstoneConductor(client.level, blockPos);
        boolean isPassable = aboveThisBlockState.getCollisionShape(client.level, blockPos.above()).isEmpty();

        return isPassable && hasASolidTop;
    }
}
