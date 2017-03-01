package silverclaw.vividbirds.client.render;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import silverclaw.vividbirds.client.model.ModelOstrich;
import silverclaw.vividbirds.common.VividBirds;
import silverclaw.vividbirds.common.entity.EntityOstrich;

public class RenderOstrich
	extends RenderLiving<EntityOstrich> {

	private final static ResourceLocation SKIN =
			new ResourceLocation(VividBirds.MODID,
					"textures/entity/birds/ostrich_black.png");
	
	public RenderOstrich(RenderManager manager) {
		super(manager, new ModelOstrich(), 1);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityOstrich entity) {
		return SKIN;
	}
}
