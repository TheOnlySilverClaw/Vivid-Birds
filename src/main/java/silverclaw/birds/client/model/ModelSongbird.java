package silverclaw.birds.client.model;

import silverclaw.birds.common.entity.ai.HeightChecker;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.MathHelper;

public class ModelSongbird extends ModelBase {

		private final ModelRenderer head;
		private final ModelRenderer beak;
		private final ModelRenderer body;
		private final ModelRenderer leftleg;
		private final ModelRenderer rightleg;
		private final ModelRenderer tail;
		private final ModelRenderer leftwing;
		private final ModelRenderer rightwing;
	  
		public ModelSongbird() {

		textureWidth = 64;
		textureHeight = 32;
		
		head = new ModelRenderer(this, 0, 0);
		head.addBox(-1.5f, -3f, -3f, 3, 3, 3);
		head.setRotationPoint(1.5f, 0.5f, 0.5f);
		head.setTextureSize(64, 32);
		head.mirror = true;
		  
		beak = new ModelRenderer(this, 0, 6);
		beak.addBox(0f, 0f, 0f, 1, 1, 2);
		beak.setRotationPoint(-0.5f, -1.5f, -4.5f);
		beak.setTextureSize(64, 32);
		beak.mirror = true;
		  
		body = new ModelRenderer(this, 0, 10);
		body.addBox(0f, 0f, 0f, 3, 3, 4);
		body.setRotationPoint(-1f, 19f, -2f);
		body.setTextureSize(64, 32);
		body.mirror = true;
		  
		leftleg = new ModelRenderer(this, 6, 17);
		leftleg.addBox(0f, 0f, -1f, 1, 2, 1);
		leftleg.setRotationPoint(2f, 2.5f, 2.5f);
		leftleg.setTextureSize(64, 32);
		leftleg.mirror = true;
		  
		rightleg = new ModelRenderer(this, 0, 17);
		rightleg.addBox(-1f, 0f, -1f, 1, 2, 1);
		rightleg.setRotationPoint(1f, 2.5f, 2.5f);
		rightleg.setTextureSize(64, 32);
		rightleg.mirror = true;
		  
		tail = new ModelRenderer(this, 0, 0);
		tail.addBox(-1.5f, 0f, 0f, 3, 0, 3);
		tail.setRotationPoint(0.5f, 19f, 2f);
		tail.setTextureSize(64, 32);
		tail.mirror = true;
		  
		leftwing = new ModelRenderer(this, 14, 4);
		leftwing.addBox(0f, 0f, 0f, 1, 3, 5);
		leftwing.setRotationPoint(2.5f, 0f, -0.0f);
		leftwing.setTextureSize(64, 32);
		leftwing.mirror = true;
		  
		rightwing = new ModelRenderer(this, 14, 15);
		rightwing.addBox(-1f, 0f, 0f, 1, 3, 5);
		rightwing.setRotationPoint(0.5f, 0f, -0.0f);
		rightwing.setTextureSize(64, 32);
		rightwing.mirror = true;
		  
		body.addChild(head);
		head.addChild(beak);
		
		body.addChild(leftwing);
		body.addChild(rightwing);
	
		body.addChild(leftleg);
		body.addChild(rightleg);
		  
		tail.rotateAngleX = 0.25f;

		}
	  
		private void setRotation(ModelRenderer model, float x, float y, float z) {
			
			model.rotateAngleX = x;
			model.rotateAngleY = y;
			model.rotateAngleZ = z;
		}
		
		@Override
		public void render(Entity par1Entity, float par2, float par3,
			float par4, float par5, float par6, float par7) {

			super.render(par1Entity, par2, par3, par4, par5, par6, par7);
		
			if(isChild) {
				GlStateManager.pushMatrix();
				GlStateManager.scale(0.4f, 0.4f, 0.4f);
				GlStateManager.translate(0, 28f * par7, 0);
				body.render(par7);
				GlStateManager.popMatrix();
			} else {
				GlStateManager.pushMatrix();
				GlStateManager.scale(0.8f, 0.8f, 0.8f);
				GlStateManager.translate(0, 4f*par7, 0);
				body.render(par7);
				GlStateManager.popMatrix();
			}
	  }
		
		@Override
		public void setRotationAngles(float time, float speed, float f3,
				float yaw, float pitch, float f6, Entity entity) {
			     
			head.rotateAngleX = pitch / 57.3f + 0.2f;
			head.rotateAngleY = yaw / 57.3f;
			   
		 	float limbSwing = ((EntityLiving) entity).limbSwingAmount;

			if(HeightChecker.isOnGround(entity)) {
			    
				leftwing.rotateAngleX = MathHelper.cos(time * 0.662f) * 0.5f * speed + 0.3f;
		        rightwing.rotateAngleX = leftwing.rotateAngleX;
		        
		        leftwing.rotateAngleY = Math.max(MathHelper.cos(time * 0.7f) * 1.5f * speed, 0.2f);
		        rightwing.rotateAngleY = -leftwing.rotateAngleY;
		        
		        leftwing.rotateAngleZ = Math.max(MathHelper.cos(time * 0.662f) * 1.5f * speed, 0.2f);
		        rightwing.rotateAngleZ = -leftwing.rotateAngleZ;
		        
				leftleg.rotateAngleX = MathHelper.cos(time * 0.7f) * 1.4f * speed;
				rightleg.rotateAngleX = MathHelper.cos(time * 0.662f + (float) Math.PI) * 1.4f * speed;
			
			} else {
				
				rightwing.rotateAngleX = MathHelper.cos(time * 0.662f) * limbSwing * speed - 0.6f;
		 		leftwing.rotateAngleX = rightwing.rotateAngleX;
		 		   
		 		rightwing.rotateAngleY = MathHelper.cos(time * 0.662f) * limbSwing * speed;
		 		leftwing.rotateAngleY = -rightwing.rotateAngleY;
		 		   
		 		rightwing.rotateAngleZ = MathHelper.cos(time * 0.662f) * limbSwing * speed + 0.8f;
		 		leftwing.rotateAngleZ = -rightwing.rotateAngleZ;
			}
	    }
}
