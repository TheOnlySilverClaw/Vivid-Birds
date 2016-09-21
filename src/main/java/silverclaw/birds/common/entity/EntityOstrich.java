package silverclaw.birds.common.entity;

import silverclaw.birds.common.BirdItem;
import silverclaw.birds.common.Birds;
import silverclaw.birds.common.FeatherVariant;
import silverclaw.birds.common.entity.ai.EntityAILayEggInNest;

import com.google.common.base.Predicate;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIControlledByPlayer;
import net.minecraft.entity.ai.EntityAIEatGrass;
import net.minecraft.entity.ai.EntityAIFollowParent;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAIRunAroundLikeCrazy;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITargetNonTamed;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovementInput;
import net.minecraft.util.MovementInputFromOptions;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;

public class EntityOstrich extends EntityTameable {

	private static final int EGG_TIMER = 50000;
	
	private float battleExperience;
	private int timeUntilNextEgg;
		
	private boolean isJumping;
	
	public EntityOstrich(World worldObj) {
		
		super(worldObj);
		setSize(1.5f, 2.0f);
		battleExperience = 0.0f;
		timeUntilNextEgg = EGG_TIMER;
		isJumping = false;
		stepHeight = 1.2f;
			
		tasks.addTask(0, new EntityAIAvoidEntity(this, new Predicate<Entity>() {

			@Override
			public boolean apply(Entity entity) {
				
				if(entity instanceof EntityLivingBase) {
					
					EntityLivingBase living = (EntityLivingBase) entity;
					ItemStack held = living.getHeldItem();
					
					if(held != null) {
						Item item = held.getItem();
						if(item != null) {
							return item instanceof ItemSword
									|| item instanceof ItemBow
									|| item == Items.blaze_rod;
						}
					} else {
						return living instanceof EntityMob
								&& 	(living.getHealth() > getHealth() 
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
		tasks.addTask(5, new EntityAIFollowParent(this, 1.2));
		tasks.addTask(5, new EntityAILayEggInNest(this));
		tasks.addTask(5, new EntityAIPanic(this, 1.5));
		tasks.addTask(7, new EntityAIWander(this, 1.0));
		tasks.addTask(10, new EntityAILookIdle(this));
		
		
		targetTasks.addTask(0, new EntityAIHurtByTarget(
				this, true, EntityLivingBase.class));
		targetTasks.addTask(5, new EntityAITargetNonTamed(
				this, EntityMob.class, true, new Predicate<Entity>() {
			
			@Override
			public boolean apply(Entity entity) {

				return entity instanceof EntityMob
						&& getHealth() > getMaxHealth() / 2;
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
		return null;
	}

	@Override
	protected void applyEntityAttributes() {
		
		super.applyEntityAttributes();
		getAttributeMap().registerAttribute(SharedMonsterAttributes.attackDamage);
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(4);
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(10);
		getEntityAttribute(SharedMonsterAttributes.maxHealth)
		.setBaseValue(isTamed() ? 20 : 16);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.25);
	}
	
	@Override
	protected void dropFewItems(boolean drop, int amount) {
		

		if(drop) dropItem(isBurning() ? Items.cooked_beef : Items.beef, 1);
			
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
	public boolean attackEntityAsMob(Entity target) {
    	
    	boolean success = target.attackEntityFrom(DamageSource.causeMobDamage(this),
    			(float) getEntityAttribute(SharedMonsterAttributes.attackDamage).getBaseValue() + battleExperience);
    	if(success && battleExperience < 5.0f) battleExperience += 0.25f;
    	return success;
    }
	

	@Override
	public void onLivingUpdate() {
		
		super.onLivingUpdate();
		
		if(rand.nextInt(10000) == 0) {
			entityDropItem(new ItemStack(Items.feather, rand.nextInt(2),
					FeatherVariant.OSTRICH.getMetaData()), 0.5f);
		}
		if (!this.worldObj.isRemote && !this.isChild() && --this.timeUntilNextEgg <= 0) {
		    this.playSound("mob.chicken.plop", 1.0F, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
		    this.dropItem(BirdItem.OSTRICH_EGG.getInstance(), 1);
		    this.timeUntilNextEgg = this.rand.nextInt(10000) + 50000;
        }
	}
	
	@Override
	public boolean interact(EntityPlayer entityplayer)	{
		
		ItemStack stack = entityplayer.getHeldItem();
		Item item = (stack == null ? null : stack.getItem());
		
		boolean interacted = false;
		
		if(isChild()) {
			
		} else {
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
			} else {
				boolean healed = false;
				if(getHealth() < getMaxHealth()) {
					if(item == BirdItem.BREADCRUMBS.getInstance()) {
						heal(0.2f);
						healed = true;
					} else if(item == Items.wheat) {
						heal(0.5f);
						healed = true;
					} else if(item == Items.bread) {
						heal(1f);
						healed = true;
					} else if(item == Item.getItemFromBlock(Blocks.hay_block)) {
						heal(5f);
						healed = true;
					}
					if(healed) {
						stack.stackSize--;
						handleHealthUpdate((byte) 0);
					}
				}
				if(!healed) {
					if(isSaddled()) {
						if(riddenByEntity == null) {
							entityplayer.mountEntity(this);
						}
					} else {
						if(item == Items.saddle) {
							stack.stackSize--;
							setSaddled(true);
							setTamed(true);
						}
					}
				}
			}
		}
		return true;
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
	public void handleHealthUpdate(byte update) {
		
		super.handleHealthUpdate(update);
		
		if(update == 0) {
			double gauss = rand.nextGaussian();
			worldObj.spawnParticle(EnumParticleTypes.HEART, posX, posY + 1.5f, posZ, 
					0.03 * gauss, 0.03 * gauss, 0.03 * gauss, 5, 5, 4, 4, 3, 3);
		}
	}

	@Override
	public Entity getOwner() {
		return null;
	}
}
