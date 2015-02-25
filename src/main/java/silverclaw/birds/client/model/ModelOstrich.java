package silverclaw.birds.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;

public class ModelOstrich extends ModelBase {

	private final ModelRenderer body;
	private final ModelRenderer neck;
	private final ModelRenderer head;
	private final ModelRenderer beak;
	private final ModelRenderer tail;
	private final ModelRenderer leg_1;
	private final ModelRenderer leg_2;
	private final ModelRenderer wing_1;
	private final ModelRenderer wing_2;
	private final ModelRenderer foot_1;
	private final ModelRenderer foot_2;

	public ModelOstrich() {
		this(0.0f);
	}

	public ModelOstrich(float par1) {
		
		body = new ModelRenderer(this, 0, 0 );
		body.setTextureSize(64, 64 );
		body.addBox(-5f, -4f, -8f, 10, 8, 16);
		body.setRotationPoint(0f, 2f, 0f);
		
		neck = new ModelRenderer(this, 52, 16 );
		neck.setTextureSize(64, 64 );
		neck.addBox(-1.5f, -16f, -1.5f, 3, 16, 3);
		neck.setRotationPoint(0f, 1f, -7f);
		
		head = new ModelRenderer(this, 36, 3 );
		head.setTextureSize(64, 64 );
		head.addBox(-2f, -2.5f, -4f, 4, 5, 8);
		head.setRotationPoint(0f, -17f, -2f);
		
		beak = new ModelRenderer(this, 48, 57 );
		beak.setTextureSize(64, 64 );
		beak.addBox(-1.5f, -1f, -2.5f, 3, 2, 5);
		beak.setRotationPoint(0f, 0.5f, -5f);
		
		tail = new ModelRenderer(this, 2, 31 );
		tail.setTextureSize(64, 64 );
		tail.addBox(-3f, -8f, -0.5f, 6, 8, 1);
		tail.setRotationPoint(0f, -1f, 6f);
		
		leg_1 = new ModelRenderer(this, 36, 24 );
		leg_1.setTextureSize(64, 64 );
		leg_1.addBox(-1f, 0f, -1f, 2, 16, 2);
		leg_1.setRotationPoint(-3f, 6f, 0f);
		
		leg_2 = new ModelRenderer(this, 36, 24 );
		leg_2.setTextureSize(64, 64 );
		leg_2.addBox(-1f, 0f, -1f, 2, 16, 2);
		leg_2.setRotationPoint(3f, 6f, 0f);
		
		wing_1 = new ModelRenderer(this, 0, 24 );
		wing_1.setTextureSize(64, 64 );
		wing_1.addBox(-1f, -4f, -1f, 2, 8, 16);
		wing_1.setRotationPoint(6f, 2f, -7f);
		
		wing_2 = new ModelRenderer(this, 0, 24 );
		wing_2.setTextureSize(64, 64 );
		wing_2.addBox(-1f, -4f, -1f, 2, 8, 16);
		wing_2.setRotationPoint(-6f, 2f, -7f);
		
		foot_1 = new ModelRenderer(this, 28, 42 );
		foot_1.setTextureSize(64, 64 );
		foot_1.addBox(-3f, -1f, -4f, 6, 2, 8);
		foot_1.setRotationPoint(-1f, 16f, -1f);
		
		foot_2 = new ModelRenderer(this, 28, 42 );
		foot_2.setTextureSize(64, 64 );
		foot_2.addBox(-3f, -1f, -4f, 6, 2, 8);
		foot_2.setRotationPoint(1f, 16f, -1f);
		
		neck.addChild(head);
		head.addChild(beak);
		
		leg_1.addChild(foot_1);
		leg_2.addChild(foot_2);
	}

	@Override
	public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7) {
		
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		
		if(isChild) {
			GlStateManager.pushMatrix();
			GlStateManager.scale(0.35f, 0.35f, 0.35f);
			GlStateManager.translate(0, par7 * 45f, 0);
			body.render(par7);
			neck.render(par7);

			tail.render(par7);
			
			leg_1.render(par7);
			leg_2.render(par7);
			
			wing_1.render(par7);
			wing_2.render(par7);
			GlStateManager.popMatrix();
		} else {
			body.render(par7);
			neck.render(par7);

			tail.render(par7);
			
			leg_1.render(par7);
			leg_2.render(par7);
			
			wing_1.render(par7);
			wing_2.render(par7);
		}
	}
	
	@Override
	public void setRotationAngles(float time, float speed, float f3,
			float yaw, float pitch, float f6, Entity entity) {
		
		
		boolean playerAiming = false;
		if(entity.riddenByEntity instanceof EntityPlayer) {
			ItemStack stack = ((EntityPlayer) entity.riddenByEntity).getItemInUse();
			playerAiming = (stack != null && stack.getItem() instanceof ItemBow);
		}

		
		neck.rotateAngleX = (entity.isSprinting() || 
				playerAiming)? 0.9f : 0.45f;
		if(entity.getEntityWorld().getBlockState(entity.getPosition()
				.up().up()).getBlock() != Blocks.air) {
			neck.rotateAngleX = 1.1f;
		}
		
	   head.rotateAngleX = pitch / 57.3f + 0.2f;
	   head.rotateAngleY = yaw / (57.3f * 2);
	   
	   //neck.rotateAngleX = pitch / 57.3f - 0.2f;
	   neck.rotateAngleY = yaw / 57.3f;
		   
       wing_1.rotateAngleX = MathHelper.cos(time * 0.662f) * 0.4f * speed + 0.3f;
       wing_2.rotateAngleX = wing_1.rotateAngleX;
        
       wing_1.rotateAngleY = MathHelper.cos(time * 0.662f) * 0.5f * speed + 0.3f;
       wing_2.rotateAngleY = -wing_1.rotateAngleY;
        
       wing_1.rotateAngleZ = Math.max(MathHelper.cos(time * 0.662f) * 1.5f * speed, 0.2f);
       wing_2.rotateAngleZ = -wing_1.rotateAngleZ;
       
       tail.rotateAngleX = -1.1f;

       leg_1.rotateAngleX = MathHelper.cos(time * 0.762f) * 1.4f * speed;
       leg_2.rotateAngleX = MathHelper.cos(time * 0.762f + (float) Math.PI) * 1.4f * speed;
	}
}
