package silverclaw.birds.common.entity;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import silverclaw.birds.common.FeatherVariant;
import silverclaw.birds.common.entity.ai.EntityAIFlyingBase;
import silverclaw.birds.common.entity.ai.EntityAIPickupItem;

public class EntitySeagull extends EntityPeacefulBird {

	public EntitySeagull(World worldObj) {
		
		super(worldObj);
		setSize(0.8f, 0.9f);
		setGrowingAge(rand.nextInt(10000) - 1000);
		
		tasks.addTask(5, new EntityAITempt(this, 1.15f, Items.fish, false));
		tasks.addTask(2, new EntityAIFlyingBase(this, 1.3f, 800, 400, 80));

		targetTasks.addTask(1, new EntityAIPickupItem(this, Items.fish, 8, 1.1f, 0.3f));
	

}

	@Override
	public EntityAgeable createChild(EntityAgeable other) {

		EntitySeagull seagull = new EntitySeagull(worldObj);
		seagull.setGrowingAge(-20000 + rand.nextInt(1000));
		return seagull;
	}
	
	@Override
    protected void applyEntityAttributes() {
		
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.25);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(5.0);
	}
	
	@Override
	public boolean isBreedingItem(ItemStack stack) {
		
		return stack.getItem() == Items.fish;
	}
	
	@Override
	protected void dropFewItems(boolean drop, int amount) {
		
		entityDropItem(new ItemStack(Items.feather, rand.nextInt(2), 
				FeatherVariant.SEAGULL.getMetaData()), 0.4f);
		
		if(rand.nextFloat() < 0.4f) {
			dropItem(Items.fish, 1);
		}
	}
	
	@Override
	protected String getLivingSound() {
		return "birdmod:seagull";
	}
}
