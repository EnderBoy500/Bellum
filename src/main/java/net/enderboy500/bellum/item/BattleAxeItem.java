package net.enderboy500.bellum.item;

import io.github.ciph3rj.cipherlib.item.component.CipherLibComponents;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import org.spongepowered.asm.mixin.Overwrite;

import java.util.Optional;

public class BattleAxeItem extends AxeItem {
    public BattleAxeItem(ToolMaterial toolMaterial, float f, float g, Properties properties) {
        super(toolMaterial, f, g, properties.component(CipherLibComponents.HAS_SWEEP_ATTACK, true).component(CipherLibComponents.HOLD_WITH_BOTH_HANDS, true));
    }
}
