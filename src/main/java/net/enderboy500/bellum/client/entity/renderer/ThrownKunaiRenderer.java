package net.enderboy500.bellum.client.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.enderboy500.bellum.client.entity.state.ThrownKunaiRenderState;
import net.enderboy500.bellum.content.BellumItems;
import net.enderboy500.bellum.projectile.ThrownKunaiEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;

@Environment(EnvType.CLIENT)
public class ThrownKunaiRenderer extends EntityRenderer<ThrownKunaiEntity, ThrownKunaiRenderState> {
    private final ItemModelResolver itemModelResolver;

    public ThrownKunaiRenderer(EntityRendererProvider.Context context) {
        super(context);
        itemModelResolver = context.getItemModelResolver();
    }

    @Override
    public void submit(ThrownKunaiRenderState thrownKunaiRenderState, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState cameraRenderState) {
        poseStack.pushPose();
        poseStack.mulPose(Axis.YP.rotationDegrees(thrownKunaiRenderState.yRot - 90));
        poseStack.mulPose(Axis.ZP.rotationDegrees(thrownKunaiRenderState.xRot + 225));
        thrownKunaiRenderState.itemStackRenderState.submit(poseStack, submitNodeCollector, thrownKunaiRenderState.lightCoords, OverlayTexture.NO_OVERLAY, thrownKunaiRenderState.outlineColor);
        poseStack.popPose();
        super.submit(thrownKunaiRenderState, poseStack, submitNodeCollector, cameraRenderState);
    }

    @Override
    public void extractRenderState(ThrownKunaiEntity thrownKunai, ThrownKunaiRenderState thrownKunaiRenderState, float f) {
        super.extractRenderState(thrownKunai, thrownKunaiRenderState, f);
        thrownKunaiRenderState.yRot = thrownKunai.getYRot(f);
        thrownKunaiRenderState.xRot = thrownKunai.getXRot(f);
        thrownKunaiRenderState.isFoil = thrownKunai.isFoil();
        thrownKunaiRenderState.level = thrownKunai.level();
        thrownKunaiRenderState.blockPos = thrownKunai.blockPosition();
        this.itemModelResolver.updateForNonLiving(thrownKunaiRenderState.itemStackRenderState, BellumItems.KUNAI.getDefaultInstance(), ItemDisplayContext.FIXED, thrownKunai);
    }

    @Override
    public ThrownKunaiRenderState createRenderState() {
        return new ThrownKunaiRenderState();
    }

    public int getLightLevel(Level world, BlockPos pos) {
        int blockLight = world.getBrightness(LightLayer.BLOCK, pos);
        int skyLight = world.getBrightness(LightLayer.SKY, pos);
        return LightTexture.pack(blockLight, skyLight);
    }
}
