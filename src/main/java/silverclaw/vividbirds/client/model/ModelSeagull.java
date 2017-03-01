package silverclaw.vividbirds.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.MathHelper;
import silverclaw.vividbirds.common.entity.util.HeightChecker;

public class ModelSeagull extends ModelBase {
	
	private final ModelRenderer body;
	private final ModelRenderer leg_1;
	private final ModelRenderer leg_2;
	private final ModelRenderer foot_1;
	private final ModelRenderer foot_2;
	private final ModelRenderer tail_1;
	private final ModelRenderer tail_2;
	private final ModelRenderer tail_3;

	private final ModelRenderer head;
	private final ModelRenderer beak;
	private final ModelRenderer wing_1;
	private final ModelRenderer wing_2;

	public ModelSeagull() {
		
		this(0.0f);
	}

	public ModelSeagull(float par1) {
		
        body = new ModelRenderer( this, 4, 4 );
        body.setTextureSize( 64, 32 );
        body.addBox( -2F, -1.5F, -3.5F, 4, 3, 7);
        body.setRotationPoint( 0F, 18F, -1F );
        
        head = new ModelRenderer( this, 37, 7 );
        head.setTextureSize( 64, 32 );
        head.addBox( -1.5F, -1.5F, -2F, 3, 3, 4);
        head.setRotationPoint( 0F, 15.91567F, -4.414027F );
        
        beak = new ModelRenderer( this, 12, 17 );
        beak.setTextureSize( 64, 32 );
        beak.addBox( -0.5F, -0.5F, -2F, 1, 1, 4);
        beak.setRotationPoint( 0F, 0F, -2.5F );
        
        leg_1 = new ModelRenderer( this, 7, 0 );
        leg_1.setTextureSize( 64, 32 );
        leg_1.addBox( -0.5F, 0F, -0.5F, 1, 5, 1);
        leg_1.setRotationPoint( -1F, 0F, 1F );
        
        leg_2 = new ModelRenderer( this, 7, 0 );
        leg_2.setTextureSize( 64, 32 );
        leg_2.addBox( -0.5F, 0F, -0.5F, 1, 5, 1);
        leg_2.setRotationPoint( 1F, 0F, 1F );
        
        foot_1 = new ModelRenderer( this, 14, 14 );
        foot_1.setTextureSize( 64, 32 );
        foot_1.addBox( -0.5F, -0.5F, -1F, 1, 1, 2);
        foot_1.setRotationPoint( 0F, 5F, -1F );
        
        foot_2 = new ModelRenderer( this, 14, 14 );
        foot_2.setTextureSize( 64, 32 );
        foot_2.addBox( -0.5F, -0.5F, -1F, 1, 1, 2);
        foot_2.setRotationPoint( 0F, 5F, -1F );
       
        wing_1 = new ModelRenderer( this, 32, 21 );
        wing_1.setTextureSize( 64, 32 );
        wing_1.addBox( 0F, 0F, 0F, 1, 3, 6);
        wing_1.setRotationPoint(-2.5F, -0.5F, -1.25f);
        
        wing_2 = new ModelRenderer( this, 32, 21 );
        wing_2.setTextureSize( 64, 32 );
        wing_2.addBox( 0F, 0F, 0F, 1, 3, 6);
        wing_2.setRotationPoint(1.5F, -0.5F, -1.25f);
       
        tail_1 = new ModelRenderer( this, 20, 17 );
        tail_1.setTextureSize( 64, 32 );
        tail_1.addBox( -1F, 0F, -3F, 2, 0, 6);
        tail_1.setRotationPoint( 0F, 19F, 2F );
        
        tail_2 = new ModelRenderer( this, 23, 19 );
        tail_2.setTextureSize( 64, 32 );
        tail_2.addBox( -0.5F, 0F, -2F, 1, 0, 4);
        tail_2.setRotationPoint( -1F, 19F, 3F );
        
        tail_3 = new ModelRenderer( this, 23, 19 );
        tail_3.setTextureSize( 64, 32 );
        tail_3.addBox( -0.5F, 0F, -2F, 1, 0, 4);
        tail_3.setRotationPoint( 1F, 19F, 3F );
        
        body.addChild(wing_1);
        body.addChild(wing_2);
        body.addChild(leg_1);
        body.addChild(leg_2);
        
        leg_1.addChild(foot_1);
        leg_2.addChild(foot_2);
        
        head.addChild(beak);
	}

