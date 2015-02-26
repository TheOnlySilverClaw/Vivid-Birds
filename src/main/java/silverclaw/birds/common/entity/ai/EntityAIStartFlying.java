package silverclaw.birds.common.entity.ai;

import com.google.common.base.Predicate;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.monster.EntityMob;

public class EntityAIStartFlying extends EntityAIBase implements TimedAI {

	protected final EntityLiving entity;
	protected final int flightTimer;
	protected final float mobSenseRange;
	protected final Class<? extends EntityLivingBase> avoidClass;
	protected final float speed;
	
	
	public EntityAIStartFlying(EntityLiving entity, int flightTimer,
			float mobSenseRange, Class<? extends EntityLivingBase> avoidClass,
			float speed) {

		this.entity = entity;
		this.flightTimer = flightTimer;
		this.mobSenseRange = mobSenseRange;
		this.avoidClass = avoidClass;
		this.speed = speed;
		
		setMutexBits(4);
		
		resetCountdown();
	}

	@Override
	public void resetCountdown() {
		
		flightCountdown = (int) (entity.getRNG().nextGaussian() * flightTimer);
	}
	
	@Override
	public boolean isInterruptible() {

		return false;
	}

	protected int flightCountdown;
	
	@Override
	public boolean shouldExecute() {

		return HeightChecker.isNearGround(entity, 3) && shouldFly();
	}

	
	@Override
	public boolean continueExecuting() {

		return HeightChecker.isNearGround(entity, 8);
	}

	protected boolean shouldFly() {
		
		boolean fly =  isEnemyNear() || isReady();
		return fly;
	}
	
	protected boolean isEnemyNear() {
		
		return !entity.getEntityWorld().getEntitiesWithinAABB(avoidClass, 
				entity.getEntityBoundingBox().expand(mobSenseRange, mobSenseRange, mobSenseRange))
				.isEmpty();
	}


	@Override
	public void startExecuting() {
		
		resetCountdown();
	}

	@Override
	public void resetTask() {

		resetCountdown();
	}

	@Override
	public void updateTask() {
		
		entity.motionY = speed * 2 * entity.getRNG().nextGaussian();
	}

	@Override
	public boolean isReady() {

		return flightCountdown-- < 0;
	}
}
