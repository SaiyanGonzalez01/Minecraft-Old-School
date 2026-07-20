package net.minecraft.src;

public class ItemBow extends Item {
	public ItemBow(int var1) {
		super(var1);
		this.maxStackSize = 1;
	}

	public boolean isFull3D() {
		return true;
	}

	public ItemStack onItemRightClick(ItemStack var1, World var2, EntityPlayer var3) {
		boolean consumed = var3.inventory.consumeInventoryItem(Item.arrow.shiftedIndex);
		if(!consumed) {
			consumed = ItemQuiver.consumeArrowFromQuiver(var3);
		}
		if(consumed) {
			var2.playSoundAtEntity(var3, "random.bow", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 0.8F));
			if(!var2.multiplayerWorld) {
				var2.entityJoinedWorld(new EntityArrow(var2, var3));
			}
		}

		return var1;
	}
}