	@Override
	public void render(Entity par1Entity, float par2, float par3,
		   float par4, float par5, float par6, float par7) {
		
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		
		if(isChild) {
			GlStateManager.pushMatrix();
			GlStateManager.scale(0.5, 0.5, 0.5);
			GlStateManager.translate(0, par7 * 22, 0);
			body.render(par7);
			tail_1.render(par7);
			tail_2.render(par7);
			tail_3.render(par7);
			head.render(par7);
			
			GlStateManager.popMatrix();
		} else {
		
			body.render(par7);
			tail_1.render(par7);
			tail_2.render(par7);
			tail_3.render(par7);
			head.render(par7);
			
		}
	}

	@Override
	public void setRotationAngles(float time, float speed, float f3,
			float yaw, float pitch, float f6, Entity entity) {

		head.rotateAngleX = pitch / 57.3f + 0.2f;
		head.rotateAngleY = yaw / 57.3f;
		
        body.rotateAngleX = -0.25f;

        tail_2.rotateAngleX = 7.5f;
        tail_2.rotateAngleY = -0.4f;
        tail_2.rotateAngleZ = -2.7f;

        tail_3.rotateAngleX = 1.3f;
        tail_3.rotateAngleY = 0.4f;
        tail_3.rotateAngleZ = 1.06f;
        
 	   float limbSwing = ((EntityLiving) entity).limbSwingAmount;
	   
 	   if(!HeightChecker.isNearGround(entity, 2)) {
 		   
 		   leg_1.rotateAngleX = 1.2f;
 		   leg_2.rotateAngleX = leg_1.rotateAngleX;
 		   
 		   wing_1.rotateAngleX = MathHelper.cos(time * 0.662f) * limbSwing * speed - 0.6f;
 		   wing_2.rotateAngleX = wing_1.rotateAngleX;
 		   
 		   wing_1.rotateAngleY = -0.55f;
 		   wing_2.rotateAngleY = -wing_1.rotateAngleY;
 		   
 		   wing_1.rotateAngleZ = MathHelper.cos(time * 0.662f) * limbSwing * speed + 0.8f;
 		   wing_2.rotateAngleZ = -wing_1.rotateAngleZ;
 		   
 		   head.rotateAngleX = 0.1f;
 		   
 		   head.rotateAngleY = yaw / 57.3f;
 		   
 	   } else {
 		    		   
 		   leg_1.rotateAngleX = MathHelper.cos(time * 0.762f) * 1.4f * speed;
 		   leg_2.rotateAngleX = MathHelper.cos(time * 0.762f + (float) Math.PI) * 1.4f * speed;
 		   
 		   wing_1.rotateAngleX = MathHelper.cos(time * 0.662f) * 2*limbSwing * speed + 0.25f;
 		   wing_2.rotateAngleX = wing_1.rotateAngleX;
 		   
 		   wing_2.rotateAngleZ = Math.max(MathHelper.cos(time * 0.662f) * 1.5f * speed, 0.1f);
 		   wing_1.rotateAngleZ = -wing_2.rotateAngleZ;
 		   
 		   wing_1.rotateAngleY = -0.3f;
 		   wing_2.rotateAngleY = -wing_1.rotateAngleY;
 		   
 		   foot_1.rotateAngleX = 0.002f;
 		   foot_2.rotateAngleX = foot_1.rotateAngleX;
 		    		   
 		   head.rotateAngleX = pitch / 57.3f + 0.2f;
 		   head.rotateAngleY = yaw / (57.3f * 2);
 		   
 	   }
		beak.rotateAngleX = 0.115f;
	
	}
}