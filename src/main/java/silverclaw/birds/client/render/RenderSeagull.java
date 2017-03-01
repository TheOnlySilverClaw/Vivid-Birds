package silverclaw.birds.client.render;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import silverclaw.birds.client.model.ModelSeagull;
import silverclaw.birds.common.Birds;
import silverclaw.birds.common.entity.EntitySeagull;

public class RenderSeagull
	extends RenderLiving<EntitySeagull> {


	private final static ResourceLocation SKIN =
			new ResourceLocation(Birds.MODID,
					"textures/entity/birds/seagull.png");
	
	public RenderSeagull(RenderManager manager) {
		super(manager, new ModelSeagull(), 0.3f);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntitySeagull entity) {
		return SKIN;
	}
}
