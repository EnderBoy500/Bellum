package net.enderboy500.bellum.client.entity.state;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

@Environment(EnvType.CLIENT)
public class ThrownAnchorRenderState extends EntityRenderState {
    public float xRot;
    public float yRot;
    public boolean isFoil;
    public Level level;
    public BlockPos blockPos;
    public final ItemStackRenderState itemStackRenderState = new ItemStackRenderState();
    public Entity owner;
    public Vec3 ringOffset = Vec3.ZERO;
    public Vec3 ownerOffset;
    public Identifier chain;
}
