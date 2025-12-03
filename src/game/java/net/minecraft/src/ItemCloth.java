package net.minecraft.src;

public class ItemCloth extends ItemBlock {
	public ItemCloth(int var1) {
		super(var1);
		this.setMaxDamage(0);
		this.setHasSubtypes(true);
	}

	public int getIconIndex(ItemStack var1) {
		return Block.cloth.getBlockTextureFromSideAndMetadata(2, BlockCloth.func_21034_c(var1.getItemDamage()));
	}

	public int func_21012_a(int var1) {
		return var1;
	}

	public String getItemNameIS(ItemStack var1) {
		return super.getItemName() + "." + ItemDye.dyeColors[BlockCloth.func_21034_c(var1.getItemDamage())];
	}
}
