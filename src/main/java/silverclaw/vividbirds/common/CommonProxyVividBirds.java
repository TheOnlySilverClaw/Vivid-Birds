package silverclaw.vividbirds.common;

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
import silverclaw.vividbirds.common.entity.EntityKiwi;
import silverclaw.vividbirds.common.entity.EntityLyrebird;
import silverclaw.vividbirds.common.entity.EntityOstrich;
import silverclaw.vividbirds.common.entity.EntityPenguin;
import silverclaw.vividbirds.common.entity.EntitySeagull;
import silverclaw.vividbirds.common.entity.EntityVulture;
import silverclaw.vividbirds.common.entity.songbirds.EntitySparrow;

public class CommonProxyVividBirds {

	
	protected ConfigHandler handler;

	public void preInit(FMLPreInitializationEvent event) {
		
		handler = new ConfigHandler(event);
		
		handler.startConfig();
		
		registerBirds();
		registerSpawns();
		registerItems();
		registerRecipes();
		registerRenderers();

		handler.endConfig();
	}


	private void registerRecipes() {
		
		GameRegistry.addShapelessRecipe(
				new ItemStack(BirdItem.BREADCRUMBS.getInstance(), 8), Items.bread);
		
		GameRegistry.addSmelting(BirdItem.WILDBIRD_RAW.getInstance(), 
				new ItemStack(BirdItem.WILDBIRD_COOKED.getInstance(), 1), 1);
	}


	public void init(FMLInitializationEvent event) {
		
		registerItemsResources();
	}
	
	private void registerItems() {
		
		for(BirdItem item : BirdItem.values()) {
			
			GameRegistry.registerItem(item.getInstance(), item.getRawName());
		}
	}

	private final void registerBirds() {
		
		EntityRegistry.registerModEntity(EntityLyrebird.class,
				"Lyrebird", 0, VividBirds.instance,
				80, 3, true,
				0xffffcc, 0x604020);
		
		EntityRegistry.registerModEntity(EntityVulture.class,
				"Vulture", 1, VividBirds.instance,
				80, 3, true,
				0xffffcc, 0);
		
		EntityRegistry.registerModEntity(EntityOstrich.class,
				"Ostrich", 2, VividBirds.instance,
				80, 3, true,
				0xffffcc, 0xf1f1f1);
		
		EntityRegistry.registerModEntity(EntityKiwi.class,
				"Kiwi", 3, VividBirds.instance,
				80, 3, true,
				0xf1f1f1, 0xf2f2f2);
		
		EntityRegistry.registerModEntity(EntitySeagull.class,
				"Seagull", 4, VividBirds.instance,
				80, 3, true,
				0x996633, 0x392613);
		
		EntityRegistry.registerModEntity(EntityPenguin.class,
				"Penguin", 5, VividBirds.instance,
				80, 3, true,
				0xffffcc, 0xffffcc);
		
		EntityRegistry.registerModEntity(EntitySparrow.class,
				"Sparrow", 6, VividBirds.instance,
				80, 3, true,
				0xffffcc * 2, 0xffffcc);
		
	}
	
	private final void registerSpawns() {
		

		addSpawn(EntityLyrebird.class, Type.JUNGLE, 10, 6, 3, EnumCreatureType.CREATURE);
		addSpawn(EntityLyrebird.class, Type.DENSE, 6, 2, 4, EnumCreatureType.CREATURE);
		
		addSpawn(EntityVulture.class, Type.DRY, 10, 3, 10, EnumCreatureType.MONSTER);
		addSpawn(EntityVulture.class, Type.HOT, 8, 2, 4, EnumCreatureType.MONSTER);
		
		addSpawn(EntityOstrich.class, Type.SAVANNA, 10, 2, 5, EnumCreatureType.CREATURE);
		
		addSpawn(EntityKiwi.class, Type.DENSE, 8, 2, 6, EnumCreatureType.CREATURE);
		
		addSpawn(EntityPenguin.class, Type.SNOWY, 8, 4, 10, EnumCreatureType.CREATURE);
		addSpawn(EntityPenguin.class, Type.COLD, 6, 4, 10, EnumCreatureType.CREATURE);
		
		addSpawn(EntitySeagull.class, Type.BEACH, 12, 4, 10, EnumCreatureType.CREATURE);
		addSpawn(EntitySeagull.class, Type.OCEAN, 8, 3, 16, EnumCreatureType.CREATURE);
		
		addSpawn(EntitySparrow.class, Type.FOREST, 10, 6, 12, EnumCreatureType.CREATURE);

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
