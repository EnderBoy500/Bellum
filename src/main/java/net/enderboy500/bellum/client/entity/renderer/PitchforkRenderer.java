package net.enderboy500.bellum.client.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.enderboy500.bellum.Bellum;
import net.enderboy500.bellum.client.entity.BellumEntityModelLayers;
import net.enderboy500.bellum.projectile.ThrownHellfork;
import net.enderboy500.bellum.projectile.ThrownPitchfork;
import net.minecraft.client.model.object.projectile.TridentModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.entity.state.ThrownTridentRenderState;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Unit;

import java.util.List;

public class PitchforkRenderer extends EntityRenderer<ThrownPitchfork, ThrownTridentRenderState> {
    public static final Identifier TRIDENT_LOCATION = Bellum.id("textures/entity/pitchfork.png");
    private final TridentModel model;

    public PitchforkRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.model = new TridentModel(context.bakeLayer(BellumEntityModelLayers.PITCHFORK));
    }

    public void submit(ThrownTridentRenderState thrownTridentRenderState, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState cameraRenderState) {
        poseStack.pushPose();
        poseStack.mulPose(Axis.YP.rotationDegrees(thrownTridentRenderState.yRot - 90.0F));
        poseStack.mulPose(Axis.ZP.rotationDegrees(thrownTridentRenderState.xRot + 90.0F));
        List<RenderType> list = ItemRenderer.getFoilRenderTypes(this.model.renderType(TRIDENT_LOCATION), false, thrownTridentRenderState.isFoil);

        for (int i = 0; i < list.size(); ++i) {
            submitNodeCollector.order(i).submitModel(this.model, Unit.INSTANCE, poseStack, (RenderType) list.get(i), thrownTridentRenderState.lightCoords, OverlayTexture.NO_OVERLAY, -1, (TextureAtlasSprite) null, thrownTridentRenderState.outlineColor, (ModelFeatureRenderer.CrumblingOverlay) null);
        }

        poseStack.popPose();
        super.submit(thrownTridentRenderState, poseStack, submitNodeCollector, cameraRenderState);
    }

    public ThrownTridentRenderState createRenderState() {
        return new ThrownTridentRenderState();
    }

    public void extractRenderState(ThrownPitchfork thrownTrident, ThrownTridentRenderState thrownTridentRenderState, float f) {
        super.extractRenderState(thrownTrident, thrownTridentRenderState, f);
        thrownTridentRenderState.yRot = thrownTrident.getYRot(f);
        thrownTridentRenderState.xRot = thrownTrident.getXRot(f);
        thrownTridentRenderState.isFoil = thrownTrident.isFoil();
    }
}