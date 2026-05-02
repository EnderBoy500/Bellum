package net.enderboy500.bellum.client.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.enderboy500.bellum.Bellum;
import net.enderboy500.bellum.client.entity.BellumEntityModelLayers;
import net.enderboy500.bellum.client.entity.model.TrainingDummy;
import net.enderboy500.bellum.entity.TrainingDummyEntity;
import net.enderboy500.bellum.client.entity.state.TrainingDummyRenderState;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.Font;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.object.armorstand.ArmorStandArmorModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

@Environment(EnvType.CLIENT)
public class TrainingDummyRenderer extends LivingEntityRenderer<TrainingDummyEntity, TrainingDummyRenderState, TrainingDummy> {
    private final TrainingDummy model;
    private final Font textRenderer;
    private float stored_damage = 0;

    public TrainingDummyRenderer(EntityRendererProvider.Context context) {
        super(context, new TrainingDummy(context.bakeLayer(BellumEntityModelLayers.TRAINING_DUMMY)), 0.3f);
        textRenderer = context.getFont();
        this.model = new TrainingDummy(context.bakeLayer(BellumEntityModelLayers.TRAINING_DUMMY));
    }

    @Override
    public void extractRenderState(TrainingDummyEntity livingEntity, TrainingDummyRenderState livingEntityRenderState, float f) {
        super.extractRenderState(livingEntity, livingEntityRenderState, f);
        livingEntityRenderState.rotation =  livingEntity.getRotation();
        livingEntityRenderState.damageAmount = livingEntity.stored_damage;
        livingEntityRenderState.damageDuration = livingEntity.hurtDuration;
    }

    @Override
    protected boolean shouldShowName(TrainingDummyEntity livingEntity, double d) {
        return false;
    }

    @Override
    public void submit(TrainingDummyRenderState livingEntityRenderState, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState cameraRenderState) {
        if (livingEntityRenderState.damageDuration > 0) Bellum.LOGGER.info(String.valueOf(livingEntityRenderState.damageAmount));
        if (livingEntityRenderState.damageDuration * 2 > 0) {
            FormattedCharSequence damageSequence = FormattedCharSequence.forward(String.valueOf(stored_damage), Style.EMPTY.withColor(TextColor.fromRgb(0)));
            poseStack.pushPose();
            poseStack.scale(0.1f, 0.1f, 0.1f);
            poseStack.mulPose(Axis.XP.rotationDegrees(livingEntityRenderState.rotation));
            submitNodeCollector.submitText(poseStack, 0, 0, damageSequence, false, Font.DisplayMode.NORMAL, 15, -1, -1, -1);
            poseStack.popPose();
        }
        super.submit(livingEntityRenderState, poseStack, submitNodeCollector, cameraRenderState);
    }

    @Override
    public @NotNull Identifier getTextureLocation(TrainingDummyRenderState livingEntityRenderState) {
        return Identifier.withDefaultNamespace("textures/entity/armadillo");
    }

    @Override
    public TrainingDummyRenderState createRenderState() {
        return new TrainingDummyRenderState();
    }
}
