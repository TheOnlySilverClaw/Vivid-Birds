package silverclaw.vividbirds.client.render;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import silverclaw.vividbirds.client.model.ModelPenguin;
import silverclaw.vividbirds.common.VividBirds;
import silverclaw.vividbirds.common.entity.EntityPenguin;

public class RenderPenguin
	extends RenderLiving<EntityPenguin> {
	
	private final static ResourceLocation SKIN =
			new ResourceLocation(VividBirds.MODID,
					"textures/entity/birds/penguin.png");
	
	public RenderPenguin(RenderManager manager) {
		super(manager, new ModelPenguin(), 0.4f);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityPenguin entity) {
		return SKIN;
	}
}
