package silverclaw.birds.common.entity.ai;

import javax.annotation.Nonnull;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.init.Blocks;
import net.minecraft.pathfinding.PathFinder;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.util.BlockPos;

public class EntityAILayEggInNest extends EntityAIBase {
	
	protected final static int nestTimer = 1500;

	protected final EntityLiving entity;
	
	protected BlockPos nest;
	protected int nestCountdown;
	
	public EntityAILayEggInNest(EntityLiving entity) {

		this.entity = entity;
		nestCountdown = nestTimer;
	}
	

	@Override
	public boolean shouldExecute() {

		return nestCountdown-- == 0 && entity.riddenByEntity == null
				&& isAreaSafe();
	}


	private boolean isAreaSafe() {

		return entity.getEntityWorld().getEntitiesWithinAABB(EntityMob.class,
				entity.getEntityBoundingBox().expand(20, 15, 20)).isEmpty();
	}


	@Override
	public boolean continueExecuting() {

		return false;
	}


	@Override
	public void startExecuting() {
		
		nestCountdown = nestTimer;
		super.startExecuting();
	}


	@Override
	public void updateTask() {
	
		BlockPos pos = entity.getPosition().down();

		nest = findExistingNest(pos);
		
		if(nest != null) {
			entity.getNavigator().tryMoveToXYZ(nest.getX(), nest.getY(), nest.getZ(), 1);
		} else {
			makeHay(pos.east(), pos.west(), pos.north(), pos.south(), pos.down());
			entity.getEntityWorld().setBlockToAir(pos);
		}
	}


	private BlockPos findExistingNest(BlockPos pos) {
		
		int counter = 0;
		int distance = 20;
		BlockPos nest = null;
		
		for(int layer : new int [] { pos.getY(), pos.down().getY(),
				pos.up().getY(), pos.up().getY()}) {
			
			for(int x = pos.getX() -distance; x < pos.getX() + distance; x++) {
				for(int z = pos.getZ() - distance; z < pos.getZ() + distance; z++) {
					if(entity.getEntityWorld().getBlockState(new BlockPos(x, pos.getY(), z))
						.getBlock() == Blocks.hay_block) {
						nest = new BlockPos(x, layer, z);
					}
				}
			}
		}
		return nest;
	}


	private void makeHay(BlockPos... positions) {
		
		for(BlockPos pos : positions) {
			entity.getEntityWorld().setBlockState(pos,
					Blocks.hay_block.getDefaultState());
		}	
	}
}
