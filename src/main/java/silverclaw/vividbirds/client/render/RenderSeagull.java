package silverclaw.vividbirds.client.render;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import silverclaw.vividbirds.client.model.ModelSeagull;
import silverclaw.vividbirds.common.VividBirds;
import silverclaw.vividbirds.common.entity.EntitySeagull;

public class RenderSeagull
	extends RenderLiving<EntitySeagull> {


	private final static ResourceLocation SKIN =
			new ResourceLocation(VividBirds.MODID,
					"textures/entity/birds/seagull.png");
	
	public RenderSeagull(RenderManager manager) {
		super(manager, new ModelSeagull(), 0.3f);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntitySeagull entity) {
		return SKIN;
	}
}
