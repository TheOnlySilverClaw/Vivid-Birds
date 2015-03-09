package silverclaw.birds.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelPenguin extends ModelBase {

	private final ModelRenderer body;
	private final ModelRenderer head;
	private final ModelRenderer wing_1;
	private final ModelRenderer wing_2;
	private final ModelRenderer foot_1;
	private final ModelRenderer foot_2;
	private final ModelRenderer beak;

	public ModelPenguin() {
		
		this(0.0f);
	}

	public ModelPenguin(float par1 ) {
		
		body = new ModelRenderer(this, 0, 8 );
		body.setTextureSize(32, 32 );
		body.addBox(-2.5f, -4f, -2f, 5, 8, 4);
		body.setRotationPoint(0f, 19f, 0f);
		
		head = new ModelRenderer(this, 0, 0 );
		head.setTextureSize(32, 32 );
		head.addBox(-2f, -2f, -2f, 4, 4, 4);
		head.setRotationPoint(0f, -4f, 0f);
		
		wing_1 = new ModelRenderer(this, 0, 20 );
		wing_1.setTextureSize(32, 32 );
		wing_1.addBox(-0.5f, 0f, -2f, 1, 8, 4);
		wing_1.setRotationPoint(-3f, -4f, 0f);
		
		wing_2 = new ModelRenderer(this, 0, 20 );
		wing_2.setTextureSize(32, 32 );
		wing_2.addBox(-0.5f, 0f, -2f, 1, 8, 4);
		wing_2.setRotationPoint(3f, -4f, 0f);
		
		foot_1 = new ModelRenderer(this, 10, 26 );
		foot_1.setTextureSize(32, 32 );
		foot_1.addBox(-1f, -0.5f, -4.5f, 2, 1, 5);
		foot_1.setRotationPoint(-1f, 23.5f, 1f);
		
		foot_2 = new ModelRenderer(this, 10, 26 );
		foot_2.setTextureSize(32, 32 );
		foot_2.addBox(-1f, -0.5f, -5.5f, 2, 1, 5);
		foot_2.setRotationPoint(1f, 23.5f, 2f);
		
		beak = new ModelRenderer(this, 12, 1 );
		beak.setTextureSize(32, 32 );
		beak.addBox(-1f, -0.5f, -1f, 2, 1, 2);
		beak.setRotationPoint(0f, 0f, -3f);
		
		body.addChild(head);
		head.addChild(beak);
		body.addChild(wing_1);
		body.addChild(wing_2);
	}
	
	@Override
	public void render(Entity par1Entity, float par2, float par3,
		   float par4, float par5, float par6, float par7) {
		
		if(isChild) {
			
			GlStateManager.pushMatrix();
			GlStateManager.scale(0.6, 0.6, 0.6);
			GlStateManager.translate(0, 10 * par7, 0);
			body.render(par7);
			foot_1.render(par7);
			foot_2.render(par7);
			GlStateManager.popMatrix();
			
		} else {
			body.render(par7);
			foot_1.render(par7);
			foot_2.render(par7);
		}			
	}
		
	@Override
	public void setRotationAngles(float time, float speed, float f3,
			float yaw, float pitch, float f6, Entity entity) {
		
		head.rotateAngleX = pitch / 57.3f + 0.2f;
		head.rotateAngleY = yaw / 57.3f;
		
        wing_1.rotateAngleX = MathHelper.cos(time * 0.33f) * 0.5f * speed + 0.03f;
        wing_2.rotateAngleX = wing_1.rotateAngleX;
        
        wing_1.rotateAngleY = -Math.max(MathHelper.cos(time * 0.662f) * 0.6f * speed, 0.02f);
        wing_2.rotateAngleY = -wing_1.rotateAngleY;
        
        wing_1.rotateAngleZ = Math.max(MathHelper.cos(time * 0.662f) * 0.6f * speed, 0.02f);
        wing_2.rotateAngleZ = -wing_1.rotateAngleZ;
	}
}
