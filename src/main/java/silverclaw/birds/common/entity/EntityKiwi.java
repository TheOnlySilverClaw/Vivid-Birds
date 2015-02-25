package silverclaw.birds.common.entity;

import silverclaw.birds.common.Birds;

import com.google.common.base.Predicate;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIFleeSun;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class EntityKiwi extends EntityPeacefulBird {
	
	public EntityKiwi(World worldObj) {
		
		super(worldObj);
		
		setSize(0.9f, 0.8f);
		setGrowingAge(rand.nextInt(5000) - 1000);
		
		tasks.addTask(0, new EntityAIAvoidEntity(this, new Predicate<Entity>() {
			
			@Override
			public boolean apply(Entity entity) {
				
				return entity instanceof EntityMob || entity instanceof EntityPlayer;
			}
		}, 10f, 1.2, 1.4));

		tasks.addTask(0, new EntityAIFleeSun(this, 1.1f));
		tasks.addTask(5, new EntityAITempt(this, 1.15f, Items.melon_seeds, true));

	}

	@Override
	public EntityAgeable createChild(EntityAgeable arg0) {

		EntityKiwi baby = new EntityKiwi(worldObj);
		baby.setGrowingAge(-4000);
		return baby;
	}

	@Override
	public boolean isBreedingItem(ItemStack stack) {
		
		return stack.getItem() instanceof ItemSeeds;
	}
	
	@Override
	protected void dropFewItems(boolean drop, int number) {
		
		if(rand.nextFloat() < 0.2f) dropItem(Items.melon, 1);
		
		dropItem(isBurning() ? Items.cooked_chicken : Items.chicken, rand.nextInt(2));
	}
	
	@Override
    protected void applyEntityAttributes() {
		
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.22);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(4.0);
	}
}
