package silverclaw.birds.client.render;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import silverclaw.birds.client.model.ModelLyrebird;
import silverclaw.birds.common.Birds;
import silverclaw.birds.common.entity.EntityLyrebird;

public class RenderLyrebird
	extends RenderLiving<EntityLyrebird> {
	
	private final static ResourceLocation SKIN =
			new ResourceLocation(Birds.MODID,
					"textures/entity/birds/lyrebird.png");
	
	public RenderLyrebird(RenderManager manager) {
		super(manager, new ModelLyrebird(), 0.3f);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityLyrebird entity) {
		return SKIN;
	}
}
