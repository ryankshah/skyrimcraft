// Made with Blockbench 3.8.4
// Exported for Minecraft version 1.15 - 1.16
// Paste this class into your mod and generate all required imports


public class steve extends EntityModel<Entity> {
	private final ModelRenderer Ears;
	private final ModelRenderer Ear_Left;
	private final ModelRenderer Ear_Right;
	private final ModelRenderer Whiskers;
	private final ModelRenderer Whiskers_Left;
	private final ModelRenderer cube_r1;
	private final ModelRenderer cube_r2;
	private final ModelRenderer Whiskers_Right;
	private final ModelRenderer cube_r3;
	private final ModelRenderer cube_r4;

	public steve() {
		textureWidth = 64;
		textureHeight = 64;

		Ears = new ModelRenderer(this);
		Ears.setRotationPoint(0.0F, 24.0F, 0.0F);
		

		Ear_Left = new ModelRenderer(this);
		Ear_Left.setRotationPoint(5.0F, -24.0F, 4.0F);
		Ears.addChild(Ear_Left);
		setRotationAngle(Ear_Left, 0.0F, 1.5708F, 0.0F);
		Ear_Left.setTextureOffset(3, 12).addBox(4.0F, 19.0F, -2.0F, 1.0F, 2.0F, 2.0F, 0.0F, false);
		Ear_Left.setTextureOffset(3, 12).addBox(4.0F, 18.0F, -1.0F, 1.0F, 2.0F, 2.0F, 0.0F, false);
		Ear_Left.setTextureOffset(4, 14).addBox(4.0F, 17.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);

		Ear_Right = new ModelRenderer(this);
		Ear_Right.setRotationPoint(-5.0F, -24.0F, -5.0F);
		Ears.addChild(Ear_Right);
		setRotationAngle(Ear_Right, 0.0F, -1.5708F, 0.0F);
		Ear_Right.setTextureOffset(3, 12).addBox(4.0F, 19.0F, -2.0F, 1.0F, 2.0F, 2.0F, 0.0F, false);
		Ear_Right.setTextureOffset(3, 12).addBox(4.0F, 18.0F, -1.0F, 1.0F, 2.0F, 2.0F, 0.0F, false);
		Ear_Right.setTextureOffset(4, 14).addBox(4.0F, 17.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);

		Whiskers = new ModelRenderer(this);
		Whiskers.setRotationPoint(-5.0F, 0.0F, -5.0F);
		

		Whiskers_Left = new ModelRenderer(this);
		Whiskers_Left.setRotationPoint(3.0F, 22.0F, 2.0F);
		Whiskers.addChild(Whiskers_Left);
		setRotationAngle(Whiskers_Left, 0.0F, -0.3054F, 0.0F);
		Whiskers_Left.setTextureOffset(3, 14).addBox(-4.0F, -0.5F, -1.0F, 4.0F, 1.0F, 0.0F, 0.0F, false);

		cube_r1 = new ModelRenderer(this);
		cube_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
		Whiskers_Left.addChild(cube_r1);
		setRotationAngle(cube_r1, 0.0F, 0.0F, -0.4363F);
		cube_r1.setTextureOffset(3, 14).addBox(-4.0F, -0.5F, -1.0F, 4.0F, 1.0F, 0.0F, 0.0F, false);

		cube_r2 = new ModelRenderer(this);
		cube_r2.setRotationPoint(0.0F, 0.0F, 0.0F);
		Whiskers_Left.addChild(cube_r2);
		setRotationAngle(cube_r2, 0.0F, 0.0F, 0.4363F);
		cube_r2.setTextureOffset(3, 14).addBox(-4.0F, -0.5F, -1.0F, 4.0F, 1.0F, 0.0F, 0.0F, false);

		Whiskers_Right = new ModelRenderer(this);
		Whiskers_Right.setRotationPoint(7.0F, 22.0F, 2.0F);
		Whiskers.addChild(Whiskers_Right);
		setRotationAngle(Whiskers_Right, 0.0F, 0.3054F, 0.0F);
		Whiskers_Right.setTextureOffset(3, 14).addBox(0.0F, -0.5F, -1.0F, 4.0F, 1.0F, 0.0F, 0.0F, true);

		cube_r3 = new ModelRenderer(this);
		cube_r3.setRotationPoint(0.0F, 0.0F, 0.0F);
		Whiskers_Right.addChild(cube_r3);
		setRotationAngle(cube_r3, 0.0F, 0.0F, 0.4363F);
		cube_r3.setTextureOffset(3, 14).addBox(0.0F, -0.5F, -1.0F, 4.0F, 1.0F, 0.0F, 0.0F, true);

		cube_r4 = new ModelRenderer(this);
		cube_r4.setRotationPoint(0.0F, 0.0F, 0.0F);
		Whiskers_Right.addChild(cube_r4);
		setRotationAngle(cube_r4, 0.0F, 0.0F, -0.4363F);
		cube_r4.setTextureOffset(3, 14).addBox(0.0F, -0.5F, -1.0F, 4.0F, 1.0F, 0.0F, 0.0F, true);
	}

	@Override
	public void setRotationAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		//previously the render function, render code was moved to a method below
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		Ears.render(matrixStack, buffer, packedLight, packedOverlay);
		Whiskers.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}