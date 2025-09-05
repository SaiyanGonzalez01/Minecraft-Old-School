package net.minecraft.src;

public class ItemGoldBow extends Item {
	public ItemGoldBow(int var1) {
		super(var1);
		this.maxStackSize = 1;
		this.setMaxDamage(512);
	}

  	public ItemStack onItemRightClick(ItemStack var1, World var2, EntityPlayer var3) {
		if(var3.inventory.consumeInventoryItem(Item.arrowGold.shiftedIndex)) {
			if(!var2.multiplayerWorld) {
				var2.playSoundAtEntity(var3, "random.bow", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 0.8F));
				var2.entityJoinedWorld(new EntityGoldArrow(var2, var3));
			}
		}
		
		return var1;
	}
}
