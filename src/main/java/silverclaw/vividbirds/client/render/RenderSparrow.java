package silverclaw.vividbirds.client.render;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import silverclaw.vividbirds.client.model.ModelSongbird;
import silverclaw.vividbirds.common.VividBirds;
import silverclaw.vividbirds.common.entity.songbirds.EntitySparrow;

public class RenderSparrow
	extends RenderLiving<EntitySparrow> {

	private final static ResourceLocation SKIN =
			new ResourceLocation(VividBirds.MODID,
					"textures/entity/birds/sparrow.png");
	
	public RenderSparrow(RenderManager manager) {
		super(manager, new ModelSongbird(), 0.15f);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntitySparrow entity) {
		return SKIN;
	}
}
