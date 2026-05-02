package net.enderboy500.bellum.client.entity.state;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;

@Environment(EnvType.CLIENT)
public class TrainingDummyRenderState extends HumanoidRenderState {
    public float rotation;
    public float damageAmount;
    public int damageDuration;
}
