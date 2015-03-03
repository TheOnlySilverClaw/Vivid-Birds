package silverclaw.birds.common.entity;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.world.World;
import silverclaw.birds.common.entity.ai.EntityAIAvoidScarecrow;

public class EntityCrow extends EntityMob {

	
	public EntityCrow(World worldObj) {
		
		super(worldObj);
		setSize(0.6f, 0.6f);
		stepHeight = 1.5f;
		
		tasks.addTask(0, new EntityAIAvoidScarecrow(this, 30, 1.5f, 0.9f));

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
