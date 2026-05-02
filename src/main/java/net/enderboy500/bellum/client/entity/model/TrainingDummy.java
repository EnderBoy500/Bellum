package net.enderboy500.bellum.client.entity.model;

import net.enderboy500.bellum.client.entity.state.TrainingDummyRenderState;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.util.Unit;

public class TrainingDummy extends EntityModel<TrainingDummyRenderState> {
	private final ModelPart training_dummy;
	private final ModelPart body;

	public TrainingDummy(ModelPart root) {
        super(root, RenderTypes::entityCutout);
        this.training_dummy = root.getChild("training_dummy");
		this.body = this.training_dummy.getChild("body");
	}

	public static LayerDefinition getTexturedModelData() {
		MeshDefinition modelData = new MeshDefinition();
		PartDefinition modelPartData = modelData.getRoot();
		PartDefinition training_dummy = modelPartData.addOrReplaceChild("training_dummy", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition stand = training_dummy.addOrReplaceChild("stand", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -14.0F, -1.0F, 2.0F, 13.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(-20, -10).addBox(-6.0F, -1.0F, -6.0F, 12.0F, 1.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition body = training_dummy.addOrReplaceChild("body", CubeListBuilder.create().texOffs(-8, -2).addBox(-4.0F, -12.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -14.0F, 0.0F));

		PartDefinition right_arm = body.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(-12, -2).addBox(-12.0F, -2.0F, -2.0F, 12.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, -9.0F, 0.0F));

		PartDefinition left_arm = body.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(-12, -2).addBox(0.0F, -2.0F, -2.0F, 12.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, -9.0F, 0.0F));

		PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(-12, -6).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -12.0F, 0.0F));
		return LayerDefinition.create(modelData, 16, 16);
	}
}