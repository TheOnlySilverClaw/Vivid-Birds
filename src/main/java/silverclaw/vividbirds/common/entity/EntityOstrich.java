package silverclaw.vividbirds.common.entity;

import com.google.common.base.Predicate;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIEatGrass;
import net.minecraft.entity.ai.EntityAIFollowParent;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITargetNonTamed;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import silverclaw.vividbirds.common.BirdItem;
import silverclaw.vividbirds.common.FeatherVariant;

public class EntityOstrich extends EntityTameable {
	
	public EntityOstrich(World worldObj) {

		super(worldObj);
		setSize(1.5f, 2.0f);
		stepHeight = 1.2f;
			
		tasks.addTask(0, new EntityAIAvoidEntity<EntityLivingBase>(
				this, EntityLivingBase.class,
				new Predicate<EntityLivingBase>() {

			@Override
			public boolean apply(EntityLivingBase entity) {
				
				if(entity instanceof EntityLivingBase) {
					
					ItemStack held = entity.getHeldItem();
					
					if(held != null) {
						Item item = held.getItem();
						if(item != null) {
							return item instanceof ItemSword
									|| item instanceof ItemBow
									|| item == Items.blaze_rod;
						}
					} else {
						return entity instanceof EntityMob
								&& 	(entity.getHealth() > getHealth() 
										|| getHealth() < getMaxHealth() / 2);
					}
				}
				return true;
			}
		}, 8f, 1.2, 1.5));
		tasks.addTask(1, new EntityAISwimming(this));
		tasks.addTask(3, new EntityAIEatGrass(this));
		tasks.addTask(2, new EntityAILeapAtTarget(this, 0.6f));
		tasks.addTask(3, new EntityAIAttackOnCollide(this, EntityLivingBase.class, 1.6f, true));
		tasks.addTask(4, new EntityAITempt(this, 1.0, Items.wheat, false));
		tasks.addTask(4, new EntityAITempt(this, 1.05, BirdItem.BREADCRUMBS.getInstance(), false));
		tasks.addTask(4, new EntityAITempt(this, 1.1, Items.bread, false));
		tasks.addTask(4, new EntityAITempt(this, 1.2, Item.getItemFromBlock(Blocks.hay_block), false));
		tasks.addTask(5, new EntityAIMate(this, 1.1));
		tasks.addTask(5, new EntityAIFollowParent(this, 1.2));
		tasks.addTask(5, new EntityAIPanic(this, 1.5));
		tasks.addTask(7, new EntityAIWander(this, 1.0));
		tasks.addTask(10, new EntityAILookIdle(this));
		
		targetTasks.addTask(0, new EntityAIHurtByTarget(
				this, true, EntityLivingBase.class));
		targetTasks.addTask(5, new EntityAITargetNonTamed<EntityMob>(
				this, EntityMob.class, true, new Predicate<EntityMob>() {
			
			@Override
			public boolean apply(EntityMob entity) {

				return getHealth() > getMaxHealth() / 2;
			}
		}));
	}

	@Override
	protected void entityInit() {
		
		super.entityInit();
		dataWatcher.addObject(13, Byte.valueOf((byte) 0));
	}
	
	public boolean isSaddled() {
		
		return dataWatcher.getWatchableObjectByte(13) == 1;
	}
	
	public void setSaddled(boolean saddled) {
		
		dataWatcher.updateObject(13, Byte.valueOf((byte) (saddled ? 1 : 0)));
	}
	
	@Override
	public EntityAgeable createChild(EntityAgeable parent) {
		EntityOstrich child = new EntityOstrich(worldObj);
		child.setGrowingAge(-30000);
		return child;
	}

