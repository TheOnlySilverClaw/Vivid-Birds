package silverclaw.birds.common.entity.ai;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.MathHelper;

public class EntityAISmoothFlying extends EntityAIBase {
	
	private final EntityLiving entity;
	private final int variance;
	private final int targetHeight;
	private final float speed;
	private final int directionTimer;
	
	private int directionCountdown;
	private float vary;
	
	private final float DEG = (float) (Math.PI / 180);
	
	public EntityAISmoothFlying(EntityLiving entity, int targetHeight, int variance,
			float speed, int directionTimer) {
		
		this.entity = entity;
		this.targetHeight = targetHeight;
		this.variance = variance;
		this.speed = speed;
		this.directionTimer = directionTimer;
		
		vary = speed;
	}

	private void resetCountdown() {
	
		directionCountdown = entity.getRNG().nextInt(directionTimer);
	}
	
	@Override
	public boolean shouldExecute() {

		return !HeightChecker.isNearGround(entity, 4);
	}

	@Override
	public boolean isInterruptible() {
		
		return true;
	}

	@Override
	public void startExecuting() {

		super.startExecuting();
	}

	@Override
	public void updateTask() {
		
		int height = entity.getPosition().getY();

		if(height + variance < targetHeight) {
			
			entity.limbSwing = 4;
			entity.motionY = speed;
			
		} else if(height - variance > targetHeight) {
			
			entity.limbSwing = 2;
			entity.motionY = -speed * 1.5f;
			
		} else {
			
			entity.limbSwing = 3;
			entity.motionY = MathHelper.sin(vary) * speed/2;
			vary += (entity.getRNG().nextFloat() - 0.5f);
		}
		
		if(directionCountdown-- == 0) {
			resetCountdown();
			entity.rotationYaw += (entity.getRNG().nextFloat() - 0.5f) * 180f;
		}
		
		entity.motionX = -speed/2 * MathHelper.sin(entity.rotationYaw * DEG);
		entity.motionZ = speed/2 * MathHelper.cos(entity.rotationYaw * DEG);
		
		entity.rotationYawHead = entity.rotationYaw 
				+ ((float) entity.getRNG().nextGaussian() - 0.5f);

	}
	
}
