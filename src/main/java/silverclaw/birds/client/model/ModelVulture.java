package silverclaw.birds.client.model;

import silverclaw.birds.common.entity.ai.HeightChecker;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;

public class ModelVulture extends ModelBase {
	
	private final ModelRenderer body;
	private final ModelRenderer neck;
	private final ModelRenderer head;
	private final ModelRenderer beak_1;
	private final ModelRenderer beak_2;
	private final ModelRenderer wing_1;
	private final ModelRenderer wing_2;
	private final ModelRenderer leg_1;
	private final ModelRenderer foot_2;
	private final ModelRenderer leg_2;
	private final ModelRenderer foot_1;
	private final ModelRenderer tail;

	public ModelVulture() {
		
		this(0.0f);
	}

	public ModelVulture(float par1 ) {
		
		body = new ModelRenderer(this, 0, 8);
		body.setTextureSize(32, 32 );
		body.addBox(-3F, -6F, -2F, 6, 12, 4);
		body.setRotationPoint(0F, 11F, 0f);
		
		neck = new ModelRenderer(this, 16, 3);
		neck.setTextureSize(32, 32 );
		neck.addBox(-1F, -1F, -3F, 2, 2, 6);
		neck.setRotationPoint(0F, -3F, -3f);
		
		head = new ModelRenderer(this, 0, 0);
		head.setTextureSize(32, 32 );
		head.addBox(-1F, -1.5F, -2.5F, 2, 3, 5);
		head.setRotationPoint(0F, 1F, -5f);
		
		beak_1 = new ModelRenderer(this, 9, 1);
		beak_1.setTextureSize(32, 32 );
		beak_1.addBox(-1F, -0.5F, -1.5F, 2, 1, 3);
		beak_1.setRotationPoint(0F, 0F, -4f);
		
		beak_2 = new ModelRenderer(this, 16, 2);
		beak_2.setTextureSize(32, 32 );
		beak_2.addBox(-1F, -0.5F, -0.5F, 2, 1, 1);
		beak_2.setRotationPoint(0F, 1F, -1f);
		
		wing_1 = new ModelRenderer(this, 20, 14);
		wing_1.setTextureSize(32, 32 );
		wing_1.addBox(-0.5F, 0F, -2F, 1, 12, 4);
		wing_1.setRotationPoint(3.5F, -4F, -1f);
		
		wing_2 = new ModelRenderer(this, 20, 14);
		wing_2.setTextureSize(32, 32 );
		wing_2.addBox(-0.5F, 0F, -2F, 1, 12, 4);
		wing_2.setRotationPoint(-3.5F, -4F, -1f);
		
		foot_1 = new ModelRenderer(this, 7, 28);
		foot_1.setTextureSize(32, 32 );
		foot_1.addBox(-1.5F, -0.5F, -1.5F, 3, 1, 3);
		foot_1.setRotationPoint(0F, 6F, -1f);		
		
		foot_2 = new ModelRenderer(this, 7, 28);
		foot_2.setTextureSize(32, 32 );
		foot_2.addBox(-1.5F, -0.5F, -1.5F, 3, 1, 3);
		foot_2.setRotationPoint(-0F, 6F, -1f);
		
		leg_2 = new ModelRenderer(this, 16, 24);
		leg_2.setTextureSize(32, 32 );
		leg_2.addBox(-0.5F, 0F, -0.5F, 1, 6, 1);
		leg_2.setRotationPoint(-2F, 5f, 0f);
		
		leg_1 = new ModelRenderer(this, 16, 24);
		leg_1.setTextureSize(32, 32 );
		leg_1.addBox(-0.5F, 1F, -0.5F, 1, 6, 1);
		leg_1.setRotationPoint(2F, 5f, 0f);
		
		tail = new ModelRenderer(this, 0, 24);
		tail.setTextureSize(32, 32 );
		tail.addBox(-2F, 0F, -0.5F, 4, 6, 1);
		tail.setRotationPoint(0F, 6f, 2f);
		
		body.addChild(neck);
		body.addChild(leg_1);
		body.addChild(leg_2);
		body.addChild(wing_1);
		body.addChild(wing_2);
		body.addChild(tail);
		
		neck.addChild(head);
		head.addChild(beak_1);
		beak_1.addChild(beak_2);
		
		leg_1.addChild(foot_1);
		leg_2.addChild(foot_2);
	}

	@Override
	public void render(Entity par1Entity, float par2, float par3, 
		   float par4, float par5, float par6, float par7) {
	   
	   setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
	   body.render(par7);
	}
   
   @Override
   public void setRotationAngles(float time, float speed, float f3,
			float yaw, float pitch, float f6, Entity entity) {
	   
	   float limbSwing = ((EntityLiving) entity).limbSwingAmount;
	   
	   if(!HeightChecker.isNearGround(entity, 2)) {
		   
		   leg_1.rotateAngleX = 0.2f;
		   leg_2.rotateAngleX = -leg_1.rotateAngleX;
		   
		   wing_1.rotateAngleX = MathHelper.cos(time * 0.662f) * limbSwing * speed - 0.4f;
		   wing_2.rotateAngleX = wing_1.rotateAngleX;
		   
		   wing_1.rotateAngleY = -0.8f;
		   wing_2.rotateAngleY = -wing_1.rotateAngleY;
		   
		   wing_1.rotateAngleZ = MathHelper.cos(time * 0.662f) * limbSwing * speed - 0.6f;
		   wing_2.rotateAngleZ = -wing_1.rotateAngleZ;
		   
		   body.rotateAngleX = 1.2f;
		   neck.rotateAngleX = -0.8f;
		   head.rotateAngleX = 0.1f;
		   tail.rotateAngleX = 0.6f;
		   
		   head.rotateAngleY = yaw / 57.3f;
		   neck.rotateAngleY = yaw / 57.3f;
		   
	   } else {
		   
		   body.rotateAngleX = 0.3f;
		   
		   leg_1.rotateAngleX = MathHelper.cos(time * 0.762f) * 1.4f * speed;
		   leg_2.rotateAngleX = MathHelper.cos(time * 0.762f + (float) Math.PI) * 1.4f * speed;
		   
		   wing_1.rotateAngleX = MathHelper.cos(time * 0.662f) * limbSwing * speed + 0.25f;
		   wing_2.rotateAngleX = wing_1.rotateAngleX;
		   
		   wing_2.rotateAngleZ = Math.max(MathHelper.cos(time * 0.662f) * 1.5f * speed, 0.1f);
		   wing_1.rotateAngleZ = -wing_2.rotateAngleZ;
		   
		   wing_1.rotateAngleY = -0.3f;
		   wing_2.rotateAngleY = -wing_1.rotateAngleY;
		   
		   foot_1.rotateAngleX = 0.002f;
		   foot_2.rotateAngleX = foot_1.rotateAngleX;
		   
		   tail.rotateAngleX = 0.5f;
		   
		   head.rotateAngleX = pitch / 57.3f + 0.2f;
		   head.rotateAngleY = yaw / (57.3f * 2);
		   
		   neck.rotateAngleX = pitch / 57.3f - 0.2f;
		   neck.rotateAngleY = yaw / 57.3f;
	   }
   }
}
