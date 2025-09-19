package net.minecraft.src;

public class ItemDrink extends Item {
	public int healAmount;

	public ItemDrink(int var1, int var2, boolean var3) {
		super(var1);
		this.healAmount = var2;
		this.maxStackSize = 1;
	}

	public ItemStack onItemRightClick(ItemStack var1, World var2, EntityPlayer var3) {
		--var1.stackSize;
		var2.playSoundAtEntity(var3, "random.drink", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 0.8F));
		var3.heal(this.healAmount);
		return new ItemStack(Item.bottle);
	}

	public int getHealAmount() {
		return this.healAmount;
	}
}
