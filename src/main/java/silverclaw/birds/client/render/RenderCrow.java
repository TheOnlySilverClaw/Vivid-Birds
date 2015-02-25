package silverclaw.birds.client.render;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderCrow extends RenderLiving {

	private final static ResourceLocation SKIN = new ResourceLocation("birdmod",
			"textures/entity/birds/raven.png");
	
	public RenderCrow(RenderManager manager, 
			ModelBase model, float shadow) {
		super(manager, model, shadow);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return SKIN;
	}
}
