// Made with Blockbench 3.8.4
// Exported for Minecraft version 1.15 - 1.16
// Paste this class into your mod and generate all required imports


public class steve extends EntityModel<Entity> {
	private final ModelRenderer Ears;
	private final ModelRenderer Ear_Left;
	private final ModelRenderer Ear_Right;

	public steve() {
		textureWidth = 64;
		textureHeight = 64;

		Ears = new ModelRenderer(this);
		Ears.setRotationPoint(0.0F, 24.0F, 0.0F);
		

		Ear_Left = new ModelRenderer(this);
		Ear_Left.setRotationPoint(0.0F, -24.0F, 0.0F);
		Ears.addChild(Ear_Left);
		Ear_Left.setTextureOffset(3, 12).addBox(4.0F, 19.0F, -2.0F, 1.0F, 2.0F, 2.0F, 0.0F, false);
		Ear_Left.setTextureOffset(3, 12).addBox(4.0F, 18.0F, -1.0F, 1.0F, 2.0F, 2.0F, 0.0F, false);
		Ear_Left.setTextureOffset(4, 14).addBox(4.0F, 17.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);

		Ear_Right = new ModelRenderer(this);
		Ear_Right.setRotationPoint(-9.0F, -24.0F, 0.0F);
		Ears.addChild(Ear_Right);
		Ear_Right.setTextureOffset(3, 12).addBox(4.0F, 19.0F, -2.0F, 1.0F, 2.0F, 2.0F, 0.0F, false);
		Ear_Right.setTextureOffset(3, 12).addBox(4.0F, 18.0F, -1.0F, 1.0F, 2.0F, 2.0F, 0.0F, false);
		Ear_Right.setTextureOffset(4, 14).addBox(4.0F, 17.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		//previously the render function, render code was moved to a method below
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		Ears.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}