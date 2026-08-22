package net.enderboy500.bellum.item;

import io.github.ciph3rj.cipherlib.item.component.CipherLibComponents;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ToolMaterial;

public class BattleAxeItem extends AxeItem {
    public BattleAxeItem(ToolMaterial toolMaterial, float f, float g, Properties properties) {
        super(toolMaterial, f, g, properties.component(CipherLibComponents.HAS_SWEEP_ATTACK, true).component(CipherLibComponents.HOLD_WITH_BOTH_HANDS, true));
    }
}
