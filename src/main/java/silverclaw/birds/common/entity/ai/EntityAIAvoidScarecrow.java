package silverclaw.birds.common.entity.ai;

import java.util.List;
import java.util.Map;

import com.google.common.base.Predicate;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Vec3;

public class EntityAIAvoidScarecrow extends EntityAIBase {

	protected final EntityCreature entity;
	protected final double searchRadius;
	protected final float farSpeed;
	protected final float nearSpeed;

	protected List<EntityArmorStand> list;
	
	protected EntityArmorStand nearest;
	
	public EntityAIAvoidScarecrow(EntityCreature entity, double searchRadius,
			float farSpeed, float nearSpeed) {

		setMutexBits(1);
		
		this.entity = entity;
		this.searchRadius = searchRadius;
		this.farSpeed = farSpeed;
		this.nearSpeed = nearSpeed;
	}

	@Override
	public boolean shouldExecute() {

		nearest = findNearestScarecrow();
		
		if(nearest == null || nearest.isDead) return false;
		
		double gauss = (entity.getRNG().nextGaussian() - 0.5f) * 0.2f;
		
		Vec3 target = RandomPositionGenerator.findRandomTargetBlockAwayFrom(entity, 16, 7, new Vec3(
				nearest.posX, nearest.posY, nearest.posZ));
		
		if(target == null) return false;
		
		entity.getNavigator()
		.tryMoveToXYZ(target.xCoord + gauss, 
				target.yCoord + gauss, target.zCoord - gauss, farSpeed);
		
		return !entity.getNavigator().noPath();
	}

	private EntityArmorStand findNearestScarecrow() {
		
		nearest = null;
		list = entity.getEntityWorld().getEntitiesWithinAABB(EntityArmorStand.class,
				entity.getEntityBoundingBox().expand(searchRadius, searchRadius, searchRadius),
				
				new Predicate<EntityArmorStand>() {
			
				@Override
				public boolean apply(EntityArmorStand armorStand) {

					double scareValue = 5.0f;
							
					ItemStack stack = null;
					
					for(int i = 0; i < 4; i++) {
						
						stack = armorStand.getCurrentArmor(i);
						if(stack != null) {
							
							if(stack.getItem() instanceof ItemArmor) {
								
								ItemArmor armorPiece = (ItemArmor) stack.getItem();
								
								scareValue += armorPiece.damageReduceAmount * 0.7;
								scareValue += armorPiece.getItemEnchantability() * 0.4;
								}
						}
					}
					if(armorStand.getCurrentArmor(3) != null 
							&& armorStand.getCurrentArmor(3).getItem() != null
							&& armorStand.getCurrentArmor(3).getItem() == Item.getItemFromBlock(Blocks.pumpkin)) {
						scareValue *= 1.5;
					}
					return scareValue > entity.getDistanceSqToEntity(armorStand);
				}	
			});
		
		if(list.size() > 0) {
			nearest = list.get(0);
		}
		
		return nearest;
	}
	
	@Override
	public void resetTask() {

		nearest = null;
		entity.getNavigator().clearPathEntity();
	}

	@Override
	public void updateTask() {
		
		entity.getLookHelper().setLookPositionWithEntity(nearest, 0f, 0f);
		entity.getNavigator().setSpeed(entity.getDistanceSqToEntity(nearest)
				> searchRadius/2 ? farSpeed : nearSpeed);
	}
}
