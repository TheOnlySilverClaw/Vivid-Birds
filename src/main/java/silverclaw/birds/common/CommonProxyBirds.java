package silverclaw.birds.common;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import silverclaw.birds.common.entity.EntityKiwi;
import silverclaw.birds.common.entity.EntityLyrebird;
import silverclaw.birds.common.entity.EntityOstrich;
import silverclaw.birds.common.entity.EntityPenguin;
import silverclaw.birds.common.entity.EntitySeagull;
import silverclaw.birds.common.entity.EntityVulture;
import silverclaw.birds.common.entity.songbirds.EntitySparrow;

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
		
		GameRegistry.addShapelessRecipe(
				new ItemStack(BirdItem.BREADCRUMBS.getInstance(), 8), Items.bread);
		
		GameRegistry.addSmelting(BirdItem.WILDBIRD_RAW.getInstance(), 
				new ItemStack(BirdItem.WILDBIRD_COOKED.getInstance(), 1), 1);
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
		
		int eggBackground = 0xffffcc;
		
		EntityRegistry.registerModEntity(EntityLyrebird.class,
				"Lyrebird", 0, Birds.instance,
				80, 3, true,
				eggBackground, 0x604020);
		
		EntityRegistry.registerModEntity(EntityVulture.class,
				"Vulture", 1, Birds.instance,
				80, 3, true,
				eggBackground, 0);
		
		EntityRegistry.registerModEntity(EntityOstrich.class,
				"Ostrich", 2, Birds.instance,
				80, 3, true,
				eggBackground, eggBackground);
		
		EntityRegistry.registerModEntity(EntityKiwi.class,
				"Kiwi", 3, Birds.instance,
				80, 3, true,
				eggBackground, 55);
		
		EntityRegistry.registerModEntity(EntitySeagull.class,
				"Seagull", 4, Birds.instance,
				80, 3, true,
				eggBackground, eggBackground * 2);
		
		EntityRegistry.registerModEntity(EntityPenguin.class,
				"Penguin", 5, Birds.instance,
				80, 3, true,
				eggBackground, eggBackground * 3);
		
		EntityRegistry.registerModEntity(EntitySparrow.class,
				"Sparrow", 6, Birds.instance,
				80, 3, true,
				eggBackground * 2, eggBackground);
		
	}
	
	

	
	private final void registerSpawns() {
		

		addSpawn(EntityLyrebird.class, Type.JUNGLE, 6, 6, 3, EnumCreatureType.CREATURE);
		addSpawn(EntityLyrebird.class, Type.DENSE, 3, 2, 4, EnumCreatureType.CREATURE);
		
		addSpawn(EntityVulture.class, Type.DRY, 4, 3, 10, EnumCreatureType.MONSTER);
		addSpawn(EntityVulture.class, Type.HOT, 3, 2, 4, EnumCreatureType.MONSTER);
		
		addSpawn(EntityOstrich.class, Type.SAVANNA, 3, 2, 5, EnumCreatureType.CREATURE);
		
		addSpawn(EntityKiwi.class, Type.DENSE, 3, 2, 6, EnumCreatureType.CREATURE);
		
		addSpawn(EntityPenguin.class, Type.SNOWY, 5, 4, 10, EnumCreatureType.CREATURE);
		addSpawn(EntityPenguin.class, Type.COLD, 3, 4, 10, EnumCreatureType.CREATURE);
		
		addSpawn(EntitySeagull.class, Type.BEACH, 6, 4, 10, EnumCreatureType.CREATURE);
		addSpawn(EntitySeagull.class, Type.OCEAN, 3, 3, 16, EnumCreatureType.CREATURE);
		
		addSpawn(EntitySparrow.class, Type.FOREST, 5, 6, 12, EnumCreatureType.CREATURE);

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
