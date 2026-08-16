package net.enderboy500.bellum.mixin;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.StringUtil;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.AnvilBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(AnvilMenu.class)
public abstract class AnvilMenuMixin extends ItemCombinerMenu {

    @Shadow
    private int repairItemCountCost;

    @Shadow
    @Final
    private DataSlot cost;

    @Shadow
    private boolean onlyRenaming;

    @Shadow
    private @Nullable String itemName;

    public AnvilMenuMixin(@Nullable MenuType<?> menuType, int i, Inventory inventory, ContainerLevelAccess containerLevelAccess, ItemCombinerMenuSlotDefinition itemCombinerMenuSlotDefinition) {
        super(menuType, i, inventory, containerLevelAccess, itemCombinerMenuSlotDefinition);
    }

    /**
     * @author
     * @reason
     */
    @Overwrite
    public void onTake(Player player, ItemStack itemStack) {
        if (!player.hasInfiniteMaterials()) {
            player.giveExperienceLevels(-this.cost.get());
        }

        if (this.repairItemCountCost > 0) {
            ItemStack itemStack2 = this.inputSlots.getItem(1);
            if (!itemStack2.isEmpty() && itemStack2.getCount() > this.repairItemCountCost) {
                itemStack2.shrink(this.repairItemCountCost);
                this.inputSlots.setItem(1, itemStack2);
            } else {
                itemStack2.shrink(this.repairItemCountCost);
                this.inputSlots.setItem(1, itemStack2);
            }
        } else if (!this.onlyRenaming) {
            ItemStack itemStack2 = this.inputSlots.getItem(1);
            itemStack2.shrink(1);
            this.inputSlots.setItem(1, itemStack2);
        }

        this.cost.set(0);
        if (player instanceof ServerPlayer serverPlayer) {
            if (!StringUtil.isBlank(this.itemName) && !this.inputSlots.getItem(0).getHoverName().getString().equals(this.itemName)) {
                serverPlayer.getTextFilter().processStreamMessage(this.itemName);
            }
        }

        this.inputSlots.setItem(0, ItemStack.EMPTY);
        this.access.execute((level, blockPos) -> {
            BlockState blockState = level.getBlockState(blockPos);
            if (!player.hasInfiniteMaterials() && blockState.is(BlockTags.ANVIL) && player.getRandom().nextFloat() < 0.12F) {
                BlockState blockState2 = AnvilBlock.damage(blockState);
                if (blockState2 == null) {
                    level.removeBlock(blockPos, false);
                    level.levelEvent(1029, blockPos, 0);
                } else {
                    level.setBlock(blockPos, blockState2, 2);
                    level.levelEvent(1030, blockPos, 0);
                }
            } else {
                level.levelEvent(1030, blockPos, 0);
            }

        });
    }
}
