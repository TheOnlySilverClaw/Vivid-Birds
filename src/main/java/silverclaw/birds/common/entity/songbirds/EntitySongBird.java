package silverclaw.birds.common.entity.songbirds;

import silverclaw.birds.common.entity.EntityPeacefulBird;
import silverclaw.birds.common.entity.ai.EntityAIFlyingAvoidEnemies;
import com.google.common.base.Predicate;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.world.World;

public class EntitySongBird extends EntityPeacefulBird {

	
	public EntitySongBird(World worldIn) {
		
		super(worldIn);
		setSize(0.4f, 0.45f);
		
		limbSwingAmount = 1;
		tasks.addTask(3, new EntityAIAvoidEntity(this, new Predicate<Entity>() {

			@Override
			public boolean apply(Entity entity) {

				return !(entity instanceof EntitySongBird);
			}
		}, 4, 0.8, 1.2));
		tasks.addTask(5, new EntityAIFlyingAvoidEnemies(this, 0.15f, 100, 50, 75, 
				20, getMaxHealth(), EntityLivingBase.class, 15));
	}

	@Override
	public EntityAgeable createChild(EntityAgeable otherBird) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
    protected void applyEntityAttributes() {
		
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(2.0);
	}
	
	@Override
	public String getLivingSound() {
		
		return "birdmod:songbird";
	}

	
	@Override
	protected float getSoundVolume() {
		
		return (float) (rand.nextGaussian() + 0.8f);
	}
	
	@Override
	public void onLivingUpdate() {
		
		super.onLivingUpdate();
		if(worldObj.getTotalWorldTime() % 200 == 0) jumpHelper.doJump();;
	}
}
