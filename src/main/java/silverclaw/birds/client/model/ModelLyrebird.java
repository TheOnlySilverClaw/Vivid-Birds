package silverclaw.birds.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelLyrebird extends ModelBase {

	private final ModelRenderer body;
	private final ModelRenderer tail;
	private final ModelRenderer head;
	private final ModelRenderer beak;
	private final ModelRenderer leg_1;
	private final ModelRenderer leg_2;
	private final ModelRenderer foot_1;
	private final ModelRenderer foot_2;
	private final ModelRenderer wing_1;
	private final ModelRenderer wing_2;
	
	public ModelLyrebird() {

		this(0.0f);
	}

	public ModelLyrebird(float par1) {

		body = new ModelRenderer(this, 2, 0);
		body.setTextureSize(32, 32 );
		body.addBox(-1.5f, -2f, -3f, 3, 4, 6);
		body.setRotationPoint(0f, 18f, 0f);

		tail = new ModelRenderer(this, 14, 7);
		tail.setTextureSize(32, 32 );
		tail.addBox(-0.5f, -7f, -1.5f, 1, 8, 3);
		tail.setRotationPoint(0f, 16f, 2.5f);

		head = new ModelRenderer(this, 0, 0);
		head.setTextureSize(32, 32 );
		head.addBox(-1f, -2.5f, -2f, 2, 3, 2);
		head.setRotationPoint(0f, 17f, -3f);

		beak = new ModelRenderer(this, 14, 0);
		beak.setTextureSize(32, 32 );
		beak.addBox(-0.5f, -0.5f, -1f, 1, 1, 2);
		beak.setRotationPoint(0f, 16f, -5.5f);

		leg_1 = new ModelRenderer(this, 0, 12);
		leg_1.setTextureSize(32, 32 );
		leg_1.addBox(-0.5f, 0f, 0f, 1, 4, 0);
		leg_1.setRotationPoint(-1f, 20f, 0f);

		leg_2 = new ModelRenderer(this, 0, 12);
		leg_2.setTextureSize(32, 32 );
		leg_2.addBox(-0.5f, 0f, 0f, 1, 4, 0);
		leg_2.setRotationPoint(1f, 20f, 0f);

		foot_1 = new ModelRenderer(this, 0, 14 );
		foot_1.setTextureSize(32, 32 );
		foot_1.addBox(-0.5f, 0f, -1f, 1, 0, 2);
		foot_1.setRotationPoint(0f, 4f, -1f);
		
		foot_2 = new ModelRenderer(this, 0, 14 );
		foot_2.setTextureSize(32, 32 );
		foot_2.addBox(-0.5f, 0f, -1f, 1, 0, 2);
		foot_2.setRotationPoint(-0f, 4f, -1f);

		wing_1 = new ModelRenderer(this, 0, 10);
		wing_1.setTextureSize(32, 32 );
		wing_1.addBox(-0.5f, 0f, 0f, 1, 4, 6);
		wing_1.setRotationPoint(-2f, 16f, -3f);

		wing_2 = new ModelRenderer(this, 0, 10);
		wing_2.setTextureSize(32, 32 );
		wing_2.addBox(-0.5f, 0f, 0f, 1, 4, 6);
		wing_2.setRotationPoint(2f, 16f, -3f);
		
		leg_1.addChild(foot_1);
		leg_2.addChild(foot_2);
	}

	@Override
	public void render(Entity par1Entity, float par2, float par3,
		float par4, float par5, float par6, float par7) {

		super.render(par1Entity, par2, par3, par4, par5, par6, par7);
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		
		if(isChild) {
			GlStateManager.pushMatrix();
			GlStateManager.scale(0.5f, 0.5f, 0.7f);
			GlStateManager.translate(0, 22f * par7, 0);
			body.render(par7);
			head.render(par7);
			tail.render(par7);
			beak.render(par7);
			leg_1.render(par7);
			leg_2.render(par7);
			wing_1.render(par7);
			wing_2.render(par7);
			GlStateManager.popMatrix();
		} else {
			GlStateManager.pushMatrix();
			GlStateManager.scale(1.1f, 1.1f, 1.6f);
			GlStateManager.translate(0, -2.5f*par7, 0);
			body.render(par7);
			head.render(par7);
			tail.render(par7);
			beak.render(par7);
			leg_1.render(par7);
			leg_2.render(par7);
			wing_1.render(par7);
			wing_2.render(par7);
			GlStateManager.popMatrix();
		}
	}
	
	@Override
	public void setRotationAngles(float time, float speed, float f3,
			float yaw, float pitch, float f6, Entity entity) {
		        
		head.rotateAngleX = pitch / 57.3f + 0.2f;
		head.rotateAngleY = yaw / 57.3f;
		   
		tail.rotateAngleX = MathHelper.cos(time * 0.762f) * 1.6f * speed - 0.25f;
        
        wing_1.rotateAngleX = MathHelper.cos(time * 0.662f) * 0.5f * speed + 0.3f;
        wing_2.rotateAngleX = wing_1.rotateAngleX;
        
        wing_1.rotateAngleY = -Math.max(MathHelper.cos(time * 0.662f) * 1.5f * speed, 0.2f);
        wing_2.rotateAngleY = -wing_1.rotateAngleY;
        
        wing_1.rotateAngleZ = Math.max(MathHelper.cos(time * 0.662f) * 1.5f * speed, 0.2f);
        wing_2.rotateAngleZ = -wing_1.rotateAngleZ;
        
		leg_1.rotateAngleX = MathHelper.cos(time * 0.662f) * 1.4f * speed;
		leg_2.rotateAngleX = MathHelper.cos(time * 0.662f + (float) Math.PI) * 1.4f * speed;
	}
}
