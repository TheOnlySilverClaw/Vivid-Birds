package silverclaw.birds.client.render;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderOstrich extends RenderLiving {

	private final static ResourceLocation SKIN = new ResourceLocation("birdmod",
			"textures/entity/birds/ostrich_black.png");
	
	public RenderOstrich(RenderManager manager, 
			ModelBase model, float shadow) {
		super(manager, model, shadow);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return SKIN;
	}
}
