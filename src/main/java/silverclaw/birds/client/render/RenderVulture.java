package silverclaw.birds.client.render;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderVulture extends RenderLiving {

	private static final ResourceLocation SKIN = new ResourceLocation("birdmod", 
			"textures/entity/birds/vulture.png");

	public RenderVulture(RenderManager manager, 
			ModelBase model, float shadow) {
		super(manager, model, shadow);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return SKIN;
	}
}
