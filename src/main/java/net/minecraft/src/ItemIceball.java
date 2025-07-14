package net.minecraft.src;

public class ItemIceball extends Item {
	public ItemIceball(int var1) {
		super(var1);
		this.maxStackSize = 16;
	}

	public ItemStack onItemRightClick(ItemStack var1, World var2, EntityPlayer var3) {
		--var1.stackSize;
		var2.playSoundAtEntity(var3, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
		if(!var2.multiplayerWorld) {
			var2.entityJoinedWorld(new EntityIceball(var2, var3));
		}

		return var1;
	}
}
