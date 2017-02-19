package silverclaw.birds.common.entity;

import silverclaw.birds.common.BirdItem;
import joptsimple.internal.Strings;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class EntityLyrebird extends EntityPeacefulBird {
	
	private final static String [] LIVING_SOUNDS = {
		
		"minecraft:mob.cat.meow",
		"minecraft:mob.cat.purr",
		"minecraft:mob.cat.purreow",
		"minecraft:mob.cow.say",
		"minecraft:mob.chicken.say",
		"minecraft:mob.sheep.say",
		"minecraft:mob.pig.say",
		"minecraft:mob.silverfish.say",
		"minecraft:mob.wolf.bark",
		"minecraft:mob.wolf.howl",
		"minecraft:dig.cloth",
		"minecraft:dig.grass",
		"minecraft:dig.gravel",
		"minecraft:dig.sand",
		"minecraft:dig.snow",
		"minecraft:dig.stone",
		"minecraft:dig.wood",
		"minecraft:fire.fire",
		"minecraft:fire.ignite",
		"minecraft:fireworks.blast",
		"minecraft:fireworks.blast_far",
		"minecraft:fireworks.largeBlast",
		"minecraft:fireworks.largeBlast_far",
		"minecraft:fireworks.launch",
		"minecraft:random.burp"
	};
	
	private final static String [] HURT_SOUNDS = {
		
		"minecraft:mob.cat.hiss",
		"minecraft:mob.horse.angry",
		"minecraft:mob.endermen.scream",
		"minecraft:mob.endermen.stare",
		"minecraft:mob.creeper.say",
		"minecraft:mob.ghast.moan",
		"minecraft:mob.blaze.hit",
		"minecraft:mob.skeleton.say",
		"minecraft:game.neutral.hurt.fall.big",
		"minecraft:game.neutral.hurt.fall.small",
		"minecraft:game.player.hurt.fall.big",
		"minecraft:game.player.hurt.fall.small",
		"minecraft:game.hostile.hurt.fall.big",
		"minecraft:game.hostile.hurt.fall.small",
		"minecraft:game.player.hurt",
		"minecraft:game.neutral.hurt",
		"minecraft:game.hostile.hurt",
		"minecraft:mob.bat.hurt",
		"minecraft:mob.chicken.hurt",
		"minecraft:mob.cow.hurt",
		"minecraft:random.explode",
		"minecraft:creeper.primed"
		};
	

	private final static String [] DEATH_SOUNDS = {
		
		"minecraft:game.player.die",
		"minecraft:game.neutral.die",
		"minecraft:game.hostile.die",
		"minecraft:mob.bat.death",
		"minecraft:mob.blaze.death",
		"minecraft:mob.creeper.death",
		"minecraft:mob.endermen.death",
		"minecraft:mob.horse.death",
		"minecraft:mob.horse.donkey.death",
		"minecraft:mob.pig.death"
	};
		
	private String[] fillSounds(String [] available, int size) {
	
		String [] toFill = new String[size];
		for(int i = 0; i < toFill.length; i++) {
			toFill[i] = randomSound(available);
		}
		return toFill;
	}
	
	private final String randomSound(String [] available) {
	
		return available[rand.nextInt(available.length)];
	}
	
	private String [] livingSounds;
	private String [] hurtSounds;
	private String deathSound;
	
	public EntityLyrebird(World worldObj) {
		
		super(worldObj);
		setSize(0.9f, 1.1f);
		tasks.addTask(5, new EntityAITempt(this, 1.35f, Items.melon_seeds, false));

		livingSounds = fillSounds(LIVING_SOUNDS, 5);
		hurtSounds = fillSounds(HURT_SOUNDS, 3);
		deathSound = randomSound(DEATH_SOUNDS);
		
	}
	
	public EntityLyrebird(World worldObj,
			String inheritedLivingSound, String inheritedHurtSound) {
		
		this(worldObj);
		livingSounds[rand.nextInt(livingSounds.length)] = inheritedLivingSound;
		hurtSounds[rand.nextInt(hurtSounds.length)] = inheritedHurtSound;
	}

	@Override
	public EntityAgeable createChild(EntityAgeable parent) {
		EntityLyrebird child = new EntityLyrebird(
				worldObj, this.getLivingSound(), 
				((EntityLyrebird) parent).getHurtSound());
		child.setGrowingAge(-22000);
		return child;
	}

	@Override
	public boolean isBreedingItem(ItemStack stack) {

		return stack != null && stack.getItem() == Items.melon_seeds;
	}

	@Override
	public String getLivingSound() {
		return randomSound(LIVING_SOUNDS);
	}

	@Override
	public String getHurtSound() {
		return randomSound(HURT_SOUNDS);
	}
	
	@Override
	public String getDeathSound() {
		return deathSound;
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
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.24);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(7.0);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound compound) {
		
		super.readEntityFromNBT(compound);
		this.livingSounds = compound.getString("LivingSounds").split(";");
		this.hurtSounds = compound.getString("HurtSounds").split(";");
		this.deathSound = compound.getString("DeathSound");
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound compound) {
		
		super.writeEntityToNBT(compound);
		compound.setString("LivingSounds", Strings.join(LIVING_SOUNDS, ";"));
		compound.setString("HurtSounds", Strings.join(HURT_SOUNDS, ";"));
		compound.setString("DeathSound", deathSound);
	}
}
