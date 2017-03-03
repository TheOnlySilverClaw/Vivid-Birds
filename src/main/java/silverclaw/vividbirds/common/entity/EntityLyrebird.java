package silverclaw.vividbirds.common.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import silverclaw.vividbirds.common.BirdItem;
import silverclaw.vividbirds.common.entity.util.LyrebirdSounds;

public class EntityLyrebird extends EntityPeacefulBird {
	
	private List<String> livingSounds;
	private List<String> hurtSounds;
	
	
	public EntityLyrebird(World worldObj) {
		
		super(worldObj);
		
		setSize(0.9f, 1.1f);
		tasks.addTask(5, new EntityAITempt(
				this, 1.35f, Items.melon_seeds, false));
		
		this.livingSounds = Collections.emptyList();
		this.hurtSounds = Collections.emptyList();
	}

	@Override
	public IEntityLivingData onInitialSpawn(
			DifficultyInstance difficulty, IEntityLivingData livingdata) {
		
		System.out.println("initial spawn");
		this.livingSounds = LyrebirdSounds.selectLivingSounds();
		this.hurtSounds = LyrebirdSounds.selectHurtSounds();
		
		return super.onInitialSpawn(difficulty, livingdata);
	}

	@Override
	public boolean interact(EntityPlayer player) {
		if(!worldObj.isRemote) System.out.println(livingSounds);
		return super.interact(player);
	}
	
	@Override
	public EntityAgeable createChild(EntityAgeable parent) {

		if(parent instanceof EntityLyrebird) {
			EntityLyrebird child = new EntityLyrebird(worldObj);
			child.setGrowingAge(-22000);
			child.livingSounds = LyrebirdSounds.mergeLivingSounds(
					this.livingSounds, ((EntityLyrebird) parent).livingSounds);
			child.hurtSounds = LyrebirdSounds.mergeHurtSounds(
					this.hurtSounds, ((EntityLyrebird) parent).hurtSounds);
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

		if(livingSounds.isEmpty()) return null;
		return LyrebirdSounds.randomSound(livingSounds);
	}

	@Override
	public String getHurtSound() {
		
		if(hurtSounds.isEmpty()) return null;
		return LyrebirdSounds.randomSound(hurtSounds);
	}
	
	@Override
	public String getDeathSound() {
		/*
		 *  does not need to be saved, since lyrebirds,
		 *  like most organic beings, usually only die once
		 */
		return LyrebirdSounds.selectDeathSound();
	}
	
	@Override
	protected float getSoundVolume() {
		
		return (float) (rand.nextGaussian() + 0.8f);
	}

	@Override
	protected void dropFewItems(boolean drop, int number) {
		
		if(worldObj.getBiomeGenForCoords(
				getPosition()) == BiomeGenBase.jungle) {
			dropItem(Items.melon_seeds, rand.nextInt(2));
		}
		
		entityDropItem(new ItemStack(
				Items.feather, rand.nextInt(3), 1), 0.5f);
		
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

		NBTTagList livingSoundsNBT = compound
				.getTagList("living_sounds", 8);
		List<String> livingSoundsLoaded = new ArrayList<>(
				LyrebirdSounds.LIVING_SOUND_NUMBER);
		for(int i = 0; i < livingSoundsNBT.tagCount(); i++) {
			livingSoundsLoaded.add(
					livingSoundsNBT.getStringTagAt(i));
		}
		this.livingSounds = livingSoundsLoaded;
		
		NBTTagList hurtSoundsNBT = compound
				.getTagList("living_sounds", 8);
		List<String> hurtSoundsLoaded = new ArrayList<>(
				LyrebirdSounds.HURT_SOUND_NUMBER);
		for(int i = 0; i < hurtSoundsNBT.tagCount(); i++) {
			hurtSoundsLoaded.add(
					hurtSoundsNBT.getStringTagAt(i));
		}
		this.hurtSounds = hurtSoundsLoaded;
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound compound) {
		
		super.writeEntityToNBT(compound);
		
		NBTTagList livingSoundsNBT = new NBTTagList();
		System.out.println(livingSounds.size());
		System.out.println(livingSounds);
		for(String livingSound : livingSounds) {
			livingSoundsNBT.appendTag(new NBTTagString(livingSound));
		}
		compound.setTag("living_sounds", livingSoundsNBT);
		
		NBTTagList hurtSoundsNBT = new NBTTagList();
		for(String hurtSound : hurtSounds) {
			hurtSoundsNBT.appendTag(new NBTTagString(hurtSound));
		}
		compound.setTag("hurt_sounds", hurtSoundsNBT);
	}
}
