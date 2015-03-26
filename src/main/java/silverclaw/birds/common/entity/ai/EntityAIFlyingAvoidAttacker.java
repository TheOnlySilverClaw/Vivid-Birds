package silverclaw.birds.common.entity.ai;

import net.minecraft.entity.EntityLiving;

public class EntityAIFlyingAvoidAttacker extends EntityAIFlyingBase {

	private final float distance;
	private final float flightHealth;
	
	public EntityAIFlyingAvoidAttacker(EntityLiving entity, float speed,
			int flightTime, int walkTime, int targetFlightHeight,
			float distance, float flightHealth) {
		super(entity, speed, flightTime, walkTime, targetFlightHeight);
		this.distance = distance;
		this.flightHealth = flightHealth;
	}

	@Override
	public boolean shouldExecute() {
		
		if(entity.getRevengeTimer() > 0 && entity.getAITarget() != null
				&& entity.getHealth() < flightHealth) {
			if(entity.getDistanceToEntity(entity.getAITarget()) < distance) {
			flightCounter = 1;
			}
		}
		return super.shouldExecute();
	}
}
