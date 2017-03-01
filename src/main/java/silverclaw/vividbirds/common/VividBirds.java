package silverclaw.vividbirds.common;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = VividBirds.MODID, version = "1.0.0", name="Vivid Birds")
public class VividBirds {
	
	public static final String MODID = "vivid_birds";
	
	@Mod.Instance(MODID)
	public static VividBirds instance;
	
	@SidedProxy(
		clientSide = "silverclaw.vividbirds.client.ClientProxyBirds",
		serverSide = "silverclaw.vividbirds.common.CommonProxyBirds"
	)
	public static CommonProxyVividBirds proxy;
	
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
