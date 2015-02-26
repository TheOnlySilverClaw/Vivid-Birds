package silverclaw.birds.common.entity.ai;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.BlockPos;

public class EntityAILandSomewhere extends EntityAIBase implements TimedAI {

	private final EntityLiving entity;
	private final int landTimer;
	private final float speed;
	
	private BlockPos landTarget;
	private int landCountdown;

	public EntityAILandSomewhere(EntityLiving entity, int landTimer, float speed) {

		setMutexBits(4);
		
		this.entity = entity;
		this.landTimer = landTimer;
		this.speed = speed;
		
		
		resetCountdown();
	}
	
	@Override
	public boolean isReady() {
		
		return landCountdown-- < 0;
	}

	@Override
	public void resetCountdown() {
		
		landCountdown = landTimer;
		
	}

	@Override
	public boolean shouldExecute() {
		
		if(entity.onGround) return false;
		return isReady();
	}

	@Override
	public boolean continueExecuting() {
		
		return landTarget.getY() + 2 < entity.getPosition().getY();
	}

	@Override
	public boolean isInterruptible() {

		return false;
	}

	@Override
	public void startExecuting() {

		landTarget = findTarget();
	}

	private BlockPos findTarget() {

		return entity.getEntityWorld().getTopSolidOrLiquidBlock(entity.getPosition());
	}

	@Override
	public void resetTask() {
		
		landTarget = null;
		resetCountdown();
	}

	@Override
	public void updateTask() {


		if(landTarget.getY() < entity.getPosition().getY()) {
			entity.motionY = -speed;
		}
	}
}
