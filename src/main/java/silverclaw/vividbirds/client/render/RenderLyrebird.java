package silverclaw.vividbirds.client.render;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import silverclaw.vividbirds.client.model.ModelLyrebird;
import silverclaw.vividbirds.common.VividBirds;
import silverclaw.vividbirds.common.entity.EntityLyrebird;

public class RenderLyrebird
	extends RenderLiving<EntityLyrebird> {
	
	private final static ResourceLocation SKIN =
			new ResourceLocation(VividBirds.MODID,
					"textures/entity/birds/lyrebird.png");
	
	public RenderLyrebird(RenderManager manager) {
		super(manager, new ModelLyrebird(), 0.3f);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityLyrebird entity) {
		return SKIN;
	}
}
