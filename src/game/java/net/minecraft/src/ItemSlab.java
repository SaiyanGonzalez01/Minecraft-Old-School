package net.minecraft.src;

public class ItemSlab extends ItemBlock {
	public ItemSlab(int var1) {
		super(var1);
		this.setMaxDamage(0);
		this.setHasSubtypes(true);
	}

	public int getIconIndex(ItemStack var1) {
		//return Block.stairSingle.getBlockTextureFromSideAndMetadata(2, var1.getItemDamage());
		return 0;
	}

	public int func_21012_a(int var1) {
		return var1;
	}

	public String getItemNameIS(ItemStack var1) {
		return super.getItemName();
	}
}
