package silverclaw.birds.common.entity.ai;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class HeightChecker {

	public static int getHeightAboveGround(Entity entity, int limit) {
		
		int blockCounter = 0;
		
		World world = entity.worldObj;
		
		BlockPos down = entity.getPosition().down();
		
		while(world.getBlockState(down).getBlock() == Blocks.air 
				&& limit >= blockCounter) {
			
			blockCounter++;
			down = down.down();
		}
		return blockCounter;
	}
	
	public static boolean isOnGround(Entity entity) {
		
		return entity.getEntityWorld().getBlockState(
				entity.getPosition().down()).getBlock() != Blocks.air;
	}
	
	public static boolean isNearGround(Entity entity, int limit) {
		
		return getHeightAboveGround(entity, limit) <= limit;
	}
}
