package silverclaw.vividbirds.common.entity.ai;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;

public class EntityAIFlyingAvoidEnemies extends EntityAIFlyingAvoidAttacker {

	private final Class<? extends EntityLivingBase> enemies;
	private final float range;
	


	public EntityAIFlyingAvoidEnemies(EntityLiving entity, float speed,
			int flightTime, int walkTime, int targetFlightHeight,
			float distance, float flightHealth,
			Class<? extends EntityLivingBase> enemies, float range) {
		
		super(entity, speed, flightTime, walkTime, targetFlightHeight, distance,
				flightHealth);
		this.enemies = enemies;
		this.range = range;
	}

	@Override
	public boolean shouldExecute() {
		
		if(!isAreaSafe()) flightCounter = 1;
		return super.shouldExecute();
	}

	private boolean isAreaSafe() {
		
		return entity.getEntityWorld().getEntitiesWithinAABB(enemies,
				entity.getEntityBoundingBox().expand(range, range, range)).isEmpty();
	}
}
