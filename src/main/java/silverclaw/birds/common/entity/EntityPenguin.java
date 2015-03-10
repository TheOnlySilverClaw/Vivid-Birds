package silverclaw.birds.common.entity;

import silverclaw.birds.common.BirdItem;
import silverclaw.birds.common.Birds;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.init.Items;
import net.minecraft.item.ItemFishFood;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenBase.TempCategory;

public class EntityPenguin extends EntityPeacefulBird {

	public EntityPenguin(World worldObj) {
		
		super(worldObj);
		setSize(0.7f, 1);
		setGrowingAge(rand.nextInt(4000) - 1000);
		
		tasks.addTask(5, new EntityAITempt(this, 1.2f, Items.fish, false));
	}

	@Override
	public EntityAgeable createChild(EntityAgeable other) {

		EntityPenguin penguin = new EntityPenguin(worldObj);
		penguin.setGrowingAge(-36000 - rand.nextInt(2000));
		return penguin;
	}
	
	@Override
	protected void dropFewItems(boolean drop, int number) {
		
		dropItem(isBurning() ? BirdItem.WILDBIRD_COOKED.getInstance() 
				: BirdItem.WILDBIRD_RAW.getInstance(), rand.nextInt(4));
	}

	
	@Override
    protected void applyEntityAttributes() {
		
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.2);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(6.0);
	}
	
	@Override
	public boolean isBreedingItem(ItemStack stack) {
		
		return stack.getItem() instanceof ItemFishFood;
	}
	
	@Override
	public void onLivingUpdate() {
		
		super.onLivingUpdate();
		
		if(rand.nextInt(50) == 0 && worldObj.getBiomeGenForCoords(getPosition()).getTempCategory()
				== TempCategory.WARM && worldObj.getLight(getPosition()) > 8) {
			damageEntity(DamageSource.inFire, 0.5f);
			handleHealthUpdate((byte) 2);
		}
	}
}
