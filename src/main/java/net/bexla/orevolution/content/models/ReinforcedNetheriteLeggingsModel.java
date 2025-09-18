package net.bexla.orevolution.content.models;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.bexla.orevolution.Orevolution;
import net.minecraft.client.model.HumanoidArmorModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.LivingEntity;

public class ReinforcedNetheriteLeggingsModel<T extends LivingEntity> extends HumanoidModel<T> {
	public static final String LAYER_LOCATION = Orevolution.MODID + ":textures/models/armor/reinforced_netherite_leggings.png";

	private final ModelPart Waist;
	private final ModelPart RightLeg;
	private final ModelPart LeftLeg;

	public ReinforcedNetheriteLeggingsModel(ModelPart root) {
		super(root);
		this.Waist = root.getChild("Waist");
		this.RightLeg = root.getChild("RightLeg");
		this.LeftLeg = root.getChild("LeftLeg");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = HumanoidArmorModel.createMesh(CubeDeformation.NONE, 0.0F);
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition LeftLeg = partdefinition.addOrReplaceChild("LeftLeg", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.5F)).mirror(false), PartPose.offset(1.9F, 12.0F, 0.0F));

		PartDefinition LeftLeg_l1 = LeftLeg.addOrReplaceChild("LeftLeg_l1", CubeListBuilder.create().texOffs(16, 0).mirror().addBox(-2.0F, -6.0F, -3.0F, 4.0F, 8.0F, 5.0F, new CubeDeformation(0.25F)).mirror(false), PartPose.offsetAndRotation(1.0F, 5.4F, 0.5F, 0.0F, 0.0F, -0.0873F));

		PartDefinition RightLeg = partdefinition.addOrReplaceChild("RightLeg", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.5F)), PartPose.offset(-1.9F, 12.0F, 0.0F));

		PartDefinition RightLeg_r1 = RightLeg.addOrReplaceChild("RightLeg_r1", CubeListBuilder.create().texOffs(16, 0).addBox(-2.0F, -6.0F, -3.0F, 4.0F, 8.0F, 5.0F, new CubeDeformation(0.25F)), PartPose.offsetAndRotation(-1.0F, 5.4F, 0.5F, 0.0F, 0.0F, 0.0873F));

		PartDefinition Waist = partdefinition.addOrReplaceChild("Waist", CubeListBuilder.create().texOffs(0, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.5F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 32);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		poseStack.pushPose();
		this.Waist.copyFrom(this.body);
		this.RightLeg.copyFrom(this.rightLeg);
		this.LeftLeg.copyFrom(this.leftLeg);
		if (this.young) {
			poseStack.scale(0.5F, 0.5F, 0.5F);
			this.Waist.setPos(0.0F, 24.0F, 0.0F);
			this.LeftLeg.setPos(2.0F, 36.0F, 0.0F);
			this.RightLeg.setPos(-2.0F, 36.0F, 0.0F);
		}
		this.Waist.render(poseStack, buffer, packedLight, packedOverlay);
		this.RightLeg.render(poseStack, buffer, packedLight, packedOverlay);
		this.LeftLeg.render(poseStack, buffer, packedLight, packedOverlay);
		poseStack.popPose();
	}
}