	@Override
	protected void applyEntityAttributes() {
		
		super.applyEntityAttributes();
		getAttributeMap().registerAttribute(SharedMonsterAttributes.attackDamage);
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(5);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(10);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(16);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.25);
	}
	
	@Override
	public boolean attackEntityAsMob(Entity target) {
		
		DamageSource damage = DamageSource.causeMobDamage(this);
		float amount = (float) getEntityAttribute(
				SharedMonsterAttributes.attackDamage).getAttributeValue();
		return target.attackEntityFrom(damage, amount);
	}
	
	@Override
	protected void dropFewItems(boolean drop, int amount) {
		

		if(drop) dropItem(
				isBurning() ? Items.cooked_beef : Items.beef,
				rand.nextInt(2)
				);
			
		if(rand.nextBoolean()) {
			dropItem(Items.leather, 1);
		} else {
			entityDropItem(new ItemStack(Items.feather, 2, 
					FeatherVariant.OSTRICH.getMetaData()), 0.3f);
		}
		if(isSaddled()) {
			dropItem(Items.saddle, 1);
		}
	}

	@Override
	public boolean isOnLadder() {
		return false;
	}
	

	@Override
	public void onLivingUpdate() {
		
		super.onLivingUpdate();
		
		if(rand.nextInt(10000) == 0) {
			entityDropItem(new ItemStack(Items.feather, rand.nextInt(2),
					FeatherVariant.OSTRICH.getMetaData()), 0.5f);
		}
	}
	
	@Override
	public boolean interact(EntityPlayer entityplayer)	{
		
		if(isChild()) return false;

		ItemStack stack = entityplayer.getHeldItem();
		Item item = (stack == null ? null : stack.getItem());
				
		// empty hands?
		if(item == null || stack.stackSize < 1) {
			if(isSaddled()) {
				if(riddenByEntity == null) {
					if(entityplayer.isSneaking()) {
						if(!worldObj.isRemote) {
							dropItem(Items.saddle, 1);
							setSaddled(false);
						}
					} else {
						entityplayer.mountEntity(this);
					}
				}
			}
			return true;
		}
		
		if(item == Items.wheat) {
			if(getHealth() < getMaxHealth()) {
				heal(1);
				//handleHealthUpdate((byte) 0);
				stack.stackSize--;
				return true;
			}
		}

		if(item == Items.saddle) {
			stack.stackSize--;
			setSaddled(true);
			setTamed(true);
			return true;
		}
		
		return super.interact(entityplayer);
	}
	
	@Override
    public void moveEntityWithHeading(float strafing, float forward) {
    	
		if(isSaddled() && riddenByEntity instanceof EntityLivingBase) {
			
			EntityLivingBase rider = (EntityLivingBase) riddenByEntity;
			
        	prevRotationYaw = rotationYaw = rider.rotationYaw;
        	rotationPitch = riddenByEntity.rotationPitch * 0.5F;
        	setRotation(rotationYaw, rotationPitch);
        	rotationYawHead = renderYawOffset = rotationYaw;
        	
            strafing = rider.moveStrafing * 0.5f;
            forward = rider.moveForward;
            setSprinting(true);
           
		}
		setSprinting(forward > 0.5f);
		setAIMoveSpeed((float) getEntityAttribute(
				SharedMonsterAttributes.movementSpeed).getBaseValue());
        super.moveEntityWithHeading(strafing, forward);

    }

	
	@Override
	public void eatGrassBonus() {
		super.eatGrassBonus();
		heal(0.25f);
	}

	@Override
	public double getMountedYOffset() {
		return 1.55;
	}
	
	@Override
	public void readEntityFromNBT(NBTTagCompound compound) {
		
		super.readEntityFromNBT(compound);
		setSaddled(compound.getBoolean("Saddled"));
	}
	
	@Override
	public void writeEntityToNBT(NBTTagCompound compound) {
		
		super.writeEntityToNBT(compound);
		compound.setBoolean("Saddled", isSaddled());
	}
	
	@Override
	public EntityLivingBase getOwner() {
		return null;
	}
	
	@Override
	public boolean isBreedingItem(ItemStack stack) {
		return super.isBreedingItem(stack);
	}
}
