package silverclaw.birds.common.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemOstrichEgg extends Item {

	public ItemOstrichEgg() {

		setMaxStackSize(16);
		setUnlocalizedName("ostrich_egg");
	}

	@Override
    public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn) {
		
		if (!playerIn.capabilities.isCreativeMode) {
			--itemStackIn.stackSize;
		}
		worldIn.playSoundAtEntity(playerIn, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
		if (!worldIn.isRemote) {
			worldIn.spawnEntityInWorld(new EntityOstrichEgg(worldIn, playerIn));
		}
		return itemStackIn;
    }
}
