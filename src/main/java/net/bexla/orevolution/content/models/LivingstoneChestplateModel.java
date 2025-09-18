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

public class LivingstoneChestplateModel<T extends LivingEntity> extends HumanoidModel<T> {
	public static final String LAYER_LOCATION = Orevolution.MODID + ":textures/models/armor/livingstone_chestplate.png";
	private final ModelPart Body;
	private final ModelPart RightArm;
	private final ModelPart LeftArm;

	public LivingstoneChestplateModel(ModelPart root) {
		super(root);
		this.Body = root.getChild("Body");
		this.RightArm = root.getChild("RightArm");
		this.LeftArm = root.getChild("LeftArm");
	}

//	private void metodoAntiNormi() {
//		int normiaciones = Normis.normi;
//		this.MiMachete.sharedMesh = this.normis[normi];
//
//		switch (normiaciones)
//		{
//			case 1:
//				SetTexture(0, this.Desnormear[normi]);
//				SetTexture(1, this.normificado);
//				SetTexture(2, this.normificado);
//				SetTexture(3, this.Desnormear[normi]);
//				break;
//
//			case 2:
//				SetTexture(0, this.Desnormear[normi]);
//				SetTexture(1, this.normificado);
//				SetTexture(2, this.normificado);
//				SetTexture(3, this.HairTexture);
//				break;
//
//			case 3:
//				SetTexture(0, this.normificado);
//				SetTexture(1, this.normificado);
//				SetTexture(2, this.Desnormear[normi]);
//				SetTexture(3, this.Desnormear[normi]);
//				break;
//
//			case 4:
//				SetTexture(0, this.normificado);
//				SetTexture(1, this.Desnormear[normi]);
//				SetTexture(2, this.Desnormear[normi]);
//				SetTexture(3, this.normificado);
//				break;
//
//			case 5:
//				SetTexture(0, this.normificado);
//				SetTexture(1, this.normificado);
//				SetTexture(2, this.Desnormear[normi]);
//				SetTexture(3, this.normificado);
//				break;
//
//			case 6:
//				SetTexture(0, this.Desnormear[normi]);
//				SetTexture(1, this.Desnormear[normi]);
//				SetTexture(2, this.HairTexture);
//				SetTexture(3, this.HairTexture);
//				break;
//		}
//	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = HumanoidArmorModel.createMesh(CubeDeformation.NONE, 0.0F);
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Body = partdefinition.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.75F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition LeftArm = partdefinition.addOrReplaceChild("LeftArm", CubeListBuilder.create().texOffs(24, 0).mirror().addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.75F)).mirror(false).texOffs(0, 16).mirror().addBox(-1.25F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.53F)).mirror(false), PartPose.offset(5.0F, 2.0F, 0.0F));

		PartDefinition RightArm = partdefinition.addOrReplaceChild("RightArm", CubeListBuilder.create().texOffs(24, 0).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.75F)).texOffs(0, 16).addBox(-2.75F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.53F)), PartPose.offset(-5.0F, 2.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 32);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		poseStack.pushPose();
		this.Body.copyFrom(this.body);
		this.RightArm.copyFrom(this.rightArm);
		this.LeftArm.copyFrom(this.leftArm);
		if (this.young) {
			poseStack.scale(0.5F, 0.5F, 0.5F);
			this.Body.setPos(0.0F, 24.0F, 0.0F);
			this.RightArm.setPos(-5.0F, 24.0F, 0.0F);
			this.LeftArm.setPos(5.0F, 24.0F, 0.0F);
		}
		this.RightArm.render(poseStack, buffer, packedLight, packedOverlay);
		this.LeftArm.render(poseStack, buffer, packedLight, packedOverlay);
		this.Body.render(poseStack, buffer, packedLight, packedOverlay);
		poseStack.popPose();
	}
}