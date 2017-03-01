package silverclaw.birds.client.render;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import silverclaw.birds.client.model.ModelPenguin;
import silverclaw.birds.common.Birds;
import silverclaw.birds.common.entity.EntityPenguin;

public class RenderPenguin
	extends RenderLiving<EntityPenguin> {
	
	private final static ResourceLocation SKIN =
			new ResourceLocation(Birds.MODID,
					"textures/entity/birds/penguin.png");
	
	public RenderPenguin(RenderManager manager) {
		super(manager, new ModelPenguin(), 0.4f);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityPenguin entity) {
		return SKIN;
	}
}
