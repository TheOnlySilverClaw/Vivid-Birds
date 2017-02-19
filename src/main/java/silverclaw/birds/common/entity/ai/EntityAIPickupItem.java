package silverclaw.birds.common.entity.ai;

import java.util.List;

import com.google.common.base.Predicate;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;

public class EntityAIPickupItem extends EntityAIBase {

	private final EntityLiving entity;
	private final Item item;
	private final double searchRadius;
	private final float speed;
	private final float reach;
	
	protected List<EntityItem> targetList;

	private EntityItem nearestTarget;

	public EntityAIPickupItem(EntityLiving entity, Item item, double searchRadius,
			float speed, float reach) {
		
		setMutexBits(1);
		
		this.entity = entity;
		this.item = item;
		this.searchRadius = searchRadius;
		this.speed = speed;
		this.reach = reach;
	}

	@Override
	public boolean shouldExecute() {
		
		 EntityItem nearestTarget = findBestTarget();
		 return nearestTarget != null;
	}

	private EntityItem findBestTarget() {
		
		nearestTarget = null;

		targetList = entity.getEntityWorld().getEntitiesWithinAABB(EntityItem.class,
					entity.getEntityBoundingBox().expand(searchRadius, searchRadius, searchRadius),
					new Predicate<Entity>() {
				
				@Override
				public boolean apply(Entity entity) {
					
					return ((EntityItem) entity).getEntityItem().getItem() == item; 
				}
		 	});
		 
		 double bestDistance = Double.MAX_VALUE;
		 double otherDistance = 0;
		 
		 if(!targetList.isEmpty()) {
			 nearestTarget = targetList.get(0);
		 }
		 for(EntityItem next : targetList) {
			 otherDistance = entity.getDistanceSqToEntity(next);
			 
			 if(otherDistance < bestDistance) {
				 bestDistance = otherDistance;
				 nearestTarget = next;
			 }
		 }
		 return nearestTarget;
	}

	@Override
	public void startExecuting() {
				/*
		System.out.println("Start going for item: " 
		+ nearestTarget.getEntityItem().getItem().getUnlocalizedName()
		+ " distance: " + entity.getDistanceSqToEntity(nearestTarget));*/
	}
	
	@Override
	public boolean continueExecuting() {
		
		return !(entity.getNavigator().noPath() || nearestTarget == null
				|| nearestTarget.isDead);
	}

	@Override
	public void resetTask() {

		nearestTarget = null;
	}

	@Override
	public void updateTask() {

		double gauss = (entity.getRNG().nextGaussian() - 0.5f) * 0.2f;
		
		entity.getNavigator().tryMoveToXYZ(nearestTarget.posX + gauss, 
				nearestTarget.posY + gauss, nearestTarget.posZ - gauss, speed);
		
		entity.getLookHelper().setLookPositionWithEntity(nearestTarget, 0f, 0f);
		
		if(entity.getEntityBoundingBox().expand(reach, reach, reach)
				.intersectsWith(nearestTarget.getEntityBoundingBox())) {
			
			nearestTarget.setDead();
			if(targetList.size() > 0) targetList.remove(0);
		}
	}
}
