package silverclaw.birds.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelKiwi extends ModelBase {
	
	private final ModelRenderer body;
	private final ModelRenderer leg_1;
	private final ModelRenderer leg_2;
	private final ModelRenderer leg_3;
	private final ModelRenderer leg_4;
	private final ModelRenderer head;
	private final ModelRenderer beak;
	private final ModelRenderer wing_1;
	private final ModelRenderer wing_2;

	public ModelKiwi() {
		
		this(0.0f);
	}

	public ModelKiwi(float par1) {
		
		body = new ModelRenderer(this, 0, 19);
		body.setTextureSize(32, 32);
		body.addBox(-3F, -2.5F, -4F, 6, 5, 8);
		body.setRotationPoint(0F, 16F, 0F);
		
		leg_1 = new ModelRenderer(this, 20, 21);
		leg_1.setTextureSize(32, 32);
		leg_1.addBox(-0.5F, -3F, 0F, 1, 6, 0);
		leg_1.setRotationPoint(1F, 5F, 0F);
		
		leg_2 = new ModelRenderer(this, 20, 21);
		leg_2.setTextureSize(32, 32);
		leg_2.addBox(-0.5F, -3F, 0F, 1, 6, 0);
		leg_2.setRotationPoint(-1F, 5F, 0F);
		
		leg_3 = new ModelRenderer(this, 20, 25);
		leg_3.setTextureSize(32, 32);
		leg_3.addBox(-0.5F, 0F, -1F, 1, 0, 2);
		leg_3.setRotationPoint(0F, 2F, -1F);
		
		leg_4 = new ModelRenderer(this, 20, 25);
		leg_4.setTextureSize(32, 32);
		leg_4.addBox(-0.5F, 0F, -1F, 1, 0, 2);
		leg_4.setRotationPoint(0F, 2F, -1F);
		
		head = new ModelRenderer(this, 0, 0);
		head.setTextureSize(32, 32);
		head.addBox(-1.5F, -2.5F, -2F, 3, 5, 4);
		head.setRotationPoint(0F, 1F, -5F);
		
		beak = new ModelRenderer(this, 0, 10);
		beak.setTextureSize(32, 32);
		beak.addBox(-0.5F, -0.5F, -4F, 1, 1, 8);
		beak.setRotationPoint(0F, 1F, -5F);
		
		wing_1 = new ModelRenderer(this, 10, 7);
		wing_1.setTextureSize(32, 32);
		wing_1.addBox(-0.5F, -2F, -3.5F, 1, 4, 7);
		wing_1.setRotationPoint(3.5F, 0F, 0F);
		
		wing_2 = new ModelRenderer(this, 10, 7);
		wing_2.setTextureSize(32, 32);
		wing_2.addBox(-0.5F, -2F, -3.5F, 1, 4, 7);
		wing_2.setRotationPoint(-3.5F, 0F, 0F);
		
		body.addChild(head);
		head.addChild(beak);
		
		body.addChild(leg_1);
		body.addChild(leg_2);
		
		body.addChild(wing_1);
		body.addChild(wing_2);
		
		leg_1.addChild(leg_3);
		leg_2.addChild(leg_4);
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
			GlStateManager.popMatrix();
		} else {
			body.render(par7);
		}
	}

	@Override
	public void setRotationAngles(float time, float speed, float f3,
			float yaw, float pitch, float f6, Entity entity) {
		        

		head.rotateAngleX = pitch / 57.3f + 0.2f;
		head.rotateAngleY = yaw / (57.3f * 2f);
		
		beak.rotateAngleX = 0.2f;
		
		
        wing_1.rotateAngleX = MathHelper.cos(time * 0.662f) * 0.5f * speed + 0.3f;
        wing_2.rotateAngleX = wing_1.rotateAngleX;
        
        wing_1.rotateAngleY = Math.max(MathHelper.cos(time * 0.662f) * 1.5f * speed, 0.2f);
        wing_2.rotateAngleY = -wing_1.rotateAngleY;
        
        wing_1.rotateAngleZ = Math.max(MathHelper.cos(time * 0.662f) * 1.5f * speed, 0.2f);
        wing_2.rotateAngleZ = -wing_1.rotateAngleZ;
        
		leg_1.rotateAngleX = MathHelper.cos(time * 0.5f) * 1.4f * speed;
		leg_2.rotateAngleX = MathHelper.cos(time * 0.5f + (float) Math.PI) * 1.4f * speed;
	}
}
