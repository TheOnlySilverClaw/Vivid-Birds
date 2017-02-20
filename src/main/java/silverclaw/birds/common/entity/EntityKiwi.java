package silverclaw.birds.common.entity;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIFleeSun;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import silverclaw.birds.common.BirdItem;

public class EntityKiwi extends EntityPeacefulBird {
	
	public EntityKiwi(World worldObj) {
		
		super(worldObj);
		
		setSize(0.9f, 0.8f);
		
		tasks.addTask(1, new EntityAIAvoidEntity<EntityMob>(
				this, EntityMob.class, 10f, 1.2, 1.45));
		tasks.addTask(1, new EntityAIAvoidEntity<EntityPlayer>(
				this, EntityPlayer.class, 10f, 1.2, 1.45));
		tasks.addTask(1, new EntityAIFleeSun(this, 1.2f));
		tasks.addTask(5, new EntityAITempt(this, 1.15f, Items.melon_seeds, true));

	}

	@Override
	public EntityAgeable createChild(EntityAgeable arg0) {

		EntityKiwi baby = new EntityKiwi(worldObj);
		baby.setGrowingAge(-20000);
		return baby;
	}

	@Override
	public boolean isBreedingItem(ItemStack stack) {
		
		return stack.getItem() instanceof ItemSeeds;
	}
	
	@Override
	protected void dropFewItems(boolean drop, int number) {
		
		if(rand.nextFloat() < 0.1f) dropItem(Items.melon, 1);
		
		dropItem(isBurning() ? BirdItem.WILDBIRD_COOKED.getInstance()
				: BirdItem.WILDBIRD_RAW.getInstance(), rand.nextInt(2));
	}
	
	@Override
    protected void applyEntityAttributes() {
		
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.22);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(4.0);
	}
}
