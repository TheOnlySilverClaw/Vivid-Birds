package silverclaw.birds.common;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.server.S38PacketPlayerListItem.AddPlayerData;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.BiomeManager.BiomeType;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import silverclaw.birds.common.entity.EntityCrow;
import silverclaw.birds.common.entity.EntityKiwi;
import silverclaw.birds.common.entity.EntityLyrebird;
import silverclaw.birds.common.entity.EntityOstrich;
import silverclaw.birds.common.entity.EntityPenguin;
import silverclaw.birds.common.entity.EntitySeagull;
import silverclaw.birds.common.entity.EntityVulture;
import silverclaw.birds.common.item.ItemOstrichEgg;

public class CommonProxyBirds {

	
	protected ConfigHandler handler;

	public void preInit(FMLPreInitializationEvent event) {
		
		handler = new ConfigHandler(event);
		
		handler.startConfig();
		
		registerBirds();
		registerSpawns();
		registerItems();
		registerRecipes();
		
		handler.endConfig();
	}


	private void registerRecipes() {
		
		GameRegistry.addShapelessRecipe(new ItemStack(BirdItem.BREADCRUMBS.getInstance(), 8), Items.bread);
		
	}


	public void init(FMLInitializationEvent event) {
		
		registerRenderers();
		registerItemsResources();
	}
	
	private void registerItems() {
		
		for(BirdItem item : BirdItem.values()) {
			
			GameRegistry.registerItem(item.getInstance(), item.getRawName());
		}
	}

	private final void registerBirds() {
		
		registerEntity(EntityLyrebird.class, 123);
		registerEntity(EntityVulture.class, 234);
		registerEntity(EntityOstrich.class, 456);
		registerEntity(EntityKiwi.class, 567);
		registerEntity(EntityCrow.class, 789);
		registerEntity(EntitySeagull.class, 479);
		registerEntity(EntityPenguin.class, 258);
	}
	
	private final void registerEntity(Class<? extends EntityLiving> entityClass, int eggColor) {
		
		EntityRegistry.registerGlobalEntityID(entityClass, Birds.getEntityName(entityClass), 
				EntityRegistry.findGlobalUniqueEntityId(), Birds.EGG_WHITE, eggColor);
	}
	

	
	private final void registerSpawns() {
		

		addSpawn(EntityLyrebird.class, Type.JUNGLE, 5, 6, 3, EnumCreatureType.CREATURE);
		addSpawn(EntityLyrebird.class, Type.DENSE, 2, 2, 4, EnumCreatureType.CREATURE);
		
		addSpawn(EntityVulture.class, Type.DRY, 3, 3, 10, EnumCreatureType.CREATURE);
		addSpawn(EntityVulture.class, Type.HOT, 2, 2, 4, EnumCreatureType.MONSTER);
		
		addSpawn(EntityOstrich.class, Type.SAVANNA, 3, 2, 5, EnumCreatureType.CREATURE);
		
		addSpawn(EntityKiwi.class, Type.DENSE, 3, 2, 6, EnumCreatureType.MONSTER);
		
		addSpawn(EntityPenguin.class, Type.SNOWY, 5, 4, 10, EnumCreatureType.CREATURE);
		addSpawn(EntityPenguin.class, Type.COLD, 2, 4, 10, EnumCreatureType.CREATURE);
		
		addSpawn(EntitySeagull.class, Type.BEACH, 3, 4, 10, EnumCreatureType.CREATURE);
		addSpawn(EntitySeagull.class, Type.OCEAN, 2, 3, 6, EnumCreatureType.CREATURE);

	}

	private void addSpawn(Class<? extends EntityLiving> entityClass, Type biomeType,
			int defaultProbability, int defaultMin, int defaultMax, EnumCreatureType creatureType) {
		
		int [] values = handler.getSpawnConfig(entityClass, biomeType, 
				defaultProbability, defaultMin, defaultMax);
		
		EntityRegistry.addSpawn(entityClass, values[0], values[1], values[2], 
				creatureType, BiomeDictionary.getBiomesForType(biomeType));
	}
	
	protected void registerRenderers() {}

	protected void registerItemsResources() {}
}
