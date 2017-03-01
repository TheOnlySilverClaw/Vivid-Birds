package silverclaw.vividbirds.client.render;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import silverclaw.vividbirds.client.model.ModelVulture;
import silverclaw.vividbirds.common.VividBirds;
import silverclaw.vividbirds.common.entity.EntityVulture;

public class RenderVulture
	extends RenderLiving<EntityVulture> {

	private static final ResourceLocation SKIN =
			new ResourceLocation(VividBirds.MODID,
					"textures/entity/birds/vulture.png");

	public RenderVulture(RenderManager manager) {
		super(manager, new ModelVulture(), 0.5f);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityVulture entity) {
		return SKIN;
	}
}
