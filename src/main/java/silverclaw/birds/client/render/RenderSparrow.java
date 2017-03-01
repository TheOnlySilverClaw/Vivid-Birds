package silverclaw.birds.client.render;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import silverclaw.birds.client.model.ModelSongbird;
import silverclaw.birds.common.Birds;
import silverclaw.birds.common.entity.songbirds.EntitySparrow;

public class RenderSparrow
	extends RenderLiving<EntitySparrow> {

	private final static ResourceLocation SKIN =
			new ResourceLocation(Birds.MODID,
					"textures/entity/birds/sparrow.png");
	
	public RenderSparrow(RenderManager manager) {
		super(manager, new ModelSongbird(), 0.15f);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntitySparrow entity) {
		return SKIN;
	}
}
