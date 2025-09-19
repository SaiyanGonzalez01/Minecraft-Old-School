package net.minecraft.src;

public class ItemDrink extends ItemFood {
	public ItemDrink(int var1, int var2) {
		super(var1, var2, false);
	}

	public ItemStack onItemRightClick(ItemStack var1, World var2, EntityPlayer var3) {
		super.onItemRightClick(var1, var2, var3);
		var2.playSoundAtEntity(var3, "random.drink", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 0.8F));
		return new ItemStack(Item.bottle);
	}
}
