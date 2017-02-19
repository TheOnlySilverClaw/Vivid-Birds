package silverclaw.birds.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;

public class ModelCrow extends ModelBase {

	private final ModelRenderer body;
	private final ModelRenderer leg_1;
	private final ModelRenderer leg_2;
	private final ModelRenderer foot_1;
	private final ModelRenderer foot_2;
	private final ModelRenderer head;
	private final ModelRenderer beak_1;
	private final ModelRenderer beak_2;
	private final ModelRenderer beak_3;
	private final ModelRenderer wing_1;
	private final ModelRenderer wing_2;
	private final ModelRenderer tail;

	public ModelCrow() {
		
		this(0.0f);
	}

	public ModelCrow(float par1) {
		
		body = new ModelRenderer( this, 0, -6 );
		body.setTextureSize( 16, 32 );
		body.addBox( -1.5F, -2F, -3F, 3, 4, 6);
		body.setRotationPoint( 0F, 18F, 0F );
		
		head = new ModelRenderer( this, 0, 24 );
		head.setTextureSize( 16, 32 );
		head.addBox( -1.5F, -2F, -2F, 3, 4, 4);
		head.setRotationPoint( 0F, 15.71693F, -3.320421F );
		
		tail = new ModelRenderer( this, 0, 4 );
		tail.setTextureSize( 16, 32 );
		tail.addBox( -0.5F, -0.5F, -2.5F, 1, 1, 5);
		tail.setRotationPoint( 0F, 16.98862F, 4.896643F );
		
		wing_1 = new ModelRenderer( this, 0, 10 );
		wing_1.setTextureSize( 16, 32 );
		wing_1.addBox( -0.5F, -3F, -2F, 1, 6, 4);
		wing_1.setRotationPoint( 2F, 18.57478F, 2.160933F );
		
		wing_2 = new ModelRenderer( this, 0, 10 );
		wing_2.setTextureSize( 16, 32 );
		wing_2.addBox( -0.5F, -3F, -2F, 1, 6, 4);
		wing_2.setRotationPoint( -2F, 18.57478F, 2.160933F );
		
		beak_1 = new ModelRenderer( this, 6, 10 );
		beak_1.setTextureSize( 16, 32 );
		beak_1.addBox( -0.5F, -1F, -1F, 1, 2, 2);
		beak_1.setRotationPoint( 0F, 15.00002F, -6.000008F );
		
		beak_2 = new ModelRenderer( this, 7, 6 );
		beak_2.setTextureSize( 16, 32 );
		beak_2.addBox( -0.5F, -1F, -0.5F, 1, 2, 1);
		beak_2.setRotationPoint( 0F, 15.61357F, -7.232218F );
		
		beak_3 = new ModelRenderer( this, 10, 8 );
		beak_3.setTextureSize( 16, 32 );
		beak_3.addBox( -0.5F, -0.5F, -1F, 1, 1, 2);
		beak_3.setRotationPoint( 0F, 16.93777F, -5.733382F );
		
		leg_1 = new ModelRenderer( this, 0, 20 );
		leg_1.setTextureSize( 16, 32 );
		leg_1.addBox( -0.5F, -2.5F, -0.5F, 1, 5, 1);
		leg_1.setRotationPoint( 1F, 22F, 1.047411E-05F );
		
		leg_2 = new ModelRenderer( this, 0, 20 );
		leg_2.setTextureSize( 16, 32 );
		leg_2.addBox( -0.5F, -2.5F, -0.5F, 1, 5, 1);
		leg_2.setRotationPoint( -1F, 22F, 1.047411E-05F );
		
		foot_1 = new ModelRenderer( this, 4, 20 );
		foot_1.setTextureSize( 16, 32 );
		foot_1.addBox( -0.5F, -0.5F, -1F, 1, 1, 2);
		foot_1.setRotationPoint( 1F, 24F, -0.9999843F );
		
		foot_2 = new ModelRenderer( this, 4, 20 );
		foot_2.setTextureSize( 16, 32 );
		foot_2.addBox( -0.5F, -0.5F, -1F, 1, 1, 2);
		foot_2.setRotationPoint( -1F, 24F, -0.9999843F );
	}

	@Override
	public void render(Entity par1Entity, float par2, float par3,
		   float par4, float par5, float par6, float par7) {
		
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		
		if(isChild) {
			GlStateManager.pushMatrix();
			GlStateManager.scale(0.5, 0.5, 0.5);
			GlStateManager.translate(0, par7 * 19, 0);
			body.render(par7);
			tail.render(par7);
			leg_1.render(par7);
			leg_2.render(par7);
			foot_1.render(par7);
			foot_2.render(par7);
			head.render(par7);
			beak_1.render(par7);
			beak_2.render(par7);
			beak_3.render(par7);
			wing_1.render(par7);
			wing_2.render(par7);
			
			GlStateManager.popMatrix();
		} else {
			GlStateManager.pushMatrix();
			GlStateManager.translate(0, par7 * -4, 0);
			body.render(par7);
			tail.render(par7);
			leg_1.render(par7);
			leg_2.render(par7);
			foot_1.render(par7);
			foot_2.render(par7);
			head.render(par7);
			beak_1.render(par7);
			beak_2.render(par7);
			beak_3.render(par7);
			wing_1.render(par7);
			wing_2.render(par7);
			
			GlStateManager.popMatrix();
		}
	}

	@Override
	public void setRotationAngles(float time, float speed, float f3,
			float yaw, float pitch, float f6, Entity entity) {
		  
		foot_2.rotateAngleZ = 2.841642E-07F;
		foot_1.rotateAngleZ = -2.812571E-07F;
		leg_2.rotateAngleX = 2.580948E-06F;
		leg_1.rotateAngleX = 2.580948E-06F;
		beak_3.rotateAngleX = 2.580948E-06F;
		beak_1.rotateAngleX = 2.580948E-06F;
		
		wing_2.rotateAngleX = 1.184617F;
		wing_2.rotateAngleY = 0.4011196F;
		wing_2.rotateAngleZ = 0.4993991F;
		
		wing_1.rotateAngleX = 1.184617F;
		wing_1.rotateAngleY = -0.4011196F;
		wing_1.rotateAngleZ = -0.4993991F;
		
		tail.rotateAngleX = -0.4663177F;
		tail.rotateAngleY = -3.141593F;
		tail.rotateAngleZ = -3.141593F;
		
		head.rotateAngleX = 2.580948E-06F;
		
		body.rotateAngleX = -0.7236136F;
		/*
		beak_1.rotateAngleX = 0.3f;
		
        wing_1.rotateAngleX = MathHelper.cos(time * 0.662f) * 0.5f * speed + 0.3f;
        wing_2.rotateAngleX = wing_1.rotateAngleX;
        
        wing_1.rotateAngleY = Math.max(MathHelper.cos(time * 0.662f) * 1.5f * speed, 0.2f);
        wing_2.rotateAngleY = -wing_1.rotateAngleY;
        
        wing_1.rotateAngleZ = Math.max(MathHelper.cos(time * 0.662f) * 1.5f * speed, 0.2f);
        wing_2.rotateAngleZ = -wing_1.rotateAngleZ;
        
		leg_1.rotateAngleX = MathHelper.cos(time * 0.5f) * 1.4f * speed;
		leg_2.rotateAngleX = MathHelper.cos(time * 0.5f + (float) Math.PI) * 1.4f * speed;
		*/
	}
}
