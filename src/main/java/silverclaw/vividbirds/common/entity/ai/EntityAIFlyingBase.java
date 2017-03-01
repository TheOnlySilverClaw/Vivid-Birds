package silverclaw.vividbirds.common.entity.ai;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.MathHelper;
import silverclaw.vividbirds.common.entity.util.HeightChecker;

public class EntityAIFlyingBase extends EntityAIBase {

	private final float DEG = (float) (Math.PI / 180);

	protected final EntityLiving entity;
	
	private final int flightTime;
	private final int walkTime;
	private final int targetFlightHeight;
	private final float speed;
	
	protected int flightCounter;
	private int turnCounter;
	
	private final double DESCEND_FACTOR = ((float) -2/(float) 3);
	
	public EntityAIFlyingBase(EntityLiving entity, float speed, int flightTime,
			int walkTime, int targetFlightHeight) {

		setMutexBits(4);
		
		this.entity = entity;
		this.speed = Math.max(0.0f, speed/10);
		this.flightTime = flightTime;
		this.walkTime = walkTime;
		this.targetFlightHeight = targetFlightHeight;
		
		flightCounter = -entity.getRNG().nextInt(walkTime);
	}

	@Override
	public boolean shouldExecute() {
		
		return flightCounter++ > 0	&& !entity.isInWater();
	}

	@Override
	public boolean isInterruptible() {

		return HeightChecker.isOnGround(entity) || entity.isInWater();
	}

	@Override
	public boolean continueExecuting() {

		return shouldExecute() && !HeightChecker.isOnGround(entity);
	}

	private void ascend() {
		
		entity.motionY = speed;
		entity.limbSwingAmount = 3;
	}
	
	private void descend() {

		entity.limbSwingAmount = 1;
		entity.motionY = speed * DESCEND_FACTOR;
	}
	
	private void align() {
		
		entity.motionX = -speed * MathHelper.sin(entity.rotationYaw * DEG);
		entity.motionZ = speed * MathHelper.cos(entity.rotationYaw * DEG);
		
		if(turnCounter-- == 0) {
			
			turnCounter = entity.getRNG().nextInt(flightCounter + flightTime);
			entity.rotationYaw += (entity.getRNG().nextGaussian() - 0.5f);
		}
	}
	
	@Override
	public void startExecuting() {

	}

	@Override
	public void resetTask() {

		flightCounter = -entity.getRNG().nextInt(walkTime);
	}

	@Override
	public void updateTask() {
		
		if(flightCounter >= flightTime) {
			descend();
		} else {
			if(flightCounter > 0) {
				if(entity.posY < targetFlightHeight) {
					ascend();
				} else {
					descend();
				}
			}
		}
		align();
	}
}

