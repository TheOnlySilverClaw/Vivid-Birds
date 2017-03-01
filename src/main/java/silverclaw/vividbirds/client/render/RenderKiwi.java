package silverclaw.vividbirds.client.render;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import silverclaw.vividbirds.client.model.ModelKiwi;
import silverclaw.vividbirds.common.VividBirds;
import silverclaw.vividbirds.common.entity.EntityKiwi;

public class RenderKiwi
	extends RenderLiving<EntityKiwi> {

	private final static ResourceLocation SKIN =
			new ResourceLocation(VividBirds.MODID,
					"textures/entity/birds/kiwi.png");
	
	public RenderKiwi(RenderManager manager) {
		super(manager, new ModelKiwi(), 0.2f);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityKiwi entity) {
		return SKIN;
	}
}
