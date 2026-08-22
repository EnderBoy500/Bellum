package net.enderboy500.bellum.client.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.serialization.MapCodec;
import net.enderboy500.bellum.client.entity.BellumEntityModelLayers;
import net.enderboy500.bellum.client.entity.model.HellforkModel;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.special.NoDataSpecialModelRenderer;
import net.minecraft.client.renderer.special.SpecialModelRenderer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.world.item.ItemDisplayContext;
import org.joml.Vector3fc;

import java.util.function.Consumer;

public class HellforkSpecialRenderer  implements NoDataSpecialModelRenderer {
    private final HellforkModel model;

    public HellforkSpecialRenderer(HellforkModel tridentModel) {
        this.model = tridentModel;
    }

    public void submit(ItemDisplayContext itemDisplayContext, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int i, int j, boolean bl, int k) {
        poseStack.pushPose();
        poseStack.scale(1.0F, -1.0F, -1.0F);
        submitNodeCollector.submitModelPart(this.model.root(), poseStack, this.model.renderType(HellforkModel.TEXTURE), i, j, (TextureAtlasSprite)null, false, bl, -1, (ModelFeatureRenderer.CrumblingOverlay)null, k);
        poseStack.popPose();
    }

    public void getExtents(Consumer<Vector3fc> consumer) {
        PoseStack poseStack = new PoseStack();
        poseStack.scale(1.0F, -1.0F, -1.0F);
        this.model.root().getExtentsForGui(poseStack, consumer);
    }

    @Environment(EnvType.CLIENT)
    public static record Unbaked() implements SpecialModelRenderer.Unbaked {
        public static final MapCodec<HellforkSpecialRenderer.Unbaked> MAP_CODEC = MapCodec.unit(new HellforkSpecialRenderer.Unbaked());

        public Unbaked() {
        }

        public MapCodec<HellforkSpecialRenderer.Unbaked> type() {
            return MAP_CODEC;
        }

        public SpecialModelRenderer<?> bake(SpecialModelRenderer.BakingContext bakingContext) {
            return new HellforkSpecialRenderer(new HellforkModel(bakingContext.entityModelSet().bakeLayer(BellumEntityModelLayers.HELLFORK)));
        }
    }
}
