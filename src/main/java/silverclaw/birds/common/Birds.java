package silverclaw.birds.common;

import silverclaw.birds.common.entity.EntityVulture;
import silverclaw.birds.common.item.ItemOstrichEgg;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = Birds.MODID, version = "0.2.1", name="Bird Mod")
public class Birds {
	
	public static final String MODID = "birdmod";
	
	public static final CreativeTabs tabBirds = new CreativeTabs("Birds") {
		
		@Override
		public Item getTabIconItem() {
			
			return Items.feather;
		}
	};
	

	public final static int EGG_WHITE = 0xfffacd;

	
	@Mod.Instance(MODID)
	public static Birds instance;
	
	@SidedProxy(
		clientSide = "silverclaw.birds.client.ClientProxyBirds",
		serverSide = "silverclaw.birds.common.CommonProxyBirds"
	)
	public static CommonProxyBirds proxy;
	
	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {

		proxy.preInit(event);
	}
	
	@Mod.EventHandler
	public void Init(FMLInitializationEvent event) {
		
		FMLCommonHandler.instance().bus().register(instance);
		proxy.init(event);
	}
	
	// utility
	public final static String getEntityName(Class<? extends Entity> entityClass) {
		
		return entityClass.getSimpleName().replaceFirst("Entity", "");
	}
}
