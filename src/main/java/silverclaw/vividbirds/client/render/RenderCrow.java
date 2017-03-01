package silverclaw.vividbirds.client.render;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import silverclaw.vividbirds.client.model.ModelCrow;
import silverclaw.vividbirds.common.VividBirds;
import silverclaw.vividbirds.common.entity.EntityCrow;

public class RenderCrow extends RenderLiving<EntityCrow> {

	private final static ResourceLocation SKIN =
			new ResourceLocation(VividBirds.MODID,
					"textures/entity/birds/raven.png");
	
	public RenderCrow(RenderManager manager) {
		super(manager, new ModelCrow(), 0.2f);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityCrow entity) {
		return SKIN;
	}
}
