package net.enderboy500.bellum.client.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.enderboy500.bellum.Bellum;
import net.enderboy500.bellum.client.entity.state.ThrownAnchorRenderState;
import net.enderboy500.bellum.client.entity.state.ThrownKunaiRenderState;
import net.enderboy500.bellum.projectile.AnchorItem;
import net.enderboy500.bellum.projectile.ThrownAnchor;
import net.enderboy500.bellum.projectile.ThrownKunaiEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.FishingRodItem;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;

@Environment(EnvType.CLIENT)
public class ThrownAnchorRenderer extends EntityRenderer<ThrownAnchor, ThrownAnchorRenderState> {
    private final ItemModelResolver itemModelResolver;
    public static final Identifier CHAIN = Bellum.id("textures/entity/anchor_chain.png");
    public ThrownAnchorRenderer(EntityRendererProvider.Context context) {
        super(context);
        itemModelResolver = context.getItemModelResolver();
    }

    @Override
    public void submit(ThrownAnchorRenderState thrownAnchorRenderState, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState cameraRenderState) {
        poseStack.pushPose();
        poseStack.scale(0.5f,0.5f,0.5f);
        poseStack.translate(0,0.6,0);
        poseStack.mulPose(Axis.YP.rotationDegrees(thrownAnchorRenderState.yRot - 90));
        poseStack.mulPose(Axis.ZP.rotationDegrees(thrownAnchorRenderState.xRot - 45));
        thrownAnchorRenderState.itemStackRenderState.submit(poseStack, submitNodeCollector, thrownAnchorRenderState.lightCoords, OverlayTexture.NO_OVERLAY, thrownAnchorRenderState.outlineColor);
        poseStack.popPose();

        if (thrownAnchorRenderState.ownerOffset != null) {
            Vec3 ring = thrownAnchorRenderState.ringOffset;
            Vec3 owner = thrownAnchorRenderState.ownerOffset;
            float length = (float) ring.distanceTo(owner);
            Vec3 side = owner.subtract(ring).normalize().multiply(0.25, 0.0, 0.25)
                    .yRot(Mth.HALF_PI);
            Vec3 first = ring.add(side);
            Vec3 second = owner.add(side);
            Vec3 third = owner.subtract(side);
            Vec3 fourth = ring.subtract(side);
            submitNodeCollector.submitCustomGeometry(poseStack, RenderTypes.entitySmoothCutout(thrownAnchorRenderState.chain), (entry, vertices) -> {
                vertex(vertices, entry, first, 0.0F, 0.0F, thrownAnchorRenderState.lightCoords);
                vertex(vertices, entry, second, 0.0F, length / 8.0F, thrownAnchorRenderState.lightCoords);
                vertex(vertices, entry, third, 1.0F, length / 8.0F, thrownAnchorRenderState.lightCoords);
                vertex(vertices, entry, fourth, 1.0F, 0.0F, thrownAnchorRenderState.lightCoords);
            });
        }

        super.submit(thrownAnchorRenderState, poseStack, submitNodeCollector, cameraRenderState);
    }

    @Override
    public ThrownAnchorRenderState createRenderState() {
        return new ThrownAnchorRenderState();
    }

    private static void vertex(VertexConsumer vertices, PoseStack.Pose entry,
                               Vec3 position, float u, float v, int light) {
        vertices.addVertex(entry, (float) position.x, (float) position.y, (float) position.z)
                .setColor(255, 255, 255, 255)
                .setUv(u, v)
                .setOverlay(OverlayTexture.NO_OVERLAY)
                .setLight(light)
                .setNormal(entry, 0.0F, 1.0F, 0.0F);
    }

    @Override
    public void extractRenderState(ThrownAnchor thrownAnchor, ThrownAnchorRenderState thrownAnchorRenderState, float f) {
        super.extractRenderState(thrownAnchor, thrownAnchorRenderState, f);
        thrownAnchorRenderState.yRot = thrownAnchor.getYRot(f);
        thrownAnchorRenderState.xRot = thrownAnchor.getXRot(f);
        thrownAnchorRenderState.isFoil = thrownAnchor.isFoil();
        thrownAnchorRenderState.level = thrownAnchor.level();
        thrownAnchorRenderState.blockPos = thrownAnchor.blockPosition();
        thrownAnchorRenderState.chain = thrownAnchor.getChain();
        this.itemModelResolver.updateForNonLiving(thrownAnchorRenderState.itemStackRenderState, thrownAnchor.getEntityData().get(ThrownAnchor.getAnchor()), ItemDisplayContext.FIXED, thrownAnchor);
        thrownAnchorRenderState.ringOffset = new Vec3(0.0, 0.0, 0.0)
                .zRot(thrownAnchorRenderState.xRot * Mth.RAD_TO_DEG)
                .yRot((thrownAnchorRenderState.yRot + 90.0F) * Mth.RAD_TO_DEG)
                .add(0.0, thrownAnchor.getBbHeight() / 2.0F, 0);
        if (thrownAnchor.getOwner() != null) {
            thrownAnchorRenderState.owner = thrownAnchor.getOwner();
            if (thrownAnchor.getOwner() instanceof Player player) {
                Vec3 ownerPosition = player.getRopeHoldPosition(f);
                thrownAnchorRenderState.ownerOffset = ownerPosition.subtract(thrownAnchor.getPosition(f));
            } else {
                thrownAnchorRenderState.ownerOffset = null;
            }
        }
    }
}
