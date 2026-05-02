package net.enderboy500.bellum.client.entity.state;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

@Environment(EnvType.CLIENT)
public class ThrownKunaiRenderState extends EntityRenderState {
    public float xRot;
    public float yRot;
    public boolean isFoil;
    public Level level;
    public BlockPos blockPos;
    public final ItemStackRenderState itemStackRenderState = new ItemStackRenderState();
}
