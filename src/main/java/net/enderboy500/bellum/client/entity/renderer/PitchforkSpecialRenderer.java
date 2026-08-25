package net.enderboy500.bellum.client.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.serialization.MapCodec;
import net.enderboy500.bellum.Bellum;
import net.enderboy500.bellum.client.entity.BellumEntityModelLayers;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.object.projectile.TridentModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.special.NoDataSpecialModelRenderer;
import net.minecraft.client.renderer.special.SpecialModelRenderer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.world.item.ItemDisplayContext;
import org.joml.Vector3fc;

import java.util.function.Consumer;

public class PitchforkSpecialRenderer implements NoDataSpecialModelRenderer {
    private final TridentModel model;

    public PitchforkSpecialRenderer(TridentModel tridentModel) {
        this.model = tridentModel;
    }

    public void submit(ItemDisplayContext itemDisplayContext, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int i, int j, boolean bl, int k) {
        poseStack.pushPose();
        poseStack.scale(1.0F, -1.0F, -1.0F);
        submitNodeCollector.submitModelPart(this.model.root(), poseStack, this.model.renderType(Bellum.PITCHFORK_TEXTURE), i, j, (TextureAtlasSprite)null, false, bl, -1, (ModelFeatureRenderer.CrumblingOverlay)null, k);
        poseStack.popPose();
    }

    public void getExtents(Consumer<Vector3fc> consumer) {
        PoseStack poseStack = new PoseStack();
        poseStack.scale(1.0F, -1.0F, -1.0F);
        this.model.root().getExtentsForGui(poseStack, consumer);
    }

    @Environment(EnvType.CLIENT)
    public static record Unbaked() implements SpecialModelRenderer.Unbaked {
        public static final MapCodec<PitchforkSpecialRenderer.Unbaked> MAP_CODEC = MapCodec.unit(new PitchforkSpecialRenderer.Unbaked());

        public Unbaked() {
        }

        public MapCodec<PitchforkSpecialRenderer.Unbaked> type() {
            return MAP_CODEC;
        }

        public SpecialModelRenderer<?> bake(BakingContext bakingContext) {
            return new PitchforkSpecialRenderer(new TridentModel(bakingContext.entityModelSet().bakeLayer(BellumEntityModelLayers.PITCHFORK)));
        }
    }
}
