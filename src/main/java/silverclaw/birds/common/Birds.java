package silverclaw.birds.common;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Birds.MODID, version = "1.0.0", name="Vivid Birds")
public class Birds {
	
	public static final String MODID = "vivid_birds";
	
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
		
		MinecraftForge.EVENT_BUS.register(instance);
		proxy.init(event);
	}
}
