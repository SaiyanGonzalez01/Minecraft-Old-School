package net.minecraft.src;

public class ItemFood extends Item {
	private int healAmount;

	public ItemFood(int var1, int var2) {
		super(var1);
		this.healAmount = var2;
		this.maxStackSize = 1;
	}

	public ItemStack onItemRightClick(ItemStack var1, World var2, EntityPlayer var3) {
		var2.playSoundAtEntity(var3, "random.eat", 0.5F, var2.rand.nextFloat() * 0.1F + 0.9F);
		--var1.stackSize;
		var3.heal(this.healAmount);
		return var1;
	}
}
