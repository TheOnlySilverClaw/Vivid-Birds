package silverclaw.birds.client.render;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import silverclaw.birds.client.model.ModelVulture;
import silverclaw.birds.common.Birds;
import silverclaw.birds.common.entity.EntityVulture;

public class RenderVulture
	extends RenderLiving<EntityVulture> {

	private static final ResourceLocation SKIN =
			new ResourceLocation(Birds.MODID,
					"textures/entity/birds/vulture.png");

	public RenderVulture(RenderManager manager) {
		super(manager, new ModelVulture(), 0.5f);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityVulture entity) {
		return SKIN;
	}
}
