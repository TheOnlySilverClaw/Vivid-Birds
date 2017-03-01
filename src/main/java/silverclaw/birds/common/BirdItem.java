package silverclaw.birds.common;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;

public enum BirdItem implements NamedResource {

	BREADCRUMBS(new ItemFood(0, false).setUnlocalizedName("breadcrumbs")),
	WILDBIRD_RAW(new ItemFood(3, true).setUnlocalizedName("wildbird_raw")),
	WILDBIRD_COOKED(new ItemFood(4, true).setUnlocalizedName("wildbird_cooked"));

	private final Item instance;
	private final String rawName;

	private BirdItem(String unlocalizedName) {
		
		this(new Item().setUnlocalizedName(unlocalizedName));
	}

	private BirdItem(Item instance) {
		
		this.instance = instance.setCreativeTab(CreativeTabs.tabFood);
		this.rawName = instance.getUnlocalizedName().replaceFirst("item.", "");
	}
	
	public Item getInstance() {
		
		return instance;
	}
	
	public String getRawName() {
		
		return rawName;
	}
	
	public String getUnlocalizedName() {
		
		return instance.getUnlocalizedName();
	}

	@Override
	public String getResourceName() {
		
		return Birds.MODID + ":" + getRawName();
	}
}
