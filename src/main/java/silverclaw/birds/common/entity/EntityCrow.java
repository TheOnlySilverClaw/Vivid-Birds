package silverclaw.birds.common.entity;

import com.google.common.base.Predicate;

import silverclaw.birds.common.entity.ai.EntityAIAvoidScarecrow;
import silverclaw.birds.common.entity.ai.EntityAIPickupItem;
import silverclaw.birds.common.entity.ai.EntityAISmoothFlying;
import silverclaw.birds.common.entity.ai.EntityAIStartFlying;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.init.Items;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class EntityCrow extends EntityMob {

	
	public EntityCrow(World worldObj) {
		
		super(worldObj);
		setSize(0.6f, 0.6f);
		stepHeight = 1.5f;
		
		tasks.addTask(0, new EntityAIAvoidScarecrow(this, 30, 1.5f, 0.9f));
		tasks.addTask(0, new EntityAIStartFlying(this, 100, 15, EntityChicken.class, 0.2f));
		tasks.addTask(0, new EntityAISmoothFlying(this, 100, 14, 0.3f, 500));
	}


	@Override
    protected void applyEntityAttributes() {
		
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.25);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(4.5);
	}
	
	@Override
	public void fall(float p_fall_1_, float p_fall_2_) {}
}
