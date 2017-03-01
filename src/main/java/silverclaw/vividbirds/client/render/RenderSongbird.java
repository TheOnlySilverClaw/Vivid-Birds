package silverclaw.vividbirds.client.render;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import silverclaw.vividbirds.client.model.ModelSongbird;
import silverclaw.vividbirds.common.VividBirds;
import silverclaw.vividbirds.common.entity.songbirds.EntitySongBird;

public class RenderSongbird
	extends RenderLiving<EntitySongBird> {

	private final static ResourceLocation SKIN =
			new ResourceLocation(VividBirds.MODID,
					"textures/entity/birds/songbird.png");
	
	public RenderSongbird(RenderManager manager) {
		super(manager, new ModelSongbird(), 0.2f);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntitySongBird entity) {
		return SKIN;
	}
}
