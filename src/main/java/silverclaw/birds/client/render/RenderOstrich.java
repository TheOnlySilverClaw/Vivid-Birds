package silverclaw.birds.client.render;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import silverclaw.birds.client.model.ModelOstrich;
import silverclaw.birds.common.Birds;
import silverclaw.birds.common.entity.EntityOstrich;

public class RenderOstrich
	extends RenderLiving<EntityOstrich> {

	private final static ResourceLocation SKIN =
			new ResourceLocation(Birds.MODID,
					"textures/entity/birds/ostrich_black.png");
	
	public RenderOstrich(RenderManager manager) {
		super(manager, new ModelOstrich(), 1);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityOstrich entity) {
		return SKIN;
	}
}
