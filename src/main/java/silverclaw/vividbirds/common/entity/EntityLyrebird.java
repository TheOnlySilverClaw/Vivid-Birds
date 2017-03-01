package silverclaw.vividbirds.common.entity;

import java.util.Arrays;

import joptsimple.internal.Strings;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import silverclaw.vividbirds.common.BirdItem;
import silverclaw.vividbirds.common.entity.util.LyrebirdSoundManager;

public class EntityLyrebird extends EntityPeacefulBird {
	
	private String [] livingSounds;
	private String [] hurtSounds;
	
	
	protected EntityLyrebird(World worldObj,
			String [] livingSounds, String [] hurtSounds) {
		
		super(worldObj);
		
		setSize(0.9f, 1.1f);
		tasks.addTask(5, new EntityAITempt(this, 1.35f, Items.melon_seeds, false));

		this.livingSounds = livingSounds;
		this.hurtSounds = hurtSounds;

	}

	public EntityLyrebird(World worldObj) {
		
		this(worldObj,
				LyrebirdSoundManager.selectLivingSounds(),
				LyrebirdSoundManager.selectHurtSounds()
				);		
	}

	public EntityLyrebird(World worldObj,
			EntityLyrebird parent1, EntityLyrebird parent2) {
		this(worldObj,
				LyrebirdSoundManager.mergeLivingSounds(
						parent1.livingSounds, parent2.livingSounds),
				LyrebirdSoundManager.mergeHurtSounds(
						parent1.hurtSounds, parent2.hurtSounds)
				);
	}

	@Override
	public boolean interact(EntityPlayer player) {
		
		if(worldObj.isRemote) {
			System.out.println(Arrays.toString(livingSounds));
			System.out.println(Arrays.toString(hurtSounds));
		}

		return super.interact(player);
	}
	
	@Override
	public EntityAgeable createChild(EntityAgeable parent) {

		if(parent instanceof EntityLyrebird) {
			EntityLyrebird child = new EntityLyrebird(
					worldObj, this, (EntityLyrebird) parent);
			child.setGrowingAge(-22000);
			return child;
		}
		return null;
	}

	@Override
	public boolean isBreedingItem(ItemStack stack) {

		return stack != null && stack.getItem() == Items.melon_seeds;
	}

	@Override
	public String getLivingSound() {
		return LyrebirdSoundManager.randomSound(livingSounds);
	}

	@Override
	public String getHurtSound() {
		return LyrebirdSoundManager.randomSound(hurtSounds);
	}
	
	@Override
	public String getDeathSound() {
		/*
		 *  does not need to be saved, since lyrebirds,
		 *  like most organic beings, usually only die once
		 */
		return LyrebirdSoundManager.selectDeathSound();
	}
	
	@Override
	protected float getSoundVolume() {
		
		return (float) (rand.nextGaussian() + 0.8f);
	}

	@Override
	protected void dropFewItems(boolean drop, int number) {
		
		if(worldObj.getBiomeGenForCoords(getPosition()) == BiomeGenBase.jungle) {
			dropItem(Items.melon_seeds, rand.nextInt(2));
		}
		
		entityDropItem(new ItemStack(Items.feather, rand.nextInt(3), 1), 0.5f);
		
		if(drop) {
			dropItem(isBurning() ? BirdItem.WILDBIRD_COOKED.getInstance()
					: BirdItem.WILDBIRD_RAW.getInstance(), 1);
		}
	}

	
	@Override
    protected void applyEntityAttributes() {
		
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.movementSpeed)
			.setBaseValue(0.24);
		getEntityAttribute(SharedMonsterAttributes.maxHealth)
			.setBaseValue(7.0);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound compound) {
		
		super.readEntityFromNBT(compound);
		this.livingSounds = compound.getString("living_sounds").split(";");
		this.hurtSounds = compound.getString("hurt_sounds").split(";");
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound compound) {
		
		super.writeEntityToNBT(compound);
		compound.setString("living_sounds", Strings.join(livingSounds, ";"));
		compound.setString("hurt_sounds", Strings.join(hurtSounds, ";"));
	}
}
