package silverclaw.birds.client;

import static net.minecraftforge.fml.client.registry.RenderingRegistry.registerEntityRenderingHandler;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.init.Items;
import net.minecraft.util.ResourceLocation;
import silverclaw.birds.client.render.RenderKiwi;
import silverclaw.birds.client.render.RenderLyrebird;
import silverclaw.birds.client.render.RenderOstrich;
import silverclaw.birds.client.render.RenderPenguin;
import silverclaw.birds.client.render.RenderSeagull;
import silverclaw.birds.client.render.RenderSparrow;
import silverclaw.birds.client.render.RenderVulture;
import silverclaw.birds.common.BirdItem;
import silverclaw.birds.common.CommonProxyBirds;
import silverclaw.birds.common.FeatherVariant;
import silverclaw.birds.common.entity.EntityKiwi;
import silverclaw.birds.common.entity.EntityLyrebird;
import silverclaw.birds.common.entity.EntityOstrich;
import silverclaw.birds.common.entity.EntityPenguin;
import silverclaw.birds.common.entity.EntitySeagull;
import silverclaw.birds.common.entity.EntityVulture;
import silverclaw.birds.common.entity.songbirds.EntitySparrow;

public class ClientProxyBirds extends CommonProxyBirds {
	
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
