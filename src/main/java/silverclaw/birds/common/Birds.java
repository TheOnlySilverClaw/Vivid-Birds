package silverclaw.birds.common;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Birds.MODID, version = "1.0.0-snapshot1", name="Bird Mod")
public class Birds {
	
	public static final String MODID = "birdmod";
	
	public static final CreativeTabs tabBirds = new CreativeTabs("Birds") {
		
		@Override
		public Item getTabIconItem() {
			
			return Items.feather;
		}
	};
	
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
}
