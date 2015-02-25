package silverclaw.birds.common.item;

import silverclaw.birds.common.entity.EntityOstrich;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityOstrichEgg extends EntityThrowable {

	// higher number means lower chance!
	private static final int SPAWN_CHANCE = 10;
	
	public EntityOstrichEgg(World worldIn) {
		super(worldIn);
	}
		
	public EntityOstrichEgg(World worldIn, EntityPlayer playerIn) {
		super(worldIn, playerIn);
	}

	@Override
	protected void onImpact(MovingObjectPosition position) {

		if(position.entityHit != null) {
			position.entityHit.attackEntityFrom(DamageSource
					.causeThrownDamage(this, this.getThrower()), 0.5f);
		}
		if(!worldObj.isRemote && rand.nextInt(SPAWN_CHANCE) == 0) {
			
			EntityOstrich ostrich = new EntityOstrich(worldObj);
			ostrich.setLocationAndAngles(posX, posY + 1, posZ, rotationYaw, 0.0f);
			ostrich.setGrowingAge(-40000);
			worldObj.spawnEntityInWorld(ostrich);
        	this.setDead();
		}
        double var5 = 0.08D;
        for (int var6 = 0; var6 < 8; ++var6) {
        	this.worldObj.spawnParticle(EnumParticleTypes.ITEM_CRACK, this.posX, this.posY, this.posZ, 
        			(this.rand.nextFloat() - 0.5D) * 0.08D, (this.rand.nextFloat() - 0.5D) * 0.08D, 
        			(this.rand.nextFloat() - 0.5D) * 0.08D, new int[] { Item.getIdFromItem(Items.egg )});

        }
	}
}
