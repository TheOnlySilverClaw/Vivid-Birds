package silverclaw.birds.common.entity;

import silverclaw.birds.common.BirdItem;
import silverclaw.birds.common.Birds;
import silverclaw.birds.common.entity.ai.EntityAIPickupItem;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIFollowParent;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.init.Items;
import net.minecraft.world.World;

public abstract class EntityPeacefulBird extends EntityAnimal {

	public EntityPeacefulBird(World worldObj) {
		
		super(worldObj);
		
		tasks.addTask(1, new EntityAIPanic(this, 1.3f));
		tasks.addTask(2, new EntityAISwimming(this));
		tasks.addTask(3, new EntityAIWander(this, 1.15f));
		tasks.addTask(4, new EntityAIMate(this, 1.05f));
		tasks.addTask(4, new EntityAITempt(this, 1.15f, BirdItem.BREADCRUMBS.getInstance(), true));
		tasks.addTask(5, new EntityAIFollowParent(this, 1.1f));
		tasks.addTask(6, new EntityAILookIdle(this));
		
		targetTasks.addTask(5, new EntityAIPickupItem(this, BirdItem.BREADCRUMBS.getInstance(), 10, 1.05f, 0.15f));

	}

	@Override
	public abstract EntityAgeable createChild(EntityAgeable otherBird);


	@Override
	public void fall(float p_fall_1_, float p_fall_2_) {}
	
	@Override
    protected void applyEntityAttributes() {
		
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.25);
	}
}
