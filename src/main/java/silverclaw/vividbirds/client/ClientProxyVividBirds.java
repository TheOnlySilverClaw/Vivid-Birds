package silverclaw.vividbirds.client;

import static net.minecraftforge.fml.client.registry.RenderingRegistry.registerEntityRenderingHandler;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.init.Items;
import net.minecraft.util.ResourceLocation;
import silverclaw.vividbirds.client.render.RenderKiwi;
import silverclaw.vividbirds.client.render.RenderLyrebird;
import silverclaw.vividbirds.client.render.RenderOstrich;
import silverclaw.vividbirds.client.render.RenderPenguin;
import silverclaw.vividbirds.client.render.RenderSeagull;
import silverclaw.vividbirds.client.render.RenderSparrow;
import silverclaw.vividbirds.client.render.RenderVulture;
import silverclaw.vividbirds.common.BirdItem;
import silverclaw.vividbirds.common.CommonProxyVividBirds;
import silverclaw.vividbirds.common.FeatherVariant;
import silverclaw.vividbirds.common.entity.EntityKiwi;
import silverclaw.vividbirds.common.entity.EntityLyrebird;
import silverclaw.vividbirds.common.entity.EntityOstrich;
import silverclaw.vividbirds.common.entity.EntityPenguin;
import silverclaw.vividbirds.common.entity.EntitySeagull;
import silverclaw.vividbirds.common.entity.EntityVulture;
import silverclaw.vividbirds.common.entity.songbirds.EntitySparrow;

public class ClientProxyVividBirds extends CommonProxyVividBirds {
	
	@Override
	public void registerRenderers() {
			
		registerEntityRenderingHandler(
				EntityLyrebird.class, RenderLyrebird::new);
		
		registerEntityRenderingHandler(
				EntityVulture.class, RenderVulture::new);
		
		registerEntityRenderingHandler(
				EntityOstrich.class, RenderOstrich::new);
		
		registerEntityRenderingHandler(
				EntityKiwi.class, RenderKiwi::new);
		
		registerEntityRenderingHandler(
				EntitySeagull.class,RenderSeagull::new);
		
		registerEntityRenderingHandler(
				EntityPenguin.class, RenderPenguin::new);
		
		//RenderingRegistry.registerEntityRenderingHandler(EntitySongBird.class, 
			//	new RenderSongbird(manager, new ModelSongbird(), 0.2f));
		
		registerEntityRenderingHandler(
				EntitySparrow.class,RenderSparrow::new);
	}

	@Override
	public void registerItemsResources() {
		

		ItemModelMesher mesher = Minecraft.getMinecraft()
				.getRenderItem().getItemModelMesher();
		
		for(BirdItem item : BirdItem.values()) {
			
			mesher.register(item.getInstance(), 0,
					new ModelResourceLocation(
							item.getResourceName(), "inventory"));
		}
		
		registerFeathers();
	}

	private void registerFeathers() {
		
		//ItemModelMesher mesher = Minecraft.getMinecraft().getRenderItem().getItemModelMesher();

		ResourceLocation[] itemNames = new ResourceLocation
				[FeatherVariant.values().length + 1];
		
		itemNames [0] = new ModelResourceLocation("feather", "inventory");
		
		int i = 1;
		
		for(FeatherVariant feather : FeatherVariant.values()) {
			
			itemNames[i++] = new ModelResourceLocation(
							feather.getResourceName(), "inventory");
		}
		ModelBakery.registerItemVariants(Items.feather, itemNames);
	}
}
